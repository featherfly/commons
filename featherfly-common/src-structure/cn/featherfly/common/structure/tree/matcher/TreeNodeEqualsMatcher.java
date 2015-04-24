
package cn.featherfly.common.structure.tree.matcher;

import cn.featherfly.common.structure.tree.TreeNodeMatcher;
import cn.featherfly.common.structure.tree.TreeNodeModel;

/**
 * <p>
 * TreeNodeIdMatcher
 * </p>
 * @param <T> 树模型对象
 * @author 钟冀
 */
public class TreeNodeEqualsMatcher<T extends TreeNodeModel<T>> implements TreeNodeMatcher<T>{
    
    private T treeNodeModel;
    
    /**
     * 
     * @param treeNodeModel treeNodeModel
     */
    public TreeNodeEqualsMatcher(T treeNodeModel) {
        this.treeNodeModel = treeNodeModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(T treeNodeModel) {
        return this.treeNodeModel.equals(treeNodeModel);
    }

    
}
