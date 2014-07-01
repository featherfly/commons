
package cn.featherfly.common.tree;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.tree.annotation.Children;
import cn.featherfly.common.tree.annotation.Equals;
import cn.featherfly.common.tree.annotation.Parent;
import cn.featherfly.common.tree.annotation.TreeNode;

/**
 * <p>
 * Dep
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
@TreeNode
public class Dep {
	
	private String id;
	
	private String name;
	
	private Dep parent;
	
	private List<Dep> children = new ArrayList<Dep>();
	
	@Override @Equals
	public boolean equals(Object o) {
		if (o instanceof Dep) {
			Dep dep2 = (Dep) o;
			if (dep2.getId() == null) {
				return false;
			}
			return dep2.getId().equals(this.getId());
		} else {
			return false;
		}
	}

	/**
	 * 返回id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回parent
	 * @return parent
	 */
	@Parent
	public Dep getParent() {
		return parent;
	}

	/**
	 * 设置parent
	 * @param parent parent
	 */
	public void setParent(Dep parent) {
		this.parent = parent;
	}

	/**
	 * 返回children
	 * @return children
	 */
	@Children
	public List<Dep> getChildren() {
		return children;
	}

	/**
	 * 设置children
	 * @param children children
	 */
	public void setChildren(List<Dep> children) {
		this.children = children;
	}
}
