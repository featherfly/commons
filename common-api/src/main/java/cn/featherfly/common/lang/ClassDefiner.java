
package cn.featherfly.common.lang;

import java.security.ProtectionDomain;

/**
 * ClassLoader.
 *
 * @author zhongj
 */
public interface ClassDefiner {

    /**
     * Define a custom class (typically a CGLIB proxy class) in this class
     * loader.
     * <p>
     * This is a public equivalent of the protected
     * {@code defineClass(String, byte[], int, int, ProtectionDomain)} method in
     * {@link ClassDefiner} which is traditionally invoked via reflection. A
     * concrete implementation in a custom class loader should simply delegate
     * to that protected method in order to make classloader-specific
     * definitions publicly available without "illegal access" warnings on JDK
     * 9+: {@code return defineClass(name, b, 0, b.length, protectionDomain)}.
     * Note that the JDK 9+ {@code Lookup#defineClass} method does not support a
     * custom target class loader for the new definition; it rather always
     * defines the class in the same class loader as the lookup's context class.
     *
     * @param classLoader      the classLoader
     * @param name             the name of the class
     * @param b                the bytes defining the class
     * @param protectionDomain the protection domain for the class, if any
     * @return the newly created class
     * @throws SecurityException in case of an invalid definition attempt
     * @see java.lang.ClassLoader#defineClass(String, byte[], int, int,
     *      ProtectionDomain)
     */
    Class<?> defineClass(ClassLoader classLoader, String name, byte[] b, ProtectionDomain protectionDomain);
}
