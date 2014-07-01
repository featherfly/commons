
/**
 * @author 钟冀 - yufei
 *		 	Mar 24, 2009 
 */
package cn.featherfly.common.structure.tree;

/**
 * 树节点的关系判断默认实现
 * @param <E> 树节点存放的对象类型
 * @author 钟冀 - yufei
 */
class DefaultRelation<E> implements Relation<TreeNode<E>> {
	
	/**
	 */
	public DefaultRelation() {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean filiation(TreeNode<E> parentNode, TreeNode<E> childNode) {
		if (parentNode == null || childNode == null) {
			return false;
		}
		TreeNode<E> p = childNode.getParentNode();
		if (p != null) {
			return p.getId().equals(parentNode.getId());
		}
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRoot(TreeNode<E> node) {
		if (node == null) {
			return false;
		}
		return node.getParentNode() == null;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeToRoot(TreeNode<E> node) {
		if (node == null) {
			throw new IllegalArgumentException("传入参数node不能为null");
		}
		node.changeDepth(TreeNode.ROOT_DEPTH);
		node.setParentNode(null);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(TreeNode<E> node, TreeNode<E> anotherNode) {
		if (node == null || anotherNode == null 
			|| node.getId() == null || anotherNode.getId() == null) {
			return false;
		}
		return node.getId().equals(anotherNode.getId());
	}
	

}
