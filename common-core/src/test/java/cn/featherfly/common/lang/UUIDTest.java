
package cn.featherfly.common.lang;

import java.util.Date;

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
        for (byte b : DateUtils.formart(new Date(), "yyMMdd").getBytes()) {
            sb.append(Integer.toHexString(b) + " ");
        }

        System.out.println(sb.toString());
    }
}
