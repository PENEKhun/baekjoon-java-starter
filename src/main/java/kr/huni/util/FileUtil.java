package kr.huni.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class FileUtil {

  private final Logger logger = Logger.getLogger(getClass().getName());
  private File srcDir;

  public void write(String sourceCode, String testCode) throws IOException {
    File src = new File(srcDir, "Main.java");
    FileWriter fileWriter = new FileWriter(src);
    fileWriter.write(sourceCode);

    File testSrc = new File(srcDir, "TestHelper.java");
    FileWriter testFileWriter = new FileWriter(testSrc);
    testFileWriter.write(testCode);

    fileWriter.close();
    testFileWriter.close();
  }

  public void createDirectory(String directory) throws IOException {
    this.srcDir = new File(directory, "src");

    if (!this.srcDir.exists()) {
      boolean success = srcDir.mkdirs();
      if (success) {
        logger.info("소스코드 디렉토리 생성 완료");
      } else {
        throw new IOException("소스코드 디렉터리 생성 실패");
      }
    }
  }

}
