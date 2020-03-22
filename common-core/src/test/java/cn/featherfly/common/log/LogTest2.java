
package cn.featherfly.common.log;

import org.testng.annotations.Test;

/**
 * <p>
 * LogTest
 * </p>
 * <p>
 * 2019-08-29
 * </p>
 *
 * @author zhongj
 */
public class LogTest2 {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final Logger logger2 = LoggerFactory.getLogger();

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
