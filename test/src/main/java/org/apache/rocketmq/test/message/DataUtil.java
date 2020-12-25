package org.apache.rocketmq.test.message;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

public class DataUtil {

  public static ByteBuffer read(String path) throws Exception {
    File file = new File(path);
    FileInputStream fis = new FileInputStream(file);
    byte[] bytes = new byte[(int) file.length()];
    fis.read(bytes);
    return ByteBuffer.wrap(bytes);
  }
}