
package cn.featherfly.common.gentool.exception.module;

import cn.featherfly.common.exception.LocalizedCodeException;

/**
 * <p>
 * ExceptionModule
 * </p>
 * 
 * @author 钟冀
 */
public class ExceptionModule extends ClassModule {
    
    public static final Class<LocalizedCodeException> DEFAULT_PARENT = LocalizedCodeException.class;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getParent() {
        return super.getParent() != null
                ? super.getParent()
                : DEFAULT_PARENT;
    }
}
