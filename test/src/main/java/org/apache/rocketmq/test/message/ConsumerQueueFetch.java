package org.apache.rocketmq.test.message;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import org.apache.rocketmq.common.message.MessageDecoder;
import org.apache.rocketmq.common.message.MessageExt;

public class ConsumerQueueFetch {

  public static void main(String[] args) throws Exception {
    String commitLogPath = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/commitlog/00000000000000000000";
    ByteBuffer commitLog = DataUtil.read(commitLogPath);

    // 变量
    String consumerPath = "/Users/didi/Desktop/import/source/rocketmq/data/storeOwn/consumequeue";
    File file = new File(consumerPath);
    File[] files = file.listFiles();
    for (File f : files) {
      if (f.isDirectory()) {
        for (File queuePath : f.listFiles()) {
          String path = queuePath + "/00000000000000000000";
          System.out.println(path);
          // 读取ConsumerQueue文件内容
          ByteBuffer buffer = DataUtil.read(path);

          while (true) {
            // 读取消息偏移量和消息长度
            long offset = (int) buffer.getLong();
            int size = buffer.getInt();
            long code = buffer.getLong();
            if (size == 0) {
              break;
            }

            System.out.println("消息偏移量:" + offset + ",消息长度:" + size + ",tag hashcode:" + code);

            // 根据偏移量和消息长度在commitlog文件中读取消息内容
            MessageExt message = getMessageByOffset(commitLog, offset, size);
            if (message != null) {
              System.out.println("主题:" + message.getTopic()
                  + ",消息内容:" + new String(message.getBody())
                  + ",MessageQueue ID:" + message.getQueueId()
                  + ",存储地址:" + message.getStoreHost());
            }
          }
        }
      }
    }
  }

  public static MessageExt getMessageByOffset(ByteBuffer commitLog, long offset, int size) {
    ByteBuffer slice = commitLog.slice();
    // 截取
    slice.position((int) offset);
    slice.limit((int) (offset + size));

    return MessageDecoder.decode(slice);
  }
}