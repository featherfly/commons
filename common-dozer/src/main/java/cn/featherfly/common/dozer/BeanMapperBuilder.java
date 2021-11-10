
/*
 * All rights Reserved, Designed By zhongj
 * @Title: BeanMapperBuilder.java
 * @Package cn.featherfly.common.dozer
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-11-10 17:24:10
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.dozer;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.github.dozermapper.core.BeanFactory;
import com.github.dozermapper.core.CustomConverter;
import com.github.dozermapper.core.CustomFieldMapper;
import com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder;
import com.github.dozermapper.core.builder.BeanMappingsBuilder;
import com.github.dozermapper.core.cache.CacheManager;
import com.github.dozermapper.core.config.processors.SettingsProcessor;
import com.github.dozermapper.core.el.ELEngine;
import com.github.dozermapper.core.events.EventListener;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.util.DozerClassLoader;

/**
 * BeanMapperBuilder.
 *
 * @author zhongj
 */
public class BeanMapperBuilder {

    private FeatherflyDozerBeanMapperBuilder builder;

    BeanMapperBuilder() {
        builder = FeatherflyDozerBeanMapperBuilder.create();
    }

    public BeanMapper build() {
        return new BeanMapper(builder.withMappingFiles(DozerConstants.JAVA8_MAPPING).build());
    }

    /**
     * @param isMapNull
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMapNull(boolean)
     */
    public BeanMapperBuilder withMapNull(boolean isMapNull) {
        builder.withMapNull(isMapNull);
        return this;
    }

    /**
     * @param isMapEmptyString
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMapEmptyString(boolean)
     */
    public BeanMapperBuilder withMapEmptyString(boolean isMapEmptyString) {
        builder.withMapEmptyString(isMapEmptyString);
        return this;
    }

    /**
     * @param mappingFiles
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingFiles(java.lang.String[])
     */
    public BeanMapperBuilder withMappingFiles(String... mappingFiles) {
        builder.withMappingFiles(mappingFiles);
        return this;
    }

    /**
     * @param mappingFiles
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingFiles(java.util.List)
     */
    public BeanMapperBuilder withMappingFiles(List<String> mappingFiles) {
        builder.withMappingFiles(mappingFiles);
        return this;
    }

    /**
     * @param classLoader
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withClassLoader(com.github.dozermapper.core.util.DozerClassLoader)
     */
    public BeanMapperBuilder withClassLoader(DozerClassLoader classLoader) {
        builder.withClassLoader(classLoader);
        return this;
    }

    /**
     * @param classLoader
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withClassLoader(java.lang.ClassLoader)
     */
    public BeanMapperBuilder withClassLoader(ClassLoader classLoader) {
        builder.withClassLoader(classLoader);
        return this;
    }

    /**
     * @param customConverter
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverter(com.github.dozermapper.core.CustomConverter)
     */
    public BeanMapperBuilder withCustomConverter(CustomConverter customConverter) {
        builder.withCustomConverter(customConverter);
        return this;
    }

    /**
     * @param customConverters
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverters(com.github.dozermapper.core.CustomConverter[])
     */
    public BeanMapperBuilder withCustomConverters(CustomConverter... customConverters) {
        builder.withCustomConverters(customConverters);
        return this;
    }

    /**
     * @param customConverters
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverters(java.util.List)
     */
    public BeanMapperBuilder withCustomConverters(List<CustomConverter> customConverters) {
        builder.withCustomConverters(customConverters);
        return this;
    }

    /**
     * @param xmlMappingSupplier
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withXmlMapping(java.util.function.Supplier)
     */
    public BeanMapperBuilder withXmlMapping(Supplier<InputStream> xmlMappingSupplier) {
        builder.withXmlMapping(xmlMappingSupplier);
        return this;
    }

    /**
     * @param mappingBuilder
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingBuilder(com.github.dozermapper.core.loader.api.BeanMappingBuilder)
     */
    public BeanMapperBuilder withMappingBuilder(BeanMappingBuilder mappingBuilder) {
        builder.withMappingBuilder(mappingBuilder);
        return this;
    }

    /**
     * @param mappingBuilders
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingBuilders(com.github.dozermapper.core.loader.api.BeanMappingBuilder[])
     */
    public BeanMapperBuilder withMappingBuilders(BeanMappingBuilder... mappingBuilders) {
        builder.withMappingBuilders(mappingBuilders);
        return this;
    }

    /**
     * @param mappingBuilders
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingBuilders(java.util.List)
     */
    public BeanMapperBuilder withMappingBuilders(List<BeanMappingBuilder> mappingBuilders) {
        builder.withMappingBuilders(mappingBuilders);
        return this;
    }

    /**
     * @param beanMappingsBuilder
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanMappingsBuilders(com.github.dozermapper.core.builder.BeanMappingsBuilder)
     */
    public BeanMapperBuilder withBeanMappingsBuilders(BeanMappingsBuilder beanMappingsBuilder) {
        builder.withBeanMappingsBuilders(beanMappingsBuilder);
        return this;
    }

    /**
     * @param beanMappingsBuilder
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanMappingsBuilders(com.github.dozermapper.core.builder.BeanMappingsBuilder[])
     */
    public BeanMapperBuilder withBeanMappingsBuilders(BeanMappingsBuilder... beanMappingsBuilder) {
        builder.withBeanMappingsBuilders(beanMappingsBuilder);
        return this;
    }

    /**
     * @param beanMappingsBuilder
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanMappingsBuilders(java.util.List)
     */
    public BeanMapperBuilder withBeanMappingsBuilders(List<BeanMappingsBuilder> beanMappingsBuilder) {
        builder.withBeanMappingsBuilders(beanMappingsBuilder);
        return this;
    }

    /**
     * @param eventListener
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withEventListener(com.github.dozermapper.core.events.EventListener)
     */
    public BeanMapperBuilder withEventListener(EventListener eventListener) {
        builder.withEventListener(eventListener);
        return this;
    }

    /**
     * @param eventListeners
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withEventListeners(com.github.dozermapper.core.events.EventListener[])
     */
    public BeanMapperBuilder withEventListeners(EventListener... eventListeners) {
        builder.withEventListeners(eventListeners);
        return this;
    }

    /**
     * @param eventListeners
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withEventListeners(java.util.List)
     */
    public BeanMapperBuilder withEventListeners(List<EventListener> eventListeners) {
        builder.withEventListeners(eventListeners);
        return this;
    }

    /**
     * @param customFieldMapper
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomFieldMapper(com.github.dozermapper.core.CustomFieldMapper)
     */
    public BeanMapperBuilder withCustomFieldMapper(CustomFieldMapper customFieldMapper) {
        builder.withCustomFieldMapper(customFieldMapper);
        return this;
    }

    /**
     * @param converterId
     * @param converter
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverterWithId(java.lang.String,
     *      com.github.dozermapper.core.CustomConverter)
     */
    public BeanMapperBuilder withCustomConverterWithId(String converterId, CustomConverter converter) {
        builder.withCustomConverterWithId(converterId, converter);
        return this;
    }

    /**
     * @param customConvertersWithId
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConvertersWithIds(java.util.Map)
     */
    public BeanMapperBuilder withCustomConvertersWithIds(Map<String, CustomConverter> customConvertersWithId) {
        builder.withCustomConvertersWithIds(customConvertersWithId);
        return this;
    }

    /**
     * @param factoryName
     * @param beanFactory
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanFactory(java.lang.String,
     *      com.github.dozermapper.core.BeanFactory)
     */
    public BeanMapperBuilder withBeanFactory(String factoryName, BeanFactory beanFactory) {
        builder.withBeanFactory(factoryName, beanFactory);
        return this;
    }

    /**
     * @param beanFactories
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanFactorys(java.util.Map)
     */
    public BeanMapperBuilder withBeanFactorys(Map<String, BeanFactory> beanFactories) {
        builder.withBeanFactorys(beanFactories);
        return this;
    }

    /**
     * @param processor
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withSettingsProcessor(com.github.dozermapper.core.config.processors.SettingsProcessor)
     */
    public BeanMapperBuilder withSettingsProcessor(SettingsProcessor processor) {
        builder.withSettingsProcessor(processor);
        return this;
    }

    /**
     * @param elEngine
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withELEngine(com.github.dozermapper.core.el.ELEngine)
     */
    public BeanMapperBuilder withELEngine(ELEngine elEngine) {
        builder.withELEngine(elEngine);
        return this;
    }

    //    /**
    //     * @param elementReader
    //     * @return
    //     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withElementReader(com.github.dozermapper.core.loader.xml.ElementReader)
    //     */
    //    public BeanMapperBuilder withElementReader(ElementReader elementReader) {
    //        builder.withElementReader(elementReader);
    //        return this;
    //    }

    /**
     * @param cacheManager
     * @return
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCacheManager(com.github.dozermapper.core.cache.CacheManager)
     */
    public BeanMapperBuilder withCacheManager(CacheManager cacheManager) {
        builder.withCacheManager(cacheManager);
        return this;
    }

}
