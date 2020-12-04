
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;
import java.util.function.Supplier;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * SimpleClient.
 *
 * @author zhongj
 */
public class SimpleClient extends ReconnectableClient<SimpleClient> {

    /**
     * Instantiates a new Client.
     */
    SimpleClient() {
    }

    /**
     * Connect client.
     *
     * @return the client
     * @throws MqttException the mqtt exception
     */
    @Override
    public SimpleClient connect() throws MqttException {
        return connect(callback);
    }

    /**
     * Connect client.
     *
     * @param supplier the supplier
     * @return the client
     * @throws MqttException the mqtt exception
     */
    public SimpleClient connect(Supplier<MqttCallback> supplier) throws MqttException {
        if (supplier != null) {
            connect(supplier.get());
        }
        return this;
    }

    /**
     * Subscribe client.
     *
     * @param topicFilter the topic filter
     * @param qos         the qos
     * @return the client
     * @throws MqttException the mqtt exception
     */
    public SimpleClient subscribe(String topicFilter, Qos qos) throws MqttException {
        client.subscribe(topicFilter, qos.ordinal());
        return this;
    }

    /**
     * Publish client.
     *
     * @param topic the topic
     * @param msg   the msg
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    public SimpleClient publish(String topic, String msg) throws MqttPersistenceException, MqttException {
        return publish(topic, msg, Qos.ONLY_ONCE);
    }

    /**
     * Publish client.
     *
     * @param topic the topic
     * @param msg   the msg
     * @param qos   the qos
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    public SimpleClient publish(String topic, String msg, Qos qos) throws MqttPersistenceException, MqttException {
        return publish(topic, msg, qos, charset);
    }

    /**
     * Publish client.
     *
     * @param topic   the topic
     * @param msg     the msg
     * @param qos     the qos
     * @param charset the charset
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    public SimpleClient publish(String topic, String msg, Qos qos, Charset charset)
            throws MqttPersistenceException, MqttException {
        MqttMessage message = new MqttMessage();
        message.setQos(qos.ordinal());
        message.setRetained(true);
        message.setPayload(msg.getBytes(charset));
        MqttTopic mqttTopic = client.getTopic(topic);
        publish(mqttTopic, message);
        return this;
    }
}