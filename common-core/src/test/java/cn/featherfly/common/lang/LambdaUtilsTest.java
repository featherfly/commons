
package cn.featherfly.common.lang;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.LambdaUtils.SerializableConsumerLambdaInfo;
import cn.featherfly.common.lang.LambdaUtils.SerializableSupplierLambdaInfo;
import cn.featherfly.common.lang.LambdaUtils.SerializedLambdaInfo;
import cn.featherfly.common.lang.function.ReturnNumberFunction;
import cn.featherfly.common.lang.function.SerializableBiConsumer;
import cn.featherfly.common.lang.function.SerializableBiFunction;
import cn.featherfly.common.lang.function.SerializableConsumer;
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
    public void testPropertyName() {
        String name = propertyName(User::getAge);
        String method = methodName(User::getAge);

        assertEquals(name, "age");
        assertEquals(method, "getAge");

        name = propertyName(User::getName);
        method = methodName(User::getName);

        assertEquals(name, "name");
        assertEquals(method, "getName");

        name = propertyName(User::isLocked);
        method = methodName(User::isLocked);

        assertEquals(name, "locked");
        assertEquals(method, "isLocked");

    }

    @Test
    public void testPropertyNumberName() {
        assertEquals(propertyName(User::getAge), "age");
        assertEquals(propertyNumberName(User::getAge), "age");
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

    @Test
    public void test9() {
        User2 user = new User2();
        user.setAge(18);
        SerializedLambdaInfo info = consumerInfo(user::setAge);
        assertEquals(info.getMethodInstanceClassName(), user.getClass().getName());
    }

    @Test
    public void testG() {
        User user = new User();
        //        g1(User::getAge);
        //        g2(User::getAge);
        //        g3(User::getAge);
        g4(User::getAge);
    }

    @Test
    public void test10() {
        User2 user = new User2();
        user.setAge(18);
        assertUser(user);
    }

    @Test
    public void testSupplier() {
        User2 user = new User2();
        user.setAge(18);

        SerializableSupplierLambdaInfo<Integer> info = LambdaUtils.getSerializableSupplierLambdaInfo(user::getAge);
        System.out.println(info.getValue());

        assertEquals(info.getValue(), user.getAge());

        assertTrue(user == info.getInstance());

        user.setName(null);
        SerializableSupplierLambdaInfo<String> info2 = LambdaUtils.getSerializableSupplierLambdaInfo(user::getName);
        System.out.println(info2.getValue());

        assertTrue(user == info2.getInstance());
    }

    @Test
    public void testConsumer() {
        Integer age = 18;
        User2 user = new User2();
        user.setAge(age);

        SerializedLambdaInfo info = info(user::setName);

        System.out.println(info);

        System.out.println(info.getMethodDeclaredClassName());
        System.out.println(info.getMethodInstanceClassName());

        assertEquals(info.getMethodDeclaredClassName(), user.getClass().getSuperclass().getName());
        assertEquals(info.getMethodInstanceClassName(), user.getClass().getName());

        SerializableConsumerLambdaInfo<Integer> consumerLambdaInfo = LambdaUtils
                .getSerializableConsumerLambdaInfo(user::setAge);

        assertTrue(consumerLambdaInfo.getInstance() == user);

        assertEquals(user.getAge(), age);
        age = 16;
        consumerLambdaInfo.accept(age);
        assertEquals(user.getAge(), age);

    }

    @Test
    public void testBiConsumer() {
        Integer age = 18;
        User2 user = new User2();
        user.setAge(age);

        SerializedLambdaInfo info = info(User::setName);

        System.out.println(info);

        System.out.println(info.getMethodDeclaredClassName());
        System.out.println(info.getMethodInstanceClassName());

        Method setName = ClassUtils.getMethod(user.getClass(), "setName", String.class);

        assertEquals(info.getMethodDeclaredClassName(), setName.getDeclaringClass().getName());
        assertEquals(info.getMethodInstanceClassName(), setName.getDeclaringClass().getName());
    }

    @Test
    public void testBiFunction() {
        Integer age = 18;
        User2 user = new User2();
        user.setAge(age);

        SerializedLambdaInfo info = info(User::getDescp);

        System.out.println(info);

        System.out.println(info.getMethodDeclaredClassName());
        System.out.println(info.getMethodInstanceClassName());

        Method getDescp = ClassUtils.getMethod(user.getClass(), "getDescp", String.class);

        assertEquals(info.getMethodDeclaredClassName(), getDescp.getDeclaringClass().getName());
        assertEquals(info.getMethodInstanceClassName(), getDescp.getDeclaringClass().getName());
    }

    @Test
    public void testPrimitive() {
        Integer age = 18;
        User2 user = new User2();
        user.setAge(age);

        SerializedLambdaInfo info = info(User::setAgeInt);

        System.out.println(info);

        System.out.println(info.getMethodDeclaredClassName());
        System.out.println(info.getMethodInstanceClassName());

        info = info(user::getAgeInt);

        System.out.println(info);

        System.out.println(info.getMethodDeclaredClassName());
        System.out.println(info.getMethodInstanceClassName());

        SerializableSupplierLambdaInfo<Integer> supplier = LambdaUtils
                .getSerializableSupplierLambdaInfo(user::getAgeInt);

        System.out.println(supplier.get());

        info = info(user::setAgeInt);

        System.out.println(info);

        System.out.println(info.getMethodDeclaredClassName());
        System.out.println(info.getMethodInstanceClassName());
    }

    @Test
    public void testInfo() {
        Integer age = 18;
        User2 user = new User2();
        user.setAge(age);

        System.out.println("User::getAge");
        SerializedLambdaInfo info = info(User::getAge);
        System.out.println("user::getAge");
        SerializedLambdaInfo info2 = info(user::getAge);
        System.out.println("user::setName");
        SerializedLambdaInfo info3 = info(user::setName);
        System.out.println("User::setName");
        SerializedLambdaInfo info4 = info(User::setName);
        System.out.println("User::getDescp");
        SerializedLambdaInfo info5 = info(User::getDescp);

        System.out.println(info.getMethod());
        System.out.println(info.getPropertyType());
        System.out.println(info2.getMethod());
        System.out.println(info2.getPropertyType());
        System.out.println(info3.getMethod());
        System.out.println(info3.getPropertyType());
        System.out.println(info4.getMethod());
        System.out.println(info4.getPropertyType());
        System.out.println(info5.getMethod());
        System.out.println(info5.getPropertyType());
    }

    public static void main(String[] args) {
        //        SerializedLambda s;
        //        s = get(User::isLocked);
        //        p(s);
        //
        //        s = get(User2::isLocked);
        //        p(s);

        User user = new User();
        Supplier<String> supplier = user::getName;
        Function<User, String> function = User::getName;
        //        BiConsumer<User, String> set = user::set;
        Consumer<String> consumer = user::setName;
        consumer.accept("yufei");
        System.out.println("user.name " + user.getName());
        System.out.println("user.name " + function.apply(user));
        System.out.println("user.name " + supplier.get());

        BiConsumer<User, String> con = User::setName;
        con.accept(user, "featherfly");
        System.out.println("user.name " + user.getName());
        System.out.println("user.name " + function.apply(user));
        System.out.println("user.name " + supplier.get());

        BiFunction<User, String, String> bifunction = User::getDescp;
        System.out.println("user.descp " + bifunction.apply(user, "yi"));

        System.out.println(supplier.getClass().isAssignableFrom(Supplier.class));
        System.out.println(function.getClass().isAssignableFrom(Function.class));
        System.out.println(Supplier.class.isAssignableFrom(supplier.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(supplier.getClass()));
        System.out.println(Supplier.class.isAssignableFrom(function.getClass()));
        System.out.println(Function.class.isAssignableFrom(function.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(function.getClass()));
        System.out.println(Function.class.isAssignableFrom(supplier.getClass()));

        LambdaUtils.getSerializedLambda((Serializable) function);
        //        t(user::setLocked);
        //        t(System.out::println);
        //        Arrays.stream(new String[0]).forEach(System.out::println);
        //        eq(User::isLocked, User::isLocked);
    }

    void assertUser(User user) {
        AssertIllegalArgument.isNotNull(user::getAge);
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

    private <T, U, R> SerializedLambdaInfo info(SerializableBiFunction<T, U, R> f) {
        return LambdaUtils.getLambdaInfo(f);
    }

    private <T> SerializedLambdaInfo info(SerializableConsumer<T> f) {
        return LambdaUtils.getLambdaInfo(f);
    }

    private <T, U> SerializedLambdaInfo info(SerializableBiConsumer<T, U> f) {
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

    public static <T> SerializedLambdaInfo consumerInfo(SerializableConsumer<T> f) {
        return LambdaUtils.getLambdaInfo(f);
    }

    <T, N extends Number> void g1(ReturnNumberFunction<T, N> f) {

    }

    <T, N extends Number> void g4(SerializableFunction<T, N> f) {
        System.out.println(f.getClass().getName());
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(f);
        System.out.println(info);
    }

    public <T> void t(Consumer<T> c) {
        g1(User::getAge);
        //        g3(User::getAge);
        g4(User::getAge);
    }

}
