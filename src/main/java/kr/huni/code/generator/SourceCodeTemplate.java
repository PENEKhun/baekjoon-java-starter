package kr.huni.code.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import kr.huni.problem_parser.TestCase;

public class SourceCodeTemplate {

  public static final String MAIN_JAVA_FILE = "code_sample/Main.java";
  public static final String TEST_JAVA_FILE = "code_sample/TestHelper.java";
  public static final String REPLACED_NUMBER = "{{number}}";
  public static final String REPLACED_TITLE = "{{title}}";
  public static final String REPLACED_TEST_CASES = "// {{test_case}}";

  public static String getMainCode(int number, String title) {
    StringBuilder sourceCode = new StringBuilder();
    try (InputStream inputStream = SourceCodeTemplate.class.getClassLoader()
        .getResourceAsStream(MAIN_JAVA_FILE);
        InputStreamReader inputStreamReader = new InputStreamReader(
            Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        line = line.replace(REPLACED_NUMBER, String.valueOf(number))
            .replace(REPLACED_TITLE, title);

        // 수정된 라인을 소스 코드에 추가
        sourceCode.append(line).append("\n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    // 수정된 소스 코드 반환
    return sourceCode.toString();
  }


  public static String getTestCode(List<TestCase> testCases) {
    if (testCases.isEmpty()) {
      return """    
          public class TestHelper {
                
            public static void main() {
              System.out.println("해당 문제는 테스트 케이스가 없습니다.");
            }
          }
          """;
    }

    StringBuilder sourceCode = new StringBuilder();

    StringBuilder testCaseCode = new StringBuilder();
    for (TestCase testCase : testCases) {
      testCaseCode.append("""
                    new TestCase(
                    // input
                    \"""
                    %s
                    \""",
                    // output
                    \"""
                    %s
                    \"""),
          """.formatted(testCase.input(), testCase.output()));
    }

    try (InputStream inputStream = SourceCodeTemplate.class.getClassLoader()
        .getResourceAsStream(TEST_JAVA_FILE);
        InputStreamReader inputStreamReader = new InputStreamReader(
            Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        line = line.replace(REPLACED_TEST_CASES, testCaseCode.toString());
        sourceCode.append(line).append("\n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return sourceCode.toString();
  }

}
