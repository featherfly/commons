
package cn.featherfly.common.bytecode.asm;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * <p>
 * AsmTest
 * </p>
 *
 * @author zhongj
 */
public class AsmUtilsTest {

    public void set(String name) {

    }

    public void set(String name, Integer age) {

    }

    public void set(String name, Double d, Integer age) {

    }

    public static void set(String name, Double d, Integer age, Long l) {

    }

    private static void paramName(Method method) {
        for (Parameter param : method.getParameters()) {
            System.out.print(param.getName() + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        for (Method method : AsmTest.class.getDeclaredMethods()) {
            System.out.println("method name: " + method.getName());
            paramName(method);
            String[] param = AsmUtils.getMethodParamNames(method);
            System.err.println(Arrays.toString(param));
            System.out.println();
        }
    }
}
