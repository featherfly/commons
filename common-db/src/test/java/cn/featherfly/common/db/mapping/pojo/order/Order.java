package cn.featherfly.common.db.mapping.pojo.order;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The type Order.
 *
 * @author zhongj
 */
@Table
public class Order extends AbstractOrder {

    @Transient
    @Override
    public OrderType getType() {
        return OrderType.TRADE;
    }
}
