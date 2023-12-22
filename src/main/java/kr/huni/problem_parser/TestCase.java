package kr.huni.problem_parser;

public class TestCase {

  private final String input;
  private final String output;

  public TestCase(String input, String output) {
    this.input = input;
    this.output = output;
  }

  public String getInput() {
    return input;
  }

  public String getOutput() {
    return output;
  }

  @Override
  public String toString() {
    return "TestCase{" +
        "input='" + input + '\'' +
        ", output='" + output + '\'' +
        '}';
  }
}
