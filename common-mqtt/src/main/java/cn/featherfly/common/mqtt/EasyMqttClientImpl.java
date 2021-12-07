
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        if (isConnected()) {
            return this;
        } else {
            return connect(new AutoDetectionMqttCallBack(this), null);
        }
    }

    //    @Override
    //    public void connect(Consumer<EasyMqttClient> consumer) throws MqttException {
    //        if (isConnected()) {
    //            consumer.accept(this);
    //        } else {
    //            connect(new AutoDetectionMqttCallBack(this), consumer);
    //        }
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
    public EasyMqttClient unsubscribe(String topicFilter) throws MqttException {
        topicConsumers.remove(topicFilter);
        client.unsubscribe(topicFilter);
        return this;
    }

    @Override
    public EasyMqttClient unsubscribeAll() throws MqttException {
        for (String topic : topicConsumers.keySet()) {
            client.unsubscribe(topic);
        }
        topicConsumers.clear();
        return this;
    }

    private void resubscribeAll() throws MqttException {
        for (Entry<String, Consumers> entry : topicConsumers.entrySet()) {
            String topic = entry.getKey();

            List<Consumer<MqttMessage>> consumers = new ArrayList<>(entry.getValue().consumers);
            List<BiConsumer<String, MqttMessage>> biConsumers = new ArrayList<>(entry.getValue().biConsumers);

            unsubscribe(topic);

            for (Consumer<MqttMessage> consumer : consumers) {
                subscribe(topic, Qos.ONLY_ONCE, consumer);
            }
            for (BiConsumer<String, MqttMessage> consumer : biConsumers) {
                subscribe(topic, Qos.ONLY_ONCE, consumer);
            }
        }
    }

    @Override
    protected void connected() throws MqttException {
        if (connectedConsumer == null) {
            resubscribeAll();
        } else {
            unsubscribeAll();
        }
        super.connected();
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