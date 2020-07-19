
package cn.featherfly.common.db.jpa;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * ColumnDefault
 * </p>
 *
 * @author zhongj
 */
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface ColumnDefault {

    String value() default "";
}
