
package cn.featherfly.common.storage;

import java.io.File;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.exception.AssertStandardSys;
import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.io.file.RenamePolicy;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.UriUtils;

/**
 * <p>
 * 本地目录存储对象
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @param <E> 存储的对象类型
 * @author 钟冀
 */
public abstract class LocalDirStorage<E> implements Storage<E, String>{

	/**
	 * 日志对象
	 */
	protected final Logger logger = LoggerFactory
			.getLogger(this.getClass());

	/**
	 */
	public LocalDirStorage() {
		setBaseDirDefault();

	}

	// ********************************************************************
	// protected method
	// ********************************************************************

	/**
	 * <p>
	 * 创建扩展目录
	 * </p>
	 * @return 扩展目录
	 */
	protected abstract String getExtDir();

	/**
	 * <p>
	 * 获取文件存储后的唯一标示
	 * </p>
	 * @param targetFile 存储后的文件
	 * @return 文件存储后的唯一标示
	 */
	protected String getId(File targetFile) {
		String extDir = getExtDir();
		if (LangUtils.isEmpty(extDir)) {
			return targetFile.getName();
		} else {
			return extDir + "/" + targetFile.getName();
		}
	}

	/**
	 * <p>
	 * 创建实际存储的文件
	 * </p>
	 * @param fileName 原始文件名称
	 * @return 实际存储的文件
	 */
	protected File createTargetFile(String fileName) {
		String extDir = getExtDir();
		File file = new File(UriUtils.linkUri(
				createTargetDir(extDir).getAbsolutePath(), rename(fileName)));;
		logger.debug("文件存储目标位置：{}", file.getAbsolutePath());
		FileUtils.createDirectory(file.getParentFile());
		return file;
	}

	/**
	 * <p>
	 * 创建相对路径对象
	 * </p>
	 * @return 相对路径对象
	 */
	protected File createRelativeDir() {
		AssertStandardSys.isNotEmpty(getBaseDir(), "基础目录不能为空");
		File file = null;
		// 设置基础目录
		String finalDir = getBaseDir();
		// 连接相对目录
		String relativeDir = getRelativeDir();
		if (LangUtils.isNotEmpty(relativeDir)) {
			finalDir = UriUtils.linkUri(finalDir, relativeDir);
		}
		file = new File(finalDir);
		logger.debug("文件存储相对目录：{}", file.getAbsolutePath());
		FileUtils.createDirectory(file);
		return file;
	}

	// ********************************************************************
	//	private method
	// ********************************************************************

	/*
	 * <p>
	 * 创建文件的实际存储目录
	 * </p>
	 * @return 文件的实际存储目录
	 */
	private File createTargetDir(String extDir) {
		File file = createRelativeDir();
		String finalDir = file.getAbsolutePath();
		// 连接扩展目录
		if (LangUtils.isNotEmpty(extDir)) {
			finalDir = UriUtils.linkUri(finalDir, extDir);
		}
		file = new File(finalDir);
		logger.debug("文件存储目标目录：{}", file.getAbsolutePath());
		FileUtils.createDirectory(file);
		return file;
	}

	/*
	 * <p>
	 * 文件重命名
	 * </p>
	 * @param fileName 文件名
	 * @return 重命名后的文件名
	 */
	private String rename(String fileName) {
		if (renamePolicy != null) {
			logger.debug("重命名策略为：{}", renamePolicy.getClass().getName());
			return renamePolicy.rename(fileName);
		} else {
			logger.debug("重命名策略为空，使用原始文件名：{}" + fileName);
			return fileName;
		}
	}

	private void setBaseDirDefault() {
		if (LangUtils.isEmpty(baseDir)) {
			URL resource = this.getClass().getResource("/");
			if (resource == null) {
				resource = Thread.currentThread().getContextClassLoader().getResource(".");
			}
			baseDir = FileUtils.getRootDir(new File(resource.getPath())).getAbsolutePath();
			logger.debug("baseDir为空，使用根目录：{}", baseDir);
		}
	}

	// ********************************************************************
	// property
	// ********************************************************************

	/**
	 * 基础目录
	 */
	private String baseDir;
	/**
	 * 相对路径
	 */
	private String relativeDir;
	/**
	 * 文件名重命名策略
	 */
	private RenamePolicy renamePolicy;

	/**
	 * 返回baseDir
	 * @return baseDir
	 */
	public String getBaseDir() {
		return baseDir;
	}

	/**
	 * 设置baseDir
	 * @param baseDir baseDir
	 */
	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	/**
	 * 返回relativeDir
	 * @return relativeDir
	 */
	public String getRelativeDir() {
		return relativeDir;
	}

	/**
	 * 设置relativeDir
	 * @param relativeDir relativeDir
	 */
	public void setRelativeDir(String relativeDir) {
		this.relativeDir = relativeDir;
	}

	/**
	 * 返回renamePolicy
	 * @return renamePolicy
	 */
	public RenamePolicy getRenamePolicy() {
		return renamePolicy;
	}

	/**
	 * 设置renamePolicy
	 * @param renamePolicy renamePolicy
	 */
	public void setRenamePolicy(RenamePolicy renamePolicy) {
		this.renamePolicy = renamePolicy;
	}
}
