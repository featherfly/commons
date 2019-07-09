
package cn.featherfly.common.storage.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.UriUtils;
import cn.featherfly.common.storage.DateLocalDirStorage;
import cn.featherfly.common.storage.StorageException;

/**
 * <p>
 * 本地目录存储对象
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author zhongj
 */
public class StreamDateLocalDirStorage extends DateLocalDirStorage<InputStream> implements StreamStorage {

    /**
     */
    public StreamDateLocalDirStorage() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String store(InputStream is) {
        ASSERT.isNotNull(is, "param InputStream");
        try {
            File targetFile = createTargetFile("stream has no name");
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            try (OutputStream os = new FileOutputStream(targetFile)) {
                IOUtils.copy(is, os);
                return getId(targetFile);
            }
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream retrieve(String id) {
        ASSERT.isNotEmpty(id, "id");
        logger.debug("存储唯一标示：{}", id);
        File file = createRelativeDir();
        file = new File(UriUtils.linkUri(file.getAbsolutePath(), id));
        logger.debug("获取文件：{}", file.getAbsolutePath());
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new StorageException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        ASSERT.isNotEmpty(id, "id");
        logger.debug("存储唯一标示：{}", id);
        File file = createRelativeDir();
        file = new File(UriUtils.linkUri(file.getAbsolutePath(), id));
        logger.debug("获取文件：{}", file.getAbsolutePath());
        return FileUtils.delete(file);
    }
}
