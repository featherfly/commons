
/*
 * All rights Reserved, Designed By zhongj
 * @Title: AbstractSerializer.java
 * @Package cn.featherfly.common.serialization
 * @Description: AbstractSerializer
 * @author: zhongj
 * @date: 2022-11-07 16:17:07
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.serialization;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * AbstractSerializer.
 *
 * @author zhongj
 */
public abstract class AbstractSerializer implements Serializer {

    protected Charset charset = StandardCharsets.UTF_8;

    /**
     * get charset value
     *
     * @return charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * set charset value
     *
     * @param charset charset
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
