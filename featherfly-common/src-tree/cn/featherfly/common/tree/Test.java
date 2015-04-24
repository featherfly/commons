
package cn.featherfly.common.tree;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.bean.BeanDescriptor;

/**
 * <p>
 * Test
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public class Test {
    
    List<Dep> deps = new ArrayList<Dep>();
        
    public Dep newChild(Dep parent, int i) {
        Dep child = new Dep();
        child.setParent(parent);            
        child.setId(child.getParent().getId() + "__" + i);
        child.setName("node_" + child.getId());
        
        deps.add(child);
        return child;
    }
            
    public void init() {
        
        Dep root = new Dep();
        root.setId("1");
        root.setName("root_node");
        deps.add(root);
        
        
        for (int i = 0; i < 10; i++) {
            Dep child = newChild(root, i);
            if (i < 2) {
                for (int j = 0; j < 3; j++) {
                    Dep childj = newChild(child, j);
                    if (j == 1) {
                        for (int j2 = 0; j2 < 2; j2++) {
                            newChild(childj, j2);
                        }
                    }
                }
            }
            if (i > 8) {
                for (int j = 0; j < 3; j++) {
                    newChild(child, j);
                }
            }
        
//            root.getChildren().add(d1);
        }
    }
    
    public static void main(String[] args) {
        Test t = new Test();
        t.init();
                
        // 加入一个参数，是使用现有对象组装还是使用新对象组装（即所有对象都重新new一个）
        BeanDescriptor<Dep> bd = BeanDescriptor.getBeanDescriptor(Dep.class);
    }
    
}
