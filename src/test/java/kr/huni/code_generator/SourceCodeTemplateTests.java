package kr.huni.code_generator;

import java.io.IOException;
import java.util.ArrayList;
import kr.huni.DynamicCodeCompileSupporter;
import kr.huni.file_generator.SourceCodeFile;
import kr.huni.problem_parser.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SourceCodeTemplate 에서")
class SourceCodeTemplateTests {

  @Test
  @DisplayName("생성되는 기본 Main.java 기본 템플릿은 문법적으로 오류가 없다.")
  void main_syntax_fine() throws IOException {
    // given
    JavaTemplate sourceCodeTemplate = new JavaTemplate();
    String mainSourceCode = sourceCodeTemplate.getMainCode(1000, "A+B");

    // when
    boolean compileWorking = DynamicCodeCompileSupporter.checkCompileWorking(mainSourceCode, null);

    // then
    Assertions.assertTrue(compileWorking);
  }

  @Test
  @DisplayName("테스트케이스가 있을때 생성된 TestHelper.java는 문법적으로 오류가 없다.")
  void test_syntax_fine() throws IOException {
    // given
    ArrayList<TestCase> testCases = new ArrayList<>();
    testCases.add(new TestCase("1 2", "3"));

    JavaTemplate sourceCodeTemplate = new JavaTemplate();
    String testCode = sourceCodeTemplate.getTestCode(testCases);
    testCode += """
            class Main {
              public static void main(String[] args) {
                // do nothing
              }
            }
        """;

    // when
    boolean working = DynamicCodeCompileSupporter.checkCompileWorking(testCode, null);

    // then
    Assertions.assertTrue(working);
  }

  @Test
  @DisplayName("테스트 케이스가 없을때 생성된 NoTestHelper.java는 문법적으로 오류가 없다.")
  void test_syntax_fine_with_no_case() throws IOException {
    // given
    JavaTemplate sourceCodeTemplate = new JavaTemplate();
    String testCode = sourceCodeTemplate.getTestCode(new ArrayList<>());

    // when
    boolean working = DynamicCodeCompileSupporter.checkCompileWorking(testCode, null);

    // then
    Assertions.assertTrue(working);
  }

  @Test
  @DisplayName("테스트케이스가 없을때 생성된 NoTestHelper.java 는 고정된 코드 문자를 반환한다.")
  void noTestHelper_load_well() throws IOException {
    // given
    JavaTemplate sourceCodeTemplate = new JavaTemplate();
    String noTestHelperCode = sourceCodeTemplate.getTestCode(new ArrayList<>());

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
  @DisplayName("TestHelper.java 코드 템플릿에 치환 문자가 존재 한다.")
  void test_replace_text_exist() throws IOException {
    // given
    String codePath = JavaTemplate.TEST_JAVA_FILE;
    String replacedTestCaseSymbol = JavaTemplate.REPLACED_TEST_CASES;

    // when
    String sourceCode = SourceCodeFile.readFileFromResource(codePath);

    // then
    Assertions.assertTrue(sourceCode.contains(replacedTestCaseSymbol));
  }

}