
package cn.featherfly.common.lang;

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
    }
}
