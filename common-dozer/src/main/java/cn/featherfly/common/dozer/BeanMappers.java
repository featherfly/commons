package cn.featherfly.common.dozer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.dozermapper.core.Mapper;

/**
 * Created by zhongj.
 *
 * @author zhongj
 */
public final class BeanMappers {

    /** The Constant IGNORE_NULL. */
    public static final BeanMapper IGNORE_NULL = new BeanMapper(0);

    /** The Constant IGNORE_EMPTY. */
    public static final BeanMapper IGNORE_EMPTY = new BeanMapper(1);

    /** The Constant DEFAULT. */
    public static final BeanMapper DEFAULT = new BeanMapper(2);

    private BeanMappers() {
    }

    /**
     * BeanMapperBuilder.
     *
     * @return the bean mapper builder
     */
    public static BeanMapperBuilder builder() {
        return new BeanMapperBuilder();
    }

    /**
     * Copy the value from A to B, use Default DozerBeanMapper.
     *
     * @param <T>               the generic type
     * @param destinationObject the destination object
     * @param source            the source
     * @return the merged destination object
     */
    public static <T> T copy(T destinationObject, Object source) {
        return DEFAULT.copy(destinationObject, source);
    }

    /**
     * Convert object type.
     *
     * @param <T>              the type parameter
     * @param destinationClass the destination class
     * @param source           the source
     * @return the merged destination object
     */
    public static <T> T copy(Class<T> destinationClass, Object source) {
        try {
            return copy(destinationClass.newInstance(), source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Map list list.
     *
     * @param <T>              the type parameter
     * @param destinationClass the destination class
     * @param sourceList       the source list
     * @return list
     */
    public static <T> List<T> copyList(Class<T> destinationClass, Collection<?> sourceList) {
        return DEFAULT.copyList(destinationClass, sourceList);
    }

    /**
     * Copy the value from A to B.
     *
     * @param <T>               the generic type
     * @param destinationObject the destination object
     * @param source            the source
     * @param mapper            the mapper
     * @return the merged destination object
     */
    public static <T> T copy(T destinationObject, Object source, Mapper mapper) {
        if (mapper == null) {
            return DEFAULT.copy(destinationObject, source);
        } else if (source != null) {
            mapper.map(source, destinationObject);
        }
        return destinationObject;
    }

    /**
     * Convert object type.
     *
     * @param <T>              the type parameter
     * @param destinationClass the destination class
     * @param source           the source
     * @param mapper           the mapper
     * @return the merged destination object
     */
    public static <T> T copy(Class<T> destinationClass, Object source, Mapper mapper) {
        try {
            return copy(destinationClass.newInstance(), source, mapper);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Map list list.
     *
     * @param <T>              the type parameter
     * @param destinationClass the destination class
     * @param sourceList       the source list
     * @param mapper           the mapper
     * @return list
     */
    public static <T> List<T> copyList(Class<T> destinationClass, Collection<?> sourceList, Mapper mapper) {
        List<T> destinationList = new ArrayList<>();
        if (mapper != null) {
            for (Object sourceObject : sourceList) {
                T destinationObject = copy(destinationClass, sourceObject, mapper);
                destinationList.add(destinationObject);
            }
        }
        return destinationList;
    }

    //    public static <T> T copyProperties(final T destination, final Object sources) {
    //        Mapper mapper = FeatherflyDozerBeanMapperBuilder.create().withMappingBuilder(new BeanMappingBuilder() {
    //            @Override
    //            protected void configure() {
    //                mapping(sources.getClass(), destination.getClass(), TypeMappingOptions.mapNull(false),
    //                        TypeMappingOptions.mapEmptyString(false));
    //            }
    //        }).build();
    //        mapper.map(sources, destination);
    //        return destination;
    //    }
}
