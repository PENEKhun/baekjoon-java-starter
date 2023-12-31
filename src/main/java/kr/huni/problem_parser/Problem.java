package kr.huni.problem_parser;

import java.util.List;
import kr.huni.user_configuration.UserConfiguration;
import kr.huni.user_configuration.UserConfigurationLoader;
import lombok.Getter;

@Getter
public class Problem {

  private final int number;
  private final String title;
  private final String sourceRootDirectory;
  private final List<TestCase> testCases;

  public Problem(int number, String title, List<TestCase> testCases) {
    UserConfiguration configuration = UserConfigurationLoader.getInstance();

    this.number = number;
    this.title = title;
    this.sourceRootDirectory = configuration.srcDirPrefix.getValue() + number;
    this.testCases = testCases;
  }

}
