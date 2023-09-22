
package cn.featherfly.common.repository.mapping;

import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.lang.WordUtils;

/**
 * use _ join type every word. ClassNameMapping as class_name_mapping.
 *
 * @author zhongj
 * @since 0.3.1
 * @version 0.3.1
 */
public class PropertyNameUnderscoreConversion implements PropertyNameConversion {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMappingName(BeanProperty<?, ?> type) {
        return WordUtils.addSignBeforeUpper(type.getName(), '_', true);
    }

}
