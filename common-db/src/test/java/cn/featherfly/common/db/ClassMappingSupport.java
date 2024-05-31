
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

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.db.mapping.JdbcPropertyMapping;
import cn.featherfly.common.db.mapping.pojo.Role;
import cn.featherfly.common.db.mapping.pojo.User;
import cn.featherfly.common.db.mapping.pojo.UserRole;
import cn.featherfly.common.db.mapping.pojo.UserRole2;
import cn.featherfly.common.repository.id.AssignGenerator;
import cn.featherfly.common.repository.mapping.PrimaryKey;

/**
 * ClassMappingSupport.
 *
 * @author zhongj
 */
public interface ClassMappingSupport {

    default String getUserProperty1() {
        return "id";
    }

    default String getUserProperty2() {
        return "name";
    }

    default String getUserProperty3() {
        return "descp";
    }

    default String getUserProperty4() {
        return "pwd";
    }

    default JdbcClassMapping<User> getUserClassMapping(Dialect dialect) {
        JdbcClassMapping<User> mapping = new JdbcClassMapping<>(User.class, "user");
        JdbcPropertyMapping pm = new JdbcPropertyMapping();
        pm.setPrimaryKey(new PrimaryKey(dialect.getIdGenerator("user", "id"), true));
        pm.setRepositoryFieldName("id");
        pm.setPropertyName(getUserProperty1());
        pm.setPropertyType(Long.class);

        JdbcPropertyMapping pm2 = new JdbcPropertyMapping();
        pm2.setPrimaryKey(null);
        pm2.setRepositoryFieldName("name");
        pm2.setPropertyName(getUserProperty2());
        pm2.setPropertyType(String.class);

        JdbcPropertyMapping pm3 = new JdbcPropertyMapping();
        pm3.setPrimaryKey(null);
        pm3.setRepositoryFieldName("descp");
        pm3.setPropertyName(getUserProperty3());
        pm3.setPropertyType(String.class);

        JdbcPropertyMapping pm4 = new JdbcPropertyMapping();
        pm4.setPrimaryKey(null);
        pm4.setRepositoryFieldName("password");
        pm4.setPropertyName(getUserProperty4());
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
        pm.setPrimaryKey(new PrimaryKey(null, false));
        pm.setRepositoryFieldName("user_id");
        pm.setPropertyName("userId");
        pm.setPropertyType(Long.class);

        JdbcPropertyMapping pm2 = new JdbcPropertyMapping();
        pm2.setPrimaryKey(new PrimaryKey(null, false));
        pm2.setRepositoryFieldName("role_id");
        pm2.setPropertyName("roleId");
        pm2.setPropertyType(Long.class);

        JdbcPropertyMapping pm3 = new JdbcPropertyMapping();
        pm3.setPrimaryKey(null);
        pm3.setRepositoryFieldName("descp");
        pm3.setPropertyName("descp");
        pm3.setPropertyType(String.class);

        JdbcPropertyMapping pm4 = new JdbcPropertyMapping();
        pm4.setPrimaryKey(null);
        pm4.setRepositoryFieldName("descp2");
        pm4.setPropertyName("descp2");
        pm4.setPropertyType(String.class);

        mapping.addPropertyMapping(pm);
        mapping.addPropertyMapping(pm2);
        mapping.addPropertyMapping(pm3);
        mapping.addPropertyMapping(pm4);

        return mapping;
    }

    default JdbcClassMapping<UserRole2> getUserRoleClassMapping2() {
        JdbcClassMapping<UserRole2> mapping = new JdbcClassMapping<>(UserRole2.class, "user_role");
        JdbcPropertyMapping pm = new JdbcPropertyMapping();
        pm.setPrimaryKey(new PrimaryKey(new AssignGenerator(), false));
        pm.setRepositoryFieldName("user_id");
        pm.setPropertyName("user");
        pm.setPropertyType(User.class);
        JdbcPropertyMapping upm = new JdbcPropertyMapping();
        upm.setPrimaryKey(new PrimaryKey(new AssignGenerator(), false));
        upm.setRepositoryFieldName("user_id");
        upm.setPropertyName("id");
        upm.setPropertyType(Long.class);
        pm.add(upm);

        JdbcPropertyMapping pm2 = new JdbcPropertyMapping();
        pm2.setPrimaryKey(new PrimaryKey(new AssignGenerator(), false));
        pm2.setRepositoryFieldName("role_id");
        pm2.setPropertyName("role");
        pm2.setPropertyType(Role.class);
        JdbcPropertyMapping rpm = new JdbcPropertyMapping();
        rpm.setPrimaryKey(new PrimaryKey(new AssignGenerator(), false));
        rpm.setRepositoryFieldName("role_id");
        rpm.setPropertyName("id");
        rpm.setPropertyType(Long.class);
        pm2.add(rpm);

        JdbcPropertyMapping pm3 = new JdbcPropertyMapping();
        pm3.setPrimaryKey(null);
        pm3.setRepositoryFieldName("descp");
        pm3.setPropertyName("descp");
        pm3.setPropertyType(String.class);

        JdbcPropertyMapping pm4 = new JdbcPropertyMapping();
        pm4.setPrimaryKey(null);
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
