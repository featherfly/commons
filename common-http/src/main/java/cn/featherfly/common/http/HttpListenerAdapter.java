
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-05-12 16:00:12
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

import okhttp3.MediaType;

/**
 * HttpListenerAdapter.
 *
 * @author zhongj
 */
public class HttpListenerAdapter implements HttpListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSerialize(Object requestBody, byte[] serializeBody, MediaType mediaType) {

    }

}
