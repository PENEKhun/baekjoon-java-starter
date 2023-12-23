package kr.huni.problem_parser;

public record TestCase(String input, String output) {

  @Override
  public String toString() {
    return "TestCase{" +
        "input='" + input + '\'' +
        ", output='" + output + '\'' +
        '}';
  }
}
