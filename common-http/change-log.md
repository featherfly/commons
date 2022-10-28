# 0.6.0 2022-10-28
1. post方法支持文件上传，在map中加入UploadFile就能上传文件

# 0.5.4 2022-04-06
1. 修复common-core不兼容升级

# 0.5.3 2022-03-04
1. 修复getSerializer日志没有在正确的逻辑条件内输出的问题
2. HttpClient加入一些列返回InputStream的stream方法
3. HttpUtils.createFormBody使用addEncoded方法编码参数

# 0.5.2 2021-03-09
1. HttpClients的autoSubscribeOnIo属性读写从HttpRxjavaClient读写
1. 修复AbstractHttpClient的参数传递错误

# 0.5.1 2021-03-05
1. 加入HttpClients, 相当于上一个版本的HttpClient

# 0.5.0 2021-03-05
1. HttpClient拆分为HttpClient,HttpAsyncClient,HttpRxjavaClient
2. Http拆分为Http,HttpAsync,HttpRxjava

# 0.4.1 2020-12-23
1. 升级rxjava2到rxjava3

# 0.4.0 2020-12-22
1. 实现BrowerHttpClient
2. HttpClient加入autoSubscribeOnIo属性，并根据该属性确定是否自动调用subscribeOn(Schedulers.io()),默认为true

# 0.3.0 2020-12-21 
1. 实现HttpClient
2. 基于HttpClient实现Http
3. OkHttpRequest重命名为HttpClientRequest,重构内部实现

# 0.2.0 2020-12-20
1. HttpRequestHandler加入completion(Consumer<T> success, Consumer<HttpErrorResponse> error)方法
2. HttpReqeust移除带connectTimeout参数的send方法
3. OkHttpRequest加入根据response的content-type来选择Serializer的功能
4. HttpRequest加入rxjava支持
5. Http,HttpUtils实现一些列工具方法

# 0.1.0 2020-12-20
1. 实现FormBody，ObjectBody请求
2. 实现下载