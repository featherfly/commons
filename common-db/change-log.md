# 0.3.6 2020-11-24
    1.修复getDeleteSqlAndParamPositions单列主键时没有返回参数映射map的问题
            
# 0.3.5 2020-11-17
    1.ClassMappingUtils加入getDeleteSqlAndParamPositions(int batchSize,
            ClassMapping<?> classMapping, Dialect dialect)方法
    
# 0.3.4 2020-11-16
    1.加入JdbcMappingFactory接口，并把原来的JdbcMappingFactory类改为JdbcMappingFactoryImpl
    
# 0.3.3 2020-11-16
    1.加入VersionManager
    
# 0.3.2 2020-11-16
    1.Migrator加入updateSql(DataSource previousDataSource, DataSource currentDataSource)等一些列方法，用于比较两个数据库生成更新sql
    2.修正0.3.1使用了jdk15构建的问题,重新使用jdk8构建
    
# 0.3.1 2020-11-11
    1.Dialect实现索引的创建·更新·删除（Mysql,Postgresql）
    2.实现从Entity生成的ClassMapping支持索引，并且生成的建表、更新表的语句已经加上索引信息
    3.Migrator的updateSql方法支持索引

# 0.3.0 2020-7-21
    1.实现Migrator的initSql,updateSql方法
    
# 0.2.5 2020-7-19
    1.修复JdbcMappingFactory中@Transient不是使用javax.persistence.Transient的问题
    2.JdbcMappingFactory加入严格的对象属性到数据库映射，类似JPA,与默认的混合映射不同，具体查看JdbcMappingFactory.MappingMode枚举项的注释
    3.加入ObjectDbMixedMappingFacotry和ObjectToDbMappingFacotry,JdbcMappingFactory只是作为这两个具体实现的代理
    4.加入Migrator，实现通过映射创建数据库表的一些列方法
    
# 0.2.4 2020-7-15
    1.修复0.2.3版本SqlTypeMappingManager的setEnumWithOrdinal,isEnumWithOrdinal方法删除问题
    
# 0.2.3 2020-7-15
    1.SqlTypeMappingManager相关的泛型extends Seriable改为extends Object
   
# 0.2.2 2020-7-15
    1.SqlTypeMappingManager加入setEnumWithOrdinal,isEnumWithOrdinal方法
    2.JdbcMappingFactory构造参数加入SqlTypeMappingManager

# 0.2.1 2020-6-24
    1.修复ClassMappingUtils.getInsertBatchSqlAndParamPositions返回的sql没字段使用对象属性的问题

# 0.2.0 2020-6-3
    1.完善SqlTypeMappingManager相关功能
    2.删除SqlType枚举,使用jdbc的JDBCType代替
    3.JdbcUtils.setParamaeter加入java.util.Optional支持,加入getColumnIndex,getResultSQLType等方法
    
# 0.1.4 2020-5-29
    1.修改LangUtils为Lang,StringUtils为Strings
    2.SqlUtils.transferNamedParamSql改为convertNamedParamSql
    
# 0.1.3 2020-5-23
    1.SqlUtils.convertSelectToCount加入多行sql支持
    
# 0.1.2 2020-4-20
    1.加入代码生成需要的

# 0.1.1 2020-4-20
    1.SqlUtils加入transferNamedParamSql方法，用于转换命名参数sql为传统sql
    2.加入SqlTypeMappingManager，用于管理SqlType和JavaType的对应关系
    3.ClassMappingUtils加入各种sql生成方法
    4.Dialect加入批量插入sql生成方法

# 0.1.0 2020-4-19
    1.从featherfly-db迁移过来
    2.dml builder重构，放弃featherfly-db的cn.featherfly.common.db.builder包的实现
    3.依赖common-model-repository
    4.Dialect加入构建各种ddl的方法