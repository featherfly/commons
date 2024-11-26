
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

    /**
     * Instantiates a new bean mapper builder.
     */
    BeanMapperBuilder() {
        builder = FeatherflyDozerBeanMapperBuilder.create();
    }

    /**
     * Builds the.
     *
     * @return the bean mapper
     */
    public BeanMapper build() {
        return new BeanMapper(builder.withMappingFiles(DozerConstants.JAVA8_MAPPING).build());
    }

    /**
     * With map null.
     *
     * @param isMapNull the is map null
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMapNull(boolean)
     */
    public BeanMapperBuilder withMapNull(boolean isMapNull) {
        builder.withMapNull(isMapNull);
        return this;
    }

    /**
     * With map empty string.
     *
     * @param isMapEmptyString the is map empty string
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMapEmptyString(boolean)
     */
    public BeanMapperBuilder withMapEmptyString(boolean isMapEmptyString) {
        builder.withMapEmptyString(isMapEmptyString);
        return this;
    }

    /**
     * With mapping files.
     *
     * @param mappingFiles the mapping files
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingFiles(java.lang.String[])
     */
    public BeanMapperBuilder withMappingFiles(String... mappingFiles) {
        builder.withMappingFiles(mappingFiles);
        return this;
    }

    /**
     * With mapping files.
     *
     * @param mappingFiles the mapping files
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingFiles(java.util.List)
     */
    public BeanMapperBuilder withMappingFiles(List<String> mappingFiles) {
        builder.withMappingFiles(mappingFiles);
        return this;
    }

    /**
     * With class loader.
     *
     * @param classLoader the class loader
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withClassLoader(com.github.dozermapper.core.util.DozerClassLoader)
     */
    public BeanMapperBuilder withClassLoader(DozerClassLoader classLoader) {
        builder.withClassLoader(classLoader);
        return this;
    }

    /**
     * With class loader.
     *
     * @param classLoader the class loader
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withClassLoader(java.lang.ClassLoader)
     */
    public BeanMapperBuilder withClassLoader(ClassLoader classLoader) {
        builder.withClassLoader(classLoader);
        return this;
    }

    /**
     * With custom converter.
     *
     * @param customConverter the custom converter
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverter(com.github.dozermapper.core.CustomConverter)
     */
    public BeanMapperBuilder withCustomConverter(CustomConverter customConverter) {
        builder.withCustomConverter(customConverter);
        return this;
    }

    /**
     * With custom converters.
     *
     * @param customConverters the custom converters
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverters(com.github.dozermapper.core.CustomConverter[])
     */
    public BeanMapperBuilder withCustomConverters(CustomConverter... customConverters) {
        builder.withCustomConverters(customConverters);
        return this;
    }

    /**
     * With custom converters.
     *
     * @param customConverters the custom converters
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverters(java.util.List)
     */
    public BeanMapperBuilder withCustomConverters(List<CustomConverter> customConverters) {
        builder.withCustomConverters(customConverters);
        return this;
    }

    /**
     * With xml mapping.
     *
     * @param xmlMappingSupplier the xml mapping supplier
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withXmlMapping(java.util.function.Supplier)
     */
    public BeanMapperBuilder withXmlMapping(Supplier<InputStream> xmlMappingSupplier) {
        builder.withXmlMapping(xmlMappingSupplier);
        return this;
    }

    /**
     * With mapping builder.
     *
     * @param mappingBuilder the mapping builder
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingBuilder(com.github.dozermapper.core.loader.api.BeanMappingBuilder)
     */
    public BeanMapperBuilder withMappingBuilder(BeanMappingBuilder mappingBuilder) {
        builder.withMappingBuilder(mappingBuilder);
        return this;
    }

    /**
     * With mapping builders.
     *
     * @param mappingBuilders the mapping builders
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingBuilders(com.github.dozermapper.core.loader.api.BeanMappingBuilder[])
     */
    public BeanMapperBuilder withMappingBuilders(BeanMappingBuilder... mappingBuilders) {
        builder.withMappingBuilders(mappingBuilders);
        return this;
    }

    /**
     * With mapping builders.
     *
     * @param mappingBuilders the mapping builders
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withMappingBuilders(java.util.List)
     */
    public BeanMapperBuilder withMappingBuilders(List<BeanMappingBuilder> mappingBuilders) {
        builder.withMappingBuilders(mappingBuilders);
        return this;
    }

    /**
     * With bean mappings builders.
     *
     * @param beanMappingsBuilder the bean mappings builder
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanMappingsBuilders(com.github.dozermapper.core.builder.BeanMappingsBuilder)
     */
    public BeanMapperBuilder withBeanMappingsBuilders(BeanMappingsBuilder beanMappingsBuilder) {
        builder.withBeanMappingsBuilders(beanMappingsBuilder);
        return this;
    }

    /**
     * With bean mappings builders.
     *
     * @param beanMappingsBuilder the bean mappings builder
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanMappingsBuilders(com.github.dozermapper.core.builder.BeanMappingsBuilder[])
     */
    public BeanMapperBuilder withBeanMappingsBuilders(BeanMappingsBuilder... beanMappingsBuilder) {
        builder.withBeanMappingsBuilders(beanMappingsBuilder);
        return this;
    }

    /**
     * With bean mappings builders.
     *
     * @param beanMappingsBuilder the bean mappings builder
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanMappingsBuilders(java.util.List)
     */
    public BeanMapperBuilder withBeanMappingsBuilders(List<BeanMappingsBuilder> beanMappingsBuilder) {
        builder.withBeanMappingsBuilders(beanMappingsBuilder);
        return this;
    }

    /**
     * With event listener.
     *
     * @param eventListener the event listener
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withEventListener(com.github.dozermapper.core.events.EventListener)
     */
    public BeanMapperBuilder withEventListener(EventListener eventListener) {
        builder.withEventListener(eventListener);
        return this;
    }

    /**
     * With event listeners.
     *
     * @param eventListeners the event listeners
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withEventListeners(com.github.dozermapper.core.events.EventListener[])
     */
    public BeanMapperBuilder withEventListeners(EventListener... eventListeners) {
        builder.withEventListeners(eventListeners);
        return this;
    }

    /**
     * With event listeners.
     *
     * @param eventListeners the event listeners
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withEventListeners(java.util.List)
     */
    public BeanMapperBuilder withEventListeners(List<EventListener> eventListeners) {
        builder.withEventListeners(eventListeners);
        return this;
    }

    /**
     * With custom field mapper.
     *
     * @param customFieldMapper the custom field mapper
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomFieldMapper(com.github.dozermapper.core.CustomFieldMapper)
     */
    public BeanMapperBuilder withCustomFieldMapper(CustomFieldMapper customFieldMapper) {
        builder.withCustomFieldMapper(customFieldMapper);
        return this;
    }

    /**
     * With custom converter with id.
     *
     * @param converterId the converter id
     * @param converter the converter
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConverterWithId(java.lang.String,
     *      com.github.dozermapper.core.CustomConverter)
     */
    public BeanMapperBuilder withCustomConverterWithId(String converterId, CustomConverter converter) {
        builder.withCustomConverterWithId(converterId, converter);
        return this;
    }

    /**
     * With custom converters with ids.
     *
     * @param customConvertersWithId the custom converters with id
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCustomConvertersWithIds(java.util.Map)
     */
    public BeanMapperBuilder withCustomConvertersWithIds(Map<String, CustomConverter> customConvertersWithId) {
        builder.withCustomConvertersWithIds(customConvertersWithId);
        return this;
    }

    /**
     * With bean factory.
     *
     * @param factoryName the factory name
     * @param beanFactory the bean factory
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanFactory(java.lang.String,
     *      com.github.dozermapper.core.BeanFactory)
     */
    public BeanMapperBuilder withBeanFactory(String factoryName, BeanFactory beanFactory) {
        builder.withBeanFactory(factoryName, beanFactory);
        return this;
    }

    /**
     * With bean factorys.
     *
     * @param beanFactories the bean factories
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withBeanFactorys(java.util.Map)
     */
    public BeanMapperBuilder withBeanFactorys(Map<String, BeanFactory> beanFactories) {
        builder.withBeanFactorys(beanFactories);
        return this;
    }

    /**
     * With settings processor.
     *
     * @param processor the processor
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withSettingsProcessor(com.github.dozermapper.core.config.processors.SettingsProcessor)
     */
    public BeanMapperBuilder withSettingsProcessor(SettingsProcessor processor) {
        builder.withSettingsProcessor(processor);
        return this;
    }

    /**
     * With EL engine.
     *
     * @param elEngine the el engine
     * @return the bean mapper builder
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
     * With cache manager.
     *
     * @param cacheManager the cache manager
     * @return the bean mapper builder
     * @see com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder#withCacheManager(com.github.dozermapper.core.cache.CacheManager)
     */
    public BeanMapperBuilder withCacheManager(CacheManager cacheManager) {
        builder.withCacheManager(cacheManager);
        return this;
    }

}
