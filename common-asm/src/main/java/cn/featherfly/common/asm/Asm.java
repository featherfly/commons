
package cn.featherfly.common.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.DLOAD;
import static org.objectweb.asm.Opcodes.DRETURN;
import static org.objectweb.asm.Opcodes.FLOAD;
import static org.objectweb.asm.Opcodes.FRETURN;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.LLOAD;
import static org.objectweb.asm.Opcodes.LRETURN;
import static org.objectweb.asm.Opcodes.RETURN;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.ParameterNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * <p>
 * AsmUtils
 * </p>
 * .
 *
 * @author zhongj
 */
public class Asm {

    private static final Map<Executable, String[]> METHOD_PARAMS = new HashMap<>();

    private static final int SPACE_LEN = 20;

    /** The Constant CONSTRUCT_METHOD. */
    public static final String CONSTRUCT_METHOD = "<init>";

    /** The Constant PRIMITIVE_BOXING_METHOD. */
    public static final String PRIMITIVE_BOXING_METHOD = "valueOf";
    /**
     * The Constant PRIMITIVE_WRAPPER_METHOD.
     *
     * @deprecated {@link #PRIMITIVE_BOXING_METHOD}
     */
    @Deprecated
    public static final String PRIMITIVE_WRAPPER_METHOD = PRIMITIVE_BOXING_METHOD;

    /** The Constant NONE_PARAMETER_DESCRIPTOR. */
    public static final String NONE_PARAMETER_DESCRIPTOR = "()V";

    /** The Constant OPCODE_MAP. */
    private static final Map<Integer, String> OPCODE_MAP = new HashMap<>();

    /** The Constant PRIMITIVE_UNBOXING_METHOD. */
    private static final Map<String, String> PRIMITIVE_UNBOXING_METHOD = new HashMap<>();

    /** The Constant PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR. */
    private static final Map<String, String> PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR = new HashMap<>();

    /** The Constant PRIMITIVE_BOXING_METHOD_DESCRIPTOR. */
    private static final Map<String, String> PRIMITIVE_BOXING_METHOD_DESCRIPTOR = new HashMap<>();

    /** The Constant PRIMITIVE_WRAPPER. */
    private static final Map<String, Class<?>> PRIMITIVE_WRAPPER = new HashMap<>();

    private static final Map<String, Type> PRIMITIVE_TYPE = new HashMap<>();

    /** The Constant WRAPPER_PRIMITIVE. */
    private static final Map<Class<?>, Class<?>> WRAPPER_PRIMITIVE = new HashMap<>();

    static {
        boolean start = false;
        for (Field field : Opcodes.class.getFields()) {
            if (field.getName().equals("NOP")) {
                start = true;
            }
            if (start) {
                Integer value = (Integer) ClassUtils.getFieldValue(Opcodes.class, field.getName());
                OPCODE_MAP.put(value, field.getName().toLowerCase());
            }
        }

        PRIMITIVE_UNBOXING_METHOD.put(Boolean.TYPE.getName(), "booleanValue");
        PRIMITIVE_UNBOXING_METHOD.put(Character.TYPE.getName(), "charValue");
        PRIMITIVE_UNBOXING_METHOD.put(Byte.TYPE.getName(), "byteValue");
        PRIMITIVE_UNBOXING_METHOD.put(Short.TYPE.getName(), "shortValue");
        PRIMITIVE_UNBOXING_METHOD.put(Integer.TYPE.getName(), "intValue");
        PRIMITIVE_UNBOXING_METHOD.put(Long.TYPE.getName(), "longValue");
        PRIMITIVE_UNBOXING_METHOD.put(Float.TYPE.getName(), "floatValue");
        PRIMITIVE_UNBOXING_METHOD.put(Double.TYPE.getName(), "doubleValue");

        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Boolean.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Boolean.class, "booleanValue")));
        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Character.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Character.class, "charValue")));
        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Byte.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Byte.class, "byteValue")));
        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Short.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Short.class, "shortValue")));
        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Integer.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Integer.class, "intValue")));
        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Long.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Long.class, "longValue")));
        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Float.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Float.class, "floatValue")));
        PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.put(Double.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Double.class, "doubleValue")));

        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Boolean.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Boolean.class, PRIMITIVE_WRAPPER_METHOD, Boolean.TYPE)));
        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Character.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Character.class, PRIMITIVE_WRAPPER_METHOD, Character.TYPE)));
        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Byte.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Byte.class, PRIMITIVE_WRAPPER_METHOD, Byte.TYPE)));
        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Short.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Short.class, PRIMITIVE_WRAPPER_METHOD, Short.TYPE)));
        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Integer.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Integer.class, PRIMITIVE_WRAPPER_METHOD, Integer.TYPE)));
        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Long.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Long.class, PRIMITIVE_WRAPPER_METHOD, Long.TYPE)));
        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Float.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Float.class, PRIMITIVE_WRAPPER_METHOD, Float.TYPE)));
        PRIMITIVE_BOXING_METHOD_DESCRIPTOR.put(Double.TYPE.getName(),
            Type.getMethodDescriptor(ClassUtils.getMethod(Double.class, PRIMITIVE_WRAPPER_METHOD, Double.TYPE)));

        PRIMITIVE_WRAPPER.put(Boolean.TYPE.getName(), Boolean.class);
        PRIMITIVE_WRAPPER.put(Character.TYPE.getName(), Character.class);
        PRIMITIVE_WRAPPER.put(Byte.TYPE.getName(), Byte.class);
        PRIMITIVE_WRAPPER.put(Short.TYPE.getName(), Short.class);
        PRIMITIVE_WRAPPER.put(Integer.TYPE.getName(), Integer.class);
        PRIMITIVE_WRAPPER.put(Long.TYPE.getName(), Long.class);
        PRIMITIVE_WRAPPER.put(Float.TYPE.getName(), Float.class);
        PRIMITIVE_WRAPPER.put(Double.TYPE.getName(), Double.class);
        PRIMITIVE_WRAPPER.put(Void.TYPE.getName(), Void.class);

        WRAPPER_PRIMITIVE.put(Boolean.class, Boolean.TYPE);
        WRAPPER_PRIMITIVE.put(Character.class, Character.TYPE);
        WRAPPER_PRIMITIVE.put(Byte.class, Byte.TYPE);
        WRAPPER_PRIMITIVE.put(Short.class, Short.TYPE);
        WRAPPER_PRIMITIVE.put(Integer.class, Integer.TYPE);
        WRAPPER_PRIMITIVE.put(Long.class, Long.TYPE);
        WRAPPER_PRIMITIVE.put(Float.class, Float.TYPE);
        WRAPPER_PRIMITIVE.put(Double.class, Double.TYPE);
        WRAPPER_PRIMITIVE.put(Void.class, Void.TYPE);

        PRIMITIVE_TYPE.put(Boolean.TYPE.getName(), Type.BOOLEAN_TYPE);
        PRIMITIVE_TYPE.put(Character.TYPE.getName(), Type.CHAR_TYPE);
        PRIMITIVE_TYPE.put(Byte.TYPE.getName(), Type.BYTE_TYPE);
        PRIMITIVE_TYPE.put(Short.TYPE.getName(), Type.SHORT_TYPE);
        PRIMITIVE_TYPE.put(Integer.TYPE.getName(), Type.INT_TYPE);
        PRIMITIVE_TYPE.put(Long.TYPE.getName(), Type.LONG_TYPE);
        PRIMITIVE_TYPE.put(Float.TYPE.getName(), Type.FLOAT_TYPE);
        PRIMITIVE_TYPE.put(Double.TYPE.getName(), Type.DOUBLE_TYPE);
    }

    /**
     * Opcode name.
     *
     * @param opcode the opcode
     * @return the string
     */
    public static String opcodeName(int opcode) {
        return opcodeName(opcode, false);
    }

    private static String opcodeName(int opcode, boolean appendSpace) {
        String result = OPCODE_MAP.get(opcode);
        if (result == null) {
            result = opcode + "";
        }
        if (appendSpace) {
            int appendSize = SPACE_LEN - result.length();
            for (int i = 0; i < appendSize; i++) {
                result += " ";
            }
        }
        return result;
    }

    /**
     * Javap string.
     *
     * @param abstractInsnNode the abstract insn node
     * @return the string
     */
    public static String javapString(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode instanceof VarInsnNode) {
            VarInsnNode node = (VarInsnNode) abstractInsnNode;
            return opcodeName(node.getOpcode()) + "_" + node.var;
        } else if (abstractInsnNode instanceof FieldInsnNode) {
            FieldInsnNode node = (FieldInsnNode) abstractInsnNode;
            return opcodeName(node.getOpcode(), true) + "// Field " + node.name + ":" + node.desc;
        } else if (abstractInsnNode instanceof TypeInsnNode) {
            TypeInsnNode node = (TypeInsnNode) abstractInsnNode;
            return opcodeName(node.getOpcode(), true) + "// class " + node.desc;
        } else if (abstractInsnNode instanceof InsnNode) {
            InsnNode node = (InsnNode) abstractInsnNode;
            return opcodeName(node.getOpcode());
        } else if (abstractInsnNode instanceof LdcInsnNode) {
            LdcInsnNode node = (LdcInsnNode) abstractInsnNode;
            return opcodeName(node.getOpcode(), true) + "// " + node.cst.getClass().getSimpleName() + " " + node.cst;
        } else if (abstractInsnNode instanceof MethodInsnNode) {
            MethodInsnNode node = (MethodInsnNode) abstractInsnNode;
            if (node.itf) {
                return opcodeName(node.getOpcode(), true) + "// InterfaceMethod " + node.owner + "." + node.name + ":"
                    + node.desc;
            } else {
                return opcodeName(node.getOpcode(), true) + "// Method " + node.owner + "." + node.name + ":"
                    + node.desc;
            }
        }
        return abstractInsnNode.getClass().getName() + " " + opcodeName(abstractInsnNode.getOpcode()) + " "
            + abstractInsnNode.getOpcode();
    }

    /**
     * Gets the method descriptor.
     *
     * @param type the type
     * @return the string
     */
    public static String _getMethodDescriptor(Class<?> type) {
        return Type.getDescriptor(type);
    }

    /**
     * Gets the constructor descriptor.
     *
     * @return the constructor descriptor
     */
    public static String getConstructorDescriptor() {
        return NONE_PARAMETER_DESCRIPTOR;
    }

    /**
     * Gets the constructor descriptor.
     *
     * @param paramTypes the param types
     * @return the constructor descriptor
     */
    public static String getConstructorDescriptor(Class<?>... paramTypes) {
        if (Lang.isEmpty(paramTypes)) {
            return NONE_PARAMETER_DESCRIPTOR;
        }
        StringBuilder params = new StringBuilder();
        for (Class<?> type : paramTypes) {
            params.append(_getMethodDescriptor(type));
        }
        return Strings.format("({0})V", params.toString());
    }

    /**
     * Gets the constructor descriptor.
     *
     * @param paramTypeDescriptors the param type descriptors
     * @return the constructor descriptor
     */
    public static String getConstructorDescriptor(String... paramTypeDescriptors) {
        if (Lang.isEmpty(paramTypeDescriptors)) {
            return NONE_PARAMETER_DESCRIPTOR;
        }
        StringBuilder params = new StringBuilder();
        for (String descriptors : paramTypeDescriptors) {
            params.append(descriptors);
        }
        return Strings.format("({0})V", params.toString());
    }

    /**
     * Gets the name.
     *
     * @param type the type
     * @return the name
     */
    public static String getName(Class<?> type) {
        return getName(type.getName());
    }

    /**
     * Gets the name.
     *
     * @param className the class name
     * @return the name
     */
    public static String getName(String className) {
        return className.replace('.', '/');
    }

    /**
     * Gets the return code.
     *
     * @param type the type
     * @return the return code
     */
    public static int getReturnCode(Class<?> type) {
        if (type.isPrimitive()) {
            return getPrimitiveReturnCode(type);
        } else {
            return ARETURN;
        }
    }

    /**
     * Gets the primitive return code.
     *
     * @param primitiveType the primitive type
     * @return the primitive return code
     */
    public static int getPrimitiveReturnCode(Class<?> primitiveType) {
        if (primitiveType == Integer.TYPE) {
            return IRETURN;
        } else if (primitiveType == Byte.TYPE) {
            return IRETURN;
        } else if (primitiveType == Short.TYPE) {
            return IRETURN;
        } else if (primitiveType == Character.TYPE) {
            return IRETURN;
        } else if (primitiveType == Boolean.TYPE) {
            return IRETURN;
        } else if (primitiveType == Long.TYPE) {
            return LRETURN;
        } else if (primitiveType == Double.TYPE) {
            return DRETURN;
        } else if (primitiveType == Float.TYPE) {
            return FRETURN;
        } else {
            return RETURN;
        }
    }

    /**
     * Gets the primitive return code.
     *
     * @param primitiveType the primitive type
     * @return the primitive return code
     */
    public static int getLoadCode(Class<?> primitiveType) {
        if (primitiveType == Integer.TYPE) {
            return ILOAD;
        } else if (primitiveType == Byte.TYPE) {
            return ILOAD;
        } else if (primitiveType == Short.TYPE) {
            return ILOAD;
        } else if (primitiveType == Character.TYPE) {
            return ILOAD;
        } else if (primitiveType == Boolean.TYPE) {
            return ILOAD;
        } else if (primitiveType == Long.TYPE) {
            return LLOAD;
        } else if (primitiveType == Double.TYPE) {
            return DLOAD;
        } else if (primitiveType == Float.TYPE) {
            return FLOAD;
        } else {
            return ALOAD;
        }
    }

    /**
     * Gets the primitive unboxing method name.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     */
    public static String getPrimitiveUnboxingMethod(String type) {
        return PRIMITIVE_UNBOXING_METHOD.get(type);
    }

    /**
     * Gets the primitive unboxing method name.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     */
    public static String getPrimitiveUnboxingMethod(Class<?> type) {
        return PRIMITIVE_UNBOXING_METHOD.get(type.getName());
    }

    /**
     * Gets the primitive unboxing method descriptor.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     */
    public static String getPrimitiveUnboxingMethodDescriptor(String type) {
        return PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.get(type);
    }

    /**
     * Gets the primitive unboxing method descriptor.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     */
    public static String getPrimitiveUnboxingMethodDescriptor(Class<?> type) {
        return PRIMITIVE_UNBOXING_METHOD_DESCRIPTOR.get(type.getName());
    }

    /**
     * Gets the primitive boxing method descriptor.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     */
    public static String getPrimitiveBoxingMethodDescriptor(String type) {
        return PRIMITIVE_BOXING_METHOD_DESCRIPTOR.get(type);
    }

    /**
     * Gets the primitive boxing method descriptor.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     */
    public static String getPrimitiveBoxingMethodDescriptor(Class<?> type) {
        return PRIMITIVE_BOXING_METHOD_DESCRIPTOR.get(type.getName());
    }

    /**
     * Gets the primitive wrapper method descriptor.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     * @deprecated {@link #getPrimitiveBoxingMethodDescriptor(String)}
     */
    @Deprecated
    public static String getPrimitiveWrapperMethodDescriptor(String type) {
        return PRIMITIVE_BOXING_METHOD_DESCRIPTOR.get(type);
    }

    /**
     * Gets the primitive wrapper method descriptor.
     *
     * @param type the type
     * @return the primitive wrapper method descriptor
     * @deprecated {@link #getPrimitiveBoxingMethodDescriptor(Class)}
     */
    @Deprecated
    public static String getPrimitiveWrapperMethodDescriptor(Class<?> type) {
        return PRIMITIVE_BOXING_METHOD_DESCRIPTOR.get(type.getName());
    }

    public static Type getType(Class<?> type) {
        Type t = PRIMITIVE_TYPE.get(type.getName());
        if (t != null) {
            return t;
        }
        return Type.getType(type);
    }

    public static Type getType(String type) {
        Type t = PRIMITIVE_TYPE.get(type);
        if (t != null) {
            return t;
        }
        return Type.getObjectType(type);
    }

    /**
     * Checks if is primitive.
     *
     * @param type the type
     * @return true, if is primitive
     */
    public static boolean isPrimitive(String type) {
        return PRIMITIVE_WRAPPER.containsKey(type);
    }

    /**
     * Gets the primitive wrapper name.
     *
     * @param type the type
     * @return the primitive wrapper name
     */
    public static String getPrimitiveWrapperName(String type) {
        Class<?> c = PRIMITIVE_WRAPPER.get(type);
        if (c == null) {
            return getName(type);
        } else {
            return Type.getInternalName(c);
        }
    }

    /**
     * Gets the primitive wrapper name.
     *
     * @param type the type
     * @return the primitive wrapper name
     */
    public static String getPrimitiveWrapperName(Class<?> type) {
        return getPrimitiveWrapperName(type.getName());
    }

    /**
     * Gets the constructor or method param names.
     *
     * @param executable constructor or method
     * @return the param names
     */
    public static String[] getParamNames(final Executable executable) {
        AssertIllegalArgument.isNotNull(executable, "executable");
        String[] names = METHOD_PARAMS.get(executable);
        if (names == null) {
            names = new String[executable.getParameterCount()];
            if (executable instanceof Constructor) {
                names = _getConstructorParamNames((Constructor<?>) executable);
            } else if (executable instanceof Method) {
                names = _getMethodParamNames((Method) executable);
            } else {
                // 截止jdk15应该不会走到这里
                throw new UnsupportedException("not suppport for " + executable.getClass().getName());
            }
            METHOD_PARAMS.put(executable, names);
        }
        return names;

    }

    /**
     * Gets the method param names.
     *
     * @param method the method
     * @return the method param names
     */
    private static String[] _getMethodParamNames(final Method method) {
        if (method == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        if (Lang.isEmpty(method.getParameterTypes())) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        final boolean statics = Modifier.isStatic(method.getModifiers());
        final String name = method.getName();
        final String descriptor = Type.getMethodDescriptor(method);
        //        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Class<?> clazz = method.getDeclaringClass();

        // ASM树接口形式访问
        ClassReader cr = classReader(clazz);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);

        // 处理
        MethodNode methodNode = getMethodNode(cn, descriptor, name);

        // 构建结果
        return getParamNames(methodNode, statics);
    }

    /**
     * Gets the constructor param names.
     *
     * @param constructor the constructor
     * @return the constructor param names
     */
    private static String[] _getConstructorParamNames(final Constructor<?> constructor) {
        if (constructor == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        if (Lang.isEmpty(constructor.getParameterTypes())) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        final boolean statics = Modifier.isStatic(constructor.getModifiers());
        final String descriptor = Type.getConstructorDescriptor(constructor);
        final Class<?> clazz = constructor.getDeclaringClass();

        // ASM树接口形式访问
        ClassReader cr = classReader(clazz);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);
        // 处理
        MethodNode methodNode = getMethodNode(cn, descriptor, CONSTRUCT_METHOD);
        // 构建结果
        return getParamNames(methodNode, statics);
    }

    private static ClassReader classReader(Class<?> clazz) {
        try {
            return new ClassReader(clazz.getName());
        } catch (IOException e) {
            throw new cn.featherfly.common.exception.IOException(e);
        }
    }

    /**
     * 获取匹配的方法节点
     *
     * @param classNode 类阶段
     * @param methodMeta 方法元信息
     * @return 方法节点
     */
    private static MethodNode getMethodNode(final ClassNode classNode, final String methodDescriptor,
        final String methodName) {
        List<MethodNode> methods = classNode.methods;
        for (MethodNode asmMethod : methods) {
            // 验证方法签名
            if (asmMethod.desc.equals(methodDescriptor) && asmMethod.name.equals(methodName)) {
                return asmMethod;
            }
        }
        // 这里理论上是不会走到的
        //   TODO 异常处理
        throw new RuntimeException("Method not found!");
    }

    /**
     * 获排序后的方法参数
     *
     * @param asmMethod 方法信息
     * @param isStatic 是否为静态
     * @return 结果列表
     */
    private static String[] getParamNames(final MethodNode asmMethod, final boolean isStatic) {
        // TreeMap能够把它保存的记录根据key排序,默认是按升序排序
        if (asmMethod.parameters != null) {
            String[] names = new String[asmMethod.parameters.size()];
            int index = 0;
            for (ParameterNode parameterNode : asmMethod.parameters) {
                names[index] = parameterNode.name;
                index++;
            }
            return names;
        } else if (asmMethod.localVariables != null) {
            Map<Integer, String> names = new TreeMap<>();
            for (LocalVariableNode variableNode : asmMethod.localVariables) {
                // index-记录了正确的方法本地变量索引。
                // (方法本地变量顺序可能会被打乱。而index记录了原始的顺序)
                int index = variableNode.index;
                String name = variableNode.name;
                // 非静态方法,第一个参数是this
                if (!isThisVarName(isStatic, variableNode)) {
                    names.put(index, name);
                }
            }
            return names.values().toArray(new String[names.size()]);
        } else {
            // 如果是interface或抽象类，没有开启-parameter编译，则asmMethod.localVariables为空，就拿不到方法参数名
            return null;
        }
    }

    /**
     * 如果是引用类型（非静态方法），第一个参数为 this(指代本身)
     *
     * @param isStatic 是否为静态
     * @param variableNode 变量节点
     * @return 是否为 this 参数
     */
    private static boolean isThisVarName(final boolean isStatic, final LocalVariableNode variableNode) {
        if (isStatic) {
            return false;
        }
        int index = variableNode.index;
        String name = variableNode.name;
        return 0 == index && "this".equals(name);
    }
}
