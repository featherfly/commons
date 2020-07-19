
package cn.featherfly.common.db.jpa;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Comment
 * </p>
 *
 * @author zhongj
 */
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public @interface Comment {

    String value() default "";
}
