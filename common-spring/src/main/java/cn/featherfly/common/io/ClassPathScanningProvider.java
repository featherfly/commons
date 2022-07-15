package cn.featherfly.common.io;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * 类路径扫描器.
 *
 * @author zhongj
 */
public class ClassPathScanningProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassPathScanningProvider.class);

    /**
     * 扫描文件扩展名
     */
    protected static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

    private String resourcePattern = DEFAULT_RESOURCE_PATTERN;

    /**
     * 创建一个类路径扫描器
     */
    public ClassPathScanningProvider() {
    }

    /**
     * 设置资源匹配模式
     *
     * @param resourcePattern 资源匹配模式
     */
    public void setResourcePattern(String resourcePattern) {
        AssertIllegalArgument.isNotNull(resourcePattern, "String resourcePattern");
        this.resourcePattern = resourcePattern;
    }

    /**
     * 扫描CLASSPATH指定包名前缀的类
     *
     * @param basePackage 基础包名
     * @return 元数据集合
     */
    public Set<MetadataReader> findMetadata(String basePackage) {
        LOGGER.debug("扫描basePackage: " + basePackage);
        Set<MetadataReader> metaSet = new LinkedHashSet<>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + resolveBasePackage(basePackage) + "/" + resourcePattern;
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                metaSet.add(metadataReader);
            }
        } catch (IOException ex) {
            throw new RuntimeException("扫描classpath时I/O异常", ex);
        }
        return metaSet;
    }

    /**
     * 扫描CLASSPATH指定包名前缀的类
     *
     * @param basePackages 基础包名数组
     * @return 元数据集合
     */
    public Set<MetadataReader> findMetadata(String... basePackages) {
        Set<MetadataReader> metaSet = new LinkedHashSet<>();
        if (Lang.isNotEmpty(basePackages)) {
            LOGGER.debug("扫描basePackages: " + ArrayUtils.toString(basePackages));
            for (String basePackage : basePackages) {
                metaSet.addAll(findMetadata(basePackage));
            }
        }
        return metaSet;
    }

    // 查找
    private String resolveBasePackage(String basePackage) {
        return org.springframework.util.ClassUtils.convertClassNameToResourcePath(
                org.springframework.util.SystemPropertyUtils.resolvePlaceholders(basePackage));
    }

    // ********************************************************************
    //
    // ********************************************************************

}