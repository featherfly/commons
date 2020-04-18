
package cn.featherfly.common.db.builder;



/**
 * <p>
 * 条件建造者工具
 * </p>
 * @author zhongj
 */
public class ConditionBuildUtils {

	/**
	 * <p>
	 * 条件添加工具
	 * </p>
	 * @param condition 现有条件
	 * @param appendCondition 添加条件
	 */
	public static void appendCondition(StringBuilder condition, String appendCondition) {
		if (condition != null) {
			if (condition.length() == 0) {
				condition.append(appendCondition.trim());
			} else if (condition.charAt(condition.length() - 1) == ' ') {
				condition.append(appendCondition.trim());
			} else {
				condition.append(" ").append(appendCondition.trim());
			}
		}
	}
}
