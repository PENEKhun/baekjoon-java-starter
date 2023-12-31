package kr.huni.Integration;

import kr.huni.problem_parser.JsoupWebParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JsoupWebParser 는")
class ProblemParserTests {

  @Test
  @DisplayName("문제 번호가 존재 할 경우 잘 생성된다.")
  void notThrowing() {
    // given
    int problemNumber = 1000;

    // when & then
    Assertions.assertDoesNotThrow(
        () -> new JsoupWebParser(problemNumber)
    );
  }

  @Test
  @DisplayName("문제 번호가 존재하지 않을 경우 예외를 던진다. = 생성되지 않는다.")
  void invalid_problem_throwing() {
    // given
    int problemNumber = -1;

    // when & then
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new JsoupWebParser(problemNumber),
        "문제를 찾을 수 없습니다. 올바른 문제 번호를 입력해주세요."
    );
  }


}
