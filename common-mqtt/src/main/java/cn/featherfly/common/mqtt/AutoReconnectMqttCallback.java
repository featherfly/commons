package cn.featherfly.common.mqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * The Interface AutoReconnectMqttCallback.
 */
public interface AutoReconnectMqttCallback extends MqttCallback {

    /**
     * Gets the client.
     *
     * @return the client
     */
    ReconnectableClient<?> getClient();

    /**
     * {@inheritDoc}
     */
    @Override
    default void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        ReconnectableClient<?> client = getClient();
        try {
            client.reconnect(); // reconnect() 有自动重连
        } catch (MqttException e) {
        }
    }
}
