/**
 * @author zhongj - yufei Mar 19, 2009
 */
package cn.featherfly.common.structure.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.structure.tree.matcher.TreeNodeEqualsMatcher;

/**
 * <p>
 * 树节点.
 * </p>
 * <blockquote>
 * <ul>
 * <li>修改人 zhongj 构造参数已经确定必须有id，去掉setId()方法
 * <li>修改人 zhongj 去掉childNodes的setter方法，用appendChildNodes()来代替
 * <li>修改人 zhongj 去掉isLeafNode属性，通过childsize来判断
 * <li>修改人 zhongj 加入clone方法，实现深度复制
 * </ul>
 * </blockquote>
 *
 * @param <E> 树节点存放的类型
 * @author zhongj
 */
public class SimpleTreeNode<E> implements Cloneable, TreeNode<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeNode.class);

    /**
     * 根节点的层级
     */
    protected static final int ROOT_DEPTH = 0;

    private String id;

    private TreeNode<E> parentNode;

    private E nodeObject;

    private int depth = ROOT_DEPTH;

    private List<TreeNode<E>> childNodes = new ArrayList<>();

    // ********************************************************************
    //    constractor
    // ********************************************************************

    /**
     * @param id 节点ID
     */
    public SimpleTreeNode(String id) {
        this.id = id;
    }

    // ********************************************************************
    //    TreeNode method 整个节点范围
    // ********************************************************************
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TreeNode<E>> getAncestors() {
        return getAncestors(new ArrayList<TreeNode<E>>(), this);
    }

    private List<TreeNode<E>> getAncestors(List<TreeNode<E>> ancestor, TreeNode<E> node) {
        TreeNode<E> parentNode = node.getParent();
        if (parentNode != null) {
            ancestor.add(parentNode);
            return getAncestors(ancestor, parentNode);
        } else {
            return ancestor;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAncestor(TreeNode<E> ancestor) {
        TreeNode<E> parentNode = this.getParent();
        if (parentNode == null || ancestor == null) {
            return false;
        } else {
            boolean is = parentNode.equals(ancestor);
            if (is) {
                return true;
            } else {
                return parentNode.hasAncestor(ancestor);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProgeny(TreeNode<E> ancestor) {
        return hasAncestor(ancestor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TreeNode<E>> getEveryNode() {
        List<TreeNode<E>> nodeList = new ArrayList<>();
        nodeList.add(this);
        getProgenys(nodeList, this);
        return nodeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TreeNode<E>> getProgenys() {
        List<TreeNode<E>> nodeList = new ArrayList<>();
        getProgenys(nodeList, this);
        return nodeList;
    }

    private void getProgenys(List<TreeNode<E>> nodeList, TreeNode<E> node) {
        if (!node.isLeaf()) {
            Iterator<TreeNode<E>> iter = node.getChildNodes().iterator();
            while (iter.hasNext()) {
                TreeNode<E> childNode = iter.next();
                nodeList.add(childNode);
                getProgenys(nodeList, childNode);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasProgeny(TreeNode<E> progeny) {
        if (progeny == null) {
            return false;
        }
        List<TreeNode<E>> childs = this.getChildNodes();
        Iterator<TreeNode<E>> iter = childs.iterator();
        while (iter.hasNext()) {
            TreeNode<E> child = iter.next();
            boolean is = progeny.equals(child);
            if (is) {
                return true;
            } else {
                is = child.hasProgeny(progeny);
            }
            if (is) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAncestor(TreeNode<E> progeny) {
        return hasProgeny(progeny);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> findProgeny(TreeNode<E> progeny) {
        if (progeny == null) {
            return null;
        }
        // TODO 使用matcher重写
        TreeNodeMatcher<TreeNode<E>> matcher = new TreeNodeEqualsMatcher<>(progeny);
        return findTreeNode(matcher, this.getChildNodes());
        //        return findProgeny(progeny.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> findProgeny(String progenyId) {
        Iterator<TreeNode<E>> it = this.getChildNodes().iterator();
        while (it.hasNext()) {
            TreeNode<E> node = it.next().findTreeNode(progenyId);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> findTreeNode(TreeNodeMatcher<TreeNode<E>> matcher) {
        if (matcher == null) {
            return null;
        }
        return findTreeNode(matcher, this);
    }

    private TreeNode<E> findTreeNode(TreeNodeMatcher<TreeNode<E>> matcher, TreeNode<E> treeNode) {
        if (matcher.test(treeNode)) {
            return treeNode;
        } else {
            findTreeNode(matcher, treeNode.getChildNodes());
            //            List<TreeNode<E>> childs = treeNode.getChildNodes();
            //            Iterator<TreeNode<E>> iter = childs.iterator();
            //            while (iter.hasNext()) {
            //                TreeNode<E> child = iter.next();
            //                TreeNode<E> result = null;
            //                result = ((SimpleTreeNode<E>) child).findTreeNode(matcher, child);
            //                if (result != null) {
            //                    return result;
            //                }
            //            }
        }
        return null;
    }

    private TreeNode<E> findTreeNode(TreeNodeMatcher<TreeNode<E>> matcher, Collection<TreeNode<E>> treeNodes) {
        if (Lang.isNotEmpty(treeNodes)) {
            Iterator<TreeNode<E>> iter = treeNodes.iterator();
            while (iter.hasNext()) {
                TreeNode<E> child = iter.next();
                TreeNode<E> result = null;
                result = ((SimpleTreeNode<E>) child).findTreeNode(matcher, child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> findTreeNode(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        // TODO 使用matcher重写，写死的matcher
        TreeNodeMatcher<TreeNode<E>> matcher = new TreeNodeEqualsMatcher<>(node);
        return findTreeNode(matcher);
        //        return findTreeNode(node.getId());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> findTreeNode(String nodeId) {
        // TODO 这种基于ID的方法是否需要删除
        if (nodeId == null || "".equals(nodeId)) {
            return null;
        }
        if (nodeId.equals(this.getId())) {
            return this;
        } else {
            List<TreeNode<E>> childs = this.getChildNodes();
            Iterator<TreeNode<E>> iter = childs.iterator();
            while (iter.hasNext()) {
                TreeNode<E> child = iter.next();
                TreeNode<E> result = null;
                result = child.findTreeNode(nodeId);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void each(NodeExecutor<TreeNode<E>> executor) {
        executor.execute(this);
        if (!this.isLeaf()) {
            Iterator<TreeNode<E>> iter = this.getChildNodes().iterator();
            while (iter.hasNext()) {
                iter.next().each(executor);
            }
        }
    }

    /**
     * 改变当前节点的depth，级联改变子孙节点
     *
     * @param depth 层级
     */
    public void changeDepth(int depth) {
        this.depth = depth;
        if (!this.isLeaf()) {
            Iterator<TreeNode<E>> it = getChildNodes().iterator();
            while (it.hasNext()) {
                TreeNode<E> node = it.next();
                ((SimpleTreeNode<E>) node).changeDepth(depth + 1);
            }
        }
    }
    // ********************************************************************
    //    TreeNode method 上下级节点范围
    // ********************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(TreeNode<E> childNode) {
        return getChildNodes().indexOf(childNode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        if (this.getParent() == null || this.getParent().getChildSize() < 2) {
            return 0;
        }
        return this.getParent().indexOf(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChildNode(TreeNode<E> childNode) {
        return indexOf(childNode) != -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isParent(TreeNode<E> childNode) {
        return this.equals(childNode.getParent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChildNode(TreeNode<E> parentNode) {
        if (parentNode == null) {
            return false;
        }
        return parentNode.hasChildNode(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getChildSize() {
        return getChildNodes().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChildNode(TreeNode<E> childNode) {
        readyForAddChild(childNode);
        getChildNodes().add(childNode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChildNodes(@SuppressWarnings("unchecked") TreeNode<E>... childNodes) {
        if (childNodes == null) {
            throw new IllegalArgumentException("传入参数childNodes不能为null");
        }
        for (TreeNode<E> childNode : childNodes) {
            readyForAddChild(childNode);
            getChildNodes().add(childNode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChildNodes(List<TreeNode<E>> childNodes) {
        if (childNodes == null) {
            throw new IllegalArgumentException("传入参数childNodes不能为null");
        }
        for (TreeNode<E> childNode : childNodes) {
            readyForAddChild(childNode);
            getChildNodes().add(childNode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertChildNode(TreeNode<E> childNode, int index) {
        int size = getChildNodes().size();
        readyForAddChild(childNode);
        if (index > -1 && index < size) {
            getChildNodes().add(index, childNode);
        } else {
            getChildNodes().add(childNode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertChildNodeBefore(TreeNode<E> childNode, TreeNode<E> refChildNode) {
        readyForAddChild(childNode);
        int index = indexOf(refChildNode);
        insertChildNode(childNode, index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertChildNodeAfter(TreeNode<E> childNode, TreeNode<E> refChildNode) {
        readyForAddChild(childNode);
        int index = indexOf(refChildNode);
        insertChildNode(childNode, index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeChildNode(TreeNode<E> childNode) {
        readyForRemoveChild(childNode);
        getChildNodes().remove(childNode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeChildNodes() {
        Iterator<TreeNode<E>> iter = getChildNodes().iterator();
        while (iter.hasNext()) {
            readyForRemoveChild(iter.next());
        }
        getChildNodes().clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replaceChild(TreeNode<E> newChild, TreeNode<E> oldChild) {
        int index = indexOf(oldChild);
        if (index != -1) {
            removeChildNode(oldChild);
            insertChildNode(newChild, index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mergeChild(TreeNode<E> child) {
        int index = indexOf(child);
        if (index != -1) {
            LOGGER.debug("node[{}].mergeChild(node[{}]) as replace", this.getId(), child.getId());
            removeChildNode(child);
            insertChildNode(child, index);
        } else {
            addChildNode(child);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        TreeNode<E> parent = this.getParent();
        if (parent != null) {
            parent.removeChildNode(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> getFirstChild() {
        if (getChildSize() > 0) {
            return getChildNodes().get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> getLastChild() {
        if (getChildSize() > 0) {
            return getChildNodes().get(getChildSize() - 1);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> getNextSibling() {
        TreeNode<E> parentNode = getParent();
        if (parentNode != null) {
            int size = parentNode.getChildSize();
            int index = parentNode.indexOf(this);
            if (size > 0 && size > index + 1) {
                return parentNode.getChildNodes().get(index + 1);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> getPreviousSibling() {
        TreeNode<E> parentNode = getParent();
        if (parentNode != null) {
            int size = parentNode.getChildSize();
            int index = parentNode.indexOf(this);
            if (size > 0 && index > 0) {
                return parentNode.getChildNodes().get(index - 1);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFirst() {
        return getPreviousSibling() == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLast() {
        return getNextSibling() == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(Comparator<TreeNode<E>> comparator) {
        Collections.sort(getChildNodes(), comparator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(Comparator<TreeNode<E>> comparator, boolean containProgeny) {
        if (containProgeny) {
            if (!this.isLeaf()) {
                this.sort(comparator);
                Iterator<TreeNode<E>> iter = this.getChildNodes().iterator();
                while (iter.hasNext()) {
                    iter.next().sort(comparator, containProgeny);
                }
            }
        } else {
            sort(comparator);
        }
    }
    // ********************************************************************
    //    判断关系的方法
    // ********************************************************************

    // ********************************************************************
    //    private method
    // ********************************************************************

    // 在添加儿子节点前的处理
    private void readyForAddChild(TreeNode<E> childNode) {
        SimpleTreeNode<E> stn = (SimpleTreeNode<E>) childNode;
        stn.depth = getDepth() + 1;
        //        childNode.setParentNode(this);
        readyForSetParent(this, false);
    }

    // 在移除儿子节点前的处理
    private void readyForRemoveChild(TreeNode<E> childNode) {
        SimpleTreeNode<E> stn = (SimpleTreeNode<E>) childNode;
        stn.changeDepth(ROOT_DEPTH);
        //        childNode.changeDepth(ROOT_DEPTH);
        //        childNode.setParentNode(null);
        readyForSetParent(null, false);
    }

    // 在设置父节点前的处理
    private void readyForSetParent(TreeNode<E> parentNode, boolean autoAppendToParent) {
        // 如果当前父节点存在，从当前父节点中移除自己
        if (this.parentNode != null) {
            this.parentNode.removeChildNode(this);
        }
        this.parentNode = parentNode;
        // 在传入的父节点中添加当前子节点
        if (autoAppendToParent) {
            parentNode.addChildNode(this);
        }

    }

    // ********************************************************************
    //    override method
    // ********************************************************************

    /**
     * 返回一个复制的节点，迭代复制所有节点
     *
     * @return 复制的节点
     */
    @Override
    public TreeNode<E> clone() {
        SimpleTreeNode<E> node = new SimpleTreeNode<>(getId());
        node.setNodeObject(getNodeObject());
        if (!isLeaf()) {
            Iterator<TreeNode<E>> iter = getChildNodes().iterator();
            while (iter.hasNext()) {
                TreeNode<E> cloneChild = ((SimpleTreeNode<E>) iter.next()).clone();
                node.addChildNode(cloneChild);
            }
        }
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<E> cloneAsRoot() {
        SimpleTreeNode<E> node = (SimpleTreeNode<E>) this.clone();
        node.changeDepth(SimpleTreeNode.ROOT_DEPTH);
        node.setParentNode(null);
        return node;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof TreeNode)) {
            return false;
        }
        if (Lang.isEmpty(this.getId())) {
            return false;
        }
        @SuppressWarnings("unchecked")
        TreeNode<E> treeNode = (TreeNode<E>) object;
        return this.getId().equals(treeNode.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "#" + this.getId().toString();
    }
    // ********************************************************************
    //    setter and getter
    // ********************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public E getNodeObject() {
        return nodeObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNodeObject(E nodeObject) {
        this.nodeObject = nodeObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoot() {
        return getParent() == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        return getChildSize() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TreeNode<E>> getChildNodes() {
        return childNodes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDepth() {
        return depth;
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
    public TreeNode<E> getParent() {
        return parentNode;
    }

    /**
     * 设置parentNode
     *
     * @param parentNode parentNode
     */
    public void setParentNode(TreeNode<E> parentNode) {
        readyForSetParent(parentNode, true);
    }
}