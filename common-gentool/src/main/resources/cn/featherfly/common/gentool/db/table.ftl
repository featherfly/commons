package ${packageName}; 

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.model.ReadonlyColumn;
import cn.featherfly.common.db.model.ReadonlyTable;

 <#assign tableTypeName=table.name()?replace("_"," ")?capitalize?replace(" ","")>
/**
 * ${table.name()} table.
 *
 * <#if author??>@author ${author}</#if>
 * create by cn.featherlfy.common:common-db generate tool at ${createTime?string('yyyy-MM-dd')}
 */
public class ${tableTypeName}Table extends ReadonlyTable{
    <#list table.columns as column>
    /**
     * ${column.name()} column
     */
     <#assign propertyName=column.name()?lower_case?replace("_"," ")?capitalize?replace(" ","")?uncap_first>
     public final Column ${propertyName} = new ReadonlyColumn("${column.name()}", ${column.type}, "${column.typeName!}"
        , ${column.size?string.computer}, "${column.remark!}", "${column.defaultValue!}"
        , ${column.nullable?string("true","false")}, ${column.columnIndex?string.computer}
        , ${column.primaryKey?string("true","false")}, ${column.decimalDigits?string.computer}, ${column.autoincrement?string("true","false")}
        , Tables.${table.name()?upper_case});
    </#list>
    ${tableTypeName}Table(String type, String name, String remark, String catalog, String schema) {
        super(type, name, remark, catalog, schema);
    <#list table.columns as column>
        add(this.${propertyName});
    </#list>
    }
}