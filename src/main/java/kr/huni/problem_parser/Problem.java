package kr.huni.problem_parser;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Problem {

  @Getter
  private final int number;
  @Getter
  private final String title;
  @Getter
  private final String directory;
  @Setter
  private ArrayList<TestCase> testCases = new ArrayList<>();

  public Problem(int number, String title) {
    this.number = number;
    this.title = title;
    this.directory = "./p" + number;
  }

  public List<TestCase> getTestCases() {
    return testCases;
  }
}
