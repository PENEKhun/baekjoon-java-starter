package kr.huni.problem_parser;

import java.util.List;
import lombok.Getter;

@Getter
public class Problem {

  private final int number;
  private final String title;
  private final String directory;
  private final List<TestCase> testCases;

  public Problem(int number, String title, List<TestCase> testCases) {
    this.number = number;
    this.title = title;
    this.directory = "./p" + number;
    this.testCases = testCases;
  }

}
