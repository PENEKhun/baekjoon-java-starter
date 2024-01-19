package kr.huni.code_generator;

import kr.huni.problem_parser.Problem;

public interface FileContentGenerator {

  GeneratedCode generateCode(Problem problem);

  String generateMarkdown(Problem problem);
}
