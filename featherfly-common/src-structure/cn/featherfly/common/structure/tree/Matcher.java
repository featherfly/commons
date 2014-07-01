
package cn.featherfly.common.structure.tree;

/**
 * <p>
 * 属性查找匹配器
 * </p>
 * @param <E> 泛型
 * @author 钟冀
 */
public interface Matcher<E> {
	public boolean match(TreeNode<E> treeNode);
}
