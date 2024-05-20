
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 17:18:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import java.security.ProtectionDomain;

/**
 * load class from bytes.
 *
 * @author zhongj
 */
public class BytesClassLoader extends ClassLoader {

    /**
     * Instantiates a new bytes class loader.
     */
    public BytesClassLoader() {
        this(Thread.currentThread().getContextClassLoader());
    }

    /**
     * Instantiates a new bytes class loader.
     *
     * @param parent the parent
     */
    public BytesClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Define class.
     *
     * @param name the name
     * @param classBytes the class bytes
     * @param protectionDomain the protection domain
     * @return the class
     */
    public Class<?> defineClass(String name, byte[] classBytes, ProtectionDomain protectionDomain) {
        return defineClass(name, classBytes, 0, classBytes.length, protectionDomain);
    }
}
