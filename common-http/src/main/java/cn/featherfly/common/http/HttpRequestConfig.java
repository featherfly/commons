package cn.featherfly.common.http;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * The Class HttpRequestConfig.
 *
 * @author zhongj
 */
public class HttpRequestConfig {

    /** The connect timeout. */
    public long connectTimeout = 60;

    /** The cache dir. */
    public File cacheDir;

    /** The cache max size. */
    public long cacheMaxSize = 5 * 1024 * 1024;

    /** The executor service. */
    public ExecutorService executorService;

    /** The max requests. */
    public int maxRequests = 64;

    /** The max requests per host. */
    public int maxRequestsPerHost = 5;

    /**
     * Instantiates a new http request config.
     */
    public HttpRequestConfig() {
        this(org.apache.commons.io.FileUtils.getTempDirectory());
    }

    /**
     * Instantiates a new http request config.
     *
     * @param cacheDir the cache dir
     */
    public HttpRequestConfig(File cacheDir) {
        this.cacheDir = cacheDir;
    }
}
