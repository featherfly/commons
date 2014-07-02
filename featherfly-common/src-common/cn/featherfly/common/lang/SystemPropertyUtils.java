package cn.featherfly.common.lang;

/**
 * <p>
 * 系统默认属性工具类
 * </p>
 * @author 钟冀
 * @since 1.0
 * @version 1.0
 */
public class SystemPropertyUtils {

    public static final String JAVA_VERSION = "java.version";
    public static final String JAVA_VENDOR = "java.vendor";
    public static final String JAVA_VENDOR_URL = "java.vendor.url";
    public static final String JAVA_HOME = "java.home";
    public static final String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";
    public static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";
    public static final String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";
    public static final String JAVA_VM_VERSION = "java.vm.version";
    public static final String JAVA_VM_VENDOR = "java.vm.vendor";
    public static final String JAVA_VM_NAME = "java.vm.name";
    public static final String JAVA_SPECIFICATION_VENDOR = "java.specification.vendor";
    public static final String JAVA_SPECIFICATION_NAME = "java.specification.name";
    public static final String JAVA_CLASS_VERSION = "java.class.version";
    public static final String JAVA_CLASS_PATH = "java.class.path";
    public static final String JAVA_LIBRARY_PATH = "java.library.path";
    public static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
    public static final String JAVA_IO_TEMDIR= "java.io.tmpdir";
    public static final String JAVA_COMPILER= "java.compiler";
    public static final String JAVA_EXT_DIRS= "java.ext.dirs";
    public static final String OS_NAME= "os.name";
    public static final String OS_VERSION= "os.version";
    public static final String OS_ARCH= "os.arch";
    public static final String FILE_SEPARATOR= "file.separator";
    public static final String PATH_SEPARATOR= "path.separator";
    public static final String LINE_SEPARATOR= "line.separator";
    public static final String USER_NAME = "user.name";
    public static final String USER_HOME = "user.home";
    public static final String USER_DIR = "user.dir";

  /*  public static void main(String[] args) {
        System.out.println(getJavaCompiler());
        System.out.println(getJavaSpecificationName());
        System.out.println(getJavaSpecificationVendor());
        System.out.println(getJavaSpecificationVersion());
        System.out.println(getJavaVendor());
        System.out.println(getJavaVendorUrl());
        System.out.println(getJavaVmName());
        System.out.println(getJavaVmSpecificationName());
        System.out.println(getJavaVmSpecificationVendor());
        System.out.println(getJavaVmSpecificationVersion());
        System.out.println(getJavaVmVendor());
        System.out.println(getJavaVmVersion());
    }*/

    //--------------------------------------------------------
    // getter
    //--------------------------------------------------------

    public static String getJavaLibraryPath(){
        return System.getProperty(JAVA_LIBRARY_PATH);
    }

    public static String getJavaClassPath(){
        return System.getProperty(JAVA_CLASS_PATH);
    }

    public static String getJavaIoTmpdir(){
        return System.getProperty(JAVA_IO_TEMDIR);
    }

    public static String getJavaVersion(){
        return System.getProperty(JAVA_VERSION);
    }

    public static String getJavaVendor(){
        return System.getProperty(JAVA_VENDOR);
    }

    public static String getJavaVendorUrl(){
        return System.getProperty(JAVA_VENDOR_URL);
    }

    public static String getJavaHome(){
        return System.getProperty(JAVA_HOME);
    }

    public static String getJavaVmSpecificationVersion(){
        return System.getProperty(JAVA_VM_SPECIFICATION_VERSION);
    }

    public static String getJavaVmSpecificationVendor(){
        return System.getProperty(JAVA_VM_SPECIFICATION_VENDOR);
    }

    public static String getJavaVmSpecificationName(){
        return System.getProperty(JAVA_VM_SPECIFICATION_NAME);
    }

    public static String getJavaVmVersion(){
        return System.getProperty(JAVA_VM_VERSION);
    }

    public static String getJavaVmVendor(){
        return System.getProperty(JAVA_VM_VENDOR);
    }

    public static String getJavaVmName(){
        return System.getProperty(JAVA_VM_NAME);
    }

    public static String getJavaSpecificationVersion(){
        return System.getProperty(JAVA_SPECIFICATION_VERSION);
    }

    public static String getJavaSpecificationVendor(){
        return System.getProperty(JAVA_SPECIFICATION_VENDOR);
    }

    public static String getJavaSpecificationName(){
        return System.getProperty(JAVA_SPECIFICATION_NAME);
    }

    public static String getJavaClassVersion(){
        return System.getProperty(JAVA_CLASS_VERSION);
    }

    public static String getJavaCompiler(){
        return System.getProperty(JAVA_COMPILER);
    }

    public static String getExtDirs(){
        return System.getProperty(JAVA_EXT_DIRS);
    }

    public static String getOsName(){
        return System.getProperty(OS_NAME);
    }

    public static String getOsArch(){
        return System.getProperty(OS_ARCH);
    }

    public static String getOsVersion(){
        return System.getProperty(OS_VERSION);
    }

    public static String getFileSeparator(){
        return System.getProperty(FILE_SEPARATOR);
    }

    public static String getPathSeparator(){
        return System.getProperty(PATH_SEPARATOR);
    }

    public static String getLineSeparator(){
        return System.getProperty(LINE_SEPARATOR);
    }

    public static String getUserName(){
        return System.getProperty(USER_NAME);
    }

    public static String getUserHome(){
        return System.getProperty(USER_HOME);
    }

    public static String getUserDir(){
        return System.getProperty(USER_DIR);
    }

    public static String getProperty(String key){
        return System.getProperty(key);
    }

//	--------------------------------------------------------
    // setter
    //--------------------------------------------------------

    public static String setJavaLibraryPath(String javaLibraryPath){
        return System.setProperty(JAVA_LIBRARY_PATH,javaLibraryPath);
    }

    public static String setJavaClassPath(String javaclassPath){
        return System.setProperty(JAVA_CLASS_PATH,javaclassPath);
    }

    public static String setJavaIoTmpdir(String javaIoTmpdir){
        return System.setProperty(JAVA_IO_TEMDIR,javaIoTmpdir);
    }

    public static String setJavaVersion(String javaVersion){
        return System.setProperty(JAVA_VERSION,javaVersion);
    }

    public static String setJavaVendor(String javaVendor){
        return System.setProperty(JAVA_VENDOR,javaVendor);
    }

    public static String setJavaVendorUrl(String javaVendorUrl){
        return System.setProperty(JAVA_VENDOR_URL,javaVendorUrl);
    }

    public static String setJavaHome(String javaHome){
        return System.setProperty(JAVA_HOME,javaHome);
    }

    public static String setJavaVmSpecificationVersion(String javaVmSpecificationVersion){
        return System.setProperty(JAVA_VM_SPECIFICATION_VERSION,javaVmSpecificationVersion);
    }

    public static String setJavaVmSpecificationVendor(String javaVmSpecificationVendor){
        return System.setProperty(JAVA_VM_SPECIFICATION_VENDOR,javaVmSpecificationVendor);
    }

    public static String setJavaVmSpecificationName(String javaVmSpecificationName){
        return System.setProperty(JAVA_VM_SPECIFICATION_NAME,javaVmSpecificationName);
    }

    public static String setJavaVmVersion(String javaVmVersion){
        return System.setProperty(JAVA_VM_VERSION,javaVmVersion);
    }

    public static String setJavaVmVendor(String javaVmVendor){
        return System.setProperty(JAVA_VM_VENDOR,javaVmVendor);
    }

    public static String setJavaVmName(String javaVmName){
        return System.setProperty(JAVA_VM_NAME,javaVmName);
    }

    public static String setJavaSpecificationVersion(String javaSpecificationVersion){
        return System.setProperty(JAVA_SPECIFICATION_VERSION,javaSpecificationVersion);
    }

    public static String setJavaSpecificationVendor(String javaSpecificationVendor){
        return System.setProperty(JAVA_SPECIFICATION_VENDOR,javaSpecificationVendor);
    }

    public static String setJavaSpecificationName(String javaSpecificationName){
        return System.setProperty(JAVA_SPECIFICATION_NAME,javaSpecificationName);
    }

    public static String setJavaClassVersion(String javaClassVersion){
        return System.setProperty(JAVA_CLASS_VERSION,javaClassVersion);
    }

    public static String setJavaCompiler(String javaCompiler){
        return System.setProperty(JAVA_COMPILER,javaCompiler);
    }

    public static String setExtDirs(String extDirs){
        return System.setProperty(JAVA_EXT_DIRS,extDirs);
    }

    public static String setOsName(String osName){
        return System.setProperty(OS_NAME,osName);
    }

    public static String setOsArch(String osArch){
        return System.setProperty(OS_ARCH,osArch);
    }

    public static String setOsVersion(String osVersion){
        return System.setProperty(OS_VERSION,osVersion);
    }

    public static String setFileSeparator(String fileSeparator){
        return System.setProperty(FILE_SEPARATOR,fileSeparator);
    }

    public static String setPathSeparator(String pathSeparator){
        return System.setProperty(PATH_SEPARATOR,pathSeparator);
    }

    public static String setLineSeparator(String lineSeparator){
        return System.setProperty(LINE_SEPARATOR,lineSeparator);
    }

    public static String setUserName(String userName){
        return System.setProperty(USER_NAME,userName);
    }

    public static String setUserHome(String userHome){
        return System.setProperty(USER_HOME,userHome);
    }

    public static String setUserDir(String userDir){
        return System.setProperty(USER_DIR,userDir);
    }

    public static String setProperty(String key,String value){
        return System.setProperty(key,value);
    }
}
