package kr.huni.problem_parser;

import java.util.List;
import java.util.Objects;

import kr.huni.user_configuration.UserConfiguration;
import kr.huni.user_configuration.UserConfigurationLoader;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Problem {

  private final int number;
  private final String title;
  private final String description;
  private final String sourceRootDirectory;
  private final int timeLimit;
  private final int memoryLimit;
  private final List<TestCase> testCases;

  @Builder
  public Problem(int number, String title, String description, Integer timeLimit, Integer memoryLimit, List<TestCase> testCases) {
    UserConfiguration configuration = UserConfigurationLoader.getInstance();

    this.number = number;
    this.title = title;
    this.sourceRootDirectory = configuration.srcDirPrefix.getValue() + number;
    this.description = description;
    this.testCases = testCases;
    this.timeLimit = Objects.requireNonNullElse(timeLimit, 1);
    this.memoryLimit = Objects.requireNonNullElse(memoryLimit, 512);
  }

  public boolean isExist() {
    return (this.number != 0 && this.title != null);
  }

}
