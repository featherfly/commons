
package cn.featherfly.common.db.data.format;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.db.data.DataFormat;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.TableMetadata;
import cn.featherfly.common.lang.DateUtils;

/**
 * <p>
 * JsonDataFormat
 * </p>
 * 
 * @author zhongj
 */
public class SqlDataFormat implements DataFormat{

	private Writer writer;
	
	private Dialect dialect;
		
	/**
	 * @param writer writer
	 * @param dialect dialect
	 */
	public SqlDataFormat(Writer writer, Dialect dialect) {
		this.writer = writer;
		this.dialect = dialect;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeDataStart(DatabaseMetadata databaseMetadata) throws IOException {
		Date now = new Date();
		StringBuilder descp = new StringBuilder();
		descp.append("/*==============================================================*/\n")
			.append("/*\t").append("\t start at -> ").append(DateUtils.formartTime(now))
			.append("\t timestamp -> ").append(now.getTime()).append("\t*/\n")
			.append("/*==============================================================*/\n")
			.append("\n\n\n");
		writer.write(descp.toString());
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException 
	 */
	@Override
	public void writeDataEnd(DatabaseMetadata databaseMetadata) throws IOException {
		Date now = new Date();
		StringBuilder descp = new StringBuilder();
		descp.append("\n\n\n")
			.append("/*==============================================================*/\n")
			.append("/*\t").append("\t end at -> ").append(DateUtils.formartTime(now))
			.append("\t timestamp -> ").append(now.getTime()).append("\t*/\n")
			.append("/*==============================================================*/\n");
		writer.write(descp.toString());
		writer.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeTableStart(TableMetadata tableMetadata) throws Exception{
		Date now = new Date();
		StringBuilder tableDescp = new StringBuilder();
		tableDescp.append("/*==============================================================*/\n")
			.append("/*\t")
			.append(tableMetadata.getName())
			.append("\t start at -> ")
			.append(DateUtils.formartTime(now))
			.append("\t timestamp -> ").append(now.getTime()).append("\t*/\n")					
			.append("/*--------------------------------------------------------------*/\n");		
		writer.write(tableDescp.toString());
	}

	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	@Override
	public void writeTableEnd(TableMetadata tableMetadata) throws Exception {
		Date now = new Date();
		StringBuilder tableDescp = new StringBuilder();
		tableDescp.append("/*--------------------------------------------------------------*/\n")
			.append("/*\t")
			.append(tableMetadata.getName())
			.append("\t end at -> ")
			.append(DateUtils.formartTime(now))
			.append("\t timestamp -> ").append(now.getTime()).append("\t*/\n")			
			.append("/*==============================================================*/\n")
			.append("\n\n");
		writer.write(tableDescp.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeRow(TableMetadata tableMetadata, ResultSet res) throws Exception {
		ResultSetMetaData rsmd = res.getMetaData();
		int columnTotal = rsmd.getColumnCount();
		String tableName = tableMetadata.getName();
		
		List<Map<String,Map<String, Object>>> rows = new ArrayList<Map<String,Map<String, Object>>>();
		Map<String,Map<String, Object>> row ;
		StringBuilder insertSql = new StringBuilder();
				
		row = new HashMap<String, Map<String, Object>>();
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		values.append(" VALUES ( ");
		columns.append(" ( ");
		for(int i=1;i<=columnTotal;i++){
			Map<String, Object> column = new HashMap<String, Object>();
			String columnName = rsmd.getColumnName(i);
			Object value = res.getObject(columnName);
			int type = rsmd.getColumnType(i);
			// 列名大写
			columns.append(getDialect().wrapName(columnName.toUpperCase())).append(",");
			values.append(getDialect().valueToSql(value, type)).append(",");
			column.put("name", columnName);
			column.put("value", value);
			column.put("type", type);
			row.put(columnName,column);
		}
		rows.add(row);
		if(columns.lastIndexOf(",")+1==columns.length()){
			columns.deleteCharAt(columns.length()-1);
		}
		columns.append(" )");
		if(values.lastIndexOf(",")+1==values.length()){
			values.deleteCharAt(values.length()-1);
		}
		values.append(" )");
		columns.append(values.toString());
		// 表名大写
		insertSql.append("INSERT INTO ").append(getDialect().wrapName(tableName.toUpperCase())).append(columns).append(";\n");
		writer.write(insertSql.toString());
	}

	
	// ********************************************************************
	//	
	// ********************************************************************
	
	/**
	 * 返回dialect
	 * @return dialect
	 */
	public Dialect getDialect() {
		return dialect;
	}
}
