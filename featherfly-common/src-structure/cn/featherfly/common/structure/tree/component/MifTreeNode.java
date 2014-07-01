
/**
 * @author 钟冀 - yufei
 *		 	Aug 25, 2009 
 */
package cn.featherfly.common.structure.tree.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * <p>
 * MifTree节点
 * </p>
 * @author 钟冀
 */
public class MifTreeNode {

	private static final String NAME_KEY = "name";
	private static final String OPEN_KEY = "open";
	private static final String CHECKED_KEY = "checked";
	private static final String ID_KEY = "id";
	private static final String LOADABLE_KEY = "loadable";

	/**
	 */
	public MifTreeNode() {
	}

	/**
	 * <p>
	 * 添加属性
	 * </p>
	 * @param name 属性名
	 * @param value 属性值
	 */
	public void addProperty(String name, Object value) {
		getProperty().put(name, value);
	}
	/**
	 * <p>
	 * 添加状态
	 * </p>
	 * @param name 名
	 * @param value 值
	 */
	public void addState(String name, Object value) {
		getState().put(name, value);
	}
	/**
	 * <p>
	 * 添加数据
	 * </p>
	 * @param name 名
	 * @param value 值
	 */
	public void addData(String name, Object value) {
		getData().put(name, value);
	}

	/**
	 * <p>
	 * 添加子节点
	 * </p>
	 * @param child 子节点
	 */
	public void addChild(MifTreeNode child) {
		children.add(child);
	}

	/**
	 * <p>
	 * 设置id
	 * </p>
	 * @param id id
	 */
	public void setId(String id) {
		getData().put(ID_KEY, id);
	}

	/**
	 * <p>
	 * 返回id
	 * </p>
	 * @return id
	 */
	@JsonIgnore
	public String getId() {
		Object id = getData().get(ID_KEY);
		if (id != null) {
			return id.toString();
		}
		return null;
	}

	/**
	 * <p>
	 * 设置名称
	 * </p>
	 * @param name 名称
	 */
	public void setName(String name) {
		getProperty().put(NAME_KEY, name);
	}

	/**
	 * <p>
	 * 返回name
	 * </p>
	 * @return name
	 */
	@JsonIgnore
	public String getName() {
		Object name = getProperty().get(NAME_KEY);
		if (name != null) {
			return name.toString();
		}
		return null;
	}

	/**
	 * <p>
	 * 设置节点是否加载
	 * </p>
	 * @param loadable 节点是否加载
	 */
	public void setLoadable(boolean loadable) {
		getProperty().put(LOADABLE_KEY, loadable);
	}

	/**
	 * <p>
	 * 返回节点是否加载
	 * </p>
	 * @return 节点是否加载
	 */
	@JsonIgnore
	public boolean isLoadable() {
		return Boolean.parseBoolean(getProperty().get(LOADABLE_KEY) + "");
	}

	/**
	 * <p>
	 * 设置是否展开
	 * </p>
	 * @param open 是否展开
	 */
	public void setOpen(boolean open) {
		getState().put(OPEN_KEY, true);
	}

	/**
	 * <p>
	 * 返回是否展开
	 * </p>
	 * @return 是否展开
	 */
	@JsonIgnore
	public boolean isOpen() {
		return Boolean.parseBoolean(getState().get(OPEN_KEY) + "");
	}

	/**
	 * <p>
	 * 设置是否选中
	 * </p>
	 * @param open 是否选中
	 */
	public void setChecked(boolean checked) {
		if (checked) {
			getState().put(CHECKED_KEY, "checked");
		} else {
			getState().put(CHECKED_KEY, "unchecked");
		}
	}

	/**
	 * <p>
	 * 返回是否选中
	 * </p>
	 * @return 是否选中
	 */
	@JsonIgnore
	public boolean isChecked() {
		return "checked".equals(getState().get(CHECKED_KEY) + "");
	}

	// ********************************************************************
	//	property
	// ********************************************************************

	private Map<String, Object> property = new HashMap<String, Object>(0);
	private Map<String, Object> state = new HashMap<String, Object>(0);
	private Map<String, Object> data = new HashMap<String, Object>(0);
	private String type;
	private List<MifTreeNode> children = new ArrayList<MifTreeNode>(0);

	/**
	 * 返回children
	 * @return children
	 */
	public List<MifTreeNode> getChildren() {
		return children;
	}
	/**
	 * 设置children
	 * @param children children
	 */
	public void setChildren(List<MifTreeNode> children) {
		this.children = children;
	}

	/**
	 * 返回property
	 * @return property
	 */
	public Map<String, Object> getProperty() {
		return property;
	}
	/**
	 * 设置property
	 * @param property property
	 */
	public void setProperty(Map<String, Object> property) {
		this.property = property;
	}
	/**
	 * 返回state
	 * @return state
	 */
	public Map<String, Object> getState() {
		return state;
	}
	/**
	 * 设置state
	 * @param state state
	 */
	public void setState(Map<String, Object> state) {
		this.state = state;
	}
	/**
	 * 返回data
	 * @return data
	 */
	public Map<String, Object> getData() {
		return data;
	}
	/**
	 * 设置data
	 * @param data data
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	/**
	 * 返回type
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置type
	 * @param type type
	 */
	public void setType(String type) {
		this.type = type;
	}
}