
package cn.featherfly.common.mqtt;

import java.nio.charset.Charset;

import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * ClientBuilder.
 *
 * @author zhongj
 */
public class ClientBuilder {

    private String host;

    private int port = EasyClient.DEFAULT_PORT;

    private String protocol = EasyClient.DEFAULT_PROTOCOL;

    private String address;
    private String clientId;
    private MqttConnectOptions options;
    private MqttClientPersistence persistence;
    private Charset charset;

    private boolean reconnectInNewThread = true;

    public ClientBuilder() {
    }

    public ClientBuilder(String address, String clientId) {
        this.address = address;
        this.clientId = clientId;
    }

    public ClientBuilder(String protocol, String host, int port, String clientId) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.clientId = clientId;
    }

    public ClientBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public ClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    public ClientBuilder port(int port) {
        this.port = port;
        return this;
    }

    public ClientBuilder address(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public ClientBuilder options(MqttConnectOptions options) {
        this.options = options;
        return this;
    }

    public ClientBuilder persistence(MqttClientPersistence persistence) {
        this.persistence = persistence;
        return this;
    }

    public ClientBuilder charset(Charset charset) {
        this.charset = charset;
        return this;
    }

    public ClientBuilder reconnectInNewThread(boolean reconnectInNewThread) {
        this.reconnectInNewThread = reconnectInNewThread;
        return this;
    }

    public EasyClient build() {
        EasyClient client = new EasyClient();
        init(client);
        return client;
    }

    public SimpleClient buildSimpleClient() {
        SimpleClient client = new SimpleClient();
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
        client.options = options;
        client.reconnectInNewThread = reconnectInNewThread;
        if (charset != null) {
            client.charset = charset;
        }
    }
}