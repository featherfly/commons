
package cn.featherfly.common.constant;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 字符集的常量
 * </p>
 *
 * @author zhongj
 */
public interface Charset {
    /**
     * ISO-8859-1
     *
     * @deprecated {@link StandardCharsets#ISO_8859_1}
     */
    @Deprecated
    String ISO_8859_1 = "ISO-8859-1";
    /**
     * ISO8859_1
     *
     * @deprecated {@link StandardCharsets#ISO_8859_1}
     */
    @Deprecated
    String ISO8859_1 = "ISO8859_1";
    /**
     * UTF-8
     *
     * @deprecated {@link StandardCharsets#UTF_8}
     */
    @Deprecated
    String UTF_8 = "UTF-8";
    /**
     * UTF-16
     *
     * @deprecated {@link StandardCharsets#UTF_16}
     */
    @Deprecated
    String UTF_16 = "UTF-16";
    /**
     * GBK
     * 
     * @deprecated {@link Charsets#GBK}
     */
    @Deprecated
    String GBK = "GBK";
    /**
     * GB2312
     * 
     * @deprecated {@link Charsets#GB2312}
     */
    @Deprecated
    String GB2312 = "GB2312";
    /**
     * GB18030
     * 
     * @deprecated {@link Charsets#GB18030}
     */
    @Deprecated
    String GB18030 = "GB18030";
    /**
     * BIG5
     * 
     * @deprecated {@link Charsets#BIG5}
     */
    @Deprecated
    String BIG5 = "BIG5";
}
