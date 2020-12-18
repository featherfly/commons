
package cn.featherfly.common.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import cn.featherfly.common.io.FileUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http.
 *
 * @author zhongj
 */
public class Http {

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .cache(new okhttp3.Cache(FileUtils.getTempDirectory(), 1024)).connectTimeout(60, TimeUnit.SECONDS).build();

    /**
     * To parame string.
     *
     * @param params the params
     * @return the string
     */
    public static String toParameString(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, Object> entry : params.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param output the output
     */
    public static final void download(String url, OutputStream output) {
        download(url, new HashMap<>(), output);
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param localFile the local file
     */
    public static final void download(String url, File localFile) {
        download(url, new HashMap<>(), localFile);
    }

    /**
     * Download.
     *
     * @param url    the url
     * @param params the params
     * @param output the output
     */
    public static final void download(String url, Map<String, Object> params, OutputStream output) {
        if (Lang.isEmpty(params)) {
            if (!url.contains("?")) {
                url += "?";
            } else {
                url += "&";
            }
            url += toParameString(params);
        }
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = CLIENT.newCall(request).execute();
            output.write(response.body().bytes());
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Download.
     *
     * @param url       the url
     * @param params    the params
     * @param localFile the local file
     */
    public static final void download(String url, Map<String, Object> params, File localFile) {
        AssertIllegalArgument.isNotNull(localFile, "localFile");
        FileUtils.makeDirectory(localFile);
        try {
            download(url, params, new FileOutputStream(localFile));
        } catch (FileNotFoundException e) {
            throw new HttpException(e);
        }
    }
}
