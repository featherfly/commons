# 0.2.1 2021-3-8
1. Serialization加载ProtostuffSerializer, JacksonXmlSerializer使用反射加载

# 0.2.0 2020-12-22
1. 加入JacksonXmlSerializer

# 0.1.1 2020-12-21
1. Serialization MimeType常量名称修正

# 0.1.0 2020-12-20
1. 完成json,kryo,protobuf的序列化与反序列化,默认使用json,自动级联jackson的包，如果要使用kryo,protobuff，需要手动加入依赖