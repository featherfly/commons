
package cn.featherfly.common.compress.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.compress.CompressException;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.constant.Charset;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.StringUtils;
import cn.featherfly.common.lang.UriUtils;

/**
 * <p>
 * ZipFile操作类
 * </p>
 *
 * @author 钟冀
 */
public class ZipFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipFile.class);

    private static final String DEFAULT_ENCODING = Charset.UTF_8;

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    /**
     * 使用默认编码（UTF-8）
     *
     * @param file 文件
     */
    public ZipFile(File file) {
        this(file, DEFAULT_ENCODING);
    }

    /**
     * 使用默认编码（UTF-8）
     *
     * @param name 文件名
     * @throws IOException IOException
     */
    public ZipFile(String name) throws IOException {
        this(new File(name));
    }

    /**
     * @param file     文件
     * @param encoding 编码
     */
    public ZipFile(File file, String encoding) {
        try {
            this.file = file;
            zipFile = new org.apache.tools.zip.ZipFile(file, encoding);
        } catch (IOException e) {
            throw new CompressException(e);
        }
    }

    /**
     * @param name     文件名
     * @param encoding 编码
     */
    public ZipFile(String name, String encoding) {
        this(new File(name), encoding);
    }

    /**
     * <p>
     * 解压缩zip文件
     * </p>
     *
     * @param decompressDir 解压缩到的目录
     * @return this
     */
    public ZipFile decompress(File decompressDir) {
        AssertIllegalArgument.isNotNull(decompressDir, "decompressDir不能为空!");
        assertIsNotFile(decompressDir);
        LOGGER.debug("开始解压ZIP文件：{}", file.getAbsolutePath());
        try {
            Enumeration<ZipEntry> emu = getEntries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = emu.nextElement();

                String entryName = getEntryPath(entry);
                if (entry.isDirectory()) {
                    File file = new File(decompressDir.getAbsolutePath() + Chars.DIV + entryName);
                    file.mkdirs();
                } else {
                    File file = new File(decompressDir.getAbsolutePath() + Chars.DIV + entryName);
                    writeFile(zipFile.getInputStream(entry), file);
                }
            }
        } catch (IOException e) {
            throw new CompressException(e);
        }
        LOGGER.debug("结束解压ZIP文件：{}", file.getAbsolutePath());
        return this;
    }

    /**
     * <p>
     * 解压缩zip文件
     * </p>
     *
     * @param decompressDir 解压缩到的目录
     * @return this
     */
    public ZipFile decompress(String decompressDir) {
        AssertIllegalArgument.isNotEmpty(decompressDir, "decompressDir不能为空!");
        decompress(new File(decompressDir));
        return this;
    }

    /**
     * <p>
     * 解压缩zip中的指定文件到指定路径.
     * </p>
     *
     * @param filePath      文件在压缩文件内的路径
     * @param decompressDir 解压缩到的目录
     * @throws IOException IOException
     * @return this
     */
    public ZipFile decompress(String filePath, String decompressDir) throws IOException {
        AssertIllegalArgument.isNotEmpty(filePath, "filePath不能为空!");
        AssertIllegalArgument.isNotEmpty(decompressDir, "decompressDir不能为空!");
        File dir = new File(decompressDir);
        assertIsNotFile(dir);
        try {
            ZipEntry zipEntry = getEntry(filePath);
            if (zipEntry != null) {
                writeFile(getResourceAsStream(filePath),
                        new File(decompressDir + Chars.DIV + getEntryFileName(zipEntry)));
            }
        } catch (IOException e) {
            throw new CompressException(e);
        }
        return this;
    }

    /**
     * <p>
     * 解压缩zip中的指定文件到指定文件.
     * </p>
     *
     * @param filePath       文件在压缩文件内的路径
     * @param decompressFile 解压缩到的文件
     * @return this
     */
    public ZipFile decompress(String filePath, File decompressFile) {
        AssertIllegalArgument.isNotEmpty(filePath, "filePath不能为空!");
        AssertIllegalArgument.isNotNull(decompressFile, "decompressFile不能为空!");
        assertIsNotDir(decompressFile);
        try {
            writeFile(getResourceAsStream(filePath), decompressFile);
        } catch (IOException e) {
            throw new CompressException(e);
        }
        return this;
    }

    /**
     * <p>
     * 解压缩zip中的指定文件到指定路径.
     * </p>
     *
     * @param filePath      文件在压缩文件内的路径
     * @param decompressDir 解压到的目录
     * @param fileName      解压缩后的文件名
     * @return this
     */
    public ZipFile decompress(String filePath, String decompressDir, String fileName) {
        AssertIllegalArgument.isNotNull(decompressDir, "decompressDir不能为空!");
        AssertIllegalArgument.isNotNull(fileName, "fileName不能为空!");
        decompress(filePath, new File(decompressDir + Chars.DIV + fileName));
        return this;
    }

    /**
     * <p>
     * zip压缩给定文件或目录下的所有文件,使用默认编码（UTF-8）
     * </p>
     *
     * @param file   压缩以后的文件
     * @param target 需要压缩的文件或文件夹,当是文件夹时，压缩文件夹下的所有文件
     * @return 压缩后的ZipFile文件
     */
    public static ZipFile compress(File file, File target) {
        return compress(file, target, DEFAULT_ENCODING);
    }

    /**
     * <p>
     * zip压缩给定文件或目录下的所有文件
     * </p>
     *
     * @param file     压缩以后的文件
     * @param target   需要压缩的文件或文件夹,当是文件夹时，压缩文件夹下的所有文件
     * @param encoding 编码
     * @return 压缩后的ZipFile文件
     */
    public static ZipFile compress(File file, File target, String encoding) {
        if (!target.exists()) {
            throw new CompressException("文件不存在：" + target.getAbsolutePath());
        }
        try {
            ZipOutputStream zipOut = new ZipOutputStream(file);
            LOGGER.debug("压缩使用编码：{}", encoding);
            zipOut.setEncoding(encoding);
            compress(target.getParentFile(), target, zipOut);
            zipOut.close();
            return new ZipFile(file);
        } catch (IOException e) {
            throw new CompressException(e);
        }
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
        return compressFile(file, DEFAULT_ENCODING, files);
    }

    /**
     * <p>
     * zip压缩给定文件
     * </p>
     *
     * @param file     压缩以后的文件
     * @param files    需要压缩的文件（多个）
     * @param encoding 编码
     * @return 压缩后的ZipFile文件
     */
    public static ZipFile compressFile(File file, String encoding, File... files) {
        for (File f : files) {
            if (!f.exists()) {
                throw new CompressException("文件不存在：" + f.getAbsolutePath());
            }
        }
        try {
            ZipOutputStream zipOut = new ZipOutputStream(file);
            LOGGER.debug("压缩使用编码：{}", encoding);
            zipOut.setEncoding(encoding);
            compressFile(zipOut, files);
            zipOut.close();
            return new ZipFile(file);
        } catch (IOException e) {
            throw new CompressException(e);
        }
    }

    /**
     * <p>
     * 查找Zip压缩文件内指定路径的资源.
     * </p>
     *
     * @param filePath 文件在压缩文件内的路径
     * @return Zip压缩文件内指定路径的资源.如果没有则返回null.
     */
    public InputStream getResourceAsStream(String filePath) {
        try {
            ZipEntry zipEntry = getEntry(filePath);
            if (zipEntry == null) {
                return null;
            } else {
                return zipFile.getInputStream(zipEntry);
            }
        } catch (IOException e) {
            throw new CompressException(e);
        }
    }

    /**
     * 关闭
     */
    public void close() {
        try {
            zipFile.close();
        } catch (IOException e) {
            throw new CompressException(e);
        }
    }

    // ********************************************************************
    //	private method
    // ********************************************************************

    //递归完成目录文件读取
    private static void compress(File baseDir, File file, ZipOutputStream zipOut) throws IOException {
        String basePath = baseDir.getAbsolutePath();
        String relativePath = StringUtils.substringAfter(file.getAbsolutePath(), basePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                //如果目录为空,则单独创建之.
                //ZipEntry的isDirectory()方法中,目录以"/"结尾.
                zipOut.putNextEntry(new ZipEntry(relativePath + "/"));
                zipOut.closeEntry();
            } else {
                for (File subFile : files) {
                    compress(baseDir, subFile, zipOut);
                }
            }
        } else {
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int readedBytes = -1;
            zipOut.putNextEntry(new ZipEntry(relativePath));
            while ((readedBytes = fis.read(buffer)) > 0) {
                zipOut.write(buffer, 0, readedBytes);
            }
            fis.close();
            zipOut.closeEntry();
        }
    }

    private static void compressFile(ZipOutputStream zipOut, File... files) throws IOException {
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                int readedBytes = -1;
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                while ((readedBytes = fis.read(buffer)) > 0) {
                    zipOut.write(buffer, 0, readedBytes);
                }
                fis.close();
                zipOut.closeEntry();
            } else {
                LOGGER.warn("{}不是文件", file.getAbsolutePath());
            }
        }
    }

    private ZipEntry getEntry(String filePath) {
        ZipEntry entry = zipFile.getEntry(filePath);
        if (entry == null) {
            LOGGER.debug("在{}ZIP文件内没有找到{}，返回null", file.getAbsolutePath(), filePath);
        } else {
            LOGGER.debug("在{}ZIP文件内找到{}", file.getAbsolutePath(), filePath);
        }
        return entry;
    }

    // 如果是存在的文件，抛出异常
    private void assertIsNotFile(File dir) {
        if (dir.exists() && dir.isFile()) {
            throw new CompressException("参数 {} 不能是已存在的文件！");
        }
    }

    // 如果是存在的目录，抛出异常
    private void assertIsNotDir(File file) {
        if (file.exists() && file.isDirectory()) {
            throw new CompressException("参数 {} 不能是已存在的目录！");
        }
    }

    private void writeFile(InputStream input, File file) throws IOException {
        //加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
        //而这个文件所在的目录还没有出现过，所以要建出目录来。
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
            LOGGER.debug("解压 - 创建文件夹: " + parent.getAbsolutePath());
        }
        LOGGER.debug("解压 - 创建文件: " + file.getAbsolutePath());
        try (BufferedInputStream bis = new BufferedInputStream(input);
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos, bufferSize)) {
            int count;
            byte[] data = new byte[bufferSize];
            while ((count = bis.read(data, 0, bufferSize)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
        }
    }

    private String getEntryFileName(ZipEntry zipEntry) {
        return StringUtils.substringAfterLast(getEntryPath(zipEntry), Chars.DIV);
    }

    private String getEntryPath(ZipEntry zipEntry) {
        return UriUtils.convertSeparator(zipEntry.getName());
    }

    // ********************************************************************
    //	field
    // ********************************************************************

    private File file;

    private org.apache.tools.zip.ZipFile zipFile;

    // ********************************************************************
    //	property
    // ********************************************************************

    private int bufferSize = DEFAULT_BUFFER_SIZE;

    /**
     * 返回bufferSize
     *
     * @return bufferSize
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * 设置bufferSize. 最小数值为512，小于512则设置512.
     *
     * @param bufferSize bufferSize
     */
    public void setBufferSize(int bufferSize) {
        final int minSize = 512;
        if (bufferSize < minSize) {
            bufferSize = minSize;
        }
        this.bufferSize = bufferSize;
    }

    /**
     * 返回file
     *
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     * 返回编码.
     *
     * @return encoding
     */
    public String getEncoding() {
        return zipFile.getEncoding();
    }

    /**
     * 返回entries
     *
     * @return entries
     */
    public Enumeration<ZipEntry> getEntries() {
        return zipFile.getEntries();
    }
}
