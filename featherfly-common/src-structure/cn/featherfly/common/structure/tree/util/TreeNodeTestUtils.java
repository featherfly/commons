
/**
 * @author 钟冀 - yufei
 *		 	Jul 17, 2009 
 */
package cn.featherfly.common.structure.tree.util;

import java.util.Iterator;

import cn.featherfly.common.structure.tree.Tree;
import cn.featherfly.common.structure.tree.TreeNode;


/**
 * 
 * <p>
 * 用来测试树的一些工具方法
 * </p>
 * 
 * @author 钟冀
 */
public final class TreeNodeTestUtils {
	
	private TreeNodeTestUtils() {
	}
	/**
	 * 返回树的结构字符串
	 * @param <E> 泛型参数
	 * @param tree 树
	 * @return  树的结构字符串
	 */
	public static <E> String constract(Tree<E> tree) {
		return constract(tree.getRootNode());
	}
	/**
	 * 返回树节点的结构字符串
	 * @param <E> 泛型参数
	 * @param treeNode 树节点
	 * @return  树节点的结构字符串
	 */
	public static <E> String constract(TreeNode<E> treeNode) {
		StringBuilder sb = new StringBuilder();
		constract(treeNode, sb);
		return sb.toString();
	}
	
	private static <E> void constract(TreeNode<E> treeNode, StringBuilder sb) {
		StringBuffer space = new StringBuffer("");
		for (int i = 0; i < treeNode.getDepth(); i++) {
			if (i == (treeNode.getDepth() - 1)) {
				space = space.append("|_");
			} else {
				space = space.append(" ");
			}
		}
		sb.append(space + treeNode.getId().toString() + "\n");				
		if (!treeNode.isLeaf()) {
			Iterator<TreeNode<E>> iter = treeNode.getChildNodes().iterator();
			TreeNode<E> node = null;
			while (iter.hasNext()) {
				node = iter.next();
				constract(node, sb);
			}
		}
	}
	/**
	 * 显示树的结构
	 * @param <E> 泛型
	 * @param tree 树
	 */
	public static <E> void show(Tree<E> tree) {
		show(tree.getRootNode());
	}
	/**
	 * 显示节点的结构
	 * @param <E> 泛型
	 * @param node 树节点
	 */
	public static <E> void show(TreeNode<E> node) {
		show(0, node);
	}

	private static <E> void show(int level, TreeNode<E> treeNode) {
		StringBuffer space = new StringBuffer();
		for (int i = 0; i < level; i++) {
			space = space.append(" ");
		}		
		System.out.println("level:" + level + " " + space + treeNode.getId() + "  ");
		
		if (!treeNode.isLeaf()) {
			Iterator<TreeNode<E>> iter = treeNode.getChildNodes().iterator();
			TreeNode<E> node = null;
			while (iter.hasNext()) {
				node = iter.next();
				show(level + 1, node);
			}
		}
	}
}
