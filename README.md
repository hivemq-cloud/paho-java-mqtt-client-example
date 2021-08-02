# Paho Java MQTT Client Example

## Overview
This is an MQTT client example project that showcases how you can use HiveMQ Cloud with the Eclipse Paho Java Client. The example project covers the basic MQTT functionality: Connecting MQTT clients to your HiveMQ Cloud cluster, subscribing to topics and publishing data (sending and receiving messages using the MQTT protocol).

The Eclipse Paho project provides open source, mainly client side, implementations of MQTT and MQTT-SN in a variety of programming languages.
The Java client used here supports MQTT v3.1, v3.1.1 and v5.
You can find documentation for this client library here: https://www.hivemq.com/blog/mqtt-client-library-encyclopedia-eclipse-paho-java/.

This example repository is easily and clearly structured, so you can start quickly:
This readme file is your starting point. Here we will describe what you have to do step-by-step to get started with this example.
[``Example.java``](/src/main/java/Example.java), located in ``/src/main/java/``, is a simple implementation that demonstrates the core functionality of an MQTT Client.
Follow the instructions in the following paragraphs to get started yourself.

## HiveMQ Cloud
[HiveMQ](https://www.hivemq.com/) is the industry leader for enterprise-ready, beautifully scalable, large-scale IoT deployments with MQTT. We help companies connect things to the Internet. Our MQTT-based messaging platform ensures fast, reliable, and secure movement of data to and from connected IoT devices for companies around the world. HiveMQ Cloud is our fully-managed, cloud-native IoT messaging platform that makes trustworthy and scalable IoT device connectivity simple. You can learn more about HiveMQ Cloud on our [website](https://www.hivemq.com/mqtt-cloud-broker/), [documentation](https://www.hivemq.com/docs/hivemq-cloud/introduction.html)  and our [blog posts](https://www.hivemq.com/tags/cloud/).

## Getting started
[By signing up](https://console.hivemq.cloud) for HiveMQ Cloud you will automatically get access to a HiveMQ Cloud Basic cluster. HiveMQ Cloud Basic is our smallest package that allows you to connect up to 100 MQTT clients for free and test the full MQTT functionality. 

The [HiveMQ Cloud Quick Start Guide](https://www.hivemq.com/docs/hivemq-cloud/introduction.html#guide) gives you step-by-step instructions on how to set up your HiveMQ Cloud account, create clusters, and connect MQTT clients.


### Prerequisites 
After signing up, you have a running HiveMQ Cloud cluster, that you can use in this example.
Now clone this repository into your local IDE.

### Broker credentials
To define the HiveMQ Cloud cluster which should be targeted, you need to fill the placeholders in the code with your host name, username and password.
The <b>host name</b> can be found in the <b>Details</b> section of the <b>Overview</b> tab of your cluster.
![cluster overview](/img/hivemq-cloud-cluster-overview.png)

After the cluster is created, add a set of credentials that you can use in this example or future implementations.
Use any secure username and password you desire.
The <b>Client Credentials</b> that you just created are the values needed for the <b>username</b> and <b>password</b> variables.
![credentials](/img/hivemq-cloud-credentials.png)

### Code Examples
This example project covers the core functionality of an MQTT client interacting with HiveMQ Cloud.
To securely connect the MQTT client with HiveMQ Cloud you need to enable TLS.
Use your username and password, to authenticate your MQTT client at HiveMQ Cloud.
To connect the client, use the port 8883 that is standard for secure MQTT communication. 

```java
MqtClient client = new MqttClient(
        "ssl://<your_host>:8883", // serverURI in format: "protocol://name:port"
        MqttClient.generateClientId(), // ClientId
        new MemoryPersistence()); // Persistence

MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
mqttConnectOptions.setUserName("<your_username>");
mqttConnectOptions.setPassword("<your_password>".toCharArray());
mqttConnectOptions.setSocketFactory(SSLSocketFactory.getDefault());
client.connect(mqttConnectOptions);
```

The code located inside [``Example.java``](/src/main/java/Example.java) connects to the configured HiveMQ Cloud Broker in a simple way. 
This is a ready-set example that can simply be run in your IDE after inputting your credentials.

The code first subscribes to the topic filter "#".  
This enables the MQTT client to receive all messages that are published to this [topic filter](https://www.hivemq.com/blog/mqtt-essentials-part-5-mqtt-topics-best-practices/).
The so-called wildcard ("#"), that is used here as the topic filter, includes <b>all</b> topics.
```java
client.subscribe("#", 1); // subscribe to everything with QoS = 1
```
Then the code publishes a message to the topic ``"topic"``.
```java
client.publish(
        "topic",
        "payload".getBytes(UTF_8),
        2, // QoS = 2
        false);
```
It prints the content (payload) of the message due to the ``messageArrived`` callback, that gets triggered when a message is received.
Other callbacks in this example are ``connectionLost`` and ``deliveryComplete``, that get called when their respective event occurs.

## Learn more
If you want to learn more about MQTT, visit the [MQTT Essentials](https://www.hivemq.com/mqtt-essentials/) guide. 
It explains the core MQTT concepts, features and other essential information.
To learn more about Paho-Java, visit the [official website](https://www.eclipse.org/paho/index.php?page=clients/java/index.php) of the eclipse foundation.

## Contributing

Please see our [contributing guidelines](./CONTRIBUTING.adoc) and [code of conduct](./code-of-conduct.md).

## License

[Apache 2.0](./LICENSE).
