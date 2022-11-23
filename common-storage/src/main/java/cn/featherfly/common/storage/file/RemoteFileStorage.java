
package cn.featherfly.common.storage.file;

import java.io.File;

import cn.featherfly.common.storage.stream.RemoteStorage;

/**
 * <p>
 * 远程存储
 * </p>
 *
 * @author zhongj
 */
public abstract class RemoteFileStorage extends RemoteStorage<File, String> implements FileStorage{

    /**
     */
    public RemoteFileStorage() {
    }

}
