TODO 加入方言自发现功能，即匹配数据库连接字符串模式来获取

# 0.6.0
1. 修复cn.featherfly.common.repository.operate包移动到cn.featherfly.common.operator的问题
2. 重构SqlSelectBasicBuilder逻辑，抽离出SqlSelectColumnsBuilder接口以及对应的实现
3. 重构distinct相关逻辑
4. 优化SqlTypeMappingManager关于JavaSqlTypeMapper的逻辑
5. 加入JdbcPropertyMapping
6. 加入FieldValueOperator，用于优化Entity的数据设置与解析不走SqlTypeMappingManager
7. Dialect加入buildDeleteFromSql方法,SqlDeleteFromBasicBuilder加入tableAlias支持
8. 修复SqliteDialect的buildInsertBatchSql方法返回sql不正确的问题
9. 优化ClassMappingUtils的getInsertBatchSqlAndParamPositions,getInsertSqlAndParamPositions方法
10. 修复SqlUtils.convertNamedParamSql参数Map内的key存在而value为null时报错的问题
11. SqlFile加入存储过程支持
12. 修复getResultSetMap(ResultSet rs),getResultSetArray(ResultSet rs)参数越界的问题
13. JdbcUtils加入存储过程的参数设置和存储过程的OUT Param的获取
14. ignorePolicy重命名为ignoreStrategy
15. 完善CatalogMetadata,SchemaMetadata,DatabaseMetadata
16. 修复SelectColumnElement未设置distinct的问题
17. SqlSortBuilder实现asc(String tableAlias, Field field),desc(String tableAlias, Field field)
18. SqlSelectColumnsBuilder加入setColumnAliasPrefixProcessor(BiFunction)，setColumnAliasPrefixTableAlias(boolean)，并实现build()逻辑
19. JdbcUtils加入获取各种类型的返回参数方法JdbcUtils.getXXX(ResultSet,int), 
20. JdbcUtils加入lookupColumnName(ResultSetMetaData,int,boolean) 当返回的column name没有设置label时，对下划线进行驼峰命名转换
21. 加入多种基础类型对应的JavaTypeSqlTypeOperator实现
22. 加入AutoCloseConnection以及对应的Statement,PreparedStatement,CallableStatement,
    调用close()方法会自动调用其创建的Statement（或ResultSet）的close()，例如：
    ```java
    Connection conn = AutoCloseConnection
    Statement stat = conn.createStatement();
    PreparedStatement prep = conn.preparedStatement("");
    PreparedStatement call = conn.prepareCall("");
    ResultSet res = stat|prep|call.executeQuery("");
    conn.close(); // 会自动依次关闭ResultSet，再关闭Statement
    ```
23. ConditionColumnElement支持ComparisonOperator.NEW|NSW|NCO|NL|BA|NBA
24. SqlSelectColumnsBuilder加入clearColumns()方法

# 0.5.5 2022-8-11
1. Dialect加入getKeywordLike(QueryPolicy)、getKeywordEq(QueryPolicy)、keywordsCase()方法
2. Dialect删除isKeywordUpcase()方法
3. ConditionColumnElement加入QueryPolicy

# 0.5.4 2022-6-29
1. 修复多个属性重复映射同一个列没有抛出异常的问题（现在抛出异常）

# 0.5.3 2022-6-3
1. 修复SqlOrderByBasicBuilder多个相同条件时生成的sql没有为每一个排序项加入ASC(DESC)关键字的问题

# 0.5.2 2022-6-2
1. ClassMappingUtils加入getSelectByPkSql，并且在没有主键时抛出异常

# 0.5.1 2022-5-30
1. 修复Sqlite的batch insert
2. ClassMappingUtils加入getUpsertSqlAndParamPositions和getUpsertBatchSqlAndParamPositions

# 0.5.0 2022-5-25
1. Dialect加入upsert相关功能

# 0.4.9 2022-04-22
1. 修复@Embedded修饰多个只映射最后一个对象的问题
2. ClassMapping加入检测同一列映射多个的问题

# 0.4.8 2022-04-21
1. 修复SqlFile生成没有忽略空白导致sql语句链接有分号独占一行的问题

# 0.4.7 2022-04-06
1. 修复common-core不兼容升级

# 0.4.6 2022-03-21
1. 修复includeResource找不到时报错不明确的问题

# 0.4.5 2022-03-18
1. SqlFile读取文件加入占位符参数支持

# 0.4.4 2022-03-18
1. SqlFile从sql文件中引入外部文件的标志符变更为（--@include executor1.sql;）

# 0.4.3 2022-03-18
1. ObjectToJsonMapper实现对象属性为Array List Map时的Json转换

# 0.4.2 2022-03-15
1. SqlFile支持从sql文件中引入外部文件，修复include读取jar包文件出错的问题

    
# 0.4.1 2022-03-02
1. 加入SqlFile支持从sql文件中引入外部文件（使用//include xxx.sql）进行引入

    ```sql
    SET FOREIGN_KEY_CHECKS=0;

    //include executor1.sql;
    //include executor2.sql;
    
    SET FOREIGN_KEY_CHECKS=1;
    ```
2. SqlExecutor加入execute(SqlFile)方法

# 0.4.0 2021-12-29  
1. MappingMode枚举名称重构
2. ObjectDbMixedMappingFactory实现统一使用数据库的列名进行映射，即jpa映射元数据的列名与数据库的列名大小写不一致（equals=false,equalsIgnoreCase=true）也会使用数据库的列名进行映射
3. common-model相关的mapping类移动到common-db-mapping-model模块

# 0.3.23 2021-12-19  
1. 加入PlatformListJavaSqlTypeMapper,PlatformArrayJavaSqlTypeMapper,NumberListJavaSqlTypeMapper,NumberArrayJavaSqlTypeMapper,PlatformJavaSqlTypeStringMapper
2. ParamedColumnElement加入Predicate<Object> ignorePolicy（使用了ParamedColumnElement的相关类都加入Predicate<Object> ignorePolicy）

# 0.3.22 2021-12-05  
1. JavaSqlTypeMapper去掉getJavaType(SQLType),getSqlType(GenericType<E>)方法
2. DefaultSqlTypeMapping加入JDBCType.SQLXML, JDBCType.ROWID支持
3. 加入ObjectToJsonTypeRegister,PlatformsTypeRegister,PlatformTypeRegister,ValueTypeRegister

# 0.3.21 2021-12-01  
1. PlatformJavaSqlTypeMapper.support(GenericType<Platform> type)实现为所有实现了Platform接口的类型都返回true
2. 加入AbstractValueJavaSqlTypeMapper
3. 兼容性升级Platform App等从common-api依赖改为common-model依赖

# 0.3.20 2021-09-03  
1. 自定义数据映射加入数据库返回数据到java对象映射时，加入其相关元数据（列名，表名）进行更具体的映射

# 0.3.19 2021-09-02  
1. JdbcUtils.setParameter支持BigInteger,AtomicInteger,AtomicLong,AtomicBoolean
   
# 0.3.18 2021-08-26  
1. ConditionColumnElement.toSql()支持QueryOperator.ISN，QueryOperator.INN传入Boolean参数
    null 表示忽略当前条件
    true 表示当前操作类型条件
    false 表示当前操作类型相反的条件（ISN变INN，INN变ISN）

# 0.3.17 2021-08-16  
1. SqlUtils.convertNamedParamSql加入in的参数为Collection或Array根据长度占位符自动转换为(?,?...)的形式

# 0.3.16 2021-07-26  
1. ConditionColumnElement加入对QueryOperator.LK的支持

# 0.3.15 2021-5-31  
1. DataTransformer.transform(currentRecordModel, originalRecordModel)方法加入参数原始数据，即没有被其他转换器处理过的原始数据
2. Dialect加入StringConverter tableAndColumnNameConverter()替换boolean isTableAndColumnNameConverterUpperCase()方法

# 0.3.14 2021-4-14  
1. 修复ClassMappingUtils.getMergeSqlAndParamPositions生成sql出错的问题

# 0.3.13 2021-3-24  
1. JdbcUtils.getResultSetMap(ResultSet rs, SqlTypeMappingManager manager)修复设置了label的名称不处理，而没设置label的字段使用驼峰命名转换

# 0.3.12 2021-3-12  
1. ClassMappingUtils.getMergeSqlAndParamPositions返回Tuple3,新加入需要更新的值的数量

# 0.3.11 2021-3-8  
1. AbstractJavaSqlTypeMapper加入getJavaType(SQLType sqlType)的默认实现
2. ObjectToDbMappingFactory createIndex加入@Table的uniqueConstraints支持
3. JdbcUtils.getResultSetValue(ResultSet rs, int index, Class<?> requiredType)加入LocalDate,LocalTime,LocalDateTime支持


# 0.3.10 2021-2-22  
1. JdbcUtils加入getResultSetMaps(ResultSet, SqlTypeMappingManager)和getResultSetMap(ResultSet, SqlTypeMappingManager)方法
2. 修复SqlUtils.convertNamedParamSql参数后跟,)参数名不正确的问题
3. SqlTypeMappingManager加入getSqlTypeMappingManager()方法

# 0.3.9 2021-2-20  
1. ObjectToJsonMapper加入storeAsString属性，写入string(varchar nvachar clob等等)或者byte array(blob)
2. 修复ObjectToJsonMapper返回值为null时报空指针错误的问题

# 0.3.8 2020-12-13  
1. 修复主键是关联对象时生成建表sql出错的问题
2. 修复废弃API调用

# 0.3.7 2020-11-27  
1. 加入ReadonlyTable类

# 0.3.6 2020-11-24  
1. 修复getDeleteSqlAndParamPositions单列主键时没有返回参数映射map的问题

# 0.3.5 2020-11-17  
1. ClassMappingUtils加入getDeleteSqlAndParamPositions(int batchSize, ClassMapping<?> classMapping, Dialect dialect)方法

# 0.3.4 2020-11-16  
1. 加入JdbcMappingFactory接口，并把原来的JdbcMappingFactory类改为JdbcMappingFactoryImpl

# 0.3.3 2020-11-16  
1. 加入VersionManager

# 0.3.2 2020-11-16  
1. Migrator加入updateSql(DataSource previousDataSource, DataSource currentDataSource)等一些列方法，用于比较两个数据库生成更新sql
2. 修正0.3.1使用了jdk15构建的问题,重新使用jdk8构建

# 0.3.1 2020-11-11  
1. Dialect实现索引的创建·更新·删除（Mysql,Postgresql）
2. 实现从Entity生成的ClassMapping支持索引，并且生成的建表、更新表的语句已经加上索引信息
3. Migrator的updateSql方法支持索引

# 0.3.0 2020-7-21  
1. 实现Migrator的initSql,updateSql方法

# 0.2.5 2020-7-19  
1. 修复JdbcMappingFactory中@Transient不是使用javax.persistence.Transient的问题
2. JdbcMappingFactory加入严格的对象属性到数据库映射，类似JPA,与默认的混合映射不同，具体查看JdbcMappingFactory.MappingMode枚举项的注释
3. 加入ObjectDbMixedMappingFacotry和ObjectToDbMappingFacotry,JdbcMappingFactory只是作为这两个具体实现的代理
4. 加入Migrator，实现通过映射创建数据库表的一些列方法

# 0.2.4 2020-7-15  
1. 修复0.2.3版本SqlTypeMappingManager的setEnumWithOrdinal,isEnumWithOrdinal方法删除问题

# 0.2.3 2020-7-15  
1. SqlTypeMappingManager相关的泛型extends Seriable改为extends Object

# 0.2.2 2020-7-15  
1. SqlTypeMappingManager加入setEnumWithOrdinal,isEnumWithOrdinal方法
2. JdbcMappingFactory构造参数加入SqlTypeMappingManager

# 0.2.1 2020-6-24  
1. 修复ClassMappingUtils.getInsertBatchSqlAndParamPositions返回的sql没字段使用对象属性的问题

# 0.2.0 2020-6-3  
1. 完善SqlTypeMappingManager相关功能
2. 删除SqlType枚举,使用jdbc的JDBCType代替
3. JdbcUtils.setParamaeter加入java.util.Optional支持,加入getColumnIndex,getResultSQLType等方法

# 0.1.4 2020-5-29  
1. 修改LangUtils为Lang,StringUtils为Strings
2. SqlUtils.transferNamedParamSql改为convertNamedParamSql

# 0.1.3 2020-5-23  
1. SqlUtils.convertSelectToCount加入多行sql支持

# 0.1.2 2020-4-20  
1. 加入代码生成需要的

# 0.1.1 2020-4-20  
1. SqlUtils加入transferNamedParamSql方法，用于转换命名参数sql为传统sql
2. 加入SqlTypeMappingManager，用于管理SqlType和JavaType的对应关系
3. ClassMappingUtils加入各种sql生成方法
4. Dialect加入批量插入sql生成方法

# 0.1.0 2020-4-19  
1. 从featherfly-db迁移过来
2. dml builder重构，放弃featherfly-db的cn.featherfly.common.db.builder包的实现
3. 依赖common-model-repository
4. Dialect加入构建各种ddl的方法