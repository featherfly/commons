
package cn.featherfly.common.lang;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.testng.annotations.Test;

import cn.featherfly.common.function.serializable.SerializableBiConsumer;
import cn.featherfly.common.function.serializable.SerializableBiFunction;
import cn.featherfly.common.function.serializable.SerializableConsumer;
import cn.featherfly.common.function.serializable.SerializableFunction;
import cn.featherfly.common.function.serializable.SerializableSupplier;
import cn.featherfly.common.lang.vo.User;
import cn.featherfly.common.lang.vo.User2;

/**
 * The Class LambdaModeTest.
 *
 * @author zhongj
 */
public class LambdaModeTest {

    /**
     * Test.
     */
    @Test
    public void test() {
        String name = "yufei";
        Integer age = 18;
        User2 user = new User2();
        user.setAge(age);
        user.setName("yi");

        Function<User, Integer> f = User::getAge;
        f.apply(user); //  相当于 user.getAge();
        BiConsumer<User, Integer> b = User::setAge;
        b.accept(user, age); //  相当于 user.setAge(age);
        Supplier<Integer> s = user::getAge;
        s.get(); //  相当于 user.getAge();
        Consumer<Integer> c = user::setAge;
        c.accept(age); //  相当于 user.setAge(age);

        // String getDescp(String name)
        BiFunction<User, String, String> bf = User::getDescp;
        bf.apply(user, "descp"); //  相当于 user.getDescp("descp");
        Consumer<String> dc = user::getDescp;
        dc.accept("descp"); // 相当于 user.getDescp("descp")
        // String getDescp(int age)
        BiFunction<User, Integer, String> bf2 = User::getDescp2;
        System.out.println(bf2.apply(user, 1)); //  相当于 user.getDescp2(1);
        Function<Integer, String> df = user::getDescp2;
        System.out.println(df.apply(1));// 相当于 user.getDescp2(1)
        Consumer<Integer> dc2 = user::getDescp2;
        dc2.accept(2); // 相当于 user.getDescp2(1)
        System.out.println("age " + user.getAge());

        // void set(String name, Integer age)
        BiConsumer<String, Integer> bc = user::set;
        bc.accept(name, age); // 相当于 user::set(name, age)
        // User set2(String name, Integer age)
        BiFunction<String, Integer, User> bc2 = user::set2;
        bc2.apply(name, age); // 相当于 user::set2(name, age)

        System.out.println("User::getAge     Integer getAge()");
        fn(User::getAge);
        System.out.println("User::setAge     void setAge(Integer age)");
        bcon(User::setAge);

        System.out.println("user::getAge     Integer getAge()");
        sp(user::getAge);
        System.out.println("user::setAge     void setAge(Integer age)");
        con(user::setAge);

        System.out.println("User::setName     void setName(String name)");
        bcon(User::setName);
        System.out.println("user::setName    void setName(String name)");
        con(user::setName);

        System.out.println("User::set     void set(String name, Integer age)");
        //        bfn(User::set);
        System.out.println("user::set     void set(String name, Integer age)");
        bcon(user::set);

        //        System.out.println("User::getDescp     String getDescp(String name)");
        //        bfn(User::getDescp);
        //        System.out.println("user::getDescp     String getDescp(String name)");
        //        con(user::getDescp);
    }

    private <R> void sp(SerializableSupplier<R> f) {
        System.out.println("Supplier");
    }

    private <T, R> void fn(SerializableFunction<T, R> f) {
        System.out.println("Function");
    }

    private <T, U, R> void bfn(SerializableBiFunction<T, U, R> f) {
        System.out.println("BiFunction");
    }

    private <T> void con(SerializableConsumer<T> f) {
        System.out.println("Consumer");
    }

    private <T, U> void bcon(SerializableBiConsumer<T, U> f) {
        System.out.println("BiConsumer");
    }
}
