package cn.featherfly.common.structure.tree;

import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * TreeNodeModel，树结构模型.
 * </p>
 * 
 * @author 钟冀
 */
public interface TreeNodeModel<T extends TreeNodeModel<T>> {
	/**
	 * 根节点的DEPTH值
	 */
	int ROOT_DEPTH = 0;

	// ********************************************************************
	//	TreeNode method 整个节点范围
	// ********************************************************************
	
	/**
	 * 返回当前节点的所有祖先节点.
	 * 即 父节点，父节点的父节点...一直到根节点.
	 * 顺序为最开始为父节点，最后的为根节点.
	 * @return 当前节点的所有祖先节点
	 */
	List<T> getAncestors();

	/**
	 * 判断传入节点是否是当前节点的祖先节点，等价{@link #isProgeny(TreeNodeModel)}.
	 * 即 父节点，父节点的父节点...一直到根节点
	 * @param ancestor 查找节点
	 * @return 传入节点是否是当前节点的祖先节点
	 */
	boolean hasAncestor(T ancestor);
	/**
	 * 判断当前节点是否是传入节点的后裔，等价{@link #hasAncestor(TreeNodeModel)}.
	 * 即 子节点，子节点的子节点....遍历整个节点树
	 * @param progeny 查找节点
	 * @return 当前节点是否是传入节点的后裔
	 */
	boolean isProgeny(T ancestor);
	
	/**
	 * 返回该节点下的所有子孙节点（包含下级的下级，不包含该节点）.
	 * @return 该节点下的所有子孙节点（包含下级的下级，不包含该节点）
	 */
	List<T> getProgenys();
	
	/**
	 * 返回该节点及其所有子孙节点（包含该节点）
	 * @return 该节点及其所有子孙节点（包含该节点）
	 */
	List<T> getEveryNode();
			
	/**
	 * 判断传入节点是否是当前节点的后裔，等价{@link #isAncestor(TreeNodeModel)}.
	 * 即 子节点，子节点的子节点....遍历整个节点树
	 * @param progeny 查找节点
	 * @return 传入节点是否是当前节点的后裔
	 */
	boolean hasProgeny(T progeny);
	/**
	 * 判断当前节点是否是传入节点的祖先节点，等价{@link #hasProgeny(TreeNodeModel)}.
	 * 即 父节点，父节点的父节点...一直到根节点
	 * @param progeny 查找节点
	 * @return 当前节点是否是传入节点的祖先节点
	 */
	boolean isAncestor(T progeny);

	/**
	 * 从子孙节点中找到指定节点,并将其返回.
	 * @param progeny 查找节点
	 * @return 子孙节点中与传入节点相同的节点
	 */
	T findProgeny(T progeny);

	/**
	 * 从子孙节点中找到指定id的节点,并将其返回
	 * @param progenyId 查找节点ID
	 * @return 子孙节点中与传入节点ID相同的节点
	 */
	T findProgeny(String progenyId);

	/**
	 * 从子孙节点中找到第一个匹配的节点，并将其返回（包含自身）
	 * @param matcher 匹配器
	 * @return 自身节点以及子孙节点中第一个匹配的节点
	 */
	T findTreeNode(TreeNodeMatcher<T> matcher);

	/**
	 * 从子孙节点中找到指定节点，并将其返回（包含自身）
	 * @param node 查找节点
	 * @return 自身节点以及子孙节点中与传入节点相同的节点
	 */
	T findTreeNode(T node);

	/**
	 * 从子孙节点中找到指定id的节点，并将其返回（包含自身）
	 * @param nodeId 超找节点ID
	 * @return 自身节点以及子孙节点中与传入节点ID相同的节点
	 */
	T findTreeNode(String nodeId);

	/**
	 * 该节点及其所有子节点使用传入执行器进行操作
	 * 深度优先
	 * @param executor 执行器
	 */
	void each(NodeExecutor<T> executor);	

	// ********************************************************************
	//	TreeNode method 上下级节点范围
	// ********************************************************************

	/**
	 * 返回指定子节点的序列值(index)
	 * @param childNode 子节点
	 * @return 指定子节点的序列值(index)
	 */
	int indexOf(T childNode);

	/**
	 * 返回自己的位置
	 * @return 自己的位置
	 */
	int getPosition();

	/**
	 * 返回传入节点是否是当前节点的子节点，等价{@link #isParent(TreeNodeModel)}.
	 * @param childNode 子节点
	 * @return 是否是当前节点的子节点
	 */
	boolean hasChildNode(T childNode);
	/**
	 * 判断当前节点是否是传入节点的子节点
	 * @param parentNode 父节点
	 * @return  判断当前节点是否是传入节点的子节点
	 */
	boolean isChildNode(T parentNode);
	/**
	 * 返回当前节点是否是传入节点的父节点，等价{@link #hasChildNode(TreeNodeModel)}.
	 * @param childNode 子节点
	 * @return 当前节点是否是传入节点的父节点
	 */
	boolean isParent(T childNode);	
	/**
	 * 返回子节点的数量
	 * @return 子节点的数量
	 */
	int getChildSize();

	/**
	 * 添加子节点
	 * @param childNode 子节点
	 */
	void addChildNode(T childNode);

	/**
	 * 批量添加子节点
	 * @param childNodes 子节点
	 */
	void addChildNodes(@SuppressWarnings("unchecked") T...childNodes);

	/**
	 * 批量添加子节点
	 * @param childNodes 子节点
	 */
	void addChildNodes(List<T> childNodes);

	/**
	 * 在指定位置插入子节点
	 * @param childNode 子节点
	 * @param index 插入位置
	 */
	void insertChildNode(T childNode, int index);

	/**
	 * 在指定节点(refChildNode)之前插入(insertChildNode)
	 * 如果找不到refChildNode就插入在最后
	 * @param childNode 子节点
	 * @param refChildNode 插入参考节点
	 */
	void insertChildNodeBefore(T childNode,
			T refChildNode);
	/**
	 * 在指定节点(refChildNode)之前插入(insertChildNode)
	 * 如果找不到refChildNode就插入在最后
	 * @param childNode 子节点
	 * @param refChildNode 插入参考节点
	 */
	void insertChildNodeAfter(T childNode,
			T refChildNode);

	/**
	 * 移出子节点
	 * @param childNode 子节点
	 */
	void removeChildNode(T childNode);

	/**
	 * 移出所有子节点
	 */
	void removeChildNodes();

	/**
	 * 替换指定节点
	 * @param newChild 新节点
	 * @param oldChild 待替换节点
	 */
	void replaceChild(T newChild,
			T oldChild);

	/**
	 * 如果传入节点存在，替换节点，不存在就添加
	 * @param child 子节点
	 */
	void mergeChild(T child);

	/**
	 * 将当前节点从其父节点移出
	 */
	void remove();

	/**
	 * 返回第一个子节点
	 * @return 第一个子节点，没有子节点返回null
	 */
	T getFirstChild();

	/**
	 * 返回最后一个子节点
	 * @return 最后一个子节点，没有子节点返回null
	 */
	T getLastChild();

	/**
	 * 返回相邻的下一个节点
	 * @return 没有相邻的下一个节点返回null
	 */
	T getNextSibling();

	/**
	 * 返回相邻的上一个节点
	 * @return	没有相邻的上一个节点返回null;
	 */
	T getPreviousSibling();

	/**
	 * 当前节点是否是第一个节点.
	 * 只有当前一个节点时,返回true
	 * @return 当前节点是否是第一个节点
	 */
	boolean isFirst();

	/**
	 * 当前节点是否是最后一个节点.
	 * 只有当前一个节点时,返回true
	 * @return 当前节点是否是最后一个节点
	 */
	boolean isLast();

	/**
	 * 通过传入的指定比较器对该节点的子节点进行排序
	 * @param comparator 比较器
	 */
	void sort(Comparator<T> comparator);

	/**
	 * 通过传入的指定比较器对该节点的子节点进行排序.
	 * 如果containProgeny为真会排序所有子孙节点（子节点的子节点）
	 * @param comparator 比较器
	 * @param containProgeny 是否包含子孙节点
	 */
	void sort(Comparator<T> comparator,
			boolean containProgeny);

	// ********************************************************************
	//	setter and getter
	// ********************************************************************	

	/**
	 * <p>
	 * 获取唯一标识
	 * </p>
	 *
	 * @return 唯一标识
	 */
	String getId();
	/**
	 * <p>
	 * 返回是否是根
	 * </p>
	 * @return 是否是跟
	 */
	boolean isRoot();
	/**
	 * <p>
	 * 返回是否是叶节点(没有子节点)
	 * </p>
	 * @return 是否是叶节点
	 */
	boolean isLeaf();

	/**
	 * 返回depth
	 * @return depth
	 */
	int getDepth();

	/**
	 * <p>
	 * 返回上级
	 * </p>
	 *
	 * @return 上级
	 */
	T getParent();
	/**
	 * <p>
	 * 返回直属下级
	 * </p>
	 *
	 * @return 直属下级
	 */
	List<T> getChildNodes();
	/**
	 * <p>
	 * 复制
	 * </p>
	 * @return 复制的对象
	 */
	T clone();
	/**
	 * <p>
	 * 复制节点并改变为根节点
	 * </p>
	 * @return 复制的对象
	 */
	T cloneAsRoot();
}