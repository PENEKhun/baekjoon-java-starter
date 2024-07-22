package kr.huni.problem_parser;

public class WebParserStub implements WebParser {

  @Override
  public String[] parse(String selector, boolean includeHtml) {
    return new String[] {"String1", "String2"};
  }

  @Override
  public void setProblemNumber(int problemNumber) {
    // do nothing
  }
}
