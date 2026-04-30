
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * ServiceLoaderUtils.
 *
 * @author zhongj
 */
public final class ServiceLoaderUtils {

    private ServiceLoaderUtils() {
    }

    /**
     * 找到多个服务时的策略.
     *
     * @author zhongj
     */
    public enum MultiPolicy {
        /**
         * 出现加载多个时抛出异常.
         */
        EXCEPTION,
        /**
         * 出现加载多个时使用第一个.
         */
        FIRST,
        /**
         * 出现加载多个时使用最后一个.
         */
        LAST;
    }

    /**
     * 没有找到服务时的策略.
     *
     * @author zhongj
     */
    public enum NotFoundPolicy {
        /**
         * 没有可加载时抛出异常.
         */
        EXCEPTION,
        /**
         * 忽略，返回null.
         */
        IGNORE
    }

    /**
     * 使用Java SPI加载指定服务.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType) {
        return load(serviceType, null, null);
    }

    /**
     * 使用Java SPI加载指定服务.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @param classLoader the class loader
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType, ClassLoader classLoader) {
        return load(serviceType, null, classLoader);
    }

    /**
     * 使用Java SPI加载指定服务，如果没有找到则使用传入的默认实现.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @param defaultService 默认服务实现
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType, T defaultService) {
        return load(serviceType, defaultService, MultiPolicy.EXCEPTION, NotFoundPolicy.EXCEPTION);
    }

    /**
     * 使用Java SPI加载指定服务，如果没有找到则使用传入的默认实现.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @param defaultService 默认服务实现
     * @param classLoader the class loader
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType, T defaultService, ClassLoader classLoader) {
        return load(serviceType, defaultService, MultiPolicy.EXCEPTION, NotFoundPolicy.EXCEPTION, classLoader);
    }

    /**
     * 使用Java SPI加载指定服务，如果没有找到则使用传入的默认实现.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @param defaultService 默认服务实现
     * @param multyPolicy 加载出多个实现时的策略{@link MultiPolicy}
     * @param notFoundPolicy 没有加载到实现并且传入的默认实现为空时的策略{@link NotFoundPolicy}
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType, T defaultService, MultiPolicy multyPolicy,
        NotFoundPolicy notFoundPolicy) {
        return load(serviceType, defaultService, multyPolicy, notFoundPolicy, null);
    }

    /**
     * 使用Java SPI加载指定服务，如果没有找到则使用传入的默认实现.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @param defaultService 默认服务实现
     * @param multyPolicy 加载出多个实现时的策略{@link MultiPolicy}
     * @param notFoundPolicy 没有加载到实现并且传入的默认实现为空时的策略{@link NotFoundPolicy}
     * @param classLoader the class loader
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType, T defaultService, MultiPolicy multyPolicy,
        NotFoundPolicy notFoundPolicy, ClassLoader classLoader) {
        List<T> factorys = loadAll(serviceType, classLoader);
        if (factorys.size() > 1) {
            if (multyPolicy == null) {
                multyPolicy = MultiPolicy.EXCEPTION;
            }
            switch (multyPolicy) {
                case FIRST:
                    return factorys.get(0);
                case LAST:
                    return factorys.get(factorys.size() - 1);
                default:
                    throw new IllegalArgumentException("找到多个" + serviceType.getName()
                        + "实现 -> " + factorys);
            }
        }
        if (factorys.isEmpty()) {
            return loadOnNotFound(serviceType, defaultService, notFoundPolicy);
        } else {
            return factorys.get(0);
        }
    }

    private static <T> T loadOnNotFound(Class<T> serviceType, T defaultService, NotFoundPolicy notFoundPolicy) {
        if (defaultService == null) {
            if (notFoundPolicy == NotFoundPolicy.IGNORE) {
                return null;
            } else {
                throw new IllegalArgumentException("没有找到" + serviceType.getName() + "实现");
            }
        } else {
            return defaultService;
        }
    }

    /**
     * 使用Java SPI加载全部服务.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @return 服务实现集合
     */
    public static <T> List<T> loadAll(Class<T> serviceType) {
        return loadAll(serviceType, null);
    }

    /**
     * 使用Java SPI加载全部服务.
     *
     * @param <T> 泛型
     * @param serviceType 服务类型
     * @param classLoader the class loader
     * @return 服务实现集合
     */
    public static <T> List<T> loadAll(Class<T> serviceType, ClassLoader classLoader) {
        final ServiceLoader<T> serviceLoader;
        if (classLoader == null) {
            serviceLoader = ServiceLoader.load(serviceType);
        } else {
            serviceLoader = ServiceLoader.load(serviceType, classLoader);
        }
        List<T> services = new ArrayList<>();
        for (T service : serviceLoader) {
            services.add(service);
        }
        return services;
    }
}
