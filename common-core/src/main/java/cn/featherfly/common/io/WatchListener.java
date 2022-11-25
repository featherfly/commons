
package cn.featherfly.common.io;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * FileListener.
 *
 * @author zhongj
 */
@FunctionalInterface
public interface WatchListener {

    void onEvent(WatchEvent<Path> event, Path watchDir);
}
