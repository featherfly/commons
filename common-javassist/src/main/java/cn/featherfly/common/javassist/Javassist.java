
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Javassist.java
 * @Package cn.featherfly.common.javassist
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2022-04-08 19:58:08
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.javassist;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ClassUtils;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * Javassist.
 *
 * @author zhongj
 */
public class Javassist {

    /**
     * Creates the method.
     *
     * @param method  the method
     * @param ctClass the ct class
     * @param pool    the pool
     * @return the ct method
     * @throws NotFoundException the not found exception
     */
    public static CtMethod createMethod(Method method, CtClass ctClass, ClassPool pool) throws NotFoundException {
        CtClass[] ctParamTypes = new CtClass[method.getParameterTypes().length];
        int i = 0;
        for (Parameter parameter : method.getParameters()) {
            ctParamTypes[i] = pool.getCtClass(parameter.getType().getName());
            i++;
        }
        CtMethod ctMethod = new CtMethod(pool.getCtClass(method.getReturnType().getTypeName()), method.getName(),
                ctParamTypes, ctClass);
        return ctMethod;
    }

    /**
     * Creates the annotations.
     *
     * @param method  the method
     * @param ctClass the ct class
     * @return the list
     * @throws Exception the exception
     */
    public static List<AnnotationsAttribute> createAnnotations(Method method, CtClass ctClass) throws Exception {
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        List<AnnotationsAttribute> list = new ArrayList<>();
        for (java.lang.annotation.Annotation annotation : method.getAnnotations()) {
            AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation ctAnnotation = new Annotation(annotation.annotationType().getName(), constPool);
            for (Method m : annotation.annotationType().getDeclaredMethods()) {
                String memberName = m.getName();
                Object annotationMemberValue;
                annotationMemberValue = m.invoke(annotation, new Object[] {});
                MemberValue memberValue = createMemberValue(annotationMemberValue, constPool);
                ctAnnotation.addMemberValue(memberName, memberValue);
            }
            attr.addAnnotation(ctAnnotation);
            list.add(attr);
        }
        return list;
    }

    /**
     * Copy annotations.
     *
     * @param method   the method
     * @param ctMethod the ct method
     * @param ctClass  the ct class
     * @throws Exception the exception
     */
    public static void copyAnnotations(Method method, CtMethod ctMethod, CtClass ctClass) throws Exception {
        createAnnotations(method, ctClass).forEach(a -> {
            ctMethod.getMethodInfo().addAttribute(a);
        });
    }

    /**
     * Creates the member value.
     *
     * @param value     the value
     * @param constPool the const pool
     * @return the member value
     */
    public static MemberValue createMemberValue(Object value, ConstPool constPool) {
        if (value.getClass().isArray()) {
            ArrayMemberValue arrayMemberValue = new ArrayMemberValue(
                    createObjectMemberValue(value.getClass().getComponentType(), constPool), constPool);
            int length = Array.getLength(value);
            MemberValue[] elements = new MemberValue[length];
            for (int i = 0; i < Array.getLength(value); i++) {
                Object v = Array.get(value, i);
                elements[i] = createObjectMemberValue(v, constPool);
            }
            arrayMemberValue.setValue(elements);
            return arrayMemberValue;
        } else {
            return createObjectMemberValue(value, constPool);
        }
    }

    @SuppressWarnings("rawtypes")
    private static MemberValue createObjectMemberValue(Object value, ConstPool constPool) {
        if (value instanceof java.lang.annotation.Annotation) {
            return new AnnotationMemberValue((Annotation) value, constPool);
        } else if (value instanceof Boolean || value.getClass() == boolean.class) {
            return new BooleanMemberValue((boolean) value, constPool);
        } else if (value instanceof Byte || value.getClass() == byte.class) {
            return new ByteMemberValue((byte) value, constPool);
        } else if (value instanceof Character || value.getClass() == char.class) {
            return new CharMemberValue((char) value, constPool);
        } else if (value instanceof Class) {
            return new ClassMemberValue(((Class) value).getName(), constPool);
        } else if (value instanceof Double || value.getClass() == double.class) {
            return new DoubleMemberValue((double) value, constPool);
        } else if (value instanceof Float || value.getClass() == float.class) {
            return new FloatMemberValue((float) value, constPool);
        } else if (value instanceof Enum) {
            //            return new EnumMemberValue(value.toString(), constPool);
            // FIXME 后续来实现
            return null;
        } else if (value instanceof Short || value.getClass() == short.class) {
            return new ShortMemberValue((short) value, constPool);
        } else if (value instanceof Integer || value.getClass() == int.class) {
            return new IntegerMemberValue(constPool, (int) value);
        } else if (value instanceof Long || value.getClass() == long.class) {
            return new LongMemberValue((long) value, constPool);
        } else if (value instanceof String) {
            return new StringMemberValue(value.toString(), constPool);
        }
        throw new UnsupportedException("not support type " + value.getClass().getName());
    }

    private static MemberValue createObjectMemberValue(Class<?> type, ConstPool constPool) {
        if (ClassUtils.isParent(java.lang.annotation.Annotation.class, type)) {
            return new AnnotationMemberValue(constPool);
        } else if (Boolean.class == type || type == boolean.class) {
            return new BooleanMemberValue(constPool);
        } else if (Byte.class == type || type == byte.class) {
            return new ByteMemberValue(constPool);
        } else if (Character.class == type || type == char.class) {
            return new CharMemberValue(constPool);
        } else if (type == Class.class) {
            return new ClassMemberValue(constPool);
        } else if (Double.class == type || type == double.class) {
            return new DoubleMemberValue(constPool);
        } else if (type == Float.class || type == float.class) {
            return new FloatMemberValue(constPool);
        } else if (ClassUtils.isParent(Enum.class, type)) {
            //            return new EnumMemberValue(value.toString(), constPool);
            // FIXME 后续来实现
            return null;
        } else if (type == Short.class || type == short.class) {
            return new ShortMemberValue(constPool);
        } else if (type == Integer.class || type == int.class) {
            return new IntegerMemberValue(constPool);
        } else if (type == Long.class || type == long.class) {
            return new LongMemberValue(constPool);
        } else if (type == String.class) {
            return new StringMemberValue(constPool);
        }
        throw new UnsupportedException("not support type " + type.getName());
    }

    /**
     * To class.
     *
     * @param ctClass  the ct class
     * @param loader   the loader
     * @param neighbor the neighbor
     * @param domain   the domain
     * @return the class
     * @throws CannotCompileException the cannot compile exception
     */
    public Class<?> toClass(CtClass ctClass, ClassLoader loader, Class<?> neighbor, ProtectionDomain domain)
            throws CannotCompileException {
        ClassPool cp = ctClass.getClassPool();
        if (loader == null) {
            loader = cp.getClassLoader();
        }
        return cp.toClass(ctClass, neighbor, loader, domain);
    }

}
