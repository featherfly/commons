
/**
 * @author zhongj - yufei
 *		 	Mar 19, 2009 
 */
package cn.featherfly.common.tree;

import java.util.ArrayList;
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
public class TreeTest2 {
	
	Tree<String> tree;
	
	TreeNode<String> n = new TreeNode<String>("n");	
		
	@BeforeMethod
	public void setUp(){
		List<TreeNode<String>> nodeList = new ArrayList<TreeNode<String>>();
		n.setNodeObject("n");
		nodeList.add(n);
		TreeNode<String> parent = n;
		
		for(int i=1;i<=15;i++){			
			TreeNode<String> node = new TreeNode<String>("n"+i);
			node.setNodeObject("n"+i);
			node.setParentNode(parent);			
			parent = node;
			nodeList.add(node);
			if(i==1){
				int index = i+1;
				for(int j=1;j<=3;j++){					
					TreeNode<String> node1 = new TreeNode<String>("n"+index+j);
					node1.setNodeObject("n"+index+j);
					node1.setParentNode(node);
					nodeList.add(node1);
					
					TreeNode<String> node2 = new TreeNode<String>("n"+index+j+j);
					node2.setNodeObject("n"+index+j+j);
					node2.setParentNode(node1);
					nodeList.add(node2);
				}
			}
			if(i==3){
				int index = i+1;
				for(int j=1;j<=5;j++){					
					TreeNode<String> node1 = new TreeNode<String>("n"+index+j);
					node1.setNodeObject("n"+index+j);
					node1.setParentNode(node);
					nodeList.add(node1);
				}
			}
		}
		tree = new Tree<String>(nodeList);
	}
	
	//@Test
	public void testAddChild(){
		System.out.println(TreeNodeTestUtils.constract(tree));
		System.out.println("------------");
		Tree<String> t;
		System.out.println(TreeNodeTestUtils.constract(tree.subTree("n")));
		System.out.println("------------");		
		System.out.println(TreeNodeTestUtils.constract(t=tree.subTree("n2")));
		System.out.println(t.getRootNode().getDepth());
		System.out.println(t.getRootNode().getProgenys().size());
		System.out.println(t.getRootNode().getEveryNode().size());
		System.out.println("------------");		
		System.out.println(TreeNodeTestUtils.constract(t=tree.subTree("n21")));
		System.out.println(t.getRootNode().getDepth());
		System.out.println(t.getRootNode().getProgenys().size());
		System.out.println(t.getRootNode().getEveryNode().size());
		System.out.println("------------");		
		System.out.println(TreeNodeTestUtils.constract(t=tree.subTree("n211")));
		System.out.println(t.getRootNode().getDepth());
		System.out.println(t.getRootNode().getProgenys().size());
		System.out.println(t.getRootNode().getEveryNode().size());
		System.out.println("------------");		
		System.out.println(TreeNodeTestUtils.constract(t=tree.subTree("n4")));
		System.out.println(t.getRootNode().getDepth());
		System.out.println(t.getRootNode().getProgenys().size());
		System.out.println(t.getRootNode().getEveryNode().size());
		System.out.println("------------");		
		System.out.println(TreeNodeTestUtils.constract(t=tree.subTree("n41")));
		System.out.println(t.getRootNode().getDepth());
		System.out.println(t.getRootNode().getProgenys().size());
		System.out.println(t.getRootNode().getEveryNode().size());
		
		System.out.println(tree.getRootNode().getProgenys().size());
	}
	
	@Test 
	public void testEach(){
		System.out.println(TreeNodeTestUtils.constract(tree));
		tree.each(new NodeExecutor<String>(){
			public void execute(TreeNode<String> treeNode) {
				System.out.println("executor: "+treeNode.getId());
			}
		});
		System.out.println("-----------");		
	}
	
	@Test
	public void testSubTree(){
		Tree<String> t1 = tree.subTree("n11");
		Tree<String> t2 = tree.subTree("n11");
		//assertEquals(t1, t2);
		assertEquals(t1.getRootNode(), t2.getRootNode());
		assertEquals(t1.getRootNode().getChildNodes(), t2.getRootNode().getChildNodes());
		assertEquals(t1.getRootNode().getFirstChild(), t2.getRootNode().getFirstChild());
		assertEquals(t1.getRootNode().getFirstChild().getChildNodes(), t2.getRootNode().getFirstChild().getChildNodes());
		
	}
}
