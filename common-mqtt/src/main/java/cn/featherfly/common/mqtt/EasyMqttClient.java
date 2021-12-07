
/*
 * All rights Reserved, Designed By zhongj
 * @Title: EasyMqttClient.java
 * @Package cn.featherfly.common.mqtt
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-04-27 11:55:27
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

/**
 * EasyMqttClient.
 *
 * @author zhongj
 */
public interface EasyMqttClient {

    /**
     * Gets the client id.
     *
     * @return the client id
     */
    String getClientId();

    /**
     * Gets the charset.
     *
     * @return the charset
     */
    Charset getCharset();

    /**
     * Connect client.
     *
     * @return the client
     * @throws MqttException the mqtt exception
     */
    EasyMqttClient connect() throws MqttException;

    //    void connect(Consumer<EasyMqttClient> consumer) throws MqttException;

    /**
     * Disconnect.
     *
     * @return true, if successful
     */
    boolean disconnect();

    /**
     * Checks if is connected.
     *
     * @return true, if is connected
     */
    boolean isConnected();

    /**
     * Subscribe.
     *
     * @param topicFilter the topic filter
     * @param qos         the qos
     * @param consumer    the consumer
     * @return the easy client
     * @throws MqttException the mqtt exception
     */
    EasyMqttClient subscribe(String topicFilter, Qos qos, Consumer<MqttMessage> consumer) throws MqttException;

    /**
     * Subscribe client.
     *
     * @param topicFilter the topic filter
     * @param qos         the qos
     * @param consumer    the consumer
     * @return the client
     * @throws MqttException the mqtt exception
     */
    EasyMqttClient subscribe(String topicFilter, Qos qos, BiConsumer<String, MqttMessage> consumer)
            throws MqttException;

    /**
     * Clear subscribe.
     *
     * @param topicFilter the topic filter
     * @return the easy mqtt client
     * @throws MqttException the mqtt exception
     */
    EasyMqttClient unsubscribe(String topicFilter) throws MqttException;

    /**
     * Clear all subscribe.
     *
     * @return the easy mqtt client
     * @throws MqttException the mqtt exception
     */
    EasyMqttClient unsubscribeAll() throws MqttException;

    /**
     * Publish client.
     *
     * @param topicFilter the topic
     * @param msg         the msg
     * @return the client
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    EasyMqttClient publish(String topicFilter, String msg) throws MqttPersistenceException, MqttException;

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
    EasyMqttClient publish(String topic, String msg, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException;

    /**
     * Publish client.
     *
     * @param topic the topic
     * @param msg   the msg
     * @param qos   the qos
     * @return the client
     * @throws MqttException the mqtt exception
     */
    EasyMqttClient publish(String topic, String msg, Qos qos) throws MqttException;

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
    EasyMqttClient publish(String topic, String msg, Qos qos, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException;

    /**
     * Publish.
     *
     * @param topic    the topic
     * @param msg      the msg
     * @param qos      the qos
     * @param retained the retained
     * @param consumer the consumer
     * @return the easy mqtt client impl
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    EasyMqttClient publish(String topic, String msg, Qos qos, boolean retained, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException;

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
    EasyMqttClient publish(String topic, String msg, Qos qos, Charset charset, Consumer<IMqttDeliveryToken> consumer)
            throws MqttPersistenceException, MqttException;

    /**
     * Publish.
     *
     * @param topic    the topic
     * @param msg      the msg
     * @param qos      the qos
     * @param charset  the charset
     * @param retained the retained
     * @param consumer the consumer
     * @return the easy mqtt client impl
     * @throws MqttPersistenceException the mqtt persistence exception
     * @throws MqttException            the mqtt exception
     */
    EasyMqttClient publish(String topic, String msg, Qos qos, Charset charset, boolean retained,
            Consumer<IMqttDeliveryToken> consumer) throws MqttPersistenceException, MqttException;

}