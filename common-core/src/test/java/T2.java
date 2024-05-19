
/*
 * All rights Reserved, Designed By zhongj
 * @Title: T.java
 * @Package cn.featherfly.common.mqtt
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-04-26 15:43:26
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */

import java.util.List;
import java.util.function.Function;

import cn.featherfly.common.function.ToArrayFunction;
import cn.featherfly.common.function.ToListFunction;

/**
 * T.
 *
 * @author zhongj
 */
public class T2 {

    public int[] getInts() {
        return null;
    }

    public Integer[] getIntegers() {
        return null;
    }

    public List<Integer> getIntegerList() {
        return null;
    }

    public String[] getStrings() {
        return null;
    }

    static void array(ToListFunction<T2, Integer> property) {

    }

    static void array(Function<T2, int[]> property) {

    }

    static <E> void array(ToArrayFunction<T2, E> property) {

    }

    static void show(Object obj) {
        System.out.println(obj.getClass().getName());
        System.out.println(obj.getClass().getSuperclass().getName());
        System.out.println();
    }

    public static void main(String[] args) {
        show(new int[0]);
        show(new Integer[0]);
        show(new String[0]);
        array(T2::getStrings);
        array(T2::getIntegers);
        array(T2::getInts);
        array(T2::getIntegerList);
    }
}
