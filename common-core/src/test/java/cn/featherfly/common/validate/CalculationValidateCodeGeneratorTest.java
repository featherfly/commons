
package cn.featherfly.common.validate;

import cn.featherfly.common.operator.CalculationOperator;

/**
 * <p>
 * CountValidateCodeGeneratorTest
 * </p>
 *
 * @author zhongj
 */
public class CalculationValidateCodeGeneratorTest {

    public static void main(String[] args) {
        CalculationValidateCodeGenerator g = new CalculationValidateCodeGenerator(1);
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());

        g = new CalculationValidateCodeGenerator(2);
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());

        System.out.println();

        g = new CalculationValidateCodeGenerator(1, CalculationOperator.PLUS, CalculationOperator.MULTIPLY,
                CalculationOperator.DIVIDE);
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());

        g = new CalculationValidateCodeGenerator(2, CalculationOperator.PLUS, CalculationOperator.MULTIPLY,
                CalculationOperator.DIVIDE);
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());
        System.out.println(g.generate());

        System.out.println();

        System.out.println("÷".length());
        System.out.println((int) "÷".toCharArray()[0]);
        for (int i = 0; i < 256; i++) {
            System.out.println(String.format("%d     %c", i, i));
        }
    }
}
