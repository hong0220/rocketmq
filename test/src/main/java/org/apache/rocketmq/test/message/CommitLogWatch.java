package org.apache.rocketmq.test.message;

import java.nio.ByteBuffer;
import org.apache.rocketmq.common.message.MessageDecoder;
import org.apache.rocketmq.common.message.MessageExt;

public class CommitLogWatch {

  /**
   * 查看CommitLog文件
   *
   * 消息的主题，消息内容，MessageQueue ID，存储地址
   */
  public static void main(String[] args) throws Exception {
    String filePath = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/commitlog/00000000000000000000";
    ByteBuffer buffer = DataUtil.read(filePath);
    while (true) {
      MessageExt message = MessageDecoder.decode(buffer);
      if (message != null) {
        break;
      }

      System.out.println("主题:" + message.getTopic()
          + ",消息内容:" + new String(message.getBody())
          + ",MessageQueue ID:" + message.getQueueId()
          + ",存储地址:" + message.getStoreHost());
    }
  }
}