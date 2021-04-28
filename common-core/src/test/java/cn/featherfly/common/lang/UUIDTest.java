
package cn.featherfly.common.lang;

import java.math.BigInteger;
import java.util.Date;

import cn.featherfly.common.lang.number.Radix;

/**
 * <p>
 * UUIDTest
 * </p>
 *
 * @author zhongj
 */
public class UUIDTest {

    public static void main(String[] args) {
        System.out.println(UUIDGenerator.generateUUID());
        System.out.println(UUIDGenerator.generateUUID32());
        System.out.println(UUIDGenerator.generateUUID36());
        System.out.println(UUIDGenerator.generateUUID22Letters());
        System.out.println(UUIDGenerator.generateUUID22Chars());

        char c1 = 0;
        char c2 = '\n';
        System.out.println(c1);
        System.out.print("---");
        System.out.print(c2);

        System.out.println(c2 == 10);

        System.out.println();
        System.out.println();

        StringBuilder sb = new StringBuilder();
        for (byte b : UUIDGenerator.generateUUID().getBytes()) {
            sb.append(Integer.toHexString(b) + " ");
        }
        for (byte b : Dates.format(new Date(), "yyMMdd").getBytes()) {
            sb.append(Integer.toHexString(b) + " ");
        }

        System.out.println(sb.toString());

        String uuid = UUIDGenerator.generateUUID();
        System.out.println("uuid: " + uuid + " length: " + uuid.length());
        BigInteger bi = NumberUtils.parse(uuid, Radix.RADIX16);
        System.out.println("int: " + bi);
        System.out.println("62: " + NumberUtils.toString62Unit(bi));
        System.out.println("64: " + NumberUtils.toString64Unit(bi));
        System.out.println("93: " + NumberUtils.toString93Unit(bi));
        System.out.println("128: " + NumberUtils.toString128Unit(bi));

        System.out.println();
        uuid = "47E1ABCD371743692000410F";
        System.out.println("uuid: " + uuid + " length: " + uuid.length());
        bi = NumberUtils.parse(uuid, Radix.RADIX16);
        System.out.println("int: " + bi);
        System.out.println("62: " + NumberUtils.toString62Unit(bi));
        System.out.println("64: " + NumberUtils.toString64Unit(bi));
        System.out.println("93: " + NumberUtils.toString93Unit(bi));
        System.out.println("128: " + NumberUtils.toString128Unit(bi));

        int i = 0;
        System.out.println();
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(255));
        System.out.println();
        System.out.println(Integer.toHexString(0));
        System.out.println(Integer.toHexString(1));
        System.out.println(Integer.toHexString(7));
        System.out.println(Integer.toHexString(255));
        System.out.println(Integer.toHexString(256));

        String s = NumberUtils.toString(256, Radix.RADIX16);
        System.out.println(s);
        System.out.println(NumberUtils.parse(s, Radix.RADIX16));

        System.out.println();
        i = Integer.parseInt("555", 8);
        System.out.println(i);
        System.out.println(Integer.toOctalString(i));
        i = Integer.parseInt("555", 8);
        System.out.println(i);
        System.out.println(Integer.toOctalString(i));
        System.out.println(Integer.toHexString(i));
        i = Integer.parseInt("050505", 16);
        System.out.println(i);
        System.out.println(Integer.toOctalString(i));
        System.out.println(Integer.toHexString(i));
    }
}
