
package cn.featherfly.common.validate;

/**
 * <p>
 * CountValidateCodeGeneratorTest
 * </p>
 * 
 * @author zhongj
 */
public class CountValidateCodeGeneratorTest {

    public static void main(String[] args) {
        CountValidateCodeGenerator g = new CountValidateCodeGenerator(1);
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());

        g = new CountValidateCodeGenerator(2);
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());
    }
}
