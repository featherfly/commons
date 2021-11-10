package cn.featherfly.common.dozer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.dozermapper.core.FeatherflyDozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

/**
 * Created by zhongj.
 *
 * @author zhongj
 */
public class BeanMapper {

    private Mapper mapper;

    BeanMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    BeanMapper(int sign) {
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add(DozerConstants.JAVA8_MAPPING);
        if (sign == 0) {
            mapper = FeatherflyDozerBeanMapperBuilder.create().withMapNull(false).withMappingFiles(mappingFiles)
                    .build();
        } else if (sign == 1) {
            mapper = FeatherflyDozerBeanMapperBuilder.create().withMapNull(false).withMapEmptyString(false)
                    .withMappingFiles(mappingFiles).build();
        } else {
            mapper = FeatherflyDozerBeanMapperBuilder.create().withMappingFiles(mappingFiles).build();
        }
    }

    /**
     * Copy the value from A to B, use Default DozerBeanMapper.
     *
     * @param <T>         the generic type
     * @param destination the destination object
     * @param source      the source
     * @return the merged destination object
     */
    public <T> T copy(T destination, Object source) {
        if (source != null) {
            mapper.map(source, destination);
        }
        return destination;
    }

    /**
     * Convert object type.
     *
     * @param <T>         the type parameter
     * @param destination the destination class
     * @param source      the source
     * @return the merged destination object
     */
    public <T> T copy(Class<T> destination, Object source) {
        try {
            return copy(destination.newInstance(), source);
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
    public <T> List<T> copyList(Class<T> destinationClass, Collection<?> sourceList) {
        List<T> destinationList = new ArrayList<>();
        if (mapper != null) {
            for (Object sourceObject : sourceList) {
                T destinationObject = copy(destinationClass, sourceObject);
                destinationList.add(destinationObject);
            }
        }
        return destinationList;
    }
}
