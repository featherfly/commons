
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 17:40:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.asm.instantiator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.asm.Asm;
import cn.featherfly.common.lang.BytesClassLoader;
import cn.featherfly.common.lang.ClassUtils;

/**
 * ClassLoaderProxyTest.
 *
 * @author zhongj
 */
public class BytesClassLoaderTest {

    BytesClassLoader bytesClassLoader;

    @BeforeClass
    void before() {
        bytesClassLoader = new BytesClassLoader(Thread.currentThread().getContextClassLoader());
    }

    @Test
    void test1() {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        String createdClassName = "cn.featherfly.User2";
        String createdClassByteCodeName = Asm.getName(createdClassName);

        ClassNode classNode = new ClassNode();
        classNode.version = Opcodes.V1_8;
        classNode.access = Opcodes.ACC_PUBLIC;
        classNode.name = createdClassByteCodeName;
        classNode.superName = Type.getInternalName(Object.class);

        MethodNode constructor = new MethodNode(Opcodes.ACC_PUBLIC, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(), null, null);
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, classNode.superName, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(), false);
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();
        // constructor
        classNode.methods.add(constructor);
        // instantiate method
        classNode.accept(cw);
        byte[] code = cw.toByteArray();
        // 定义类
        //        classLoaderProxy.defineClass(this.getClass().getClassLoader(), createdClassByteCodeName, code, null);

        Class<?> c = bytesClassLoader.defineClass(createdClassName, code, this.getClass().getProtectionDomain());
        System.out.println(c.getName());
        System.out.println(ClassUtils.newInstance(c));
    }

}
