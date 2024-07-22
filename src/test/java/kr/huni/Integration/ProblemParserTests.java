package kr.huni.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.huni.problem_parser.JsoupWebParser;

@DisplayName("JsoupWebParser 는")
class ProblemParserTests {

  @Test
  @DisplayName("setProblemNumber 가 잘 동작한다.")
  void test() throws NoSuchFieldException, IllegalAccessException {
    // given
    JsoupWebParser jsoupWebParser = new JsoupWebParser();

    // when
    jsoupWebParser.setProblemNumber(1000);

    // then
    Class<?> clazz = jsoupWebParser.getClass();
    Field field = clazz.getDeclaredField("problemNumber");
    field.setAccessible(true);
    int problemNumber = (int) field.get(jsoupWebParser);
    assertEquals(1000, problemNumber);
  }
}
