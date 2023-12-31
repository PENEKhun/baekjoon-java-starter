package kr.huni.file_generator;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaSourceCodeFile implements SourceCodeFile {

  @Override
  public void write(String directory, String sourceCode, String testCode) throws IOException {
    writeToFile(directory + "/src", "Main.java", sourceCode);
    writeToFile(directory + "/src", "TestHelper.java", testCode);
  }

  @Override
  public void createDirectory(String directory) throws IOException {
    File srcDir = new File(directory, "src");

    if (!srcDir.exists()) {
      boolean success = srcDir.mkdirs();
      if (success) {
        log.info("소스코드 디렉토리 생성 완료");
      } else {
        throw new IOException("소스코드 디렉터리 생성 실패, 프로그램을 종료합니다.");
      }
    }
  }

}
