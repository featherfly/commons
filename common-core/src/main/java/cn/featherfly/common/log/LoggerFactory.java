
package cn.featherfly.common.log;

import java.lang.reflect.Method;

import cn.featherfly.common.bytecode.JavassistUtils;
import cn.featherfly.common.exception.InitException;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.LangUtils;
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

    public static Logger getLogger() {
        String className = LangUtils.getInvoker().getClassName();
        return getLogger(className);
    }

    public static Logger getLogger(String name) {
        init();
        return (Logger) org.slf4j.LoggerFactory.getLogger(name);
    }

    public static Logger getLogger(Class<?> clazz) {
        init();
        return (Logger) org.slf4j.LoggerFactory.getLogger(clazz);
    }

    private static void init() {
        if (!init) {
            synchronized (LOG4J) {
                if (!init) {
                    if (SystemPropertyUtils.getJavaClassPath().contains("slf4j-log4j12")) {
                        ClassPool pool = ClassPool.getDefault();
                        try {
                            CtClass ctClass = pool.get(LOG4J);
                            ctClass.addInterface(pool.get(Logger.class.getName()));
                            addMethods(ctClass, pool);
                            ctClass.toClass();
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
        for (Method method : Logger.class.getDeclaredMethods()) {
            CtMethod ctMethod = JavassistUtils.createMethod(method, ctClass, pool);
            ctMethod.setBody(String.format("if (is%sEnabled()) {this.%s($1, $2.get());}",
                    WordUtils.upperCaseFirst(ctMethod.getName()), ctMethod.getName()));
            ctClass.addMethod(ctMethod);
        }
    }
}
