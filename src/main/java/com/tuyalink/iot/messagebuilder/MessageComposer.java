package com.tuyalink.iot.messagebuilder;

import com.tuyalink.iot.TuyaMQTT3ClientDemo;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;

public class MessageComposer {
    public static void buildMesaage(boolean switchStatus){
        // Property report content
        // message UUID
        UUID msgUUID = UUID.randomUUID();
        // Property report topic
        String topic = "tylink/" + TuyaMQTT3ClientDemo.deviceId + "/thing/property/report";
        // Current timestamp
        long timestamp = System.currentTimeMillis();

        String content = "{\n" +
                "\t\"msgId\":\"" + msgUUID + "\",\n" +
//                "\t\"msgId\":\"45lkj3551234002\",\n" +
                "  \t\"time\":" + timestamp + ",\n" +
                "\t\"data\":{\n" +
                "    \t\"switch\":{\n" +
                "        \t\"value\":" + switchStatus + ",\n" +
                "        \t\"time\": " + timestamp + "  \n" +
                "        }\n" +
                "\t}\n" +
                "}";


        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(1);
        try {
            TuyaMQTT3ClientDemo.sampleClient.publish(topic, message);
            System.out.println("publish topic: " + topic);
            System.out.println("publish content: " + content);
        } catch (MqttException e) {
            System.out.println("reason " + e.getReasonCode());
            System.out.println("msg " + e.getMessage());
            System.out.println("loc " + e.getLocalizedMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("excep " + e);
            e.printStackTrace();
        }

    }

}
