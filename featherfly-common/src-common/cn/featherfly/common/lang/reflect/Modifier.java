
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
    PUBLIC,
    /**
     * The {@code int} value representing the {@code private}
     * modifier.
     */
    PRIVATE,

    /**
     * The {@code int} value representing the {@code protected}
     * modifier.
     */
    PROTECTED,

    /**
     * The {@code int} value representing the {@code static}
     * modifier.
     */
    STATIC,

    /**
     * The {@code int} value representing the {@code final}
     * modifier.
     */
    FINAL,

    /**
     * The {@code int} value representing the {@code synchronized}
     * modifier.
     */
    SYNCHRONIZED,

    /**
     * The {@code int} value representing the {@code volatile}
     * modifier.
     */
    VOLATILE,

    /**
     * The {@code int} value representing the {@code transient}
     * modifier.
     */
    TRANSIENT,

    /**
     * The {@code int} value representing the {@code native}
     * modifier.
     */
    NATIVE,

    /**
     * The {@code int} value representing the {@code interface}
     * modifier.
     */
    INTERFACE,

    /**
     * The {@code int} value representing the {@code abstract}
     * modifier.
     */
    ABSTRACT,

    /**
     * The {@code int} value representing the {@code strictfp}
     * modifier.
     */
    STRICT;
    
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
