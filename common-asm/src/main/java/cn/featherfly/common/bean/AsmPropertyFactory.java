
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:49:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureVisitor;
import org.objectweb.asm.signature.SignatureWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import cn.featherfly.common.asm.Asm;
import cn.featherfly.common.asm.AsmException;
import cn.featherfly.common.lang.BytesClassLoader;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.WordUtils;

/**
 * AsmInstantiatorFactory.
 *
 * @author zhongj
 */
public class AsmPropertyFactory implements Opcodes {

    /** The Constant CLASS_NAME_SUFFIX. */
    public static final String CLASS_NAME_SUFFIX = "Property";

    private static final String GET_METHOD_NAME = "get";
    private static final String SET_METHOD_NAME = "set";

    private final BytesClassLoader bytesClassLoader;

    /**
     * Instantiates a new asm instantiator factory.
     *
     * @param bytesClassLoader the bytes class loader
     * @param classLoader the class loader
     */
    public AsmPropertyFactory(BytesClassLoader bytesClassLoader) {
        this.bytesClassLoader = bytesClassLoader;
    }

    /**
     * New instance.
     *
     * @param <T> the generic type
     * @param <V> the value type
     * @param propertyType the property type
     * @param propertyVisitor the property visitor
     * @return the property
     */
    public <T, V> Property<T, V> newInstance(Class<Property<T, V>> propertyType, PropertyAccessor<T> propertyVisitor) {
        return ClassUtils.newInstance(propertyType, propertyVisitor);
    }

    /**
     * Creates a new AsmProperty object.
     *
     * @param <T> the generic type
     * @param <V> the value type
     * @param instanceType the instance class name
     * @param propertyType the property type
     * @param propertyName the property name
     * @param propertyIndex the property index
     * @return the class< property< t, v>>
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public <T, V> Class<Property<T, V>> create(String propertyVisitorName, Class<T> instanceType, Class<V> propertyType,
        String propertyName, int propertyIndex) {
        try {
            String createdClassName = createInnerClassName(propertyVisitorName, propertyName);
            // bcf byte code format
            String createdClassNameBcf = Asm.getName(createdClassName);
            String instanceClassNameBcf = Asm.getName(instanceType);
            String propertyClassNameBcf = Asm.getName(propertyType);

            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

            ClassNode classNode = new ClassNode();
            classNode.version = Opcodes.V1_8;
            classNode.access = ACC_PUBLIC;
            classNode.name = createdClassNameBcf;
            // extends
            classNode.superName = Type.getInternalName(AbstractProperty.class);
            SignatureWriter signature = new SignatureWriter();
            SignatureVisitor instantiatorVisitor = signature.visitSuperclass();
            instantiatorVisitor.visitClassType(classNode.superName); // generic of Instantiator
            SignatureVisitor instanceTypeVisitor = instantiatorVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF);
            instanceTypeVisitor.visitClassType(Type.getInternalName(instanceType));
            instanceTypeVisitor.visitEnd();
            SignatureVisitor propertyTypeVisitor = instantiatorVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF);
            propertyTypeVisitor.visitClassType(Type.getInternalName(propertyType));
            propertyTypeVisitor.visitEnd();
            instantiatorVisitor.visitEnd();
            classNode.signature = signature.toString();
            classNode.visitInnerClass(createdClassNameBcf, instanceClassNameBcf, createClassName(propertyName),
                ACC_PUBLIC);

            // constructor
            classNode.methods.add(constract(instanceType, propertyType, propertyName, propertyIndex));
            // instantiate method
            // get
            classNode.methods.addAll(methodGet(createdClassNameBcf, instanceType, propertyClassNameBcf, propertyName));
            // set
            classNode.methods.addAll(methodSet(createdClassNameBcf, instanceType, propertyClassNameBcf, propertyName));

            classNode.accept(cw);
            byte[] code = cw.toByteArray();

            // FIXME 后续功能完成后删除此测试
            //            FileOutputStream os = new FileOutputStream(new File(createdClassName + ".class"));
            //            os.write(code);
            //            os.flush();
            //            os.close();

            return (Class<Property<T, V>>) bytesClassLoader.defineClass(createdClassName, code,
                instanceType.getProtectionDomain());
        } catch (Exception e) {
            throw new AsmException(e);
        }
    }

    private MethodNode constract(Class<?> instaceType, Class<?> propertyType, String propertyName, int propertyIndex) {
        SignatureWriter signature = new SignatureWriter();
        SignatureVisitor propertyVisitorTypeVisitor = signature.visitParameterType();
        propertyVisitorTypeVisitor.visitClassType(Type.getInternalName(PropertyAccessor.class));
        SignatureVisitor propertyTypeVisitor = propertyVisitorTypeVisitor
            .visitTypeArgument(SignatureVisitor.INSTANCEOF);
        propertyTypeVisitor.visitClassType(Type.getInternalName(instaceType));
        propertyTypeVisitor.visitEnd();
        propertyVisitorTypeVisitor.visitEnd();

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(PropertyAccessor.class), signature.toString(), null);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitLdcInsn(Type.getType(instaceType));
        if (propertyType.isPrimitive()) {
            methodNode.visitFieldInsn(GETSTATIC, Asm.getPrimitiveWrapperName(propertyType), "TYPE",
                Type.getType(Class.class).toString());
        } else {
            methodNode.visitLdcInsn(Type.getType(propertyType));
        }
        methodNode.visitLdcInsn(propertyName);
        switch (propertyIndex) {
            case 0:
                methodNode.visitInsn(ICONST_0);
                break;
            case 1:
                methodNode.visitInsn(ICONST_1);
                break;
            case 2:
                methodNode.visitInsn(ICONST_2);
                break;
            case 3:
                methodNode.visitInsn(ICONST_3);
                break;
            case 4:
                methodNode.visitInsn(ICONST_4);
                break;
            case 5:
                methodNode.visitInsn(ICONST_5);
                break;
            default:
                methodNode.visitIntInsn(Opcodes.BIPUSH, propertyIndex);
        }
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(AbstractProperty.class), Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, Class.class, String.class, int.class, PropertyAccessor.class),
            false);
        methodNode.visitInsn(RETURN);
        return methodNode;
    }

    private List<MethodNode> methodGet(String createdClassNameBcf, Class<?> instanceType, String propertyClassNameBcf,
        String propertyName) throws NoSuchMethodException, SecurityException {
        String instanceClassNameBcf = Type.getInternalName(instanceType);

        final boolean primitive = Asm.isPrimitive(propertyClassNameBcf);
        final String warrperPropertyClassNameBcf;
        final String prefix = "boolean".equals(propertyClassNameBcf) ? "is" : "get";
        if (primitive) { // auto wrapper primitive type
            warrperPropertyClassNameBcf = Asm.getPrimitiveWrapperName(propertyClassNameBcf);
        } else {
            warrperPropertyClassNameBcf = propertyClassNameBcf;
        }

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, GET_METHOD_NAME, Type.getMethodDescriptor(
            Type.getObjectType(warrperPropertyClassNameBcf), Type.getObjectType(instanceClassNameBcf)), null, null);
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, instanceClassNameBcf, prefix + WordUtils.upperCaseFirst(propertyName),
            Type.getMethodDescriptor(Asm.getType(propertyClassNameBcf)), false);

        if (primitive) { // auto wrapper primitive type
            methodNode.visitMethodInsn(INVOKESTATIC, warrperPropertyClassNameBcf, Asm.PRIMITIVE_BOXING_METHOD,
                Asm.getPrimitiveBoxingMethodDescriptor(propertyClassNameBcf), false);
        }

        methodNode.visitInsn(ARETURN);
        //methodNode.visitMaxs(1, 2); // user ClassWriter.COMPUTE_MAXS instead
        methodNode.visitEnd();

        // ----------------------------------------------------------------------------------------------------------------

        MethodNode methodNode2 = new MethodNode(ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, GET_METHOD_NAME,
            Type.getMethodDescriptor(Property.class.getMethod(GET_METHOD_NAME, Object.class)), null, null);
        methodNode2.visitVarInsn(ALOAD, 0);
        methodNode2.visitVarInsn(ALOAD, 1);
        methodNode2.visitTypeInsn(CHECKCAST, instanceClassNameBcf);
        methodNode2.visitMethodInsn(INVOKEVIRTUAL, createdClassNameBcf, GET_METHOD_NAME, Type.getMethodDescriptor(
            Type.getObjectType(warrperPropertyClassNameBcf), Type.getObjectType(instanceClassNameBcf)), false);
        methodNode2.visitInsn(ARETURN);
        //methodNode2.visitMaxs(2, 2); // user ClassWriter.COMPUTE_MAXS instead
        methodNode2.visitEnd();

        return Lang.list(methodNode, methodNode2);
    }

    private List<MethodNode> methodSet(String createdClassNameBcf, Class<?> instanceType, String propertyClassNameBcf,
        String propertyName) throws NoSuchMethodException, SecurityException {
        String instanceClassNameBcf = Type.getInternalName(instanceType);

        final boolean primitive = Asm.isPrimitive(propertyClassNameBcf);
        final String warrperPropertyClassNameBcf;
        if (primitive) { // auto wrapper primitive type
            warrperPropertyClassNameBcf = Asm.getPrimitiveWrapperName(propertyClassNameBcf);
        } else {
            warrperPropertyClassNameBcf = propertyClassNameBcf;
        }

        String setMethodDescptor = Type.getMethodDescriptor(Type.getType(Void.TYPE),
            Type.getObjectType(instanceClassNameBcf), Type.getObjectType(warrperPropertyClassNameBcf));

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, SET_METHOD_NAME, setMethodDescptor, null, null);
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitVarInsn(ALOAD, 2);
        if (primitive) { // auto wrapper primitive type
            methodNode.visitMethodInsn(INVOKEVIRTUAL, warrperPropertyClassNameBcf,
                Asm.getPrimitiveUnboxingMethod(propertyClassNameBcf),
                Asm.getPrimitiveUnboxingMethodDescriptor(propertyClassNameBcf), false);
        }
        methodNode.visitMethodInsn(INVOKEVIRTUAL, instanceClassNameBcf, "set" + WordUtils.upperCaseFirst(propertyName),
            Type.getMethodDescriptor(Type.getType(Void.TYPE), Asm.getType(propertyClassNameBcf)), false);
        methodNode.visitInsn(RETURN);
        //methodNode.visitMaxs(2, 3); // user ClassWriter.COMPUTE_MAXS instead
        methodNode.visitEnd();

        // ----------------------------------------------------------------------------------------------------------------

        MethodNode methodNode2 = new MethodNode(ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, SET_METHOD_NAME,
            Type.getMethodDescriptor(Property.class.getMethod(SET_METHOD_NAME, Object.class, Object.class)), null,
            null);
        methodNode2.visitVarInsn(ALOAD, 0);
        methodNode2.visitVarInsn(ALOAD, 1);
        methodNode2.visitTypeInsn(CHECKCAST, instanceClassNameBcf);
        methodNode2.visitVarInsn(ALOAD, 2);
        methodNode2.visitTypeInsn(CHECKCAST, warrperPropertyClassNameBcf);
        methodNode2.visitMethodInsn(INVOKEVIRTUAL, createdClassNameBcf, SET_METHOD_NAME, setMethodDescptor, false);
        methodNode2.visitInsn(RETURN);
        //methodNode.visitMaxs(3, 3); // user ClassWriter.COMPUTE_MAXS instead
        methodNode2.visitEnd();

        return Lang.list(methodNode, methodNode2);
    }

    private String createClassName(String propertyName) {
        // inner class
        return WordUtils.upperCaseFirst(propertyName) + CLASS_NAME_SUFFIX;
    }

    private String createInnerClassName(String outerClassName, String propertyName) {
        // inner class
        //        return outerClassName + "$" + createClassName(propertyName);
        return outerClassName + createClassName(propertyName);
    }
}
