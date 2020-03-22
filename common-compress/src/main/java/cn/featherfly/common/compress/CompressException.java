
package cn.featherfly.common.compress;

/**
 * <p>
 * 压缩、解压异常
 * </p>
 * 
 * @author 钟冀
 */
public class CompressException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     */
    public CompressException() {
        super();
    }

    /**
     * 构造方法
     * 
     * @param msg
     *            信息
     * @param ex
     *            异常
     */
    public CompressException(String msg, Throwable ex) {
        super(msg, ex);
    }

    /**
     * 构造方法
     * 
     * @param msg
     *            信息
     */
    public CompressException(String msg) {
        super(msg);
    }

    /**
     * 构造方法
     * 
     * @param ex
     *            异常
     */
    public CompressException(Throwable ex) {
        super(ex);
    }
}
