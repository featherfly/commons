
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SqlFile.java
 * @Package cn.featherfly.common.db.migration
 * @Description: read sql file
 * @author: zhongj
 * @date: 2022-03-02 14:38:02
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * SqlFile.
 *
 * @author zhongj
 */
public class SqlFile {

    /**
     * The Enum IncludeExistPolicy.
     *
     * @author zhongj
     */
    public enum IncludeExistPolicy {

        /** The ignore. */
        IGNORE,

        /** The exception. */
        EXCEPTION,

        /** The include. */
        INCLUDE;
    }

    /** The Constant INCLUDE_SYMBOL. */
    //    public static final String INCLUDE_SYMBOL = "--@include";

    /** The Constant INCLUDE_SYMBOL_PATTERN. */
    public static final Pattern INCLUDE_SYMBOL_PATTERN = Pattern.compile("(--[ ]*@include) (.+)");
    /** The Constant END_SQL_SIGN. */
    public static final String END_SQL_SIGN = ";";

    private static final Pattern CREATE_PROCEDURE_PATTERN = Pattern
        .compile("CREATE[\\s\\r\\n]+(.+[\\s\\r\\n]+)?PROCEDURE[\\s\\r\\n]+([\\w\\W]+)", Pattern.CASE_INSENSITIVE);

    private static final Pattern END_PATTERN = Pattern.compile("[\\w\\W]+END[\\s\\r\\n]*", Pattern.CASE_INSENSITIVE);

    private String file;

    private Charset charset = StandardCharsets.UTF_8;

    private Map<String, String> includeFiles = new HashMap<>();

    private List<String> sqlList = new ArrayList<>(0);

    private IncludeExistPolicy includeExistPolicy = IncludeExistPolicy.IGNORE;

    /**
     * Instantiates a new sql file.
     *
     * @param file the file
     * @param charset the charset
     */
    private SqlFile(String file, Charset charset) {
        super();
        this.file = file;
        this.charset = charset;
    }

    /**
     * get charset value.
     *
     * @return charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * Gets the include exist policy.
     *
     * @return the include exist policy
     */
    public IncludeExistPolicy getIncludeExistPolicy() {
        return includeExistPolicy;
    }

    /**
     * get includeFiles value.
     *
     * @return includeFiles
     */
    public Map<String, String> getIncludeFiles() {
        return includeFiles;
    }

    /**
     * get file value.
     *
     * @return file
     */
    public String getFile() {
        return file;
    }

    /**
     * get sqlList value.
     *
     * @return sqlList
     */
    public List<String> getSqlList() {
        return sqlList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SqlFile [file=" + file + ", charset=" + charset + ", includeFiles=" + includeFiles + ", sqlList="
            + sqlList + ", includeExistPolicy=" + includeExistPolicy + "]";
    }

    /**
     * Adds the include.
     *
     * @param source the source
     * @param include the include
     * @return true, if successful
     */
    public boolean addInclude(String source, String include) {
        if (includeFiles.containsKey(include)) {
            switch (includeExistPolicy) {
                case EXCEPTION:
                    throw new JdbcException(Strings.format("file {0} already include", include));
                case IGNORE:
                    return false;
                default:
            }
        }
        includeFiles.put(include, source);
        return true;
    }

    /**
     * Write.
     *
     * @param os the output stream
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(OutputStream os) throws IOException {
        write(os, charset);
    }

    /**
     * Write.
     *
     * @param os the os
     * @param charset the charset
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(OutputStream os, Charset charset) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(os, charset);
        for (String sql : sqlList) {
            if (Lang.isNotEmpty(sql)) {
                writer.write(sql);
                writer.write(END_SQL_SIGN);
                writer.write(Chars.NEW_LINE_CHAR);
            }
        }
        writer.flush();
        writer.close();
    }

    /**
     * Write.
     *
     * @param file the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(File file) throws IOException {
        write(file, charset);
    }

    /**
     * Write.
     *
     * @param file the file
     * @param charset the charset
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(File file, Charset charset) throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        write(os, charset);
        os.flush();
        os.close();
    }

    /**
     * Read.
     *
     * @param file the file
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(File file) throws IOException {
        return read(file, StandardCharsets.UTF_8);
    }

    /**
     * Read.
     *
     * @param resource the resource
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(URL resource) throws IOException {
        return read(resource, StandardCharsets.UTF_8);
    }

    /**
     * Read.
     *
     * @param file the file
     * @param charset the charset
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(File file, Charset charset) throws IOException {
        return read(file, charset, IncludeExistPolicy.IGNORE);
    }

    /**
     * Read.
     *
     * @param resource the resource
     * @param charset the charset
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(URL resource, Charset charset) throws IOException {
        return read(resource, charset, IncludeExistPolicy.IGNORE);
    }

    /**
     * Read.
     *
     * @param file the file
     * @param charset the charset
     * @param includeExistPolicy the include exist policy
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(File file, Charset charset, IncludeExistPolicy includeExistPolicy) throws IOException {
        return read(file, charset, includeExistPolicy, null);
    }

    /**
     * Read.
     *
     * @param resource the resource
     * @param charset the charset
     * @param includeExistPolicy the include exist policy
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(URL resource, Charset charset, IncludeExistPolicy includeExistPolicy)
        throws IOException {
        return read(resource, charset, includeExistPolicy, null);
    }

    /**
     * Read.
     *
     * @param file the file
     * @param params the params
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(File file, Map<String, Serializable> params) throws IOException {
        return read(file, StandardCharsets.UTF_8, params);
    }

    /**
     * Read.
     *
     * @param resource the resource
     * @param params the params
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(URL resource, Map<String, Serializable> params) throws IOException {
        return read(resource, StandardCharsets.UTF_8, params);
    }

    /**
     * Read.
     *
     * @param file the file
     * @param charset the charset
     * @param params the params
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(File file, Charset charset, Map<String, Serializable> params) throws IOException {
        return read(file, charset, IncludeExistPolicy.IGNORE, params);
    }

    /**
     * Read.
     *
     * @param resource the resource
     * @param charset the charset
     * @param params the params
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(URL resource, Charset charset, Map<String, Serializable> params) throws IOException {
        return read(resource, charset, IncludeExistPolicy.IGNORE, params);
    }

    /**
     * Read.
     *
     * @param file the file
     * @param charset the charset
     * @param includeExistPolicy the include exist policy
     * @param params the params
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(File file, Charset charset, IncludeExistPolicy includeExistPolicy,
        Map<String, Serializable> params) throws IOException {
        return read(file.toURI().toURL(), charset, includeExistPolicy, params);
    }

    /**
     * Read.
     *
     * @param resource the resource
     * @param charset the charset
     * @param includeExistPolicy the include exist policy
     * @param params the params
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(URL resource, Charset charset, IncludeExistPolicy includeExistPolicy,
        Map<String, Serializable> params) throws IOException {
        SqlFile sqlFile = new SqlFile(resource.getPath(), charset);
        if (includeExistPolicy != null) {
            sqlFile.includeExistPolicy = includeExistPolicy;
        }
        read(resource, charset, sqlFile, params);
        return sqlFile;
    }

    //    public static void main(String[] args) {
    //        System.out.println(CREATE_PROCEDURE_PATTERN
    //                .matcher("create DEFINER=`root`@`localhost` PROCEDURE `call_query_user`").matches());
    //        System.out.println(END_PATTERN.matcher("end").matches());
    //        System.out.println(END_PATTERN.matcher("end    ").matches());
    //        System.out.println(END_PATTERN.matcher("end\n\n").matches());
    //    }

    private static String readSql(String content, int start, AtomicInteger end) {
        StringBuilder sql = new StringBuilder();
        boolean isProcedure = false;
        for (int i = start; i < content.length(); i++) {
            char c = content.charAt(i);
            // 忽略sql开头的空白字符
            if (sql.length() == 0 && Character.isWhitespace(c)) {
                continue;
            }
            if (isProcedure) {
                if (c == ';' && END_PATTERN.matcher(sql).matches()) {
                    end.set(i + 1);
                    return sql.toString();
                } else {
                    sql.append(c);
                }
            } else {
                if (c == ';') {
                    if (CREATE_PROCEDURE_PATTERN.matcher(sql).matches()) {
                        isProcedure = true;
                        sql.append(c);
                    } else {
                        end.set(i + 1);
                        return sql.toString();
                    }
                } else {
                    sql.append(c);
                }
            }
        }
        end.set(content.length());
        return sql.toString();
    }

    private static void initSqlFile(String sql, final SqlFile sqlFile, final URL resource, final Charset charset,
        final Map<String, Serializable> params) throws IOException {
        sql = sql.trim();
        if (sql.isEmpty()) {
            return;
        }
        Matcher matcher = INCLUDE_SYMBOL_PATTERN.matcher(sql);
        if (matcher.matches()) {
            //            if (sql.startsWith(INCLUDE_SYMBOL)) {
            //                String includePath = StringUtils.substringAfter(sql, INCLUDE_SYMBOL).trim();
            String includePath = matcher.group(2).trim();

            URL includeResource = null;
            if (!includePath.startsWith("/")) {
                File file = new File(resource.getPath());
                String path = file.getParent() + "/" + includePath;
                File includeFile = new File(path);
                if (includeFile.exists()) {
                    includeResource = includeFile.toURI().toURL();
                }
            }
            if (includeResource == null) {
                includeResource = ClassLoaderUtils.getResource(includePath);
            }

            if (includeResource == null) {
                throw new IllegalArgumentException(
                    Strings.format("can not found {0} in filepath and classpath ", includePath));
            }

            sqlFile.getSqlList().add("\n-- include  " + includePath + " start");
            if (sqlFile.addInclude(resource.getFile(), includeResource.getFile())) {
                read(includeResource, charset, sqlFile, params);
            }
            sqlFile.getSqlList().add("\n-- include  " + includePath + " end");
        } else {
            sqlFile.getSqlList().add(sql);
        }
    }

    private static void read(final URL resource, Charset charset, final SqlFile sqlFile,
        final Map<String, Serializable> params) throws IOException {
        String content = IOUtils.toString(resource, charset);
        if (Lang.isNotEmpty(params)) {
            content = Strings.format(content, params);
        }

        AtomicInteger index = new AtomicInteger(0);
        do {
            initSqlFile(readSql(content, index.get(), index), sqlFile, resource, charset, params);
        } while (index.get() < content.length());

        //        String[] sqls = content.split(";");
        //        for (String sql : sqls) {
        //            sql = sql.trim();
        //            Matcher matcher = INCLUDE_SYMBOL_PATTERN.matcher(sql);
        //            if (matcher.matches()) {
        //                //            if (sql.startsWith(INCLUDE_SYMBOL)) {
        //                //                String includePath = StringUtils.substringAfter(sql, INCLUDE_SYMBOL).trim();
        //                String includePath = matcher.group(2).trim();
        //
        //                URL includeResource = null;
        //                if (!includePath.startsWith("/")) {
        //                    File file = new File(resource.getPath());
        //                    String path = file.getParent() + "/" + includePath;
        //                    File includeFile = new File(path);
        //                    if (includeFile.exists()) {
        //                        includeResource = includeFile.toURI().toURL();
        //                    }
        //                }
        //                if (includeResource == null) {
        //                    includeResource = ClassLoaderUtils.getResource(includePath);
        //                }
        //
        //                if (includeResource == null) {
        //                    throw new IllegalArgumentException(
        //                            Strings.format("can not found {0} in filepath and classpath ", includePath));
        //                }
        //
        //                sqlFile.getSqlList().add("\n-- include  " + includePath + " start");
        //                if (sqlFile.addInclude(resource.getFile(), includeResource.getFile())) {
        //                    read(includeResource, charset, sqlFile, params);
        //                }
        //                sqlFile.getSqlList().add("\n-- include  " + includePath + " end");
        //            } else {
        //                sqlFile.getSqlList().add(sql);
        //            }
        //        }
    }
}
