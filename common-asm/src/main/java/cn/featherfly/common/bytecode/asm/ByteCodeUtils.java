
package cn.featherfly.common.bytecode.asm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * <p>
 * AsmUtils
 * </p>
 *
 * @author zhongj
 */
public class ByteCodeUtils {

    private static final int SPACE_LEN = 20;

    public static final String CONSTRUCT_METHOD = "<init>";

    public static final String NONE_PARAMETER_DESCRIPTOR = "()V";

    public static final Map<Integer, String> OPCODE_MAP = new HashMap<>();

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
    }

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

    public static String _getMethodDescriptor(Class<?> type) {
        return Type.getDescriptor(type);
    }

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

    public static String getName(Class<?> type) {
        return getName(type.getName());
    }

    public static String getName(String className) {
        return className.replace('.', '/');
    }
}
