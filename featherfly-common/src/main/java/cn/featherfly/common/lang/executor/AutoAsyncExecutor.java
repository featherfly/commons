package cn.featherfly.common.lang.executor;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 执行者实现,如果设置了线程池则使用异步调用,否则同步调用.
 * </p>
 * 
 * @author zhongj
 * @since 1.6
 * @version 1.0
 */
public class AutoAsyncExecutor implements Executor{

    
    /**
     * 
     */
    public AutoAsyncExecutor() {
        super();
    }

    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Executor execute(Executable executable) {
        if (executorService != null) {
            logger.debug("execute async");
            executorService.execute(() -> {
                executable.execute();
            });
        } else {
            logger.debug("execute sync");
            executable.execute();
        }
        return this;
    }

    /**
     * <p>
     * 销毁
     * </p>
     */
    public void destroy() {
        executorService.shutdown();
    }

    private ExecutorService executorService;

    /**
     * 返回executorService
     * @return executorService
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * 设置executorService
     * @param executorService executorService
     */
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
