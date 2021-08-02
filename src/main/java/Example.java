/*
 * Copyright 2021 HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.net.ssl.SSLSocketFactory;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Example {
    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient(
                "ssl://<your_host>:8883", // serverURI in format: "protocol://name:port"
                MqttClient.generateClientId(), // ClientId
                new MemoryPersistence()); // Persistence


        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName("<your_username>");
        mqttConnectOptions.setPassword("<your_password>".toCharArray());
        mqttConnectOptions.setSocketFactory(SSLSocketFactory.getDefault()); // using the default socket factory
        client.connect(mqttConnectOptions);


        client.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable cause) { // Called when the client lost the connection to the broker
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                System.out.println(topic + ": " + Arrays.toString(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) { // Called when a outgoing publish is complete
            }
        });

        client.subscribe("#", 1); // subscribe to everything with QoS = 1

        client.publish(
                "topic",
                "payload".getBytes(UTF_8),
                2, // QoS = 2
                false);

        client.disconnect();

    }
}
