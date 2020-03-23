
package cn.featherfly.common.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 字符集的常量
 * </p>
 *
 * @author zhongj
 */
public interface Charsets {
    /**
     * ISO8859_1
     *
     * @deprecated {@link StandardCharsets#ISO_8859_1}
     */
    @Deprecated
    Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
    /**
     * UTF-8
     *
     * @deprecated {@link StandardCharsets#UTF_8}
     */
    @Deprecated
    Charset UTF_8 = StandardCharsets.UTF_8;
    /**
     * UTF-16
     *
     * @deprecated {@link StandardCharsets#UTF_16}
     */
    @Deprecated
    Charset UTF_16 = StandardCharsets.UTF_16;
    /**
     * GBK
     */
    Charset GBK = Charset.forName("GBK");
    /**
     * GB2312
     */
    Charset GB2312 = Charset.forName("GB2312");
    /**
     * GB18030
     */
    Charset GB18030 = Charset.forName("GB18030");
    /**
     * BIG5
     */
    Charset BIG5 = Charset.forName("BIG5");
}
