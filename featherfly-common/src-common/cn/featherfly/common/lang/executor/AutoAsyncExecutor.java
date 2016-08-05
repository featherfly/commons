package cn.featherfly.common.lang.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

/**
 * 执行者实现,如果设置了线程池则使用异步调用,否则同步调用.
 */
public class AutoAsyncExecutor implements Executor{

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

    public void destroy() {
        executorService.shutdown();
    }

    private ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
