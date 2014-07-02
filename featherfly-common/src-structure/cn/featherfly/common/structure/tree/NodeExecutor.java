
/**
 * @author 钟冀 - yufei
 *		 	Jul 17, 2009 
 */
package cn.featherfly.common.structure.tree;

/**
 * <p>
 * 节点操作的接口
 * </p>
 * @param <E> 树节点存放的对象类型
 * @author 钟冀
 */
public interface NodeExecutor<T extends TreeNodeModel<T>> {
	/**
	 * @param treeNode 树节点 
	 */
	void execute(T treeNode);
}
