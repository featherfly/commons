
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
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.ClassLoaderUtils;
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
    public static final String INCLUDE_SYMBOL = "--@include";

    /** The Constant END_SQL_SIGN. */
    public static final String END_SQL_SIGN = ";";

    private String file;

    private Charset charset = StandardCharsets.UTF_8;

    private Map<String, String> includeFiles = new HashMap<>();

    private List<String> sqlList = new ArrayList<>(0);

    private IncludeExistPolicy includeExistPolicy = IncludeExistPolicy.IGNORE;

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
     * * @param file.
     *
     * @param file the file
     */
    public SqlFile(String file) {
        super();
        this.file = file;
    }

    /**
     * Instantiates a new sql file.
     *
     * @param file    the file
     * @param charset the charset
     */
    public SqlFile(String file, Charset charset) {
        super();
        this.file = file;
        this.charset = charset;
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
     * @param source  the source
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
     * @param os      the os
     * @param charset the charset
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(OutputStream os, Charset charset) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(os, charset);
        for (String sql : sqlList) {
            writer.write(sql);
            writer.write(END_SQL_SIGN);
            writer.write(Chars.NEW_LINE_CHAR);
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
     * @param file    the file
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
     * @param file    the file
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
     * @param file               the file
     * @param charset            the charset
     * @param includeExistPolicy the include exist policy
     * @return the sql file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static SqlFile read(File file, Charset charset, IncludeExistPolicy includeExistPolicy) throws IOException {
        SqlFile sqlFile = new SqlFile(file.getAbsolutePath(), charset);
        if (includeExistPolicy != null) {
            sqlFile.includeExistPolicy = includeExistPolicy;
        }
        read(file, charset, sqlFile);
        return sqlFile;
    }

    private static void read(File file, Charset charset, final SqlFile sqlFile) throws IOException {
        read(file.toURI().toURL(), charset, sqlFile);
    }

    private static void read(URL resource, Charset charset, final SqlFile sqlFile) throws IOException {
        String content = IOUtils.toString(resource, charset);
        String[] sqls = content.split(";");
        for (String sql : sqls) {
            sql = sql.trim();
            if (sql.startsWith(INCLUDE_SYMBOL)) {
                //                System.out.println(sql);
                String includePath = StringUtils.substringAfter(sql, INCLUDE_SYMBOL).trim();
                //                System.out.println(includeFile);
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

                sqlFile.getSqlList().add("\n-- include  " + includePath + " start");
                if (sqlFile.addInclude(resource.getFile(), includeResource.getFile())) {
                    read(includeResource, charset, sqlFile);
                }
                sqlFile.getSqlList().add("\n-- include  " + includePath + " end");
            } else {
                sqlFile.getSqlList().add(sql);
            }
        }
    }
}
