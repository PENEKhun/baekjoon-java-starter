package kr.huni.problem_parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Problem 유닛 테스트")
class ProblemTests {

  @Test
  @DisplayName("Problem을 생성하면 directory 문자열 앞에 ./p가 붙는다.")
  void problem_dir_prefix_test() {
    // given & when
    int problemId = 1000;
    Problem problem = new Problem(problemId, "A+B", null);

    // then
    Assertions.assertEquals("./p%d".formatted(problemId), problem.getDirectory());
  }
}