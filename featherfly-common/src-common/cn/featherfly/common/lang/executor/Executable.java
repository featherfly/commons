package cn.featherfly.common.lang.executor;

/**
 * 可执行接口
 */
@FunctionalInterface
public interface Executable {
    /**
     * 执行
     */
    void execute();
}
