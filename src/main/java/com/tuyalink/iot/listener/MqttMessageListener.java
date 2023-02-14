package com.tuyalink.iot.listener;

import com.tuyalink.iot.TuyaMQTT3ClientDemo;
import com.tuyalink.iot.messagebuilder.MessageComposer;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * MQTT Message Listener
 */
public class MqttMessageListener implements IMqttMessageListener {
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("response topic  : " + topic);
        System.out.println("response payload: " + message.toString());
        // json parser "denoartri...."
        if(message.toString().indexOf("true") >= 0) {
            TuyaMQTT3ClientDemo.switchStatus = true;
        } else {
            TuyaMQTT3ClientDemo.switchStatus = false;
        }
        System.out.println("from cloud switch was set to " + TuyaMQTT3ClientDemo.switchStatus);
        System.out.println("we send back the new status;");
        MessageComposer.buildMesaage(TuyaMQTT3ClientDemo.switchStatus);


    }
}
