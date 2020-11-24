
package cn.featherfly.common.lang.vo;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 * ListImpl
 * </p>
 *
 * @author zhongj
 */
public interface UserFunction extends Function<User2, User3>, Supplier<User> {

}
