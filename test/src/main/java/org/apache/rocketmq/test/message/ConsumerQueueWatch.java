package org.apache.rocketmq.test.message;

import java.nio.ByteBuffer;

public class ConsumerQueueWatch {

  /**
   * 查看ConsumerQueue文件
   *
   * 消息偏移量，消息长度，tag hashcode
   */
  public static void main(String[] args) throws Exception {
    String path = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/consumequeue/topic/0/00000000000000000000";
    printConsumerQueue(path);

    path = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/consumequeue/topic/1/00000000000000000000";
    printConsumerQueue(path);

    path = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/consumequeue/topic/2/00000000000000000000";
    printConsumerQueue(path);

    path = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/consumequeue/topic/3/00000000000000000000";
    printConsumerQueue(path);
  }

  public static void printConsumerQueue(String path) throws Exception {
    System.out.println(path);
    ByteBuffer buffer = DataUtil.read(path);
    while (true) {
      long offset = buffer.getLong();
      long size = buffer.getInt();
      long code = buffer.getLong();
      if (size == 0) {
        break;
      }

      System.out.println("消息偏移量:" + offset + ",消息长度:" + size + ",tag hashcode:" + code);
    }
  }
}