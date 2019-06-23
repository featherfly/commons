
package cn.featherfly.common.policy;

import java.util.Collection;
import java.util.HashSet;

import cn.featherfly.common.lang.CollectionUtils;

/**
 * <p>
 * 白黑名单策略，黑名单优先级更高
 * </p>
 * 
 * @param <T>
 *            需要判断的类型
 * @author 钟冀
 */
public abstract class WhiteBlackListPolicy<T>
        implements WhiteListPolicy<T, WhiteBlackListPolicy<T>>, BlackListPolicy<T, WhiteBlackListPolicy<T>> {

    /**
     */
    public WhiteBlackListPolicy() {
    }

    private Collection<T> blackList = new HashSet<T>();

    private Collection<T> whiteList = new HashSet<T>();

    private boolean enableBlackList = true;

    private boolean enableWhiteList = true;

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> addBlack(T t) {
        this.blackList.add(t);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> addBlack(@SuppressWarnings("unchecked") T... t) {
        CollectionUtils.addAll(blackList, t);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> removeBlack(T t) {
        this.blackList.remove(t);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> clearBlackList() {
        this.blackList.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> addWhite(T t) {
        this.whiteList.add(t);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> addWhite(@SuppressWarnings("unchecked") T... t) {
        CollectionUtils.addAll(whiteList, t);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> removeWhite(T t) {
        this.whiteList.remove(t);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WhiteBlackListPolicy<T> clearWhiteList() {
        this.whiteList.clear();
        return this;
    }

    /**
     * <p>
     * 清除黑名单和白名单
     * </p>
     * 
     * @return this
     */
    public WhiteBlackListPolicy<T> clear() {
        this.blackList.clear();
        this.whiteList.clear();
        return this;
    }

    /**
     * 返回blackList
     * 
     * @return blackList
     */
    @Override
    public Collection<T> getBlackList() {
        return new HashSet<>(blackList);
    }

    /**
     * 设置blackList
     * 
     * @param blackList
     *            blackList
     */
    @Override
    public WhiteBlackListPolicy<T> setBlackList(Collection<T> blackList) {
        this.blackList = blackList;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<T> getWhiteList() {
        return new HashSet<>(whiteList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWhiteList(Collection<T> whiteList) {
        this.whiteList = whiteList;
    }

    /**
     * 返回enableBlackList
     * 
     * @return enableBlackList
     */
    public boolean isEnableBlackList() {
        return enableBlackList;
    }

    /**
     * 设置enableBlackList
     * 
     * @param enableBlackList
     *            enableBlackList
     * @return this
     */
    public WhiteBlackListPolicy<T> setEnableBlackList(boolean enableBlackList) {
        this.enableBlackList = enableBlackList;
        return this;
    }

    /**
     * 返回enableWhiteList
     * 
     * @return enableWhiteList
     */
    public boolean isEnableWhiteList() {
        return enableWhiteList;
    }

    /**
     * 设置enableWhiteList
     * 
     * @param enableWhiteList
     *            enableWhiteList
     * @return this
     */
    public WhiteBlackListPolicy<T> setEnableWhiteList(boolean enableWhiteList) {
        this.enableWhiteList = enableWhiteList;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAllow(T target) {
        if (isEnableBlackList() && isInBlackList(target)) {
            return false;
        }
        if (isEnableWhiteList()) {
            return isInWhiteList(target);
        }
        return true;
    }

    /**
     * <p>
     * 判断传入目标是否一致
     * </p>
     * 
     * @param target1
     *            target1
     * @param target2
     *            target2
     * @return 传入目标是否一致
     */
    protected abstract boolean isEquals(T target1, T target2);

    /**
     * <p>
     * 判断传入目标是否在黑名单中
     * </p>
     * 
     * @param target
     *            target
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
     * 
     * @param target
     *            target
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
