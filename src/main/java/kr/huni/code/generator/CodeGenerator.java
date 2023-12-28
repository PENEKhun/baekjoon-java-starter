package kr.huni.code.generator;

import java.io.IOException;
import kr.huni.problem_parser.Problem;
import kr.huni.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeGenerator {

  private final Problem problem;

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
    log.info("소스코드 생성 완료");
  }
}
