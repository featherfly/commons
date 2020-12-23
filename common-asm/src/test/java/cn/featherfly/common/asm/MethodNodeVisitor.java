
package cn.featherfly.common.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;
import org.objectweb.asm.tree.MethodNode;

/**
 * MethodNodeVisitor.
 *
 * @author zhongj
 */
public class MethodNodeVisitor extends MethodVisitor {

    /**
     * @param api
     */
    public MethodNodeVisitor() {
        super(Opcodes.ASM9);
    }

    /**
     * @param api
     */
    public MethodNodeVisitor(int api) {
        super(api);
    }

    final MethodNode methodNode = new MethodNode();

    /**
     * get methodNode value
     *
     * @return methodNode
     */
    public MethodNode getMethodNode() {
        return methodNode;
    }

    /**
     * @param name
     * @param access
     * @see org.objectweb.asm.tree.MethodNode#visitParameter(java.lang.String,
     *      int)
     */
    @Override
    public void visitParameter(String name, int access) {
        methodNode.visitParameter(name, access);
    }

    /**
     * @return
     * @see org.objectweb.asm.tree.MethodNode#visitAnnotationDefault()
     */
    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        return methodNode.visitAnnotationDefault();
    }

    /**
     * @param descriptor
     * @param visible
     * @return
     * @see org.objectweb.asm.tree.MethodNode#visitAnnotation(java.lang.String,
     *      boolean)
     */
    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return methodNode.visitAnnotation(descriptor, visible);
    }

    /**
     * @param typeRef
     * @param typePath
     * @param descriptor
     * @param visible
     * @return
     * @see org.objectweb.asm.tree.MethodNode#visitTypeAnnotation(int,
     *      org.objectweb.asm.TypePath, java.lang.String, boolean)
     */
    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        return methodNode.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    /**
     * @param parameterCount
     * @param visible
     * @see org.objectweb.asm.tree.MethodNode#visitAnnotableParameterCount(int,
     *      boolean)
     */
    @Override
    public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
        methodNode.visitAnnotableParameterCount(parameterCount, visible);
    }

    /**
     * @param parameter
     * @param descriptor
     * @param visible
     * @return
     * @see org.objectweb.asm.tree.MethodNode#visitParameterAnnotation(int,
     *      java.lang.String, boolean)
     */
    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
        return methodNode.visitParameterAnnotation(parameter, descriptor, visible);
    }

    /**
     * @param attribute
     * @see org.objectweb.asm.tree.MethodNode#visitAttribute(org.objectweb.asm.Attribute)
     */
    @Override
    public void visitAttribute(Attribute attribute) {
        methodNode.visitAttribute(attribute);
    }

    /**
     * @see org.objectweb.asm.tree.MethodNode#visitCode()
     */
    @Override
    public void visitCode() {
        methodNode.visitCode();
    }

    /**
     * @param type
     * @param numLocal
     * @param local
     * @param numStack
     * @param stack
     * @see org.objectweb.asm.tree.MethodNode#visitFrame(int, int,
     *      java.lang.Object[], int, java.lang.Object[])
     */
    @Override
    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        methodNode.visitFrame(type, numLocal, local, numStack, stack);
    }

    /**
     * @param opcode
     * @see org.objectweb.asm.tree.MethodNode#visitInsn(int)
     */
    @Override
    public void visitInsn(int opcode) {
        methodNode.visitInsn(opcode);
    }

    /**
     * @param opcode
     * @param operand
     * @see org.objectweb.asm.tree.MethodNode#visitIntInsn(int, int)
     */
    @Override
    public void visitIntInsn(int opcode, int operand) {
        methodNode.visitIntInsn(opcode, operand);
    }

    /**
     * @param opcode
     * @param var
     * @see org.objectweb.asm.tree.MethodNode#visitVarInsn(int, int)
     */
    @Override
    public void visitVarInsn(int opcode, int var) {
        methodNode.visitVarInsn(opcode, var);
    }

    /**
     * @param opcode
     * @param type
     * @see org.objectweb.asm.tree.MethodNode#visitTypeInsn(int,
     *      java.lang.String)
     */
    @Override
    public void visitTypeInsn(int opcode, String type) {
        methodNode.visitTypeInsn(opcode, type);
    }

    /**
     * @param opcode
     * @param owner
     * @param name
     * @param descriptor
     * @see org.objectweb.asm.tree.MethodNode#visitFieldInsn(int,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        methodNode.visitFieldInsn(opcode, owner, name, descriptor);
    }

    /**
     * @param opcodeAndSource
     * @param owner
     * @param name
     * @param descriptor
     * @param isInterface
     * @see org.objectweb.asm.tree.MethodNode#visitMethodInsn(int,
     *      java.lang.String, java.lang.String, java.lang.String, boolean)
     */
    @Override
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor,
            boolean isInterface) {
        methodNode.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
    }

    /**
     * @param name
     * @param descriptor
     * @param bootstrapMethodHandle
     * @param bootstrapMethodArguments
     * @see org.objectweb.asm.tree.MethodNode#visitInvokeDynamicInsn(java.lang.String,
     *      java.lang.String, org.objectweb.asm.Handle, java.lang.Object[])
     */
    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle,
            Object... bootstrapMethodArguments) {
        methodNode.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    /**
     * @param opcode
     * @param label
     * @see org.objectweb.asm.tree.MethodNode#visitJumpInsn(int,
     *      org.objectweb.asm.Label)
     */
    @Override
    public void visitJumpInsn(int opcode, Label label) {
        methodNode.visitJumpInsn(opcode, label);
    }

    /**
     * @param label
     * @see org.objectweb.asm.tree.MethodNode#visitLabel(org.objectweb.asm.Label)
     */
    @Override
    public void visitLabel(Label label) {
        methodNode.visitLabel(label);
    }

    /**
     * @param value
     * @see org.objectweb.asm.tree.MethodNode#visitLdcInsn(java.lang.Object)
     */
    @Override
    public void visitLdcInsn(Object value) {
        methodNode.visitLdcInsn(value);
    }

    /**
     * @param var
     * @param increment
     * @see org.objectweb.asm.tree.MethodNode#visitIincInsn(int, int)
     */
    @Override
    public void visitIincInsn(int var, int increment) {
        methodNode.visitIincInsn(var, increment);
    }

    /**
     * @param min
     * @param max
     * @param dflt
     * @param labels
     * @see org.objectweb.asm.tree.MethodNode#visitTableSwitchInsn(int, int,
     *      org.objectweb.asm.Label, org.objectweb.asm.Label[])
     */
    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        methodNode.visitTableSwitchInsn(min, max, dflt, labels);
    }

    /**
     * @param dflt
     * @param keys
     * @param labels
     * @see org.objectweb.asm.tree.MethodNode#visitLookupSwitchInsn(org.objectweb.asm.Label,
     *      int[], org.objectweb.asm.Label[])
     */
    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        methodNode.visitLookupSwitchInsn(dflt, keys, labels);
    }

    /**
     * @param descriptor
     * @param numDimensions
     * @see org.objectweb.asm.tree.MethodNode#visitMultiANewArrayInsn(java.lang.String,
     *      int)
     */
    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        methodNode.visitMultiANewArrayInsn(descriptor, numDimensions);
    }

    /**
     * @param typeRef
     * @param typePath
     * @param descriptor
     * @param visible
     * @return
     * @see org.objectweb.asm.tree.MethodNode#visitInsnAnnotation(int,
     *      org.objectweb.asm.TypePath, java.lang.String, boolean)
     */
    @Override
    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        return methodNode.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
    }

    /**
     * @param start
     * @param end
     * @param handler
     * @param type
     * @see org.objectweb.asm.tree.MethodNode#visitTryCatchBlock(org.objectweb.asm.Label,
     *      org.objectweb.asm.Label, org.objectweb.asm.Label, java.lang.String)
     */
    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        methodNode.visitTryCatchBlock(start, end, handler, type);
    }

    /**
     * @param typeRef
     * @param typePath
     * @param descriptor
     * @param visible
     * @return
     * @see org.objectweb.asm.tree.MethodNode#visitTryCatchAnnotation(int,
     *      org.objectweb.asm.TypePath, java.lang.String, boolean)
     */
    @Override
    public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor,
            boolean visible) {
        return methodNode.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible);
    }

    /**
     * @param name
     * @param descriptor
     * @param signature
     * @param start
     * @param end
     * @param index
     * @see org.objectweb.asm.tree.MethodNode#visitLocalVariable(java.lang.String,
     *      java.lang.String, java.lang.String, org.objectweb.asm.Label,
     *      org.objectweb.asm.Label, int)
     */
    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end,
            int index) {
        methodNode.visitLocalVariable(name, descriptor, signature, start, end, index);
    }

    /**
     * @param typeRef
     * @param typePath
     * @param start
     * @param end
     * @param index
     * @param descriptor
     * @param visible
     * @return
     * @see org.objectweb.asm.tree.MethodNode#visitLocalVariableAnnotation(int,
     *      org.objectweb.asm.TypePath, org.objectweb.asm.Label[],
     *      org.objectweb.asm.Label[], int[], java.lang.String, boolean)
     */
    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end,
            int[] index, String descriptor, boolean visible) {
        return methodNode.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
    }

    /**
     * @param line
     * @param start
     * @see org.objectweb.asm.tree.MethodNode#visitLineNumber(int,
     *      org.objectweb.asm.Label)
     */
    @Override
    public void visitLineNumber(int line, Label start) {
        methodNode.visitLineNumber(line, start);
    }

    /**
     * @param opcode
     * @param owner
     * @param name
     * @param descriptor
     * @deprecated
     * @see org.objectweb.asm.MethodVisitor#visitMethodInsn(int,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    @Deprecated
    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor) {
        methodNode.visitMethodInsn(opcode, owner, name, descriptor);
    }

    /**
     * @param maxStack
     * @param maxLocals
     * @see org.objectweb.asm.tree.MethodNode#visitMaxs(int, int)
     */
    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        methodNode.visitMaxs(maxStack, maxLocals);
    }

    /**
     * @see org.objectweb.asm.tree.MethodNode#visitEnd()
     */
    @Override
    public void visitEnd() {
        methodNode.visitEnd();
    }

}
