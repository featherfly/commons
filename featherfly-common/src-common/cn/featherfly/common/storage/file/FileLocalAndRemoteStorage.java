
package cn.featherfly.common.storage.file;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.exception.AssertStandardSys;
import cn.featherfly.common.io.FileUtils;

/**
 * <p>
 * 本地和远程都存储的存储实现
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author 钟冀
 */
public class FileLocalAndRemoteStorage implements FileStorage{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileLocalAndRemoteStorage.class);

	private FileLocalDirStorage localDirStorage;

	private List<RemoteFileStorage> remoteStorages;

	/**
	 * @param localDirStorage 本地存储
	 * @param remoteStorages 远程存储
	 */
	public FileLocalAndRemoteStorage(FileLocalDirStorage localDirStorage, List<RemoteFileStorage> remoteStorages) {
		AssertStandardSys.isNotNull(localDirStorage, "localDirStorage本地存储不能为空");
		AssertStandardSys.isNotEmpty(remoteStorages, "remoteStorages远程存储集合不能为空");
		this.localDirStorage = localDirStorage;
		this.remoteStorages = remoteStorages;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File retrieve(String id) {
		File file = localDirStorage.retrieve(id);
		if (file != null && file.exists()) {
			LOGGER.debug("使用本地存储[{}]查找文件存储唯一标示[{}]的文件，找到文件：{}",
					new Object[]{localDirStorage, id, file.getAbsolutePath()});
			return file;
		} else {
			LOGGER.debug("使用本地存储[{}]查找文件存储唯一标示[{}]的文件，未找到", localDirStorage, id);
			for (RemoteFileStorage remoteStorage : remoteStorages) {
				file = remoteStorage.retrieve(id);
				if (file != null && file.exists()) {
					LOGGER.debug("使用远程存储[{}]查找文件存储唯一标示[{}]的文件，找到文件：{}",
							new Object[]{remoteStorage, id, file.getAbsolutePath()});
					return file;
				} else {
					LOGGER.debug("使用远程存储[{}]查找文件存储唯一标示[{}]的文件，未找到",
							new Object[]{remoteStorage, id});
				}
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String store(File file) {
		String id = localDirStorage.store(file);
		LOGGER.debug("使用本地存储[{}]存储指定文件[{}]，产生存储识别信息：{}",
				new Object[]{localDirStorage, file.getAbsolutePath(), id});
		for (RemoteFileStorage remoteStorage : remoteStorages) {
			String rid = remoteStorage.store(file);
			if (file != null && file.exists()) {
				LOGGER.debug("使用远程存储[{}]存储指定文件[{}]，产生存储识别信息：{}",
						new Object[]{remoteStorage, file.getAbsolutePath(), rid});
			}
		}
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(String id) {
		File file = retrieve(id);
		return FileUtils.delete(file);
	}
}
