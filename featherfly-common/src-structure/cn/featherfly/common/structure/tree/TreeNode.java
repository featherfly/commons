/**
 * @author 钟冀 - yufei
 *		 	Mar 19, 2009
 */
package cn.featherfly.common.structure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 树节点
 * </p>
 * <blockquote>
 * <ul>
 * <li>修改人 钟冀 构造参数已经确定必须有id，去掉setId()方法
 * <li>修改人 钟冀 去掉childNodes的setter方法，用appendChildNodes()来代替
 * <li>修改人 钟冀 去掉isLeafNode属性，通过childsize来判断
 * <li>修改人 钟冀 加入clone方法，实现深度复制
 * </ul>
 * </blockquote>
 * @param <E> 树节点存放的类型
 * @author 钟冀
 */
public class TreeNode<E> implements Cloneable{

	private static final Logger LOGGER = LoggerFactory.getLogger(TreeNode.class);

	/**
	 * 根节点的层级
	 */
	protected static final int ROOT_DEPTH = 0;

	private String id;

	private Relation<TreeNode<E>> relation = new DefaultRelation<E>();

	private TreeNode<E> parentNode;

	private E nodeObject;

	private int depth = ROOT_DEPTH;

	private List<TreeNode<E>> childNodes = new ArrayList<TreeNode<E>>();

	// ********************************************************************
	//	constractor
	// ********************************************************************

	/**
	 * @param id 节点ID
	 */
	public TreeNode(String id) {
		this.id = id;
	}

	// ********************************************************************
	//	TreeNode method 整个节点范围
	// ********************************************************************
	/**
	 * 返回当前节点的所有祖先节点.
	 * 即 父节点，父节点的父节点...一直到根节点
	 * 顺序为最开始为父节点，最后的为根节点
	 * @return 当前节点的所有祖先节点
	 */
	public List<TreeNode<E>> getAncestors() {
		return getAncestors(new ArrayList<TreeNode<E>>(), this);
	}
	private List<TreeNode<E>> getAncestors(List<TreeNode<E>> ancestor,
			TreeNode<E> node) {
		TreeNode<E> parentNode = node.getParentNode();
		if (parentNode != null) {
			ancestor.add(parentNode);
			return getAncestors(ancestor, parentNode);
		} else {
			return ancestor;
		}
	}
	/**
	 * 判断传入节点是否是当前节点的祖先节点.
	 * 即 父节点，父节点的父节点...一直到根节点
	 * @param ancestor 查找节点
	 * @return 传入节点是否是当前节点的祖先节点
	 */
	public boolean hasAncestor(TreeNode<E> ancestor) {
		TreeNode<E> parentNode = this.getParentNode();
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
	 * 返回该节点及其所有子孙节点（包含该节点）
	 * @return 该节点及其所有子孙节点（包含该节点）
	 */
	public List<TreeNode<E>> getEveryNode() {
		List<TreeNode<E>> nodeList = new ArrayList<TreeNode<E>>();
		nodeList.add(this);
		getProgenys(nodeList, this);
		return nodeList;
	}
	/**
	 * 返回该节点下的所有子孙节点（不包含该节点）
	 * @return 该节点下的所有子孙节点（不包含该节点）
	 */
	public List<TreeNode<E>> getProgenys() {
		List<TreeNode<E>> nodeList = new ArrayList<TreeNode<E>>();
		getProgenys(nodeList, this);
		return nodeList;
	}
	private void getProgenys(List<TreeNode<E>> nodeList, TreeNode<E> node) {
		if (!node.isLeafNode()) {
			Iterator<TreeNode<E>> iter = node.getChildNodes().iterator();
			while (iter.hasNext()) {
				TreeNode<E> childNode = iter.next();
				nodeList.add(childNode);
				getProgenys(nodeList, childNode);
			}
		}
	}
	/**
	 * 判断传入节点是否是当前节点的后裔.
	 * 即 子节点，子节点的子节点....遍历整个节点树
	 * @param progeny 查找节点
	 * @return 传入节点是否是当前节点的后裔
	 */
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
	 * 从子孙节点中找到指定节点,并将其返回.
	 * @param progeny 查找节点
	 * @return 子孙节点中与传入节点相同的节点
	 */
	public TreeNode<E> findProgeny(TreeNode<E> progeny) {
		if (progeny == null) {
			return null;
		}
		return findProgeny(progeny.getId());
	}
	/**
	 * 从子孙节点中找到指定id的节点,并将其返回
	 * @param progenyId 查找节点ID
	 * @return 子孙节点中与传入节点ID相同的节点
	 */
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
	 * 从子孙节点中找到第一个匹配的节点，并将其返回（包含自身）
	 * @param matcher 匹配器
	 * @return 自身节点以及子孙节点中第一个匹配的节点
	 */
	public TreeNode<E> findTreeNode(Matcher<E> matcher) {
		if (matcher == null) {
			return null;
		}
		return findTreeNode(matcher, this);
	}

	private TreeNode<E> findTreeNode(Matcher<E> matcher, TreeNode<E> treeNode) {
		if (matcher.match(treeNode)) {
			return treeNode;
		} else {
			List<TreeNode<E>> childs = treeNode.getChildNodes();
			Iterator<TreeNode<E>> iter = childs.iterator();
			while (iter.hasNext()) {
				TreeNode<E> child = iter.next();
				TreeNode<E> result = null;
				result = child.findTreeNode(matcher, child);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
	/**
	 * 从子孙节点中找到指定节点，并将其返回（包含自身）
	 * @param node 查找节点
	 * @return 自身节点以及子孙节点中与传入节点相同的节点
	 */
	public TreeNode<E> findTreeNode(TreeNode<E> node) {
		if (node == null) {
			return null;
		}
		return findTreeNode(node.getId());
	}
	/**
	 * 从子孙节点中找到指定id的节点，并将其返回（包含自身）
	 * @param nodeId 超找节点ID
	 * @return 自身节点以及子孙节点中与传入节点ID相同的节点
	 */
	public TreeNode<E> findTreeNode(String nodeId) {
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
	 * 该节点及其所有子节点使用传入执行器进行操作
	 * 深度优先
	 * @param executor 执行器
	 */
	public void each(NodeExecutor<E> executor) {
		executor.execute(this);
		if (!this.isLeafNode()) {
			Iterator<TreeNode<E>> iter = this.getChildNodes().iterator();
			while (iter.hasNext()) {
				iter.next().each(executor);
			}
		}
	}
	/**
	 * 改变当前节点的depth，级联改变子孙节点
	 * @param depth 层级
	 */
	public void changeDepth(int depth) {
		this.depth = depth;
		if (!this.isLeafNode()) {
			Iterator<TreeNode<E>> it = getChildNodes().iterator();
			while (it.hasNext()) {
				TreeNode<E> node = it.next();
				node.changeDepth(depth + 1);
			}
		}
	}
	// ********************************************************************
	//	TreeNode method 上下级节点范围
	// ********************************************************************

	/**
	 * 返回指定子节点的序列值(index)
	 * @param childNode 子节点
	 * @return 指定子节点的序列值(index)
	 */
	public int indexOf(TreeNode<E> childNode) {
		return getChildNodes().indexOf(childNode);
	}
	/**
	 * 返回自己的位置
	 * @return 自己的位置
	 */
	public int getPosition() {
		if (this.getParentNode() == null || this.getParentNode().getChildSize() < 2) {
			return 0;
		}
		return this.getParentNode().indexOf(this);
	}
	/**
	 * 返回是否拥有指定子节点
	 * @param childNode 子节点
	 * @return 是否拥有指定子节点
	 */
	public boolean hasChildNode(TreeNode<E> childNode) {
		return indexOf(childNode) != -1;
	}

	/**
	 * 返回子节点的数量
	 * @return 子节点的数量
	 */
	public int getChildSize() {
		return getChildNodes().size();
	}
	/**
	 * 添加子节点
	 * @param childNode 子节点
	 */
	public void appendChildNode(TreeNode<E> childNode) {
		readyForAddChild(childNode);
		getChildNodes().add(childNode);
	}
	/**
	 * 批量添加子节点
	 * @param childNodes 子节点
	 */
	public void appendChildNodes(TreeNode<E>...childNodes) {
		if (childNodes == null) {
			throw new IllegalArgumentException("传入参数childNodes不能为null");
		}
		for (TreeNode<E> childNode : childNodes) {
			readyForAddChild(childNode);
			getChildNodes().add(childNode);
		}
	}
	/**
	 * 批量添加子节点
	 * @param childNodes 子节点
	 */
	public void appendChildNodes(List<TreeNode<E>> childNodes) {
		if (childNodes == null) {
			throw new IllegalArgumentException("传入参数childNodes不能为null");
		}
		for (TreeNode<E> childNode : childNodes) {
			readyForAddChild(childNode);
			getChildNodes().add(childNode);
		}
	}
	/**
	 * 在指定位置插入子节点
	 * @param childNode 子节点
	 * @param index 插入位置
	 */
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
	 * 在指定节点(refChildNode)之前插入(insertChildNode)
	 * 如果找不到refChildNode就插入在最后
	 * @param childNode 子节点
	 * @param refChildNode 插入参考节点
	 */
	public void insertBefore(TreeNode<E> childNode, TreeNode<E> refChildNode) {
		readyForAddChild(childNode);
		int index = indexOf(refChildNode);
		insertChildNode(childNode, index);
	}
	/**
	 * 移出子节点
	 * @param childNode 子节点
	 */
	public void removeChildNode(TreeNode<E> childNode) {
		readyForRemoveChild(childNode);
		getChildNodes().remove(childNode);
	}
	/**
	 * 移出所有子节点
	 */
	public void removeChildNodes() {
		Iterator<TreeNode<E>> iter =  getChildNodes().iterator();
		while (iter.hasNext()) {
			readyForRemoveChild(
					iter.next());
		}
		getChildNodes().clear();
	}
	/**
	 * 替换指定节点
	 * @param newChild 新节点
	 * @param oldChild 待替换节点
	 */
	public void replaceChild(TreeNode<E> newChild, TreeNode<E> oldChild) {
		int index = indexOf(oldChild);
		if (index != -1) {
			removeChildNode(oldChild);
			insertChildNode(newChild, index);
		}
	}
	/**
	 * 如果传入节点存在，替换节点，不存在就添加
	 * @param child 子节点
	 */
	public void mergeChild(TreeNode<E> child) {
		int index = indexOf(child);
		if (index != -1) {
			LOGGER.debug("node[{}].mergeChild(node[{}]) as replace",
					this.getId(), child.getId());
			removeChildNode(child);
			insertChildNode(child, index);
		} else {
			appendChildNode(child);
		}
	}
	/**
	 * 将当前节点从其父节点移出
	 */
	public void remove() {
		TreeNode<E> parent = this.getParentNode();
		if (parent != null) {
			parent.removeChildNode(this);
		}
	}
	/**
	 * 返回第一个子节点
	 * @return 第一个子节点，没有子节点返回null
	 */
	public TreeNode<E> getFirstChild() {
		if (getChildSize() > 0) {
			return getChildNodes().get(0);
		}
		return null;
	}
	/**
	 * 返回最后一个子节点
	 * @return 最后一个子节点，没有子节点返回null
	 */
	public TreeNode<E> getLastChild() {
		if (getChildSize() > 0) {
			return getChildNodes().get(getChildSize() - 1);
		}
		return null;
	}
	/**
	 * 返回相邻的下一个节点
	 * @return 没有相邻的下一个节点返回null
	 */
	public TreeNode<E> getNextSibling() {
		TreeNode<E> parentNode = getParentNode();
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
	 * 返回相邻的上一个节点
	 * @return	没有相邻的上一个节点返回null;
	 */
	public TreeNode<E> getPreviousSibling() {
		TreeNode<E> parentNode = getParentNode();
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
	 * 当前节点是否是第一个节点.
	 * 只有当前一个节点时,返回true
	 * @return 当前节点是否是第一个节点
	 */
	public boolean isFirst() {
		return getPreviousSibling() == null;
	}
	/**
	 * 当前节点是否是最后一个节点.
	 * 只有当前一个节点时,返回true
	 * @return 当前节点是否是最后一个节点
	 */
	public boolean isLast() {
		return getNextSibling() == null;
	}
	/**
	 * 通过传入的指定比较器对该节点的子节点进行排序
	 * @param comparator 比较器
	 */
	public void sort(Comparator<TreeNode<E>> comparator) {
		Collections.sort(getChildNodes(), comparator);
	}
	/**
	 * 通过传入的指定比较器对该节点的子节点进行排序.
	 * 如果containProgeny为真会排序所有子孙节点（子节点的子节点）
	 * @param comparator 比较器
	 * @param containProgeny 是否包含子孙节点
	 */
	public void sort(Comparator<TreeNode<E>> comparator, boolean containProgeny) {
		if (containProgeny) {
			if (!this.isLeafNode()) {
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
	//	判断关系的方法
	// ********************************************************************
	/**
	 * 判断当前节点是否是传入节点的父节点,使用对象的Relation
	 * @param childNode 子节点
	 * @return 当前节点是否是传入节点的父节点
	 */
	public boolean isParent(TreeNode<E> childNode) {
		return isParent(childNode, getRelation());
	}
	/**
	 * 判断当前节点是否是传入节点的父节点，使用传入的Relation
	 * @param childNode 子节点
	 * @param relation 关系判断对象
	 * @return 当前节点是否是传入节点的父节点
	 */
	public boolean isParent(TreeNode<E> childNode, Relation<TreeNode<E>> relation) {
		return relation.filiation(this, childNode);
	}
	/**
	 * 判断当前节点是否是传入节点的子节点,使用对象的Relation
	 * @param parentNode 父节点
	 * @return  判断当前节点是否是传入节点的子节点
	 */
	public boolean isChild(TreeNode<E> parentNode) {
		return isChild(parentNode, getRelation());
	}
	/**
	 * 判断当前节点是否是传入节点的子节点,使用传入的Relation
	 * @param parentNode 父节点
	 * @param relation 关系判断对象
	 * @return  判断当前节点是否是传入节点的子节点
	 */
	public boolean isChild(TreeNode<E> parentNode, Relation<TreeNode<E>> relation) {
		return relation.filiation(parentNode, this);
	}


	// ********************************************************************
	//	private method
	// ********************************************************************

	// 在添加儿子节点前的处理
	private void readyForAddChild(TreeNode<E> childNode) {
		childNode.depth = getDepth() + 1;
//		childNode.setParentNode(this);
		readyForSetParent(this, false);
	}
	// 在移除儿子节点前的处理
	private void readyForRemoveChild(TreeNode<E> childNode) {
		childNode.changeDepth(ROOT_DEPTH);
//		childNode.setParentNode(null);
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
			parentNode.appendChildNode(this);
		}
			
	}

	// ********************************************************************
	//	override method
	// ********************************************************************

	/**
	 *	返回一个复制的节点，迭代复制所有节点
	 *	@return 复制的节点
	 */
	@Override
	public TreeNode<E> clone() {
		TreeNode<E> node = new TreeNode<E>(getId());
		node.setNodeObject(getNodeObject());
		node.setRelation(getRelation());
		if (!isLeafNode()) {
			Iterator<TreeNode<E>> iter = getChildNodes().iterator();
			while (iter.hasNext()) {
				node.appendChildNode(iter.next().clone());
			}
		}
		return node;
	}
	@Override @SuppressWarnings("unchecked")
	public boolean equals(Object object) {
		if (object == null || !(object instanceof TreeNode)) {
			return false;
		}
		TreeNode<E> treeNode = (TreeNode<E>) object;
		return getRelation().equals(this, treeNode);
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
	//	setter and getter
	// ********************************************************************

	/**
	 * 返回nodeObject
	 * @return nodeObject
	 */
	public E getNodeObject() {
		return nodeObject;
	}

	/**
	 * 设置nodeObject
	 * @param nodeObject nodeObject
	 */
	public void setNodeObject(E nodeObject) {
		this.nodeObject = nodeObject;
	}

	/**
	 * 是否叶节点
	 * @return 是否叶节点
	 */
	public boolean isLeafNode() {
		return getChildSize() == 0;
	}

	/**
	 * 返回childNodes
	 * @return childNodes
	 */
	public List<TreeNode<E>> getChildNodes() {
		return childNodes;
	}

	/**
	 * 返回depth
	 * @return depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * 返回id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 返回relation
	 * @return relation
	 */
	public Relation<TreeNode<E>> getRelation() {
		return relation;
	}

	/**
	 * 设置relation
	 * @param relation relation
	 */
	public void setRelation(Relation<TreeNode<E>> relation) {
		this.relation = relation;
	}

	/**
	 * 返回parentNode
	 * @return parentNode
	 */
	public TreeNode<E> getParentNode() {
		return parentNode;
	}

	/**
	 * 设置parentNode
	 * @param parentNode parentNode
	 */
	public void setParentNode(TreeNode<E> parentNode) {
		readyForSetParent(parentNode, true);		
	}
}