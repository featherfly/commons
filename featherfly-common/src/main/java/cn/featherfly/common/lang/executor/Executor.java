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
    /**
     * <p>
     * 执行
     * </p>
     * @param executable 执行内容
     * @return this
     */
    Executor execute(Executable executable);
}
