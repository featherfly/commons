
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;

import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import cn.featherfly.common.lang.Strings;

/**
 * ClientBuilder.
 *
 * @author zhongj
 */
public class EasyMqttClientBuilder {

    private String host;

    private int port = EasyMqttClientImpl.DEFAULT_PORT;

    private String protocol = EasyMqttClientImpl.DEFAULT_PROTOCOL;

    private String address;

    private String clientId;

    private Charset charset;

    private boolean reconnectInNewThread = true;

    private String username;

    private String password;

    // 下面属性与 paho.client 耦合了
    private MqttConnectOptions options;

    private MqttClientPersistence persistence;

    /**
     * Instantiates a new client builder.
     */
    public EasyMqttClientBuilder() {
    }

    /**
     * Instantiates a new client builder.
     *
     * @param address  the address
     * @param clientId the client id
     */
    public EasyMqttClientBuilder(String address, String clientId) {
        this.address = address;
        this.clientId = clientId;
    }

    /**
     * Instantiates a new client builder.
     *
     * @param host     the host
     * @param port     the port
     * @param clientId the client id
     */
    public EasyMqttClientBuilder(String host, int port, String clientId) {
        this.host = host;
        this.port = port;
        this.clientId = clientId;
    }

    /**
     * Instantiates a new client builder.
     *
     * @param protocol the protocol
     * @param host     the host
     * @param port     the port
     * @param clientId the client id
     */
    public EasyMqttClientBuilder(String protocol, String host, int port, String clientId) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.clientId = clientId;
    }

    /**
     * Protocol.
     *
     * @param protocol the protocol
     * @return the client builder
     */
    public EasyMqttClientBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * Host.
     *
     * @param host the host
     * @return the client builder
     */
    public EasyMqttClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    /**
     * Port.
     *
     * @param port the port
     * @return the client builder
     */
    public EasyMqttClientBuilder port(int port) {
        this.port = port;
        return this;
    }

    /**
     * Address.
     *
     * @param address the address
     * @return the client builder
     */
    public EasyMqttClientBuilder address(String address) {
        this.address = address;
        return this;
    }

    /**
     * Client id.
     *
     * @param clientId the client id
     * @return the client builder
     */
    public EasyMqttClientBuilder clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * Options.
     *
     * @param options the options
     * @return the client builder
     */
    public EasyMqttClientBuilder options(MqttConnectOptions options) {
        this.options = options;
        return this;
    }

    /**
     * Persistence.
     *
     * @param persistence the persistence
     * @return the client builder
     */
    public EasyMqttClientBuilder persistence(MqttClientPersistence persistence) {
        this.persistence = persistence;
        return this;
    }

    /**
     * Charset.
     *
     * @param charset the charset
     * @return the client builder
     */
    public EasyMqttClientBuilder charset(Charset charset) {
        this.charset = charset;
        return this;
    }

    /**
     * Username.
     *
     * @param username the username
     * @return the client builder
     */
    public EasyMqttClientBuilder username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Password.
     *
     * @param password the password
     * @return the client builder
     */
    public EasyMqttClientBuilder password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Reconnect in new thread.
     *
     * @param reconnectInNewThread the reconnect in new thread
     * @return the client builder
     */
    public EasyMqttClientBuilder reconnectInNewThread(boolean reconnectInNewThread) {
        this.reconnectInNewThread = reconnectInNewThread;
        return this;
    }

    /**
     * Builds the.
     *
     * @return the easy client
     */
    public EasyMqttClient build() {
        EasyMqttClientImpl client = new EasyMqttClientImpl();
        init(client);
        return client;
    }

    private void init(ReconnectableClient<?> client) {
        client.host = host;
        client.port = port;
        if (protocol != null && protocol.length() > 0) {
            client.protocol = protocol;
        }
        client.address = address;
        client.clientId = clientId;
        if (persistence == null) {
            persistence = new MemoryPersistence();
        }
        client.persistence = persistence;
        if (options == null) {
            options = new MqttConnectOptions();
        }

        if (Strings.isNotEmpty(username)) {
            options.setUserName(username);
        }
        if (Strings.isNotEmpty(password)) {
            options.setPassword(password.toCharArray());
        }
        client.options = options;
        client.reconnectInNewThread = reconnectInNewThread;
        if (charset != null) {
            client.charset = charset;
        }
    }
}