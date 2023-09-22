
package cn.featherfly.common.lang;

import java.lang.invoke.SerializedLambda;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.function.serializable.SerializableFunction;
import cn.featherfly.common.lang.LambdaUtils2.SerializedLambdaInfo;
import cn.featherfly.common.lang.vo.User;

/**
 * @author zhongj
 */
public class LambdaUtilsTest2 {

    int times = 500000;

    @BeforeClass
    public void before() {
        Console.log("");
        Console.log("LambdaUtils performance test with loop times {}", times);
        Console.log("");
    }

    @Test
    public void test0() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {
            sl0(User::isLocked);
            sl0(User::getAge);
            sl0(User::getAgeInt);
            sl0(User::getConstruct);
            sl0(User::getList);
            sl0(User::getMap);
            sl0(User::getName);
            sl0(User::getOptional);
        }
        long time = timer.stop();
        System.out.println("test0: " + time);
    }

    @Test
    public void test1() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {
            sl1(User::isLocked);
            sl1(User::getAge);
            sl1(User::getAgeInt);
            sl1(User::getConstruct);
            sl1(User::getList);
            sl1(User::getMap);
            sl1(User::getName);
            sl1(User::getOptional);
        }
        long time = timer.stop();
        System.out.println("test1: " + time);
    }

    @Test
    public void test2() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {
            sl2(User::isLocked);
            sl2(User::getAge);
            sl2(User::getAgeInt);
            sl2(User::getConstruct);
            sl2(User::getList);
            sl2(User::getMap);
            sl2(User::getName);
            sl2(User::getOptional);
        }
        long time = timer.stop();
        System.out.println("test2: " + time);
    }

    @Test
    public void testInfo0() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {
            info0(User::isLocked);
            info0(User::getAge);
            info0(User::getAgeInt);
            info0(User::getConstruct);
            info0(User::getList);
            info0(User::getMap);
            info0(User::getName);
            info0(User::getOptional);
        }
        long time = timer.stop();
        System.out.println("testInfo0: " + time);
    }

    @Test
    public void testInfo1() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {
            info(User::isLocked);
            info(User::getAge);
            info(User::getAgeInt);
            info(User::getConstruct);
            info(User::getList);
            info(User::getMap);
            info(User::getName);
            info(User::getOptional);
        }
        long time = timer.stop();
        System.out.println("testInfo1: " + time);
    }

    @Test
    public void testInfo2() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {
            info2(User::isLocked);
            info2(User::getAge);
            info2(User::getAgeInt);
            info2(User::getConstruct);
            info2(User::getList);
            info2(User::getMap);
            info2(User::getName);
            info2(User::getOptional);
        }
        long time = timer.stop();
        System.out.println("testInfo2: " + time);
    }

    @Test
    public void testInfo3() {
        Timer timer = Timer.start();
        for (int i = 0; i < times; i++) {
            info3(User::isLocked);
            info3(User::getAge);
            info3(User::getAgeInt);
            info3(User::getConstruct);
            info3(User::getList);
            info3(User::getMap);
            info3(User::getName);
            info3(User::getOptional);
        }
        long time = timer.stop();
        System.out.println("testInfo3: " + time);
    }

    private <T, R> SerializedLambda sl0(SerializableFunction<T, R> f) {
        //        System.out.println(f);
        return LambdaUtils.getSerializedLambda(f);
    }

    private <T, R> SerializedLambda sl1(SerializableFunction<T, R> f) {
        //        System.out.println(f);
        return LambdaUtils2.getSerializedLambda(f);
    }

    private <T, R> SerializedLambda sl2(SerializableFunction<T, R> f) {
        //        System.out.println(f);
        return LambdaUtils2.getSerializedLambda2(f);
    }

    private <T, R> cn.featherfly.common.lang.LambdaUtils.SerializedLambdaInfo info0(SerializableFunction<T, R> f) {
        return LambdaUtils.getLambdaInfo(f);
    }

    private <T, R> SerializedLambdaInfo info(SerializableFunction<T, R> f) {
        return LambdaUtils2.getLambdaInfo(f);
    }

    private <T, R> SerializedLambdaInfo info2(SerializableFunction<T, R> f) {
        return LambdaUtils2.getLambdaInfo2(f);
    }

    private <T, R> SerializedLambdaInfo info3(SerializableFunction<T, R> f) {
        return LambdaUtils2.getLambdaInfo3(f);
    }
}
