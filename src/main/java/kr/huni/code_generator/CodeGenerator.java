package kr.huni.code_generator;

import kr.huni.problem_parser.Problem;

public interface CodeGenerator {

  GeneratedCode generate(Problem problem);
}
