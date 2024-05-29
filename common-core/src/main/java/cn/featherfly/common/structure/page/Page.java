package cn.featherfly.common.structure.page;

/**
 * 分页模型.
 *
 * @author zhongj
 */
public interface Page {

    /**
     * 获取每页数量.
     *
     * @return 每页数量
     */
    int getSize();

    /**
     * 获取当前页数（第几页）.
     *
     * @return 当前页数（第几页）
     */
    int getNumber();
}
