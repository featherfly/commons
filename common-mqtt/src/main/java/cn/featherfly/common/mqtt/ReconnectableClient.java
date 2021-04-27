
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReconnectableClient.
 *
 * @author zhongj
 * @param <C> the generic ReconnectableClient type
 */
public abstract class ReconnectableClient<C extends ReconnectableClient<C>> implements EasyMqttClient {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The constant DEFAULT_PORT.
     */
    public static final int DEFAULT_PORT = 1883;

    /**
     * The constant DEFAULT_PROTOCOL.
     */
    public static final String DEFAULT_PROTOCOL = "tcp";

    /** 连接状态. */
    boolean connected;

    /**
     * The Protocol.
     */
    String protocol = DEFAULT_PROTOCOL;
    /**
     * The Host.
     */
    //tcp://127.0.0.1:1883
    //MQTT安装的服务器地址:MQTT定义的端口号
    // 域名，IP
    String host;
    /**
     * The Port.
     */
    // 端口
    int port = DEFAULT_PORT;

    /**
     * The Address.
     */
    String address;

    /**
     * The Client id.
     */
    //定义MQTT的ID，可以在MQTT服务配置中指定
    String clientId;

    /**
     * The Options.
     */
    MqttConnectOptions options;

    /**
     * The Persistence.
     */
    MqttClientPersistence persistence;

    /**
     * The Client.
     */
    MqttClient client;

    /** The charset. */
    Charset charset = StandardCharsets.UTF_8;

    boolean reconnectInNewThread = true;

    /** The callback. */
    MqttCallback callback;

    /**
     * Instantiates a new Client.
     */
    ReconnectableClient() {
    }

    /**
     * Reconnect.
     *
     * @throws MqttException the mqtt exception
     */
    void reconnect() throws MqttException {
        connect(callback);
    }

    /**
     * Connect client.
     *
     * @param callback the callback
     * @return the client
     * @throws MqttException the mqtt exception
     */
    @SuppressWarnings("unchecked")
    protected C connect(MqttCallback callback) throws MqttException {
        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        if (!connected) {
            if (address == null) {
                address = protocol + "://" + host + ":" + port;
            }
            client = new MqttClient(address, clientId, persistence);
            //            client.setCallback(new ClientCallback(this));
            if (callback != null) {
                this.callback = callback;
                client.setCallback(callback);
            }
            try {
                logger.debug("client {}, address {}, options {}", clientId, address, options);
                client.connect(options);
                connected = true;
            } catch (Exception e) {
                // 开启线程自动重新连接
                connected = false;
                logger.error(e.getMessage());
                if (reconnectInNewThread) {
                    new Thread(() -> {
                        autoReconnect(1000);
                    }).start();
                } else {
                    autoReconnect(1000);
                }
            }
        }
        return (C) this;
    }

    /**
     * Auto reconnect.
     *
     * @param delayTimes the delay times
     */
    protected void autoReconnect(int delayTimes) {
        try {
            logger.debug("client {}, address {}, options {}", clientId, address, options);
            client.connect(options);
            connected = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            connected = false;
            try {
                logger.info("autoReconnect delay {}", delayTimes);
                Thread.sleep(delayTimes);
                if (delayTimes < 8000) {
                    autoReconnect(delayTimes * 2);
                } else {
                    autoReconnect(delayTimes);
                }
            } catch (InterruptedException interruptedException) {
                logger.error(interruptedException.getMessage());
            }
        }
    }

    /**
     * Disconnect.
     *
     * @return true, if successful
     */
    public boolean disconnect() {
        try {
            logger.debug("client {} disconnect", clientId);
            connected = false;
            client.disconnect();
            return true;
        } catch (MqttException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * Gets the client id.
     *
     * @return the client id
     */
    @Override
    public String getClientId() {
        return clientId;
    }

    /**
     * get charset value
     *
     * @return charset
     */
    @Override
    public Charset getCharset() {
        return charset;
    }

    /**
     * Publish.
     *
     * @param topic   the topic
     * @param message the message
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    protected void publish(MqttTopic topic, MqttMessage message) throws MqttPersistenceException, MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
    }

}