
package cn.featherfly.common.repository.mapping;

import javax.persistence.Column;

import cn.featherfly.common.bean.BeanProperty;

/**
 * <p>
 * PropertyNameJpaConversion jpa impl. use javax.persistence.Table
 * </p>
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
        Column column = type.getAnnotation(Column.class);
        if (column != null) {
            return column.name();
            //            ManyToOne manyToOne = type.getAnnotation(ManyToOne.class);
            //            OneToOne oneToOne = type.getAnnotation(OneToOne.class);
            //            if (manyToOne == null && oneToOne == null) {
            //                return column.name();
            //            } else {
            //                BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(type.getType());
            //                BeanProperty<?> bp = bd.findBeanProperty(new BeanPropertyAnnotationMatcher(Id.class));
            //                if (bp == null) {
            //                    throw new HammerException(type.getType().getName() + " no property annotated with @Id");
            //                }
            //                return
            //            }
        }
        return null;
    }

}
