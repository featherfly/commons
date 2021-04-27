
/*
 * All rights Reserved, Designed By zhongj
 * @Title: T.java
 * @Package cn.featherfly.common.mqtt
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-04-26 15:43:26
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.featherfly.common.lang.NumberUtils;
import cn.featherfly.common.lang.Randoms;

/**
 * T.
 *
 * @author zhongj
 */
public class T {
    static String s() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
                + String.format("%04d", Randoms.getInt(10000));
    }

    static String s2() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"))
                + String.format("%04d", Randoms.getInt(10000));
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println("202104151544487372415");
        System.out.println("2104151544487372415");

        System.out.println(s());
        System.out.println(s2());

        System.out.println(System.currentTimeMillis());
        long v = 2104151544487372415L;
        System.out.println(v);
        String s62 = NumberUtils.toString62Unit(v);
        System.out.println(s62);
        System.out.println(NumberUtils.parse62UnitToLong(s62));
        //        System.out.println(NumberUtils.toString64Unit(v));
        //        System.out.println(NumberUtils.toString93Unit(v));

        BigInteger b = new BigInteger("202104151544487372415");
        System.out.println(b);
        s62 = NumberUtils.toString62Unit(b);
        System.out.println(s62);
        System.out.println(NumberUtils.parse62Unit(s62));
        //        System.out.println(b.longValue());
        //        System.out.println(b.longValueExact());
        //        System.out.println(NumberUtils.toString64Unit(b));
        //        System.out.println(NumberUtils.toString93Unit(b));
    }
}
