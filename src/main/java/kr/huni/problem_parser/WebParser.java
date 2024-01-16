package kr.huni.problem_parser;

public interface WebParser {

  /**
   * @param selector    파싱할 selector
   * @param includeHtml html 태그를 포함할지 여부
   * @return 파싱된 String[]
   * @implSpec 주어진 selector를 파싱하여 String[]로 반환한다
   */
  String[] parse(String selector, boolean includeHtml);

}
