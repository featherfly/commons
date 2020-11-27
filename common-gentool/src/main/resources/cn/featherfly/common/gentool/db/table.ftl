package ${packageName}; 

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.model.SimpleColumn;
import cn.featherfly.common.db.model.ReadonlyTable;

 <#assign tableTypeName=table.name?replace("_"," ")?capitalize?replace(" ","")>
/**
 * ${table.name} table.
 *
 * <#if author??>@author ${author}</#if>
 * create by cn.featherlfy.common:common-db generate tool at ${createTime?string('yyyy-MM-dd')}
 */
public class ${tableTypeName}Table extends ReadonlyTable{
    <#list table.columns as column>
    /**
     * ${column.name} column
     */
     public final Column ${column.name?lower_case} = new SimpleColumn().setName("${column.name}").setPrimaryKey(${column.primaryKey?string("true","false")}).setAutoincrement(${column.autoincrement?string("true","false")})
        .setNullable(${column.nullable?string("true","false")}).setType(${column.type}).setColumnIndex(${column.columnIndex?string.computer}).setDecimalDigits(${column.decimalDigits?string.computer}).setSize(${column.size?string.computer})<#if column.typeName??>.setTypeName("${column.typeName}")</#if><#if column.remark??>.setRemark("${column.remark}")</#if><#if column.defaultValue??>.setDefaultValue("${column.defaultValue}")</#if>
        .setTable(Tables.${table.name?upper_case});
    </#list>
    
    ${tableTypeName}Table(String type, String name, String remark, String catalog, String schema) {
        super(type, name, remark, catalog, schema);
    <#list table.columns as column>
        add(this.${column.name?lower_case});
    </#list>
    }
}