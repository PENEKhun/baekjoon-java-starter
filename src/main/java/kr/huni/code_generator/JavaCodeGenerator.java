package kr.huni.code_generator;

import java.io.IOException;
import kr.huni.problem_parser.Problem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaCodeGenerator implements FileContentGenerator {

  @Override
  public GeneratedCode generateCode(Problem problem) {
    if (problem.getTestCases() == null) {
      throw new IllegalArgumentException("테스트는 null이 될 수 없습니다.");
    }

    try {
      JavaTemplate sourceCodeTemplate = new JavaTemplate();
      String mainCode = sourceCodeTemplate.getMainCode(problem.getNumber(), problem.getTitle());
      String testCode = sourceCodeTemplate.getTestCode(problem.getTestCases(), problem.getTimeLimit());

      return new GeneratedCode(mainCode, testCode);
    } catch (IOException e) {
      log.error("샘플 코드 생성에 실패했습니다.", e);
      return new GeneratedCode("", "");
    }
  }

  @Override
  public String generateMarkdown(Problem problem) {
    JavaTemplate markdownTemplate = new JavaTemplate();
    return markdownTemplate.getMarkdownContent(problem.getNumber(), problem.getTitle(),
        problem.getDescription(), problem.getTimeLimit(), problem.getMemoryLimit());
  }

}
