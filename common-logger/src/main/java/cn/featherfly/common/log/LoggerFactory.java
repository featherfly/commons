
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
 * <p>
 * LoggerFactory
 * </p>
 *
 * @author zhongj
 */
public class LoggerFactory {

    private static final String LOG4J = "org.slf4j.impl.Log4jLoggerAdapter";

    private static boolean init;

    public static Slf4jLogger getLogger() {
        String className = Lang.getInvoker().getClassName();
        return getLogger(className);
    }

    public static Slf4jLogger getLogger(String name) {
        init();
        return (Slf4jLogger) org.slf4j.LoggerFactory.getLogger(name);
    }

    public static Slf4jLogger getLogger(Class<?> clazz) {
        init();
        return (Slf4jLogger) org.slf4j.LoggerFactory.getLogger(clazz);
    }

    private static void init() {
        if (!init) {
            synchronized (LOG4J) {
                if (!init) {
                    if (SystemPropertyUtils.getJavaClassPath().contains("slf4j-log4j12")) {
                        ClassPool pool = ClassPool.getDefault();
                        try {
                            // FIXME 如果其他地方先加载了，这里会报错
                            CtClass ctClass = pool.get(LOG4J);
                            ctClass.addInterface(pool.get(Slf4jLogger.class.getName()));
                            addMethods(ctClass, pool);
                            ctClass.toClass(Thread.currentThread().getContextClassLoader(),
                                    Thread.currentThread().getContextClassLoader().getClass().getProtectionDomain());
                            ctClass.detach();
                            init = true;
                        } catch (NotFoundException | CannotCompileException e) {
                            throw new InitException(e);
                        }
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
