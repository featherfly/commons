# 0.2.0 2020-12-20
1. HttpRequestHandler加入completion(Consumer<T> success, Consumer<HttpErrorResponse> error)方法
2. HttpReqeust移除带connectTimeout参数的send方法
3. OkHttpRequest加入根据response的content-type来选择Serializer的功能
4. HttpRequest加入rxjava支持

# 0.1.0 2020-12-20
1. 实现FormBody，ObjectBody请求
2. 实现下载