package cn.featherfly.common.lang;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import cn.featherfly.common.exception.ExceptionWrapper;
import cn.featherfly.common.exception.IOException;
import cn.featherfly.common.structure.ChainMap;

/**
 * A utility class that encapsulates some common operations that are syntactically cumbersome. <br>
 * 对一些在语法上显得拖沓的常用操作进行封装的工具类 .
 *
 * @author zhongj
 * @since 1.8.6
 */
public final class Lang {

    /** The Constant WRAPPER. */
    private static final ExceptionWrapper<RuntimeException> WRAPPER = new ExceptionWrapper<>(RuntimeException.class);

    private static List<EnumConvertor> enumConvertors = ServiceLoaderUtils.loadAll(EnumConvertor.class);

    /**
     * Instantiates a new lang utils.
     */
    private Lang() {
    }

    /**
     * 如果第一个参数为空(null），返回第二个参数，否则返回第一个参数 .
     *
     * @param <T> the generic type
     * @param target the target
     * @param defaultTarget the default target
     * @return 第一个参数为空(null），返回第二个参数，否则返回第一个参数
     * @deprecated {@link #ifNull(Object, Object)}
     */
    @Deprecated
    public static <T> T pick(T target, T defaultTarget) {
        return target == null ? defaultTarget : target;
    }

    /**
     * 如果第一个参数为空(null），返回第二个参数，否则返回第一个参数 .
     *
     * @param <T> the generic type
     * @param target the target
     * @param defaultTarget the default target
     * @return 第一个参数为空(null），返回第二个参数，否则返回第一个参数
     * @deprecated use {@link #ifNull(Object, Supplier)} instead
     */
    @Deprecated
    public static <T> T pick(T target, Supplier<T> defaultTarget) {
        return target == null ? defaultTarget.get() : target;
    }

    /**
     * 返回第一个非空的项，!=null .
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 第一个非空的对象
     * @deprecated use {@link #ifNotNullFirst(Object...)} instead
     */
    @Deprecated
    public static <T> T pickFirst(@SuppressWarnings("unchecked") T... objects) {
        if (objects != null) {
            for (T obj : objects) {
                if (obj != null) {
                    return obj;
                }
            }
        }
        return null;
    }

    /**
     * 返回第一个非空的对象，!=null .
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 第一个非空的对象
     * @deprecated use {@link #ifNotNullFirst(Iterable)} instead
     */
    @Deprecated
    public static <T> T pickFirst(Iterable<T> objects) {
        if (objects != null) {
            for (T obj : objects) {
                if (obj != null) {
                    return obj;
                }
            }
        }
        return null;
    }

    /**
     * 返回最后一个非空的对象，!=null .
     *
     * @param <T> the generic type
     * @param pickedItems the picked items
     * @return 最后一个非空的对象
     * @deprecated use {@link #ifNotNullLast(Iterable)} instead
     */
    @Deprecated
    public static <T> T pickLast(@SuppressWarnings("unchecked") T... pickedItems) {
        if (pickedItems != null) {
            for (int i = pickedItems.length - 1; i >= 0; i--) {
                T t = pickedItems[i];
                if (t != null) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * 返回最后一个非空的对象，!=null .
     *
     * @param <T> the generic type
     * @param pickedItems the picked items
     * @return 最后一个非空的对象
     * @deprecated use {@link #ifNotNullLast(List)} instead
     */
    @Deprecated
    public static <T> T pickLast(List<T> pickedItems) {
        if (pickedItems != null) {
            for (int i = pickedItems.size() - 1; i >= 0; i--) {
                T t = pickedItems.get(i);
                if (t != null) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param optional the optional
     * @return the t
     */
    public static <T> T get(Optional<T> optional) {
        if (isNull(optional)) {
            return null;
        }
        return optional.orElse(null);
    }

    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param supplier the supplier
     * @return the t
     */
    public static <T> T get(Supplier<T> supplier) {
        if (supplier == null) {
            return null;
        }
        return supplier.get();
    }

    /**
     * 返回第一个通过Predicate判断的对象.
     *
     * @param <T> the generic type
     * @param predicate the predicate
     * @param objects the objects
     * @return 第一个通过Predicate判断的对象, 如果没有满足条件的，返回null
     */
    public static <T> T getFirst(Predicate<T> predicate, @SuppressWarnings("unchecked") T... objects) {
        return getFirst(objects, predicate);
    }

    /**
     * 返回第一个通过Predicate判断的对象.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @param predicate the predicate
     * @return 第一个通过Predicate判断的对象, 如果没有满足条件的，返回null
     */
    public static <T> T getFirst(T[] objects, Predicate<T> predicate) {
        if (objects == null) {
            return null;
        }
        for (T obj : objects) {
            if (predicate.test(obj)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * 返回第一个通过Predicate判断的对象.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @param predicate the predicate
     * @return 第一个通过Predicate判断的对象, 如果没有满足条件的，返回null
     */
    public static <T> T getFirst(Iterable<T> objects, Predicate<T> predicate) {
        if (objects == null) {
            return null;
        }
        for (T obj : objects) {
            if (predicate.test(obj)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * 返回最后一个通过Predicate判断的对象.
     *
     * @param <T> the generic type
     * @param predicate the predicate
     * @param objects the objects
     * @return 最后一个通过Predicate判断的对象, 如果没有满足条件的，返回null
     */
    public static <T> T getLast(Predicate<T> predicate, @SuppressWarnings("unchecked") T... objects) {
        return getLast(objects, predicate);
    }

    /**
     * 返回最后一个通过Predicate判断的对象.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @param predicate the predicate
     * @return 最后一个通过Predicate判断的对象, 如果没有满足条件的，返回null
     */
    public static <T> T getLast(T[] objects, Predicate<T> predicate) {
        if (objects == null) {
            return null;
        }
        for (int i = objects.length - 1; i >= 0; i--) {
            T t = objects[i];
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 返回最后一个通过Predicate判断的对象.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @param predicate the predicate
     * @return 最后一个通过Predicate判断的对象, 如果没有满足条件的，返回null
     */
    public static <T> T getLast(List<T> objects, Predicate<T> predicate) {
        if (objects == null) {
            return null;
        }
        for (int i = objects.size() - 1; i >= 0; i--) {
            T t = objects.get(i);
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 返回传入Optional是否为空（是null或者内部数据为null）.
     * <p>
     * <code>
     * optional == null || !optional.isPresent()
     * </code>
     * </p>
     *
     * @param optional the optional
     * @return 传入Optional是否为空
     */
    public static boolean isNull(Optional<?> optional) {
        return optional == null || !optional.isPresent();
    }

    /**
     * 返回传入Optional是否不为空.
     *
     * @param optional the optional
     * @return 传入Optional是否不为空
     */
    public static boolean isNotNull(Optional<?> optional) {
        return !isNull(optional);
    }

    /**
     * 返回传入Supplier是否为空（是null或者get()返回值为null） .
     *
     * @param supplier the supplier
     * @return 传入Optional是否为空
     */
    public static boolean isNull(Supplier<?> supplier) {
        return supplier == null || supplier.get() == null;
    }

    /**
     * 返回传入Supplier是否为空（是null或者get()返回值为null） .
     *
     * @param supplier the supplier
     * @return 传入Optional是否为空
     */
    public static boolean isNotNull(Supplier<?> supplier) {
        return !isNull(supplier);
    }

    /**
     * 返回传入对象是否为空，（String、Collection、Map、Array还要判断长度是否为0） .
     *
     * @param object the object
     * @return 传入对象是否为空
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return isEmpty((String) object);
        }
        if (object instanceof Collection) {
            return isEmpty((Collection<?>) object);
        }
        if (object instanceof Map) {
            return isEmpty((Map<?, ?>) object);
        }
        if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }
        if (object instanceof Optional) {
            return isEmpty((Optional<?>) object);
        }
        if (object instanceof Supplier) {
            return isEmpty((Supplier<?>) object);
        }
        return false;
    }

    /**
     * 返回传入对象是否不为空（String、Collection、Map、Array还要判断长度是否为0） .
     *
     * @param object the object
     * @return 传入对象是否不为空
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * ifTrue.
     *
     * @param <R> the generic type
     * @param bool the bool
     * @param isTrue the is true
     * @param isFalse the is false
     * @return obj
     */
    public static <R> R ifTrue(boolean bool, Supplier<R> isTrue, Supplier<R> isFalse) {
        if (bool) {
            return isTrue.get();
        } else {
            return isFalse.get();
        }
    }

    /**
     * ifTrue.
     *
     * @param <R> the generic type
     * @param decideSupplier the decide supplier
     * @param isTrue the is true
     * @param isFalse the is false
     * @return obj
     */
    public static <R> R ifTrue(BooleanSupplier decideSupplier, Supplier<R> isTrue, Supplier<R> isFalse) {
        return decideSupplier == null ? null : ifTrue(decideSupplier.getAsBoolean(), isTrue, isFalse);
    }

    /**
     * ifTrue.
     *
     * @param <R> the generic type
     * @param bool the bool
     * @param isFalse the is false
     * @param isTrue the is true
     * @return obj
     */
    public static <R> R ifFalse(boolean bool, Supplier<R> isFalse, Supplier<R> isTrue) {
        if (bool) {
            return isTrue.get();
        } else {
            return isFalse.get();
        }
    }

    /**
     * ifTrue.
     *
     * @param <R> the generic type
     * @param decideSupplier the decide supplier
     * @param isFalse the is false
     * @param isTrue the is true
     * @return obj
     */
    public static <R> R ifFalse(BooleanSupplier decideSupplier, Supplier<R> isFalse, Supplier<R> isTrue) {
        return decideSupplier == null ? null : ifFalse(decideSupplier.getAsBoolean(), isFalse, isTrue);
    }

    /**
     * 判断传入对象是否为empty,使用{@link #isEmpty(Object)}判断. 如果为空则返回defaultObject, 否则直接返回传入对象.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param defaultObject the default object
     * @return object
     */
    public static <O, R extends O> O ifEmpty(O object, R defaultObject) {
        if (Lang.isNotEmpty(object)) {
            return object;
        }
        return defaultObject;
    }

    /**
     * 判断传入对象是否为empty,使用{@link #isEmpty(Object)}判断. 如果为空则执行传入的supplier, 并返回其返回值, 否则直接返回传入对象.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param supplier the supplier
     * @return object
     */
    public static <O, R extends O> O ifEmpty(O object, Supplier<R> supplier) {
        if (Lang.isNotEmpty(object)) {
            return object;
        }
        return supplier.get();
    }

    /**
     * 判断传入对象是否为empty，使用{@link #isEmpty(Object)}判断， 如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return object
     */
    @SafeVarargs
    public static <O, R extends O> O ifEmpty(O object, Supplier<R>... suppliers) {
        if (Lang.isEmpty(object) && suppliers != null) {
            for (Supplier<R> supplier : suppliers) {
                R obj = supplier.get();
                if (isNotEmpty(obj)) {
                    return obj;
                }
            }
        }
        return object;
    }

    /**
     * 判断传入对象是否为empty，使用{@link #isEmpty(Object)}判断， 如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return object
     */
    public static <O, R extends O> O ifEmpty(O object, Iterable<Supplier<R>> suppliers) {
        if (Lang.isEmpty(object) && suppliers != null) {
            for (Supplier<R> supplier : suppliers) {
                R obj = supplier.get();
                if (isNotEmpty(obj)) {
                    return obj;
                }
            }
        }
        return object;
    }

    /**
     * 判断传入对象是否为empty,使用{@link #isEmpty(Object)}判断. 如果为空则执行传入supplier,并返回其执行值,否则直接返回传入对象.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param supplier the supplier
     * @return object
     */
    public static <O, R extends O> O ifEmpty(Supplier<O> object, Supplier<R> supplier) {
        return object == null ? null : ifEmpty(object.get(), supplier);
    }

    /**
     * 判断传入对象是否为empty，使用{@link #isEmpty(Object)}判断， 如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return object
     */
    @SafeVarargs
    public static <O, R extends O> O ifEmpty(Supplier<O> object, Supplier<R>... suppliers) {
        return object == null ? null : ifEmpty(object.get(), suppliers);
    }

    /**
     * 判断传入对象是否为empty，使用{@link #isEmpty(Object)}判断， 如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return object
     */
    public static <O, R extends O> O ifEmpty(Supplier<O> object, Iterable<Supplier<R>> suppliers) {
        return object == null ? null : ifEmpty(object.get(), suppliers);
    }

    /**
     * 判断传入对象是否为空，使用{@link #isEmpty(Object)}判断，
     * 如果为空返回参数emptyFunction的执行结果，否则返回参数notEmptyFunction的执行结果.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param emptyFunction the empty function
     * @param notEmptyFunction the not empty function
     * @return emptyFunction or notEmptyFunction execute result
     */
    public static <O, R> R ifEmptyOrElse(O object, Function<O, R> emptyFunction, Function<O, R> notEmptyFunction) {
        return isEmpty(object) ? emptyFunction.apply(object) : notEmptyFunction.apply(object);
    }

    /**
     * 判断传入对象是否为空，使用{@link #isEmpty(Object)}判断，
     * 如果为空返回参数nullSupplier的执行结果，否则返回参数notEmptyFunction的执行结果.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param emptySupplier the empty supplier
     * @param notEmptyFunction the not empty function
     * @return object
     */
    public static <O, R> R ifEmptyOrElse(O object, Supplier<R> emptySupplier, Function<O, R> notEmptyFunction) {
        return isEmpty(object) ? emptySupplier.get() : notEmptyFunction.apply(object);
    }

    /**
     * 返回第一个非空的对象，使用!=null判断逻辑.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 第一个非空的对象
     */
    public static <T> T ifNotNullFirst(@SuppressWarnings("unchecked") T... objects) {
        return getFirst(objects, Objects::nonNull);
    }

    /**
     * 返回第一个非空的对象，使用!=null判断逻辑.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 第一个非空的对象
     */
    public static <T> T ifNotNullFirst(Iterable<T> objects) {
        return getFirst(objects, Objects::nonNull);
    }

    /**
     * 返回最后一个非空的对象，使用!=null判断逻辑.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 最后一个非空的对象
     */
    public static <T> T ifNotNullLast(@SuppressWarnings("unchecked") T... objects) {
        return getLast(objects, Objects::nonNull);
    }

    /**
     * 返回最后一个非空的对象，使用!=null判断逻辑.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 最后一个非空的对象
     */
    public static <T> T ifNotNullLast(List<T> objects) {
        return getLast(objects, Objects::nonNull);
    }

    /**
     * Determines whether the passed object is not null. If it is not null, the passed function is
     * executed; otherwise,
     * null is returned. <br>
     * 判断传入对象是否不为null，不为空则执行传入的function，否则返回null.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param notNull the not null
     * @return null or notNull function return type
     */
    public static <O, R> R ifNotNull(O object, Function<O, R> notNull) {
        return object == null ? null : notNull.apply(object);
    }

    /**
     * Determines({@link #isNotEmpty(Object)}) whether the passed object is not empty. If it is not
     * empty, the result of
     * the function parameter is returned. If it is not empty, the result of the supplier parameter
     * is returned. <br>
     * 判断({@link #isNotEmpty(Object)})传入对象是否不为空，不为空则返回参数function的执行结果，否则返回参数supplier的执行结果.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param notNullFunction the not null function
     * @param nullSupplier the null supplier
     * @return notNullFunction or nullSupplier execute result
     */

    public static <O, R> R ifNotNullOrElse(O object, Function<O, R> notNullFunction, Supplier<R> nullSupplier) {
        return object == null ? nullSupplier.get() : notNullFunction.apply(object);
    }

    /**
     * 如果第一个参数为空(null），返回第二个参数，否则返回第一个参数 .
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param defaultObject the default object
     * @return object
     */
    public static <O, R extends O> O ifNull(O object, R defaultObject) {
        return object == null ? defaultObject : object;
    }

    /**
     * 判断传入对象是否为null，如果为空则执行传入的supplier, 并返回其返回值,否则直接返回传入对象.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param supplier the supplier
     * @return object
     */
    public static <O, R extends O> O ifNull(O object, Supplier<R> supplier) {
        return object == null ? supplier.get() : object;
    }

    /**
     * 判断传入对象是否为null，如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return object
     */
    @SafeVarargs
    public static <O, R extends O> O ifNull(O object, Supplier<R>... suppliers) {
        if (object == null && suppliers != null) {
            for (Supplier<R> supplier : suppliers) {
                R obj = supplier.get();
                if (obj != null) {
                    return obj;
                }
            }
        }
        return object;
    }

    /**
     * 判断传入对象是否为null，如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return object
     */
    public static <O, R extends O> O ifNull(O object, Iterable<Supplier<R>> suppliers) {
        if (object == null && suppliers != null) {
            for (Supplier<R> supplier : suppliers) {
                R obj = supplier.get();
                if (obj != null) {
                    return obj;
                }
            }
        }
        return object;
    }

    /**
     * 判断传入对象是否为null，如果为空则执行传入的方法, 并返回其返回值.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param supplier the supplier
     * @return object
     */
    public static <O, R extends O> O ifNull(Supplier<O> object, Supplier<R> supplier) {
        return object == null ? null : ifNull(object.get(), supplier);
    }

    /**
     * 判断传入对象是否为null，如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return the o
     */
    @SafeVarargs
    public static <O, R extends O> O ifNull(Supplier<O> object, Supplier<R>... suppliers) {
        return object == null ? null : ifNull(object.get(), suppliers);
    }

    /**
     * 判断传入对象是否为null，如果为空则迭代suppliers并执行，如果其返回对象不是null，则返回.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param suppliers the suppliers
     * @return the o
     */
    public static <O, R extends O> O ifNull(Supplier<O> object, Iterable<Supplier<R>> suppliers) {
        return object == null ? null : ifNull(object.get(), suppliers);
    }

    /**
     * 判断传入对象是否为null，如果为null返回参数nullSupplier的执行结果，否则返回参数notNullFunction的执行结果.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param nullSupplier the null supplier
     * @param notNullFunction the not null function
     * @return nullSupplier or notNullFunction execute result
     */
    public static <O, R> R ifNullOrElse(O object, Supplier<R> nullSupplier, Function<O, R> notNullFunction) {
        return object == null ? nullSupplier.get() : notNullFunction.apply(object);
    }

    /**
     * 返回第一个非空的对象，使用{@link #isNotEmpty(Object)}判断逻辑.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 第一个非空的对象
     */
    public static <T> T ifNotEmptyFirst(@SuppressWarnings("unchecked") T... objects) {
        return getFirst(objects, Lang::isNotEmpty);
    }

    /**
     * 返回第一个非空的对象，使用{@link #isNotEmpty(Object)}判断逻辑.
     *
     * @param <T> the generic type
     * @param objects the objects
     * @return 第一个非空的对象
     */
    public static <T> T ifNotEmptyFirst(Iterable<T> objects) {
        return getFirst(objects, Lang::isNotEmpty);
    }

    /**
     * 返回第一个非空的对象，使用{@link #isNotEmpty(Object)}判断逻辑.
     *
     * @param <T> the generic type
     * @param suppliers the suppliers
     * @return 第一个非空的对象
     */
    public static <T> T ifNotEmptyFirst(@SuppressWarnings("unchecked") Supplier<T>... suppliers) {
        if (suppliers == null) {
            return null;
        }
        for (Supplier<T> supplier : suppliers) {
            T obj = get(supplier);
            if (isNotEmpty(obj)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Check ({@link #isNotEmpty(Object)}) whether the passed object is not empty. If it is not
     * empty, the passed
     * function is executed; otherwise, null is returned.
     * 判断({@link #isNotEmpty(Object)})传入对象是否不为空，不为空则执行传入的function，否则返回null.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param notEmptyFunction the not empty function
     * @return null or notEmptyFunction function return type
     */
    public static <O, R> R ifNotEmpty(O object, Function<O, R> notEmptyFunction) {
        return Lang.isEmpty(object) ? null : notEmptyFunction.apply(object);
    }

    /**
     * Check ({@link #isNotEmpty(Object)}) whether the passed object is not empty. If it is not
     * empty, the result of the
     * first argument (notEmptyFunction) is returned. * Otherwise, the result of the second argument
     * (emptyFunction) is
     * returned. <br>
     * 判断({@link #isNotEmpty(Object)})传入对象是否不为空，不为空则返回第一个参数(notEmptyFunction)的执行结果，
     * 否则返回第二个参数(emptyFunction)的执行结果.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param notEmptyFunction the not empty function
     * @param emptyFunction the empty function
     * @return notEmptyFunction or emptyFunction execute result
     */
    public static <O, R> R ifNotEmptyOrElse(O object, Function<O, R> notEmptyFunction, Function<O, R> emptyFunction) {
        return Lang.isEmpty(object) ? emptyFunction.apply(object) : notEmptyFunction.apply(object);
    }

    /**
     * Check ({@link #isNotEmpty(Object)}) whether the passed object is not empty. If it is not
     * empty, the result of the
     * first argument (notEmptyFunction) is returned. * Otherwise, the result of the second argument
     * (emptySupplier) is
     * returned. <br>
     * 判断({@link #isNotEmpty(Object)})传入对象是否不为空，不为空则返回第一个参数(notEmptyFunction)的执行结果，
     * 否则返回第二个参数(emptySupplier)的执行结果.
     *
     * @param <O> the generic type
     * @param <R> the generic type
     * @param object the object
     * @param notEmptyFunction the not empty function
     * @param emptySupplier the empty supplier
     * @return notEmptyFunction or emptySupplier execute result
     */
    public static <O, R> R ifNotEmptyOrElse(O object, Function<O, R> notEmptyFunction, Supplier<R> emptySupplier) {
        return Lang.isEmpty(object) ? emptySupplier.get() : notEmptyFunction.apply(object);
    }

    /**
     * 判断传入对象是否不为空，（String、Collection、Map、Array还要判断长度是否为0），如果不为空则执行传入的方法 .
     *
     * @param <O> the generic type
     * @param object 传入的对象
     * @param consumer 需要执行的方法
     */
    public static <O> void ifNotEmpty(O object, Consumer<O> consumer) {
        if (Lang.isNotEmpty(object)) {
            consumer.accept(object);
        }
    }

    /**
     * 返回传入Optional是否为空（是null或者内部数据为空(使用isEmpty判断内部数据)） .
     *
     * @param optional the optional
     * @return 传入Optional是否为空
     */
    public static boolean isEmpty(Optional<?> optional) {
        if (isNull(optional)) {
            return true;
        }
        return isEmpty(optional.orElse(null));
    }

    /**
     * 返回传入Supplier是否为空（是null或者get()返回值为null） .
     *
     * @param supplier the supplier
     * @return 传入Optional是否为空
     */
    public static boolean isEmpty(Supplier<?> supplier) {
        if (supplier == null) {
            return true;
        }
        return isEmpty(supplier.get());
    }

    /**
     * 返回传入Optional是否不为空（不是null或者内部数据不为null） .
     *
     * @param optional the optional
     * @return 传入Optional是否为空
     */
    public static boolean isNotEmpty(Optional<?> optional) {
        return !isEmpty(optional);
    }

    /**
     * 返回传入Supplier是否不为空（不是null或者get()返回值不为null） .
     *
     * @param supplier the supplier
     * @return Supplier is not empty
     */
    public static boolean isNotEmpty(Supplier<?> supplier) {
        return !isEmpty(supplier);
    }

    /**
     * 返回传入字符串是否为空（是null或是空字符串） .
     *
     * @param string the string
     * @return 传入字符串是否为空
     */
    public static boolean isEmpty(String string) {
        return Str.isEmpty(string);
    }

    /**
     * 返回传入字符串是否不为空（不是null或不是空字符串） .
     *
     * @param string the string
     * @return 传入字符串是否不为空
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 返回数组是否为空（是null或是空数组） .
     *
     * @param <E> the element type
     * @param array the array
     * @return 传入数组是否为空
     */
    public static <E> boolean isEmpty(E[] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 返回数组是否不为空（null或空数组） .
     *
     * @param array the array
     * @return 传入数组是否不为空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 返回传入集合是否为空（是null或size=0） .
     *
     * @param collection the collection
     * @return 传入集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 返回传入集合是否不为空（不是null或size&gt;0） .
     *
     * @param collection the collection
     * @return 传入集合是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 返回传入map是否为空（是null或size=0） .
     *
     * @param map the map
     * @return 传入map是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return CollectionUtils.isEmpty(map);
    }

    /**
     * 返回传入map是否不为空（不是null或size&gt;0） .
     *
     * @param map the map
     * @return 传入map是否不为空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Checks if is true.
     *
     * @param value the value
     * @return true, if is true
     */
    public static boolean isTrue(Boolean value) {
        return value != null && value;
    }

    /**
     * Checks if is null or true.
     *
     * @param value the value
     * @return true, if is null or true
     */
    public static boolean isNullOrTrue(Boolean value) {
        return value == null || value;
    }

    /**
     * Checks if is false.
     *
     * @param value the value
     * @return true, if is false
     */
    public static boolean isFalse(Boolean value) {
        return value != null && !value;
    }

    /**
     * Checks if is null or false.
     *
     * @param value the value
     * @return true, if is null or false
     */
    public static boolean isNullOrFalse(Boolean value) {
        return value == null || !value;
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在 .
     *
     * @param file the file
     * @return 传入文件对象代表的物理文件是否存在
     */
    public static boolean isExists(File file) {
        return file != null && file.exists();
    }

    /**
     * 判断传入文件对象代表的物理文件是否不存在 .
     *
     * @param file the file
     * @return 传入文件对象代表的物理文件是否存在
     */
    public static boolean isNotExists(File file) {
        return !isExists(file);
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，存在则执行传入方法 .
     *
     * @param file 判断的文件
     * @param consumer 需要执行的方法
     */
    public static void ifExists(File file, Consumer<File> consumer) {
        if (isExists(file)) {
            consumer.accept(file);
        }
    }

    /**
     * 判断传入文件对象代表的物理文件是否存在，存在则执行传入方法 .
     *
     * @param <R> the generic type
     * @param file the file
     * @param exists the exists
     * @param notExists the not exists
     * @return obj
     */
    public static <R> R ifExists(File file, Function<File, R> exists, Function<File, R> notExists) {
        if (isExists(file)) {
            return exists.apply(file);
        } else {
            return notExists.apply(file);
        }
    }

    /**
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法 .
     *
     * @param file 判断的文件
     * @param consumer 需要执行的方法
     */
    public static void ifNotExists(File file, Consumer<File> consumer) {
        if (isNotExists(file)) {
            consumer.accept(file);
        }
    }

    /**
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法 .
     *
     * @param <R> the generic type
     * @param file the file
     * @param notExists the not exists
     * @param exists the exists
     * @return obj
     */
    public static <R> R ifNotExists(File file, Function<File, R> notExists, Function<File, R> exists) {
        if (isNotExists(file)) {
            return notExists.apply(file);
        } else {
            return exists.apply(file);
        }
    }

    /**
     * 判断传入文件对象代表的物理文件是否不存在，不存在则执行传入方法 .
     *
     * @param <R> the generic type
     * @param file the file
     * @param supplier the supplier
     * @return obj
     */
    public static <R> R ifNotExists(File file, Supplier<R> supplier) {
        if (isNotExists(file)) {
            return supplier.get();
        }
        return null;
    }

    /**
     * 将传入对象转换为枚举 .
     *
     * @param <T> the generic type
     * @param toClass the to class
     * @param object the object
     * @return 转换后的枚举，如果是无法转换或不存在的枚举类型，则返回null
     */
    public static <T extends Enum<T>> T toEnum(Class<T> toClass, Object object) {
        if (object == null) {
            return null;
        }
        T result = null;
        for (EnumConvertor enumConvertor : enumConvertors) {
            result = enumConvertor.toEnum(toClass, object);
            if (result != null) {
                return result;
            }
        }
        return toEnum0(toClass, object);
    }

    /**
     * 将传入对象转换为枚举 .
     *
     * @param <T> the generic type
     * @param toClass the to class
     * @param object the object
     * @return 转换后的枚举，如果是无法转换或不存在的枚举类型，则返回null
     */
    public static <T extends Enum<T>> T toEnum0(Class<T> toClass, Object object) {
        if (object != null) {
            if (object instanceof Enum) {
                return toEnum(toClass, (Enum<?>) object);
            } else if (object instanceof String) {
                return toEnum(toClass, (String) object);
            } else if (object instanceof Integer || object.getClass() == int.class) {
                return toEnum(toClass, (Integer) object);
            } else if (object instanceof String[]) {
                return toEnum(toClass, ((String[]) object)[0]);
            } else if (object instanceof Byte || object.getClass() == byte.class) {
                Byte ordinal = (Byte) object;
                return toEnum(toClass, Integer.valueOf(ordinal));
            } else if (object instanceof Short || object.getClass() == short.class) {
                Short ordinal = (Short) object;
                return toEnum(toClass, Integer.valueOf(ordinal));
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> T toEnum(Class<T> toClass, Enum<?> value) {
        if (value.getClass() == toClass) {
            return (T) value;
        } else {
            return Enum.valueOf(toClass, value.name());
        }
    }

    private static <T extends Enum<T>> T toEnum(Class<T> toClass, String value) {
        if (StringUtils.isNumeric(value)) {
            int ordinal = Integer.parseInt(value);
            return toEnum(toClass, ordinal);
        } else {
            return Enum.valueOf(toClass, value);
        }
        // 优化逻辑，try catch 性能不行
        //        try {
        //            int ordinal = Integer.parseInt(value);
        //            return toEnum(toClass, ordinal);
        //        } catch (NumberFormatException e) {
        //            return Enum.valueOf(toClass, value);
        //        }
    }

    private static <T extends Enum<T>> T toEnum(Class<T> toClass, Integer ordinal) {
        T[] es = toClass.getEnumConstants();
        for (T e : es) {
            if (e.ordinal() == ordinal) {
                return e;
            }
        }
        return null;
    }

    /**
     * 安全的equals，防止空指针异常 .
     *
     * @param target the target
     * @param otherTarget the other target
     * @return 比较结果
     */
    public static boolean equals(Object target, Object otherTarget) {
        return target == otherTarget || target != null && target.equals(otherTarget);
    }

    /**
     * 返回hash code，如果传入参数为null,返回0.
     *
     * @param o the o
     * @return hash code
     * @see Object#hashCode
     */
    public static int hashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    /**
     * 转换为String，如果不能转换（null）则使用默认值 .
     *
     * @param obj the obj
     * @param defaultValue the default value
     * @return string
     */
    public static String toString(Object obj, String defaultValue) {
        return obj != null ? obj.toString() : defaultValue;
    }

    /**
     * 转换为String，如果不能转换（null）则返回null .
     *
     * @param obj the obj
     * @return string
     */
    public static String toString(Object obj) {
        return toString(obj, null);
    }

    /**
     * 获取调用getInvoker方法所在的方法被调用的信息（即调用方法、类等）. 在main方法中调用该方法会返回null,因为main方法是入口方法，没有其他方法会调用他.
     *
     * @return StackTraceElement
     */
    public static StackTraceElement getInvoker() {
        return getInvoker(3);
    }

    /**
     * 获取调用getInvoker方法所在的方法被调用的信息（即调用方法、类等） .
     *
     * @param depth the depth
     * @return StackTraceElement
     */
    public static StackTraceElement getInvoker(int depth) {
        AssertIllegalArgument.isGt(depth, 0, "depth");
        StackTraceElement[] ss = Thread.currentThread().getStackTrace();
        AssertIllegalArgument.isLt(depth, ss.length, "depth");
        final String methodName = "getInvoker";
        int i = depth;
        for (StackTraceElement stackTraceElement : ss) {
            if (stackTraceElement.getClassName().equals(Lang.class.getName())
                && stackTraceElement.getMethodName().equals(methodName) && ss.length > i) {
                return ss[i];
            }
            i++;
        }
        return null;
    }

    /**
     * 获取调用此方法的调用方法栈 .
     *
     * @return List&lt;StackTraceElement&gt;
     */
    public static List<StackTraceElement> getInvokers() {
        final String methodName = "getInvokers";
        List<StackTraceElement> invokers = new ArrayList<>();
        StackTraceElement[] ss = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : ss) {
            if (stackTraceElement.getClassName().equals(Lang.class.getName())
                && stackTraceElement.getMethodName().equals(methodName)) {
                Collections.addAll(invokers, ss);
                return invokers;
            }
        }
        return invokers;
    }

    /**
     * 转换为数组. 如果传入集合为空（null或者size=0）或者集合内的对象都是null,返回null.
     *
     * @param <A> the generic type
     * @param collection the collection
     * @return 数组
     */
    public static <A> A[] toArray(Collection<A> collection) {
        return CollectionUtils.toArray(collection);
    }

    /**
     * <p>
     * 转换为数组. 如果传入集合为空（null或者size=0），返回长度为0的数组（不会返回null）.
     * </p>
     *
     * @param <A> the generic type
     * @param collection the collection
     * @param type the type
     * @return 数组
     */
    public static <A> A[] toArray(Collection<A> collection, Class<A> type) {
        return CollectionUtils.toArray(collection, type);
    }

    /**
     * <p>
     * 转换为列表
     * </p>
     * .
     *
     * @param <A> the generic type
     * @param arrays the arrays
     * @return 列表
     */
    public static <A> List<A> toList(A[] arrays) {
        return ArrayUtils.toList(arrays);
    }

    /**
     * 转换为以数组索引为key,数组值为value的map.
     *
     * @param <A> the generic type
     * @param arrays the arrays
     * @return map
     */
    public static <A> Map<Integer, A> toMap(A[] arrays) {
        return ArrayUtils.toMap(arrays);
    }

    /**
     * 转换为以数组索引为key,数组值为value的map.
     *
     * @param <A> the generic type
     * @param arrays the arrays
     * @return map
     * @deprecated use {@link #toMapStringKey(Object...)} instead
     */
    @Deprecated
    public static <A> Map<String, A> toMap2(A[] arrays) {
        return toMapStringKey(arrays);
    }

    /**
     * 转换为以数组索引为key(string类型),数组值为value的map.
     *
     * @param <A> the generic type
     * @param arrays the arrays
     * @return 列表
     */
    public static <A> Map<String, A> toMapStringKey(@SuppressWarnings("unchecked") A... arrays) {
        return ArrayUtils.toMapStringKey(arrays);
    }

    /**
     * Wrap throw.
     *
     * @param throwable the throwable
     */
    public static void wrapThrow(Throwable throwable) {
        AssertIllegalArgument.isNotNull(throwable, "throwable");
        WRAPPER.throwException(throwable);
    }

    /**
     * Wrap throw.
     *
     * @param ioException the io exception
     */
    public static void wrapThrow(java.io.IOException ioException) {
        AssertIllegalArgument.isNotNull(ioException, "ioException");
        throw new IOException(ioException);
    }

    /**
     * Wrap throw.
     *
     * @param <E> the element type
     * @param throwable the throwable
     * @param wrappedRuntimeException the wrapped runtime exception
     */
    public static <E extends RuntimeException> void wrapThrow(Throwable throwable, Class<E> wrappedRuntimeException) {
        AssertIllegalArgument.isNotNull(throwable, "throwable");
        AssertIllegalArgument.isNotNull(wrappedRuntimeException, "wrappedRuntimeException");
        new ExceptionWrapper<>(wrappedRuntimeException).throwException(throwable);
    }

    /**
     * Each.
     *
     * @param <T> the generic type
     * @param consumer the consumer
     * @param array the array
     */
    public static <T> void each(ObjIntConsumer<T> consumer, @SuppressWarnings("unchecked") T... array) {
        ArrayUtils.each(consumer, array);
    }

    /**
     * Each.
     *
     * @param <T> the generic type
     * @param array the array
     * @param consumer the consumer
     */
    public static <T> void each(T[] array, ObjIntConsumer<T> consumer) {
        ArrayUtils.each(consumer, array);
    }

    /**
     * Each.
     *
     * @param <T> the generic type
     * @param iterable the iterable
     * @param consumer the consumer
     */
    public static <T> void each(Iterable<T> iterable, ObjIntConsumer<T> consumer) {
        CollectionUtils.each(iterable, consumer);
    }

    /**
     * Each.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param map the map
     * @param consumer the consumer
     */
    public static <K, V> void each(Map<K, V> map, ObjIntConsumer<Entry<K, V>> consumer) {
        CollectionUtils.each(map, consumer);
    }

    /**
     * If a given object is iterable(Iterable or array or Map), iterate over it and pass each item
     * to the consumer. otherwise pass object to the consumer once.
     *
     * @param obj the array
     * @param consumer the consumer
     */
    public static void eachObj(Object obj, Consumer<Object> consumer) {
        if (obj == null) {
            consumer.accept(obj);
        } else if (obj instanceof Iterable) {
            for (Object o : (Iterable<?>) obj) {
                consumer.accept(o);
            }
        } else if (obj.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(obj); i++) {
                consumer.accept(Array.get(obj, i));
            }
        } else if (obj instanceof Map) {
            for (Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                consumer.accept(entry);
            }
        } else {
            consumer.accept(obj);
        }
    }

    /**
     * If a given object is iterable(Iterable or array or Map), iterate over it and pass each item
     * to the consumer. otherwise pass object to the consumer once.
     *
     * @param obj the array
     * @param consumer the consumer
     */
    @SuppressWarnings("unchecked")
    public static void eachObj(Object obj, ObjIntConsumer<Object> consumer) {
        if (obj == null) {
            consumer.accept(null, 0);
        } else if (obj instanceof Iterable) {
            CollectionUtils.each((Iterable<Object>) obj, consumer);
        } else if (obj.getClass().isArray()) {
            ArrayUtils.each(obj, consumer);
        } else if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            int i = 0;
            for (Entry<?, ?> entry : map.entrySet()) {
                consumer.accept(entry, i);
                i++;
            }
        } else {
            consumer.accept(obj, 0);
        }
    }

    /**
     * create new array.
     *
     * @param <T> the generic type
     * @param array the array
     * @return the t[]
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] array(T... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the int[]
     */
    public static int[] array(int... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the long[]
     */
    public static long[] array(long... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the short[]
     */
    public static short[] array(short... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the byte[]
     */
    public static byte[] array(byte... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the char[]
     */
    public static char[] array(char... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the boolean[]
     */
    public static boolean[] array(boolean... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the double[]
     */
    public static double[] array(double... array) {
        return array;
    }

    /**
     * create new array.
     *
     * @param array the array
     * @return the float[]
     */
    public static float[] array(float... array) {
        return array;
    }

    /**
     * craete new list.
     *
     * @see {@link CollectionUtils#list(Object...)}
     * @param <E> the element type
     * @param elements the elements
     * @return the list
     */
    public static <E> List<E> list(@SuppressWarnings("unchecked") E... elements) {
        return CollectionUtils.list(elements);
    }

    /**
     * create new set.
     *
     * @see {@link CollectionUtils#set(Object...)}
     * @param <E> the element type
     * @param elements the elements
     * @return the set
     */
    public static <E> Set<E> set(@SuppressWarnings("unchecked") E... elements) {
        return CollectionUtils.set(elements);
    }

    /**
     * create new map.
     *
     * @see {@link CollectionUtils#map(Object, Object)}
     * @param <K> the key type
     * @param <V> the value type
     * @param key the key
     * @param value the value
     * @return the map
     */
    public static <K, V> ChainMap<K, V> map(K key, V value) {
        return CollectionUtils.map(key, value);
    }
}
