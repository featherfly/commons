package cn.featherfly.common.spring.jdbc.datasouce;


/**
 * <pre>
 * 读/写动态数据库 决策者
 * 根据DataSourceType是write/read 来决定是使用读/写数据库
 * 通过ThreadLocal绑定实现选择功能
 * </pre>
 */
public final class ReadWriteDataSourceDecision {
    
    /**
     */
    private ReadWriteDataSourceDecision() {
    }

    /**
     * <p>
     * DataSourceType
     * </p>
     * 
     * @author zhongj
     */
    public enum DataSourceType {
        /**
         * 写
         */
        WRITE,
        /**
         * 读
         */
        READ;
    }
    
    
    private static final ThreadLocal<DataSourceType> HOLDER = new ThreadLocal<DataSourceType>();

    /**
     * <p>
     * 标记写
     * </p>
     */
    public static void markWrite() {
        HOLDER.set(DataSourceType.WRITE);
    }
    
    /**
     * <p>
     * 标记读
     * </p>
     */
    public static void markRead() {
        HOLDER.set(DataSourceType.READ);
    }
    
    /**
     * <p>
     * 重置标记
     * </p>
     */
    public static void reset() {
        HOLDER.set(null);
    }
    
    /**
     * <p>
     * 是否标记
     * </p>
     * @return 是否标记
     */
    public static boolean isChoiceNone() {
        return null == HOLDER.get(); 
    }
    
    /**
     * <p>
     * 是否标记写
     * </p>
     * @return 是否标记写
     */
    public static boolean isChoiceWrite() {
        return DataSourceType.WRITE == HOLDER.get();
    }
    
    /**
     * <p>
     * 是否标记读
     * </p>
     * @return 是否标记读
     */
    public static boolean isChoiceRead() {
        return DataSourceType.READ == HOLDER.get();
    }

}
