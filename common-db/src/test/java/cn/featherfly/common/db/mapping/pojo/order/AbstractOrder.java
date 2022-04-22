package cn.featherfly.common.db.mapping.pojo.order;

import javax.persistence.Embedded;
import javax.persistence.Id;

/**
 * The type abstract Order.
 *
 * @author zhongj
 */
public abstract class AbstractOrder {

    @Id
    private Long id;

    private String no; //订单编号

    private String appKey;

    // 微信支付相关 --------------------------------------------------------

    @Embedded
    private WechatPayOrder wechatOrder;

    //    private String appId;
    //
    //    private String wxTransactionId;
    //
    //    private String wxRefundId;
    //
    //    private String wxPrepayId;
    //
    //    private Date wxPrepayIdExpireTime;

    // 微信支付相关 --------------------------------------------------------

    // 支付宝相关 --------------------------------------------------------

    //    private String alipayTradeNo;

    @Embedded
    private AliPayOrder alipayOrder;

    public abstract OrderType getType();

    /**
     * get id value
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id value
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get no value
     *
     * @return no
     */
    public String getNo() {
        return no;
    }

    /**
     * set no value
     *
     * @param no no
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * get appKey value
     *
     * @return appKey
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * set appKey value
     *
     * @param appKey appKey
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * get wechatOrder value
     *
     * @return wechatOrder
     */
    public WechatPayOrder getWechatOrder() {
        return wechatOrder;
    }

    /**
     * set wechatOrder value
     *
     * @param wechatOrder wechatOrder
     */
    public void setWechatOrder(WechatPayOrder wechatOrder) {
        this.wechatOrder = wechatOrder;
    }

    /**
     * get alipayOrder value
     *
     * @return alipayOrder
     */
    public AliPayOrder getAlipayOrder() {
        return alipayOrder;
    }

    /**
     * set alipayOrder value
     *
     * @param alipayOrder alipayOrder
     */
    public void setAlipayOrder(AliPayOrder alipayOrder) {
        this.alipayOrder = alipayOrder;
    }

}
