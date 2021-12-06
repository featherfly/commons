
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class EasyMqttClientImpl extends ReconnectableClient<EasyMqttClientImpl> {

    /**
     * Instantiates a new Client.
     */
    EasyMqttClientImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient connect() throws MqttException {
        if (connected) {
            return this;
        } else {
            return connect(new AutoDetectionMqttCallBack(this));
        }
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
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient subscribe(String topicFilter, Qos qos, Consumer<MqttMessage> consumer) throws MqttException {
        logger.debug("subscribe topicFilter -> {}, qos -> {}", topicFilter, qos);

        Consumers consumers = getConsumers(topicFilter);
        if (consumers == null) {
            consumers = new Consumers();
            topicConsumers.put(topicFilter, consumers);

            client.subscribe(topicFilter, qos.ordinal(), (topic, message) -> {
                if (logger.isDebugEnabled()) {
                    logger.debug("receive topic -> {}, msgId -> {}, qos -> {}, payload -> {}", topic, message.getId(),
                            message.getQos(), new String(message.getPayload(), charset));
                }
                Consumers cs = getConsumers(topicFilter);
                if (cs != null) {
                    for (Consumer<MqttMessage> c : cs.consumers) {
                        c.accept(message);
                    }
                }
                //                consumer.accept(topic, message);
            });
        }

        consumers.consumers.add(consumer);

        //        client.subscribe(topicFilter, qos.ordinal(), (topic, message) -> {
        //            if (logger.isDebugEnabled()) {
        //                logger.debug("receive topic -> {}, msgId -> {}, qos -> {}, payload -> {}", topic, message.getId(),
        //                        message.getQos(), new String(message.getPayload(), charset));
        //            }
        //            consumer.accept(message);
        //        });
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient subscribe(String topicFilter, Qos qos, BiConsumer<String, MqttMessage> consumer)
            throws MqttException {
        logger.debug("subscribe topicFilter -> {}, qos -> {}", topicFilter, qos);

        Consumers consumers = getConsumers(topicFilter);
        if (consumers == null) {
            consumers = new Consumers();
            topicConsumers.put(topicFilter, consumers);

            client.subscribe(topicFilter, qos.ordinal(), (topic, message) -> {
                if (logger.isDebugEnabled()) {
                    logger.debug("receive topic -> {}, msgId -> {}, qos -> {}, payload -> {}", topic, message.getId(),
                            message.getQos(), new String(message.getPayload(), charset));
                }
                Consumers cs = getConsumers(topicFilter);
                if (cs != null) {
                    for (BiConsumer<String, MqttMessage> bi : cs.biConsumers) {
                        bi.accept(topicFilter, message);
                    }
                }
                //                consumer.accept(topic, message);
            });
        }

        consumers.biConsumers.add(consumer);

        //        client.subscribe(topicFilter, qos.ordinal(), (topic, message) -> {
        //            if (logger.isDebugEnabled()) {
        //                logger.debug("receive topic -> {}, msgId -> {}, qos -> {}, payload -> {}", topic, message.getId(),
        //                        message.getQos(), new String(message.getPayload(), charset));
        //            }
        //            consumer.accept(topic, message);
        //        });
        return this;
    }

    @Override
    public EasyMqttClient clearSubscribe(String topicFilter) {
        topicConsumers.remove(topicFilter);
        return this;
    }

    @Override
    public EasyMqttClient clearAllSubscribe() {
        topicConsumers.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient publish(String topicFilter, String msg) throws MqttPersistenceException, MqttException {
        return publish(topicFilter, msg, Qos.ONLY_ONCE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient publish(String topic, String msg, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException {
        return publish(topic, msg, Qos.ONLY_ONCE, consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient publish(String topic, String msg, Qos qos) throws MqttException {
        return publish(topic, msg, qos, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient publish(String topic, String msg, Qos qos, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException {
        return publish(topic, msg, qos, charset, consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient publish(String topic, String msg, Qos qos, boolean retained,
            Consumer<IMqttDeliveryToken> consumer) throws MqttPersistenceException, MqttException {
        return publish(topic, msg, qos, charset, retained, consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient publish(String topic, String msg, Qos qos, Charset charset,
            Consumer<IMqttDeliveryToken> consumer) throws MqttPersistenceException, MqttException {
        return publish(topic, msg, qos, charset, false, consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EasyMqttClient publish(String topic, String msg, Qos qos, Charset charset, boolean retained,
            Consumer<IMqttDeliveryToken> consumer) throws MqttPersistenceException, MqttException {
        if (consumer != null) {
            ((AutoDetectionMqttCallBack) callback).publish(topic, consumer);
        }
        logger.debug("publish topic -> {}, qos -> {}, msg -> {}", topic, qos.ordinal(), msg);
        MqttMessage message = new MqttMessage();
        message.setQos(qos.ordinal());
        message.setRetained(retained);
        message.setPayload(msg.getBytes(charset));
        MqttTopic mqttTopic = client.getTopic(topic);
        publish(mqttTopic, message);
        return this;
    }

    private class Consumers {

        private List<Consumer<MqttMessage>> consumers = new ArrayList<>(0);

        private List<BiConsumer<String, MqttMessage>> biConsumers = new ArrayList<>(0);

    }

    private Map<String, Consumers> topicConsumers = new HashMap<>();

    private Consumers getConsumers(String topic) {
        return topicConsumers.get(topic);
    }
}