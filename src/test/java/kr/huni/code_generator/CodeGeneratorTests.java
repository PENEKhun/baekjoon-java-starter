package kr.huni.code_generator;

import java.util.ArrayList;
import kr.huni.problem_parser.Problem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CodeGenerator 는")
class CodeGeneratorTests {

  @Test
  @DisplayName("Main.java과 TestHelper.java에 사용할 소스코드 문자를 생성한다.")
  void javaCodeGenerator_return_code() {
    // given
    JavaCodeGenerator javaCodeGenerator = new JavaCodeGenerator();
    Problem problem = new Problem(-1, "A+B", new ArrayList<>());

    // when
    GeneratedCode generatedCode = javaCodeGenerator.generate(problem);

    // then
    Assertions.assertAll(
        () -> Assertions.assertNotNull(generatedCode.mainCode()),
        () -> Assertions.assertTrue(
            generatedCode.mainCode().contains("public static void main(String[] args)")),
        () -> Assertions.assertNotNull(generatedCode.testCode())
    );
  }


}
