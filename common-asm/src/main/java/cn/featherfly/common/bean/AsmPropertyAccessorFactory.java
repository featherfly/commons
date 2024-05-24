
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:49:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.UnaryOperator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureVisitor;
import org.objectweb.asm.signature.SignatureWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import cn.featherfly.common.asm.Asm;
import cn.featherfly.common.asm.AsmException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.BytesClassLoader;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.ReloadableClassloader;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.policy.AllowDenyListPolicy;
import cn.featherfly.common.policy.AllowDenyListPolicy.Strategy;
import cn.featherfly.common.policy.AllowPolicy;

/**
 * asm property accessor factory.
 *
 * @author zhongj
 */
public class AsmPropertyAccessorFactory extends ReloadableClassloader implements PropertyAccessorFactory, Opcodes {

    private final PropertyAccessorManagerImpl manager;

    /** The Constant CLASS_NAME_SUFFIX. */
    public static final String CLASS_NAME_SUFFIX = "PropertyVisitorCreateByFeatherfly";

    private static final String PROPERTY_MAP = "propertyMap";
    private static final String MAP_NAME = Type.getInternalName(LinkedHashMap.class);
    private static final String MAP_DESCRIPTOR = Type.getDescriptor(LinkedHashMap.class);
    private static final String GET = "get";
    private static final String GET_DESCRIPTOR = Type.getMethodDescriptor(Type.getType(Object.class),
        Type.getType(Object.class));
    private static final String SET = "set";
    private static final String SET_DESCRIPTOR = Type.getMethodDescriptor(Type.getType(void.class),
        Type.getType(Object.class), Type.getType(Object.class));
    private static final String MAP_PUT_NAME = "put";
    private static final String MAP_PUT_DESCRIPTOR = Type.getMethodDescriptor(Type.getType(Object.class),
        Type.getType(Object.class), Type.getType(Object.class));

    private static final String GET_PROPERTY_METHOD = "getProperty";
    private static final String GET_PROPERTY_DESCRIPTOR_BYNAME = Type.getMethodDescriptor(Type.getType(Property.class),
        Type.getType(String.class));

    private static final String SET_PROPERTY_DESCRIPTOR_BY_NAME = Type.getMethodDescriptor(Type.getType(void.class),
        Type.getType(Object.class), Type.getType(String.class), Type.getType(Object.class));
    private static final String SET_PROPERTY_DESCRIPTOR_BY_INDEX = Type.getMethodDescriptor(Type.getType(void.class),
        Type.getType(Object.class), Type.getType(int.class), Type.getType(Object.class));

    private static final String GET_PROPERTY_VALUE_METHOD = "getPropertyValue";
    private static final String SET_PROPERTY_VALUE_METHOD = "setPropertyValue";

    private final BytesClassLoader bytesClassLoader;

    private final AsmPropertyFactory propertyFactory;

    private final AllowPolicy<java.lang.reflect.Type> propertyVisitorCascadeCreatePolicy;

    private static AllowDenyListPolicy<java.lang.reflect.Type> defaultPolicy() {
        return new AllowDenyListPolicy<java.lang.reflect.Type>(Strategy.DENY_ONLY)
            // deny all java package and primitive type
            .addDeny(type -> {
                if (type.getTypeName().startsWith("java")) {
                    return true;
                }
                if (type instanceof Class) {
                    Class<?> c = (Class<?>) type;
                    return c.isPrimitive() || c.isInterface() || c.isAnnotation() || c.isArray() || c.isEnum();
                }
                return false;
            }) // .addAllow(null) // do more here
        ;

    }

    /**
     * Instantiates a new asm instantiator factory.
     *
     * @param classLoader the class loader
     */
    public AsmPropertyAccessorFactory(ClassLoader classLoader) {
        this(classLoader, defaultPolicy());
    }

    /**
     * Instantiates a new instantiator factor.
     *
     * @param classLoader the class loader
     * @param unary the unary
     */
    public AsmPropertyAccessorFactory(ClassLoader classLoader,
        UnaryOperator<AllowDenyListPolicy<java.lang.reflect.Type>> unary) {
        this(classLoader, unary.apply(defaultPolicy()));
    }

    /**
     * Instantiates a new instantiator factor.
     *
     * @param classLoader the class loader
     * @param propertyVisitorCascadeCreatePolicy the property visitor cascade create policy
     */
    public AsmPropertyAccessorFactory(ClassLoader classLoader,
        AllowPolicy<java.lang.reflect.Type> propertyVisitorCascadeCreatePolicy) {
        super();
        bytesClassLoader = new BytesClassLoader(classLoader);
        propertyFactory = new AsmPropertyFactory(bytesClassLoader);

        this.propertyVisitorCascadeCreatePolicy = propertyVisitorCascadeCreatePolicy;
        manager = new PropertyAccessorManagerImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> PropertyAccessor<T> create(Class<T> type, ClassLoader classLoader) {
        // load PropertyVisitor type class from cache, if exists, return, else goto create PropertyVisitor type class.
        PropertyAccessor<T> propertyAccessor = manager.getPropertyAccessor(type);
        if (propertyAccessor != null) {
            return propertyAccessor;
        }

        try {
            Class<PropertyAccessor<T>> newType = create0(type, classLoader);
            propertyAccessor = ClassUtils.newInstance(newType);
            manager.add(type, propertyAccessor);
            return propertyAccessor;
        } catch (Exception e) {
            throw new AsmException(e);
        }
    }

    /**
     * Creates a new AsmPropertyAccessor object.
     */
    public void createPropertyAccessorCascade() {
        for (PropertyAccessor<?> propertyAccessor : manager.getAll()) {
            createPropertyAccessorRecursion(propertyAccessor);
        }
    }

    private void createPropertyAccessorRecursion(PropertyAccessor<?> propertyAccessor) {
        for (Property<?, ?> property : propertyAccessor.getProperties()) {
            createPropertyAccessorRecursion(property);
        }
    }

    private <V> void createPropertyAccessorRecursion(Property<?, V> property) {
        if (property.getPropertyAccessor() == null && propertyVisitorCascadeCreatePolicy.isAllow(property.getType())) {
            if (!manager.containsType(property.getType())) {
                PropertyAccessor<V> newPv = create(property.getType());
                ((AbstractProperty<?, V>) property).setPropertyAccessor(newPv);
                createPropertyAccessorRecursion(newPv);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<PropertyAccessor<T>> create0(Class<T> type, ClassLoader classLoader)
        throws NoSuchMethodException, SecurityException {
        // check empty argu constructor
        type.getConstructor(ArrayUtils.EMPTY_CLASS_ARRAY);

        classLoader = prepare(classLoader);

        String instantiateType = Type.getInternalName(type);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        String createdClassName = createClassName(type);
        String createdClassByteCodeName = Asm.getName(createdClassName);

        ClassNode classNode = new ClassNode();
        classNode.version = Opcodes.V1_8;
        classNode.access = Opcodes.ACC_PUBLIC;
        classNode.name = createdClassByteCodeName;
        classNode.superName = Type.getInternalName(AbstractPropertyAccessor.class);

        SignatureWriter signature = new SignatureWriter();
        SignatureVisitor instantiatorVisitor = signature.visitSuperclass();
        instantiatorVisitor.visitClassType(classNode.superName); // generic of Instantiator
        SignatureVisitor typeVisitor = instantiatorVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF);
        typeVisitor.visitClassType(Type.getInternalName(type));
        typeVisitor.visitEnd();
        instantiatorVisitor.visitEnd();
        classNode.signature = signature.toString();

        // name property map
        String hashMapBcf = MAP_NAME;

        SignatureWriter fieldsMapSignature = new SignatureWriter();
        fieldsMapSignature.visitClassType(hashMapBcf);
        SignatureVisitor fieldsMapKey = fieldsMapSignature.visitTypeArgument(SignatureVisitor.INSTANCEOF);
        fieldsMapKey.visitClassType(Type.getInternalName(String.class));
        fieldsMapKey.visitEnd();
        SignatureVisitor fieldsMapValue = fieldsMapSignature.visitTypeArgument(SignatureVisitor.INSTANCEOF);
        fieldsMapValue.visitClassType(Type.getInternalName(Property.class));
        fieldsMapValue.visitEnd();
        fieldsMapSignature.visitEnd();

        // all property
        BeanDescriptor<T> bd = BeanDescriptor.getBeanDescriptor(type);
        int i = 0;
        List<Class<?>> newPropertyTypes = new ArrayList<>();
        for (BeanProperty<T, ?> beanProperty : bd.getBeanProperties()) {
            Class<?> propertyType = propertyFactory.create(createdClassName, type, beanProperty.getType(),
                beanProperty.getName(), i, classLoader);
            i++;

            newPropertyTypes.add(propertyType);

            FieldNode fieldNode = new FieldNode(ACC_PRIVATE | ACC_FINAL, beanProperty.getName(),
                Type.getDescriptor(propertyType), null, null);
            classNode.fields.add(fieldNode);
        }
        classNode.fields.add(
            new FieldNode(ACC_PRIVATE | ACC_FINAL, PROPERTY_MAP, MAP_DESCRIPTOR, fieldsMapSignature.toString(), null));

        // constructor
        classNode.methods.add(constructor(classNode, newPropertyTypes));

        // instantiate method
        // instantiate
        classNode.methods.add(AsmInstantiatorFactory.createMethodInstantiate(instantiateType));
        // getType
        classNode.methods.add(AsmInstantiatorFactory.createMethodGetType(type));

        // PropertyVisitor method
        classNode.methods.add(methodGetPropertyByIndex(classNode, newPropertyTypes, bd));
        classNode.methods.add(methodGetPropertyByName(classNode, newPropertyTypes, bd));
        classNode.methods.addAll(methodGetPropertyValueByIndex(classNode, newPropertyTypes, bd));
        classNode.methods.addAll(methodGetPropertyValueByName(classNode, bd));
        classNode.methods.addAll(methodSetPropertyValueByName(classNode, bd));
        classNode.methods.addAll(methodSetPropertyValueByIndex(classNode, newPropertyTypes, bd));
        classNode.methods.add(methodGetProperties(classNode));

        classNode.accept(cw);
        byte[] code = cw.toByteArray();

        // FIXME 后续功能完成后删除此测试
        //        FileOutputStream os = new FileOutputStream(new File("bin/" + createdClassName + ".class"));
        //        os.write(code);
        //        os.flush();
        //        os.close();

        return (Class<PropertyAccessor<T>>) ClassLoaderUtils.defineClass(classLoader, createdClassByteCodeName, code,
            type.getProtectionDomain(),
            () -> bytesClassLoader.defineClass(createdClassName, code, type.getProtectionDomain()));
    }

    private MethodNode constructor(ClassNode classNode, List<Class<?>> propertyTypes) {
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, Asm.CONSTRUCT_METHOD, Asm.getConstructorDescriptor(),
            null, null);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitMethodInsn(INVOKESPECIAL, classNode.superName, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(), false);

        String hashMapBcf = MAP_NAME;
        // map
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitTypeInsn(NEW, hashMapBcf);
        methodNode.visitInsn(DUP);
        methodNode.visitMethodInsn(INVOKESPECIAL, hashMapBcf, Asm.CONSTRUCT_METHOD, Asm.getConstructorDescriptor(),
            false);
        methodNode.visitFieldInsn(PUTFIELD, classNode.name, PROPERTY_MAP, MAP_DESCRIPTOR);

        // property
        int i = 0;
        for (Class<?> propertyType : propertyTypes) {
            // set field
            FieldNode field = classNode.fields.get(i);
            String propertyTypeBcf = Type.getInternalName(propertyType);

            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitTypeInsn(NEW, Type.getInternalName(propertyType));
            methodNode.visitInsn(DUP);
            methodNode.visitInsn(ACONST_NULL);
            methodNode.visitMethodInsn(INVOKESPECIAL, propertyTypeBcf, Asm.CONSTRUCT_METHOD,
                Asm.getConstructorDescriptor(PropertyAccessor.class), false);
            methodNode.visitFieldInsn(PUTFIELD, classNode.name, field.name, field.desc);

            // put to map
            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitFieldInsn(GETFIELD, classNode.name, PROPERTY_MAP, MAP_DESCRIPTOR);
            methodNode.visitLdcInsn(field.name);
            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitFieldInsn(GETFIELD, classNode.name, field.name, field.desc);
            methodNode.visitMethodInsn(INVOKEVIRTUAL, hashMapBcf, MAP_PUT_NAME, MAP_PUT_DESCRIPTOR, false);
            methodNode.visitInsn(POP);

            i++;
        }
        methodNode.visitInsn(RETURN);
        methodNode.visitEnd();
        return methodNode;
    }

    // ****************************************************************************************************************
    //  getPropertyValue(Object, int)
    // ****************************************************************************************************************
    private List<MethodNode> methodGetPropertyValueByIndex(ClassNode classNode, List<Class<?>> propertyTypes,
        BeanDescriptor<?> bd) throws SecurityException {
        final String methodName = GET_PROPERTY_VALUE_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(Type.getType(Object.class), Type.getType(bd.getType()),
            Type.getType(int.class));
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitIntInsn(ILOAD, 2);
        Label[] labels = new Label[propertyTypes.size()];
        for (int i = 0; i < propertyTypes.size(); i++) {
            labels[i] = new Label();
        }
        Label labelDeft = new Label();
        methodNode.visitTableSwitchInsn(0, propertyTypes.size() - 1, labelDeft, labels);
        for (int i = 0; i < propertyTypes.size(); i++) {
            BeanProperty<?, ?> bp = bd.getBeanProperty(i);
            methodNode.visitLabel(labels[i]);
            // use ClassWriter.COMPUTE_FRAMES
            // methodNode.visitFrame(F_SAME, 0, null, 0, null);
            methodNode.visitVarInsn(ALOAD, 1);
            final String prefix = bp.getType() == Boolean.TYPE ? "is" : "get";
            methodNode.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(bd.getType()),
                prefix + WordUtils.upperCaseFirst(bp.getName()), Type.getMethodDescriptor(Asm.getType(bp.getType())),
                false);
            if (bp.getType().isPrimitive()) { // auto wrapper primitive type
                methodNode.visitMethodInsn(INVOKESTATIC, Asm.getPrimitiveWrapperName(bp.getType().getName()),
                    Asm.PRIMITIVE_BOXING_METHOD, Asm.getPrimitiveBoxingMethodDescriptor(bp.getType().getName()), false);
            }

            methodNode.visitInsn(ARETURN);
        }
        String exceptionBcnf = Type.getInternalName(NoSuchPropertyException.class);

        methodNode.visitLabel(labelDeft);
        // methodNode.visitFrame(F_SAME, 0, null, 0, null);
        methodNode.visitTypeInsn(Opcodes.NEW, exceptionBcnf);
        methodNode.visitInsn(DUP);
        methodNode.visitLdcInsn(Type.getType(bd.getType()));
        methodNode.visitIntInsn(ILOAD, 2);
        methodNode.visitMethodInsn(INVOKESPECIAL, exceptionBcnf, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, int.class), false);
        methodNode.visitInsn(ATHROW);

        methodNode.visitEnd();

        // ----------------------------------------------------------------------------------------------------------------
        MethodNode methodNode2 = new MethodNode(Opcodes.ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, methodName,
            Type.getMethodDescriptor(Type.getType(Object.class), Type.getType(Object.class), Type.getType(int.class)),
            null, null);
        methodNode2.visitVarInsn(ALOAD, 0);
        methodNode2.visitVarInsn(ALOAD, 1);
        methodNode2.visitTypeInsn(CHECKCAST, Type.getInternalName(bd.getType()));
        methodNode2.visitIntInsn(ILOAD, 2);
        methodNode2.visitMethodInsn(INVOKEVIRTUAL, classNode.name, methodName, methodDescriptor, false);
        methodNode2.visitInsn(ARETURN);
        methodNode2.visitEnd();

        return Lang.list(methodNode, methodNode2);
    }

    // ****************************************************************************************************************
    //  getPropertyValue(Object, String)
    // ****************************************************************************************************************
    private List<MethodNode> methodGetPropertyValueByName(ClassNode classNode, BeanDescriptor<?> bd)
        throws SecurityException {
        final String methodName = GET_PROPERTY_VALUE_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(Type.getType(Object.class), Type.getType(bd.getType()),
            Type.getType(String.class));
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitVarInsn(ALOAD, 2);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, classNode.name, GET_PROPERTY_METHOD, GET_PROPERTY_DESCRIPTOR_BYNAME,
            false);
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(Property.class), GET, GET_DESCRIPTOR, true);
        methodNode.visitInsn(ARETURN);
        methodNode.visitEnd();

        // ----------------------------------------------------------------------------------------------------------------

        MethodNode methodNode2 = new MethodNode(Opcodes.ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, methodName, Type
            .getMethodDescriptor(Type.getType(Object.class), Type.getType(Object.class), Type.getType(String.class)),
            null, null);
        methodNode2.visitVarInsn(ALOAD, 0);
        methodNode2.visitVarInsn(ALOAD, 1);
        methodNode2.visitTypeInsn(CHECKCAST, Type.getInternalName(bd.getType()));
        methodNode2.visitVarInsn(ALOAD, 2);
        methodNode2.visitMethodInsn(INVOKEVIRTUAL, classNode.name, methodName, methodDescriptor, false);
        methodNode2.visitInsn(ARETURN);
        methodNode2.visitEnd();

        return Lang.list(methodNode, methodNode2);
    }

    // ****************************************************************************************************************
    //  setPropertyValue(Object, String, Object)
    // ****************************************************************************************************************
    private List<MethodNode> methodSetPropertyValueByIndex(ClassNode classNode, List<Class<?>> propertyTypes,
        BeanDescriptor<?> bd) throws SecurityException {
        final String methodName = SET_PROPERTY_VALUE_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(Type.getType(void.class), Type.getType(bd.getType()),
            Type.getType(int.class), Type.getType(Object.class));

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitIntInsn(ILOAD, 2);
        Label[] labels = new Label[propertyTypes.size()];
        for (int i = 0; i < propertyTypes.size(); i++) {
            labels[i] = new Label();
        }
        Label labelDeft = new Label();
        methodNode.visitTableSwitchInsn(0, propertyTypes.size() - 1, labelDeft, labels);
        for (int i = 0; i < propertyTypes.size(); i++) {
            BeanProperty<?, ?> bp = bd.getBeanProperty(i);
            String propertyTypeBcnf = Type.getInternalName(bp.getType());
            methodNode.visitLabel(labels[i]);
            methodNode.visitVarInsn(ALOAD, 1);
            methodNode.visitVarInsn(ALOAD, 3);
            methodNode.visitTypeInsn(CHECKCAST, Asm.getPrimitiveWrapperName(propertyTypeBcnf));
            if (bp.getType().isPrimitive()) { // auto unwrapper primitive type
                methodNode.visitMethodInsn(INVOKEVIRTUAL, Asm.getPrimitiveWrapperName(bp.getType()),
                    Asm.getPrimitiveUnboxingMethod(bp.getType()),
                    Asm.getPrimitiveUnboxingMethodDescriptor(bp.getType()), false);
            }
            methodNode.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(bd.getType()),
                "set" + WordUtils.upperCaseFirst(bp.getName()),
                Type.getMethodDescriptor(Type.getType(void.class), Type.getType(bp.getType())), false);
            methodNode.visitInsn(RETURN);
        }
        String exceptionBcnf = Type.getInternalName(NoSuchPropertyException.class);

        methodNode.visitLabel(labelDeft);
        methodNode.visitTypeInsn(Opcodes.NEW, exceptionBcnf);
        methodNode.visitInsn(DUP);
        methodNode.visitLdcInsn(Type.getType(bd.getType()));
        methodNode.visitIntInsn(ILOAD, 2);
        methodNode.visitMethodInsn(INVOKESPECIAL, exceptionBcnf, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, int.class), false);
        methodNode.visitInsn(ATHROW);

        methodNode.visitEnd();

        // ----------------------------------------------------------------------------------------------------------------

        MethodNode methodNode2 = new MethodNode(Opcodes.ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, methodName,
            SET_PROPERTY_DESCRIPTOR_BY_INDEX, null, null);
        methodNode2.visitVarInsn(ALOAD, 0);
        methodNode2.visitVarInsn(ALOAD, 1);
        methodNode2.visitTypeInsn(CHECKCAST, Type.getInternalName(bd.getType()));
        methodNode2.visitIntInsn(ILOAD, 2);
        methodNode2.visitVarInsn(ALOAD, 3);
        methodNode2.visitMethodInsn(INVOKEVIRTUAL, classNode.name, methodName, methodDescriptor, false);
        methodNode2.visitInsn(RETURN);
        methodNode2.visitEnd();

        return Lang.list(methodNode, methodNode2);
    }

    // ****************************************************************************************************************
    //  setPropertyValue(Object, String, Object)
    // ****************************************************************************************************************
    private List<MethodNode> methodSetPropertyValueByName(ClassNode classNode, BeanDescriptor<?> bd)
        throws SecurityException {
        final String methodName = SET_PROPERTY_VALUE_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(Type.getType(void.class), Type.getType(bd.getType()),
            Type.getType(String.class), Type.getType(Object.class));
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitVarInsn(ALOAD, 2);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, classNode.name, GET_PROPERTY_METHOD, GET_PROPERTY_DESCRIPTOR_BYNAME,
            false);
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitVarInsn(ALOAD, 3);
        methodNode.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(Property.class), SET, SET_DESCRIPTOR, true);
        methodNode.visitInsn(RETURN);
        methodNode.visitEnd();

        // ----------------------------------------------------------------------------------------------------------------

        MethodNode methodNode2 = new MethodNode(Opcodes.ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, methodName,
            SET_PROPERTY_DESCRIPTOR_BY_NAME, null, null);
        methodNode2.visitVarInsn(ALOAD, 0);
        methodNode2.visitVarInsn(ALOAD, 1);
        methodNode2.visitTypeInsn(CHECKCAST, Type.getInternalName(bd.getType()));
        methodNode2.visitVarInsn(ALOAD, 2);
        methodNode2.visitVarInsn(ALOAD, 3);
        methodNode2.visitMethodInsn(INVOKEVIRTUAL, classNode.name, methodName, methodDescriptor, false);
        methodNode2.visitInsn(RETURN);
        methodNode2.visitEnd();

        return Lang.list(methodNode, methodNode2);
    }

    // ****************************************************************************************************************
    //  getProperty(int)
    // ****************************************************************************************************************
    private MethodNode methodGetPropertyByIndex(ClassNode classNode, List<Class<?>> propertyTypes, BeanDescriptor<?> bd)
        throws NoSuchMethodException, SecurityException {
        final String methodName = GET_PROPERTY_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(PropertyAccessor.class.getMethod(methodName, int.class));

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitIntInsn(ILOAD, 1);
        Label[] labels = new Label[propertyTypes.size()];
        for (int i = 0; i < propertyTypes.size(); i++) {
            labels[i] = new Label();
        }
        Label labelDeft = new Label();
        methodNode.visitTableSwitchInsn(0, propertyTypes.size() - 1, labelDeft, labels);
        for (int i = 0; i < propertyTypes.size(); i++) {
            FieldNode field = classNode.fields.get(i);
            methodNode.visitLabel(labels[i]);
            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitFieldInsn(GETFIELD, classNode.name, field.name, field.desc);
            methodNode.visitInsn(ARETURN);
        }
        String exceptionBcnf = Type.getInternalName(NoSuchPropertyException.class);

        methodNode.visitLabel(labelDeft);
        methodNode.visitTypeInsn(Opcodes.NEW, exceptionBcnf);
        methodNode.visitInsn(DUP);
        methodNode.visitLdcInsn(Type.getType(bd.getType()));
        methodNode.visitIntInsn(ILOAD, 1);
        methodNode.visitMethodInsn(INVOKESPECIAL, exceptionBcnf, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, int.class), false);
        methodNode.visitInsn(ATHROW);

        methodNode.visitEnd();
        return methodNode;
    }

    // ****************************************************************************************************************
    //  getProperty(String)
    // ****************************************************************************************************************
    private MethodNode methodGetPropertyByName(ClassNode classNode, List<Class<?>> propertyTypes, BeanDescriptor<?> bd)
        throws NoSuchMethodException, SecurityException {
        final String methodName = GET_PROPERTY_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(PropertyAccessor.class.getMethod(methodName, String.class));

        String exceptionBcnf = Type.getInternalName(NoSuchPropertyException.class);
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitFieldInsn(GETFIELD, classNode.name, PROPERTY_MAP, MAP_DESCRIPTOR);
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, MAP_NAME, GET, GET_DESCRIPTOR, false);
        methodNode.visitTypeInsn(CHECKCAST, Type.getInternalName(Property.class));
        methodNode.visitVarInsn(ASTORE, 2);
        methodNode.visitVarInsn(ALOAD, 2);
        Label labelNoneNull = new Label();
        methodNode.visitJumpInsn(IFNONNULL, labelNoneNull);
        methodNode.visitTypeInsn(NEW, exceptionBcnf);
        methodNode.visitInsn(DUP);
        methodNode.visitLdcInsn(Type.getType(bd.getType()));
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitMethodInsn(INVOKESPECIAL, exceptionBcnf, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, String.class), false);
        methodNode.visitInsn(ATHROW);
        methodNode.visitLabel(labelNoneNull);
        // use ClassWriter.COMPUTE_FRAMES // methodNode.visitFrame(Opcodes.F_APPEND, 1, new Object[] { "cn/featherfly/common/bean/Property" }, 0, null);
        methodNode.visitVarInsn(ALOAD, 2);
        methodNode.visitInsn(ARETURN);

        methodNode.visitEnd();
        return methodNode;
    }

    // ****************************************************************************************************************
    //  getProperty(String)
    // ****************************************************************************************************************
    private MethodNode methodGetProperties(ClassNode classNode) throws NoSuchMethodException, SecurityException {
        final String methodName = "getProperties";

        String methodDescriptor = Type.getMethodDescriptor(PropertyAccessor.class.getMethod(methodName));

        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitFieldInsn(GETFIELD, classNode.name, PROPERTY_MAP, MAP_DESCRIPTOR);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, MAP_NAME, "values",
            Type.getMethodDescriptor(Type.getType(Collection.class)), false);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitFieldInsn(GETFIELD, classNode.name, PROPERTY_MAP, MAP_DESCRIPTOR);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, MAP_NAME, "size", Type.getMethodDescriptor(Type.getType(int.class)),
            false);
        methodNode.visitTypeInsn(ANEWARRAY, Type.getInternalName(Property.class));
        methodNode.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(Collection.class), "toArray",
            "([Ljava/lang/Object;)[Ljava/lang/Object;", true);
        methodNode.visitTypeInsn(CHECKCAST, "[Lcn/featherfly/common/bean/Property;");
        methodNode.visitInsn(ARETURN);

        methodNode.visitEnd();
        return methodNode;
    }

    private String createClassName(Class<?> type) {
        return type.getName() + CLASS_NAME_SUFFIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doClassLoaderReload() {
        manager.clear();
    }
}
