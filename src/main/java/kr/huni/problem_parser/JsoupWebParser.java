package kr.huni.problem_parser;

import static kr.huni.problem_parser.BaekjoonProblemParser.PROBLEM_URL;
import static kr.huni.problem_parser.BaekjoonProblemParser.USER_AGENT;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupWebParser implements WebParser {

  private final Document document;

  /**
   * 생성시 문제 번호를 받아 해당 문제의 페이지를 필드에 기록합니다.
   *
   * @param problemNumber 문제 번호
   * @throws IllegalArgumentException 문제를 찾을 수 없을 때
   */
  public JsoupWebParser(int problemNumber) {
    try {
      this.document = Jsoup.connect(PROBLEM_URL + problemNumber)
          .userAgent(USER_AGENT)
          .get();
    } catch (IOException e) {
      throw new IllegalArgumentException("문제를 찾을 수 없습니다. 올바른 문제 번호를 입력해주세요.");
    }
  }

  @Override
  public String[] parse(String selector, boolean includeHtml) {

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

}
