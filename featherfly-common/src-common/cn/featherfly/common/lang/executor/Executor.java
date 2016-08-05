package cn.featherfly.common.lang.executor;

/**
 * 执行者
 */
public interface Executor {
    Executor execute(Executable executable);
}
