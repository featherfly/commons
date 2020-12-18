package cn.featherfly.common.http;

import java.io.File;

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
