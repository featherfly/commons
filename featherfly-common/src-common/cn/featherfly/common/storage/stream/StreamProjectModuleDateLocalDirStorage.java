
package cn.featherfly.common.storage.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import cn.featherfly.common.exception.AssertStandardSys;
import cn.featherfly.common.exception.StandardSysException;
import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.UUIDGenerator;
import cn.featherfly.common.lang.UriUtils;
import cn.featherfly.common.storage.ProjectModuleDateLocalDirStorage;

/**
 * <p>
 * 本地目录存储对象
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class StreamProjectModuleDateLocalDirStorage extends ProjectModuleDateLocalDirStorage<InputStream>
    implements StreamStorage {

    /**
     */
    public StreamProjectModuleDateLocalDirStorage() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String store(InputStream is) {
        AssertStandardSys.isNotNull(is, "参数流不能为空");
        try {
            File targetFile = createTargetFile(UUIDGenerator.generateUUID32());
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            OutputStream os = new FileOutputStream(targetFile); 
            IOUtils.copy(is, os);
            IOUtils.closeQuietly(os);
            return getId(targetFile);
        } catch (IOException e) {
            throw new StandardSysException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream retrieve(String id) {
        AssertStandardSys.isNotEmpty(id, "存储唯一标示不能为空");
        logger.debug("存储唯一标示：{}", id);
        File file = createRelativeDir();
        file = new File(UriUtils.linkUri(
                file.getAbsolutePath(), id));
        logger.debug("获取文件：{}", file.getAbsolutePath());
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new StandardSysException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        AssertStandardSys.isNotEmpty(id, "存储唯一标示不能为空");
        logger.debug("存储唯一标示：{}", id);
        File file = createRelativeDir();
        file = new File(UriUtils.linkUri(
                file.getAbsolutePath(), id));
        logger.debug("获取文件：{}", file.getAbsolutePath());
        return FileUtils.delete(file);
    }
}
