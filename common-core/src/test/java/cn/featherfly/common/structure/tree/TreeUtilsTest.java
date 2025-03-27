
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-03-27 14:30:27
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure.tree;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Lang;

/**
 * TreeUtilsTest.
 *
 * @author zhongj
 */
public class TreeUtilsTest {

    private static final String DEFAULT_CONSTRUCT_DESCP = "Department [id=0, parent=null]\n"
        + "  Department [id=1, parent=Department [id=0, parent=null]]\n"
        + "    Department [id=11, parent=Department [id=1, parent=Department [id=0, parent=null]]]\n"
        + "  Department [id=2, parent=Department [id=0, parent=null]]\n"
        + "    Department [id=21, parent=Department [id=2, parent=Department [id=0, parent=null]]]\n"
        + "    Department [id=22, parent=Department [id=2, parent=Department [id=0, parent=null]]]\n"
        + "      Department [id=221, parent=Department [id=22, parent=Department [id=2, parent=Department [id=0, parent=null]]]]\n";

    private static final String CONSTRUCT_DESCP = "id: 0\n"
        + "  id: 1\n"
        + "    id: 11\n"
        + "  id: 2\n"
        + "    id: 21\n"
        + "    id: 22\n"
        + "      id: 221\n";

    List<Department> list = Lang.list(new Department(0, null), new Department(1, 0), new Department(2, 0),
        new Department(21, 2), new Department(22, 2), new Department(221, 22), new Department(11, 1));

    @Test
    void test() {
        List<Department> nodes = new ArrayList<>(list);
        Department root = TreeUtils.buildTree(nodes, n -> 0 == n.id, (parent, node) -> {
            if (node.parent != null && node.parent.id == parent.id) {
                parent.children.add(node);
                node.parent = parent;
                return true;
            }
            return false;
        });
        root.show();

        assertEquals(nodes.size(), 0);

        assertEquals(root.getId(), 0);

        assertEquals(root.getChildren().size(), 2);

        assertEquals(root.getChildren().get(0).getId(), 1);
        assertEquals(root.getChildren().get(1).getId(), 2);

        String s = TreeUtils.construct(root, n -> n.children, n -> "id: " + n.id);
        System.out.println(s);
        assertEquals(s, CONSTRUCT_DESCP);

        s = TreeUtils.construct(root, n -> n.children);
        System.out.println(s);
        assertEquals(s, DEFAULT_CONSTRUCT_DESCP);
    }
}
