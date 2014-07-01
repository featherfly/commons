/**
 * @author 钟冀 - yufei
 *		 	Mar 19, 2009
 */
package cn.featherfly.common.structure.tree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.structure.tree.util.TreeNodeTestUtils;


/**
 * <p>
 * 树的数据结构
 * </p>
 * <ul>
 * <li>修改人 钟冀 修改subtree方法，首先深度复制节点，再返回副本对象形成的树对象
 * </ul>
 * @param <E> 节点存放的对象类型
 * @author 钟冀 - yufei
 */
public class Tree<E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Tree.class);

	private TreeNode<E> rootNode;

	private boolean autoClearNodeList = true;

	private Relation<TreeNode<E>> relation = new DefaultRelation<E>();

	private Tree(TreeNode<E> rootNode) {
		if (!getRelation().isRoot(rootNode)) {
			throw new IllegalArgumentException("传入的node不是根节点");
		}
		this.rootNode = rootNode;
	}
	/**
	 * 根据传入的集合创建树，使用DefaultRelation作为关系判断依据.
	 * IllegalArgumentException 在集合中没有找到根节点抛出（根据Relation判断）
	 * @param nodeList 节点列表
	 */
	public Tree(List<TreeNode<E>> nodeList) {
		this(nodeList, new DefaultRelation<E>());
	}
	/**
	 * 根据传入的集合创建树，使用传入的relation作为关系判断依据.
	 * IllegalArgumentException 在集合中没有找到根节点抛出（根据Relation判断）
	 * @param nodeList 节点列表
	 * @param relation 关系判断对象
	 */
	public Tree(List<TreeNode<E>> nodeList, Relation<TreeNode<E>> relation) {
		buildTree(nodeList, relation);
	}
//	YUFEI_TODO 此模式暂时还不支持
//	创建根节点再加子节点
//	public Tree(TreeNode<E> rootNode) {
//		this.rootNode = rootNode;
//	}
	/*
	 * 创建树
	 */
	private void buildTree(List<TreeNode<E>> nodeList, Relation<TreeNode<E>> relation) {
		if (nodeList != null) {
			Iterator<TreeNode<E>> iter = nodeList.iterator();
			while (iter.hasNext()) {
				TreeNode<E> node = iter.next();
				if (getRelation().isRoot(node)) {
					if (rootNode != null) {
						throw new IllegalArgumentException("在TreeNode集合中找到多个根节点");
					}
					rootNode = node;
					iter.remove();
				}
			}
			if (rootNode == null) {
				throw new IllegalArgumentException("没有在TreeNode集合中找到根节点");
			}
			iter = nodeList.iterator();
			while (iter.hasNext()) {
				addChildNode(nodeList, iter.next(), relation);
				//addChildNode(nodeList,getRootNode(), iter.next(),relation);
			}
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("\n" + TreeNodeTestUtils.constract(getRootNode()));
			}
			if (autoClearNodeList) {
				LOGGER.debug("autoClearNodeList = {}，已经自动清理传入的nodeList", autoClearNodeList);
				nodeList.clear();
				nodeList = null;
			}
		}
	}
	/*
	 * 添加子节点
	 */
	private void addChildNode(List<TreeNode<E>> nodeList,
			TreeNode<E> childNode, Relation<TreeNode<E>> relation) {
		rootNode.setRelation(relation);
		if (rootNode.isParent(childNode)) {
			rootNode.mergeChild(childNode);
		} else if (!rootNode.hasProgeny(childNode)) {
			addProgeny(nodeList, childNode, relation);
		}
	}
	/*
	 * 添加子孙
	 */
	private void addProgeny(List<TreeNode<E>> nodes,
			TreeNode<E> progenyNode, Relation<TreeNode<E>> relation) {
		int index = nodes.indexOf(progenyNode.getParentNode());
		if (index < nodes.size() && index > -1) {
			TreeNode<E> progenyParent = nodes.get(index);
			//把当前的节点加它的父节点
			progenyParent.mergeChild(progenyNode);
			//把父节点加入树种
			addChildNode(nodes, progenyParent, relation);
		}
	}
	/**
	 * 根据指定childNode查找树中相同节点，找到就以该节点作为根节点返回一棵子树
	 * @param childNode 查找节点
	 * @return 以查找节点为根的子树
	 */
	public Tree<E> subTree(TreeNode<E> childNode) {
		return subTree(childNode.getId());
	}
	/**
	 * 根据指定id查找树中相同id的节点，找到就以该节点作为根节点返回一棵子树
	 * @param treeNodeId 查找树节点ID
	 * @return 以查找节点为根的子树
	 */
	public Tree<E> subTree(String treeNodeId) {
		TreeNode<E> node = getRootNode().findTreeNode(treeNodeId);
		if (node != null) {
			TreeNode<E> nodeCopy = node.clone();
			getRelation().changeToRoot(nodeCopy);
			return new Tree<E>(nodeCopy);
		}
		return null;
	}
	/**
	 * <p>
	 * 查找指定匹配节点
	 * </p>
	 * @param matcher 匹配器
	 * @return 指定匹配节点
	 */
	public TreeNode<E> find(Matcher<E> matcher) {
		return getRootNode().findTreeNode(matcher);
	}
	/**
	 * 查找指定id的节点
	 * @param id 节点id
	 * @return 查找到的节点
	 */
	public TreeNode<E> find(String id) {
		return getRootNode().findTreeNode(id);
	}
	/**
	 * 查找指定节点
	 * @param node 节点
	 * @return 查找到的节点
	 */
	public TreeNode<E> find(TreeNode<E> node) {
		return getRootNode().findTreeNode(node);
	}
	/**
	 * 返回该树所有节点的列表
	 * @return 树所有节点的列表
	 */
	public List<TreeNode<E>> getEveryNode() {
		return getRootNode().getEveryNode();
	}
	/**
	 * 通过传入的指定比较器对该树进行排序（所有子孙节点都会排序）
	 * @param comparator 比较器
	 */
	public void sort(Comparator<TreeNode<E>> comparator) {
		getRootNode().sort(comparator, true);
	}
	/**
	 * 树的所有子节点使用传入执行器进行操作
	 * @param executor 执行器
	 */
	public void each(NodeExecutor<E> executor) {
		getRootNode().each(executor);
	}

	// ********************************************************************
	// setter and getter
	// ********************************************************************

	/**
	 * 返回rootNode
	 * @return rootNode
	 */
	public TreeNode<E> getRootNode() {
		return rootNode;
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
	 * 返回autoClearNodeList
	 * @return autoClearNodeList
	 */
	public boolean isAutoClearNodeList() {
		return autoClearNodeList;
	}
	/**
	 * 设置autoClearNodeList
	 * @param autoClearNodeList autoClearNodeList
	 */
	public void setAutoClearNodeList(boolean autoClearNodeList) {
		this.autoClearNodeList = autoClearNodeList;
	}
}
