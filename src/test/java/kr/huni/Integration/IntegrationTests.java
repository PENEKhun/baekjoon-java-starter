package kr.huni.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import kr.huni.BojStarter;
import kr.huni.TestCleaner;
import kr.huni.code.runner.FakeCodeOpen;
import kr.huni.code_generator.JavaCodeGenerator;
import kr.huni.file_generator.JavaSourceCodeFile;
import kr.huni.problem_parser.BaekjoonProblemParser;
import kr.huni.problem_parser.JsoupWebParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Large Integration Test")
class IntegrationTests {

  @BeforeEach
  void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
    TestCleaner.clean();
  }

  @AfterEach
  void tearDown() throws IOException, NoSuchFieldException, IllegalAccessException {
    TestCleaner.clean();
  }

  @Test
  @DisplayName("예상된 Main.java 파일의 내용이 생성된다.")
  void mainJavaContent() throws IOException {
    // given
    String expected = """
        import java.util.Scanner;
        
        /*
            BAEKJOON 1000번 A+B
            https://www.acmicpc.net/problem/1000
        */
        
        public class Main {
        
          public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            // 코드를 작성하세요.
          }
        }
        """;

    BojStarter program = new BojStarter(
        new FakeCodeOpen(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser(1000)));
    String path = "p1000/src/Main.java";
    program.run(1000);

    // when
    String generatedMain = Files.readString(Path.of(path));

    // then
    assertEquals(expected, generatedMain);
  }

  @Test
  @DisplayName("프로그램에서 TestHelper.java 파일이 잘 생성된다.")
  void testHelper() {
    // given
    BojStarter program = new BojStarter(
        new FakeCodeOpen(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser(1000)));

    // when
    program.run(1000);

    // then
    assertTrue(new File("p1000/src/TestHelper.java").exists());
  }

  @Test
  @DisplayName("프로그램에서 README.md 파일이 잘 생성된다.")
  void Readme_generate_well() {
    // given
    BojStarter program = new BojStarter(
        new FakeCodeOpen(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser(1000)));

    // when
    program.run(1000);

    // then
    assertTrue(new File("p1000/src/README.md").exists());
  }
}
