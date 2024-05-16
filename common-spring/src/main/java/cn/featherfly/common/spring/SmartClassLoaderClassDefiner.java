
package cn.featherfly.common.spring;

import java.security.ProtectionDomain;

import org.springframework.core.SmartClassLoader;

import cn.featherfly.common.lang.ClassDefiner;

/**
 * SmartClassLoaderClassDefiner.
 *
 * @author zhongj
 */
public class SmartClassLoaderClassDefiner implements ClassDefiner {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> defineClass(ClassLoader classLoader, String name, byte[] b, ProtectionDomain protectionDomain) {
        if (classLoader instanceof SmartClassLoader) {
            return ((SmartClassLoader) classLoader).publicDefineClass(name, b, protectionDomain);
        }
        return null;
    }
}
