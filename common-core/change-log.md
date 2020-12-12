# 1.8.18 2020-12-12
    1.BeanDescriptor.getBeanProperty加入内嵌属性支持(user.name)
    2.修复ArrayUtils.toString参数数组中有null时报错的问题
     
# 1.8.17 2020-12-12
    1.Strings.format(String, Object[])修改为(String str, Object... args)
    2.修正LambdaUtils.getSerializableSupplierLambdaInfo在linux出错的问题
    3.ILocalizedAssert数字大小判断改为泛型N extends Number,同时加入NumberSupplier<N> value参数的方法

# 1.8.16 2020-11-24
    1.ClassUtils的方法getFieldType,getMethodReturnType,getMethodParameterType如果为泛型支持获取已经在父类中具现化的类型
    2.ClassUtils的方法getFieldGenericParameterType,getMethodReturnTypeGenericParameterType,getMethodGenericParameterType支持获取泛型类型已经在父类中具现化的类型
    
# 1.8.15 2020-11-19
    1.LocalizedException.getMessage修改为返回本地化后的字符串

# 1.8.14 2020-11-16
    1.加入Lang.wrapThrow(java.io.IOException)方法
    2.删除Dates.parse(String strDate)方法
    3.加入VersionRenamePolicy
    
# 1.8.13 2020-11-16
    1.Properties加入PropertiesPlus(java.util.Properties defaults)构造器
    2.修正1.8.12使用了jdk15构建的问题,重新使用jdk8构建

# 1.8.12 2020-11-10

    1.ArrayUtils加入fillAll相关的方法
    2.NumberUtils加入128进制的转换，64进制转换中@替换为~
    
# 1.8.11 2020-7-19

    1.修正Lang.wrapThrow(Throwable, Class<E>)
    2.ArrayUtils,Lang的加入toMapStringKey，toArray2标注为废弃
    3.Lang加入array方法
    
# 1.8.10 2020-7-4

    1.修正ClassUtils.isGetter(Method)方法的问题
    
# 1.8.9 2020-6-30

    1.LambdaUtils加入getSerializableSupplierLambdaInfo(SerializableSupplier<T> lambda)方法
    
    
# 1.8.8 2020-6-19

    1.加入Randoms代替RandomUtils,后续大版本会删除RandomUtils
    2.Lang,CollectionUtils加入each方法
    
# 1.8.7 2020-6-3

    1.Lang.isEmpty,isNotEmpty等一系列判断方法加入Option支持
    2.加入Dates替代DateUtils,后续大版本会删除DateUtils
    3.Dates加入getTime(LocalDateTime),getTime(LocalDateTime,ZoneId)
    4.Dates中部分使用Calendar实现的功能使用LocalDate,LocalTime,LocalDateTime重构
    5.ArrayUtils加入#contain(String[], String, boolean), toNumbers(Class<A>, String...), each(T[], BiConsumer<T, Integer>)
        , toString(A[], char)和一些列空数组定义
    6.GenericClass,BeanProperty加入hashcode(),equals实现

# 1.8.6 2020-5-29

    1.加入Lang替代LangUtils，后续大版本会删除LangUtils
    2.加入Strings替代StringUtils，后续大版本会删除StringUtils
    3.加入StringFormatter,Strings.format方法使用StringFormatter实现
    4.部分接口移动到common-api中

# 1.8.5 2020-5-29

    1. 加入ThreadLocalMap
    2. 加入SimplagePage,Page加入getSize,getNumber方法，原来的getPageSize,getPageNumber标注为废弃，后续版本会删除

# 1.8.4 2020-5-19

    1. NumberUtils加入62 64进制字符串和数字互相转换的方法
    2. ArrayUtils加入fill(T[] target, T[] source)方法
    
# 1.8.3 2020-4-20

    1. ClassUtils加入setFieldValue
    
# 1.8.2 2020-4-20

    1. 升级common-algorithm依赖
    2. 加入LocaleMessage
    
    
# 1.8.1 2020-4-15

    1. AssertIllegalArgument加入IllegalArgumentAssert新增加方法
    2. ExceptionCodeException加入更多包含Throwable的构造方法
    3. ClassUtils加入packageToFile(Class<?> type, boolean containExt) {

# 1.8.0 2020-4-11

    1.ResouceBundleJdkProxy实现传入字符集自动转码功能，如果没有指定则不操作
    2.LocalizedException加入charset属性，用于设置国际化输出是否需要转码
    3.删除AlgorithmUtils，引入common-algorithm包
    4.LangUtils加入isEmpty(o, Consumer), isNotEmpty(o, Consumer)方法
    5.加入Properties,PropertiesImpl,PropertiesPlus
    6.StringUtils加入format(String content, Map<String, String> params)方法
    
# 1.7.16 2020-3-17

    1.加入AlgorithmUtils类，实现了crc32方法
    2.FileUtils加入crc32(file)方法
    3.ClassUtils加入Annotation getAnnotation(Annotation a, Class<Annotation> type)方法，getAnnotaions三个对应getAnnotation的方法
    4.ServiceLoaderUtils的NotFoundPolicy枚举类中的枚举项都改为大写

# 1.7.15 2019-12-03

    1.修复LambdaUtils的bug
    
# 1.7.14 2019-12-03

    1.修改group名称
    
# 1.7.13 2019-11-28

    1.加入SerializableSupplier
    2.ILocalizedAssert中的isNotNull,isNotEmpty,isNotBlank加入传入SerializableSupplier参数的重载方法
    
# 1.7.12 2019-9-22

    1.LambdaUtils加入getLambdaPropertyName,getLambdaMethodName重载方法
    
# 1.7.11 2019-9-20

    1.LambdaUtils加入getLambdaInfo,getLambdaMethod,getSerializedLambda
    2.ClassUtils加入两个重载的invokeMethod

# 1.7.10 2019-9-17

    1.加入location相关的类
    2.加入LambdaUtils,SerializableFunction

# 1.7.9 2019-9-9

    1.加入Watcher,WatchListener
    2.FileUtils加入watch一些列方法
    3.加入Logger,LoggerFactory实现对slf4j logger的扩展
    4.LangUtils.toEnum(Class<T> toClass, Object object)加入short支持

# 1.7.8 2019-8-27

    1.修复FileUtils.listAll的问题

# 1.7.7 2019-8-26

    1.ClassUtils加入getFiled,getFiledValue,invokeMethod等方法
    2.将StringUtils中的toChineseMoneyNumber toChineseNumber方法移动到WordUtils中
    3.修正StringUtils.substringAfter方法，加入substringLast方法
    4.加入LoggerWrapper,LogUtils加入对应方法
    5.Chars的常量加入char类型

# 1.7.6 2019-8-20

    1.移除spring依赖，移除类ClassPathScanningProvider到common-spring

# 1.7.5 2019-8-16

    1.FileUtils，StringUtils继承自common的同名文件，把以前的包装方法去除了
    2,加入LocaleNumber，Childnes, StringUtils加入toChineseMoneyNumber toChineseNumber方法
    

# 1.7.4 2019-8-15

    1.BeanUtils加入toString方法
    2.ClassUtils加入getRawType(Type type)方法
    3.修复newInstance(Class<T> clazz, Object... args)在定义参数是接口时，找不到构造器的问题
    4.修复调用AssertIllegalArgument.isNotNull历史遗留的描述错误问题
    5.ILocalizedAssert加入更多断言
    6.加入SimplePaginationResults,SimplePagination不再实现PaginationResults

# 1.7.3 2019-8-7
    1.优化UUIDGenerator生成UUID效率
    2.加入InitException

# 1.7.2 2019-07-31

    1.删除MifTreeNode支持
    2.NumberUtils 加入short与byte[]以及short与byte[]互相转换的方法
    3.FileUtils加入 getPathInJar isResourceInJar方法
    4.SimplePagination加入 SimplePagination(Limit limit),SimplePagination(int offset, int limit)构造函数
    
    
# 1.7.1 2019-7-19

    1.加入limit
    2.LangUtils.toEnum(Class<T> toClass, Object object)加入byte支持
    3.SystemPropertyUtils加入isWindows, isLinux方法
    
# 1.7.0 2019-6-24

    1.加入LocalizedCodeException,LocalizedExceptionCode,SimpleExceptionCode,SimpleLocalizedExceptionCode
        ,LoadedMessageLocalizedExceptionCode,LocalizedExceptionUtils等一些列方法
    2.加入cn.featherfly.common.lang.asserts包下一系列类
    3.ResourceBundleUtils加入getString(Class<?> type, String key, Object[] argus,Locale locale)等一系列方法
    4.删除StandardResourceBundleException,StandardAppException,StandardConfigException,StandardSysException,
        AssertStandardApp,AssertStandardConfig,AssertStandardSys
    5.使用新的常规方法为LocalizedException实现了异常消息国际化实现取代以前的hack式实现方式

# 1.6.3 2018-01-11

    1.加入LimitQueue
    
# 1.6.2 2017-09-06

    1.LocalizedException获取ResourceBundle首先从异常同目录查找同名资源文件，找不到再从根目录超找同名文件
    
# 1.6.1 2017-4-26

    1.RandomUtils加入getRandomFromTotal(double total, int num)

# 1.6.0 2017-4-10

    1.使用jdk8编译
    2.加入ExceptionCode,定义的几个标准异常加入ExceptionCode支持
    3.加入Executor、Executable、AutoAsyncExecutor
    4.加入VerifyCodeUtils
    5.删除logger包
    6.删除TypeValue相关功能类和包
    7.StringUtils加入unicodeToString、stringToUnicode
    8.WhiteBlackListPolicy加入黑名单和白名单启用参数和逻辑
    9.BeanDescriptor、BeanUtils的setProperty、getProperty使用obj.pro时，会动态根据当前获取的对象，再获取类型进行下一步设值（取值）
    10.NumberUtils加入conver方法用于数字对象之间的转换

# 1.5.2 2016-08-01

    1.加入TypeValue接口

# 1.5.1 2016-05-06

    1.PaginationResults加入getResultSize方法

# 1.5.0 2016-04-25

    1.加入typevalue支持

# 1.4.3 2015-07-30

    1.BeanDescription修复把Object的getClass当做class属性了，直接忽略

# 1.4.2 2015-07-12

	1.修复mergeProperties(E target, E from, CopyRuleEnum copyRuleEnum)方法调用了copyProperties方法BUG
	
# 1.4.1 2015-07-08

	1.NumberUtils加入fillingAtStart方法

# 1.4.0 2015-06-30

	1.加入日志接口
	2.使用LocalizedException代替StandardResourceBundleException
	3.加入AssertLocalized
	
# 1.3.1 2015-06-28

	1.BeanDescriptor初始化方式变更，使用方法开始的扫描方式，这样可以支持动态属性，即只有set或get方法的属性
	
# 1.3.0 2015-06-24

	1.BeanDescriptor加入泛型支持
	2.ClassUtils加入返回父类泛型映射的方法getSuperClassGenricTypeMap(Class<?> clazz)
	
# 1.2.9 2015-06-19

	1.修复ResourceBundleUtils获取KEY的BUG
	
# 1.2.8 2015-06-19

	1.加入ResourceBundleUtils
	
# 1.2.7 2015-06-17

	1.BeanDescriptor.addProperty修正自动创建集合的实现，可以实现更多类型
	2.加入BeanUtils.mergeProperties方法，用于对象属性合并，不需要继承关系
	
	
# 1.2.6 2015-05-13

	1.加入DateUtils.getAge方法
	
# 1.2.6 2015-05-06

	1.取消Assert中的泛型

# 1.2.4 2015-04-24

	1.加入NumberUtils，把StringUtils.parse方法移动到NumberUtils
	2.加入GenricType接口，GenericClass实现类
	3.BeanProperty实现GenricType接口，加入泛型声明
	
# 1.2.3 2015-04-20

	1.LangUtils加入equals hashcode方法
	2.Assert类和其余几个Assert工具类方法都把传入参数进行返回了
	3.StringUtils加入parse方法，用于parse各种数字
	
# 1.2.2 2015-03-16

	1.去除Pagination接口的泛型参数
	
# 1.2.1 2014-07-23

	1.合并standard
	2.加入gradle构建
	3.部署到maven中心库
	

# 1.2.0 2014-06-16

	1.移植到cn.featherfly
	2.TreeNode.setParentNode加入从本来的父节点删除当前节点的功能
	3.加入ServiceLoaderUtils类
	4.使用Java SPI加载BeanProperty的字节码增强实现（基于javassist）
	
# 1.1.3 2013-07-11

	1.BeanDescriptor.addProperty修正为空时空指针异常问题
	2.加入Assert和AssertUtils
	3.ClassUtils加入多个重载版本的newInstance方法
	4.BeanUtils加入instantiateClass(Class<T> clazz, Object...args)方法

# 1.1.2 2013-03-21

	1.BeanUtils.copyProperties，可以复制父子对象属性


# 1.1.1 2012-9-27

	1. StringUtils加入filterHtml和toHtml两个html转换方法
	2. 修复加载小bug
	3. FileUtils所有检查异常包装为非检查异常
	4. FileUtils加入write一系列方法
	5. BeanDescriptor加入getBeanProperty(int index)方法
	7. LangUtils.isEmpty(Object object)，LangUtils.isNotEmpty(Object object)加入数组判断
	8. ArrayUtils 加入 isEmpty isNotEmpty方法

# 1.1.0 2012-8-29

	1 加入bcel支持

# 1.0.10 2012-8-21

	1 BeanUtils，BeanDescriptor加入addProperty方法

# 1.0.9 2012-3-9

	1 把codec包剔除
	2 加入BeanPropertyClassCondition
	3 加入字节码增强支持，使用beanDescriptor.setUseByteCode(true)开启

# 1.0.8 2011-10-12

	1 把db包剔除
	2 BeanUtils修改为泛型实现
	3 BeanUtils加入toMap,fromMap,fillProperties实现

# 1.0.7 2011-09-30

	1 LogUtils.logObject修改为debugObject
	2 把net包剔除
	3 LangUtils添加文件存在的判断，AssertIllegalArgument添加文件存在的断言
	4 BeanPropertyNameRegexCondition匹配从字符串直接匹配改为预编译Pattern匹配

# 1.0.6 2011-9-19

	1 重构strutcture的包名com.cdthgk.strutcture为com.cdthgk.common.strutcture

# 1.0.5 2011-9-15

	1 加入UUIDGenerator
	2 加入BeanPropertyFactory

# 1.0.4 2011-08-28

	1 FtpClient的makeDirectory方法添加嵌套目录生成支持
	2 FtpClient添加isDirectoryExist方法

# 1.0.3 2011-08-28

	1 修正FtpClient的makeDirectory方法没有设置工作目录的问题

# 1.0.2 2011-08-26

	1 FtpClient添加makeDirectory创建目录方法

# 1.0.1 2011-06-16

	1 BeanDescriptor增加getChildBeanProperty(String)方法
	2 BeanProperty私有方法checkType的判断从完全类型匹配改为类型匹配及子类匹配

# 1.0 2011-06-16


