
/*
 * Thgk-Commons created on May 21, 2010 12:06:27 AM
 */
package cn.featherfly.common.lang;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 类加载器工具.
 *
 * @author zhongj
 * @version 1.0
 * @since 1.0
 */
public final class ClassLoaderUtils {

    private ClassLoaderUtils() {
    }

    private static final String ENTER = "\n";
    private static final String TAB = "\t";

    private static List<ClassDefiner> classDefiners = ServiceLoaderUtils.loadAll(ClassDefiner.class);

    /**
     * Define class.
     *
     * @param classLoader      the class loader
     * @param name             the name
     * @param code             the code
     * @param protectionDomain the protection domain
     * @param supplier         the supplier
     * @return the class
     */
    public static Class<?> defineClass(ClassLoader classLoader, String name, byte[] code,
            ProtectionDomain protectionDomain, Supplier<Class<?>> supplier) {
        Class<?> res;
        for (ClassDefiner cd : classDefiners) {
            res = cd.defineClass(classLoader, name, code, protectionDomain);
            if (res != null) {
                return res;
            }
        }
        return supplier.get();
    }

    /**
     * 加载给定名称的所有资源，将搜索类加载器或得的所有结果汇总.
     * 如果没有发现结果，如果资源文件不是以'/'开头，则在资源名称前加前缀'/'，并再次查找。 此方法将尝试加载资源的顺序如下：
     * <ul>
     * <li>Thread.currentThread().getContextClassLoader()
     * <li>ResourceLoader.class.getClassLoader()
     * <li>callingClass.getClassLoader()
     * </ul>
     *
     * @param resourceName 需要加载的资源名称
     * @param aggregate    aggregate
     * @return 资源路径的迭代器
     * @throws IOException IO异常
     */
    public static Iterator<URL> getResources(String resourceName, boolean aggregate) throws IOException {
        AggregateIterator<URL> iterator = new AggregateIterator<>();

        iterator.addEnumeration(Thread.currentThread().getContextClassLoader().getResources(resourceName));

        if (!iterator.hasNext() || aggregate) {
            iterator.addEnumeration(ClassLoaderUtils.class.getClassLoader().getResources(resourceName));
        }

        Class<?> callingClass = ClassUtils.forName(Lang.getInvoker().getClassName());

        if (!iterator.hasNext() || aggregate) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                iterator.addEnumeration(cl.getResources(resourceName));
            }
        }

        if (!iterator.hasNext() && resourceName != null && resourceName.charAt(0) != '/') {
            return getResources('/' + resourceName, callingClass, aggregate);
        }

        return iterator;
    }

    /**
     * 加载给定名称的所有资源，将搜索类加载器或得的所有结果汇总.
     * 如果没有发现结果，如果资源文件不是以'/'开头，则在资源名称前加前缀'/'，并再次查找。 此方法将尝试加载资源的顺序如下：
     * <ul>
     * <li>Thread.currentThread().getContextClassLoader()
     * <li>ResourceLoader.class.getClassLoader()
     * <li>callingClass.getClassLoader()
     * </ul>
     *
     * @param resourceName 需要加载的资源名称
     * @param callingClass 调用对象的class
     * @param aggregate    aggregate
     * @return 资源路径的迭代器
     * @throws IOException IO异常
     */
    public static Iterator<URL> getResources(String resourceName, Class<?> callingClass, boolean aggregate)
            throws IOException {

        AggregateIterator<URL> iterator = new AggregateIterator<>();

        iterator.addEnumeration(Thread.currentThread().getContextClassLoader().getResources(resourceName));

        if (!iterator.hasNext() || aggregate) {
            iterator.addEnumeration(ClassLoaderUtils.class.getClassLoader().getResources(resourceName));
        }

        if (!iterator.hasNext() || aggregate) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                iterator.addEnumeration(cl.getResources(resourceName));
            }
        }

        if (!iterator.hasNext() && resourceName != null && resourceName.charAt(0) != '/') {
            return getResources('/' + resourceName, callingClass, aggregate);
        }

        return iterator;
    }

    /**
     * 加载给定的资源. 此方法将尝试加载资源的顺序如下：
     * <ul>
     * <li>从 Thread.currentThread().getContextClassLoader() 加载
     * <li>从 ResourceLoader.class.getClassLoader() 加载
     * <li>callingClass.getClassLoader()
     * </ul>
     *
     * @param resourceName 需要加载的资源名称
     * @return 资源路径
     */
    public static URL getResource(String resourceName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = ClassLoaderUtils.class.getClassLoader().getResource(resourceName);
        }
        if (url != null) {
            return url;
        }
        Class<?> callingClass = ClassUtils.forName(Lang.getInvoker().getClassName());
        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if (url == null && resourceName != null && resourceName.charAt(0) != '/') {
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

    /**
     * 这是一个用来方便的加载流资源的方法. 用于查找资源的算法在getResource（）中给出了
     *
     * @param resourceName 需要加载的资源名称
     * @return 资源输入流
     */
    public static InputStream getResourceAsStream(String resourceName) {
        URL url = getResource(resourceName);
        try {
            return url != null ? url.openStream() : null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 加载给定的资源. 此方法将尝试加载资源的顺序如下：
     * <ul>
     * <li>从 Thread.currentThread().getContextClassLoader() 加载
     * <li>从 ResourceLoader.class.getClassLoader() 加载
     * <li>callingClass.getClassLoader()
     * </ul>
     *
     * @param resourceName 需要加载的资源名称
     * @param callingClass 调用对象的class
     * @return 资源路径
     */
    public static URL getResource(String resourceName, Class<?> callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = ClassLoaderUtils.class.getClassLoader().getResource(resourceName);
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if (url == null && resourceName != null && resourceName.charAt(0) != '/') {
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

    /**
     * 这是一个用来方便的加载流资源的方法. 用于查找资源的算法在getResource（）中给出了
     *
     * @param resourceName 需要加载的资源名称
     * @param callingClass 调用对象的class
     * @return 资源输入流
     */
    public static InputStream getResourceAsStream(String resourceName, Class<?> callingClass) {
        URL url = getResource(resourceName, callingClass);

        try {
            return url != null ? url.openStream() : null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 使用传入的名称加载类（class）. 加载的顺序如下所示：
     * <ul>
     * <li>从 Thread.currentThread().getContextClassLoader() 加载
     * <li>使用 Class.forName() 加载
     * <li>从 ResourceLoader.class.getClassLoader() 加载
     * <li>callingClass.getClassLoader()
     * </ul>
     *
     * @param className    需要加载的类（class）名称
     * @param callingClass 调用类或对象的class属性
     * @return 加载的类
     * @throws ClassNotFoundException 如果从以上提供的几个地方都未加载到，则抛出.
     */
    public static Class<?> loadClass(String className, Class<?> callingClass) throws ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                try {
                    return ClassLoaderUtils.class.getClassLoader().loadClass(className);
                } catch (ClassNotFoundException exc) {
                    return callingClass.getClassLoader().loadClass(className);
                }
            }
        }
    }

    /**
     * 打印输出当前类加载器的层次结构 - 调试很有用.
     */
    public static void printClassLoader() {
        System.out.println("ClassLoaderUtils.printClassLoader");
        printClassLoader(Thread.currentThread().getContextClassLoader());
    }

    /**
     * 打印输出给定类加载器的层次结构 - 调试很有用.
     *
     * @param cl 给定的类加载器
     */
    public static void printClassLoader(ClassLoader cl) {
        System.out.println("ClassLoaderUtils.printClassLoader(cl = " + cl + ")");
        if (cl != null) {
            printClassLoader(cl.getParent());
        }
    }

    /**
     * 显示类加载器的层次结构. 使用默认换行符和制表符的文本字符.
     *
     * @param obj  对象层次结构的分析装载机
     * @param role 当前类在应用程序中作用的说明 （例如，"servlet"或"EJB"的引用）
     * @return 一个字符串，显示这个类的类加载器层次结构
     */
    public static String showClassLoaderHierarchy(Object obj, String role) {
        return showClassLoaderHierarchy(obj, role, ENTER, TAB);
    }

    /**
     * 显示类加载器的层次结构.
     *
     * @param obj       对象层次结构的分析装载机
     * @param role      当前类在应用程序中作用的说明 （例如，"servlet"或"EJB"的引用）
     * @param lineBreak 设置断行符
     * @param tabText   设置tabText文本使用的标签
     * @return 一个字符串，显示这个类的类加载器层次结构
     */
    public static String showClassLoaderHierarchy(Object obj, String role, String lineBreak, String tabText) {
        String s = "object of " + obj.getClass() + ": role is " + role + lineBreak;
        return s + showClassLoaderHierarchy(obj.getClass().getClassLoader(), lineBreak, tabText, 0);
    }

    /**
     * 显示给定类加载器的层次结构. 使用默认换行符和制表符的文本字符.
     *
     * @param cl 需要分析的classLoader
     * @return 一个字符串，显示这个类的类加载器层次结构
     */
    public static String showClassLoaderHierarchy(ClassLoader cl) {
        return showClassLoaderHierarchy(cl, ENTER, TAB);
    }

    /**
     * 显示给定类加载器的层次结构.
     *
     * @param cl        需要分析的classLoader
     * @param lineBreak 设置断行符
     * @param tabText   设置tabText文本使用的标签
     * @return 一个字符串，显示这个类的类加载器层次结构
     */
    public static String showClassLoaderHierarchy(ClassLoader cl, String lineBreak, String tabText) {
        return showClassLoaderHierarchy(cl, lineBreak, tabText, 0);
    }

    /**
     * 显示给定类加载器的层次结构.
     *
     * @param c1        需要分析的classLoader
     * @param lineBreak 设置断行符
     * @param tabText   设置tabText文本使用的标签
     * @param indent    当前loader的嵌套级别（从0开始）;用于格式化打印输出
     * @return 一个字符串，显示这个类的类加载器层次结构
     */
    private static String showClassLoaderHierarchy(ClassLoader cl, String lineBreak, String tabText, int indent) {
        if (cl == null) {
            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            return "context class loader=[" + ccl + " ] hashCode=" + ccl.hashCode();
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < indent; i++) {
            buf.append(tabText);
        }
        buf.append("[").append(cl).append("] hashCode=").append(cl.hashCode()).append(lineBreak);
        ClassLoader parent = cl.getParent();
        return buf.toString() + showClassLoaderHierarchy(parent, lineBreak, tabText, indent + 1);
    }

    /**
     * 聚合成一个能进行重复迭代和过滤的枚举实例。 始终保持一领先的统计员，以防止重复返回。.
     *
     * @author zhongj
     * @param <E> the element type
     */
    protected static class AggregateIterator<E> implements Iterator<E> {

        /** The enums. */
        LinkedList<Enumeration<E>> enums = new LinkedList<>();

        /** The cur. */
        Enumeration<E> cur = null;

        /** The next. */
        E next = null;

        /** The loaded. */
        Set<E> loaded = new HashSet<>();

        /**
         * Adds the enumeration.
         *
         * @param e the e
         * @return the aggregate iterator
         */
        @SuppressWarnings("rawtypes")
        public AggregateIterator addEnumeration(Enumeration<E> e) {
            if (e.hasMoreElements()) {
                if (cur == null) {
                    cur = e;
                    next = e.nextElement();
                    loaded.add(next);
                } else {
                    enums.add(e);
                }
            }
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E next() {
            if (next != null) {
                E prev = next;
                next = loadNext();
                return prev;
            } else {
                throw new NoSuchElementException();
            }
        }

        private Enumeration<E> determineCurrentEnumeration() {
            if (cur != null && !cur.hasMoreElements()) {
                if (enums.size() > 0) {
                    cur = enums.removeLast();
                } else {
                    cur = null;
                }
            }
            return cur;
        }

        private E loadNext() {
            if (determineCurrentEnumeration() != null) {
                E tmp = cur.nextElement();
                while (loaded.contains(tmp)) {
                    tmp = loadNext();
                    if (tmp == null) {
                        break;
                    }
                }
                if (tmp != null) {
                    loaded.add(tmp);
                }
                return tmp;
            }
            return null;

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
