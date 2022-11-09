# 0.5.0 2022-11-09
1. 加入operator包

# 0.4.1 2022-07-15
1. 加入ClassDefiner

# 0.4.0 2022-03-28
1. 提炼出ExceptionCodeSupport
2. 移动common-core的ExceptionCode、LocalizedExceptionCode到api包

# 0.3.0 2021-11-39
1. 移除app包
2. 加入EnumConvertor

# 0.2.9 2021-11-10
1. Platforms加入更多平台

# 0.2.8 2021-08-17
1. 去掉不需要的依赖

# 0.2.7 2021-08-16
1. 修复Chars常量拼写错误

# 0.2.6 2021-06-25
1. Platforms加入valueOf(Integer),valueOf(int)方法


# 0.2.5 2020-12-22
1. 加入Version接口
2. 加入Platform接口和Platforms枚举

# 0.2.4 2020-12-12
1. 加入SerializableBiConsumer,SerializableBiFunction
2. 删除GetFunction
    
# 0.2.3 2020-11-18
    1.加入LoggerFunctionArgs, Logger接口继承自LoggerFunctionArgs

#0.2.2 2020-6-30
    1.加入ReturnStringFunction,ReturnLocalDateFunction,ReturnLocalDateTimeFunction,ReturnLocalTimeFunction
        ,ReturnNumberFunction, ReturnEnumFunction,EnumSupplier
    2.删除StringGetFunction,LocalDateGetFunction,LocalDateTimeGetFunction,LocalTimeGetFunction,NumberGetFunction

#0.2.1 2020-6-30

    1.加入GetFunction,StringGetFunction,LocalDateGetFunction,LocalDateTimeGetFunction,LocalTimeGetFunction,NumberGetFunction

#0.2.0 2020-6-29

    1.加入DateSupplier,LocalDateSupplier,LocalDateTimeSupplier,LocalTimeSupplier,NumberSupplier

#0.1.0 2020-5-29

    1.从core包迁移部分接口过来
    2.加入Logger,ArraySupplier,MapSupplier