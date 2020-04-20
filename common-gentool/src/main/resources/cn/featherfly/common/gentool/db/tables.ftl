package ${packageName};

/**
 * ${moduleName!} tables.
 *
 * <#if author??>@author ${author}</#if>
 * create by cn.featherlfy.common:common-db generate tool at ${createTime?string('yyyy-MM-dd')}
 */
public interface ${moduleName!}Tables {

    <#list tables as table>
    /**
     * ${table.name} table
     */<#assign tableTypeName=table.name?replace("_"," ")?capitalize?replace(" ","")>
    ${tableTypeName}Table ${table.name?upper_case} = (${tableTypeName}Table) new ${tableTypeName}Table()
        .setName("${table.name}")<#if table.remark??>.setRemark("${table.remark}")</#if><#if table.type??>.setType("${table.type}")</#if><#if table.catalog??>.setCatalog("${table.catalog}")</#if>;
    </#list>

}