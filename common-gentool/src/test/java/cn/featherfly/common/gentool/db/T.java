
package cn.featherfly.common.gentool.db;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.db.builder.ColumnModel;
import cn.featherfly.common.db.builder.TableModel;
import cn.featherfly.common.lang.ClassUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * <p>
 * T
 * </p>
 *
 * @author zhongj
 */
public class T {
    public static void main(String[] args) throws TemplateException, IOException {
        freemarker.template.Configuration cfg;
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        System.out.println(ClassUtils.packageToDir(T.class));
        cfg.setClassForTemplateLoading(T.class, "/");
        //        cfg.setTemplateLoader(new ClassTemplateLoader(T.class, ClassUtils.packageToDir(T.class)));

        Map<String, Object> root = new HashMap<>();
        root.put("createTime", new Date());
        root.put("modulePackage", "cn.fetherlfy");
        //        root.put("moduleName", "");
        List<TableModel> tables = new ArrayList<>();
        tables.add(getTableModel());
        tables.add(getMultiKeyTableModel());
        root.put("tables", tables);
        Writer out = new OutputStreamWriter(System.out);
        Template temp = cfg.getTemplate(ClassUtils.packageToDir(T.class) + "/tables.ftl");
        temp.process(root, out);

        for (TableModel tableModel : tables) {
            root = new HashMap<>();
            root.put("createTime", new Date());
            root.put("modulePackage", "cn.fetherlfy");
            root.put("table", tableModel);
            temp = cfg.getTemplate("table.ftl");
            temp.process(root, out);
        }
    }

    public static TableModel getTableModel() {
        String database = "db_test";
        TableModel tableModel = new TableModel();
        tableModel.setName("user");
        tableModel.setRemark("user用户表");
        tableModel.setCatalog(database);

        int index = 0;
        ColumnModel id = new ColumnModel();
        id.setName("id");
        id.setPrimaryKey(true);
        id.setAutoincrement(true);
        id.setColumnIndex(index);
        id.setType(Types.INTEGER);
        id.setSize(11);
        id.setNullable(false);
        id.setRemark("id主键");

        index++;
        ColumnModel name = new ColumnModel();
        name.setName("name");
        name.setColumnIndex(index);
        name.setType(Types.VARCHAR);
        name.setSize(255);
        name.setNullable(false);
        name.setRemark("name名称");

        index++;
        ColumnModel money = new ColumnModel();
        money.setName("money");
        money.setColumnIndex(index);
        money.setType(Types.DECIMAL);
        money.setSize(11);
        money.setDecimalDigits(2);
        money.setNullable(false);
        money.setRemark("money金额");

        index++;
        ColumnModel state = new ColumnModel();
        state.setName("state");
        state.setColumnIndex(index);
        state.setType(Types.TINYINT);
        state.setSize(4);
        state.setDefaultValue("0");
        state.setNullable(false);
        state.setRemark("state状态：0禁用，1启用");

        index++;
        ColumnModel descp = new ColumnModel();
        descp.setName("descp");
        descp.setColumnIndex(index);
        descp.setType(Types.VARCHAR);
        descp.setSize(255);
        descp.setNullable(true);
        descp.setRemark("descp描述");

        tableModel.addColumn(id, name, money, state, descp);
        return tableModel;
    }

    public static TableModel getMultiKeyTableModel() {
        TableModel tableModel = new TableModel();
        tableModel.setName("user_role");
        tableModel.setRemark("user role 关系表");
        //        tableModel.setCatalog(database);

        int index = 0;
        ColumnModel userId = new ColumnModel();
        userId.setName("user_id");
        userId.setPrimaryKey(true);
        userId.setColumnIndex(index);
        userId.setType(Types.INTEGER);
        userId.setSize(11);
        userId.setNullable(false);
        userId.setRemark("user id");

        index++;
        ColumnModel roleId = new ColumnModel();
        roleId.setName("role_id");
        roleId.setPrimaryKey(true);
        roleId.setColumnIndex(index);
        roleId.setType(Types.INTEGER);
        roleId.setSize(11);
        roleId.setNullable(false);
        roleId.setRemark("role id");

        index++;
        ColumnModel descp = new ColumnModel();
        descp.setName("descp");
        descp.setColumnIndex(index);
        descp.setType(Types.VARCHAR);
        descp.setSize(255);
        descp.setNullable(true);
        descp.setRemark("descp描述");

        tableModel.addColumn(userId, roleId, descp);
        return tableModel;
    }
}

//package ${modelPackage};
//
//package ${modelPackage};
///**
// * ${moduleName} tables.
// *
// * create by cn.featherlfy.common:common-db generate tool at ${createTime?string('yyyy-MM-dd')}
// */
//public interface ${moduleName}Tables {
//
//    <#list tables as table>
//    /**
//     * ${table.name} table
//     */
//    ${table.name?capitalize}Table ${table.name?upper_case} = (${table.name?capitalize}Table) new ${table.name?capitalize}Table()
//        <#if table.name??>.setName("${table.name}")</#if><#if table.remark??>.setRemark("${table.remark}")</#if><#if table.type??>.setType("${table.type}")</#if><#if table.catalog??>.setCatalog("${table.catalog}")</#if>;
//    </#list>
//
//}