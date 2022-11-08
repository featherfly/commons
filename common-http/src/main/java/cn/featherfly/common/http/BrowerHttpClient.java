
package cn.featherfly.common.http;

import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.serialization.Serialization;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * mock brower http client.
 *
 * @author zhongj
 */
public class BrowerHttpClient extends HttpSyncClient {

    /**
     */
    public BrowerHttpClient() {
        this(null);
    }

    /**
     * @param headers the headers
     */
    public BrowerHttpClient(Map<String, String> headers) {
        this(null, headers);
    }

    /**
     * @param client  the client
     * @param headers the headers
     */
    public BrowerHttpClient(OkHttpClient client, Map<String, String> headers) {
        this(client, null, null);
    }

    /**
     * @param client        the client
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public BrowerHttpClient(OkHttpClient client, Serialization serialization, MediaType mediaType) {
        this(client, null, serialization, mediaType);
    }

    /**
     * @param client        the client
     * @param headers       the headers
     * @param serialization the serialization
     * @param mediaType     the media type
     */
    public BrowerHttpClient(OkHttpClient client, Map<String, String> headers, Serialization serialization,
            MediaType mediaType) {
        super(client, headers, serialization, mediaType);

        Map<String, String> browersHeaders = new HashMap<>();
        browersHeaders.put("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        //browersHeaders.put("Accept-Encoding", "gzip, deflate");
        // 不加入Accept-Encoding，okhttp默认会支持gzip
        browersHeaders.put("Accept-Language", "zh-CN,zh;q=0.9");
        //        browersHeaders.put("Connection", "keep-alive");
        browersHeaders.put("Cache-Control", "max-age=0");
        browersHeaders.put("Upgrade-Insecure-Requests", "1");
        // TODO 后续加入操作系统参数
        browersHeaders.put("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36 SE 2.X MetaSr 1.0");

        if (Lang.isNotEmpty(headers)) {
            browersHeaders.putAll(headers);
        }

        init(client, browersHeaders, serialization, mediaType);
    }

}
