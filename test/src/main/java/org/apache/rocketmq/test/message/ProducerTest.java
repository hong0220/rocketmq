package org.apache.rocketmq.test.message;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class ProducerTest {

  public static void main(String[] args) throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
    producer.setNamesrvAddr("127.0.0.1:9876");
    producer.start();
    for (int i = 0; i < 10; i++) {
      Message message = new Message();
      message.setTopic("topic");
      message.setBody(("hong0220的博客").getBytes());
      SendResult sendResult = producer.send(message);
    }
    producer.shutdown();
  }
}