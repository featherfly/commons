package cn.featherfly.common.db.mapping.pojo.order;

/**
 * The enum Order status.
 *
 * @author zhongj
 */
public enum OrderStatus {
    /**
     * 0.未支付
     */
    NOTPAY(0, "未支付"),
    /**
     * 1.支付中
     */
    PAYING(1, "支付中"),
    /**
     * 2.已支付
     */
    PAYED(2, "已支付"),
    /**
     * 3.已发货
     */
    DELIVERING(3, "已发货"),
    /**
     * 4.已送达
     */
    DELIVERED(4, "已送达"),
    /**
     * 5.发货出错
     */
    DELIVERING_EXCEPTION(5, "发货出错"),
    /**
     * 6.交易成功
     */
    SUCCESS(6, "交易成功"),
    /**
     * 7.退款中
     */
    REFUNDING(7, "退款中"),
    /**
     * 8.退款成功
     */
    REFUND(8, "退款成功"),
    /**
     * 9.用户取消
     */
    CANCEL(9, "用户取消"),
    /**
     * 10.超时取消
     */
    TIMEOUT(10, "超时取消"),
    /**
     * 11.异常
     */
    EXCEPTION(11, "异常");

    private int value;

    private String label;

    OrderStatus(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    public Integer value() {
        return value;
    }

    public String label() {
        return label;
    }
}