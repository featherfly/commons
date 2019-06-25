
/**
 * @author zhongj - yufei
 *		 	Mar 19, 2009 
 */
package cn.featherfly.common.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cn.featherfly.common.structure.tree.NodeExecutor;
import cn.featherfly.common.structure.tree.Tree;
import cn.featherfly.common.structure.tree.TreeNode;
import cn.featherfly.common.structure.tree.util.TreeNodeTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.testng.Assert.*;
/**
 * @author zhongj - yufei
 *
 */
public class TreeTest {
	TreeNode<String> n = new TreeNode<String>("n");	
	TreeNode<String> n1 = new TreeNode<String>("n1");
	TreeNode<String> n11 = new TreeNode<String>("n11");	
	TreeNode<String> n12 = new TreeNode<String>("n12");	
	TreeNode<String> n121 = new TreeNode<String>("n121");	
	TreeNode<String> n2 = new TreeNode<String>("n2");	
	TreeNode<String> n3 = new TreeNode<String>("n3");	
	TreeNode<String> n31 = new TreeNode<String>("n31");
	TreeNode<String> n311 = new TreeNode<String>("n311");
	
	@BeforeMethod
	public void setUp(){ 		
		n.setNodeObject("n");
		n1.setNodeObject("n1");
		n11.setNodeObject("n11");
		n12.setNodeObject("n12");
		n121.setNodeObject("n121");
		n2.setNodeObject("n2");
		n3.setNodeObject("n3");
		n31.setNodeObject("n31");
		n311.setNodeObject("n311");		

		n.setParentNode(n);
		n1.setParentNode(n);
		n2.setParentNode(n);		
		n3.setParentNode(n);
		n11.setParentNode(n1);
		n12.setParentNode(n1);
		n121.setParentNode(n12);
		n31.setParentNode(n3);
		n311.setParentNode(n31);
	}
	
	//@Test
	public void testAddChild(){
//		Tree<String> tree = new Tree<String>(n);
//		tree.addChildNode(n1);
//		System.out.println("n1");
//		tree.addChildNode(n2);
//		System.out.println("n2");
//		tree.addChildNode(n3);
//		System.out.println("n3");
//		tree.addChildNode(n11);
//		System.out.println("n11");
//		tree.addChildNode(n12);
//		System.out.println("n12");
//		tree.addChildNode(n31);
//		System.out.println("n31");

		assertEquals(n.getFirstChild(), n1);
		assertEquals(n.getLastChild(), n3);
		assertFalse(n.isLeafNode());
		assertEquals(n.getChildSize(), 3);

		assertEquals(n1.getFirstChild(), n11);
		assertEquals(n1.getLastChild(), n12);
		assertFalse(n1.isLeafNode());
		assertEquals(n1.getChildSize(), 2);
		
		assertEquals(n2.getFirstChild(), null);
		assertEquals(n2.getLastChild(), null);
		assertTrue(n2.isLeafNode());
		assertEquals(n2.getChildSize(), 0);
		
		assertEquals(n3.getFirstChild(), n31);
		assertEquals(n3.getLastChild(), n31);
		assertEquals(n3.getChildSize(), 1);
		assertFalse(n3.isLeafNode());
		
		assertEquals(n31.getFirstChild(), null);
		assertEquals(n31.getLastChild(),null);
		assertEquals(n31.getChildSize(), 0);
		assertTrue(n31.isLeafNode());
				
	}
	
	@Test
	public void testAddChild2(){
		List<TreeNode<String>> nodes = new ArrayList<TreeNode<String>>();
		n.setParentNode(null);
		nodes.add(n1);
		nodes.add(n31);
		nodes.add(n12);
		nodes.add(n11);
		nodes.add(n3);
		nodes.add(n2);
		nodes.add(n);
		nodes.add(n311);
		nodes.add(n121);
		Tree<String> tree = new Tree<String>(nodes);
		TreeNodeTestUtils.show(tree.getRootNode());
		
		System.out.println("sort");
		tree.sort(new Comparator<TreeNode<String>>(){
			public int compare(TreeNode<String> o1, TreeNode<String> o2) {
				return o1.getId().toString().compareTo(o2.getId().toString());
			}			
		});
		
		TreeNodeTestUtils.show(tree.getRootNode());
		
		System.out.println(TreeNodeTestUtils.constract(tree.getRootNode()));
		
		final String a="1";
		
		tree.each(new NodeExecutor<String>(){
			public void execute(TreeNode<String> treeNode) {
				System.out.println("executor: "+treeNode.getId()+a);
			}
		});

		assertEquals(n.getFirstChild(), n1);
		assertEquals(n.getLastChild(), n3);
		assertFalse(n.isLeafNode());
		assertEquals(n.getChildSize(), 3);

		assertEquals(n1.getFirstChild(), n11);
		assertEquals(n1.getLastChild(), n12);
		assertFalse(n1.isLeafNode());
		assertEquals(n1.getChildSize(), 2);
		
		assertEquals(n2.getFirstChild(), null);
		assertEquals(n2.getLastChild(), null);
		assertTrue(n2.isLeafNode());
		assertEquals(n2.getChildSize(), 0);
		
		assertEquals(n3.getFirstChild(), n31);
		assertEquals(n3.getLastChild(), n31);
		assertEquals(n3.getChildSize(), 1);
		assertFalse(n3.isLeafNode());
		
		assertEquals(n31.getFirstChild(), n311);
		assertEquals(n31.getLastChild(),n311);
		assertEquals(n31.getChildSize(), 1);
		assertFalse(n31.isLeafNode());
		
		assertEquals(n311.getFirstChild(), null);
		assertEquals(n311.getLastChild(),null);
		assertEquals(n311.getChildSize(), 0);
		assertTrue(n311.isLeafNode());				
	}	
}
