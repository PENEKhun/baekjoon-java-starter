package kr.huni.code_generator;

import java.io.IOException;
import java.util.ArrayList;
import kr.huni.DynamicCodeCompileSupporter;
import kr.huni.problem_parser.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SourceCodeTemplate 테스트")
class SourceCodeTemplateTest {

  @Test
  @DisplayName("main 메소드가 존재하는 소스코드는 컴파일이 잘 된다.")
  void main_syntax_fine() throws IOException {
    // given
    String mainSourceCode = SourceCodeTemplate.getMainCode(1000, "A+B");

    // when
    boolean compileWorking = DynamicCodeCompileSupporter.checkCompileWorking(mainSourceCode);

    // then
    Assertions.assertTrue(compileWorking);
  }

  @Test
  @DisplayName("test 메소드가 존재하는 소스코드는 컴파일이 잘 된다.")
  void test_syntax_fine() throws IOException {
    // given
    ArrayList<TestCase> testCases = new ArrayList<>();
    testCases.add(new TestCase("1 2", "3"));

    String testCode = SourceCodeTemplate.getTestCode(testCases);
    testCode += """
            class Main {
              public static void main(String[] args) {
                // do nothing
              }
            }
        """;

    // when
    boolean working = DynamicCodeCompileSupporter.checkCompileWorking(testCode);

    // then
    Assertions.assertTrue(working);
  }

}