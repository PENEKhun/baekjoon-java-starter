package kr.huni.Integration;

import kr.huni.problem_parser.ProblemParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ProblemParser 통합 테스트")
class ProblemParserTests {

  @Test
  @DisplayName("문제 번호가 존재할 경우 ProblemParser 생성자는 아무것도 던지지 않는다.")
  void notThrowing() {
    // given
    int problemNumber = 1000;

    // when & then
    Assertions.assertDoesNotThrow(
        () -> new ProblemParser(problemNumber)
    );
  }


  @Test
  @DisplayName("문제 번호가 존재하지 않을 경우 ProblemParser 생성자는 IAE를 던진다.")
  void invalid_problem_throwing() {
    // given
    int problemNumber = -1;

    // when & then
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new ProblemParser(problemNumber),
        "문제를 찾을 수 없습니다. 올바른 문제 번호를 입력해주세요."
    );
  }


}
