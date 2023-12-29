package kr.huni.util;

import java.io.BufferedReader;
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
    if (file.exists()) {
      log.warn("{}/{}가 이미 존재합니다. 덮어 씌우시겠습니까? (y, n)", directory.getName(), fileName);
      BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
      String answer = reader.readLine();
      if (answer.equals("y")) {
        log.info("기존 파일을 덮어 씌웁니다.");
      } else {
        return;
      }
    }

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
