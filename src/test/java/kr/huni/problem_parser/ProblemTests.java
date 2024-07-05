package kr.huni.problem_parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.huni.user_configuration.UserConfigurationLoader;

@DisplayName("Problem 유닛 테스트")
class ProblemTests {

  @Test
  @DisplayName("Problem을 생성하면 directory 문자열 앞에 srcDirPrefix 값이 붙는다.")
  void problem_dir_prefix_test() {
    // given & when
    int problemId = 1000;
    Problem problem = new Problem(problemId, "A+B", "두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.", 2, 128,
        null);
    String srcDirPrefix = UserConfigurationLoader.getInstance().srcDirPrefix.getValue();
    String expected = srcDirPrefix + problemId;

    // then
    Assertions.assertEquals(expected, problem.getSourceRootDirectory());
  }
}