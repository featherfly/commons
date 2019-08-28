
package cn.featherfly.common.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.LogUtils;

/**
 * <p>
 * Watcher
 * </p>
 * <p>
 * 2019-08-28
 * </p>
 *
 * @author zhongj
 */
public class Watcher {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WatchListener listener;

    private Path directory;

    private WatchKey watchKey;

    public Watcher() {
    }

    /**
     * @param listener
     * @param directory
     */
    public Watcher(Path directory, WatchListener listener) {
        super();
        this.listener = listener;
        this.directory = directory;
    }

    /**
     * 返回listener
     *
     * @return listener
     */
    public WatchListener getListener() {
        return listener;
    }

    /**
     * 设置listener
     *
     * @param listener listener
     */
    public void setListener(WatchListener listener) {
        this.listener = listener;
    }

    /**
     * 返回directory
     *
     * @return directory
     */
    public Path getDirectory() {
        return directory;
    }

    /**
     * 设置directory
     *
     * @param directory directory
     */
    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    public void watch() {
        // 监听新文件
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.OVERFLOW);
            @SuppressWarnings("unchecked")
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        watchKey = watchService.take();
                        List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                        for (WatchEvent<?> event : watchEvents) {
                            listener.onEvent((WatchEvent<Path>) event, directory);
                        }
                        watchKey.reset();
                    }
                } catch (InterruptedException e) {
                    LogUtils.error(e, logger);
                }
            });
            thread.setDaemon(false);
            thread.start();

            // 增加jvm关闭的钩子来关闭监听
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    watchService.close();
                } catch (Exception e) {
                }
            }));
        } catch (IOException e) {
            throw new cn.featherfly.common.exception.IOException(e);
        }
    }
}
