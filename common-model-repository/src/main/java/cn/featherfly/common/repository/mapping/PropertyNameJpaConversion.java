
package cn.featherfly.common.repository.mapping;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import cn.featherfly.common.bean.BeanProperty;

/**
 * PropertyNameJpaConversion jpa impl. use javax.persistence.Table .
 *
 * @author zhongj
 * @since 0.1.0
 * @version 0.1.0
 */
public class PropertyNameJpaConversion implements PropertyNameConversion {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMappingName(BeanProperty<?, ?> type) {
        JoinColumn joinColumn = type.getAnnotation(JoinColumn.class);
        if (joinColumn != null) {
            return joinColumn.name();
        }
        Column column = type.getAnnotation(Column.class);
        if (column != null) {
            return column.name();
        }
        return null;
    }

}
