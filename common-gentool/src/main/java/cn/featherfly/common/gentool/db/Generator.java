
package cn.featherfly.common.gentool.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.gentool.Module;
import cn.featherfly.common.gentool.db.method.SqlTypeToJavaTypeMethod;
import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.UriUtils;
import cn.featherfly.common.lang.WordUtils;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/**
 * Generator.
 *
 * @author zhongj
 */
public class Generator {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /** The config. */
    DbGenerateConfig config;

    /** The cfg. */
    Configuration cfg;

    /** The matcher. */
    AntPathMatcher matcher = new AntPathMatcher();

    /**
     * Instantiates a new generator.
     *
     * @param config the config
     */
    protected Generator(DbGenerateConfig config) {
        super();
        this.config = config;

        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
    }

    /**
     * Generate.
     *
     * @throws Exception the exception
     */
    public void generate() throws Exception {
        ConnectionWrapper connection = JdbcUtils.getConnection(config.driverClassName, config.url, config.username,
            config.password);

        DatabaseMetadata dbmd = DatabaseMetadataManager.getDefaultManager().create(connection);
        Map<String, List<Table>> moduleTableMap = new HashMap<>();
        for (Module module : config.modules) {
            if (module.moduleName == null) {
                module.moduleName = "";
            }
            List<Table> moduleList = new ArrayList<>();
            for (Table table : dbmd.getTables()) {
                boolean exclude = false;
                if (Lang.isNotEmpty(module.excludes)) {
                    for (String pattern : module.includes) {
                        if (matcher.match(pattern, table.getName())) {
                            exclude = true;
                            break;
                        }
                    }
                }
                if (exclude) {
                    break;
                }
                if (Lang.isEmpty(module.includes)) {
                    moduleList.add(table);
                } else {
                    for (String pattern : module.includes) {
                        if (matcher.match(pattern, table.getName())) {
                            moduleList.add(table);
                            break;
                        }
                    }
                }
            }
            if (moduleTableMap.containsKey(module.moduleName)) {
                throw new IllegalArgumentException("duplicate moduleName [" + module.moduleName + "]");
            }
            moduleTableMap.put(module.moduleName, moduleList);
        }

        for (DbModule module : config.modules) {
            generateDatabaseModel(module, moduleTableMap.get(module.moduleName));
            generateEntity(module, moduleTableMap.get(module.moduleName));
        }
    }

    private void generateEntity(DbModule module, List<Table> tables) throws TemplateNotFoundException,
        MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Date createTime = new Date();
        if (module.generateEntity != null && module.generateEntity || config.generateEntity) {
            if (config.generateDbModel) {
                Map<String, Object> root = new HashMap<>();
                //            root.put("createTime", createTime);
                //            root.put("packageName", module.packageName);
                //            root.put("author", module.author);
                //            if (Lang.isNotEmpty(module.moduleName)) {
                //                root.put("moduleName", module.moduleName);
                //            }
                //            root.put("tables", tables);

                for (Table table : tables) {
                    root = new HashMap<>();
                    root.put("createTime", createTime);
                    root.put("packageName", module.packageName);
                    root.put("author", module.author);
                    if (Lang.isNotEmpty(module.moduleName)) {
                        root.put("moduleName", module.moduleName);
                    }
                    SqlTypeMappingManager manager = new SqlTypeMappingManager();
                    root.put("table", table);
                    root.put("sql_java", new SqlTypeToJavaTypeMethod(manager));
                    Template temp = cfg.getTemplate(UriUtils.linkUri(config.getTemplateDir(), "/entity.ftl"));
                    Writer out = getWriter(UriUtils.linkUri(config.getJavaSrcDir(),
                        ClassUtils.packageToDir(module.packageName),
                        WordUtils.upperCaseFirst(WordUtils.parseToUpperFirst(table.getName(), Chars.UNDER_LINE_CHAR))
                            + ".java"));
                    temp.process(root, out);
                    // generate entity
                }
            }
        }
    }

    private void generateDatabaseModel(DbModule module, List<Table> tables) throws TemplateNotFoundException,
        MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Date createTime = new Date();
        if (module.generateDbModel != null && module.generateDbModel || config.generateDbModel) {
            Map<String, Object> root = new HashMap<>();
            root.put("createTime", createTime);
            root.put("packageName", module.packageName);
            root.put("author", getAuthor(module));
            if (Lang.isNotEmpty(module.moduleName)) {
                root.put("moduleName", module.moduleName);
            }
            root.put("tables", tables);
            Writer out = getWriter(UriUtils.linkUri(config.getJavaSrcDir(), ClassUtils.packageToDir(module.packageName),
                WordUtils.upperCaseFirst(WordUtils.parseToUpperFirst(module.moduleName, Chars.UNDER_LINE_CHAR))
                    + "Tables.java"));
            Template temp = cfg.getTemplate(UriUtils.linkUri(config.getTemplateDir(), "/tables.ftl"));
            temp.process(root, out);
            // generate tables

            for (Table tableModel : tables) {
                root = new HashMap<>();
                root.put("createTime", createTime);
                root.put("packageName", module.packageName);
                root.put("author", getAuthor(module));
                if (Lang.isNotEmpty(module.moduleName)) {
                    root.put("moduleName", module.moduleName);
                }
                root.put("table", tableModel);
                out = getWriter(UriUtils.linkUri(config.getJavaSrcDir(), ClassUtils.packageToDir(module.packageName),
                    WordUtils.upperCaseFirst(WordUtils.parseToUpperFirst(tableModel.getName(), Chars.UNDER_LINE_CHAR))
                        + "Table.java"));
                temp = cfg.getTemplate(UriUtils.linkUri(config.getTemplateDir(), "/table.ftl"));
                temp.process(root, out);
                // generate table
            }
        }
    }

    private String getAuthor(DbModule module) {
        return Lang.isEmpty(module.author) ? config.author : module.author;
    }

    private Writer getWriter(String path) throws FileNotFoundException {
        File file = new File(new File(path).getAbsolutePath());
        FileUtils.makeDirectory(file);
        return new OutputStreamWriter(new FileOutputStream(file), config.getCharset());
    }
}
