
package cn.featherfly.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Dep
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public class Dep {
	
	private String id;
	
	private String name;
	
	private Dep parent;
	
	private List<Dep> children = new ArrayList<Dep>();
	

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
