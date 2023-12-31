package kr.huni.code_generator;

import java.io.IOException;
import java.util.List;
import kr.huni.file_generator.SourceCodeFile;
import kr.huni.problem_parser.TestCase;
import kr.huni.user_configuration.UserConfigurationField;
import kr.huni.user_configuration.UserConfigurationLoader;

public class SourceCodeTemplateImpl implements SourceCodeTemplate {

  public String getMainCode(int number, String title) {
    String template = DEFAULT_MAIN_CODE_TEMPLATE;
    UserConfigurationField mainCodeTemplate =
        UserConfigurationLoader.getInstance().mainCodeTemplate;
    boolean useCustomTemplate = mainCodeTemplate.getValue() != null;

    if (useCustomTemplate) {
      template = mainCodeTemplate.getValue();
    }

    return template
        .replace(REPLACED_NUMBER, String.valueOf(number))
        .replace(REPLACED_TITLE, title);
  }

  public String getTestCode(List<TestCase> testCases) throws IOException {
    if (testCases.isEmpty()) {
      return SourceCodeFile.readFileFromResource(NO_TEST_JAVA_FILE);
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

    String template = SourceCodeFile.readFileFromResource(TEST_JAVA_FILE);
    return template.replace(REPLACED_TEST_CASES, testCaseCode.toString());
  }
}
