
package cn.featherfly.common.repository.mapping;

import cn.featherfly.common.lang.WordUtils;

/**
 * use _ join type every word. ClassNameMapping as class_name_mapping.
 *
 * @author zhongj
 * @since 0.3.1
 * @version 0.3.1
 */
public class ClassNameUnderscoreConversion implements ClassNameConversion {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMappingName(Class<?> type) {
        return WordUtils.addSignBeforeUpper(type.getSimpleName(), '_', true);
    }

}
