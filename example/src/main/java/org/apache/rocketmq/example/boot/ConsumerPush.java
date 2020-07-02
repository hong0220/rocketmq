package org.apache.rocketmq.example.boot;

import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class ConsumerPush {

  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consumer");
    consumer.setNamesrvAddr("127.0.0.1:9876");
    // 订阅多个topic,tag过滤
    consumer.subscribe("test_topic", "*");
    // 注册回调方法处理拉取信息
    consumer.registerMessageListener(new MessageListenerConcurrently() {

      @Override
      public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);

        // 消息重复消费

        // 消息稍后重复消费
        // return ConsumeConcurrentlyStatus.RECONSUME_LATER;

        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }
    });
    // 启动
    consumer.start();
  }
}