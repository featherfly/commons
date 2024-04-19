
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

import java.util.Collection;

import cn.featherfly.common.db.builder.ColumnModel;
import cn.featherfly.common.db.metadata.CatalogMetadata;
import cn.featherfly.common.db.metadata.SchemaMetadata;
import cn.featherfly.common.db.metadata.TableMetadata;
import cn.featherfly.common.lang.Lang;

/**
 * MetadataSupport.
 *
 * @author zhongj
 */
public interface MetadataSupport {

    SchemaMetadata SCHEMA = new SchemaMetadata(new CatalogMetadata(null) {
        @Override
        public String getName() {
            return "default";
        }
    }) {
        @Override
        public String getName() {
            return "default";
        }
    };

    default TableMetadata getUserMetadata() {
        return new TableMetadata(SCHEMA) {
            @Override
            public String getName() {
                return "user";
            }

            @Override
            public Collection<Column> getColumns() {
                return Lang.list(new ColumnModel("id"), new ColumnModel("name"), new ColumnModel("descp"),
                    new ColumnModel("password"));
            }
        };
    }

    default TableMetadata getUserRoleMetadata() {
        return new TableMetadata(SCHEMA) {
            /**
             * {@inheritDoc}
             */
            @Override
            public String getName() {
                return "user_role";
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public Collection<Column> getColumns() {
                return Lang.list(new ColumnModel("user_id"), new ColumnModel("role_id"), new ColumnModel("descp"),
                    new ColumnModel("descp2"));
            }
        };
    }
}
