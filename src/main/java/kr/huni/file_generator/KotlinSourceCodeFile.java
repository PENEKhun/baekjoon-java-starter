package kr.huni.file_generator;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KotlinSourceCodeFile implements SourceCodeFile {

  @Override
  public void write(String sourceRootDirectory, String sourceCode, String testCode, String readme,
      boolean enableReadme)
      throws IOException {
    File srcDir = new File(sourceRootDirectory, "src");

    log.info("srcDir : {}", srcDir.getAbsolutePath());
    srcDir.mkdirs();

    log.info("소스코드 디렉토리 생성 완료");
    writeToFile(srcDir, "Main.kt", sourceCode);
    writeToFile(srcDir, "TestHelper.java", testCode);
    log.info("소스코드 파일 생성 완료");

    if (enableReadme) {
      writeToFile(srcDir, "README.md", readme);
      log.info("README.md 파일 생성 완료");
    }
  }
}
