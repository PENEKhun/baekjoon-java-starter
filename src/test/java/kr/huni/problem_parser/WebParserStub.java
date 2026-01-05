package kr.huni.problem_parser;

import java.util.HashMap;
import java.util.Map;

public class WebParserStub implements WebParser {

  private int problemNumber;
  private final Map<Integer, Map<String, String[]>> responses = new HashMap<>();

  public WebParserStub() {
    // 기본 데이터 설정 (1000번 문제)
    Map<String, String[]> p1000 = new HashMap<>();
    p1000.put(BaekjoonProblemParser.PROBLEM_TITLE_SELECTOR, new String[]{"A+B"});
    p1000.put(BaekjoonProblemParser.PROBLEM_DESCRIPTION_SELECTOR, new String[]{"두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오."});
    p1000.put(BaekjoonProblemParser.PROBLEM_INFORMATION_SELECTOR, new String[]{"2.0 초", "128 MB", "", "", "", ""});
    p1000.put(BaekjoonProblemParser.PROBLEM_INPUT_SELECTOR, new String[]{"1 2"});
    p1000.put(BaekjoonProblemParser.PROBLEM_OUTPUT_SELECTOR, new String[]{"3"});
    responses.put(1000, p1000);

    // 2438번 문제 (별 찍기 - 1)
    Map<String, String[]> p2438 = new HashMap<>();
    p2438.put(BaekjoonProblemParser.PROBLEM_TITLE_SELECTOR, new String[]{"별 찍기 - 1"});
    p2438.put(BaekjoonProblemParser.PROBLEM_DESCRIPTION_SELECTOR, new String[]{"첫째 줄에는 별 1개, 둘째 줄에는 별 2개, n번째 줄에는 별 n개를 찍는 문제"});
    p2438.put(BaekjoonProblemParser.PROBLEM_INFORMATION_SELECTOR, new String[]{"1.0 초", "128 MB", "", "", "", ""});
    p2438.put(BaekjoonProblemParser.PROBLEM_INPUT_SELECTOR, new String[]{"5"});
    p2438.put(BaekjoonProblemParser.PROBLEM_OUTPUT_SELECTOR, new String[]{"*\n**\n***\n****\n*****"});
    responses.put(2438, p2438);

    // 15686번 문제 (치킨 배달)
    Map<String, String[]> p15686 = new HashMap<>();
    p15686.put(BaekjoonProblemParser.PROBLEM_TITLE_SELECTOR, new String[]{"치킨 배달"});
    p15686.put(BaekjoonProblemParser.PROBLEM_DESCRIPTION_SELECTOR, new String[]{"크기가 N×N인 도시가 있다. 도시는 1×1 크기의 칸으로 나누어져 있다..."});
    p15686.put(BaekjoonProblemParser.PROBLEM_INFORMATION_SELECTOR, new String[]{"1.0 초", "512 MB", "", "", "", ""});
    p15686.put(BaekjoonProblemParser.PROBLEM_INPUT_SELECTOR, new String[]{
        "5 3\n0 0 1 0 0\n0 0 2 0 1\n0 1 2 0 0\n0 0 1 0 0\n0 0 0 0 2",
        "5 2\n0 2 0 1 0\n1 0 1 0 0\n0 0 0 0 0\n2 0 0 1 1\n2 2 0 1 2",
        "5 1\n1 2 0 0 0\n1 2 0 0 0\n1 2 0 0 0\n1 2 0 0 0\n1 2 0 0 0",
        "5 1\n1 2 0 2 1\n1 2 0 2 1\n1 2 0 2 1\n1 2 0 2 1\n1 2 0 2 1"
    });
    p15686.put(BaekjoonProblemParser.PROBLEM_OUTPUT_SELECTOR, new String[]{"5", "10", "11", "32"});
    responses.put(15686, p15686);
  }

  @Override
  public String[] parse(String selector, boolean includeHtml) {
    Map<String, String[]> problemData = responses.get(problemNumber);
    if (problemData == null) {
      throw new IllegalArgumentException("문제를 찾을 수 없습니다. 올바른 문제 번호를 입력해주세요.");
    }
    String[] result = problemData.get(selector);
    if (result == null) {
      return new String[]{"N/A"};
    }
    return result;
  }

  @Override
  public void setProblemNumber(int problemNumber) {
    this.problemNumber = problemNumber;
  }
}
