package cn.featherfly.common.db.mapping.pojo.order;

import javax.persistence.Column;

/**
 * The type AlipayOrderInfo.
 *
 * @author zhongj
 */
public class AliPayOrder extends PaymentPlatformOrder {

    @Column(name = "alipay_trade_no")
    private String tradeNo;

    /**
     * get tradeNo value
     *
     * @return tradeNo
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * set tradeNo value
     *
     * @param tradeNo tradeNo
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

}
