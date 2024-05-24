
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:49:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.objectweb.asm.Opcodes.INVOKESPECIAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureVisitor;
import org.objectweb.asm.signature.SignatureWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import cn.featherfly.common.asm.Asm;
import cn.featherfly.common.asm.AsmException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.BytesClassLoader;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.ReloadableClassloader;

/**
 * asm instantiator factory.
 *
 * @author zhongj
 */
public class AsmInstantiatorFactory extends ReloadableClassloader implements InstantiatorFactory {

    private final Map<Class<?>, Instantiator<?>> typeInstances = new HashMap<>(0);

    /** The Constant CLASS_NAME_SUFFIX. */
    public static final String CLASS_NAME_SUFFIX = "InstantiatorByFeatherfly";

    private static final String METHOD_INSTANTIATE = "instantiate";
    private static final String METHOD_GETTYPE = "getType";

    private final BytesClassLoader bytesClassLoader;

    /**
     * Instantiates a new instantiator factor.
     *
     * @param classLoader the class loader
     */
    public AsmInstantiatorFactory(ClassLoader classLoader) {
        super();
        bytesClassLoader = new BytesClassLoader(classLoader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Instantiator<T> create(Class<T> type, ClassLoader classLoader) {
        // load Instantiator type class from cache, if exists, return, else goto create instantiator type class.
        Instantiator<T> instantiator = (Instantiator<T>) typeInstances.get(type);
        if (instantiator != null) {
            return instantiator;
        }

        try {
            Class<Instantiator<T>> newType = create0(type, classLoader);
            instantiator = ClassUtils.newInstance(newType);
            typeInstances.put(type, instantiator);
            return instantiator;
        } catch (Exception e) {
            throw new AsmException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<Instantiator<T>> create0(Class<T> type, ClassLoader classLoader)
        throws NoSuchMethodException, SecurityException {
        // check empty argu constructor
        type.getConstructor(ArrayUtils.EMPTY_CLASS_ARRAY);

        classLoader = prepare(classLoader);

        String instantiateType = Type.getInternalName(type);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        String createdClassName = createClassName(type);
        String createdClassByteCodeName = Asm.getName(createdClassName);

        ClassNode classNode = new ClassNode();
        classNode.version = Opcodes.V1_8;
        classNode.access = Opcodes.ACC_PUBLIC;
        classNode.name = createdClassByteCodeName;
        classNode.superName = Type.getInternalName(Object.class);

        classNode.interfaces.add(Asm.getName(Instantiator.class));
        SignatureWriter signature = new SignatureWriter();
        SignatureVisitor instantiatorVisitor = signature.visitSuperclass();
        instantiatorVisitor.visitClassType(classNode.interfaces.get(0)); // generic of Instantiator
        SignatureVisitor typeVisitor = instantiatorVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF);
        typeVisitor.visitClassType(Type.getInternalName(type));
        typeVisitor.visitEnd();
        instantiatorVisitor.visitEnd();
        classNode.signature = signature.toString();

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
        classNode.methods.add(createMethodInstantiate(instantiateType));
        classNode.methods.add(createMethodGetType(type));

        classNode.accept(cw);
        byte[] code = cw.toByteArray();

        return (Class<Instantiator<T>>) ClassLoaderUtils.defineClass(classLoader, createdClassName, code,
            type.getProtectionDomain(),
            () -> bytesClassLoader.defineClass(createdClassName, code, type.getProtectionDomain()));
    }

    private String createClassName(Class<?> type) {
        return type.getName() + CLASS_NAME_SUFFIX;
    }

    /**
     * Creates a new AsmInstantiator object.
     *
     * @param typeInternalName the type internal name
     * @return the method node
     * @throws NoSuchMethodException the no such method exception
     * @throws SecurityException the security exception
     */
    public static MethodNode createMethodInstantiate(String typeInternalName)
        throws NoSuchMethodException, SecurityException {
        String methodDescriptor = Type
            .getMethodDescriptor(Instantiator.class.getMethod(METHOD_INSTANTIATE, ArrayUtils.EMPTY_CLASS_ARRAY));

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, METHOD_INSTANTIATE, methodDescriptor, null, null);
        methodNode.parameters = new ArrayList<>();
        methodNode.visitTypeInsn(Opcodes.NEW, typeInternalName);
        methodNode.visitInsn(Opcodes.DUP);
        methodNode.visitMethodInsn(INVOKESPECIAL, typeInternalName, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(), false);
        methodNode.visitInsn(Opcodes.ARETURN);
        methodNode.visitMaxs(2, 1);
        methodNode.visitEnd();
        return methodNode;
    }

    /**
     * Creates a new AsmInstantiator object.
     *
     * @param type the type
     * @return the method node
     * @throws NoSuchMethodException the no such method exception
     * @throws SecurityException the security exception
     */
    public static MethodNode createMethodGetType(Class<?> type) throws NoSuchMethodException, SecurityException {
        String methodDescriptor = Type
            .getMethodDescriptor(Instantiator.class.getMethod(METHOD_GETTYPE, ArrayUtils.EMPTY_CLASS_ARRAY));

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, METHOD_GETTYPE, methodDescriptor, null, null);
        methodNode.parameters = new ArrayList<>();
        methodNode.visitLdcInsn(Type.getType(type));
        methodNode.visitInsn(Opcodes.ARETURN);
        methodNode.visitMaxs(1, 1);
        methodNode.visitEnd();
        return methodNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doClassLoaderReload() {
        typeInstances.clear();
    }
}
