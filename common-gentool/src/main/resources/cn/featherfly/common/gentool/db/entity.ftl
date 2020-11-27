package ${packageName};

import javax.persistence.Id;
import javax.persistence.Table;

<#assign entityName=table.name?replace("_"," ")?capitalize?replace(" ","")>
/**
 * ${entityName} mapping with table ${table.name}.
 *
 * <#if author??>@author ${author}</#if>
 * create by cn.featherlfy.common:common-db generate tool at ${createTime?string('yyyy-MM-dd')}
 */
@javax.persistence.Entity
@Table(name = "${table.name}")
public class ${entityName}{

    <#list table.columns as column>
    /**
     * property for ${column.name} 
     */
     <#if column.primaryKey>@Id</#if>
     private ${sql_java(column.sqlType)} ${column.name?lower_case?replace("_"," ")?cap_first?replace(" ","")?uncap_first};
    </#list>

    public ${entityName}(){
    }
    
    /**
     * <#list table.primaryColumns as column>
     * @param ${column.name?lower_case?replace("_"," ")?cap_first?replace(" ","")?uncap_first}</#list>
     */
    public ${entityName}(<#list table.primaryColumns as column>${sql_java(column.sqlType)} ${column.name?lower_case?replace("_"," ")?cap_first?replace(" ","")?uncap_first}<#if column?has_next>,</#if></#list>){
    }
    
    <#list table.columns as column>
    <#assign propertyName=column.name?lower_case?replace("_"," ")?cap_first?replace(" ","")?uncap_first>
    /**
     * set ${propertyName} 
     */
     public ${entityName} set${column.name?lower_case?replace("_"," ")?cap_first?replace(" ","")}(${sql_java(column.sqlType)} ${propertyName}){
        this.${propertyName} = ${propertyName};
        return this;
     }
     /**
     * get ${propertyName} 
     */
     public ${sql_java(column.sqlType)} get${column.name?lower_case?replace("_"," ")?cap_first?replace(" ","")}(){
        return ${propertyName};
     }
    </#list>
}