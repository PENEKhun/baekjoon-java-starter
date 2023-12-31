package kr.huni.problem_parser;

import java.util.ArrayList;

/**
 * 백준 문제 파서
 */
public class BaekjoonProblemParser {

  static final String PROBLEM_URL = "https://www.acmicpc.net/problem/";
  static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
      + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
  static final String PROBLEM_TITLE_SELECTOR = "span#problem_title";
  static final String PROBLEM_INPUT_SELECTOR = "pre[id^=sample-input]";
  static final String PROBLEM_OUTPUT_SELECTOR = "pre[id^=sample-output]";
  private final WebParser webParser;

  /**
   * 생성자
   *
   * @param webParser 파싱에 사용할 WebParser
   */
  public BaekjoonProblemParser(WebParser webParser) {
    this.webParser = webParser;
  }

  /**
   * 문제 번호를 받아 해당 문제를 파싱합니다.
   *
   * @param problemNumber 백준 문제 번호
   * @return Problem 객체
   */
  public Problem parse(int problemNumber) {
    String title = webParser.parse(PROBLEM_TITLE_SELECTOR)[0];
    final ArrayList<TestCase> testCases = new ArrayList<>();

    String[] inputs = webParser.parse(PROBLEM_INPUT_SELECTOR);
    String[] outputs = webParser.parse(PROBLEM_OUTPUT_SELECTOR);

    assert inputs.length == outputs.length;
    for (int i = 0; i < inputs.length; i++) {
      testCases.add(new TestCase(inputs[i], outputs[i]));
    }

    return new Problem(problemNumber, title, testCases);
  }

}
