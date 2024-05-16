
package cn.featherfly.common.asm;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_8;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.ArrayUtils;

/**
 * AsmMethodClone.
 *
 * @author zhongj
 */
public class AsmMethodCloneTest {

    @Test
    public void test() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        Class<?> type = User.class;
        ClassReader reader = new ClassReader(type.getName());

        final String typeName = User.class.getName() + "$Enhance";
        final String name = Asm.getName(typeName);

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassNode cn = new ClassNode();
        cn.version = V1_8;
        cn.access = ACC_PUBLIC;
        cn.name = name;
        cn.superName = Asm.getName(Object.class);
        cn.interfaces.add(Asm.getName(type));

        MethodNode constructor = new MethodNode(ACC_PUBLIC, Asm.CONSTRUCT_METHOD, Asm.getConstructorDescriptor(), null,
            null);
        constructor.visitVarInsn(ALOAD, 0);
        constructor.visitMethodInsn(INVOKESPECIAL, cn.superName, Asm.CONSTRUCT_METHOD, Asm.getConstructorDescriptor(),
            false);
        constructor.visitInsn(RETURN);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();
        cn.methods.add(constructor);

        //        for (Method m : type.getMethods()) {
        //            System.out.println(m.toString());
        //            MethodNode methodNode = new MethodNode();
        //            methodNode.access = ACC_PUBLIC;
        //            methodNode.name = m.getName();
        //            methodNode.desc = Type.getMethodDescriptor(m);
        //            methodNode.visitLdcInsn(m.getDefaultValue());
        //            cn.methods.add(methodNode);
        //        }
        //        reader.accept(new TraceClassVisitor(new PrintWriter(System.out)), ClassReader.EXPAND_FRAMES);

        ClassNode classNode = new ClassNode();
        reader.accept(classNode, ClassReader.EXPAND_FRAMES);

        for (MethodNode mn : classNode.methods) {
            //            MethodNode methodNode = new MethodNode();
            //            methodNode.name = "$$" + mn.name;
            //            methodNode.access = mn.access;
            //            methodNode.desc = mn.desc;
            //            methodNode.signature = mn.signature;
            //            methodNode.annotationDefault = mn.annotationDefault;
            //            methodNode.attrs = mn.attrs;
            //            methodNode.exceptions = mn.exceptions;
            //            methodNode.instructions = mn.instructions;
            //            methodNode.invisibleAnnotableParameterCount = mn.invisibleAnnotableParameterCount;
            //            methodNode.invisibleAnnotations = mn.invisibleAnnotations;
            //            methodNode.invisibleLocalVariableAnnotations = mn.invisibleLocalVariableAnnotations;
            //            methodNode.invisibleTypeAnnotations = mn.invisibleTypeAnnotations;
            //            methodNode.invisibleParameterAnnotations = mn.invisibleParameterAnnotations;
            //            methodNode.localVariables = mn.localVariables;
            //            methodNode.maxLocals = mn.maxLocals;
            //            methodNode.maxStack = mn.maxStack;
            //            methodNode.parameters = mn.parameters;
            //            methodNode.tryCatchBlocks = mn.tryCatchBlocks;
            //            methodNode.visibleAnnotableParameterCount = mn.visibleAnnotableParameterCount;
            //            methodNode.visibleAnnotations = mn.visibleAnnotations;
            //            methodNode.visibleLocalVariableAnnotations = mn.visibleLocalVariableAnnotations;
            //            methodNode.visibleParameterAnnotations = mn.visibleParameterAnnotations;
            //            methodNode.visibleTypeAnnotations = mn.visibleTypeAnnotations;
            //            System.out.println(mn.access);
            //            System.out.println(mn.name);
            //            System.out.println(mn.desc);
            //            System.out.println(mn.signature);
            //            System.out.println(mn.annotationDefault);
            //            Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();
            //            while (iter.hasNext()) {
            //                AbstractInsnNode insn = iter.next();
            //                if (insn instanceof LabelNode || insn instanceof LineNumberNode) {
            //                    iter.remove();
            //                }
            //            }
            for (AbstractInsnNode insn : mn.instructions) {
                switch (insn.getType()) {
                    case Opcodes.LDC:
                        // TODO 设置值
                        break;
                    case Opcodes.BIPUSH:
                        // TODO 设置值
                        break;
                }
                System.out.println(insn.getClass().getName() + " " + insn.getType() + " " + insn.getOpcode());
            }
            cn.methods.add(mn);
        }

        reader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, final String descriptor,
                final String signature, final String[] exceptions) {
                MethodNodeVisitor mnv = new MethodNodeVisitor();
                final MethodNode methodNode = mnv.getMethodNode();
                methodNode.access = access;
                methodNode.name = "$$" + name;
                methodNode.desc = descriptor;
                methodNode.signature = signature;
                methodNode.exceptions = ArrayUtils.toList(exceptions);
                cn.methods.add(methodNode);
                return mnv;
                //                //                return new MethodVisitor(api) {
                //                //                    @Override
                //                //                    public void visitInsn(final int opcode) {
                //                //                        System.out.println("visitInsn " + opcode);
                //                //                        methodNode.visitInsn(opcode);
                //                //                    }
                //                //
                //                //                    /**
                //                //                     * Visits a parameter of this method.
                //                //                     *
                //                //                     * @param name parameter name or {@literal null} if none is provided.
                //                //                     * @param access the parameter's access flags, only {@code ACC_FINAL}, {@code ACC_SYNTHETIC}
                //                //                     *     or/and {@code ACC_MANDATED} are allowed (see {@link Opcodes}).
                //                //                     */
                //                //                    public void visitParameter(final String name, final int access) {
                //                //                      if (api < Opcodes.ASM5) {
                //                //                        throw new UnsupportedOperationException(REQUIRES_ASM5);
                //                //                      }
                //                //                      if (mv != null) {
                //                //                        mv.visitParameter(name, access);
                //                //                      }
                //                //                    }
                //                //
                //                //                    @Override
                //                //                    public void visitIntInsn(final int opcode, final int operand) {
                //                //                        System.out.println("visitIntInsn " + opcode + " " + operand);
                //                //                        methodNode.visitIntInsn(opcode, operand);
                //                //                    }
                //                //
                //                //                    @Override
                //                //                    public void visitVarInsn(final int opcode, final int var) {
                //                //                        System.out.println("visitVarInsn " + opcode + " " + var);
                //                //                        methodNode.visitVarInsn(opcode, var);
                //                //                    }
                //                //
                //                //                    @Override
                //                //                    public void visitTypeInsn(final int opcode, final String type) {
                //                //                        methodNode.visitTypeInsn(opcode, type);
                //                //                    }
                //                //
                //                //                    @Override
                //                //                    public void visitFieldInsn(final int opcode, final String owner, final String name,
                //                //                            final String descriptor) {
                //                //                        methodNode.visitFieldInsn(opcode, owner, name, descriptor);
                //                //                    }
                //                //
                //                //                    @Override
                //                //                    public void visitLdcInsn(final Object value) {
                //                //                        methodNode.visitLdcInsn(value);
                //                //                    }
                //                //
                //                //                    @Override
                //                //                    public void visitIincInsn(final int var, final int increment) {
                //                //                        methodNode.visitIincInsn(var, increment);
                //                //                    }
                //                //                };
            }
        }, ClassReader.EXPAND_FRAMES);

        //        for (MethodNode mn : cn.methods) {
        //            System.out.println(mn.name);
        //            for (AbstractInsnNode insn : mn.instructions) {
        //                System.out.println(insn.getClass().getName() + " " + insn.getType() + " " + insn.getOpcode());
        //            }
        //        }

        cn.accept(classWriter);
        byte[] code = classWriter.toByteArray();

        //将二进制流写到本地磁盘上
        //        FileOutputStream fos = new FileOutputStream("User$Enhance.class");
        //        fos.write(code);
        //        fos.close();

        new BytesClassLoader().defineClass(typeName, code, this.getClass().getProtectionDomain());

        Class.forName(typeName).newInstance();
    }

    @Test
    public void test2() {
        System.out.println("yufei".hashCode());
    }
}
