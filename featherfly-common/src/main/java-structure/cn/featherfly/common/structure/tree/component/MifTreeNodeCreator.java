
/**
 * @author 钟冀 - yufei
 *             Aug 25, 2009 
 */
package cn.featherfly.common.structure.tree.component;

import cn.featherfly.common.structure.tree.TreeNode;

/**
 * <p>
 * 每个节点数据生成操作的接口
 * </p>
 * @param <E> 树节点包含对象的类型
 * @author 钟冀
 */
public interface MifTreeNodeCreator<E> {
    /**
     * 通过TreeNode节点，生成MifTree节点
     * @param node 当前节点
     * @return MifTree节点MifTree节点
     */
    MifTreeNode createNode(TreeNode<E> node);
}
