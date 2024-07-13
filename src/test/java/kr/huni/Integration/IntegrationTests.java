package kr.huni.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.huni.BojStarter;
import kr.huni.DynamicCodeCompileSupporter;
import kr.huni.TestCleaner;
import kr.huni.code.runner.FakeCodeOpen;
import kr.huni.code_generator.JavaCodeGenerator;
import kr.huni.file_generator.JavaSourceCodeFile;
import kr.huni.problem_parser.BaekjoonProblemParser;
import kr.huni.problem_parser.JsoupWebParser;

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

  @Test
  @DisplayName("정답 솔루션으로 테스트헬퍼 실행 검증")
  void p1000Success() throws IOException, InterruptedException {
    // given
    BojStarter program = new BojStarter(
        new FakeCodeOpen(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser(1000)));
    program.run(1000);

    String testCode = Files.readString(Path.of("p1000/src/TestHelper.java"));
    String solution = """
        import java.util.Scanner;
                    
        public class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                System.out.println(a + b);
            }
        }
        """;

    // when & then
    assertTrue(DynamicCodeCompileSupporter.testRunWell(solution, testCode, """
        ===============
        테스트 완료 (1 / 1)
        주어진 케이스에 대해 잘 동작하고 있습니다."""));
  }

  @Test
  @DisplayName("시간초과 솔루션에 대한 테스트헬퍼 실행 검증")
  void testHelperTimeoutLogicTest() throws IOException, InterruptedException {
    // given
    BojStarter program = new BojStarter(
        new FakeCodeOpen(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser(1000)));
    program.run(1000);

    String testCode = Files.readString(Path.of("p1000/src/TestHelper.java"));
    String solution = """
        public class Main {
            public static void main(String[] args) throws InterruptedException {
                Thread.sleep(5000);
            }
        }
        """;

    // when & then
    assertTrue(DynamicCodeCompileSupporter.testRunWell(solution, testCode, "시간 초과 발생"));
  }

  @Test
  @DisplayName("틀린 솔루션에 대한 테스트헬퍼 실행 검증")
  void testHelperWrongSolutionTest() throws IOException, InterruptedException {
    // given
    BojStarter program = new BojStarter(
        new FakeCodeOpen(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser(1000)));
    program.run(1000);

    String testCode = Files.readString(Path.of("p1000/src/TestHelper.java"));
    String solution = """
        public class Main {
            public static void main(String[] args) throws InterruptedException {
                System.out.println("anstjdgns");
            }
        }
        """;

    // when & then
    assertTrue(DynamicCodeCompileSupporter.testRunWell(solution, testCode, """
        ====== 1 번째 케이스 실패 ======
        [입력 값]
        1 2
                
        [기대 값]
        3
                
        [실제 값]
        anstjdgns
                
        출력 값이 기대한 값과 다릅니다.
        ===============
        """));
  }
}
