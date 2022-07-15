
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ClassLoaderProxy.java
 * @Package cn.featherfly.common.spring
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2022-07-15 14:18:15
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.spring;

import java.security.ProtectionDomain;

import cn.featherfly.common.lang.ClassDefiner;
import cn.featherfly.common.lang.ClassUtils;

/**
 * RestartClassLoaderClassDefiner.
 *
 * @author zhongj
 */
public class RestartClassLoaderClassDefiner implements ClassDefiner {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> defineClass(ClassLoader classLoader, String name, byte[] b, ProtectionDomain protectionDomain) {
        if (classLoader.getClass().getName()
                .equals("org.springframework.boot.devtools.restart.classloader.RestartClassLoader")) {
            return (Class<?>) ClassUtils.invokeMethod(classLoader, "publicDefineClass", name, b, protectionDomain);
        }
        return null;
    }
}
