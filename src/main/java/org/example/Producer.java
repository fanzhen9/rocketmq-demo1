package org.example;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("my_test_producer_group");
        producer.setNamesrvAddr("192.168.18.136:9876");
        producer.start();

        for (int i = 0; i < 1; i++) {
            Message message = new Message("test_topic_1","Tag1",i+"",(String.format("这是第%d条消息",i)).getBytes());
            try {
                SendResult send = producer.send(message);
                System.out.println(String.format("%d",i)+send);
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
