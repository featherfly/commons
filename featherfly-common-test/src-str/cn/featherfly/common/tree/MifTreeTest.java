
/**
 * @author zhongj - yufei
 *		 	Mar 19, 2009 
 */
package cn.featherfly.common.tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.structure.tree.Tree;
import cn.featherfly.common.structure.tree.TreeNode;
import cn.featherfly.common.structure.tree.component.MifTreeHelper;
import cn.featherfly.common.structure.tree.component.MifTreeNode;
import cn.featherfly.common.structure.tree.component.MifTreeNodeCreator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * @author zhongj - yufei
 *
 */
public class MifTreeTest {
	
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
	
	
	@Test 
	public void test(){
		MifTreeHelper helper = new MifTreeHelper(tree);
		String str1 = helper.format(new MifTreeNodeCreator<Object>(){
			public MifTreeNode createNode(TreeNode<Object> node) {
				MifTreeNode mifNode = new MifTreeNode();
				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name","testname");
				mifNode.setProperty(propertyMap);
				Map<String, Object> stateMap = new HashMap<String, Object>();
				stateMap.put("open", true);
				mifNode.setState(stateMap);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", new Date().getTime());
				mifNode.setData(dataMap);
				mifNode.setType("folder");
				return mifNode;
			}
		}, false).toString();
		System.out.println(str1);
		
		MifTreeHelper helper2 = new MifTreeHelper(tree.getRootNode());
		String str2 = helper2.format(new MifTreeNodeCreator<Object>(){
			public MifTreeNode createNode(TreeNode<Object> node) {
				MifTreeNode mifNode = new MifTreeNode();
				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name","testname");
				mifNode.setProperty(propertyMap);
				Map<String, Object> stateMap = new HashMap<String, Object>();
				stateMap.put("open", true);
				mifNode.setState(stateMap);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", new Date().getTime());
				mifNode.setData(dataMap);
				mifNode.setType("folder");
				return mifNode;
			}
		}, false).toString();
		System.out.println(str2);
		
		System.out.println(str1.equals(str2));
	}
}
