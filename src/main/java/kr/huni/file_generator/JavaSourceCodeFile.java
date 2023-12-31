package kr.huni.file_generator;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaSourceCodeFile implements SourceCodeFile {

  @Override
  public void write(String sourceRootDirectory, String sourceCode, String testCode)
      throws IOException {
    File srcDir = new File(sourceRootDirectory, "src");

    log.info("srcDir : {}", srcDir.getAbsolutePath());
    srcDir.mkdirs();

    log.info("소스코드 디렉토리 생성 완료");
    writeToFile(srcDir, "Main.java", sourceCode);
    writeToFile(srcDir, "TestHelper.java", testCode);
  }

}
