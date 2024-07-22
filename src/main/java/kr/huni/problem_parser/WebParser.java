package kr.huni.problem_parser;

public interface WebParser {

  /**
   * selector를 이용하여 웹 페이지를 파싱한다.
   *
   * @param selector    파싱할 selector
   * @param includeHtml html 태그를 포함할지 여부
   * @return 파싱된 String[]
   * @implSpec 주어진 selector를 파싱하여 String[]로 반환한다
   */
  String[] parse(String selector, boolean includeHtml);

  /**
   * 파싱시 사용할 문제번호를 입력한다.
   *
   * @param problemNumber 백준 문제 번호
   */
  void setProblemNumber(int problemNumber);
}
