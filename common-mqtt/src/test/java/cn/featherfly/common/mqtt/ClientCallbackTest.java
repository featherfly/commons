
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

import cn.featherfly.common.lang.Randoms;

/**
 * EasyClientTest.
 *
 * @author zhongj
 */
public class ClientCallbackTest {

    public static void main(String[] args) throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setKeepAliveInterval(5);
        options.setConnectionTimeout(8);

        final EasyMqttClientImpl easyClient = (EasyMqttClientImpl) new EasyMqttClientBuilder().host("47.108.132.160")
                .clientId("client-test").charset(StandardCharsets.UTF_8)
                //                .username("cdzkdc").password("123456")
                .reconnectInNewThread(false).options(options).build();
        easyClient.connect();

        easyClient.subscribe("ml302/pub", Qos.ONLY_ONCE, t -> {
            System.out.println("subscribe ml302/pub");
            System.out.println("msg id = " + t.getId());
            System.out.println("msg qos = " + t.getQos());
            System.out.println("msg payload = " + new String(t.getPayload()));
            System.out.println("msg retained = " + t.isRetained());
            System.out.println("msg duplicate = " + t.isDuplicate());
        });

        easyClient.publish("ml302/pub", "test:" + Randoms.getString(4), Qos.ONLY_ONCE, c -> {
            System.out.println("complete" + c.getMessageId());
        });
        easyClient.publish("ml302/pub", "test:" + Randoms.getString(4), Qos.ONLY_ONCE);
        easyClient.publish("ml302/pub", "test:" + Randoms.getString(4), Qos.ONLY_ONCE);
        easyClient.publish("ml302/pub", "test:" + Randoms.getString(4), Qos.ONLY_ONCE);

        //        String topic = "vending/device/sub/sn-test";
        //        String msg = new StringFormatter('<', '>').format("{\"ono\": \"<orderNo>\", \"act\",1}",
        //                new HashChainMap<String, Object>().putChain("orderNo", "orderNo_" + Randoms.getInt(10000)));
        //        mqttClinet.publish(topic, msg, Qos.ONLY_ONCE);

        System.out.println("waiting ...");

        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}
