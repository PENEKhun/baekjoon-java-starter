package kr.huni.code_generator;

import static kr.huni.problem_parser.BaekjoonProblemParser.PROBLEM_URL;

import java.io.IOException;
import java.util.List;
import kr.huni.file_generator.SourceCodeFile;
import kr.huni.problem_parser.TestCase;
import kr.huni.user_configuration.UserConfigurationField;
import kr.huni.user_configuration.UserConfigurationLoader;

public class KotlinTemplate implements FileContentTemplate {

  public static final String DEFAULT_KOTLIN_MAIN_CODE_TEMPLATE = """
      import java.util.Scanner
            
      /*
          BAEKJOON {{number}}번 {{title}}
          https://www.acmicpc.net/problem/{{number}}
      */
            
      fun main(args: Array<String>) {
          val scanner = Scanner(System.`in`)
          // 코드를 작성하세요.
      }
      """;

  @Override
  public String getMainCode(int number, String title) {
    String template = DEFAULT_KOTLIN_MAIN_CODE_TEMPLATE;
    UserConfigurationField mainCodeTemplate =
        UserConfigurationLoader.getInstance().mainCodeTemplate;
    boolean useCustomTemplate = mainCodeTemplate.getValue() != null
        && !mainCodeTemplate.getValue().equals(DEFAULT_MAIN_CODE_TEMPLATE);

    if (useCustomTemplate) {
      template = mainCodeTemplate.getValue();
    }

    return template
        .replace(REPLACED_NUMBER, String.valueOf(number))
        .replace(REPLACED_TITLE, title);
  }

  @Override
  public String getTestCode(List<TestCase> testCases, Double timeLimit) throws IOException {
    // Kotlin 프로젝트라도 TestHelper는 Java로 생성 (기존 로직 재사용)
    return new JavaTemplate().getTestCode(testCases, timeLimit);
  }

  @Override
  public String getMarkdownContent(int number, String title, String description, Double timeLimit,
      int memoryLimit) {
    String template = DEFAULT_MARKDOWN_TEMPLATE;
    UserConfigurationField markdownTemplate =
        UserConfigurationLoader.getInstance().markdownTemplate;
    boolean useCustomTemplate = markdownTemplate.getValue() != null
        && !markdownTemplate.getValue().equals(DEFAULT_MARKDOWN_TEMPLATE);

    if (useCustomTemplate) {
      template = markdownTemplate.getValue();
    }

    return template
        .replace(REPLACED_NUMBER, String.valueOf(number))
        .replace(REPLACED_TITLE, title)
        .replace(REPLACED_DESCRIPTION, description)
        .replace(REPLACED_URL, PROBLEM_URL + number)
        .replace(REPLACED_TIME_LIMIT, String.valueOf(timeLimit))
        .replace(REPLACED_MEMORY_LIMIT, String.valueOf(memoryLimit));
  }
}
