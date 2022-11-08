
/*
 * All rights Reserved, Designed By zhongj
 * @Title: HttpErrorResponseException.java
 * @Package cn.featherfly.common.http
 * @Description: HttpErrorResponseException
 * @author: zhongj
 * @date: 2022-11-08 11:59:08
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

/**
 * HttpErrorResponseException.
 *
 * @author zhongj
 */
public class HttpErrorResponseException extends HttpException {

    private static final long serialVersionUID = 5238669637038661539L;

    private HttpResponse httpResponse;

    public HttpErrorResponseException(String message, HttpResponse httpResponse) {
        super(message);
        this.httpResponse = httpResponse;
    }

    /**
     * get httpResponse value
     *
     * @return httpResponse
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }
}
