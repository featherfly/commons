package cn.featherfly.common.db.mapping.pojo.order;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * The type AlipayOrderInfo.
 *
 * @author zhongj
 */
public class WechatPayOrder extends PaymentPlatformOrder {

    @Transient
    private String nonceStr;

    @Transient
    private String timeStamp;

    @Transient
    private String signType;

    @Transient
    private String paySign;

    @Column(name = "wx_package")
    private String packageValue;

    @Column(name = "wx_package_expire_time")
    private LocalDateTime packageExpireTime;

    /**
     * get nonceStr value
     *
     * @return nonceStr
     */
    public String getNonceStr() {
        return nonceStr;
    }

    /**
     * set nonceStr value
     *
     * @param nonceStr nonceStr
     */
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    /**
     * get timeStamp value
     *
     * @return timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * set timeStamp value
     *
     * @param timeStamp timeStamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * get signType value
     *
     * @return signType
     */
    public String getSignType() {
        return signType;
    }

    /**
     * set signType value
     *
     * @param signType signType
     */
    public void setSignType(String signType) {
        this.signType = signType;
    }

    /**
     * get paySign value
     *
     * @return paySign
     */
    public String getPaySign() {
        return paySign;
    }

    /**
     * set paySign value
     *
     * @param paySign paySign
     */
    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    /**
     * get packageValue value
     *
     * @return packageValue
     */
    public String getPackageValue() {
        return packageValue;
    }

    /**
     * set packageValue value
     *
     * @param packageValue packageValue
     */
    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    /**
     * get packageExpireTime value
     *
     * @return packageExpireTime
     */
    public LocalDateTime getPackageExpireTime() {
        return packageExpireTime;
    }

    /**
     * set packageExpireTime value
     *
     * @param packageExpireTime packageExpireTime
     */
    public void setPackageExpireTime(LocalDateTime packageExpireTime) {
        this.packageExpireTime = packageExpireTime;
    }

}
