
package cn.featherfly.common.policy;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Predicate;

import cn.featherfly.common.lang.CollectionUtils;

/**
 * 允许拒绝混合名单策略，拒绝名单优先级更高.
 *
 * @author zhongj
 * @param <T> 需要判断的类型
 */
public class AllowDenyListPolicy<T>
    implements AllowListPolicy<T, AllowDenyListPolicy<T>>, DenyListPolicy<T, AllowDenyListPolicy<T>> {

    /**
     * The Enum Strategy.
     *
     * @author zhongj
     */
    public enum Strategy {

        /** The both. */
        BOTH,
        /** The deny only. */
        DENY_ONLY,
        /** The allow only. */
        ALLOW_ONLY;
    }

    private Collection<Predicate<T>> denys = new LinkedHashSet<>();

    private Collection<Predicate<T>> allows = new LinkedHashSet<>();

    private final boolean enableDeny;

    private final boolean enableAllow;

    /**
     * Instantiates a new allow deny list policy.
     */
    public AllowDenyListPolicy() {
        this(Strategy.BOTH);
    }

    /**
     * Instantiates a new allow deny list policy.
     *
     * @param strategy the strategy
     */
    public AllowDenyListPolicy(Strategy strategy) {
        switch (strategy) {
            case DENY_ONLY:
                enableAllow = false;
                enableDeny = true;
                break;
            case ALLOW_ONLY:
                enableAllow = true;
                enableDeny = false;
                break;
            default:
                enableAllow = true;
                enableDeny = true;
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> addDeny(Predicate<T> deny) {
        denys.add(deny);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> addDeny(@SuppressWarnings("unchecked") Predicate<T>... denys) {
        CollectionUtils.addAll(this.denys, denys);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> addDeny(Collection<Predicate<T>> denys) {
        CollectionUtils.addAll(this.denys, denys);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> removeDeny(T t) {
        denys.remove(t);
        return this;
    }

    /**
     * 返回blackList.
     *
     * @return blackList
     */
    @SuppressWarnings("unchecked")
    @Override
    public Predicate<T>[] getDenys() {
        return denys.toArray(new Predicate[denys.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> clearDeny() {
        denys.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> addAllow(Predicate<T> allow) {
        allows.add(allow);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> addAllow(@SuppressWarnings("unchecked") Predicate<T>... allows) {
        CollectionUtils.addAll(this.allows, allows);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> addAllow(Collection<Predicate<T>> allows) {
        CollectionUtils.addAll(this.allows, allows);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> removeAllow(Predicate<T> allow) {
        allows.remove(allow);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllowDenyListPolicy<T> clearAllow() {
        allows.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Predicate<T>[] getAllows() {
        return allows.toArray(new Predicate[allows.size()]);
    }

    /**
     * clear denys and allows.
     *
     * @return this
     */
    public AllowDenyListPolicy<T> clear() {
        denys.clear();
        allows.clear();
        return this;
    }

    /**
     * Checks if is enable deny.
     *
     * @return true, if is enable deny
     */
    public boolean isEnableDeny() {
        return enableDeny;
    }

    /**
     * Checks if is enable allow.
     *
     * @return true, if is enable allow
     */
    public boolean isEnableAllow() {
        return enableAllow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAllow(T target) {
        if (isEnableDeny() && decideByDeny(target)) {
            return false;
        }
        if (isEnableAllow()) {
            return decideByAllow(target);
        }
        return true;
    }

    /**
     * 判断传入目标是拒绝策略是否通过（任意一个通过）.
     *
     * @param target target
     * @return 是否在拒绝名单
     */
    protected boolean decideByDeny(T target) {
        for (Predicate<T> deny : denys) {
            if (deny.test(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断传入目标是允许策略是否通过（任意一个通过）.
     *
     * @param target target
     * @return 允许名单是否有允许
     */
    protected boolean decideByAllow(T target) {
        for (Predicate<T> allow : allows) {
            if (allow.test(target)) {
                return true;
            }
        }
        return false;
    }
}
