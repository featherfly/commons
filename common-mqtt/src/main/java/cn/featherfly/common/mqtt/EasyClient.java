
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * EasyClient.
 *
 * @author zhongj
 */
public class EasyClient extends ReconnectableClient<EasyClient> {

    /**
     * Instantiates a new Client.
     */
    EasyClient() {
    }

    /**
     * Connect client.
     *
     * @return the client
     * @throws MqttException the mqtt exception
     */
    @Override
    public EasyClient connect() throws MqttException {
        return connect(new AutoDetectionMqttCallBack(this));
    }

    //    /**
    //     * Connect client.
    //     *
    //     * @return the client
    //     * @throws MqttException the mqtt exception
    //     */
    //    private EasyClient _connect(AutoDetectionMqttCallBack callback) throws MqttException {
    //        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
    //        if (!connected) {
    //            if (address == null) {
    //                address = protocol + "://" + host + ":" + port;
    //            }
    //            client = new MqttClient(address, clientId, persistence);
    //            this.callback = callback;
    //            client.setCallback(callback);
    //            try {
    //                client.connect(options);
    //                connected = true;
    //            } catch (Exception e) {
    //                // 开启线程自动重新连接
    //                connected = false;
    //                new Thread(() -> {
    //                    autoReconnect(1000);
    //                }).run();
    //            }
    //        }
    //        return this;
    //    }

    /**
     * Subscribe.
     *
     * @param topicFilter the topic filter
     * @param qos         the qos
     * @param consumer    the consumer
     * @return the easy client
     * @throws MqttException the mqtt exception
     */
    public EasyClient subscribe(String topicFilter, Qos qos, Consumer<MqttMessage> consumer) throws MqttException {
        client.subscribe(topicFilter, qos.ordinal(), (topic, message) -> {
            consumer.accept(message);
        });
        return this;
    }

    /**
     * Subscribe client.
     *
     * @param topicFilter the topic filter
     * @param qos         the qos
     * @param consumer    the consumer
     * @return the client
     * @throws MqttException the mqtt exception
     */
    public EasyClient subscribe(String topicFilter, Qos qos, BiConsumer<String, MqttMessage> consumer)
            throws MqttException {
        client.subscribe(topicFilter, qos.ordinal(), (topic, message) -> {
            consumer.accept(topicFilter, message);
        });
        return this;
    }

    /**
     * Publish client.
     *
     * @param topicFilter the topic
     * @param msg         the msg
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    public EasyClient publish(String topicFilter, String msg) throws MqttPersistenceException, MqttException {
        return publish(topicFilter, msg, Qos.ONLY_ONCE);
    }

    /**
     * Publish client.
     *
     * @param topic    the topic
     * @param msg      the msg
     * @param consumer the consumer
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    public EasyClient publish(String topic, String msg, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException {
        return publish(topic, msg, Qos.ONLY_ONCE, consumer);
    }

    /**
     * Publish client.
     *
     * @param topic the topic
     * @param msg   the msg
     * @param qos   the qos
     * @return the client
     * @throws MqttException the mqtt exception
     */
    public EasyClient publish(String topic, String msg, Qos qos) throws MqttException {
        return publish(topic, msg, qos, null);
    }

    /**
     * Publish client.
     *
     * @param topic    the topic
     * @param msg      the msg
     * @param qos      the qos
     * @param consumer the consumer
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    public EasyClient publish(String topic, String msg, Qos qos, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException {
        return publish(topic, msg, qos, charset, consumer);
    }

    /**
     * Publish client.
     *
     * @param topic    the topic
     * @param msg      the msg
     * @param qos      the qos
     * @param charset  the charset
     * @param consumer the consumer
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    public EasyClient publish(String topic, String msg, Qos qos, Charset charset, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException {
        if (consumer != null) {
            ((AutoDetectionMqttCallBack) callback).publish(topic, consumer);
        }
        MqttMessage message = new MqttMessage();
        message.setQos(qos.ordinal());
        message.setRetained(true);
        message.setPayload(msg.getBytes(charset));
        MqttTopic mqttTopic = client.getTopic(topic);
        publish(mqttTopic, message);
        return this;
    }
}