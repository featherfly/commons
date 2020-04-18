package cn.featherfly.common.repository.mapping;

/**
 * <p>
 * 记录行映射接口
 * </p>
 *
 * @param <E> 要映射的具体类
 * @author zhongj
 */
@FunctionalInterface
public interface RowMapper<E> {
    /**
     * <p>
     * 映射记录到指定的对象
     * </p>
     *
     * @param res    数据集
     * @param rowNum 行数
     * @return 记录映射的对象
     */
    E mapRow(ResultSet res, int rowNum);
}