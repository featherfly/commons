
package cn.featherfly.common.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import cn.featherfly.common.asm.Asm;

/**
 * <p>
 * AsmTest
 * </p>
 *
 * @author zhongj
 */
public class AsmTest {

    public AsmTest(String name) {

    }

    public AsmTest(String name, Integer age) {

    }

    public AsmTest(String name, Double d, Integer age) {

    }

    public AsmTest(String name, Double d, Integer age, Long l) {

    }

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

    private static void paramName(Constructor<?> constructor) {
        for (Parameter param : constructor.getParameters()) {
            System.out.print(param.getName() + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Class<AsmTest> type = AsmTest.class;
        for (Method method : type.getDeclaredMethods()) {
            System.out.println("method name: " + method.getName());
            paramName(method);
            String[] param = Asm.getParamNames(method);
            System.out.println("method param names: " + Arrays.toString(param));
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
        for (Constructor<?> constructor : type.getDeclaredConstructors()) {
            System.out.println("constructor name: " + constructor.getName());
            paramName(constructor);
            String[] param = Asm.getParamNames(constructor);
            System.out.println("constructor param names: " + Arrays.toString(param));
            System.out.println();
        }
    }
}
