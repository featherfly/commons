package cn.featherfly.common.lang.executor;

/**
 * <p>
 * 执行者
 * </p>
 * 
 * @author 钟冀
 * @since 1.6
 * @version 1.0
 */
public interface Executor {
    Executor execute(Executable executable);
}
