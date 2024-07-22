package kr.huni.problem_parser;

import static kr.huni.problem_parser.BaekjoonProblemParser.PROBLEM_URL;
import static kr.huni.problem_parser.BaekjoonProblemParser.USER_AGENT;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupWebParser implements WebParser {

  private Document document;
  private Integer problemNumber;

  @Override
  public String[] parse(String selector, boolean includeHtml) {
    if (this.problemNumber == null || this.problemNumber < 0) {
      throw new IllegalArgumentException("문제번호를 제대로 설정해주세요.");
    }

    if (this.document == null) {
      try {
        this.document = Jsoup.connect(PROBLEM_URL + problemNumber)
            .userAgent(USER_AGENT)
            .get();
      } catch (IOException e) {
        throw new IllegalArgumentException("문제를 찾을 수 없습니다. 올바른 문제 번호를 입력해주세요.");
      }
    }

    Elements result = this.document.select(selector);
    String[] arr = new String[result.size()];
    for (int i = 0; i < result.size(); i++) {
      if (includeHtml) {
        arr[i] = result.get(i).html();
      } else {
        arr[i] = result.get(i).text();
      }
    }

    return arr;
  }

  @Override
  public void setProblemNumber(int problemNumber) {
    if (problemNumber < 0) {
      throw new IllegalArgumentException("문제번호는 0보다 커야합니다.");
    }
    this.problemNumber = problemNumber;
  }
}
