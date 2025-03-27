
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-03-26 22:31:26
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.function.ToBooleanBiFunction;

/**
 * TreeUtils.
 *
 * @author zhongj
 */
public class TreeUtils {

    /**
     * build tree.
     *
     * @param <E> the element type
     * @param nodes the nodes, nodes will be updated.
     *        if the nodes is correct, every one will be remove (nodes size == 0).
     * @param isRoot the is root
     * @param addChild the add child
     * @return the root node
     */
    public static <E> E buildTree(Collection<E> nodes, Predicate<E> isRoot, ToBooleanBiFunction<E, E> addChild) {
        E root = null;
        Iterator<E> iter = nodes.iterator();
        while (iter.hasNext()) {
            E node = iter.next();
            if (isRoot.test(node)) {
                root = node;
                iter.remove();
                break;
            }
        }
        addChild(root, nodes, isRoot, addChild);
        return root;
    }

    /**
     * build node list.
     *
     * @param <E> the element type
     * @param nodes the nodes, nodes will be updated.
     *        if the nodes is correct, every one will be remove (nodes size == 0).
     * @param isRoot the is root
     * @param addChild the add child
     * @return the root node child node list
     */
    public static <E> List<E> buildTreeList(Collection<E> nodes, Predicate<E> isRoot,
        ToBooleanBiFunction<E, E> addChild) {
        List<E> treeList = new ArrayList<>();
        Iterator<E> iter = nodes.iterator();
        while (iter.hasNext()) {
            E node = iter.next();
            if (isRoot.test(node)) {
                treeList.add(node);
                iter.remove();
            }
        }

        for (E node : treeList) {
            addChild(node, nodes, isRoot, addChild);
        }
        return treeList;
    }

    private static <E> void addChild(E parent, Collection<E> nodes, Predicate<E> isRoot,
        ToBooleanBiFunction<E, E> addChild) {
        List<E> addedNodes = new ArrayList<>();
        Iterator<E> iter = nodes.iterator();
        while (iter.hasNext()) {
            E node = iter.next();
            if (addChild.applyAsBoolean(parent, node)) {
                addedNodes.add(node);
                iter.remove();
            }
        }

        for (E node : addedNodes) {
            addChild(node, nodes, isRoot, addChild);
        }
    }

    /**
     * construct.
     *
     * @param <E> the element type
     * @param node the node
     * @param getChildren the get children
     * @return the node construct string
     */
    public static <E> String construct(E node, Function<E, Collection<E>> getChildren) {
        return construct(node, getChildren, Object::toString, 0).toString();
    }

    /**
     * construct.
     *
     * @param <E> the element type
     * @param node the node
     * @param getChildren the get children
     * @param printStr the print str
     * @return the node construct string
     */
    public static <E> String construct(E node, Function<E, Collection<E>> getChildren, Function<E, String> printStr) {
        return construct(node, getChildren, printStr, 0).toString();
    }

    private static <E> StringBuilder construct(E node, Function<E, Collection<E>> getChildren,
        Function<E, String> nodeDescp, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        sb.append(nodeDescp.apply(node)).append("\n");
        for (E child : getChildren.apply(node)) {
            sb.append(construct(child, getChildren, nodeDescp, level + 1));
        }
        return sb;
    }
}
