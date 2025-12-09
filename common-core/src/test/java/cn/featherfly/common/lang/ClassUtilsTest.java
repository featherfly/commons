
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static cn.featherfly.common.lang.ArrayUtils.EMPTY_CLASS_ARRAY;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.lang.annotation.Documented;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.vo.AbstractSuperGet;
import cn.featherfly.common.lang.vo.Bar;
import cn.featherfly.common.lang.vo.Coo;
import cn.featherfly.common.lang.vo.Foo;
import cn.featherfly.common.lang.vo.GenericSuper;
import cn.featherfly.common.lang.vo.GenericTest;
import cn.featherfly.common.lang.vo.User;
import cn.featherfly.common.lang.vo.User2;
import cn.featherfly.common.lang.vo.User3;
import cn.featherfly.common.lang.vo.UserFunction;
import cn.featherfly.common.lang.vo.UserList;

public class ClassUtilsTest {

    static Class<GenericTest> genericType = GenericTest.class;

    @Test
    void getPrimitiveType() {
        assertEquals(ClassUtils.getPrimitiveType("boolean"), Boolean.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("byte"), Byte.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("short"), Short.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("int"), Integer.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("long"), Long.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("double"), Double.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("float"), Float.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("void"), Void.TYPE);

        assertEquals(ClassUtils.getPrimitiveType("boolean", true), Boolean.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("byte", true), Byte.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("short", true), Short.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("int", true), Integer.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("long", true), Long.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("double", true), Double.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("float", true), Float.TYPE);
        assertEquals(ClassUtils.getPrimitiveType("void", true), Void.TYPE);

        Function<String, Class<?>> fn = null;
        fn = ClassUtils.getPrimitiveTypeFunction(true);
        assertEquals(fn.apply("boolean"), Boolean.TYPE);
        assertEquals(fn.apply("byte"), Byte.TYPE);
        assertEquals(fn.apply("short"), Short.TYPE);
        assertEquals(fn.apply("int"), Integer.TYPE);
        assertEquals(fn.apply("long"), Long.TYPE);
        assertEquals(fn.apply("double"), Double.TYPE);
        assertEquals(fn.apply("float"), Float.TYPE);
        assertEquals(fn.apply("void"), Void.TYPE);

        fn = ClassUtils.getPrimitiveTypeFunction(false);
        assertEquals(fn.apply("boolean"), Boolean.TYPE);
        assertEquals(fn.apply("byte"), Byte.TYPE);
        assertEquals(fn.apply("short"), Short.TYPE);
        assertEquals(fn.apply("int"), Integer.TYPE);
        assertEquals(fn.apply("long"), Long.TYPE);
        assertEquals(fn.apply("double"), Double.TYPE);
        assertEquals(fn.apply("float"), Float.TYPE);
        assertEquals(fn.apply("void"), Void.TYPE);
    }

    @Test
    void getPrimitiveWrapped() {
        assertEquals(ClassUtils.getPrimitiveWrapped(Boolean.TYPE), Boolean.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Byte.TYPE), Byte.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Short.TYPE), Short.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Integer.TYPE), Integer.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Long.TYPE), Long.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Double.TYPE), Double.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Float.TYPE), Float.class);

        assertEquals(ClassUtils.getPrimitiveWrapped(Float.class), Float.class);

        assertEquals(ClassUtils.getPrimitiveWrapped(Boolean.TYPE, true), Boolean.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Byte.TYPE, true), Byte.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Short.TYPE, true), Short.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Integer.TYPE, true), Integer.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Long.TYPE, true), Long.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Double.TYPE, true), Double.class);
        assertEquals(ClassUtils.getPrimitiveWrapped(Float.TYPE, true), Float.class);

        assertEquals(ClassUtils.getPrimitiveWrapped(Float.class, true), Float.class);

        Function<Class<?>, Class<?>> fn = null;
        fn = ClassUtils.getPrimitiveWrappedFunction(true);
        assertEquals(fn.apply(Boolean.TYPE), Boolean.class);
        assertEquals(fn.apply(Byte.TYPE), Byte.class);
        assertEquals(fn.apply(Short.TYPE), Short.class);
        assertEquals(fn.apply(Integer.TYPE), Integer.class);
        assertEquals(fn.apply(Long.TYPE), Long.class);
        assertEquals(fn.apply(Double.TYPE), Double.class);
        assertEquals(fn.apply(Float.TYPE), Float.class);

        fn = ClassUtils.getPrimitiveWrappedFunction(false);
        assertEquals(fn.apply(Boolean.TYPE), Boolean.class);
        assertEquals(fn.apply(Byte.TYPE), Byte.class);
        assertEquals(fn.apply(Short.TYPE), Short.class);
        assertEquals(fn.apply(Integer.TYPE), Integer.class);
        assertEquals(fn.apply(Long.TYPE), Long.class);
        assertEquals(fn.apply(Double.TYPE), Double.class);
        assertEquals(fn.apply(Float.TYPE), Float.class);
    }

    @Test
    void getPrimitiveClassValueMethodName() {
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Boolean.TYPE), "booleanValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Boolean.class), "booleanValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Byte.TYPE), "byteValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Byte.class), "byteValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Short.TYPE), "shortValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Short.class), "shortValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Integer.TYPE), "intValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Integer.class), "intValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Long.TYPE), "longValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Long.class), "longValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Double.TYPE), "doubleValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Double.class), "doubleValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Float.TYPE), "floatValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Float.class), "floatValue");

        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Boolean.TYPE, true), "booleanValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Boolean.class, true), "booleanValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Byte.TYPE, true), "byteValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Byte.class, true), "byteValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Short.TYPE, true), "shortValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Short.class, true), "shortValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Integer.TYPE, true), "intValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Integer.class, true), "intValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Long.TYPE, true), "longValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Long.class, true), "longValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Double.TYPE, true), "doubleValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Double.class, true), "doubleValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Float.TYPE, true), "floatValue");
        assertEquals(ClassUtils.getPrimitiveClassValueMethodName(Float.class, true), "floatValue");

        Function<Class<?>, String> fn = null;
        fn = ClassUtils.getPrimitiveClassValueMethodNameFunction(true);
        assertEquals(fn.apply(Boolean.TYPE), "booleanValue");
        assertEquals(fn.apply(Boolean.class), "booleanValue");
        assertEquals(fn.apply(Byte.TYPE), "byteValue");
        assertEquals(fn.apply(Byte.class), "byteValue");
        assertEquals(fn.apply(Short.TYPE), "shortValue");
        assertEquals(fn.apply(Short.class), "shortValue");
        assertEquals(fn.apply(Integer.TYPE), "intValue");
        assertEquals(fn.apply(Integer.class), "intValue");
        assertEquals(fn.apply(Long.TYPE), "longValue");
        assertEquals(fn.apply(Long.class), "longValue");
        assertEquals(fn.apply(Double.TYPE), "doubleValue");
        assertEquals(fn.apply(Double.class), "doubleValue");
        assertEquals(fn.apply(Float.TYPE), "floatValue");
        assertEquals(fn.apply(Float.class), "floatValue");

        fn = ClassUtils.getPrimitiveClassValueMethodNameFunction(false);
        assertEquals(fn.apply(Boolean.TYPE), "booleanValue");
        assertEquals(fn.apply(Boolean.class), "booleanValue");
        assertEquals(fn.apply(Byte.TYPE), "byteValue");
        assertEquals(fn.apply(Byte.class), "byteValue");
        assertEquals(fn.apply(Short.TYPE), "shortValue");
        assertEquals(fn.apply(Short.class), "shortValue");
        assertEquals(fn.apply(Integer.TYPE), "intValue");
        assertEquals(fn.apply(Integer.class), "intValue");
        assertEquals(fn.apply(Long.TYPE), "longValue");
        assertEquals(fn.apply(Long.class), "longValue");
        assertEquals(fn.apply(Double.TYPE), "doubleValue");
        assertEquals(fn.apply(Double.class), "doubleValue");
        assertEquals(fn.apply(Float.TYPE), "floatValue");
        assertEquals(fn.apply(Float.class), "floatValue");
    }

    @Test
    public void isParent() {
        assertTrue(ClassUtils.isParent(Map.class, HashMap.class));
        assertFalse(ClassUtils.isParent(HashMap.class, Map.class));
    }

    @Test
    public void isAbstractClass() {
        assertTrue(ClassUtils.isAbstractClass(Map.class));
        assertTrue(ClassUtils.isAbstractClass(AbstractList.class));
        assertFalse(ClassUtils.isAbstractClass(ArrayList.class));
    }

    @Test
    public void isInstanceClass() {
        assertFalse(ClassUtils.isInstanceClass(Map.class));
        assertFalse(ClassUtils.isInstanceClass(AbstractList.class));
        assertTrue(ClassUtils.isInstanceClass(ArrayList.class));
    }

    @Test
    public void newInstance() {
        List<String> list = new ArrayList<>();
        list.add("ArrayList1");
        User user = ClassUtils.newInstance(User.class, list);
        System.out.println(user);
        assertEquals(user.construct, "List");

        list = new LinkedList<>();
        list.add("LinkedList1");
        user = ClassUtils.newInstance(User.class, list);
        System.out.println(user);
        assertEquals(user.construct, "LinkedList");

        String name = "yufei";
        Integer age = 18;
        user = ClassUtils.newInstance(User.class, name, age);
        System.out.println(user);
        assertEquals(user.name, name);
        assertEquals(user.age, age);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void newInstance2() {
        ClassUtils.newInstance(null);
    }

    @Test
    public void invokeMethod() {
        String name = "yufei";
        Integer age = 18;
        User user = new User();

        assertNull(user.name);

        ClassUtils.invokeMethod(user, "setName", name);
        assertEquals(user.name, name);

        ClassUtils.invokeMethod(user, "setAge", age);
        assertEquals(user.age, age);

        user = new User();

        assertNull(user.name);
        assertNull(user.age);

        ClassUtils.invokeMethod(user, "set", name, age);
        assertEquals(user.name, name);
        assertEquals(user.age, age);

    }

    @Test
    public void getAnnotation() {
        Bar bar = Coo.class.getAnnotation(Bar.class);
        Foo foo = ClassUtils.getAnnotation(bar, Foo.class);
        assertEquals("test", foo.value());
        assertNull(ClassUtils.getAnnotation(bar, Documented.class));
    }

    @Test
    public void getter() throws NoSuchMethodException, SecurityException {
        Method method = User3.class.getMethod("isUser", new Class[] { String.class });
        assertFalse(ClassUtils.isGetter(method));

        // boolean isUser
        method = User3.class.getMethod("isUser", new Class[0]);
        assertTrue(ClassUtils.isGetter(method));

        // Boolean isUser
        method = User3.class.getMethod("isUser2", new Class[0]);
        assertFalse(ClassUtils.isGetter(method));
    }

    @Test
    public void getSuperClassGenericType() throws NoSuchMethodException, SecurityException {
        assertEquals(ClassUtils.getSuperClassGenericType(genericType), Integer.class);
        assertEquals(ClassUtils.getSuperClassGenericType(genericType, 1), String.class);
        assertEquals(ClassUtils.getSuperClassGenericType(genericType, 2), Long.class);

        Map<Class<?>,
            Map<String, Type>> typeGenericParameterMap = ClassUtils.getSuperClassAllGenericTypeMap(genericType);
        Map<String, Type> superClassGenericType = ClassUtils.getSuperClassGenericTypeMap(genericType);
        assertEquals(typeGenericParameterMap.get(genericType.getSuperclass()), superClassGenericType);
        Map<String, Type> abstractSuperGetClassGenericType = typeGenericParameterMap.get(AbstractSuperGet.class);
        assertEquals(abstractSuperGetClassGenericType.get("T"), Short.class);
        assertEquals(abstractSuperGetClassGenericType.get("U2"), Byte.class);
        System.out.println(superClassGenericType);
        System.out.println(typeGenericParameterMap);
    }

    @Test
    public void getInterfaceGenericType() throws NoSuchMethodException, SecurityException {
        assertEquals(ClassUtils.getInterfaceGenericType(UserList.class, List.class), User.class);

        assertEquals(ClassUtils.getInterfaceGenericType(UserFunction.class, Supplier.class), User.class);
        assertEquals(ClassUtils.getInterfaceGenericType(UserFunction.class, Function.class), User2.class);
        assertEquals(ClassUtils.getInterfaceGenericType(UserFunction.class, 1, Function.class), User3.class);

        //        System.out.println(ClassUtils.getInterfaceGenericTypeMap(UserList.class, List.class));

        //        System.out.println(ClassUtils.getMethodGenericReturnType(UserList.class.getMethod("get", int.class)));

    }

    @Test
    public void getFieldType() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
        assertEquals(ClassUtils.getFieldType(genericType, "id"), Long.class);
    }

    @Test
    public void getFieldGenericParameterType() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("list")),
            String.class);
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("map")),
            String.class);
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("map"), 1),
            Integer.class);
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("optional")),
            String.class);
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("obj")),
            Object.class);
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("obj1")),
            Number.class);
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("obj2")),
            User.class);
        assertEquals(ClassUtils.getFieldGenericParameterType(User.class, User.class.getDeclaredField("obj3")),
            Object.class);

        assertEquals(ClassUtils.getFieldGenericParameterType(genericType, "map"), Integer.class);

        assertEquals(ClassUtils.getFieldGenericParameterType(genericType, "map", 1), String.class);

        assertEquals(ClassUtils.getFieldGenericParameterType(genericType, "listId"), Long.class);

        assertEquals(ClassUtils.getFieldGenericParameterType(GenericSuper.class, "map"), Object.class);

        assertEquals(ClassUtils.getFieldGenericParameterType(GenericSuper.class, "map", 1), Object.class);

        assertEquals(ClassUtils.getFieldGenericParameterType(GenericSuper.class, "listId"), Number.class);

    }

    @Test
    public void getMethodReturnType() {
        assertEquals(ClassUtils.getMethodReturnType(genericType, "get", EMPTY_CLASS_ARRAY), User.class);
        assertEquals(ClassUtils.getMethodReturnType(genericType, "apply", Object.class), String.class);
        assertEquals(ClassUtils.getMethodReturnType(genericType, "getId", EMPTY_CLASS_ARRAY), Long.class);
        assertEquals(ClassUtils.getMethodReturnType(genericType, "superGet", EMPTY_CLASS_ARRAY), Short.class);
        assertEquals(ClassUtils.getMethodReturnType(genericType, "listId", EMPTY_CLASS_ARRAY), List.class);
        assertEquals(ClassUtils.getMethodReturnType(genericType, "string", EMPTY_CLASS_ARRAY), String.class);

        //        System.out.println(ClassUtils.getMethodGenericReturnType(genericType, "listId", EMPTY_CLASS_ARRAY));
        //        System.out.println(ClassUtils.getMethodGenericReturnType(genericType, "string", EMPTY_CLASS_ARRAY));
        //        System.out.println(ClassUtils.getMethodGenericReturnType(genericType, "superGet2", EMPTY_CLASS_ARRAY));
        //        System.out.println(ClassUtils.getMethodGenericReturnType(genericType, "superGet", EMPTY_CLASS_ARRAY));

        assertEquals(ClassUtils.getMethodReturnType(genericType, "number", EMPTY_CLASS_ARRAY), Number.class);
    }

    @Test
    public void getMethodReturnType2() {
        assertEquals(ClassUtils.getMethodReturnType(genericType, "number", EMPTY_CLASS_ARRAY), Number.class);
        assertEquals(ClassUtils.getMethodReturnType(genericType, "number", Number.class), Number.class);
    }

    @Test
    public void getMethodReturnTypeGenericParameterType2() {
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType, "listNumber", EMPTY_CLASS_ARRAY),
            Number.class);
    }

    @Test
    public void getMethodReturnTypeGenericParameterType()
        throws NoSuchFieldException, SecurityException, NoSuchMethodException {
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType,
            genericType.getMethod("listUser", EMPTY_CLASS_ARRAY)), User.class);
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType,
            genericType.getMethod("listInteger", EMPTY_CLASS_ARRAY)), Integer.class);
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType,
            genericType.getMethod("listString", EMPTY_CLASS_ARRAY)), String.class);
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType,
            genericType.getMethod("function", EMPTY_CLASS_ARRAY), 0), Integer.class);
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType,
            genericType.getMethod("function", EMPTY_CLASS_ARRAY), 1), String.class);
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType, "listId", EMPTY_CLASS_ARRAY),
            Long.class);
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType, "getMap", EMPTY_CLASS_ARRAY),
            Integer.class);
        assertEquals(ClassUtils.getMethodReturnTypeGenericParameterType(genericType,
            ClassUtils.getMethod(genericType, "getMap", EMPTY_CLASS_ARRAY), 1), String.class);
        //        // TODO 返回泛型 <T> T get(); 拿不到具体泛型
        //        System.out.println(ClassUtils
        //                .getMethodReturnTypeGenericParameterType(genericType.getMethod("listGeneric", EMPTY_CLASS_ARRAY)));
        //        // TODO 返回泛型 <T> T get(T t); 拿不到具体泛型
        //        System.out.println(
        //                ClassUtils.getMethodReturnTypeGenericParameterType(genericType.getMethod("listGeneric", Object.class)));
        System.out
            .println(ClassUtils.getMethodReturnTypeGenericParameterType(genericType, "listId", EMPTY_CLASS_ARRAY));
        System.out
            .println(ClassUtils.getMethodReturnTypeGenericParameterTypes(genericType, "getMap", EMPTY_CLASS_ARRAY));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getMethodParameterType() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
        //        Method method = ClassUtils.getMethod(genericType, "set", Integer.class, String.class, Long.class);
        Method method = ClassUtils.getMethod(genericType, "set", Object.class, Object.class, Number.class);
        System.out.println(Arrays.toString(method.getParameterTypes()));
        assertEquals(ClassUtils.getMethodParameterType(genericType, method), Integer.class);
        assertEquals(ClassUtils.getMethodParameterType(genericType, method, 1), String.class);
        assertEquals(ClassUtils.getMethodParameterType(genericType, method, 2), Long.class);

        assertEquals(ClassUtils.getMethodParameterTypes(genericType, method),
            ArrayUtils.toList(Integer.class, String.class, Long.class));

        //        System.out.println(ClassUtils.getMethodParameterType(genericType,
        //                ClassUtils.getMethod(genericType, "setId", Number.class), 0));
        //        System.out.println(ClassUtils.getMethodParameterType(genericType,
        //                ClassUtils.getMethod(genericType, "setId", Number.class), 2));
        //        System.out.println(ClassUtils.getMethodParameterType(genericType,
        //                ClassUtils.getMethod(genericType, "setId", Number.class), -1));

        //        System.out.println(genericType.getMethod("setId", Number.class));
        //        for (Method m : genericType.getMethods()) {
        //            if (m.getName().equals("setId") || m.getName().equals("setListId")) {
        //                System.out.println(m);
        //                System.out.println("m.getParameters()");
        //                for (Parameter p : m.getParameters()) {
        //                    System.out.println(p.getName());
        //                    System.out.println(p.getType());
        //                    System.out.println(p.getParameterizedType());
        //                }
        //                System.out.println("m.getTypeParameters()");
        //                for (Type type : m.getTypeParameters()) {
        //                    System.out.println(type);
        //                }
        //                System.out.println("m.getParameterTypes()");
        //                for (Type p : m.getParameterTypes()) {
        //                    System.out.println(p);
        //                }
        //                System.out.println("m.getGenericParameterTypes()");
        //                for (Type p : m.getGenericParameterTypes()) {
        //                    System.out.println(p);
        //                }
        //            }
        //        }
    }

    @Test
    public void getMethodParameterType2() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
        //        Method method = ClassUtils.getMethod(genericType, "set", Integer.class, String.class, Long.class);
        Method number = ClassUtils.getMethod(genericType, "number", Number.class);
        System.out.println(number);
        assertEquals(ClassUtils.getMethodParameterType(genericType, number), Number.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getMethodGenericParameterType() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
        //        assertEquals(
        //                ClassUtils.getMethodGenericParameterType(genericType, genericType.getMethod("setId", Object.class)),
        //                Long.class);
        assertEquals(
            ClassUtils.getMethodGenericParameterType(genericType, genericType.getMethod("setListId", List.class)),
            Long.class);

        Method setMap = genericType.getMethod("setMap", Map.class);
        assertEquals(ClassUtils.getMethodGenericParameterType(genericType, setMap), Integer.class);
        assertEquals(ClassUtils.getMethodGenericParameterType(genericType, setMap, 0, 1), String.class);
        assertEquals(ClassUtils.getMethodGenericParameterTypes(genericType, setMap, 0),
            ArrayUtils.toList(Integer.class, String.class));

        Method set = genericType.getMethod("set2", List.class, Map.class);
        assertEquals(ClassUtils.getMethodGenericParameterType(genericType, set), Long.class);
        assertEquals(ClassUtils.getMethodGenericParameterType(genericType, set, 1, 0), Integer.class);
        assertEquals(ClassUtils.getMethodGenericParameterType(genericType, set, 1, 1), String.class);

        List<Class<?>> param1 = ArrayUtils.toList(Long.class);
        List<Class<?>> param2 = ArrayUtils.toList(Integer.class, String.class);
        assertEquals(ClassUtils.getMethodGenericParameterTypes(genericType, set), param1);
        assertEquals(ClassUtils.getMethodGenericParameterTypes(genericType, set, 1), param2);
        assertEquals(ClassUtils.getMethodGenericParameterTypesAll(genericType, set), ArrayUtils.toList(param1, param2));

        System.out.println(
            ClassUtils.getMethodGenericParameterType(genericType, genericType.getMethod("setId", Number.class)));
        System.out.println(
            ClassUtils.getMethodGenericParameterType(genericType, genericType.getMethod("setListId", List.class)));
        System.out.println(ClassUtils.getMethodGenericParameterType(genericType, setMap));
        System.out.println(ClassUtils.getMethodGenericParameterType(genericType, setMap, 0, 1));

    }

    @Test
    public void getMethodGenericParameterType2() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
        //        assertEquals(ClassUtils.getMethodGenericReturnType(User.class.getMethod("getList", new Class[0])),
        //                String.class);
        //        assertEquals(ClassUtils.getMethodGenericReturnType(User.class.getMethod("getMap", new Class[0])), String.class);
        //        assertEquals(ClassUtils.getMethodGenericReturnType(User.class.getMethod("getMap", new Class[0]), 1),
        //                Integer.class);
        Method setList = User.class.getMethod("setList", new Class[] { List.class });
        Method setMap = User.class.getMethod("setMap", new Class[] { Map.class });
        assertEquals(ClassUtils.getMethodGenericParameterType(User.class, setList), String.class);
        assertEquals(ClassUtils.getMethodGenericParameterType(User.class, setMap), String.class);
        assertEquals(ClassUtils.getMethodGenericParameterType(User.class, setMap, 0, 1), Integer.class);
        assertEquals(ClassUtils.getMethodGenericParameterTypes(User.class, setList).size(), 1);
        assertEquals(ClassUtils.getMethodGenericParameterTypes(User.class, setMap).size(), 2);
    }

    static void p(Method method, Class<?> target) throws NoSuchMethodException, SecurityException {
        System.out.println(Str.format("target {0}, method {1}, declaringClass {2}",
            new Object[] { target.getName(), method.getName(), method.getDeclaringClass().getName() }));
        System.out.println(method);
        TypeVariable<?> tv = (TypeVariable<?>) method.getGenericReturnType();
        System.out.println(tv);
        System.out.println(tv.getName());
        System.out.println(tv.getTypeName());

        System.out.println(tv.getGenericDeclaration());
        System.out.println(tv.getGenericDeclaration().getClass());
        System.out.println(tv.getGenericDeclaration().equals(target.getSuperclass()));

        System.out.println(ClassUtils.getInterfaceGenericTypeMap(target));

        if (tv.getGenericDeclaration() instanceof Class) {
            Map<String,
                Type> genericMap = ClassUtils.getInterfaceGenericTypeMap(target).get(tv.getGenericDeclaration());
            if (genericMap != null) {
                System.err.println(genericMap.get(tv.getName()));
            }
        }

        System.out.println(ClassUtils.getSuperClassGenericTypeMap(target).get(tv.getName()));
        System.out.println();
    }

    static Type getReturnType(Class<?> clazz, Method method) {
        Type returnType = method.getGenericReturnType();
        System.out.println(returnType.getClass());
        if (returnType instanceof TypeVariable) {
            TypeVariable<?> tv = (TypeVariable<?>) returnType;
            System.out.println("getGenericDeclaration " + tv.getGenericDeclaration().getClass());
            if (tv.getGenericDeclaration() instanceof Class) {
                Type result = null;
                Map<String, Type> genericMap = ClassUtils.getSuperClassGenericTypeMap(clazz);
                result = genericMap.get(tv.getName());
                if (result == null) {
                    genericMap = ClassUtils.getInterfaceGenericTypeMap(clazz).get(tv.getGenericDeclaration());
                    if (genericMap != null) {
                        result = genericMap.get(tv.getName());
                    }
                }
                return result;
            } else {
                // N getName()
                // N getName(N n)
                // 以上两种
                return Object.class;
            }
        }
        return returnType;
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException {
        //        p(UserList.class.getMethod("get", int.class), UserList.class);
        //        p(UserFunction.class.getMethod("get", ArrayUtils.EMPTY_CLASS_ARRAY), UserFunction.class);
        //        p(UserFunction.class.getMethod("apply", Object.class), UserFunction.class);
        //        p(genericType.getMethod("get", ArrayUtils.EMPTY_CLASS_ARRAY), genericType);

        System.out.println(genericType.getField("id").getType());
        System.out.println(genericType.getField("id").getGenericType());
        System.out.println(genericType.getMethod("getId", ArrayUtils.EMPTY_CLASS_ARRAY).getGenericReturnType());
        //        System.out.println(genericType.getField("setId").getGenericType());

        Class<?> target = genericType;
        Method method = target.getMethod("getId", ArrayUtils.EMPTY_CLASS_ARRAY);
        System.out.println(getReturnType(target, method));

        method = target.getMethod("getName", ArrayUtils.EMPTY_CLASS_ARRAY);
        System.out.println(getReturnType(target, method));

        method = target.getMethod("getName", Object.class);
        System.out.println(getReturnType(target, method));
    }
}
