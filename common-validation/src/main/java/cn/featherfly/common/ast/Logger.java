
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Logger.java
 * @Package cn.featherfly.common.ast
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-30 18:29:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.ast;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * Logger.
 *
 * @author zhongj
 */
public class Logger {

    private String tag;

    /** The messager. */
    private Messager messager;

    /**
     * none 0. error 1. warning 2. notice 3. debug 4.
     */
    private int logLevel = 0;

    /**
     * Instantiates a new logger.
     *
     * @param tag      the tag
     * @param messager the messager
     * @param logLevel the log level
     */
    public Logger(String tag, Messager messager, int logLevel) {
        super();
        this.tag = tag;
        this.messager = messager;
        this.logLevel = logLevel;
    }

    public void debug(String message) {
        if (logLevel > 3) {
            messager.printMessage(Diagnostic.Kind.NOTE, tag + ": " + message);
        }
    }

    public void info(String message) {
        if (logLevel > 2) {
            messager.printMessage(Diagnostic.Kind.NOTE, tag + ": " + message);
        }
    }

    public void warning(String message) {
        if (logLevel > 1) {
            messager.printMessage(Diagnostic.Kind.WARNING, tag + ": " + message);
        }
    }

    public void error(String message) {
        if (logLevel > 0) {
            messager.printMessage(Diagnostic.Kind.ERROR, tag + ": " + message);
        }
    }
}
