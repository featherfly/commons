
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.function.SerializableFunction;
import cn.featherfly.common.lang.vo.User;

/**
 * <p>
 * LambdaUtilsTest
 * </p>
 * <p>
 * 2019-09-17
 * </p>
 *
 * @author zhongj
 */
public class LambdaUtilsTest {

    @Test
    public void test() {
        String name = propertyName(User::getAge);
        String method = methodName(User::getAge);

        assertEquals(name, "age");
        assertEquals(method, "getAge");
    }

    @Test
    public void test2() {
        String name = propertyName(User::getName);
        String method = methodName(User::getName);

        assertEquals(name, "name");
        assertEquals(method, "getName");
    }

    @Test
    public void test3() {
        String name = propertyName(User::isLocked);
        String method = methodName(User::isLocked);

        assertEquals(name, "locked");
        assertEquals(method, "isLocked");
    }

    private <T, R> String propertyName(SerializableFunction<T, R> f) {
        return LambdaUtils.getLambdaPropertyName(f);
    }

    private <T, R> String methodName(SerializableFunction<T, R> f) {
        return LambdaUtils.getLambdaMethodName(f);
    }
}
