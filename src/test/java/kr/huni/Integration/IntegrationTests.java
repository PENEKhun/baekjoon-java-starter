package kr.huni.Integration;

import static kr.huni.user_configuration.UserConfigurationLoader.CONFIGURATION_FILE_NAME;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import kr.huni.BojStarter;
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

  void clean() throws IOException {
    System.out.println("Clean up...");

    // 설정 파일 삭제
    File configFile = new File(CONFIGURATION_FILE_NAME);
    if (configFile.exists()) {
      Files.deleteIfExists(Path.of(CONFIGURATION_FILE_NAME));
      assert !new File(CONFIGURATION_FILE_NAME).exists();
    }

    // 생성된 파일 삭제
    Files.deleteIfExists(Path.of("p1000/src/Main.java"));
    Files.deleteIfExists(Path.of("p1000/src/TestHelper.java"));
    Files.deleteIfExists(Path.of("p1000/src/NoTestHelper.java"));
    Files.deleteIfExists(Path.of("p1000/src"));
    Files.deleteIfExists(Path.of("p1000"));

    assert !new File("p1000").exists();
  }

  @BeforeEach
  void setUp() throws IOException {
    clean();
  }

  @AfterEach
  void tearDown() throws IOException {
    clean();
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

}
