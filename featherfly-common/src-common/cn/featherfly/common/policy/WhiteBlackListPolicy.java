
package cn.featherfly.common.policy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 白黑名单上传策略，白名单优先级更高
 * </p>
 * @param <T> 需要判断的类型
 * @author 钟冀
 */
public abstract class WhiteBlackListPolicy<T> implements WhiteListPolicy<T>, BlackListPolicy<T>{

    /**
     */
    public WhiteBlackListPolicy() {
    }

    private List<T> blackList = new ArrayList<T>();

    private List<T> whiteList = new ArrayList<T>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlackList(List<T> policys) {
        this.blackList = policys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWhiteList(List<T> policys) {
        this.whiteList = policys;
    }

    /**
     * 返回blackList
     * @return blackList
     */
    public List<T> getBlackList() {
        return blackList;
    }

    /**
     * 返回whiteList
     * @return whiteList
     */
    public List<T> getWhiteList() {
        return whiteList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAllow(T target) {
        if (isInBlackList(target)) {
            return false;
        }
        return isInWhiteList(target); 
    }
    /**
     * <p>
     * 判断传入目标是否一致
     * </p>
     * @param target1 target1
     * @param target2 target2
     * @return 传入目标是否一致
     */
    protected abstract boolean isEquals(T target1, T target2);
    /**
     * <p>
     * 判断传入目标是否在黑名单中
     * </p>
     * @param target target
     * @return 是否在黑名单
     */
    protected boolean isInBlackList(T target) {
        for (T t : blackList) {
            if (isEquals(target, t)) {
                return true;
            }
        }
        return false;
    }
    /**
     * <p>
     * 判断传入目标是否在白名单中
     * </p>
     * @param target target
     * @return 是否在白名单
     */
    protected boolean isInWhiteList(T target) {
        for (T t : whiteList) {
            if (isEquals(target, t)) {
                return true;
            }
        }
        return false;
    }
}
