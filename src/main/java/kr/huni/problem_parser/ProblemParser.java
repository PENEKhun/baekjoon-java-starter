package kr.huni.problem_parser;

import java.io.IOException;
import java.util.ArrayList;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ProblemParser {

  private static final String BOJ_URL = "https://www.acmicpc.net/problem/";
  private static final String USER_AGENT =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
          + "(KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";

  private static final String PROBLEM_TITLE_SELECTOR = "span#problem_title";
  private static final String SAMPLE_INPUT_SELECTOR = "pre[id^=sample-input]";
  private static final String SAMPLE_OUTPUT_SELECTOR = "pre[id^=sample-output]";

  @Getter
  private final Problem problem;
  private final Document document;

  /**
   * 백준 문제 파싱을 위한 클래스입니다. 문제번호를 입력받아 문제를 파싱합니다.
   *
   * @param number : 백준 문제번호
   * @throws IllegalArgumentException 존재하지 않은 문제 번호
   */
  public ProblemParser(int number) throws IllegalArgumentException {
    this.document = validProblem(number);

    String title = this.document.select(PROBLEM_TITLE_SELECTOR).text();
    System.out.printf("""
        문제 정보 파싱완료
             제목 : %s
        %n""", title);
    this.problem = new Problem(number, title, parseTestCases());
  }

  /**
   * 문제번호가 유효한지 확인합니다.
   *
   * @param number : 백준 문제번호
   * @return jsoup document
   * @throws IllegalArgumentException 존재하지 않은 문제 번호
   */
  private Document validProblem(int number) throws IllegalArgumentException {
    Document doc = null;
    try {
      doc = Jsoup.connect(BOJ_URL + number)
          .userAgent(USER_AGENT)
          .get();
    } catch (IOException e) {
      throw new IllegalArgumentException("문제를 찾을 수 없습니다. 올바른 문제 번호를 입력해주세요.");
    }

    return doc;
  }

  private ArrayList<TestCase> parseTestCases() {
    final ArrayList<TestCase> testCases = new ArrayList<>();

    Elements inputs = this.document.select(SAMPLE_INPUT_SELECTOR);
    Elements outputs = this.document.select(SAMPLE_OUTPUT_SELECTOR);

    for (int i = 0; i < inputs.size(); i++) {
      testCases.add(new TestCase(inputs.get(i).text(), outputs.get(i).text()));
    }

    return testCases;
  }

}
