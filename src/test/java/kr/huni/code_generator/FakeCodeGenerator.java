package kr.huni.code_generator;

import kr.huni.problem_parser.Problem;

public class FakeCodeGenerator implements CodeGenerator {

  @Override
  public GeneratedCode generate(Problem problem) {
    return new GeneratedCode("mainCode", "testCode");
  }
}
