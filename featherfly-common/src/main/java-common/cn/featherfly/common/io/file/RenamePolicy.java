package cn.featherfly.common.io.file;

/**
 * <p>
 * 重命名策略
 * </p>
 *
 * @author 钟冀
 */
public interface RenamePolicy {

    /**
     * <p>
     * 重命名
     * </p>
     * @param fileName 名称
     * @return 重命名后的名称
     */
    String rename(String fileName);
}
