
package cn.featherfly.common.db.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.db.migration.Migrator.ModifyType;
import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.io.Properties;
import cn.featherfly.common.io.PropertiesImpl;
import cn.featherfly.common.lang.Dates;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * VersionManager
 * </p>
 *
 * @author zhongj
 */
public class VersionManager {

    private static final String VERSION_FILE_NAME = ".versions.properties";

    /** The Constant DEFAULT_INIT_VERSION. */
    public static final Version DEFAULT_INIT_VERSION = new Version(1, 0, 0);

    /** The Constant INIT_VERSION_KEY. */
    public static final String INIT_VERSION_KEY = "init_version";

    /** The Constant CURRENT_VERSION_KEY. */
    public static final String CURRENT_VERSION_KEY = "current_version";

    private File dir;

    private File file;

    private Properties properties;

    private String name;

    private Migrator migrator;

    private Version current;

    private Version init;

    private boolean dropNotExist = true;

    private ModifyType modifyType = ModifyType.MODIFY;

    /**
     * Instantiates a new version manager.
     *
     * @param dir      the dir
     * @param name     the file name
     * @param migrator the migrator
     */
    public VersionManager(File dir, String name, Migrator migrator) {
        this(dir, name, DEFAULT_INIT_VERSION, migrator);
    }

    /**
     * Instantiates a new version manager.
     *
     * @param dir         the dir
     * @param name        the file name
     * @param initVersion the init version
     * @param migrator    the migrator
     */
    public VersionManager(File dir, String name, Version initVersion, Migrator migrator) {
        this.name = name;
        this.migrator = migrator;
        this.dir = dir;
        if (dir == null) {
            throw new IllegalArgumentException(dir + " can not be null");
        }
        if (Lang.isNotExists(dir)) {
            FileUtils.makeDirectory(dir);
        }
        file = new File(dir.getAbsolutePath() + "/" + VERSION_FILE_NAME);
        init = initVersion;
        properties = new PropertiesImpl(StandardCharsets.UTF_8);
        if (Lang.isExists(file)) {
            try {
                properties.load(new FileInputStream(file));
            } catch (IOException e) {
                Lang.wrapThrow(e);
            }
        }
        properties.setProperty(INIT_VERSION_KEY, init.getVersion());
        String currentVersoin = properties.getProperty(CURRENT_VERSION_KEY);
        if (Lang.isNotEmpty(currentVersoin)) {
            current = Version.create(currentVersoin);
        }
        store();
    }

    private void store() {
        try (OutputStream os = new FileOutputStream(file)) {
            properties.store(os);
        } catch (IOException e) {
            Lang.wrapThrow(e);
        }
    }

    /**
     * Gets the current version.
     *
     * @return the current version
     */
    public String getCurrentVersion() {
        if (current == null) {
            return init.getVersion();
        } else {
            return current.getVersion();
        }
    }

    /**
     * Next.
     *
     * @param classMappings the class mappings
     * @return the file
     */
    public File next(Set<JdbcClassMapping<?>> classMappings) {
        String version = null;
        File f = null;
        if (current == null) {
            version = init.getVersion();
            f = createInitSqlFile(version, classMappings);
        } else {
            version = current.getNextMinorVersion();
            f = createUpdateSqlFile(version, classMappings);
        }
        properties.setProperty(CURRENT_VERSION_KEY, version);
        properties.setProperty(version, Dates.formatTime(new Date()));
        store();
        return f;
    }

    /**
     * Next.
     *
     * @param classMappings the class mappings
     * @param next          the next
     * @return the file
     */
    public File next(Set<JdbcClassMapping<?>> classMappings, Version next) {
        String version = null;
        File f = null;
        if (current == null) {
            version = init.getVersion();
            f = createInitSqlFile(version, classMappings);
        } else {
            version = next.getVersion();
            f = createUpdateSqlFile(version, classMappings);
        }
        properties.setProperty(CURRENT_VERSION_KEY, version);
        properties.setProperty(version, Dates.formatTime(new Date()));
        store();
        return f;
    }

    private String name(String version, String type) {
        String extName = FileUtils.getFileExtName(name);
        if (Lang.isEmpty(extName)) {
            extName = "sql";
        }
        return name + "-" + version + "-" + type + "." + extName;
    }

    /**
     * Creates the init sql file.
     *
     * @param version       the version
     * @param classMappings the class mappings
     * @return the file
     */
    public File createInitSqlFile(String version, Set<JdbcClassMapping<?>> classMappings) {
        File file = new File(dir.getPath() + "/" + name(version, "init"));
        try {
            org.apache.commons.io.FileUtils.write(file, migrator.initSql(classMappings), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Lang.wrapThrow(e);
        }
        return file;
    }

    /**
     * Creates the update sql file.
     *
     * @param version       the version
     * @param classMappings the class mappings
     * @return the file
     */
    public File createUpdateSqlFile(String version, Set<JdbcClassMapping<?>> classMappings) {
        File file = new File(dir.getPath() + "/" + name(version, "update"));
        try {
            org.apache.commons.io.FileUtils.write(file, migrator.updateSql(classMappings, modifyType, dropNotExist),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            Lang.wrapThrow(e);
        }
        return file;
    }

    /**
     * 返回dropNotExist
     *
     * @return dropNotExist
     */
    public boolean isDropNotExist() {
        return dropNotExist;
    }

    /**
     * 设置dropNotExist
     *
     * @param dropNotExist dropNotExist
     */
    public void setDropNotExist(boolean dropNotExist) {
        this.dropNotExist = dropNotExist;
    }

    /**
     * 返回modifyType
     *
     * @return modifyType
     */
    public ModifyType getModifyType() {
        return modifyType;
    }

    /**
     * 设置modifyType
     *
     * @param modifyType modifyType
     */
    public void setModifyType(ModifyType modifyType) {
        this.modifyType = modifyType;
    }
}