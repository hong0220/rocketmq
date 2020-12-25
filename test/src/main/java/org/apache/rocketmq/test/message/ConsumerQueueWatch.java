package org.apache.rocketmq.test.message;

import java.nio.ByteBuffer;

public class ConsumerQueueWatch {

  public static void main(String[] args) throws Exception {
    String path = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/consumequeue/topic/1/00000000000000000000";
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