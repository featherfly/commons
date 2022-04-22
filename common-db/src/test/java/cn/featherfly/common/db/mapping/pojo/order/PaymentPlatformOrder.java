package cn.featherfly.common.db.mapping.pojo.order;

/**
 * The type Payment platform order.
 *
 * @author zhongj
 */

public abstract class PaymentPlatformOrder {

    private String appId;

    /**
     * get appId value
     *
     * @return appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * set appId value
     *
     * @param appId appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

}