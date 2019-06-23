
/**
 * @author 钟冀 - yufei
 *             Aug 25, 2009 
 */
package cn.featherfly.common.structure.tree.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.structure.tree.Tree;
import cn.featherfly.common.structure.tree.TreeNode;


/**
 * 
 * <p>
 * JavaScript 树形组件 MifTree 的帮助类，
 * 以Tree或TreeNode为MifTree生成生成需要的JSON数据
 * </p>
 * 
 * @author 钟冀
 */
public class MifTreeHelper {

    private Tree<?> tree;
    
    private TreeNode<?> treeNode;

    /**
     * 
     * @param tree tree
     */
    public MifTreeHelper(Tree<?> tree) {
        this.tree = tree;
    }
        
    /**
     * 
     * @param treeNode treeNode
     */
    public MifTreeHelper(TreeNode<?> treeNode) {
        this.treeNode = treeNode;
    }
    /**
     * 用list和map封装前台js树需要用到的json格式,包含根
     * @param creator 节点生成器
     * @return 用list和map封装前台js树需要用到的json格式
     */    
    public List<Map<String, Object>> format(MifTreeNodeCreator<?> creator) {
        return format(creator, true);
    }
    /**
     * 
     * 用list和map封装前台js树需要用到的json格式
     * @param creator 节点生成器
     * @param containRoot 是否包含根
     * @return 用list和map封装前台js树需要用到的json格式
     */
    public List<Map<String, Object>> format(MifTreeNodeCreator<?> creator, boolean containRoot) {
        if (tree != null) {
            if (containRoot) {
                return format(tree.getRootNode(), new ArrayList<Map<String, Object>>(), creator);
            } else {
                List<Map<String, Object>> firstNodes = new ArrayList<Map<String, Object>>();
                Iterator<?> iter = tree.getRootNode().getChildNodes().iterator();
                while (iter.hasNext()) {
                    format((TreeNode<?>) iter.next(), firstNodes, creator);
                }
                return firstNodes;
            }
        } else if (treeNode != null) {
            if (containRoot) {
                return format(treeNode, new ArrayList<Map<String, Object>>(), creator);
            } else {
                List<Map<String, Object>> firstNodes = new ArrayList<Map<String, Object>>();
                Iterator<?> iter = treeNode.getChildNodes().iterator();
                while (iter.hasNext()) {
                    format((TreeNode<?>) iter.next(), firstNodes, creator);
                }
                return firstNodes;
            }
        } else {
            throw new IllegalArgumentException("没有Tree或者没有TreeNode");
        }
    }    

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> format(TreeNode<?> treeNode, List<Map<String, Object>> nodeList
                        , MifTreeNodeCreator creator) {
        MifTreeNode node = creator.createNode(treeNode);
        Map<String, Object> nodeMap = new HashMap<String, Object>();
        nodeList.add(nodeMap);
        nodeMap.put("property", node.getProperty());
        nodeMap.put("type", node.getType());
        nodeMap.put("state", node.getState());
        nodeMap.put("data", node.getData());
        if (!treeNode.isLeaf()) {
            List<Map<String, Object>> childNodeList = new ArrayList<Map<String, Object>>();
            nodeMap.put("children", childNodeList);
            Iterator<?> iter = treeNode.getChildNodes().iterator();
            while (iter.hasNext()) {
                format((TreeNode<?>) iter.next(), childNodeList, creator);
            }
        }
        return nodeList;
    }
}
