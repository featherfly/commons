
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ClassMappingSupport.java
 * @Package cn.featherfly.common.db
 * @Description: ClassMappingSupport
 * @author: zhongj
 * @date: 2022-11-11 15:47:11
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db;

import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.db.mapping.JdbcPropertyMapping;
import cn.featherfly.common.db.mapping.pojo.User;
import cn.featherfly.common.db.mapping.pojo.UserRole;

/**
 * ClassMappingSupport.
 *
 * @author zhongj
 */
public interface ClassMappingSupport {

    default JdbcClassMapping<User> getUserClassMapping() {
        JdbcClassMapping<User> mapping = new JdbcClassMapping<>(User.class, "user");
        JdbcPropertyMapping pm = new JdbcPropertyMapping();
        pm.setPrimaryKey(true);
        pm.setRepositoryFieldName("id");
        pm.setPropertyName("id");
        pm.setPropertyType(Long.class);

        JdbcPropertyMapping pm2 = new JdbcPropertyMapping();
        pm2.setPrimaryKey(false);
        pm2.setRepositoryFieldName("name");
        pm2.setPropertyName("name");
        pm2.setPropertyType(String.class);

        JdbcPropertyMapping pm3 = new JdbcPropertyMapping();
        pm3.setPrimaryKey(false);
        pm3.setRepositoryFieldName("descp");
        pm3.setPropertyName("descp");
        pm3.setPropertyType(String.class);

        JdbcPropertyMapping pm4 = new JdbcPropertyMapping();
        pm4.setPrimaryKey(false);
        pm4.setRepositoryFieldName("password");
        pm4.setPropertyName("pwd");
        pm4.setPropertyType(String.class);

        mapping.addPropertyMapping(pm);
        mapping.addPropertyMapping(pm2);
        mapping.addPropertyMapping(pm3);
        mapping.addPropertyMapping(pm4);

        return mapping;
    }

    default JdbcClassMapping<UserRole> getUserRoleClassMapping() {
        JdbcClassMapping<UserRole> mapping = new JdbcClassMapping<>(UserRole.class, "user_role");
        JdbcPropertyMapping pm = new JdbcPropertyMapping();
        pm.setPrimaryKey(true);
        pm.setRepositoryFieldName("user_id");
        pm.setPropertyName("userId");
        pm.setPropertyType(Long.class);

        JdbcPropertyMapping pm2 = new JdbcPropertyMapping();
        pm2.setPrimaryKey(true);
        pm2.setRepositoryFieldName("role_id");
        pm2.setPropertyName("roleId");
        pm2.setPropertyType(Long.class);

        JdbcPropertyMapping pm3 = new JdbcPropertyMapping();
        pm3.setPrimaryKey(false);
        pm3.setRepositoryFieldName("descp");
        pm3.setPropertyName("descp");
        pm3.setPropertyType(String.class);

        JdbcPropertyMapping pm4 = new JdbcPropertyMapping();
        pm4.setPrimaryKey(false);
        pm4.setRepositoryFieldName("descp2");
        pm4.setPropertyName("descp2");
        pm4.setPropertyType(String.class);

        mapping.addPropertyMapping(pm);
        mapping.addPropertyMapping(pm2);
        mapping.addPropertyMapping(pm3);
        mapping.addPropertyMapping(pm4);

        return mapping;
    }
}
