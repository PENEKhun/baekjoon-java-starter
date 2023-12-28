package kr.huni.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

  private File srcDir;

  public void write(String sourceCode, String testCode) throws IOException {
    writeToFile(srcDir, "Main.java", sourceCode);
    writeToFile(srcDir, "TestHelper.java", testCode);
  }

  private void writeToFile(File directory, String fileName, String code) throws IOException {
    File file = new File(directory, fileName);

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(code);
    }
  }

  public void createDirectory(String directory) throws IOException {
    this.srcDir = new File(directory, "src");

    if (!this.srcDir.exists()) {
      boolean success = srcDir.mkdirs();
      if (success) {
        log.info("소스코드 디렉토리 생성 완료");
      } else {
        throw new IOException("소스코드 디렉터리 생성 실패");
      }
    }
  }

}
