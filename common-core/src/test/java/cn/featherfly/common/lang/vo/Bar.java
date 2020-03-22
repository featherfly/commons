package cn.featherfly.common.lang.vo;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Foo("test")
public @interface Bar {
}

@Bar
class C {

    public static <A extends Annotation> void isAnnotationPresent(A annotation) {
        System.out.println(annotation.getClass().isAnnotationPresent(Foo.class));
    }

    public static <A extends Annotation> void getPresentedAnnotation(A annotation) {
        System.out.println(annotation.getClass());
        System.out.println(annotation.annotationType());
        System.out.println("Documented" + annotation.getClass().isAnnotationPresent(Documented.class));

        if (annotation.annotationType().isAnnotationPresent(Foo.class)) {
            Foo foo = annotation.annotationType().getAnnotation(Foo.class);
            System.out.println(foo.value());
        }
        //        InvocationHandler handler = Proxy.getInvocationHandler(annotation);
        //        Field field = handler.getClass().getDeclaredField("type");
        //        field.setAccessible(true);
        //        Class<?> clazz = (Class<?>) field.get(handler);
        //        if (clazz.isAnnotationPresent(Foo.class)) {
        //            Foo foo = clazz.getAnnotation(Foo.class);
        //            System.out.println(foo.value()); // "test"
        //        }
    }

    public static void main(String... args) throws NoSuchFieldException, IllegalAccessException {
        Bar bar = C.class.getAnnotation(Bar.class);
        InvocationHandler handler = Proxy.getInvocationHandler(bar);
        Field field = handler.getClass().getDeclaredField("type");
        field.setAccessible(true);
        Class<?> clazz = (Class<?>) field.get(handler);
        if (clazz.isAnnotationPresent(Foo.class)) {
            Foo foo = clazz.getAnnotation(Foo.class);
            System.out.println(foo.value()); // "test"
        }
    }
}
