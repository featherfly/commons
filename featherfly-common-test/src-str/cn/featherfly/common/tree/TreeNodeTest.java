
/**
 * @author 钟冀 - yufei
 *		 	Mar 19, 2009 
 */
package cn.featherfly.common.tree;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import cn.featherfly.common.structure.tree.TreeNode;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author 钟冀 - yufei
 *
 */
public class TreeNodeTest {
	
	TreeNode<String> n;	
	TreeNode<String> n1;
	TreeNode<String> n11;	
	TreeNode<String> n12;	
	TreeNode<String> n2;	
	TreeNode<String> n3;	
	TreeNode<String> n31;
	
	@BeforeMethod
	public void setUp(){
		n = new TreeNode<String>("n");	
		n1 = new TreeNode<String>("n1");
		n11 = new TreeNode<String>("n11");	
		n12 = new TreeNode<String>("n12");	
		n2 = new TreeNode<String>("n2");	
		n3 = new TreeNode<String>("n3");	
		n31 = new TreeNode<String>("n31");
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
		assertTrue(n.hasChildNode(n1));
		assertTrue(n.hasChildNode(n2));
		assertTrue(n.hasChildNode(n3));
		assertFalse(n.hasChildNode(n31));
		assertFalse(n.hasChildNode(n));
		
		assertTrue(n1.hasChildNode(n11));		
		assertTrue(n1.hasChildNode(n12));		
		assertFalse(n1.hasChildNode(n2));		
	}	
	@Test
	public void testDepth(){
		assertEquals(n.getDepth(), 0);
		assertEquals(n1.getDepth(), 1);
		assertEquals(n2.getDepth(), 1);
		assertEquals(n3.getDepth(), 1);
		assertEquals(n11.getDepth(), 2);
		assertEquals(n12.getDepth(), 2);
		assertEquals(n31.getDepth(), 2);		
	}
	@Test
	public void testFirstLast(){
		assertTrue(n.isFirst());
		assertTrue(n.isLast());
		assertTrue(n1.isFirst());
		assertFalse(n1.isLast());
		assertTrue(n11.isFirst());
		assertFalse(n11.isLast());
		assertFalse(n12.isFirst());
		assertTrue(n12.isLast());
		assertFalse(n2.isFirst());
		assertFalse(n2.isLast());
		assertFalse(n3.isFirst());
		assertTrue(n3.isLast());
		assertTrue(n31.isFirst());
		assertTrue(n31.isLast());
	}
	@Test
	public void testFirstChildLastChild(){
		assertEquals(n.getFirstChild(), n1);
		assertEquals(n.getLastChild(), n3);

		assertEquals(n2.getFirstChild(), null);
		assertEquals(n2.getLastChild(), null);
		
		assertEquals(n1.getFirstChild(), n11);
		assertEquals(n1.getLastChild(), n12);
		
		assertEquals(n3.getFirstChild(), n31);
		assertEquals(n3.getLastChild(), n31);
	}
	@Test
	public void testNextPreviousSibling(){
		assertEquals(n.getPreviousSibling(), null);
		assertEquals(n.getNextSibling(), null);
		
		assertEquals(n1.getPreviousSibling(), null);
		assertEquals(n1.getNextSibling(), n2);
		
		assertEquals(n11.getPreviousSibling(), null);
		assertEquals(n11.getNextSibling(), n12);
		
		assertEquals(n12.getPreviousSibling(), n11);
		assertEquals(n12.getNextSibling(), null);
				
		assertEquals(n2.getPreviousSibling(), n1);
		assertEquals(n2.getNextSibling(), n3);
		
		assertEquals(n3.getPreviousSibling(), n2);
		assertEquals(n3.getNextSibling(), null);
		
		assertEquals(n31.getPreviousSibling(), null);
		assertEquals(n31.getNextSibling(), null);
	}
	@Test
	public void testAddChild(){
		TreeNode<String> tn = new TreeNode<String>("tn");
		TreeNode<String> tn1 = new TreeNode<String>("tn1");
		
		assertEquals(tn.getChildNodes().size(), 0);		
		assertEquals(tn.getFirstChild(), null);
		assertEquals(tn1.getParentNode(), null);
		assertEquals(tn1.getDepth(), 0);
		
		tn.appendChildNode(tn1);
		
		assertEquals(tn.getChildNodes().size(), 1);		
		assertEquals(tn.getFirstChild(), tn1);
		assertEquals(tn1.getParentNode(), tn);
		assertEquals(tn1.getDepth(), 1);
		
	}
	@Test
	public void testRemoveChild(){
		assertEquals(n1.getFirstChild(), n11);
		n1.removeChildNode(n11);
		assertEquals(n1.getFirstChild(),n12);
		
		assertEquals(n3.getFirstChild(), n31);
		n3.removeChildNode(n31);
		assertEquals(n3.getFirstChild(),null);
	}
	
	@Test
	public void testRemove(){
		assertEquals(n1.getFirstChild(), n11);
		n11.remove();
		assertEquals(n1.getFirstChild(),n12);
		
		assertEquals(n3.getFirstChild(), n31);
		n31.remove();
		assertEquals(n3.getFirstChild(),null);
	}
	@Test
	public void testIndexOf(){
		assertEquals(n1.indexOf(n11), 0);
		assertEquals(n1.indexOf(n12), 1);
				
		assertEquals(n3.indexOf(n31), 0);
		assertEquals(n3.indexOf(n2), -1);
		
		assertEquals(n.indexOf(n1), 0);
		assertEquals(n.indexOf(n2), 1);
		assertEquals(n.indexOf(n3), 2);
		assertEquals(n.indexOf(n31), -1);
	}
	
	@Test
	public void testInsertChild(){
		TreeNode<String> tn = new TreeNode<String>("tn");
		TreeNode<String> tn1 = new TreeNode<String>("tn1");
		TreeNode<String> tn2 = new TreeNode<String>("tn2");
		TreeNode<String> tn3 = new TreeNode<String>("tn3");
		TreeNode<String> tn4 = new TreeNode<String>("tn4");
		
		tn.appendChildNode(tn1);
		tn.appendChildNode(tn2);
		
		assertEquals(tn.getFirstChild(), tn1);
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		
		tn.insertChildNode(tn3, 0);
				
		assertEquals(tn.getFirstChild(), tn3);
		assertEquals(tn.indexOf(tn1), 1);
		assertEquals(tn.indexOf(tn2), 2);
		
		tn.removeChildNodes();
		
		assertEquals(tn.getChildSize(), 0);
		
		tn.appendChildNode(tn1);
		tn.appendChildNode(tn2);
		
		assertEquals(tn.getFirstChild(), tn1);
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		
		tn.insertChildNode(tn3, 1);
				
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 2);
		assertEquals(tn.indexOf(tn3), 1);
		
		tn.removeChildNodes();
		
		assertEquals(tn.getChildSize(), 0);
		
		tn.appendChildNode(tn1);
		tn.appendChildNode(tn2);
		
		assertEquals(tn.getFirstChild(), tn1);
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		
		tn.insertChildNode(tn3, 100);
				
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		assertEquals(tn.indexOf(tn3), 2);
		
		tn.insertChildNode(tn4, tn.getChildSize());
		
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		assertEquals(tn.indexOf(tn3), 2);
		assertEquals(tn.indexOf(tn4), 3);
	}
	
	@Test
	public void testInsertBefore(){
		TreeNode<String> tn = new TreeNode<String>("tn");
		TreeNode<String> tn1 = new TreeNode<String>("tn1");
		TreeNode<String> tn2 = new TreeNode<String>("tn2");
		TreeNode<String> tn3 = new TreeNode<String>("tn3");
		
		tn.appendChildNode(tn1);
		tn.appendChildNode(tn2);
		
		assertEquals(tn.getFirstChild(), tn1);
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		
		tn.insertBefore(tn3, tn1);
				
		assertEquals(tn.getFirstChild(), tn3);
		assertEquals(tn.indexOf(tn1), 1);
		assertEquals(tn.indexOf(tn2), 2);
		
		tn.removeChildNodes();
		
		assertEquals(tn.getChildSize(), 0);
		
		tn.appendChildNode(tn1);
		tn.appendChildNode(tn2);
		
		assertEquals(tn.getFirstChild(), tn1);
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		
		tn.insertBefore(tn3, tn2);
				
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 2);
		assertEquals(tn.indexOf(tn3), 1);
		
		tn.removeChildNodes();
		
		assertEquals(tn.getChildSize(), 0);
		
		tn.appendChildNode(tn1);
		tn.appendChildNode(tn2);
		
		assertEquals(tn.getFirstChild(), tn1);
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		
		tn.insertBefore(tn3, n);
				
		assertEquals(tn.indexOf(tn1), 0);
		assertEquals(tn.indexOf(tn2), 1);
		assertEquals(tn.indexOf(tn3), 2);
	}
	
	@Test
	public void testReplaceChild(){
		TreeNode<String> tn = new TreeNode<String>("tn");
		TreeNode<String> tn1 = new TreeNode<String>("tn1");
		assertEquals(n.getFirstChild(), n1);
		int size = n.getChildSize();
		
		n.replaceChild(tn, n1);
		assertEquals(n.getFirstChild(), tn);		
		assertEquals(n.getChildSize(), size);
		
		assertEquals(n.getLastChild(), n3);
		n.replaceChild(tn1, n31);
		assertEquals(n.getLastChild(), n3);	
	}
	
	@Test
	public void testHasAncestor(){
		assertTrue(n31.hasAncestor(n3));
		assertTrue(n31.hasAncestor(n));
		assertTrue(n11.hasAncestor(n1));
		assertTrue(n12.hasAncestor(n1));
		assertTrue(n11.hasAncestor(n));
		assertTrue(n12.hasAncestor(n));
		assertFalse(n31.hasAncestor(n2));
		assertFalse(n31.hasAncestor(n1));
	}
	
	@Test
	public void testHasProgeny(){
		assertTrue(n3.hasProgeny(n31));
		assertTrue(n.hasProgeny(n31));
		assertTrue(n.hasProgeny(n3));
		assertTrue(n.hasProgeny(n1));
		assertTrue(n.hasProgeny(n11));
		assertTrue(n.hasProgeny(n12));
		assertTrue(n1.hasProgeny(n11));
		assertTrue(n1.hasProgeny(n12));
		
		assertFalse(n1.hasAncestor(n31));
		assertFalse(n2.hasAncestor(n31));
	}
	
	@Test
	public void testGetAncestors(){
		assertEquals(n31.getAncestors().size(),2);
		assertEquals(n11.getAncestors().size(),2);
		assertEquals(n12.getAncestors().size(),2);
		assertEquals(n1.getAncestors().size(),1);
		assertEquals(n2.getAncestors().size(),1);
		assertEquals(n3.getAncestors().size(),1);
		assertEquals(n.getAncestors().size(),0);
	}
	
	@Test
	public void testFindProgeny(){
		assertTrue(n3.findProgeny(n3)==null);
		assertTrue(n3.findProgeny(n31)!=null);
	}
	
	@Test
	public void testPosition(){
		assertTrue(n.getPosition()==0);
		assertTrue(n1.getPosition()==0);
		assertTrue(n2.getPosition()==1);
		assertTrue(n3.getPosition()==2);
		assertTrue(n12.getPosition()==1);
		assertTrue(n31.getPosition()==0);
	}
}
