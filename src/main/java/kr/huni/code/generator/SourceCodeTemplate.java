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
  public static final String NO_TEST_JAVA_FILE = "code_sample/NoTestHelper.java";
  public static final String REPLACED_NUMBER = "{{number}}";
  public static final String REPLACED_TITLE = "{{title}}";
  public static final String REPLACED_TEST_CASES = "// {{test_case}}";

  public static String readFile(String filePath) throws IOException {
    StringBuilder sourceCode = new StringBuilder();
    try (InputStream inputStream = SourceCodeTemplate.class.getClassLoader()
        .getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(
            Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        sourceCode.append(line).append("\n");
      }

    }
    return sourceCode.toString();
  }

  public static String getMainCode(int number, String title) throws IOException {
    String template = readFile(MAIN_JAVA_FILE);
    return template.replace(REPLACED_NUMBER, String.valueOf(number))
        .replace(REPLACED_TITLE, title);
  }

  public static String getTestCode(List<TestCase> testCases) throws IOException {
    if (testCases.isEmpty()) {
      return readFile(NO_TEST_JAVA_FILE);
    }

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

    String template = readFile(TEST_JAVA_FILE);
    return template.replace(REPLACED_TEST_CASES, testCaseCode.toString());
  }
}
