
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
    }
}
