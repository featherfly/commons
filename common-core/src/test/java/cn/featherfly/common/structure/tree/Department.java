package cn.featherfly.common.structure.tree;

import java.util.ArrayList;
import java.util.List;

class Department {

    /**
     * Instantiates a new test tree.
     *
     * @param id the id
     * @param pid the pid
     */
    public Department(Integer id, Integer pid) {
        this.id = id;
        if (pid != null) {
            parent = new Department(pid, null);
        }
    }

    Integer id;

    Department parent;

    List<Department> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Department [id=" + id + ", parent=" + parent + "]";
    }

    public void show() {
        show(this, 0);
    }

    private void show(Department node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println("id: " + node.id);
        for (Department child : node.children) {
            show(child, level + 1);
        }
    }
}