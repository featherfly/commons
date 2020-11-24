
package cn.featherfly.common.bytecode.asm;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import com.github.houbb.asm.tool.reflection.AsmMethods;

/**
 * <p>
 * AsmTest
 * </p>
 *
 * @author zhongj
 */
public class AsmTest {

    public void set(String name) {

    }

    public void set(String name, Integer age) {

    }

    public void set(String name, Double d, Integer age) {

    }

    private static void paramName(Method method) {
        for (Parameter param : method.getParameters()) {
            System.out.println(param.getName());
        }
    }

    public static void main(String[] args) throws Exception {
        Method method = AsmTest.class.getMethod("set", String.class);
        paramName(method);
        List<String> param = AsmMethods.getParamNamesByAsm(method);
        System.out.println(param);

        method = AsmTest.class.getMethod("set", String.class, Integer.class);
        paramName(method);
        param = AsmMethods.getParamNamesByAsm(method);
        System.out.println(param);

        method = AsmTest.class.getMethod("set", String.class, Double.class, Integer.class);
        paramName(method);
        param = AsmMethods.getParamNamesByAsm(method);
        System.out.println(param);
    }
}
