
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

import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * EasyClientTest.
 *
 * @author zhongj
 */
public class EasyClientTest {

    public static void main(String[] args) throws MqttException {
        final EasyClient easyClient = new ClientBuilder().host("127.0.0.1").clientId("client-test")
                .charset(StandardCharsets.UTF_8).build();
        easyClient.connect();
        new Thread(() -> {
            try {

                easyClient.subscribe("/device/#", Qos.ONLY_ONCE, (topic, message) -> {
                    System.out.println("--------------------------------------");
                    System.out.println();
                    System.out.println("topic -> " + topic);
                    System.out.println("message.id -> " + message.getId());
                    System.out.println("message.qos -> " + message.getQos());
                    System.out.println(" -> " + new String(message.getPayload(), StandardCharsets.UTF_8));
                    System.out.println();
                    System.out.println("--------------------------------------");
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }).start();
        while (true) {

        }
    }
}
