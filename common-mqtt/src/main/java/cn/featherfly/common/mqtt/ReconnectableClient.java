/*
 * All rights Reserved, Designed By zhongj
 * @Title: ReconnectableClient.java
 * @Package cn.featherfly.common.mqtt
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021年12月7日 下午6:40:30
 * @version V1.0
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */

package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

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

    /** The connected consumer. */
    protected Consumer<EasyMqttClient> connectedConsumer;

    /**
     * The Client.
     */
    MqttClient client;

    /** The charset. */
    Charset charset = StandardCharsets.UTF_8;

    /** The reconnect in new thread. */
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
        connect(null, null);
    }

    /**
     * Connect client.
     *
     * @param callback the callback
     * @param consumer the consumer
     * @return the client
     * @throws MqttException the mqtt exception
     */
    @SuppressWarnings("unchecked")
    protected C connect(MqttCallback callback, Consumer<EasyMqttClient> consumer) throws MqttException {
        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        if (!isConnected()) {
            if (client == null) {
                if (address == null) {
                    address = protocol + "://" + host + ":" + port;
                }
                client = new MqttClient(address, clientId, persistence);
            }

            if (callback != null) {
                this.callback = callback;
            }
            if (this.callback != null) {
                client.setCallback(this.callback);
            }

            if (consumer != null) {
                this.connectedConsumer = consumer;
            }

            try {
                logger.debug("client {}, address {}, options {}", clientId, address, options);
                client.connect(options);
                logger.debug("client connect success");
                connected();
            } catch (Exception e) {
                // 开启线程自动重新连接
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
            if (client.isConnected()) {
                return;
            }
            logger.debug("client {}, address {}, options {}", clientId, address, options);
            client.connect(options);
            logger.debug("client connect success");
            connected();
        } catch (Exception e) {
            logger.error(e.getMessage());
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
     * Connected.
     *
     * @throws MqttException the mqtt exception
     */
    protected void connected() throws MqttException {
        if (connectedConsumer != null) {
            connectedConsumer.accept(this);
        }
    }

    /**
     * Disconnect.
     *
     * @return true, if successful
     */
    @Override
    public boolean disconnect() {
        try {
            logger.debug("client {} disconnect", clientId);
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
     * get charset value.
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnected() {
        return client != null && client.isConnected();
    }

}