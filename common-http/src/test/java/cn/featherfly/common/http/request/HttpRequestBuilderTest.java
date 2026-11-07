
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 17:17:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

import java.util.Collections;

import cn.featherfly.common.http.HttpSyncClient;

/**
 * HttpRequestBuilderTest.
 *
 * @author zhongj
 */
public class HttpRequestBuilderTest {

    public static void main(String[] args) {
        HttpRequestBuilder builder = null;

        // http get
        builder.url("").get().getHttpRequest();
        // set header
        builder.url("").get().header(Collections.emptyMap()).getHttpRequest();
        builder.url("").get().header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        builder.url("").get().parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").get().parameter(pb -> pb.set("name", "")).getHttpRequest();
        // set header and parameter
        builder.url("").get().header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").get().header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()).getHttpRequest();

        builder.url("").get().header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").get().header(hb -> hb.add("header", "")).parameter(pb -> pb.set("name", "")).getHttpRequest();

        // http head
        builder.url("").delete().getHttpRequest();
        // set header
        builder.url("").delete().header(Collections.emptyMap()).getHttpRequest();
        builder.url("").delete().header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        builder.url("").delete().parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").delete().parameter(pb -> pb.set("name", "")).getHttpRequest();
        // set header and parameter
        builder.url("").delete().header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").delete().header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()).getHttpRequest();

        builder.url("").delete().header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").delete().header(hb -> hb.add("header", "")).parameter(pb -> pb.set("name", ""))
            .getHttpRequest();

        // http delete
        builder.url("").delete().getHttpRequest();
        // set header
        builder.url("").delete().header(Collections.emptyMap()).getHttpRequest();
        builder.url("").delete().header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        builder.url("").delete().parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").delete().parameter(pb -> pb.set("name", "")).getHttpRequest();
        // set header and parameter
        builder.url("").delete().header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").delete().header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()).getHttpRequest();

        builder.url("").delete().header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").delete().header(hb -> hb.add("header", "")).parameter(pb -> pb.set("name", ""))
            .getHttpRequest();

        // -----------------------

        // http post
        builder.url("").post().getHttpRequest();
        // set header
        builder.url("").post().header(Collections.emptyMap()).getHttpRequest();
        builder.url("").post().header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        builder.url("").post().parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").post().parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").post().parameter(pb -> pb.body(String.class)).getHttpRequest();
        builder.url("").post().parameter(pb -> pb.set("name", "").body(String.class)).getHttpRequest();
        // set header and parameter
        builder.url("").post().header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").post().header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").post().header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").post().header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class))
            .getHttpRequest();

        // http put
        builder.url("").put().getHttpRequest();
        // set header
        builder.url("").put().header(Collections.emptyMap()).getHttpRequest();
        builder.url("").put().header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        builder.url("").put().parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").put().parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").put().parameter(pb -> pb.body(String.class)).getHttpRequest();
        builder.url("").put().parameter(pb -> pb.set("name", "").body(String.class)).getHttpRequest();
        // set header and parameter
        builder.url("").put().header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").put().header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").put().header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").put().header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class))
            .getHttpRequest();

        // http patch
        builder.url("").patch().getHttpRequest();
        // set header
        builder.url("").patch().header(Collections.emptyMap()).getHttpRequest();
        builder.url("").patch().header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        builder.url("").patch().parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").patch().parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").patch().parameter(pb -> pb.body(String.class)).getHttpRequest();
        builder.url("").patch().parameter(pb -> pb.set("name", "").body(String.class)).getHttpRequest();
        // set header and parameter
        builder.url("").patch().header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").patch().header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()).getHttpRequest();
        builder.url("").patch().header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        builder.url("").patch().header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class))
            .getHttpRequest();

        BasicMethodHttpRequestBuilder basicMethodBuilder = null;

        // http get head delete
        basicMethodBuilder.url("").getHttpRequest();
        // set header
        basicMethodBuilder.url("").header(Collections.emptyMap()).getHttpRequest();
        basicMethodBuilder.url("").header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        basicMethodBuilder.url("").parameter(Collections.emptyMap()).getHttpRequest();
        basicMethodBuilder.url("").parameter(pb -> pb.set("name", "")).getHttpRequest();
        // set header and parameter
        basicMethodBuilder.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        basicMethodBuilder.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap())
            .getHttpRequest();

        basicMethodBuilder.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        basicMethodBuilder.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.set("name", ""))
            .getHttpRequest();

        FormMethodHttpRequestBuilder formMethodBuilder = null;
        // http post put patch
        formMethodBuilder.url("").getHttpRequest();
        // set header
        formMethodBuilder.url("").header(Collections.emptyMap()).getHttpRequest();
        formMethodBuilder.url("").header(hb -> hb.add("header", "")).getHttpRequest();
        // set parameter
        formMethodBuilder.url("").parameter(Collections.emptyMap()).getHttpRequest();
        formMethodBuilder.url("").parameter(pb -> pb.set("name", "")).getHttpRequest();
        formMethodBuilder.url("").parameter(pb -> pb.body(String.class)).getHttpRequest();
        formMethodBuilder.url("").parameter(pb -> pb.set("name", "").body(String.class)).getHttpRequest();
        // set header and parameter
        formMethodBuilder.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()).getHttpRequest();
        formMethodBuilder.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()).getHttpRequest();
        formMethodBuilder.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")).getHttpRequest();
        formMethodBuilder.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class))
            .getHttpRequest();

        HttpSyncClient http = null;
        // get request
        http.get(rb -> rb.url(""));
        // set header
        http.get(rb -> rb.url("").header(Collections.emptyMap()));
        http.get(rb -> rb.url("").header(hb -> hb.add("header", "")));
        // set parameter
        http.get(rb -> rb.url("").parameter(Collections.emptyMap()));
        http.get(rb -> rb.url("").parameter(pb -> pb.set("name", "")));
        // set header and parameter
        http.get(rb -> rb.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()));
        http.get(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()));
        http.get(rb -> rb.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")));
        http.get(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.set("name", "")));

        // head request
        http.delete(rb -> rb.url(""));
        // set header
        http.delete(rb -> rb.url("").header(Collections.emptyMap()));
        http.delete(rb -> rb.url("").header(hb -> hb.add("header", "")));
        // set parameter
        http.delete(rb -> rb.url("").parameter(Collections.emptyMap()));
        http.delete(rb -> rb.url("").parameter(pb -> pb.set("name", "")));
        // set header and parameter
        http.delete(rb -> rb.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()));
        http.delete(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()));
        http.delete(rb -> rb.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")));
        http.delete(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.set("name", "")));

        // head request
        http.delete(rb -> rb.url(""));
        // set header
        http.delete(rb -> rb.url("").header(Collections.emptyMap()));
        http.delete(rb -> rb.url("").header(hb -> hb.add("header", "")));
        // set parameter
        http.delete(rb -> rb.url("").parameter(Collections.emptyMap()));
        http.delete(rb -> rb.url("").parameter(pb -> pb.set("name", "")));
        // set header and parameter
        http.delete(rb -> rb.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()));
        http.delete(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()));
        http.delete(rb -> rb.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")));
        http.delete(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.set("name", "")));

        // http post
        http.post(rb -> rb.url(""));
        // set header
        http.post(rb -> rb.url("").header(Collections.emptyMap()));
        http.post(rb -> rb.url("").header(hb -> hb.add("header", "")));
        // set parameter
        http.post(rb -> rb.url("").parameter(Collections.emptyMap()));
        http.post(rb -> rb.url("").parameter(pb -> pb.set("name", "")));
        http.post(rb -> rb.url("").parameter(pb -> pb.body(String.class)));
        http.post(rb -> rb.url("").parameter(pb -> pb.set("name", "").body(String.class)));
        // set header and parameter
        http.post(rb -> rb.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()));
        http.post(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()));
        http.post(rb -> rb.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")));
        http.post(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class)));

        // http put
        http.put(rb -> rb.url(""));
        // set header
        http.put(rb -> rb.url("").header(Collections.emptyMap()));
        http.put(rb -> rb.url("").header(hb -> hb.add("header", "")));
        // set parameter
        http.put(rb -> rb.url("").parameter(Collections.emptyMap()));
        http.put(rb -> rb.url("").parameter(pb -> pb.set("name", "")));
        http.put(rb -> rb.url("").parameter(pb -> pb.body(String.class)));
        http.put(rb -> rb.url("").parameter(pb -> pb.set("name", "").body(String.class)));
        // set header and parameter
        http.put(rb -> rb.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()));
        http.put(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()));
        http.put(rb -> rb.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")));
        http.put(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class)));

        // http patch
        http.patch(rb -> rb.url(""));
        // set header
        http.patch(rb -> rb.url("").header(Collections.emptyMap()));
        http.patch(rb -> rb.url("").header(hb -> hb.add("header", "")));
        // set parameter
        http.patch(rb -> rb.url("").parameter(Collections.emptyMap()));
        http.patch(rb -> rb.url("").parameter(pb -> pb.set("name", "")));
        http.patch(rb -> rb.url("").parameter(pb -> pb.body(String.class)));
        http.patch(rb -> rb.url("").parameter(pb -> pb.set("name", "").body(String.class)));
        // set header and parameter
        http.patch(rb -> rb.url("").header(Collections.emptyMap()).parameter(Collections.emptyMap()));
        http.patch(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()));
        http.patch(rb -> rb.url("").header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")));
        http.patch(rb -> rb.url("").header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class)));

        // http request
        http.request(rb -> rb.url("").post());
        // set header
        http.request(rb -> rb.url("").post().header(Collections.emptyMap()));
        http.request(rb -> rb.url("").post().header(hb -> hb.add("header", "")));
        // set parameter
        http.request(rb -> rb.url("").post().parameter(Collections.emptyMap()));
        http.request(rb -> rb.url("").post().parameter(pb -> pb.set("name", "")));
        http.request(rb -> rb.url("").post().parameter(pb -> pb.body(String.class)));
        http.request(rb -> rb.url("").post().parameter(pb -> pb.set("name", "").body(String.class)));
        // set header and parameter
        http.request(rb -> rb.url("").post().header(Collections.emptyMap()).parameter(Collections.emptyMap()));
        http.request(rb -> rb.url("").post().header(hb -> hb.add("header", "")).parameter(Collections.emptyMap()));
        http.request(rb -> rb.url("").post().header(Collections.emptyMap()).parameter(pb -> pb.set("name", "")));
        http.request(rb -> rb.url("").post().header(hb -> hb.add("header", "")).parameter(pb -> pb.body(String.class)));
    }
}
