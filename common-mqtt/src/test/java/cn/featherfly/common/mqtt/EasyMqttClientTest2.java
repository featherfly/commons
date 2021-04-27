
/*
 * All rights Reserved, Designed By zhongj
 * @Title: EasyClientTest.java
 * @Package cn.featherfly.common.mqtt
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-03-03 17:18:03
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.UUIDGenerator;

/**
 * EasyClientTest.
 *
 * @author zhongj
 */
public class EasyMqttClientTest2 {

    public static void main(String[] args) throws MqttException {
        String clientId = "test-" + UUIDGenerator.generateUUID22Letters();
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setWill("vending/device/will", Strings.format("{\"sn\":\"{0}\"}", clientId).getBytes(),
                Qos.ONLY_ONCE.ordinal(), false);
        EasyMqttClient mqttClinet = new EasyMqttClientBuilder().host("47.108.132.160").port(1883).clientId(clientId)
                .options(mqttConnectOptions).build();
        System.out.println("clientId = " + mqttClinet.getClientId());

        mqttClinet.connect().subscribe("vending/device/pub", Qos.ONLY_ONCE, (s, mqttMessage) -> {
            System.out.println(Strings.format("receive topic -> {0}, msgId -> {1}, payload -> {2}", s,
                    mqttMessage.getId(), new String(mqttMessage.getPayload())));
        }).subscribe("vending/device/sub/sn-test", Qos.ONLY_ONCE, (s, mqttMessage) -> {
            System.out.println(Strings.format("receive topic -> {0}, msgId -> {1}, payload -> {2}", s,
                    mqttMessage.getId(), new String(mqttMessage.getPayload())));
        }).subscribe("vending/device/will", Qos.ONLY_ONCE, (s, mqttMessage) -> {
            System.out.println(Strings.format("receive topic -> {0}, msgId -> {1}, payload -> {2}", s,
                    mqttMessage.getId(), new String(mqttMessage.getPayload())));
        });

        //        String topic = "vending/device/sub/sn-test";
        //        String msg = new StringFormatter('<', '>').format("{\"ono\": \"<orderNo>\", \"act\",1}",
        //                new HashChainMap<String, Object>().putChain("orderNo", "orderNo_" + Randoms.getInt(10000)));
        //        mqttClinet.publish(topic, msg, Qos.ONLY_ONCE);

        System.out.println("waiting ...");

        while (true) {

        }
    }
}
