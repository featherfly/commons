
package cn.featherfly.common.cache.caffeine;

import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;

/**
 * CaffeineStringKeyConfiguration.
 *
 * @author zhongj
 */
public class CaffeineConfiguration<K, V> extends MutableConfiguration<K, V> {

    /**
     *
     */
    private static final long serialVersionUID = 7856187324429337457L;

    /**
     *
     */
    public CaffeineConfiguration() {
        super();
    }

    /**
     * @param configuration
     */
    public CaffeineConfiguration(CompleteConfiguration<K, V> configuration) {
        super(configuration);
        ModifiedExpiryPolicy.factoryOf(Duration.ONE_MINUTE);
    }
}
