package kr.huni.Integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
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
  void setUp() throws IOException {
    TestCleaner.clean();
  }

  @AfterEach
  void tearDown() throws IOException {
    TestCleaner.clean();
  }

  @Test
  @DisplayName("프로그램에서 Main.java 파일이 잘 생성된다.")
  void applicationTest() {
    // given
    BojStarter program = new BojStarter(
        new FakeCodeOpen(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser(1000)));

    // when
    program.run(1000);

    // then
    assertTrue(new File("p1000/src/Main.java").exists());
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
