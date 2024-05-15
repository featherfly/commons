
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-09-26 15:19:26
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.log;

import cn.featherfly.common.function.ArraySupplier;
import cn.featherfly.common.lang.Lang;

/**
 * Utils.
 *
 * @author zhongj
 */
public class Utils {
    public static Logger logger() {
        String className = Lang.getInvoker().getClassName();
        return new Logger() {

            @Override
            public void warn(String msg, ArraySupplier<Object> supplier) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void trace(String msg, ArraySupplier<Object> supplier) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void info(String msg, ArraySupplier<Object> supplier) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void error(String msg, ArraySupplier<Object> supplier) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void debug(String msg, ArraySupplier<Object> supplier) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void warn(String msg, Throwable t) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void warn(String format, Object arg1, Object arg2) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void warn(String format, Object... arguments) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void warn(String format, Object arg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void warn(String msg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void trace(String msg, Throwable t) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void trace(String format, Object... arguments) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void trace(String format, Object arg1, Object arg2) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void trace(String format, Object arg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void trace(String msg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public boolean isWarnEnabled() {
                // YUFEI_TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isTraceEnabled() {
                // YUFEI_TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isInfoEnabled() {
                // YUFEI_TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isErrorEnabled() {
                // YUFEI_TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isDebugEnabled() {
                // YUFEI_TODO Auto-generated method stub
                return false;
            }

            @Override
            public void info(String msg, Throwable t) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void info(String format, Object... arguments) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void info(String format, Object arg1, Object arg2) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void info(String format, Object arg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void info(String msg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public String getName() {
                return className;
            }

            @Override
            public void error(String msg, Throwable t) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void error(String format, Object... arguments) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void error(String format, Object arg1, Object arg2) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void error(String format, Object arg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void error(String msg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void debug(String msg, Throwable t) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void debug(String format, Object... arguments) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void debug(String format, Object arg1, Object arg2) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void debug(String format, Object arg) {
                // YUFEI_TODO Auto-generated method stub

            }

            @Override
            public void debug(String msg) {
                // YUFEI_TODO Auto-generated method stub

            }
        };
    }
}
