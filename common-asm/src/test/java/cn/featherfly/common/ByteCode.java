
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 21:38:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common;

import org.objectweb.asm.Type;

/**
 * ByteCode.
 *
 * @author zhongj
 */
public class ByteCode {

    public ClassInfo defineClass(String className) {
        return null;
    }
}

interface ClassInfo {

    default ClassInfo parent(Class<?> type) {
        return parent(Type.getDescriptor(type));
    }

    ClassInfo parent(String className);
}
