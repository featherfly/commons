/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-05-23 18:09:23
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common;

import java.util.concurrent.TimeUnit;

/**
 * Test.
 *
 * @author zhongj
 */
public class Progress {

    public static void main(String[] args) throws InterruptedException {
        System.out.print(ColorEnum.GREEN.getValue());
        for (int i = 0; i <= 100; i++) {
            System.out.print("\r" + i + "%");
            TimeUnit.MILLISECONDS.sleep(100L);
        }
    }

    /**
     * 颜色枚举
     */
    enum ColorEnum {

        /**
         * 白色
         */
        WHITE("\33[0m"),

        /**
         * 红色
         */
        RED("\33[1m\33[31m"),

        /**
         * 绿色
         */
        GREEN("\33[1m\33[32m"),

        /**
         * 黄色
         */
        YELLOW("\33[1m\33[33m"),

        /**
         * 蓝色
         */
        BLUE("\33[1m\33[34m"),

        /**
         * 粉色
         */
        PINK("\33[1m\33[35m"),

        /**
         * 青色
         */
        CYAN("\33[1m\33[36m");

        /**
         * 颜色值
         */
        private String value;

        ColorEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
