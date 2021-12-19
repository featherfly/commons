# 0.2.1 2021-12-19
    1. 加入IgnorePolicy
    
# 0.2.0 2021-12-05
    1. 加入ExecutionInterceptor，InterceptionExecution接口
    
# 0.1.11 2021-08-17
    1. 加入Operator接口，QueryOperator,SortOperator,LogicOperator都实现该接口
    
# 0.1.10 2021-07-26
    1. QueryOperator加入LK
    
# 0.1.9 2020-12-12
    1.加入PropertyMapping.getPropertyFullName方法，返回带全路径的属性名(user.id)
    
# 0.1.8 2020-11-24
    1.ClassMapping.getPrivaryKeyPropertyMappings支持嵌套对象
    
# 0.1.7 2020-11-16
    1.使用jdk8构建,修复0.1.6使用jdk15构建的问题
    
# 0.1.6 2020-11-11
    1.ClassMapping加入schema属性和getIndex相关的一系列方法
    
# 0.1.5 2020-7-20
    1.ClassMapping加入getPropertyMappingLeafNodes方法
    2.PropertyMapping的remark默认值改为空字符串
    3.getPropertyMappingByPersitField(String persitField)修改为从getPropertyMappingLeafNodes()中查找
    
# 0.1.4 2020-7-19
    1.设置PropertyMapping的nullable属性默认值为true
    
# 0.1.3 2020-4-20
    1.ClassMapping,PropertyMapping加入更多映射属性
    2.ClassMapping的PropertyMappings使用index排序
    
# 0.1.2 2020-4-20
    1.ClassMapping,PropertyMapping加入更多映射属性
    
# 0.1.1 2020-4-18
    1.BuilderException使用codegen生成
    
# 0.1.0 2020-4-18
    1.执行以及查询模型对象
    2.各种操作模型对象
    3.类(Class)和存储(Repository)之间的关系映射模型
    4.存储使用的dml,ddl的构建模型