package cn.featherfly.common.lang;

/**
 * <p>
 * 系统默认属性工具类
 * </p>
 *
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public final class SystemPropertyUtils {

    /**
     */
    private SystemPropertyUtils() {
    }

    private static final String JAVA_VERSION = "java.version";
    private static final String JAVA_VENDOR = "java.vendor";
    private static final String JAVA_VENDOR_URL = "java.vendor.url";
    private static final String JAVA_HOME = "java.home";
    private static final String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";
    private static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";
    private static final String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";
    private static final String JAVA_VM_VERSION = "java.vm.version";
    private static final String JAVA_VM_VENDOR = "java.vm.vendor";
    private static final String JAVA_VM_NAME = "java.vm.name";
    private static final String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor";
    private static final String JAVA_SPECIFICATION_NAME = "java.specification.name";
    private static final String JAVA_CLASS_VERSION = "java.class.version";
    private static final String JAVA_CLASS_PATH = "java.class.path";
    private static final String JAVA_LIBRARY_PATH = "java.library.path";
    private static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
    private static final String JAVA_IO_TEMDIR = "java.io.tmpdir";
    private static final String JAVA_COMPILER = "java.compiler";
    private static final String JAVA_EXT_DIRS = "java.ext.dirs";
    private static final String OS_NAME = "os.name";
    private static final String OS_VERSION = "os.version";
    private static final String OS_ARCH = "os.arch";
    private static final String FILE_SEPARATOR = "file.separator";
    private static final String PATH_SEPARATOR = "path.separator";
    private static final String LINE_SEPARATOR = "line.separator";
    private static final String USER_NAME = "user.name";
    private static final String USER_HOME = "user.home";
    private static final String USER_DIR = "user.dir";

    //--------------------------------------------------------
    // getter
    //--------------------------------------------------------

    /**
     * <p>
     * 返回 JavaLibraryPath
     * </p>
     *
     * @return JavaLibraryPath
     */
    public static String getJavaLibraryPath() {
        return System.getProperty(JAVA_LIBRARY_PATH);
    }

    /**
     * <p>
     * 返回 JavaClassPath
     * </p>
     *
     * @return JavaClassPath
     */
    public static String getJavaClassPath() {
        return System.getProperty(JAVA_CLASS_PATH);
    }

    /**
     * <p>
     * 返回 JavaIoTmpdir
     * </p>
     *
     * @return JavaIoTmpdir
     */
    public static String getJavaIoTmpdir() {
        return System.getProperty(JAVA_IO_TEMDIR);
    }

    /**
     * <p>
     * 返回 JavaVersion
     * </p>
     *
     * @return JavaVersion
     */
    public static String getJavaVersion() {
        return System.getProperty(JAVA_VERSION);
    }

    /**
     * <p>
     * 返回 JavaVendor
     * </p>
     *
     * @return JavaVendor
     */
    public static String getJavaVendor() {
        return System.getProperty(JAVA_VENDOR);
    }

    /**
     * <p>
     * 返回 JavaVendorUrl
     * </p>
     *
     * @return JavaVendorUrl
     */
    public static String getJavaVendorUrl() {
        return System.getProperty(JAVA_VENDOR_URL);
    }

    /**
     * <p>
     * 返回 JavaHome
     * </p>
     *
     * @return JavaHome
     */
    public static String getJavaHome() {
        return System.getProperty(JAVA_HOME);
    }

    /**
     * <p>
     * 返回 JavaVmSpecificationVersion
     * </p>
     *
     * @return JavaVmSpecificationVersion
     */
    public static String getJavaVmSpecificationVersion() {
        return System.getProperty(JAVA_VM_SPECIFICATION_VERSION);
    }

    /**
     * <p>
     * 返回 JavaVmSpecificationVendor
     * </p>
     *
     * @return JavaVmSpecificationVendor
     */
    public static String getJavaVmSpecificationVendor() {
        return System.getProperty(JAVA_VM_SPECIFICATION_VENDOR);
    }

    /**
     * <p>
     * 返回 JavaVmSpecificationName
     * </p>
     *
     * @return JavaVmSpecificationName
     */
    public static String getJavaVmSpecificationName() {
        return System.getProperty(JAVA_VM_SPECIFICATION_NAME);
    }

    /**
     * <p>
     * 返回 JavaVmVersion
     * </p>
     *
     * @return JavaVmVersion
     */
    public static String getJavaVmVersion() {
        return System.getProperty(JAVA_VM_VERSION);
    }

    /**
     * <p>
     * 返回 JavaVmVendor
     * </p>
     *
     * @return JavaVmVendor
     */
    public static String getJavaVmVendor() {
        return System.getProperty(JAVA_VM_VENDOR);
    }

    /**
     * <p>
     * 返回 JavaVmName
     * </p>
     *
     * @return JavaVmName
     */
    public static String getJavaVmName() {
        return System.getProperty(JAVA_VM_NAME);
    }

    /**
     * <p>
     * 返回 JavaSpecificationVersion
     * </p>
     *
     * @return JavaSpecificationVersion
     */
    public static String getJavaSpecificationVersion() {
        return System.getProperty(JAVA_SPECIFICATION_VERSION);
    }

    /**
     * <p>
     * 返回 JavaSpecificationVendor
     * </p>
     *
     * @return JavaSpecificationVendor
     */
    public static String getJavaSpecificationVendor() {
        return System.getProperty(JAVA_SPECIFICATION_VENDOR);
    }

    /**
     * <p>
     * 返回 JavaSpecificationName
     * </p>
     *
     * @return JavaSpecificationName
     */
    public static String getJavaSpecificationName() {
        return System.getProperty(JAVA_SPECIFICATION_NAME);
    }

    /**
     * <p>
     * 返回 JavaClassVersion
     * </p>
     *
     * @return JavaClassVersion
     */
    public static String getJavaClassVersion() {
        return System.getProperty(JAVA_CLASS_VERSION);
    }

    /**
     * <p>
     * 返回 JavaCompiler
     * </p>
     *
     * @return JavaCompiler
     */
    public static String getJavaCompiler() {
        return System.getProperty(JAVA_COMPILER);
    }

    /**
     * <p>
     * 返回 ExtDirs
     * </p>
     *
     * @return ExtDirs
     */
    public static String getExtDirs() {
        return System.getProperty(JAVA_EXT_DIRS);
    }

    /**
     * <p>
     * 返回 OsName
     * </p>
     *
     * @return OsName
     */
    public static String getOsName() {
        return System.getProperty(OS_NAME);
    }

    /**
     * <p>
     * 返回 OsArch
     * </p>
     *
     * @return OsArch
     */
    public static String getOsArch() {
        return System.getProperty(OS_ARCH);
    }

    /**
     * <p>
     * 返回 OsVersion
     * </p>
     *
     * @return OsVersion
     */
    public static String getOsVersion() {
        return System.getProperty(OS_VERSION);
    }

    /**
     * <p>
     * 返回 FileSeparator
     * </p>
     *
     * @return FileSeparator
     */
    public static String getFileSeparator() {
        return System.getProperty(FILE_SEPARATOR);
    }

    /**
     * <p>
     * 返回 PathSeparator
     * </p>
     *
     * @return PathSeparator
     */
    public static String getPathSeparator() {
        return System.getProperty(PATH_SEPARATOR);
    }

    /**
     * <p>
     * 返回 LineSeparator
     * </p>
     *
     * @return LineSeparator
     */
    public static String getLineSeparator() {
        return System.getProperty(LINE_SEPARATOR);
    }

    /**
     * <p>
     * 返回 UserName
     * </p>
     *
     * @return UserName
     */
    public static String getUserName() {
        return System.getProperty(USER_NAME);
    }

    /**
     * <p>
     * 返回 UserHome
     * </p>
     *
     * @return UserHome
     */
    public static String getUserHome() {
        return System.getProperty(USER_HOME);
    }

    /**
     * <p>
     * 返回 UserDir
     * </p>
     *
     * @return UserDir
     */
    public static String getUserDir() {
        return System.getProperty(USER_DIR);
    }

    /**
     * <p>
     * 返回 key对应的属性
     * </p>
     *
     * @param key key
     * @return key对应的属性
     */
    public static String getProperty(String key) {
        return System.getProperty(key);
    }

    //    --------------------------------------------------------
    // setter
    //--------------------------------------------------------
    /**
     * <p>
     * 设置javaLibraryPath
     * </p>
     *
     * @param javaLibraryPath javaLibraryPath
     * @return 设置之前的javaLibraryPath
     */
    public static String setJavaLibraryPath(String javaLibraryPath) {
        return System.setProperty(JAVA_LIBRARY_PATH, javaLibraryPath);
    }

    /**
     * <p>
     * 设置javaclassPath
     * </p>
     *
     * @param javaclassPath javaclassPath
     * @return 设置之前的javaclassPath
     */
    public static String setJavaClassPath(String javaclassPath) {
        return System.setProperty(JAVA_CLASS_PATH, javaclassPath);
    }

    /**
     * <p>
     * 设置javaIoTmpdir
     * </p>
     *
     * @param javaIoTmpdir javaIoTmpdir
     * @return 设置之前的javaIoTmpdir
     */
    public static String setJavaIoTmpdir(String javaIoTmpdir) {
        return System.setProperty(JAVA_IO_TEMDIR, javaIoTmpdir);
    }

    /*
     * public static String setJavaCompiler(String javaCompiler) { return
     * System.setProperty(JAVA_COMPILER, javaCompiler); } public static String
     * setExtDirs(String extDirs) { return System.setProperty(JAVA_EXT_DIRS,
     * extDirs); } public static String setOsName(String osName) { return
     * System.setProperty(OS_NAME, osName); } public static String
     * setOsArch(String osArch) { return System.setProperty(OS_ARCH, osArch); }
     * public static String setOsVersion(String osVersion) { return
     * System.setProperty(OS_VERSION, osVersion); } public static String
     * setFileSeparator(String fileSeparator) { return
     * System.setProperty(FILE_SEPARATOR, fileSeparator); } public static String
     * setPathSeparator(String pathSeparator) { return
     * System.setProperty(PATH_SEPARATOR, pathSeparator); } public static String
     * setLineSeparator(String lineSeparator) { return
     * System.setProperty(LINE_SEPARATOR, lineSeparator); } public static String
     * setUserName(String userName) { return System.setProperty(USER_NAME,
     * userName); } public static String setUserHome(String userHome) { return
     * System.setProperty(USER_HOME, userHome); } public static String
     * setUserDir(String userDir) { return System.setProperty(USER_DIR,
     * userDir); }
     */
    /**
     * <p>
     * 设置环境变量
     * </p>
     *
     * @param key   key
     * @param value value
     * @return 对应key设置前的value值
     */
    public static String setProperty(String key, String value) {
        return System.setProperty(key, value);
    }

    /**
     * <p>
     * 添加环境变量，如果指定key已经存在值，则再添加到最后，使用PathSeparator隔离（windows ; linux :）
     * </p>
     *
     * @param key   key
     * @param value value
     * @return 对应key设置前的value值
     */
    public static String addProperty(String key, String value) {
        String pv = getProperty(key);
        if (LangUtils.isNotEmpty(pv)) {
            value = pv + getPathSeparator() + value;
        }
        return System.setProperty(key, value);
    }

    /**
     * 返回是否是windows操作系统
     * 
     * @return boolean
     */
    public static boolean isWindows() {
        return SystemPropertyUtils.getOsName().startsWith("Windows");
    }

    /**
     * 返回是否是linux操作系统
     * 
     * @return boolean
     */
    public static boolean isLinux() {
        return SystemPropertyUtils.getOsName().startsWith("Linux");
    }
}
