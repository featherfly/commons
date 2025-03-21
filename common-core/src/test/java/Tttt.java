
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-03-22 03:33:22
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Console;
import cn.featherfly.common.lang.TypeNames;

/**
 * Tttt.
 *
 * @author zhongj
 */
public class Tttt {

    @Test
    void test() {
        Console.log("{} : {}", Integer.TYPE.toString(), Integer.TYPE.hashCode());
        Console.log("{} : {}", Integer.class.toString(), Integer.class.hashCode());

        Console.log("type: {}", Integer.TYPE.getName());
        //        Console.log("type: {}", ClassUtils.forName("int"));
        Set<Integer> codes = new HashSet<>();
        for (Field field : TypeNames.class.getDeclaredFields()) {
            String value = ClassUtils.getFieldValue(TypeNames.class, field.getName()).toString();
            if (codes.contains(value.hashCode())) {
                throw new RuntimeException("codes.contains(value.hashCode())");
            }
            codes.add(value.hashCode());
            //            Class<?> type = ClassUtils.forName(value);
            Console.log("{} : {} : {}", field.getName(), value, value.hashCode());
            //            Console.log("{} : {}", type.toString(), type.hashCode());
        }
    }
}
