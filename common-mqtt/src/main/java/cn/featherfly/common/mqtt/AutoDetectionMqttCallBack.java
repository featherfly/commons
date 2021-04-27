package cn.featherfly.common.mqtt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * The Class AutoDetectionMqttCallBack.
 */
public class AutoDetectionMqttCallBack implements AutoReconnectMqttCallback {

    private ReconnectableClient<?> client;

    //    private BiPredicate<String, String> topicMatcher;

    //    private Map<String, List<BiConsumer<String, MqttMessage>>> subscribeConsumers = new HashMap<>();

    private Map<String, List<Consumer<IMqttDeliveryToken>>> publishConsumers = new HashMap<>();

    /**
     * Instantiates a new auto detection mqtt call back.
     *
     * @param client the client
     */
    public AutoDetectionMqttCallBack(ReconnectableClient<?> client) {
        this.client = client;
    }

    //    /**
    //     * Instantiates a new auto detection mqtt call back.
    //     *
    //     * @param client       the client
    //     * @param topicMatcher the topic matcher
    //     */
    //    public AutoDetectionMqttCallBack(EasyClient client, BiPredicate<String, String> topicMatcher) {
    //        this.client = client;
    //        this.topicMatcher = topicMatcher;
    //    }

    //    /**
    //     * Subscribe.
    //     *
    //     * @param topic    the topic
    //     * @param consumer the consumer
    //     */
    //    public void subscribe(String topic, BiConsumer<String, MqttMessage> consumer) {
    //        List<BiConsumer<String, MqttMessage>> list = subscribeConsumers.get(topic);
    //        if (list == null) {
    //            list = new ArrayList<>();
    //            subscribeConsumers.put(topic, list);
    //        }
    //        list.add(consumer);
    //    }

    /**
     * Publish.
     *
     * @param topic    the topic
     * @param consumer the consumer
     */
    public void publish(String topic, Consumer<IMqttDeliveryToken> consumer) {
        List<Consumer<IMqttDeliveryToken>> list = publishConsumers.get(topic);
        if (list == null) {
            list = new ArrayList<>();
            publishConsumers.put(topic, list);
        }
        list.add(consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReconnectableClient<?> getClient() {
        return client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //        for (Entry<String, List<BiConsumer<String, MqttMessage>>> entry : subscribeConsumers.entrySet()) {
        //            if (matchTopic(topic, entry.getKey())) {
        //                List<BiConsumer<String, MqttMessage>> list = entry.getValue();
        //                if (list != null) {
        //                    for (BiConsumer<String, MqttMessage> consumer : list) {
        //                        consumer.accept(topic, message);
        //                    }
        //                }
        //            }
        //        }

        //        List<BiConsumer<String, MqttMessage>> list = subscribeConsumers.get(topic);
        //        if (list != null) {
        //            for (BiConsumer<String, MqttMessage> consumer : list) {
        //                consumer.accept(topic, message);
        //            }
        //        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        if (token.getTopics() == null) {
            return;
        }
        for (String topic : token.getTopics()) {
            List<Consumer<IMqttDeliveryToken>> list = publishConsumers.get(topic);
            if (list != null) {
                for (Consumer<IMqttDeliveryToken> consumer : list) {
                    consumer.accept(token);
                }
            }
        }
    }

    //    private boolean matchTopic(String topic, String topicFilter) {
    //        return topicMatcher.test(topic, topicFilter);
    //    }
}
