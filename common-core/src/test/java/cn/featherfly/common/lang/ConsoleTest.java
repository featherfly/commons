
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-05-28 15:30:28
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import java.util.Date;

/**
 * ConsoleTest.
 *
 * @author zhongj
 */
public class ConsoleTest {
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Console.log("test console");
        Console.error("test console");
        String time = Dates.formatTime(new Date());
        Console.log("test console at {}", time);
        Console.error("test console at {}", time);

        Thread.sleep(1000);
        String name = "yufei";
        int age = 18;
        time = Dates.formatTime(new Date());
        Object[] params = new Object[] { time, name, age };

        Console.log("test console at {}, name = {}, age = {}", params);
        Console.error("test console at {}, name = {}, age = {}", params);
    }
}
