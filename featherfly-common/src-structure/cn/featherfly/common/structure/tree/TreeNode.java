package cn.featherfly.common.structure.tree;


/**
 * <p>
 * TreeNode，树型数据结构，和List,Set是一个类型
 * </p>
 * 
 * @author 钟冀
 */
public interface TreeNode<O> extends TreeNodeModel<TreeNode<O>>{
	/**
	 * 返回nodeObject
	 * @return nodeObject
	 */
	O getNodeObject();	
	/**
	 * <p>
	 * 设置节点存放的对象
	 * </p>
	 * @param object 对象
	 * @return 节点存放的对象
	 */
	void setNodeObject(O object);
}