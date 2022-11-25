
package cn.featherfly.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.LogUtils;

/**
 * LogTest.
 *
 * @author zhongj
 */
public class LogTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {
        logger.info("test");
        Integer a = null;
        try {
            a.intValue();
        } catch (Exception e) {
            LogUtils.error(e, logger);
        }
    }

}
