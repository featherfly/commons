package cn.featherfly.common.exception;

/**
 * 异常code
 * @author 钟冀
 * @since 1.6.0
 * @version 1.0
 */
public interface ExceptionCode {

    int START_NUM = 100001;

    /**
     * 返回异常编号
     * @return 异常编号
     */
    Integer getNum();
    /**
     * 返回异常消息
     * @return 异常消息
     */
    String getMessage();
    /**
     * 返回异常模块
     * @return 异常模块
     */
    String getModule();
    /**
     * 返回异常码
     * @return 异常码
     */
    default String getCode() {
        return getModule() + getNum();
    }
}
