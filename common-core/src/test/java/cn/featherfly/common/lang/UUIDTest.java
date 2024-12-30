
package cn.featherfly.common.lang;

import java.math.BigInteger;
import java.util.Date;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.number.Radix;

/**
 * UUIDTest.
 *
 * @author zhongj
 */
public class UUIDTest {

    @Test
    public void uuid() {
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
        BigInteger bi = Num.parse(uuid, Radix.RADIX16);
        System.out.println("int: " + bi);
        System.out.println("62: " + Num.toString62Unit(bi));
        System.out.println("64: " + Num.toString64Unit(bi));
        System.out.println("93: " + Num.toString93Unit(bi));
        System.out.println("128: " + Num.toString128Unit(bi));

        System.out.println();
        uuid = "47E1ABCD371743692000410F";
        System.out.println("uuid: " + uuid + " length: " + uuid.length());
        bi = Num.parse(uuid, Radix.RADIX16);
        System.out.println("int: " + bi);
        System.out.println("62: " + Num.toString62Unit(bi));
        System.out.println("64: " + Num.toString64Unit(bi));
        System.out.println("93: " + Num.toString93Unit(bi));
        System.out.println("128: " + Num.toString128Unit(bi));

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

        String s = Num.toString(256, Radix.RADIX16);
        System.out.println(s);
        System.out.println(Num.parse(s, Radix.RADIX16));

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

    @Test
    public void uuidHex() {
        for (int i = 0; i < 5; i++) {
            System.out.println(UUIDHexGenerator.generateUUID());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(UUIDHexGenerator.generateUUID32());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(UUIDHexGenerator.generateUUID36());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(UUIDHexGenerator.generateUUID22Letters());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(UUIDHexGenerator.generateUUID22Chars());
        }
    }

    public static void main(String[] args) {

    }
}
