package kr.huni.file_generator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import kr.huni.TestCleaner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JavaSourceCodeFileTests {

  @BeforeEach
  void setUp() throws IOException {
    TestCleaner.clean();
  }

  @AfterEach
  void tearDown() throws IOException {
    TestCleaner.clean();
  }

  @Test
  @DisplayName("이미 존재하는 파일을 생성하려고 할때 y 입력시 파일이 덮어씌워진다.")
  void writeToFile() throws IOException {
    // given
    String sourceRootDirectory = "p1000";
    String fileName = "Main.java";
    String overWrittenSourceCode = "over written source code";

    JavaSourceCodeFile javaSourceCodeFile = new JavaSourceCodeFile();
    javaSourceCodeFile.write(sourceRootDirectory, fileName, overWrittenSourceCode);
    File srcDir = new File(sourceRootDirectory, "src");

    // when
    System.setIn(new ByteArrayInputStream("y".getBytes()));
    javaSourceCodeFile.writeToFile(srcDir, fileName, overWrittenSourceCode);

    // then
    String sourceCode = readFile("p1000/src/Main.java");
    Assertions.assertEquals(overWrittenSourceCode, sourceCode);
  }

  String readFile(String filePath) throws IOException {
    StringBuilder sourceCode;
    FileReader fileReader = new FileReader(filePath);

    sourceCode = new StringBuilder();
    int ch;
    while ((ch = fileReader.read()) != -1) {
      sourceCode.append((char) ch);
    }

    return sourceCode.toString();
  }
}