package kr.huni.code_generator;

import java.io.IOException;
import java.util.List;
import kr.huni.problem_parser.TestCase;

public interface SourceCodeTemplate {

  String TEST_JAVA_FILE = "code_sample/TestHelper.java";
  String NO_TEST_JAVA_FILE = "code_sample/NoTestHelper.java";
  String REPLACED_NUMBER = "{{number}}";
  String REPLACED_TITLE = "{{title}}";
  String REPLACED_TEST_CASES = "// {{test_case}}";
  String DEFAULT_MAIN_CODE_TEMPLATE = """
      import java.util.Scanner;
            
      /*
          BAEKJOON {{number}} {{title}}
          https://www.acmicpc.net/problem/{{number}}
      */
            
      public class Main {
            
        public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          // 코드를 작성하세요.
        }
      }
      """;

  /**
   * Main.java 소스코드 문자를 생성하고 반환합니다.
   *
   * @param number 문제 번호
   * @param title  문제 제목
   * @return Main.java 파일의 내용
   */
  String getMainCode(int number, String title);

  /**
   * TestHelper.java 소스코드 문자를 생성하고 반환합니다.
   *
   * @param testCases 테스트 케이스 목록
   * @return TestHelper.java 파일의 내용
   */
  String getTestCode(List<TestCase> testCases) throws IOException;

}
