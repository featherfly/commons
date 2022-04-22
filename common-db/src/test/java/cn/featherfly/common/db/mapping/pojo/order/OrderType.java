package cn.featherfly.common.db.mapping.pojo.order;

/**
 * The type OrderType.
 *
 * @author zhongj
 */
public enum OrderType {
    /**
     * 交易订单.
     */
    TRADE,
    /**
     * 用于测试交易订单逻辑的沙盒订单.
     */
    SANDBOX;
}
