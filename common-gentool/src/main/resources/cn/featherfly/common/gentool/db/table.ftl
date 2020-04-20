package ${packageName}; 

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.model.SimpleColumn;
import cn.featherfly.common.db.model.SimpleTable;

 <#assign tableTypeName=table.name?replace("_"," ")?capitalize?replace(" ","")>
/**
 * ${table.name} table.
 *
 * <#if author??>@author ${author}</#if>
 * create by cn.featherlfy.common:common-db generate tool at ${createTime?string('yyyy-MM-dd')}
 */
public class ${tableTypeName}Table extends SimpleTable{
    <#list table.columns as column>
    /**
     * ${column.name} column
     */
     public final Column ${column.name?lower_case} = new SimpleColumn().setName("${column.name}").setPrimaryKey(${column.primaryKey?string("true","false")}).setAutoincrement(${column.autoincrement?string("true","false")})
        .setNullable(${column.nullable?string("true","false")}).setType(${column.type}).setColumnIndex(${column.columnIndex}).setDecimalDigits(${column.decimalDigits}).setSize(${column.size})<#if column.typeName??>.setTypeName("${column.typeName}")</#if><#if column.remark??>.setRemark("${column.remark}")</#if><#if column.defaultValue??>.setDefaultValue("${column.defaultValue}")</#if>;
    </#list>
    
    ${tableTypeName}Table() {
    <#list table.columns as column>
        add(${column.name?lower_case});
    </#list>
    }
}