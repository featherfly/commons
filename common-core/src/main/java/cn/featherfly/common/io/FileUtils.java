package cn.featherfly.common.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.algorithm.CRC;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.LogUtils;

/**
 * <p>
 * 文件操作类<br>
 * 此类中封装一些常用的文件操作<br>
 * </p>
 * .
 *
 * @author zhongj
 */
public final class FileUtils extends org.apache.commons.io.FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    private static final Map<String, Long> FILES = new HashMap<>();

    // 私有构造方法，防止类的实例化，因为工具类不需要实例化。
    private FileUtils() {
    }

    /**
     * <p>
     * 判断传入的文件自系统启动后是否修改过. 如果传入的文件是系统启动后对该文件的第一次判断，那么肯定返回true。
     * 如果传入的文件是系统启动后对该文件的非第一次判断，则返回上一次判断时文件的修改时间和这次文件的修改时间是否一致。
     * </p>
     *
     * @param file 文件
     * @return 文件自系统启动后是否修改过
     */
    public static boolean isChanged(File file) {
        boolean changed = true;
        String path = file.getPath();
        long lastModified = file.lastModified();
        if (FILES.containsKey(path)) {
            changed = FILES.get(path) != lastModified;
        } else {
            FILES.put(path, lastModified);
        }
        return changed;
    }

    /**
     * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
     * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>.
     *
     * @param file 需要修改最后访问时间的文件。
     */
    public static void touch(File file) {
        long currentTime = System.currentTimeMillis();
        if (!file.exists()) {
            LOGGER.warn("file not found:{}", file.getName());
            LOGGER.debug("Create a new file:{}", file.getName());
            try {
                if (file.createNewFile()) {
                    LOGGER.info("Succeeded!");
                } else {
                    LOGGER.debug("Create file failed!");
                }
            } catch (IOException e) {
                LogUtils.warn(e, LOGGER);
            }
        }
        boolean result = file.setLastModified(currentTime);
        if (!result) {
            LOGGER.error("touch failed: {}", file.getName());
        }
    }

    /**
     * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
     * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>.
     *
     * @param fileName 需要修改最后访问时间的文件的文件名。
     */
    public static void touch(String fileName) {
        File file = new File(fileName);
        touch(file);
    }

    /**
     * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
     * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>.
     *
     * @param files 需要修改最后访问时间的文件数组。
     */
    public static void touch(File[] files) {
        for (int i = 0; i < files.length; i++) {
            touch(files[i]);
        }
    }

    /**
     * 修改文件的最后访问时间。 如果文件不存在则创建该文件。
     * <b>目前这个方法的行为方式还不稳定，主要是方法有些信息输出，这些信息输出是否保留还在考虑中。</b>.
     *
     * @param fileNames 需要修改最后访问时间的文件名数组。
     */
    public static void touch(String[] fileNames) {
        File[] files = new File[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            files[i] = new File(fileNames[i]);
        }
        touch(files);
    }

    /**
     * 创建指定的目录。 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录。
     * <b>注意：可能会在返回false的时候创建部分父目录。</b>.
     *
     * @param file 要创建的目录
     * @return 完全创建成功时返回true，否则返回false。
     */
    public static boolean makeDirectory(File file) {
        File parent = file.getParentFile();
        if (parent != null) {
            return parent.mkdirs();
        }
        return false;
    }

    /**
     * 创建指定的目录。 如果指定的目录的父目录不存在则创建其目录书上所有需要的父目录。
     * <b>注意：可能会在返回false的时候创建部分父目录。</b>.
     *
     * @param fileName 要创建的目录的目录名
     * @return 完全创建成功时返回true，否则返回false。
     */
    public static boolean makeDirectory(String fileName) {
        File file = new File(fileName);
        return makeDirectory(file);
    }

    /**
     * 清空指定目录中的文件。 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
     * 另外这个方法不会迭代删除，即不会删除子目录及其内容。.
     *
     * @param directory 要清空的目录
     * @return 目录下的所有文件都被成功删除时返回true，否则返回false.
     */
    public static boolean clearDirectory(File directory) {
        boolean result = true;
        File[] entries = directory.listFiles();
        for (int i = 0; i < entries.length; i++) {
            if (!entries[i].delete()) {
                result = false;
            }
        }
        return result;
    }

    /**
     * 清空指定目录中的文件。 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。.
     *
     * @param directory 要清空的目录
     * @param cascade   是否级联删除子目录
     * @return 目录下的所有文件都被成功删除时返回true，否则返回false.
     */
    public static boolean clearDirectory(File directory, boolean cascade) {
        boolean result = true;
        File[] entries = directory.listFiles();
        if (cascade) {
            for (int i = 0; i < entries.length; i++) {
                if (!delete(entries[i])) {
                    result = false;
                }
            }
        } else {
            for (int i = 0; i < entries.length; i++) {
                if (!entries[i].delete()) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * 清空指定目录中的文件。 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
     * 另外这个方法不会迭代删除，即不会删除子目录及其内容。.
     *
     * @param directoryName 要清空的目录的目录名
     * @return 目录下的所有文件都被成功删除时返回true，否则返回false。
     */
    public static boolean clearDirectory(String directoryName) {
        File dir = new File(directoryName);
        return clearDirectory(dir);
    }

    /**
     * 删除指定目录及其中的所有内容。.
     *
     * @param dirName 要删除的目录的目录名
     * @return 删除成功时返回true，否则返回false。
     */
    public static boolean deleteDir(String dirName) {
        AssertIllegalArgument.isNotEmpty(dirName, "String dirName");
        return deleteDir(new File(dirName));
    }

    /**
     * 删除指定目录及其中的所有内容。.
     *
     * @param dir 要删除的目录
     * @return 删除成功时返回true，否则返回false。
     */
    public static boolean deleteDir(File dir) {
        AssertIllegalArgument.isNotNull(dir, "File dir");
        AssertIllegalArgument.isExists(dir, "File dir");
        AssertIllegalArgument.isDirectory(dir, "File dir");
        File[] entries = dir.listFiles();
        int sz = entries.length;
        for (int i = 0; i < sz; i++) {
            if (entries[i].isDirectory()) {
                if (!deleteDir(entries[i])) {
                    return false;
                }
            } else {
                if (!entries[i].delete()) {
                    return false;
                }
            }
        }
        if (!dir.delete()) {
            return false;
        }
        return true;
    }

    /**
     * 删除指定文件。.
     *
     * @param fileName 要删除的文件名
     * @return 删除成功时返回true，否则返回false。
     */
    public static boolean deleteFile(String fileName) {
        AssertIllegalArgument.isNotEmpty(fileName, "String fileName");
        return deleteFile(new File(fileName));
    }

    /**
     * 删除指定文件。.
     *
     * @param file 要删除的文件
     * @return 删除成功时返回true，否则返回false。
     */
    public static boolean deleteFile(File file) {
        AssertIllegalArgument.isNotNull(file, "File file");
        AssertIllegalArgument.isExists(file, "File dir");
        AssertIllegalArgument.isFile(file, "File dir");
        return file.delete();
    }

    /**
     * 删除指定文件或目录。.
     *
     * @param fileName 要删除的文件或目录
     * @return 删除成功时返回true，否则返回false。
     */
    public static boolean delete(String fileName) {
        AssertIllegalArgument.isNotEmpty(fileName, "String fileName");
        return delete(new File(fileName));
    }

    /**
     * 删除指定文件或目录。.
     *
     * @param file 要删除的文件或目录
     * @return 删除成功时返回true，否则返回false。
     */
    public static boolean delete(File file) {
        AssertIllegalArgument.isNotNull(file, "File file");
        AssertIllegalArgument.isExists(file, "File file");
        if (file.isDirectory()) {
            return deleteDir(file);
        } else {
            return deleteFile(file);
        }
    }

    /**
     * 列出目录中的所有文件和目录，包括其子目录中的文件和目录。.
     *
     * @param fileName 要列出的目录的目录名
     * @return 目录的子文件和子目录数组。
     */
    public static File[] listAll(String fileName) {
        return listAll(new File(fileName));
    }

    /**
     * 列出目录中的所有文件和目录，包括其子目录中的文件和目录。.
     *
     * @param file 要列出的目录
     * @return 目录的子文件和子目录数组。
     */
    public static File[] listAll(File file) {
        return listAll(file, f -> {
            return true;
        });
    }

    /**
     * 列出目录中的所有文件和目录，包括其子目录中的文件和目录。.
     *
     * @param file   要列出的目录
     * @param filter 过滤器
     * @return 目录的文件和目录数组。
     */
    public static File[] listAll(File file, FileFilter filter) {
        ArrayList<File> list = new ArrayList<>();
        File[] files;
        if (!file.exists() || file.isFile()) {
            return null;
        }
        list(list, file, filter);
        files = new File[list.size()];
        return list.toArray(files);
    }

    /**
     * 将目录中的内容添加到列表。
     *
     * @param list   文件列表
     * @param file   目录
     * @param filter 过滤器
     */
    private static void list(ArrayList<File> list, File file, FileFilter filter) {
        if (filter != null && filter.accept(file)) {
            list.add(file);
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                list(list, files[i], filter);
            }
        }
    }

    /**
     * <p>
     * 获取文件根目录
     * </p>
     * .
     *
     * @param file 文件
     * @return 根目录
     */
    public static File getRootDir(File file) {
        if (file.getParentFile() != null) {
            return getRootDir(file.getParentFile());
        } else {
            return file;
        }
    }

    /**
     * <p>
     * 获取当前运行程序所在的文件根目录
     * </p>
     * .
     *
     * @return 根目录
     */
    public static File getRootDir() {
        URL resource = FileUtils.class.getResource("/");
        if (resource == null) {
            resource = Thread.currentThread().getContextClassLoader().getResource(".");
        }
        return getRootDir(new File(resource.getPath()));
    }

    /**
     * 返回文件的URI地址. 如果传入对象为null则返回null
     *
     * @param file 文件
     * @return 文件对应的的URI地址
     */
    public static URI getURI(File file) {
        if (file == null) {
            return null;
        }
        return file.toURI();
    }

    /**
     * <p>
     * 返回文件大小（速度快）.如果文件不存在返回-1.
     * </p>
     *
     * @param file 文件
     * @return 文件大小
     */
    public static long getLength(File file) {
        if (file.exists()) {
            return file.length();
        } else {
            return -1;
        }
    }

    /**
     * 从文件路径得到文件名。.
     *
     * @param filePath 文件的路径，可以是相对路径也可以是绝对路径
     * @return 对应的文件名
     */
    public static String getFileName(String filePath) {
        return getFileName(new File(filePath));
    }

    /**
     * 从文件路径得到文件名.
     *
     * @param file 文件对象
     * @return 对应的文件名
     */
    public static String getFileName(File file) {
        if (file == null) {
            return null;
        }
        return file.getName();
    }

    /**
     * 从文件名得到文件绝对路径。.
     *
     * @param fileName 文件名
     * @return 对应的文件路径
     */
    public static String getFilePath(String fileName) {
        return getFilePath(new File(fileName));
    }

    /**
     * 从文件得到文件绝对路径。.
     *
     * @param file 文件
     * @return 对应的文件路径
     */
    public static String getFilePath(File file) {
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }

    /**
     * 得到上级路径. 就是得到文件（文件夹）所在的路径.
     *
     * @param path 路径
     * @return 上级路径
     */
    public static String getParentPath(String path) {
        return getParentPath(new File(path));
    }

    /**
     * 得到上级路径. 就是得到文件（文件夹）所在的路径.
     *
     * @param file 文件
     * @return 上级路径
     */
    public static String getParentPath(File file) {
        if (file == null) {
            return null;
        }
        return file.getParent();
    }

    /**
     * 得到文件的扩展名. 实际上就是得到文件名中最后一个“.”后面的部分。
     *
     * @param fileName 文件名
     * @return 文件名中的类型部分
     */
    public static String getFileExtName(String fileName) {
        return org.apache.commons.lang3.StringUtils.substringAfterLast(fileName, Chars.DOT);
    }

    /**
     * 得到文件的扩展名.
     *
     * @param file 文件
     * @return 文件名中的类型部分
     * @see #getFileExtName(String filename) getFileExtName
     */
    public static String getFileExtName(File file) {
        if (file == null) {
            return "";
        }
        return getFileExtName(file.getAbsolutePath());
    }

    /**
     * 将DOS/Windows格式的路径转换为UNIX/Linux格式的路径。
     * 其实就是将路径中的"\"全部换为"/"，因为在某些情况下我们转换为这种方式比较方便，
     * 某中程度上说"/"比"\"更适合作为路径分隔符，而且DOS/Windows也将它当作路径分隔符。.
     *
     * @param filePath 转换前的路径
     * @return 转换后的路径
     */
    public static String toUnixPath(String filePath) {
        if (filePath == null) {
            return null;
        }
        return filePath.replace('\\', '/');
    }

    /**
     * 从文件名得到UNIX风格的文件绝对路径。.
     *
     * @param fileName 文件名
     * @return 对应的UNIX风格的文件路径
     * @see #toUnixPath(String filePath) toUNIXpath
     */
    public static String getUnixFilePath(String fileName) {
        if (fileName == null) {
            return null;
        }
        File file = new File(fileName);
        return toUnixPath(file.getAbsolutePath());
    }

    /**
     * 得到路径分隔符在文件路径中首次出现的位置。 对于DOS或者UNIX风格的分隔符都可以。.
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中首次出现的位置，没有出现时返回-1。
     */
    public static int getPathIndex(String fileName) {
        if (fileName == null) {
            return -1;
        }
        int point = fileName.indexOf(Chars.DIV);
        if (point == -1) {
            point = fileName.indexOf(Chars.TL);
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中指定位置后首次出现的位置。 对于DOS或者UNIX风格的分隔符都可以。.
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的位置
     * @return 路径分隔符在路径中指定位置后首次出现的位置，没有出现时返回-1。
     */
    public static int getPathIndex(String fileName, int fromIndex) {
        if (fileName == null) {
            return -1;
        }
        int point = fileName.indexOf(Chars.DIV, fromIndex);
        if (point == -1) {
            point = fileName.indexOf(Chars.TL, fromIndex);
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。.
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
     */
    public static int getPathLsatIndex(String fileName) {
        if (fileName == null) {
            return -1;
        }
        int point = fileName.lastIndexOf(Chars.DIV);
        if (point == -1) {
            point = fileName.lastIndexOf(Chars.TL);
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中指定位置前最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。.
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的位置
     * @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
     */
    public static int getPathLsatIndex(String fileName, int fromIndex) {
        if (fileName == null) {
            return -1;
        }
        int point = fileName.lastIndexOf(Chars.DIV, fromIndex);
        if (point == -1) {
            point = fileName.lastIndexOf(Chars.TL, fromIndex);
        }
        return point;
    }

    /**
     * 将文件名中的扩展名部分去掉.
     *
     * @param fileName 文件名
     * @return 去掉类型部分的结果
     */
    public static String trimExtName(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(0, index);
        } else {
            return fileName;
        }
    }

    /**
     * 得到相对路径。 文件名不是目录名的子节点时返回文件名。.
     *
     * @param pathName 目录名
     * @param fileName 文件名
     * @return 得到文件名相对于目录名的相对路径，目录下不存在该文件时返回文件名
     */
    public static String getSubpath(String pathName, String fileName) {
        int index = fileName.indexOf(pathName);
        if (index != -1) {
            return fileName.substring(index + pathName.length() + 1);
        } else {
            return fileName;
        }
    }

    /**
     * 复制文件夹.
     *
     * @param srcDir  源文件夹
     * @param destDir 目标文件夹
     * @throws IOException IOException
     */
    public static void copyDirectory(String srcDir, String destDir) throws IOException {
        copyDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 复制文件夹.
     *
     * @param srcDir     源文件夹
     * @param destDir    目标文件夹
     * @param fileFilter 过滤器
     * @throws IOException IOException
     */
    public static void copyDirectory(String srcDir, String destDir, FileFilter fileFilter) throws IOException {
        copyDirectory(new File(srcDir), new File(destDir), fileFilter);
    }

    /**
     * 移动文件夹.
     *
     * @param srcDir  源文件夹
     * @param destDir 目标文件夹
     * @throws IOException IOException
     */
    public static void moveDirectory(String srcDir, String destDir) throws IOException {
        moveDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 复制文件.
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @throws IOException IOException
     */
    public static void copyFile(String srcFile, String destFile) throws IOException {
        copyFile(new File(srcFile), new File(destFile));
    }

    /**
     * 复制文件到指定文件夹.
     *
     * @param srcFile 源文件（夹）
     * @param destDir 目标文件夹，作为源文件（夹）的父目录
     * @throws IOException IOException
     */
    public static void copyFileToDirectory(String srcFile, String destDir) throws IOException {
        copyFileToDirectory(new File(srcFile), new File(destDir));
    }

    /**
     * 移动文件.
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @throws IOException IOException
     */
    public static void moveFile(String srcFile, String destFile) throws IOException {
        moveDirectory(new File(srcFile), new File(destFile));
    }

    /**
     * 读取文档正文.
     *
     * @param filePath 文件路径
     * @param charset  字符集
     * @return 文档正文
     * @throws IOException IOException
     */
    public static String getFileContent(String filePath, String charset) throws IOException {
        String tempStr = "";
        StringBuilder tempStr2 = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset));
        while ((tempStr = in.readLine()) != null) {
            tempStr2.append(tempStr).append("\n");
        }
        in.close();
        return tempStr2.toString();
    }

    /**
     * 读取文档正文.
     *
     * @param filePath 文件路径
     * @param charset  字符集
     * @return 文档正文
     * @throws IOException IOException
     */
    public static List<String> getFileContentLines(String filePath, String charset) throws IOException {
        List<String> lines = new ArrayList<>();
        String line = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset));
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }
        in.close();
        return lines;
    }

    /**
     * 判断指定的文件是否存在。.
     *
     * @param fileName 要判断的文件的文件名
     * @return 存在时返回true，否则返回false。
     */
    public static boolean isExist(String fileName) {
        return new File(fileName).exists();
    }

    /**
     * <p>
     * 文件的创建 如果路经不存在，则自动创建路经
     * </p>
     * .
     *
     * @param file 需要创建的文件
     * @return 创建是否成功
     * @throws IOException IOException
     */
    public static boolean createFile(File file) throws IOException {
        AssertIllegalArgument.isNotNull(file, "File file");
        if (file.isDirectory()) {
            createDirectory(file);
            return true;
        } else {
            createDirectory(file);
            file.createNewFile();
            return true;
        }
    }

    /**
     * 创建文件夹，自动创建不存在的父目录.
     *
     * @param file 需要创建的目录
     * @return 创建是否成功
     */
    public static boolean createDirectory(File file) {
        AssertIllegalArgument.isNotNull(file, "File file");
        if (file.isFile()) {
            return file.getParentFile().mkdirs();
        } else {
            return file.mkdirs();
        }
    }

    /**
     * 获取jar包内的path. 如果resourceUrl为空，则返回空，如果不是jar包内资源，则直接返回path
     *
     * @param resourceUrl resource url
     * @return path
     */
    public static String getPathInJar(URL resourceUrl) {
        if (resourceUrl == null) {
            return null;
        }
        if (isResourceInJar(resourceUrl)) {
            return org.apache.commons.lang3.StringUtils.substringAfterLast(resourceUrl.getPath(), ".jar!");
        } else {
            return resourceUrl.getPath();
        }
    }

    /**
     * 获取jar包内的path. 如果resourceUrl为空，则返回空，如果不是jar包内资源，则直接返回path
     *
     * @param resourceUrl resource url
     * @return path
     */
    public static String getClassPathInJar(URL resourceUrl) {
        if (resourceUrl == null) {
            return null;
        }
        if (isResourceInJar(resourceUrl)) {
            return org.apache.commons.lang3.StringUtils.substringAfterLast(resourceUrl.getPath(), "!");
        } else {
            return resourceUrl.getPath();
        }
    }

    /**
     * 判断传入资源是否是jar包内的资源.
     *
     * @param resourceUrl 资源定位
     * @return boolean
     */
    public static boolean isResourceInJar(URL resourceUrl) {
        if (resourceUrl == null) {
            return false;
        }
        return "jar".equalsIgnoreCase(resourceUrl.getProtocol());
    }

    /**
     * generate crc32 long number.
     *
     * @param file file
     * @return crc32 long number
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static long crc32(File file) throws IOException {
        return CRC.crc32(file);
    }

    /**
     * generate crc32 hex string.
     *
     * @param file file
     * @return crc32 hex string
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String crc32Hex(File file) throws IOException {
        return CRC.crc32Hex(file);
    }

    /**
     * 监控传入目录.
     *
     * @param dir      目录
     * @param listener WatchListener
     * @return Watcher
     */
    public static Watcher watch(File dir, WatchListener listener) {
        return watch(dir, listener, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.OVERFLOW);
    }

    /**
     * 监控传入目录.
     *
     * @param dir      目录
     * @param listener WatchListener
     * @param events   events
     * @return Watcher
     */
    public static Watcher watch(File dir, WatchListener listener, WatchEvent.Kind<?>... events) {
        return watch(Paths.get(dir.toURI()), listener, events);
    }

    /**
     * 监控传入目录及其子目录.
     *
     * @param dir      目录
     * @param listener WatchListener
     * @return Watcher List
     */
    public static List<Watcher> watchAll(File dir, WatchListener listener) {
        return watchAll(dir, listener, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.OVERFLOW);
    }

    /**
     * 监控传入目录及其子目录.
     *
     * @param dir      目录
     * @param listener WatchListener
     * @param events   events
     * @return Watcher List
     */
    public static List<Watcher> watchAll(File dir, WatchListener listener, WatchEvent.Kind<?>... events) {
        return watchAll(dir, listener, v -> {
            return true;
        }, events);
    }

    /**
     * 监控由传入过滤器过滤后的传入目录及其子目录.
     *
     * @param dir      目录
     * @param listener WatchListener
     * @param filter   FileFilter
     * @return Watcher List
     */
    public static List<Watcher> watchAll(File dir, WatchListener listener, FileFilter filter) {
        return watchAll(dir, listener, filter, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.OVERFLOW);
    }

    /**
     * 监控由传入过滤器过滤后的传入目录及其子目录.
     *
     * @param dir      目录
     * @param listener WatchListener
     * @param filter   FileFilter
     * @param events   events
     * @return Watcher List
     */
    public static List<Watcher> watchAll(File dir, WatchListener listener, FileFilter filter,
            WatchEvent.Kind<?>... events) {
        return watch(Arrays.stream(FileUtils.listAll(dir, v -> {
            return v.isDirectory() && filter.accept(v);
        })).map(d -> {
            return Paths.get(d.toURI());
        }).collect(Collectors.toList()), listener, events);
    }

    private static List<Watcher> watch(List<Path> paths, WatchListener listener, WatchEvent.Kind<?>[] events) {
        List<Watcher> watchers = new ArrayList<>();
        for (Path path : paths) {
            watchers.add(watch(path, listener, events));
        }
        return watchers;
    }

    private static Watcher watch(Path watchDirectory, WatchListener listener, WatchEvent.Kind<?>[] events) {
        Watcher watcher = new Watcher(watchDirectory, listener, events);
        watcher.watch();
        return watcher;
    }
}