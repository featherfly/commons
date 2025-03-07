
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:49:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.policy.AllowDenyListPolicy;
import cn.featherfly.common.policy.AllowDenyListPolicy.Strategy;
import cn.featherfly.common.policy.AllowPolicy;

/**
 * asm property accessor factory.
 *
 * @author zhongj
 */
// ENHANCE 生成类的引用以ClassLoader为key,这样多个AsmPropertyAccessorFactory实例，只要是同一个classloader，就不会重复创建了
public class AsmPropertyAccessorFactory extends ReloadableClassloader implements PropertyAccessorFactory, Opcodes {

    /** The Constant CLASS_NAME_SUFFIX. */
    public static final String CLASS_NAME_SUFFIX = "PropertyVisitorCreateByFeatherfly";

    private static final String STRING_TYPE = Type.getInternalName(String.class);
    private static final String HASH_CODE_NAME = "hashCode";
    private static final String EQUALS_NAME = "equals";
    private static final String EQUALS_DESCRIPTOR = "(Ljava/lang/Object;)Z";

    private static final String PROPERTIES_ARRAY_NAME = "properties";
    private static final String PROPERTIES_ARRAY_DESCRIPTOR = "[Lcn/featherfly/common/bean/Property;";
    private static final String PROPERTIES_ARRAY_SIGNATURE = "[Lcn/featherfly/common/bean/Property<Lvo/Teacher;*>;";

    //    private static final String PROPERTY_MAP = "propertyMap";
    //    private static final String MAP_NAME = Type.getInternalName(LinkedHashMap.class);
    //    private static final String MAP_DESCRIPTOR = Type.getDescriptor(LinkedHashMap.class);

    private static final String GET = "get";
    private static final String SET = "set";

    //    private static final String MAP_PUT_NAME = "put";
    //    private static final String MAP_PUT_DESCRIPTOR = Type.getMethodDescriptor(Type.getType(Object.class),
    //        Type.getType(Object.class), Type.getType(Object.class));

    private static final String GET_PROPERTY_METHOD = "getProperty";
    private static final String GET_PROPERTY_VALUE_METHOD = "getPropertyValue";
    private static final String SET_PROPERTY_VALUE_METHOD = "setPropertyValue";

    private boolean debug = false;

    private final BytesClassLoader bytesClassLoader;

    private final AsmPropertyFactory propertyFactory;

    private final PropertyAccessorManagerImpl manager;

    private final boolean cascade;

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
     * Instantiates a new asm instantiator factory.
     *
     * @param classLoader the class loader
     * @param cascade the cascade
     */
    public AsmPropertyAccessorFactory(ClassLoader classLoader, boolean cascade) {
        this(classLoader, defaultPolicy(), cascade);
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
     * @param unary the unary
     * @param cascade the cascade
     */
    public AsmPropertyAccessorFactory(ClassLoader classLoader,
        UnaryOperator<AllowDenyListPolicy<java.lang.reflect.Type>> unary, boolean cascade) {
        this(classLoader, unary.apply(defaultPolicy()), cascade);
    }

    /**
     * Instantiates a new instantiator factor.
     *
     * @param classLoader the class loader
     * @param propertyVisitorCascadeCreatePolicy the property visitor cascade create policy
     */
    public AsmPropertyAccessorFactory(ClassLoader classLoader,
        AllowPolicy<java.lang.reflect.Type> propertyVisitorCascadeCreatePolicy) {
        this(classLoader, propertyVisitorCascadeCreatePolicy, true);
    }

    /**
     * Instantiates a new instantiator factor.
     *
     * @param classLoader the class loader
     * @param propertyVisitorCascadeCreatePolicy the property visitor cascade create policy
     * @param cascade the cascade
     */
    public AsmPropertyAccessorFactory(ClassLoader classLoader,
        AllowPolicy<java.lang.reflect.Type> propertyVisitorCascadeCreatePolicy, boolean cascade) {
        super();
        bytesClassLoader = new BytesClassLoader(classLoader);
        propertyFactory = new AsmPropertyFactory(bytesClassLoader);

        this.propertyVisitorCascadeCreatePolicy = propertyVisitorCascadeCreatePolicy;
        this.cascade = cascade;
        manager = new PropertyAccessorManagerImpl();
    }

    // TODO 后续添加build模式
    /**
     * get debug value
     *
     * @return debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * set debug value
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
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
            if (cascade) {
                createPropertyAccessorRecursion(propertyAccessor, classLoader);
            }
            return propertyAccessor;
        } catch (Exception e) {
            throw new AsmException(e);
        }
    }

    /**
     * Creates a new AsmPropertyAccessor object.
     *
     * @param classLoader the class loader
     */
    @Override
    public void createPropertyAccessorCascade(ClassLoader classLoader) {
        for (PropertyAccessor<?> propertyAccessor : new ArrayList<>(manager.getAll())) {
            createPropertyAccessorRecursion(propertyAccessor, classLoader);
        }
    }

    private void createPropertyAccessorRecursion(PropertyAccessor<?> propertyAccessor, ClassLoader classLoader) {
        for (Property<?, ?> property : propertyAccessor.getProperties()) {
            createPropertyAccessorRecursion(property, classLoader);
        }
    }

    @SuppressWarnings("unchecked")
    private <V> void createPropertyAccessorRecursion(Property<?, V> property, ClassLoader classLoader) {
        if (property.getPropertyAccessor() == null && propertyVisitorCascadeCreatePolicy.isAllow(property.getType())) {
            if (manager.containsType(property.getType())) {
                ((AbstractProperty<?, V>) property)
                    .setPropertyAccessor((PropertyAccessor<V>) manager.get(property.getType()));
            } else {
                PropertyAccessor<V> newPv = create(property.getType(), classLoader);
                ((AbstractProperty<?, V>) property).setPropertyAccessor(newPv);
                createPropertyAccessorRecursion(newPv, classLoader);
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

        // all property
        BeanDescriptor<T> bd = BeanDescriptor.getBeanDescriptor(type);
        int i = 0;
        List<NewPropertyType> newPropertyTypeList = new ArrayList<>();
        for (BeanProperty<T, ?> beanProperty : bd.getBeanProperties()) {
            Class<?> propertyType = propertyFactory.create(createdClassName, type, beanProperty.getType(),
                beanProperty.getName(), i, classLoader);
            i++;

            FieldNode fieldNode = new FieldNode(ACC_PRIVATE | ACC_FINAL, beanProperty.getName(),
                Type.getDescriptor(propertyType), null, null);
            classNode.fields.add(fieldNode);

            newPropertyTypeList.add(new NewPropertyType(propertyType, beanProperty, fieldNode));
        }

        classNode.fields.add(new FieldNode(ACC_PRIVATE | ACC_FINAL, PROPERTIES_ARRAY_NAME, PROPERTIES_ARRAY_DESCRIPTOR,
            PROPERTIES_ARRAY_SIGNATURE, null));

        // constructor
        classNode.methods.add(constructor(classNode, newPropertyTypeList));

        // instantiate method
        // instantiate
        classNode.methods.add(AsmInstantiatorFactory.createMethodInstantiate(instantiateType));
        // getType
        classNode.methods.add(AsmInstantiatorFactory.createMethodGetType(type));

        // PropertyVisitor method
        NewPropertyType[] sortedNewPropertyTypes = newPropertyTypeList.stream().sorted( //
            (o1, o2) -> Integer.compare(o1.property.getName().hashCode(), o2.property.getName().hashCode()))
            .toArray(num -> new NewPropertyType[num]);
        classNode.methods.add(methodGetPropertyByIndex(classNode, newPropertyTypeList, bd));
        classNode.methods.add(methodGetPropertyByName(classNode, sortedNewPropertyTypes, bd));
        classNode.methods.addAll(methodGetPropertyValueByIndex(classNode, newPropertyTypeList, bd));
        classNode.methods.addAll(methodGetPropertyValueByName(classNode, sortedNewPropertyTypes, bd));
        classNode.methods.addAll(methodSetPropertyValueByIndex(classNode, newPropertyTypeList, bd));
        classNode.methods.addAll(methodSetPropertyValueByName(classNode, sortedNewPropertyTypes, bd));
        classNode.methods.add(methodGetProperties(classNode));

        classNode.accept(cw);
        byte[] code = cw.toByteArray();

        if (debug) {
            try {
                FileOutputStream os = new FileOutputStream(new File("bin/" + createdClassName + ".class"));
                os.write(code);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return (Class<PropertyAccessor<T>>) ClassLoaderUtils.defineClass(classLoader, createdClassByteCodeName, code,
            type.getProtectionDomain(),
            () -> bytesClassLoader.defineClass(createdClassName, code, type.getProtectionDomain()));
    }

    private MethodNode constructor(ClassNode classNode, List<NewPropertyType> propertyTypes) {
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, Asm.CONSTRUCT_METHOD, Asm.getConstructorDescriptor(),
            null, null);
        methodNode.visitVarInsn(ALOAD, 0);
        methodNode.visitMethodInsn(INVOKESPECIAL, classNode.superName, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(), false);

        // properties array
        methodNode.visitVarInsn(ALOAD, 0);
        setInt(methodNode, propertyTypes.size());

        methodNode.visitTypeInsn(ANEWARRAY, Type.getInternalName(Property.class));
        methodNode.visitFieldInsn(PUTFIELD, classNode.name, PROPERTIES_ARRAY_NAME, PROPERTIES_ARRAY_DESCRIPTOR);

        // property
        int i = 0;
        for (NewPropertyType pt : propertyTypes) {
            // set field
            Class<?> propertyType = pt.propertyType;
            FieldNode field = classNode.fields.get(i);
            String propertyTypeBcf = Type.getInternalName(propertyType);

            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitTypeInsn(NEW, Type.getInternalName(propertyType));
            methodNode.visitInsn(DUP);
            methodNode.visitInsn(ACONST_NULL);
            methodNode.visitMethodInsn(INVOKESPECIAL, propertyTypeBcf, Asm.CONSTRUCT_METHOD,
                Asm.getConstructorDescriptor(PropertyAccessor.class), false);
            methodNode.visitFieldInsn(PUTFIELD, classNode.name, field.name, field.desc);

            // set to array
            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitFieldInsn(GETFIELD, classNode.name, PROPERTIES_ARRAY_NAME, PROPERTIES_ARRAY_DESCRIPTOR);
            setInt(methodNode, i);
            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitFieldInsn(GETFIELD, classNode.name, field.name, field.desc);
            methodNode.visitInsn(AASTORE);
            i++;
        }
        methodNode.visitInsn(RETURN);
        methodNode.visitEnd();
        return methodNode;
    }

    private void setInt(MethodNode methodNode, int size) {
        switch (size) {
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
                if (size < 32768) { // Max short / 2 
                    methodNode.visitIntInsn(SIPUSH, size);
                } else {
                    methodNode.visitLdcInsn(Strings.format("new Integer({})", size));
                }
        }
    }

    // ****************************************************************************************************************
    //  getPropertyValue(Object, int)
    // ****************************************************************************************************************
    private List<MethodNode> methodGetPropertyValueByIndex(ClassNode classNode, List<NewPropertyType> propertyTypes,
        BeanDescriptor<?> bd) throws SecurityException {
        final String methodName = GET_PROPERTY_VALUE_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(Type.getType(Object.class), Type.getType(bd.getType()),
            Type.INT_TYPE);
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitIntInsn(ILOAD, 2);
        Label[] labels = new Label[propertyTypes.size()];
        for (int i = 0; i < propertyTypes.size(); i++) {
            labels[i] = new Label();
        }
        Label labelDeft = new Label();
        methodNode.visitTableSwitchInsn(0, propertyTypes.size() - 1, labelDeft, labels);
        for (int i = 0; i < propertyTypes.size(); i++) {
            Property<?, ?> bp = propertyTypes.get(i).property;
            methodNode.visitLabel(labels[i]);
            methodNode.visitVarInsn(ALOAD, 1);
            final String prefix = bp.getType() == Boolean.TYPE ? "is" : GET;
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
    private List<MethodNode> methodGetPropertyValueByName(ClassNode classNode, NewPropertyType[] propertyTypes,
        BeanDescriptor<?> bd) throws SecurityException {
        final String methodName = GET_PROPERTY_VALUE_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(Type.getType(Object.class), Type.getType(bd.getType()),
            Type.getType(String.class));
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitVarInsn(ALOAD, 2);
        methodNode.visitInsn(DUP);
        methodNode.visitVarInsn(ASTORE, 3);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, STRING_TYPE, HASH_CODE_NAME, Type.getMethodDescriptor(Type.INT_TYPE),
            false);
        Label[] labels = new Label[propertyTypes.length];
        Label[] labelsLookUp = new Label[propertyTypes.length];
        int[] keys = new int[propertyTypes.length];
        for (int i = 0; i < propertyTypes.length; i++) {
            labels[i] = new Label();
            keys[i] = propertyTypes[i].property.getName().hashCode();
        }
        Label labelDeft = new Label();
        methodNode.visitLookupSwitchInsn(labelDeft, keys, labels);
        for (int i = 0; i < propertyTypes.length; i++) {
            Property<?, ?> bp = propertyTypes[i].property;
            methodNode.visitLabel(labels[i]);
            if (i == 0) {
                methodNode.visitFrame(Opcodes.F_APPEND, 1, new Object[] { STRING_TYPE }, 0, null);
            } else {
                methodNode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            }
            methodNode.visitVarInsn(ALOAD, 3);
            methodNode.visitLdcInsn(bp.getName());
            methodNode.visitMethodInsn(INVOKEVIRTUAL, STRING_TYPE, EQUALS_NAME, EQUALS_DESCRIPTOR, false);

            labelsLookUp[i] = new Label();
            methodNode.visitJumpInsn(IFNE, labelsLookUp[i]);
            methodNode.visitJumpInsn(GOTO, labelDeft);
        }

        for (int i = 0; i < propertyTypes.length; i++) {
            NewPropertyType npt = propertyTypes[i];
            BeanProperty<?, ?> bp = npt.property;
            methodNode.visitLabel(labelsLookUp[i]);
            methodNode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodNode.visitVarInsn(ALOAD, 1);
            final String prefix = bp.getType() == Boolean.TYPE ? "is" : GET;
            methodNode.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(bd.getType()),
                prefix + WordUtils.upperCaseFirst(bp.getName()), Type.getMethodDescriptor(Asm.getType(bp.getType())),
                false);
            if (bp.getType().isPrimitive()) { // auto wrapper primitive type
                methodNode.visitMethodInsn(INVOKESTATIC, Asm.getPrimitiveWrapperName(bp.getType().getName()),
                    Asm.PRIMITIVE_BOXING_METHOD, Asm.getPrimitiveBoxingMethodDescriptor(bp.getType().getName()), false);
            }
            methodNode.visitInsn(ARETURN);
        }

        // switch default label
        String exceptionBcnf = Type.getInternalName(NoSuchPropertyException.class);
        methodNode.visitLabel(labelDeft);
        methodNode.visitFrame(F_SAME, 0, null, 0, null);
        methodNode.visitTypeInsn(Opcodes.NEW, exceptionBcnf);
        methodNode.visitInsn(DUP);
        methodNode.visitLdcInsn(Type.getType(bd.getType()));
        methodNode.visitVarInsn(ALOAD, 2);
        methodNode.visitMethodInsn(INVOKESPECIAL, exceptionBcnf, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, String.class), false);
        methodNode.visitInsn(ATHROW);

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
    private List<MethodNode> methodSetPropertyValueByIndex(ClassNode classNode, List<NewPropertyType> propertyTypes,
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
                SET + WordUtils.upperCaseFirst(bp.getName()),
                bp.getSetter() != null ? Type.getMethodDescriptor(bp.getSetter())
                    : Type.getMethodDescriptor(Type.getType(void.class), Type.getType(bp.getType())),
                false);
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
            Type.getMethodDescriptor(Type.getType(void.class), Type.getType(Object.class), Type.getType(int.class),
                Type.getType(Object.class)),
            null, null);
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
    private List<MethodNode> methodSetPropertyValueByName(ClassNode classNode, NewPropertyType[] propertyTypes,
        BeanDescriptor<?> bd) throws SecurityException {
        final String methodName = SET_PROPERTY_VALUE_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(Type.getType(void.class), Type.getType(bd.getType()),
            Type.getType(String.class), Type.getType(Object.class));
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitVarInsn(ALOAD, 2);
        methodNode.visitInsn(DUP);
        methodNode.visitVarInsn(ASTORE, 4);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, STRING_TYPE, HASH_CODE_NAME, Type.getMethodDescriptor(Type.INT_TYPE),
            false);
        Label[] labels = new Label[propertyTypes.length];
        Label[] labelsLookUp = new Label[propertyTypes.length];
        int[] keys = new int[propertyTypes.length];
        for (int i = 0; i < propertyTypes.length; i++) {
            labels[i] = new Label();
            keys[i] = propertyTypes[i].property.getName().hashCode();
        }
        Label labelDeft = new Label();
        methodNode.visitLookupSwitchInsn(labelDeft, keys, labels);
        for (int i = 0; i < propertyTypes.length; i++) {
            Property<?, ?> bp = propertyTypes[i].property;
            methodNode.visitLabel(labels[i]);
            if (i == 0) {
                methodNode.visitFrame(Opcodes.F_APPEND, 1, new Object[] { STRING_TYPE }, 0, null);
            } else {
                methodNode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            }
            methodNode.visitVarInsn(ALOAD, 4);
            methodNode.visitLdcInsn(bp.getName());
            methodNode.visitMethodInsn(INVOKEVIRTUAL, STRING_TYPE, EQUALS_NAME, EQUALS_DESCRIPTOR, false);

            labelsLookUp[i] = new Label();
            methodNode.visitJumpInsn(IFNE, labelsLookUp[i]);
            methodNode.visitJumpInsn(GOTO, labelDeft);
        }

        for (int i = 0; i < propertyTypes.length; i++) {
            NewPropertyType npt = propertyTypes[i];
            BeanProperty<?, ?> bp = npt.property;
            methodNode.visitLabel(labelsLookUp[i]);
            methodNode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodNode.visitVarInsn(ALOAD, 1);
            methodNode.visitVarInsn(ALOAD, 3);

            methodNode.visitTypeInsn(CHECKCAST, Asm.getPrimitiveWrapperName(Type.getInternalName(bp.getType())));
            if (bp.getType().isPrimitive()) { // auto unwrapper primitive type
                methodNode.visitMethodInsn(INVOKEVIRTUAL, Asm.getPrimitiveWrapperName(bp.getType()),
                    Asm.getPrimitiveUnboxingMethod(bp.getType()),
                    Asm.getPrimitiveUnboxingMethodDescriptor(bp.getType()), false);
            }
            methodNode.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(bd.getType()),
                SET + WordUtils.upperCaseFirst(bp.getName()),
                bp.getSetter() != null ? Type.getMethodDescriptor(bp.getSetter())
                    : Type.getMethodDescriptor(Type.getType(void.class), Type.getType(bp.getType())),
                false);

            methodNode.visitInsn(RETURN);
        }

        // switch default label
        String exceptionBcnf = Type.getInternalName(NoSuchPropertyException.class);
        methodNode.visitLabel(labelDeft);
        methodNode.visitFrame(F_SAME, 0, null, 0, null);
        methodNode.visitTypeInsn(Opcodes.NEW, exceptionBcnf);
        methodNode.visitInsn(DUP);
        methodNode.visitLdcInsn(Type.getType(bd.getType()));
        methodNode.visitVarInsn(ALOAD, 2);
        methodNode.visitMethodInsn(INVOKESPECIAL, exceptionBcnf, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, String.class), false);
        methodNode.visitInsn(ATHROW);

        methodNode.visitEnd();

        // ----------------------------------------------------------------------------------------------------------------

        MethodNode methodNode2 = new MethodNode(Opcodes.ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, methodName,
            Type.getMethodDescriptor(Type.getType(void.class), Type.getType(Object.class), Type.getType(String.class),
                Type.getType(Object.class)),
            null, null);
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
    private MethodNode methodGetPropertyByIndex(ClassNode classNode, List<NewPropertyType> propertyTypes,
        BeanDescriptor<?> bd) throws NoSuchMethodException, SecurityException {
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
    private MethodNode methodGetPropertyByName(ClassNode classNode, NewPropertyType[] propertyTypes,
        BeanDescriptor<?> bd) throws NoSuchMethodException, SecurityException {
        final String methodName = GET_PROPERTY_METHOD;

        String methodDescriptor = Type.getMethodDescriptor(PropertyAccessor.class.getMethod(methodName, String.class));
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC, methodName, methodDescriptor, null, null);
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitInsn(DUP);
        methodNode.visitVarInsn(ASTORE, 2);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, STRING_TYPE, HASH_CODE_NAME, Type.getMethodDescriptor(Type.INT_TYPE),
            false);
        Label[] labels = new Label[propertyTypes.length];
        Label[] labelsLookUp = new Label[propertyTypes.length];
        int[] keys = new int[propertyTypes.length];
        for (int i = 0; i < propertyTypes.length; i++) {
            labels[i] = new Label();
            keys[i] = propertyTypes[i].property.getName().hashCode();
        }
        Label labelDeft = new Label();
        methodNode.visitLookupSwitchInsn(labelDeft, keys, labels);
        for (int i = 0; i < propertyTypes.length; i++) {
            Property<?, ?> bp = propertyTypes[i].property;
            methodNode.visitLabel(labels[i]);
            if (i == 0) {
                methodNode.visitFrame(Opcodes.F_APPEND, 1, new Object[] { STRING_TYPE }, 0, null);
            } else {
                methodNode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            }
            methodNode.visitVarInsn(ALOAD, 2);
            methodNode.visitLdcInsn(bp.getName());
            methodNode.visitMethodInsn(INVOKEVIRTUAL, STRING_TYPE, EQUALS_NAME, EQUALS_DESCRIPTOR, false);

            labelsLookUp[i] = new Label();
            methodNode.visitJumpInsn(IFNE, labelsLookUp[i]);
            methodNode.visitJumpInsn(GOTO, labelDeft);
        }

        for (int i = 0; i < propertyTypes.length; i++) {
            NewPropertyType npt = propertyTypes[i];
            methodNode.visitLabel(labelsLookUp[i]);
            methodNode.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodNode.visitVarInsn(ALOAD, 0);
            methodNode.visitFieldInsn(GETFIELD, classNode.name, npt.fieldNode.name, npt.fieldNode.desc);
            methodNode.visitInsn(ARETURN);
        }

        // switch default label
        String exceptionBcnf = Type.getInternalName(NoSuchPropertyException.class);
        methodNode.visitLabel(labelDeft);
        methodNode.visitFrame(F_SAME, 0, null, 0, null);
        methodNode.visitTypeInsn(Opcodes.NEW, exceptionBcnf);
        methodNode.visitInsn(DUP);
        methodNode.visitLdcInsn(Type.getType(bd.getType()));
        methodNode.visitVarInsn(ALOAD, 1);
        methodNode.visitMethodInsn(INVOKESPECIAL, exceptionBcnf, Asm.CONSTRUCT_METHOD,
            Asm.getConstructorDescriptor(Class.class, String.class), false);
        methodNode.visitInsn(ATHROW);

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
        methodNode.visitFieldInsn(GETFIELD, classNode.name, PROPERTIES_ARRAY_NAME, PROPERTIES_ARRAY_DESCRIPTOR);
        methodNode.visitMethodInsn(INVOKEVIRTUAL, PROPERTIES_ARRAY_DESCRIPTOR, "clone", "()Ljava/lang/Object;", false);
        methodNode.visitTypeInsn(CHECKCAST, PROPERTIES_ARRAY_DESCRIPTOR);
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

    private class NewPropertyType {

        /**
         * Instantiates a new property holder.
         *
         * @param propertyType the property type
         * @param property the property
         */
        private NewPropertyType(Class<?> propertyType, BeanProperty<?, ?> property, FieldNode fieldNode) {
            super();
            this.propertyType = propertyType;
            this.property = property;
            this.fieldNode = fieldNode;
        }

        private Class<?> propertyType;

        private BeanProperty<?, ?> property;

        private FieldNode fieldNode;
    }
}
