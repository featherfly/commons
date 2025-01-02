
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-01-02 15:54:02
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import java.math.BigInteger;

import cn.featherfly.common.lang.number.Radix;

/**
 * TimeIdGeneratorTest.
 *
 * @author zhongj
 */
public class TimeIdGeneratorTest {
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println('\0' == (char) 0);
        //        System.out.println(Num.toString(9999, Radix.RADIX16));
        //        System.out.println(Num.toString(9999, Radix.RADIX32));
        //
        //        System.out.println(Num.toString(Integer.MAX_VALUE, Radix.RADIX16));
        //        System.out.println(Num.toString(Integer.toUnsignedLong(-1), Radix.RADIX16));
        //        System.out.println(Num.toString(Integer.MAX_VALUE, Radix.RADIX32));
        //        System.out.println(Num.toString(Integer.toUnsignedLong(-1), Radix.RADIX32));

        //        String pattern = "20249999999999";
        //        System.out.println("pattern.length " + pattern.length());
        //        BigInteger t1 = new BigInteger("20149999999999");
        //        System.out.println(t1.toString(16));
        //        System.out.println(t1.toString(16).length());
        //        System.out.println(t1.toString(32));
        //        System.out.println(t1.toString(32).length());
        //
        //        System.out.println();
        //        t1 = new BigInteger("40249999999999");
        //        System.out.println(t1.toString(16));
        //        System.out.println(t1.toString(16).length());
        //        System.out.println(t1.toString(32));
        //        System.out.println(t1.toString(32).length());
        //
        //        System.out.println();
        //        t1 = new BigInteger("99999999999999");
        //        System.out.println(t1.toString(16));
        //        System.out.println(t1.toString(16).length());
        //        System.out.println(t1.toString(32));
        //        System.out.println(t1.toString(32).length());

        //        if (true) {
        //            return;
        //        }

        TimeIdGenerator id = new TimeIdGenerator();
        System.out.println(id.generate());
        System.out.println(id.generate());
        System.out.println(id.generate());

        id = new TimeIdGenerator(Radix.RADIX16, false);
        System.out.println(id.generate());
        System.out.println(id.generate());
        String s = id.generate().toString();
        System.out.println(s);
        System.out.println("number: " + Num.parse(s, Radix.RADIX16, BigInteger.class));
        System.out.println("time: " + id.getTime(s));

        id = new TimeIdGenerator(Radix.RADIX32, false);
        System.out.println(id.generate());
        System.out.println(id.generate());
        s = id.generate().toString();
        System.out.println(s);
        System.out.println("number: " + Num.parse(s, Radix.RADIX32, BigInteger.class));
        System.out.println("time: " + id.getTime(s));

        //        id = new TimeIdGenerator(Radix.RADIX10, true);
        System.out.println("\nappendIp\n");
        id = TimeIdGenerator.builder().radix10().appendIpToEnd().build();
        System.out.println(id.generate());
        System.out.println(id.generate());
        s = id.generate().toString();
        System.out.println(s.length());
        System.out.println(s);
        System.out.println("number: " + Num.parse(s, Radix.RADIX32, BigInteger.class));
        System.out.println("time: " + id.getTime(s));
        System.out.println("ip: " + id.getIp(s));
        System.out.println("ipStr: " + id.getIpStr(s));

        //        id = new TimeIdGenerator(Radix.RADIX16, true);
        id = TimeIdGenerator.builder().radix16().appendIpToEnd().build();
        System.out.println(id.generate());
        System.out.println(id.generate());
        s = id.generate().toString();
        System.out.println(s.length());
        System.out.println(s);
        System.out.println("number: " + Num.parse(s, Radix.RADIX32, BigInteger.class));
        System.out.println("time: " + id.getTime(s));
        System.out.println("ip: " + id.getIp(s));
        System.out.println("ipStr: " + id.getIpStr(s));

        //        id = new TimeIdGenerator(Radix.RADIX32, true);
        id = TimeIdGenerator.builder().radix32().appendIpToEnd() //
            .build();
        System.out.println(id.generate());
        System.out.println(id.generate());
        s = id.generate().toString();
        System.out.println(s.length());
        System.out.println(s);
        System.out.println("number: " + Num.parse(s, Radix.RADIX32, BigInteger.class));
        System.out.println("time: " + id.getTime(s));
        System.out.println("ip: " + id.getIp(s));
        System.out.println("ipStr: " + id.getIpStr(s));
    }
}
