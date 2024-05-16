
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 20:52:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create instantiator by proxy, if error happen, create a ReflectionInstantiator.
 *
 * @author zhongj
 * @since 1.12.1
 */
public class ProxyAndReflectionInstantiatorFactory implements InstantiatorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyAndReflectionInstantiatorFactory.class);

    private final InstantiatorFactory instantiatorFactory;

    /**
     * Instantiates a new proxy and reflection instantiator factory.
     *
     * @param instantiatorFactory the instantiator factory
     */
    public ProxyAndReflectionInstantiatorFactory(InstantiatorFactory instantiatorFactory) {
        super();
        this.instantiatorFactory = instantiatorFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Instantiator<T> create(Class<T> type) {
        try {
            return instantiatorFactory.create(type);
        } catch (Exception e) {
            LOGGER.warn(
                "InstantiatorFactor create Instantiator for {} error, use ReflectionInstantiator instead. \n\t{}",
                type.getName(), e.getMessage());
            return new ReflectionInstantiator<>(type);
        }
    }
}
