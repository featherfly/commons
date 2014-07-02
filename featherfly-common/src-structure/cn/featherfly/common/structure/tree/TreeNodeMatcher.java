
package cn.featherfly.common.structure.tree;

import cn.featherfly.common.data.Matcher;

/**
 * <p>
 * 树节点匹配器
 * </p>
 * @param <E> 泛型
 * @author 钟冀
 */
public interface TreeNodeMatcher<T extends TreeNodeModel<T>> extends Matcher<T>{
}
