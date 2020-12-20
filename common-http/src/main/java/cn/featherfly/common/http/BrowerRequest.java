
package cn.featherfly.common.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * BrowerRequest.
 *
 * @author zhongj
 */
public class BrowerRequest implements HttpRequest {

    private HttpRequest httpRequest;

    /**
     * Instantiates a new brower request.
     *
     * @param httpRequest the http request
     */
    public BrowerRequest(HttpRequest httpRequest) {
        super();
        this.httpRequest = httpRequest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, R requestBody,
            Class<T> responseType) {
        return sendCompletion(method, url, requestBody, new HashMap<>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, R requestBody,
            Map<String, String> headers, Class<T> responseType) {
        addBrowerHeaders(headers);
        return httpRequest.sendCompletion(method, url, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> T send(HttpMethod method, String url, R requestBody, Class<T> responseType,
            ErrorListener errorListener) {
        return send(method, url, requestBody, new HashMap<>(), responseType, errorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener) {
        addBrowerHeaders(headers);
        return httpRequest.send(method, url, requestBody, headers, responseType, errorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> T send(HttpMethod method, String url, R requestBody, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener, long requestTimeoutSeconds) {
        addBrowerHeaders(headers);
        return httpRequest.send(method, url, requestBody, headers, responseType, errorListener, requestTimeoutSeconds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType) {
        addBrowerHeaders(headers);
        return httpRequest.sendCompletion(method, url, params, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> HttpRequestCompletion<T> sendCompletion(HttpMethod method, String url, Map<String, Serializable> params,
            Class<T> responseType) {
        return sendCompletion(method, url, params, new HashMap<>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, Serializable> params, Map<String, String> headers,
            Class<T> responseType, ErrorListener errorListener) {
        addBrowerHeaders(headers);
        return httpRequest.send(method, url, params, headers, responseType, errorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T send(HttpMethod method, String url, Map<String, Serializable> params, Class<T> responseType,
            ErrorListener errorListener) {
        return send(method, url, params, new HashMap<>(), responseType, errorListener);
    }

    private void addBrowerHeaders(Map<String, String> headers) {
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        //        headers.put("Connection", "keep-alive");
        //        headers.put("Cache-Control", "max-age=0");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36 SE 2.X MetaSr 1.0");

        //        headers.put("", "");
        //                Cookie: PSTM=1562912828; BIDUPSID=98103ECE22548A8A51DDA7E983AC47D3; H_WISE_SIDS=145788_147930_147698_143879_146170_141748_144117_147279_146538_145931_145837_131246_147638_144742_147888_147347_127969_146034_146551_145971_146749_145417_146652_146732_138426_144376_128701_142205_147528_147913_128142_107317_146338_139910_146824_146637_140312_146181_147990_144966_147302_145607_139884_147735_144762_141911_145398_147285_139913_110085; BAIDUID=EDFF24B7ACF424EDF48722DC2777E583:FG=1; Hm_lvt_520a61d0af6df31c19bb0fbd9dba0972=1594902624,1596260435; MCITY=-75%3A; BDUSS=lhM25zU0hpZm5xSDNVQ3pSWmpUNVNYS25jVzc5Qn5PLVlUSVMxMUFvczNCd1JnRVFBQUFBJCQAAAAAAAAAAAEAAADbDMoeZmVhdGhlcmZseV95aQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADd63F83etxfWG; BDORZ=FFFB88E999055A3F8A630C64834BD6D0; H_PS_PSSID=1465_33224_33306_32970_33285_33286_33313_33312_33311_33310_32845_33309_33198_33308_33307_33149; delPer=0; PSINO=6
        //                Host: api.map.baidu.com
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> Observable<T> sendObservable(HttpMethod method, String url, R requestBody, Class<T> responseType) {
        return sendObservable(method, url, requestBody, new HashMap<>(), responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R, T> Observable<T> sendObservable(HttpMethod method, String url, R requestBody,
            Map<String, String> headers, Class<T> responseType) {
        addBrowerHeaders(headers);
        return httpRequest.sendObservable(method, url, requestBody, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, Serializable> params,
            Map<String, String> headers, Class<T> responseType) {
        addBrowerHeaders(headers);
        return httpRequest.sendObservable(method, url, params, headers, responseType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Observable<T> sendObservable(HttpMethod method, String url, Map<String, Serializable> params,
            Class<T> responseType) {
        return sendObservable(method, url, params, new HashMap<>(), responseType);
    }

}
