# 0.5.3 2025-03-20
1. 重命名TupleMappedBuilder到TupleMappedExecutorBuilder，PrefixedBeanMapper[1-6]到PrefixedBeanMappedExecutor[1-6]
2. 加入PrefixedBeanMappedExecutor[1-6]的实现类PrefixedBeanMappedExecutor[1-6]Impl
3. 加入ArrayParamedExecutionExecutor,ArrayParamedExecutionExecutorEx,ArrayParamTupleMapperBuilder
4. 加入MapParamedExecutionExecutor,MapParamedExecutionExecutorEx,MapParamsTupleMapperBuilder
5. 加入TupleRowMapperBuilder,PrefixedBeanMapper[1-6]

# 0.5.2 2025-02-13
1. AutoCloseableIterable替换为RowIterable

# 0.5.1 2024-11-06
1. 更改tuple接口包 

# 0.5.0 2024-06-02
1. 加入tuple mapper相关的接口
2. 加入query executor和paramed execution executor相关的接口
3. PropertyMapping加入Property property属性用于直接访问映射对象属性的数据
4. Params改为class,原来的枚举改为其内部类ParamType
5. 加入IdGenerator

# 0.4.0 2024-05-05
1. ClassMapping, PropertyMapping, MappingFactory加入泛型
2. PropertyMapping加入mode
3. 加入Field, Params
4. 加入Alias,AliasField
5. IgnorePolicy重命名为IgnoreStrategy
6. 加入ClassNameUnderscoreConversion替代,ClassNameUnderscoreConversion
7. 加入PropertyNameUnderscoreConversion替代PropertyNameUnderscoreConversion
8. AliasField&vert;Repository add getAliasOrName() method
9. 加入  Aliasable&vert;Distinct&vert;Distinctive&vert;FunctionField&vert;Nameable&vert;Naming&vert;QueryableField&vert;StringField&vert;FieldConsumer&vert;FieldFunction&vert;RepositoryConsumer&vert;RepositoryFunction
10. 加入 RepositoryAwareField
11. AliasManager support customer AliasGeneretor
12. 加入 FieldAware,RepositoryAwareFieldImpl


# 0.3.0 2022-11-09
1. 修复AliasManager.getAlias(int index)越界检查BUG
2. 加入Repository AliasRepository
3. 使用common-api的operator包，删除内部operator包

# 0.2.4 2022-08-11
1. 加入LikeQueryPolicy

# 0.2.3 2022-04-22
1. PropertyMapping.getRepositoryFieldName()方法当repositoryFieldName为空并且子映射不为空时，返回子映射的repositoryFieldName的合集（xx,yy,zz）

# 0.2.2 2022-04-06
1. 修复common-core不兼容升级

# 0.2.1 2021-12-19
1. 加入IgnorePolicy

# 0.2.0 2021-12-05
1. 加入ExecutionInterceptor，InterceptionExecution接口

# 0.1.11 2021-08-17
1. 加入Operator接口，QueryOperator,SortOperator,LogicOperator都实现该接口

# 0.1.10 2021-07-26
1. QueryOperator加入LK

# 0.1.9 2020-12-12
1. 加入PropertyMapping.getPropertyFullName方法，返回带全路径的属性名(user.id)

# 0.1.8 2020-11-24
1. ClassMapping.getPrivaryKeyPropertyMappings支持嵌套对象

# 0.1.7 2020-11-16
1. 使用jdk8构建,修复0.1.6使用jdk15构建的问题

# 0.1.6 2020-11-11
1. ClassMapping加入schema属性和getIndex相关的一系列方法

# 0.1.5 2020-7-20
1. ClassMapping加入getPropertyMappingLeafNodes方法
2. PropertyMapping的remark默认值改为空字符串
3. getPropertyMappingByPersitField(String persitField)修改为从getPropertyMappingLeafNodes()中查找

# 0.1.4 2020-7-19
1. 设置PropertyMapping的nullable属性默认值为true

# 0.1.3 2020-4-20
1. ClassMapping,PropertyMapping加入更多映射属性
2. ClassMapping的PropertyMappings使用index排序

# 0.1.2 2020-4-20
1. ClassMapping,PropertyMapping加入更多映射属性

# 0.1.1 2020-4-18
1. BuilderException使用codegen生成

# 0.1.0 2020-4-18
1. 执行以及查询模型对象
2. 各种操作模型对象
3. 类(Class)和存储(Repository)之间的关系映射模型
4. 存储使用的dml,ddl的构建模型