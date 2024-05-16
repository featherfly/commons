
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:49:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.asm;

import static org.objectweb.asm.Opcodes.INVOKESPECIAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureVisitor;
import org.objectweb.asm.signature.SignatureWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import cn.featherfly.common.bean.Instantiator;
import cn.featherfly.common.bean.InstantiatorFactory;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.ClassUtils;

/**
 * AsmInstantiatorFactory.
 *
 * @author zhongj
 */
public class AsmInstantiatorFactory implements InstantiatorFactory {

    private final Map<Class<?>, Instantiator<?>> types = new HashMap<>(0);

    /** The Constant CLASS_NAME_SUFFIX. */
    public static final String CLASS_NAME_SUFFIX = "InstantiatorByFeatherfly";

    private static final String METHOD_INSTANTIATE = "instantiate";
    private static final String METHOD_GETTYPE = "getType";

    private final Supplier<ClassLoader> classLoader;

    private final BytesClassLoader bytesClassLoader;

    private boolean cacheResults;

    /**
     * Instantiates a new asm instantiator factory.
     *
     * @param classLoader the class loader
     */
    public AsmInstantiatorFactory(Supplier<ClassLoader> classLoader) {
        this(classLoader, true);
    }

    /**
     * Instantiates a new instantiator factor.
     *
     * @param classLoader the class loader
     * @param cacheResults the cache results
     */
    public AsmInstantiatorFactory(Supplier<ClassLoader> classLoader, boolean cacheResults) {
        super();
        this.classLoader = classLoader;
        this.cacheResults = cacheResults;
        bytesClassLoader = new BytesClassLoader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Instantiator<T> create(Class<T> type) {
        Instantiator<T> instantiator = (Instantiator<T>) types.get(type);
        if (cacheResults && instantiator != null) {
            return instantiator;
        }

        try {
            Class<Instantiator<T>> newType = create0(type);
            instantiator = ClassUtils.newInstance(newType);
            if (cacheResults) {
                types.put(type, instantiator);
            }
            return instantiator;
        } catch (Exception e) {
            throw new AsmException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<Instantiator<T>> create0(Class<T> type) throws Exception {
        // check empty argu constructor
        type.getConstructor(ArrayUtils.EMPTY_CLASS_ARRAY);

        String instantiateType = Type.getInternalName(type);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        String createdClassName = type.getName() + CLASS_NAME_SUFFIX;
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
        classNode.methods.add(instantiateMethod(instantiateType));
        classNode.methods.add(getTypeMethod(type));

        classNode.accept(cw);
        byte[] code = cw.toByteArray();

        return (Class<Instantiator<T>>) ClassLoaderUtils.defineClass(classLoader.get(), createdClassName, code,
            type.getProtectionDomain(),
            () -> bytesClassLoader.defineClass(createdClassName, code, type.getProtectionDomain()));
    }

    private MethodNode instantiateMethod(String typeInternalName) throws NoSuchMethodException, SecurityException {
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

    private MethodNode getTypeMethod(Class<?> type) throws NoSuchMethodException, SecurityException {
        String methodDescriptor = Type
            .getMethodDescriptor(Instantiator.class.getMethod(METHOD_GETTYPE, ArrayUtils.EMPTY_CLASS_ARRAY));

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, METHOD_GETTYPE, methodDescriptor, null, null);
        methodNode.parameters = new ArrayList<>();
        System.out.println(Type.getType(type));
        methodNode.visitLdcInsn(Type.getType(type));
        methodNode.visitInsn(Opcodes.ARETURN);
        methodNode.visitMaxs(1, 1);
        methodNode.visitEnd();
        return methodNode;
    }
}
