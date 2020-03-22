package cn.featherfly.common.structure.tree;


/**
 * <p>
 * TreeNode，树型数据结构，和List,Set是一个类型
 * </p>
 * @param <O> 节点保存的对象
 * @author zhongj
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
     */
    void setNodeObject(O object);
}