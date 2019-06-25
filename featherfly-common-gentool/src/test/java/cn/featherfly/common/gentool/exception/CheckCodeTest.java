
package cn.featherfly.common.gentool.exception;

import java.lang.reflect.Field;

import cn.featherfly.common.gentool.exception.gencode.GenExceptionCode;
import cn.featherfly.common.gentool.exception.util.ExceptionCodeCheck;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.ClassUtils;

/**
 * <p>
 * CheckCodeTest
 * </p>
 * 
 * @author zhongj
 */
public class CheckCodeTest {

    public static void main(String[] args) {
        for (Field f : GenExceptionCode.class.getDeclaredFields()) {
            System.out.println(
                    f + " " + f.isEnumConstant() + " " + f.getType().isEnum());
        }

        System.out.println(
                ArrayUtils.toList(GenExceptionCode.class.getEnumConstants()));
        System.out.println(GenExceptionCode.class.getEnclosingClass());

        System.out.println(
                ArrayUtils.toList(GenExceptionCode.class.getClasses()));
        System.out.println(GenExceptionCode.class.getComponentType());

        Class c = ClassUtils.forName(GenExceptionCode.class.getName() + "$"
                + GenExceptionCode.class.getSimpleName() + "s");
        System.out.println(c);
        System.out.println(ArrayUtils.toList(c.getEnumConstants()));
        System.out.println(c.getEnclosingClass());
        System.out.println(ArrayUtils.toList(c.getClasses()));
        System.out.println(c.getComponentType());

        ExceptionCodeCheck.checkCode(GenExceptionCode.class);
        ExceptionCodeCheck.checkCode(GenExceptionCode.class);
    }
}
