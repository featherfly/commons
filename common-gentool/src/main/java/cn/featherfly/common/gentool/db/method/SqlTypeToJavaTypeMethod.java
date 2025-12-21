package cn.featherfly.common.gentool.db.method;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.util.List;

import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.lang.AssertIllegalArgument;
import freemarker.ext.beans.GenericObjectModel;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * <p>
 * WrapMethodModel
 * </p>
 *
 * @author zhongj
 */
public class SqlTypeToJavaTypeMethod implements TemplateMethodModelEx {

    private SqlTypeMappingManager manager;

    /**
     * @param manager manager
     */
    public SqlTypeToJavaTypeMethod(SqlTypeMappingManager manager) {
        super();
        AssertIllegalArgument.isNotNull(manager, "SqlTypeMappingManager");
        this.manager = manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
        if (arguments.size() != 1) {
            throw new RuntimeException("Wrong arguments, only one argument allow");
        }
        Object type = arguments.get(0);
        SQLType sqlType = null;
        if (type instanceof SimpleNumber) {
            SimpleNumber sn = (SimpleNumber) type;
            Number n = sn.getAsNumber();
            if (n.getClass() == Integer.class || n.getClass() == Integer.TYPE) {
                sqlType = JDBCType.valueOf((int) n);
            }
        } else if (type instanceof GenericObjectModel) {
            sqlType = JDBCType.valueOf(((GenericObjectModel) type).getAsString());
        }
        if (sqlType == null) {
            throw new RuntimeException(
                "Wrong argument, it must be a sql type, only int,Integer,String,SqlType types support");
        }
        return manager.getJavaType(sqlType).getName();
    }

}