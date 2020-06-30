package cn.featherfly.common.lang.function;

/**
 * <p>
 * GetFunction
 * </p>
 *
 * @author zhongj
 */
@FunctionalInterface
// FIXME 这里的入参Void是有问题的，编译器识别不了get方法，需要确认编译器识别get方法（即无参数方法）lambda时的入参泛型
public interface GetFunction<R> extends SerializableFunction<Void, R> {

}