package cn.featherfly.common.compress.zip;

import java.io.File;
import java.io.IOException;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * <p>
 * 压缩、解压工具类
 * </p>
 *
 * @author 钟冀
 */
public final class ZipFileUtils {

    private ZipFileUtils() {
    }

    /**
     * <p>
     * 解压缩zip文件
     * </p>
     *
     * @param zipFile       zip压缩文件
     * @param decompressDir 解压缩到的目录
     */
    public static void decompress(File zipFile, String decompressDir) {
        AssertIllegalArgument.isNotNull(zipFile, "File zipFile");
        new ZipFile(zipFile).decompress(decompressDir).close();
    }

    /**
     * <p>
     * 解压缩zip文件
     * </p>
     *
     * @param zipFile       zip压缩文件
     * @param decompressDir 解压缩到的目录
     */
    public static void decompress(File zipFile, File decompressDir) {
        AssertIllegalArgument.isNotNull(zipFile, "File zipFile");
        new ZipFile(zipFile).decompress(decompressDir).close();
    }

    /**
     * <p>
     * 解压缩zip文件
     * </p>
     *
     * @param zipFile       zip压缩文件
     * @param decompressDir 解压缩到的目录
     * @param encoding      编码
     */
    public static void decompress(File zipFile, File decompressDir, String encoding) {
        AssertIllegalArgument.isNotNull(zipFile, "File zipFile");
        new ZipFile(zipFile, encoding).decompress(decompressDir).close();
    }

    /**
     * <p>
     * 解压缩zip中的指定文件到指定路径.
     * </p>
     *
     * @param zipFile       zip压缩文件
     * @param filePath      文件在压缩文件内的路径
     * @param decompressDir 解压缩到的目录
     * @throws IOException IOException
     */
    public static void decompress(File zipFile, String filePath, String decompressDir) throws IOException {
        AssertIllegalArgument.isNotNull(zipFile, "zipFile");
        new ZipFile(zipFile).decompress(filePath, decompressDir).close();
    }

    /**
     * <p>
     * 解压缩zip中的指定文件到指定文件.
     * </p>
     *
     * @param zipFile        zip压缩文件
     * @param filePath       文件在压缩文件内的路径
     * @param decompressFile 解压缩到的文件
     */
    public static void decompress(File zipFile, String filePath, File decompressFile) {
        AssertIllegalArgument.isNotNull(zipFile, "zipFile");
        new ZipFile(zipFile).decompress(filePath, decompressFile).close();
    }

    /**
     * <p>
     * 解压缩zip中的指定文件到指定路径.
     * </p>
     *
     * @param zipFile       zip压缩文件
     * @param filePath      文件在压缩文件内的路径
     * @param decompressDir 解压到的目录
     * @param fileName      解压缩后的文件名
     */
    public static void decompress(File zipFile, String filePath, String decompressDir, String fileName) {
        AssertIllegalArgument.isNotNull(decompressDir, "decompressDir");
        AssertIllegalArgument.isNotNull(fileName, "fileName");
        decompress(zipFile, filePath, new File(decompressDir + Chars.DIV + fileName));
    }

    /**
     * <p>
     * zip压缩,使用默认编码（UTF-8）
     * </p>
     *
     * @param file   压缩以后的文件
     * @param target 需要压缩的文件或文件夹,当是文件夹时，压缩文件夹下的所有文件
     * @return 压缩后的ZipFile文件
     */
    public static ZipFile compress(File file, File target) {
        AssertIllegalArgument.isNotNull(file, "file");
        AssertIllegalArgument.isNotNull(target, "target");
        return ZipFile.compress(file, target);
    }

    /**
     * <p>
     * zip压缩
     * </p>
     *
     * @param file     压缩以后的文件
     * @param target   需要压缩的文件或文件夹,当是文件夹时，压缩文件夹下的所有文件
     * @param encoding 编码
     * @return 压缩后的ZipFile文件
     */
    public static ZipFile compress(File file, File target, String encoding) {
        AssertIllegalArgument.isNotNull(file, "file");
        AssertIllegalArgument.isNotNull(target, "target");
        AssertIllegalArgument.isNotNull(encoding, "encoding");
        return ZipFile.compress(file, target, encoding);
    }

    /**
     * <p>
     * zip压缩给定文件,使用默认编码（UTF-8）
     * </p>
     *
     * @param file  压缩以后的文件
     * @param files 需要压缩的文件（多个）
     * @return 压缩后的ZipFile文件
     */
    public static ZipFile compressFile(File file, File... files) {
        AssertIllegalArgument.isNotNull(file, "file");
        return ZipFile.compressFile(file, files);
    }

    /**
     * <p>
     * zip压缩给定文件
     * </p>
     *
     * @param file     压缩以后的文件
     * @param encoding 编码
     * @param files    需要压缩的文件（多个）
     * @return 压缩后的ZipFile文件
     */
    public static ZipFile compressFile(File file, String encoding, File... files) {
        AssertIllegalArgument.isNotNull(file, "file");
        return ZipFile.compressFile(file, encoding, files);
    }

    //	/**
    //	 * 这个的资源管理完全交给调用者去管理，不妥当，暂时先屏蔽
    //	 * <p>
    //	 * 查找Zip压缩文件内指定路径的资源.
    //	 * </p>
    //	 * @param zipFile 压缩文件
    //	 * @param filePath 文件在压缩文件内的路径
    //	 * @return Zip压缩文件内指定路径的资源.如果没有则返回null.
    //	 */
    //	public static InputStream getResourceAsStream(File zipFile, String filePath) {
    //		return new ZipFile(zipFile).getResourceAsStream(filePath);
    //	}

}
