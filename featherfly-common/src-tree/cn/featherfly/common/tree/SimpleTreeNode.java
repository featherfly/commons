
package cn.featherfly.common.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.featherfly.common.lang.CollectionUtils;

import cn.featherfly.model.structure.TreeNode;

/**
 * <p>
 * SimpleTreeNode
 * </p>
 * 
 * @author 钟冀
 */
public class SimpleTreeNode<N> implements TreeNode<SimpleTreeNode<N>>{
    
    private String id;
    
    private boolean root;
    
    private boolean leaf;
    
    private SimpleTreeNode<N> parent;    
    
    private List<SimpleTreeNode<N>> children = new ArrayList<SimpleTreeNode<N>>();
    
    private N nodeObject;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleTreeNode<N> getParent() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParent(SimpleTreeNode<N> parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<SimpleTreeNode<N>> getChildren() {        
        return children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<SimpleTreeNode<N>> getProgenys() {        
        throw new UnsupportedOperationException("not implement yet");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(SimpleTreeNode<N> child) {
        this.children.add(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChilds(SimpleTreeNode<N>...childs) {
        CollectionUtils.addAll(this.children, childs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChilds(Collection<SimpleTreeNode<N>> childs) {
        this.children.addAll(childs);
    }

    /**
     * 返回nodeObject
     * @return nodeObject
     */
    public N getNodeObject() {
        return nodeObject;
    }

    /**
     * 设置nodeObject
     * @param nodeObject nodeObject
     */
    public void setNodeObject(N nodeObject) {
        this.nodeObject = nodeObject;
    }

    
}
