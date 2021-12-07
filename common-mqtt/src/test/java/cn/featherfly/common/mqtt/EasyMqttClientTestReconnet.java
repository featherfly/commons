
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

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * EasyClientTest.
 *
 * @author zhongj
 */
public class EasyMqttClientTestReconnet {

    public static void main(String[] args) throws MqttException, InterruptedException {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setKeepAliveInterval(5);
        options.setConnectionTimeout(8);

        final EasyMqttClientImpl easyClient = (EasyMqttClientImpl) new EasyMqttClientBuilder().host("127.0.0.1")
                .clientId("client-test").charset(StandardCharsets.UTF_8).username("cdzkdc").password("123456")
                .reconnectInNewThread(false).options(options).build();

        //        System.out.println("after connect() client.isConnected()  -> " + easyClient.client.isConnected());

        //        new Thread(() -> {
        //            try {
        //                easyClient.connect(client -> {
        //                    try {
        //                        client.subscribe("device/1", Qos.ONLY_ONCE, (topic, message) -> {
        //                            System.out.println("--------------------------------------");
        //                            System.out.println();
        //                            System.out.println("topic -> " + topic);
        //                            System.out.println("message.id -> " + message.getId());
        //                            System.out.println("message.qos -> " + message.getQos());
        //                            System.out.println(" -> " + new String(message.getPayload(), StandardCharsets.UTF_8));
        //                            System.out.println();
        //                            System.out.println("--------------------------------------");
        //                        });
        //
        //                        client.subscribe("device/1", Qos.ONLY_ONCE, (topic, message) -> {
        //                            System.out.println("***************************************");
        //                            System.out.println("topic -> " + topic);
        //                            System.out.println("message.id -> " + message.getId());
        //                            System.out.println("message.qos -> " + message.getQos());
        //                            System.out.println(" -> " + new String(message.getPayload(), StandardCharsets.UTF_8));
        //                            System.out.println("***************************************");
        //                        });
        //                        System.out.println("subscribe1");
        //                    } catch (MqttException e) {
        //                        e.printStackTrace();
        //                    }
        //                });
        //            } catch (MqttException e) {
        //                e.printStackTrace();
        //            }
        //        }).start();
        Thread.sleep(1000 * 600);

        if (easyClient.disconnect()) {
            System.out.println("disconnect success");
        } else {
            System.out.println("disconnect error");
        }
    }
}
