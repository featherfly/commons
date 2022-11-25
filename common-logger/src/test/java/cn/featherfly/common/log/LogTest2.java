
package cn.featherfly.common.log;

import org.testng.annotations.Test;

/**
 * LogTest.
 *
 * @author zhongj
 */
public class LogTest2 {

    protected final org.slf4j.Logger logge4r = org.slf4j.LoggerFactory.getLogger(this.getClass());

    protected final Slf4jLogger logger = LoggerFactory.getLogger(this.getClass());

    protected final Slf4jLogger logger2 = LoggerFactory.getLogger();

    protected final Logger logger3 = LoggerFactory.getLogger();

    @Test
    public void test() {
        logger.info("info");
        logger.info("info {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });
        logger2.info("info");
        logger2.info("info {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });

        logger3.info("info");
        logger3.info("info {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });

        logger.debug("debug");
        logger.debug("debug {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });
        logger2.debug("debug");
        logger2.debug("debug {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });

        logger.error("error");
        logger.error("error {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });
        logger2.error("error");
        logger2.error("error {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });

        //        Integer a = null;
        //        try {
        //            a.intValue();
        //        } catch (Exception e) {
        //            logger.error(e);
        //        }
    }

    @Test
    public void test2() {
        LoggerUtils.logger().debug("test2");
        LoggerUtils.logger().error("test2 {}, {}", () -> {
            return new Object[] { "argu1", "argu2" };
        });
    }

}
