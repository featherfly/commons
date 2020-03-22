
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Consumer;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.LambdaUtils.SerializedLambdaInfo;
import cn.featherfly.common.lang.function.SerializableFunction;
import cn.featherfly.common.lang.function.SerializableSupplier;
import cn.featherfly.common.lang.vo.User;
import cn.featherfly.common.lang.vo.User2;

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

    @Test
    public void test4() {
        SerializedLambdaInfo info = info(User::isLocked);

        User user = new User();
        assertEquals(User.class.getName(), info.getMethodDeclaredClassName());
        assertEquals(User.class.getName(), info.getMethodInstanceClassName());
        assertEquals(false, ClassUtils.invokeMethod(user, info.getMethod(), new Object[0]));
        user.setLocked(true);
        assertEquals(true, ClassUtils.invokeMethod(user, info.getMethod(), new Object[0]));

        info = info(User2::isLocked);
        User user2 = new User();
        assertEquals(User.class.getName(), info.getMethodDeclaredClassName());
        assertEquals(User2.class.getName(), info.getMethodInstanceClassName());
        assertEquals(false, ClassUtils.invokeMethod(user2, info.getMethod(), new Object[0]));
        user2.setLocked(true);
        assertEquals(true, ClassUtils.invokeMethod(user2, info.getMethod(), new Object[0]));
    }

    @Test
    public void test5() {
        assertEquals(propertyName(User::getAge), "age");
        assertEquals(propertyNumberName(User::getAge), "age");
        //        propertyNumberName(User::getName);
    }

    @Test
    public void test6() {
        User user = new User();
        user.setAge(18);

        String p = propertyName(user::getAge);
        System.out.println(p);
        assertEquals(p, "age");
        p = propertyNumberName(user::getAge);
        System.out.println(p);
        assertEquals(p, "age");
        p = propertyStrName(user::getName);
        System.out.println(p);
        assertEquals(p, "name");

        SerializedLambdaInfo info = info(User::getAge);
        System.out.println(info);
        System.out.println(info.getSerializedLambda().getCapturedArgCount());
        SerializedLambdaInfo info2 = info(user::getAge);
        System.out.println(info2);
        System.out.println(info2.getSerializedLambda().getCapturedArgCount());
        System.out.println(info2.getSerializedLambda().getCapturedArg(0));
        //        propertyNumberName(User::getName);
    }

    @Test
    public void test7() {
        User2 user = new User2();
        user.setAge(18);

        SerializedLambdaInfo info = info(User2::getAge);
        System.out.println(info);
        System.out.println(info.getSerializedLambda().getCapturedArgCount());
        SerializedLambdaInfo info2 = info(user::getAge);
        System.out.println(info2);
        System.out.println(info2.getSerializedLambda().getCapturedArgCount());
        System.out.println(info2.getSerializedLambda().getCapturedArg(0));
        //        propertyNumberName(User::getName);
    }

    @Test
    public void test8() {
        User2 user = new User2();
        user.setAge(18);
        SerializedLambda sl = null;
        sl = get(user::getAge);
        assertEquals(LambdaUtils.getLambdaMethodName(sl), "getAge");
        assertEquals(LambdaUtils.getLambdaPropertyName(sl), "age");

        sl = get(User::getAge);
        assertEquals(LambdaUtils.getLambdaMethodName(sl), "getAge");
        assertEquals(LambdaUtils.getLambdaPropertyName(sl), "age");

    }

    public static void main(String[] args) {
        SerializedLambda s;
        s = get(User::isLocked);
        p(s);

        s = get(User2::isLocked);
        p(s);

        //        User user = null;
        //        t(user::setLocked);
        //        t(System.out::println);
        //        Arrays.stream(new String[0]).forEach(System.out::println);
        //        eq(User::isLocked, User::isLocked);
    }

    public static void p(SerializedLambda s) {
        System.out.println(s.getImplClass());
        System.out.println(s.getImplMethodKind());
        System.out.println(s.getImplMethodName());
        System.out.println(s.getImplMethodSignature());
        System.out.println(s.getInstantiatedMethodType());
        System.out.println(s.getCapturingClass());
        System.out.println(s.getCapturedArgCount());

        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(s);
        System.out.println(info.getMethodName());
        System.out.println(info.getMethodDeclaredClassName());
        System.out.println(info.getMethodInstanceClassName());

        System.out.println(info.getMethod());

        User2 user = new User2();
        System.out.println(ClassUtils.invokeMethod(user, info.getMethod(), new Object[0]));
        user.setLocked(true);
        System.out.println(ClassUtils.invokeMethod(user, info.getMethod(), new Object[0]));

        Method method = LambdaUtils.getLambdaMethod(s);
        System.out.println(method);
        System.out.println(ClassUtils.invokeMethod(user, method, new Object[0]));
        user.setLocked(false);
        System.out.println(ClassUtils.invokeMethod(user, method, new Object[0]));

        System.err.println(s);
    }

    private static <T, R> void eq(SerializableFunction<T, R> f, SerializableFunction<T, R> f2) {
        System.err.println(f == f2);
        System.err.println(f.equals(f2));
    }

    private <T, R extends Number> String propertyNumberName(SerializableFunction<T, R> f) {
        return LambdaUtils.getLambdaPropertyName(f);
    }

    private <T, R> String propertyName(SerializableFunction<T, R> f) {
        return LambdaUtils.getLambdaPropertyName(f);
    }

    private <T, R> SerializedLambdaInfo info(SerializableFunction<T, R> f) {
        return LambdaUtils.getLambdaInfo(f);
    }

    private <T, R> String methodName(SerializableFunction<T, R> f) {
        return LambdaUtils.getLambdaMethodName(f);
    }

    public static <T, R> SerializedLambda get(SerializableFunction<T, R> f) {
        return LambdaUtils.getSerializedLambda(f);
    }

    private <T extends Number> String propertyNumberName(SerializableSupplier<T> f) {
        return LambdaUtils.getLambdaPropertyName(f);
    }

    private <T> String propertyName(SerializableSupplier<T> f) {
        return LambdaUtils.getLambdaPropertyName(f);
    }

    private <T> String propertyStrName(SerializableSupplier<String> f) {
        return LambdaUtils.getLambdaPropertyName(f);
    }

    private <T> SerializedLambdaInfo info(SerializableSupplier<T> f) {
        return LambdaUtils.getLambdaInfo(f);
    }

    private <T> String methodName(SerializableSupplier<T> f) {
        return LambdaUtils.getLambdaMethodName(f);
    }

    public static <T> SerializedLambda get(SerializableSupplier<T> f) {
        return LambdaUtils.getSerializedLambda(f);
    }

    public static <T> void t(Consumer<T> c) {

    }

}
