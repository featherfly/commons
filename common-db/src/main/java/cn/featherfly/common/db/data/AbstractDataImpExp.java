
package cn.featherfly.common.db.data;

import java.sql.Types;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * 抽象导入导出器工具
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractDataImpExp{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * <p>
	 * 根据表名称获取查询sql
	 * </p>
	 * @param tableName 表名称
	 * @return 查询sql
	 */
	protected String getTableQuerySql(String tableName) {
		return "select * from " + tableName;
	}
	
//	/**
//	 * <p>
//	 * 根据表名称获取查询对象
//	 * </p>
//	 * @param tableName 表名称
//	 * @return 查询对象
//	 */
//	protected Query getTableQuery(String tableName) {
//		return new TableQuery(tableName);
//	}

	/*
	 * 数据源
	 */
	private DataSource dataSource;

	// 数据库方言
	private Dialect dialect;

	/**
	 * 返回dialect
	 * @return dialect
	 */
	public Dialect getDialect() {
		if (dialect == null) {
			throw new ImportException("#dialect.null");
//			throw new ImportException("dialect 未设置");
		}
		return dialect;
	}

	/**
	 * 设置dialect
	 * @param dialect dialect
	 */
	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	/**
	 * <p>
	 * 设置数据源
	 * </p>
	 * @param dataSource 数据源
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 返回dataSource
	 * @return dataSource
	 */
	protected DataSource getDataSource() {
		if (dataSource == null) {
			throw new ExportException("#dataSource.null");
//			throw new ExportException("dataSource未设置");
		}
		return dataSource;
	}
	
	/**
	 * <p>
	 * 返回DatabaseMetadata
	 * </p>
	 * @return DatabaseMetadata
	 */
	protected DatabaseMetadata getDatabaseMetadata() {
		return DatabaseMetadataManager.getDefaultManager().create(getDataSource(), getCheckedDatabase());
	}
	
	private String getCheckedDatabase() {
		String database = JdbcUtils.getCatalog(getDataSource());
		if (LangUtils.isEmpty(database)) {
			database = JdbcUtils.getCatalog(getDataSource());
			if (database == null) {
				throw new ExportException("#database.null");
//				throw new ExportException("数据库连接的具体库名称不能为空！");
			}
		}
		return database;
	}

	/**
	 * <p>
	 * 把字符串转换为指定类型的sql语句参数
	 * </p>
	 * @param value 值
	 * @param type javax.sql.Types定义的type
	 * @param isNull 是否为null
	 * @return sql语句参数
	 */
	protected String getValueToSql(String value, int type, String isNull) {
		String v =  null;
		// "1"代表isNull属性的值为真
		if ("1".equals(isNull) && LangUtils.isEmpty(value)) {
			v = "null";
		} else {
			v = getDialect().valueToSql(value, type);
		}
		return v;
	}

	/**
	 * <p>
	 * 把字符串表示的type转换为int表示的type
	 * </p>
	 * @param strType type
	 * @return javax.sql.Types定义的type
	 */
	protected int getType(String strType) {
		int type = Types.VARCHAR;
		try {
			type = Integer.parseInt(strType);
		} catch (Exception e) {
			logger.warn("转换sql类型失败，目标类型 -> " + strType);
		}
		return type;
	}
}
