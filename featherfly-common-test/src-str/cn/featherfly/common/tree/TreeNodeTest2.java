
/**
 * @author zhongj - yufei
 *		 	Mar 19, 2009 
 */
package cn.featherfly.common.tree;

import cn.featherfly.common.structure.tree.TreeNode;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.testng.Assert.*;
/**
 * @author zhongj - yufei
 *
 */
public class TreeNodeTest2 {
	
	TreeNode<String> n = new TreeNode<String>("n");	
	TreeNode<String> n1 = new TreeNode<String>("n1");
	TreeNode<String> n11 = new TreeNode<String>("n11");	
	TreeNode<String> n12 = new TreeNode<String>("n12");	
	TreeNode<String> n2 = new TreeNode<String>("n2");	
	TreeNode<String> n3 = new TreeNode<String>("n3");	
	TreeNode<String> n31 = new TreeNode<String>("n31");
	
	@BeforeMethod
	public void setUp(){
		n.setNodeObject("n");
		n1.setNodeObject("n1");
		n11.setNodeObject("n11");
		n12.setNodeObject("n12");
		n2.setNodeObject("n2");
		n3.setNodeObject("n3");
		n31.setNodeObject("n31");
		n.appendChildNode(n1);
		n.appendChildNode(n2);
		n.appendChildNode(n3);
		n1.appendChildNode(n11);
		n1.appendChildNode(n12);
		n3.appendChildNode(n31);
	}
	@Test
	public void testHasChildNode(){
		TreeNode<String> nn = n.clone();
		assertEquals(nn, n);
		
		assertEquals(nn.getChildSize(), n.getChildSize());
		
		assertEquals(nn.getFirstChild(), n.getFirstChild());
		assertEquals(nn.getFirstChild().getFirstChild(), n.getFirstChild().getFirstChild());
		assertEquals(nn.getFirstChild().getLastChild(), n.getFirstChild().getLastChild());
		
		assertEquals(nn.getLastChild(), n.getLastChild());
		assertEquals(nn.getLastChild().getFirstChild(), n.getLastChild().getFirstChild());
		assertEquals(nn.getLastChild().getLastChild(), n.getLastChild().getLastChild());
	}
}
