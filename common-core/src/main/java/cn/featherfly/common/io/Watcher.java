
package cn.featherfly.common.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.LogUtils;

/**
 * Watcher.
 *
 * @author zhongj
 */
public class Watcher {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WatchListener listener;

    private Path directory;

    private WatchKey watchKey;

    private WatchEvent.Kind<?>[] events;

    /**
     * Instantiates a new watcher.
     */
    public Watcher() {
    }

    /**
     * Instantiates a new watcher.
     *
     * @param directory the directory
     * @param listener  the listener
     * @param events    the events
     */
    public Watcher(Path directory, WatchListener listener, WatchEvent.Kind<?>... events) {
        super();
        this.listener = listener;
        this.directory = directory;
        this.events = events;
    }

    /**
     * 返回listener.
     *
     * @return listener
     */
    public WatchListener getListener() {
        return listener;
    }

    /**
     * 设置listener.
     *
     * @param listener listener
     */
    public void setListener(WatchListener listener) {
        this.listener = listener;
    }

    /**
     * 返回directory.
     *
     * @return directory
     */
    public Path getDirectory() {
        return directory;
    }

    /**
     * 设置directory.
     *
     * @param directory directory
     */
    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    /**
     * 返回events.
     *
     * @return events
     */
    public WatchEvent.Kind<?>[] getEvents() {
        return events;
    }

    /**
     * 设置events.
     *
     * @param events events
     */
    public void setEvents(WatchEvent.Kind<?>[] events) {
        this.events = events;
    }

    /**
     * Watch.
     */
    public void watch() {
        // 监听新文件
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, events);
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
