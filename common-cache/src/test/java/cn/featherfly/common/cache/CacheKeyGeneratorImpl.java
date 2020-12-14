
package cn.featherfly.common.cache;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import javax.cache.annotation.CacheKeyGenerator;
import javax.cache.annotation.CacheKeyInvocationContext;
import javax.cache.annotation.GeneratedCacheKey;

/**
 * GeneratedCacheKeyImpl.
 *
 * @author zhongj
 */
public class CacheKeyGeneratorImpl implements CacheKeyGenerator {

    private static class GeneratedCacheKeyImpl implements GeneratedCacheKey {

        /**
         *
         */
        private static final long serialVersionUID = -4316660277610592517L;

        private Object[] keys;

        /**
         * @param keys
         */
        public GeneratedCacheKeyImpl(Object[] keys) {
            super();
            this.keys = keys;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.deepHashCode(keys);
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            GeneratedCacheKeyImpl other = (GeneratedCacheKeyImpl) obj;
            if (!Arrays.deepEquals(keys, other.keys)) {
                return false;
            }
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeneratedCacheKey generateCacheKey(
            CacheKeyInvocationContext<? extends Annotation> cacheKeyInvocationContext) {
        Object[] keys = Arrays.stream(cacheKeyInvocationContext.getKeyParameters()).map(p -> p.getValue()).toArray();
        return new GeneratedCacheKeyImpl(keys);
    }

}
