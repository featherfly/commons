# 0.1.6 2021-06-22
1. publish(String topic, String msg, Qos qos, Charset charset, boolean retained,Consumer<IMqttDeliveryToken> consumer)
2. MqttMessage.retain默认为false

# 0.1.5 2021-04-27
1. 加入日志

# 0.1.4 2021-04-27
1. 加入EasyMqttClient接口，删除SimpleClient,重命名EasyClient,ClientBuilder为EasyMqttClientImpl,EasyMqttClientBuilder
2. 修复AutoDetectionMqttCallBack中deliveryComplete(IMqttDeliveryToken token)方法中token.getTopics()返回null时的空指针异常

# 0.1.3 2021-04-25
1. ClientBuilder加入username,password选项

# 0.1.3 2021-04-25
1. ClientBuilder加入username,password选项

# 0.1.2 2021-03-03
1. 修复subscribe()

# 0.1.1 2020-12-17
1. 修复依赖关系

# 0.1.0 2020-12-04
1. 实现SimpleClient和EasyClient两个客户端
                    支持调用connect时，如果服务器没启动，自动不断重试（内部开启线程）             
                    支持断线自动重连
       TopicMatcher没有实现模式匹配，下版本实现
    