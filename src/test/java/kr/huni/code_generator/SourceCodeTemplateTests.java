package kr.huni.code_generator;

import java.io.IOException;
import java.util.ArrayList;
import kr.huni.DynamicCodeCompileSupporter;
import kr.huni.code.generator.SourceCodeTemplate;
import kr.huni.problem_parser.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SourceCodeTemplate 테스트")
class SourceCodeTemplateTests {

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
  @DisplayName("테스트케이스가 있을때 생성된 TestHelper.java는 컴파일이 잘 된다.")
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

  @Test
  @DisplayName("테스트 케이스가 없을때 생성된 TestHelper.java도 컴파일이 잘 된다.")
  void test_syntax_fine_with_no_case() throws IOException {
    // given
    String testCode = SourceCodeTemplate.getTestCode(new ArrayList<>());
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

  @Test
  @DisplayName("테스트 케이스가 없을 때 NoTestHelper.java 내용이 잘 로드된다.")
  void noTestHelper_load_well() throws IOException {
    // given
    String noTestHelperCode = SourceCodeTemplate.getTestCode(new ArrayList<>());

    // when & then
    Assertions.assertEquals(
        """
            public class TestHelper {
                        
              public static void main() {
                System.out.println("해당 문제는 테스트 케이스가 없습니다.");
              }
            }
            """,
        noTestHelperCode
    );
  }

  @Test
  @DisplayName("Main.java 코드 템플릿에 치환 문자가 존재 한다.")
  void main_replace_text_exist() throws IOException {
    // given
    String codePath = SourceCodeTemplate.MAIN_JAVA_FILE;
    String replacedNumberSymbol = SourceCodeTemplate.REPLACED_NUMBER;
    String replacedTitleSymbol = SourceCodeTemplate.REPLACED_TITLE;

    // when
    String sourceCode = SourceCodeTemplate.readFile(codePath);

    // then
    Assertions.assertTrue(sourceCode.contains(replacedNumberSymbol));
    Assertions.assertTrue(sourceCode.contains(replacedTitleSymbol));
  }

  @Test
  @DisplayName("TestHelper.java 코드 템플릿에 치환 문자가 존재 한다.")
  void test_replace_text_exist() throws IOException {
    // given
    String codePath = SourceCodeTemplate.TEST_JAVA_FILE;
    String replacedTestCaseSymbol = SourceCodeTemplate.REPLACED_TEST_CASES;

    // when
    String sourceCode = SourceCodeTemplate.readFile(codePath);

    // then
    Assertions.assertTrue(sourceCode.contains(replacedTestCaseSymbol));
  }

}