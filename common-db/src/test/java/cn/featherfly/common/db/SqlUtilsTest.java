
package cn.featherfly.common.db;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * copyright cdthgk 2010-2020, all rights reserved.
 * </p>
 * 
 * @author 钟冀
 */
public class SqlUtilsTest {
	public static void main(String[] args) {
		String[] sqls = new String[]{
			"select u.username from user u where u.age > 0",
			"select u.username FROM user u inner join class c on c.id =u.class_id where u.age > 0",
			"select (select p.name from project p where p.id = 1) from user u inner join class c on c.id =u.class_id where u.age > 0",
			"select u.username,(select p.name from project p where p.id = 1) from user u inner join class c on c.id =u.class_id where u.age > 0",
			"select distinct u.username,u.id from user u inner join class c on c.id =u.class_id where u.age > 0"
		};

		for (int i = 0; i < sqls.length; i++) {
			System.out.println("sqls["+i+"]\n\t" + sqls[i] +"\n\t"
					+ SqlUtils.convertSelectToCount(sqls[i]));
		}
	}
}
