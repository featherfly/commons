
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * <p>
 * ServiceLoaderUtils
 * </p>
 * 
 * @author 钟冀
 */
public final class ServiceLoaderUtils {
    /**
     */
    private ServiceLoaderUtils() {
    }
    /**
     * <p>
     * 找到多个服务时的策略
     * </p>
     * @author 钟冀
     */
    public enum MultiPolicy {
        /**
         * 出现加载多个时抛出异常
         */
        EXCEPTION
        /**
         * 出现加载多个时使用第一个
         */
        , FIRST
        /**
         * 出现加载多个时使用最后一个
         */
        , LAST;
    } 
    /**
     * <p>
     * 没有找到服务时的策略
     * </p>
     * @author 钟冀
     */
    public enum NotFoundPolicy {
        /**
         * 没有可加载时抛出异常
         */
        exception
        /**
         * 忽略，返回null
         */
        , ignore
    } 
    
    /**
     * <p>
     * 使用Java SPI加载指定服务.
     * </p>
     * @param serviceType 服务类型
     * @param <T> 泛型
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType) {
        return load(serviceType, null);
    }
    /**
     * <p>
     * 使用Java SPI加载指定服务，如果没有找到则使用传入的默认实现.
     * </p>
     * @param serviceType 服务类型
     * @param defaultService 默认服务实现
     * @param <T> 泛型
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType, T defaultService) {
        return load(serviceType, defaultService, MultiPolicy.EXCEPTION, NotFoundPolicy.exception);
    }
    /**
     * <p>
     * 使用Java SPI加载指定服务，如果没有找到则使用传入的默认实现.
     * </p>
     * @param serviceType 服务类型
     * @param defaultService 默认服务实现
     * @param multyPolicy 加载出多个实现时的策略{@link MultiPolicy}
     * @param notFoundPolicy 没有加载到实现并且传入的默认实现为空时的策略{@link NotFoundPolicy}
     * @param <T> 泛型
     * @return 服务实现
     */
    public static <T> T load(Class<T> serviceType, T defaultService, MultiPolicy multyPolicy
                    , NotFoundPolicy notFoundPolicy) {        
        List<T> factorys = loadAll(serviceType);
        if (factorys.size() > 1) {
            if (multyPolicy == null) {
                multyPolicy = MultiPolicy.EXCEPTION;
            }            
            switch (multyPolicy) {
                case FIRST: return factorys.get(0);    
                case LAST: return factorys.get(factorys.size() - 1);
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
            switch (notFoundPolicy) {
                case ignore: return null;    
                default:
                    throw new IllegalArgumentException("没有找到" + serviceType.getName() + "实现");
            }
        } else {
            return defaultService;
        }
    }
    /**
     * <p>
     * 使用Java SPI加载全部服务
     * </p>
     * @param serviceType 服务类型
     * @param <T> 泛型
     * @return 服务实现集合
     */
    public static <T> List<T> loadAll(Class<T> serviceType) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(serviceType);
        List<T> services = new ArrayList<T>();        
        for (T service : serviceLoader) {
            services.add(service);
        }
        return services;
    }
}
