
package cn.featherfly.common.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

/**
 * SimpleExpiryPolicy.
 *
 * @author zhongj
 */
public class ExpiryPolicyImpl implements ExpiryPolicy {

    private Duration expiryForCreation;

    private Duration expiryForAccess;

    private Duration expiryForUpdate;

    /**
     * Factory of.
     *
     * @param expiryForCreation the expiry
     * @return the factory
     */
    public static Factory<ExpiryPolicy> factoryOf(java.time.Duration expiryForCreation) {
        return factoryOf(expiryForCreation, null);
    }

    /**
     * Factory of.
     *
     * @param expiryForCreation the expiry for creation
     * @param expiryForAccess   the expiry for access
     * @return the factory
     */
    public static Factory<ExpiryPolicy> factoryOf(java.time.Duration expiryForCreation,
            java.time.Duration expiryForAccess) {
        return factoryOf(expiryForCreation, expiryForAccess, expiryForAccess);
    }

    /**
     * Factory of.
     *
     * @param expiryForCreation the expiry for creation
     * @param expiryForAccess   the expiry for access
     * @param expiryForUpdate   the expiry for update
     * @return the factory
     */
    public static Factory<ExpiryPolicy> factoryOf(java.time.Duration expiryForCreation,
            java.time.Duration expiryForAccess, java.time.Duration expiryForUpdate) {
        Duration _expiryForCreation = null;
        Duration _expiryForAccess = null;
        Duration _expiryForUpdate = null;
        if (expiryForCreation != null) {
            _expiryForCreation = new Duration(TimeUnit.MILLISECONDS, expiryForCreation.toMillis());
        }
        if (expiryForAccess != null) {
            _expiryForAccess = new Duration(TimeUnit.MILLISECONDS, expiryForAccess.toMillis());
        }
        if (expiryForUpdate != null) {
            _expiryForUpdate = new Duration(TimeUnit.MILLISECONDS, expiryForUpdate.toMillis());
        }
        return new FactoryBuilder.SingletonFactory<>(
                new ExpiryPolicyImpl(_expiryForCreation, _expiryForAccess, _expiryForUpdate));
    }

    /**
     * Factory of.
     *
     * @param expiryForCreation the expiry
     * @return the factory
     */
    public static Factory<ExpiryPolicy> factoryOf(Duration expiryForCreation) {
        return factoryOf(expiryForCreation, null);
    }

    /**
     * Factory of.
     *
     * @param expiryForCreation the expiry for creation
     * @param expiryForAccess   the expiry for access
     * @return the factory
     */
    public static Factory<ExpiryPolicy> factoryOf(Duration expiryForCreation, Duration expiryForAccess) {
        return factoryOf(expiryForCreation, expiryForAccess, expiryForAccess);
    }

    /**
     * Factory of.
     *
     * @param expiryForCreation the expiry for creation
     * @param expiryForAccess   the expiry for access
     * @param expiryForUpdate   the expiry for update
     * @return the factory
     */
    public static Factory<ExpiryPolicy> factoryOf(Duration expiryForCreation, Duration expiryForAccess,
            Duration expiryForUpdate) {
        return new FactoryBuilder.SingletonFactory<>(
                new ExpiryPolicyImpl(expiryForCreation, expiryForAccess, expiryForUpdate));
    }

    /**
     * Instantiates a new simple expiry policy.
     *
     * @param expiryForCreation the expiry for creation
     */
    public ExpiryPolicyImpl(Duration expiryForCreation) {
        this(expiryForCreation, null, null);
    }

    /**
     * Instantiates a new simple expiry policy.
     *
     * @param expiryForCreation the expiry for creation
     * @param expiryForAccess   the expiry for access
     */
    public ExpiryPolicyImpl(Duration expiryForCreation, Duration expiryForAccess) {
        this(expiryForCreation, expiryForAccess, expiryForAccess);
    }

    /**
     * Instantiates a new simple expiry policy.
     *
     * @param expiryForCreation the expiry for creation
     * @param expiryForAccess   the expiry for access
     * @param expiryForUpdate   the expiry for update
     */
    public ExpiryPolicyImpl(Duration expiryForCreation, Duration expiryForAccess, Duration expiryForUpdate) {
        super();
        this.expiryForCreation = expiryForCreation;
        this.expiryForAccess = expiryForAccess;
        this.expiryForUpdate = expiryForUpdate;
    }

    /**
     * 返回expiryForCreation.
     *
     * @return expiryForCreation
     */
    @Override
    public Duration getExpiryForCreation() {
        return expiryForCreation;
    }

    /**
     * 返回expiryForAccess.
     *
     * @return expiryForAccess
     */
    @Override
    public Duration getExpiryForAccess() {
        return expiryForAccess;
    }

    /**
     * 返回expiryForUpdate.
     *
     * @return expiryForUpdate
     */
    @Override
    public Duration getExpiryForUpdate() {
        return expiryForUpdate;
    }

}
