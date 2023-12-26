package kr.huni.code.generator;

import java.io.IOException;
import java.util.logging.Logger;
import kr.huni.problem_parser.Problem;
import kr.huni.util.FileUtil;

public class CodeGenerator {

  private final Problem problem;
  private final Logger logger = Logger.getLogger(getClass().getName());

  public CodeGenerator(Problem problem) {
    this.problem = problem;
  }

  public void generate() throws IOException {
    String codeTemplate = SourceCodeTemplate.getMainCode(this.problem.getNumber(),
        this.problem.getTitle());
    String testCodeTemplate = SourceCodeTemplate.getTestCode(this.problem.getTestCases());

    FileUtil fileUtil = new FileUtil();
    fileUtil.createDirectory(this.problem.getDirectory());
    fileUtil.write(codeTemplate, testCodeTemplate);
    logger.info("소스코드 생성 완료");
  }

  public void runIdea() throws IOException {
    String command = "idea " + this.problem.getDirectory();
    Runtime.getRuntime().exec(command);
  }
}
