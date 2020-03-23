package cn.featherfly.common.lang.function;

import java.io.Serializable;
import java.util.function.Consumer;

@FunctionalInterface
public interface SerializableConsumer<T> extends Serializable, Consumer<T> {

}