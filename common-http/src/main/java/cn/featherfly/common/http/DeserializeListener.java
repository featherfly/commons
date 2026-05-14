
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-05-12 15:50:12
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http;

import okhttp3.MediaType;

/**
 * DeserializeListener.
 *
 * @author zhongj
 */
public interface DeserializeListener {

    /**
     * On deserialize.
     *
     * @param responseBody the response body
     * @param deserializeBody the deserialize body
     * @param mediaType the media type
     */
    void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType);
}
