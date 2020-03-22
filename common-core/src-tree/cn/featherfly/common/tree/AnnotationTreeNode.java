
package cn.featherfly.common.tree;

import java.lang.reflect.Method;
import java.util.Collection;

import cn.featherfly.model.structure.TreeNode;

/**
 * <p>
 * SimpleTreeNode
 * 类的说明放这里
 * </p>
 * 
 * @author zhongj
 */
public class AnnotationTreeNode<N, T extends TreeNode<T>> implements TreeNode<T>{
    
    private N node;
    
    private Method parentMethod;
    
    private Method childrenMethod;
    
    public AnnotationTreeNode(N node, Method parent, Method childrenMethod) {
        
    }
        
    
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
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        // YUFEI_TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getParent() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParent(T parent) {
        // YUFEI_TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<T> getChildren() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<T> getProgenys() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(T child) {
        // YUFEI_TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChilds(T... childs) {
        // YUFEI_TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChilds(Collection<T> childs) {
        // YUFEI_TODO Auto-generated method stub
        
    }

}
