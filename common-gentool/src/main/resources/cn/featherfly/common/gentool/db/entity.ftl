package ${packageName};

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

<#assign entityName=table.name()?replace("_"," ")?capitalize?replace(" ","")>
/**
 * ${entityName} mapping with table ${table.name()}.
 *
 * <#if author??>@author ${author}</#if>
 * create by cn.featherlfy.common:common-db generate tool at ${createTime?string('yyyy-MM-dd')}
 */
@Entity
@Table(name = "${table.name()}")
public class ${entityName}{

    <#list table.columns as column>
    /**
     * property for ${column.name()} 
     */
     <#if column.primaryKey>@Id</#if>
     <#assign propertyName=column.name()?lower_case?replace("_"," ")?capitalize?replace(" ","")?uncap_first>
     private ${sql_java(column.sqlType)} ${propertyName};
    </#list>

    public ${entityName}(){
    }
    
    /**
     * <#list table.primaryColumns as column>
     * @param ${column.name()?lower_case?replace("_"," ")?cap_first?replace(" ","")?uncap_first}</#list>
     */
    public ${entityName}(<#list table.primaryColumns as column>${sql_java(column.sqlType)} ${column.name()?lower_case?replace("_"," ")?cap_first?replace(" ","")?uncap_first}<#if column?has_next>,</#if></#list>){
    }
    
    <#list table.columns as column>
    <#assign propertyName=column.name()?lower_case?replace("_"," ")?capitalize?replace(" ","")?uncap_first>
    /**
     * column ${column.name()}
     * set ${propertyName} 
     */
     public ${entityName} set${propertyName?cap_first}(${sql_java(column.sqlType)} ${propertyName}){
        this.${propertyName} = ${propertyName};
        return this;
     }
     /**
     * get ${propertyName} 
     */
     public ${sql_java(column.sqlType)} get${propertyName?cap_first}(){
        return ${propertyName};
     }
    </#list>
}