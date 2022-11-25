
package cn.featherfly.common.log;

import java.lang.reflect.Method;

import cn.featherfly.common.exception.InitException;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.javassist.Javassist;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.lang.WordUtils;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * LoggerFactory.
 *
 * @author zhongj
 */
public class LoggerFactory {

    private static final String SLF4J_LOG4J_IMPL = "org.slf4j.impl.Log4jLoggerAdapter";

    private static final String LOG4J2_SLF4J_IMPL = "org.apache.logging.slf4j.Log4jLogger";

    private static boolean init;

    public static Slf4jLogger getLogger() {
        String className = Lang.getInvoker().getClassName();
        return getLogger(className);
    }

    public static Slf4jLogger getLogger(String name) {
        _init();
        return (Slf4jLogger) org.slf4j.LoggerFactory.getLogger(name);
    }

    public static Slf4jLogger getLogger(Class<?> clazz) {
        _init();
        return (Slf4jLogger) org.slf4j.LoggerFactory.getLogger(clazz);
    }

    public static synchronized void init() {
        if (!init) {
            if (initLog4j2Slf4j() || initSlf4jLog4j12()) {
                init = true;
            } else {
                throw new UnsupportedException("unsupported");
            }
        }
    }

    private static boolean initSlf4jLog4j12() {
        // slf4j-log4j12-1.7.30.jar
        if (SystemPropertyUtils.getJavaClassPath().contains("slf4j-log4j12")) {
            ClassPool pool = ClassPool.getDefault();
            try {
                // FIXME 如果其他地方先加载了，这里会报错
                CtClass ctClass = pool.get(SLF4J_LOG4J_IMPL);
                ctClass.addInterface(pool.get(Slf4jLogger.class.getName()));
                addMethods(ctClass, pool);
                ctClass.toClass(Thread.currentThread().getContextClassLoader(),
                        Thread.currentThread().getContextClassLoader().getClass().getProtectionDomain());
                ctClass.detach();
                return true;
            } catch (NotFoundException | CannotCompileException e) {
                throw new InitException(e);
            }
        }
        return false;
    }

    private static boolean initLog4j2Slf4j() {
        // log4j-slf4j-impl-2.19.0.jar
        if (SystemPropertyUtils.getJavaClassPath().contains("log4j-slf4j-impl")) {
            ClassPool pool = ClassPool.getDefault();
            try {
                // FIXME 如果其他地方先加载了，这里会报错
                CtClass ctClass = pool.get(LOG4J2_SLF4J_IMPL);
                ctClass.addInterface(pool.get(Slf4jLogger.class.getName()));
                addMethods(ctClass, pool);
                ctClass.toClass(Thread.currentThread().getContextClassLoader(),
                        Thread.currentThread().getContextClassLoader().getClass().getProtectionDomain());
                ctClass.detach();
                return true;
            } catch (NotFoundException | CannotCompileException e) {
                throw new InitException(e);
            }
        }
        return false;
    }

    private static void _init() {
        if (!init) {
            synchronized (SLF4J_LOG4J_IMPL) {
                if (!init) {
                    if (initLog4j2Slf4j() || initSlf4jLog4j12()) {
                        init = true;
                    } else {
                        throw new UnsupportedException("unsupported");
                    }
                }
            }
        }
    }

    private static void addMethods(CtClass ctClass, ClassPool pool) throws NotFoundException, CannotCompileException {
        for (Method method : LoggerFunctionArgs.class.getDeclaredMethods()) {
            CtMethod ctMethod = Javassist.createMethod(method, ctClass, pool);
            ctMethod.setBody(String.format("if (is%sEnabled()) {this.%s($1, $2.get());}",
                    WordUtils.upperCaseFirst(ctMethod.getName()), ctMethod.getName()));
            ctClass.addMethod(ctMethod);
        }
    }
}
