package org.apache.rocketmq.example.boot;

import java.io.UnsupportedEncodingException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class ProducerASync {

  public static void main(String[] args)
      throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
    DefaultMQProducer producer = new DefaultMQProducer("test_producer");
    producer.setNamesrvAddr("127.0.0.1:9876");
    producer.start();
    Message msg = new Message("test_topic" /* Topic */,
        "Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET)); /* Message body */
    // 异步发送消息重试次数
    producer.setRetryTimesWhenSendAsyncFailed(3);

    // 异步发送消息 ，如果返回状态不是SendStatus.SEND_OK，重试
    producer.send(msg, new SendCallback() {
      /**
       * 消息发送成功
       */
      @Override
      public void onSuccess(SendResult sendResult) {

      }

      /**
       * 消息发送失败，持久化，补偿机制
       */
      @Override
      public void onException(Throwable e) {

      }
    });
  }
}