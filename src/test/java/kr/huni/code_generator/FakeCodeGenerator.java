package kr.huni.code_generator;

import kr.huni.problem_parser.Problem;

public class FakeCodeGenerator implements FileContentGenerator {

  @Override
  public GeneratedCode generateCode(Problem problem) {
    return new GeneratedCode("mainCode", "testCode");
  }

  @Override
  public String generateMarkdown(Problem problem) {
    return """
        # 1. A+B
        """;
  }
}
