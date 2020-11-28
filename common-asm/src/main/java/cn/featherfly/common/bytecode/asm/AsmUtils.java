
package cn.featherfly.common.bytecode.asm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * AsmUtils
 * </p>
 *
 * @author zhongj
 */
public class AsmUtils {

    /**
     * 根据注解获取对应的参数名称
     *
     * @param method 方法信息
     * @return 方法列表
     */
    public static String[] getMethodParamNames(final Method method) {
        if (method == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        if (Lang.isEmpty(method.getParameterTypes())) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        final boolean statics = Modifier.isStatic(method.getModifiers());
        final String name = method.getName();
        final String descriptor = Type.getMethodDescriptor(method);
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Class<?> clazz = method.getDeclaringClass();

        // ASM树接口形式访问
        ClassReader cr = classReader(clazz);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);

        // 处理
        MethodNode methodNode = getMethodNode(cn, descriptor, name);

        // 构建结果
        return CollectionUtils.toArray(getParamNames(methodNode, statics), String.class);
        //                return Instances.singleton(AsmMethodParamName.class).getParamNames(methodMeta);
    }

    private static ClassReader classReader(Class<?> clazz) {
        try {
            return new ClassReader(clazz.getName());
        } catch (IOException e) {
            // TODO 异常处理
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取匹配的方法节点
     *
     * @param classNode  类阶段
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
     * @param isStatic  是否为静态
     * @return 结果列表
     */
    @SuppressWarnings("unchecked")
    private static List<String> getParamNames(final MethodNode asmMethod, final boolean isStatic) {
        List<LocalVariableNode> localVariableNodes = asmMethod.localVariables;

        // TreeMap能够把它保存的记录根据key排序,默认是按升序排序
        Map<Integer, String> names = new TreeMap<>();

        for (LocalVariableNode variableNode : localVariableNodes) {
            // index-记录了正确的方法本地变量索引。
            // (方法本地变量顺序可能会被打乱。而index记录了原始的顺序)
            int index = variableNode.index;
            String name = variableNode.name;
            // 非静态方法,第一个参数是this
            if (!isThisVarName(isStatic, variableNode)) {
                names.put(index, name);
            }
        }
        return new ArrayList<>(names.values());
    }

    /**
     * 如果是引用类型（非静态方法），第一个参数为 this(指代本身)
     *
     * @param isStatic     是否为静态
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
