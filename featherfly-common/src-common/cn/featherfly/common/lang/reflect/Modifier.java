
package cn.featherfly.common.lang.reflect;

/**
 * <p>
 * Modifier
 * </p>
 * 
 * @author 钟冀
 */
public enum Modifier {
     /**
     * The {@code int} value representing the {@code public}
     * modifier.
     */
    PUBLIC(java.lang.reflect.Modifier.PUBLIC),
    /**
     * The {@code int} value representing the {@code private}
     * modifier.
     */
    PRIVATE(java.lang.reflect.Modifier.PRIVATE),

    /**
     * The {@code int} value representing the {@code protected}
     * modifier.
     */
    PROTECTED(java.lang.reflect.Modifier.PROTECTED),

    /**
     * The {@code int} value representing the {@code static}
     * modifier.
     */
    STATIC(java.lang.reflect.Modifier.STATIC),

    /**
     * The {@code int} value representing the {@code final}
     * modifier.
     */
    FINAL(java.lang.reflect.Modifier.FINAL),

    /**
     * The {@code int} value representing the {@code synchronized}
     * modifier.
     */
    SYNCHRONIZED(java.lang.reflect.Modifier.SYNCHRONIZED),

    /**
     * The {@code int} value representing the {@code volatile}
     * modifier.
     */
    VOLATILE(java.lang.reflect.Modifier.VOLATILE),

    /**
     * The {@code int} value representing the {@code transient}
     * modifier.
     */
    TRANSIENT(java.lang.reflect.Modifier.TRANSIENT),

    /**
     * The {@code int} value representing the {@code native}
     * modifier.
     */
    NATIVE(java.lang.reflect.Modifier.NATIVE),

    /**
     * The {@code int} value representing the {@code interface}
     * modifier.
     */
    INTERFACE(java.lang.reflect.Modifier.INTERFACE),

    /**
     * The {@code int} value representing the {@code abstract}
     * modifier.
     */
    ABSTRACT(java.lang.reflect.Modifier.ABSTRACT),

    /**
     * The {@code int} value representing the {@code strictfp}
     * modifier.
     */
    STRICT(java.lang.reflect.Modifier.STRICT);
    
    private int mod;
    
    private Modifier(int mod) {
        this.mod = mod;
    }
    
    public boolean isModifier(int mod) {
        boolean result = false;
        switch (this) {
            case PUBLIC:
                result = java.lang.reflect.Modifier.isPublic(mod);
                break;
            case PRIVATE:
                result = java.lang.reflect.Modifier.isPrivate(mod);            
                break;
            case PROTECTED:
                result = java.lang.reflect.Modifier.isProtected(mod);            
                break;
            case STATIC:
                result = java.lang.reflect.Modifier.isStatic(mod);            
                break;
            case FINAL:
                result = java.lang.reflect.Modifier.isFinal(mod);            
                break;
            case SYNCHRONIZED:
                result = java.lang.reflect.Modifier.isSynchronized(mod);            
                break;
            case VOLATILE:
                result = java.lang.reflect.Modifier.isVolatile(mod);            
                break;
            case TRANSIENT:
                result = java.lang.reflect.Modifier.isTransient(mod);            
                break;
            case NATIVE:
                result = java.lang.reflect.Modifier.isNative(mod);            
                break;
            case INTERFACE:
                result = java.lang.reflect.Modifier.isInterface(mod);            
                break;
            case ABSTRACT:
                result = java.lang.reflect.Modifier.isAbstract(mod);            
                break;
            case STRICT:
                result = java.lang.reflect.Modifier.isStrict(mod);            
                break;
        }
        return result;
    }
}
