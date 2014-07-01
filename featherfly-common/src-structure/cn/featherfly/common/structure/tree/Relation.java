
/**
 * @author 钟冀 - yufei
 *		 	Mar 24, 2009 
 */
package cn.featherfly.common.structure.tree;

/**
 * 树节点的关系判接口
 * @param <N> 树节点
 * @author 钟冀 - yufei
 */
public interface Relation<N extends TreeNode<?>>{
	/**
	 * 判断父子关系，第一个作为父节点第二个作为子节点判断
	 * @param parentNode 父节点
	 * @param childNode 子节点
	 * @return 是否是父子节点
	 */
	boolean filiation(N parentNode, N childNode);
	/**
	 * 判断是否为根节点，当没有父节点时，判断为根节点
	 * @param node 节点
	 * @return 是否是根节点
	 */
	boolean isRoot(N node);
	/**
	 * 将传入节点转换为根节点
	 * @param node 节点
	 */
	void changeToRoot(N node);
	/**
	 * 判断两个节点是否相同，使用node的id的equals方法判断
	 * @param node 节点
	 * @param anotherNode 另一个节点
	 * @return 两个节点是否相同
	 */
	boolean equals(N node, N anotherNode);
}
