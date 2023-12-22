package kr.huni.problem_parser;

import java.util.ArrayList;

public class Problem {

  private final int number;
  private final String title;
  private String directory;
  private ArrayList<TestCase> testCases = new ArrayList<>();

  public Problem(int number, String title) {
    this.number = number;
    this.title = title;
    this.directory = "./p" + number;
  }

  public int getNumber() {
    return number;
  }

  public String getTitle() {
    return title;
  }

  public String getDirectory() {
    return directory;
  }

  public void setTestCases(ArrayList<TestCase> testCases) {
    this.testCases = testCases;
  }

  public ArrayList<TestCase> getTestCases() {
    return testCases;
  }
}
