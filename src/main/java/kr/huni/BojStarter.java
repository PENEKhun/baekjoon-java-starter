package kr.huni;

import java.io.IOException;
import kr.huni.code.generator.CodeGenerator;
import kr.huni.code.runner.IntellijRunManager;
import kr.huni.problem_parser.ProblemParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BojStarter {

  private final int problemNumber;

  public BojStarter(int problemNumber) {
    this.problemNumber = problemNumber;
  }

  public void start() {
    ProblemParser parser = new ProblemParser(this.problemNumber);
    CodeGenerator generator = new CodeGenerator(parser.getProblem());
    generator.generate();
    IntellijRunManager runManager = new IntellijRunManager();
    try {
      runManager.run(parser.getProblem().getDirectory());
    } catch (IOException e) {
      log.error("""
          [ERROR]
          IntelliJ IDEA의 idea 명령어가 설치되어 있지 않습니다.
          직접 IntelliJ IDEA를 실행해서 프로젝트를 열어주세요.
          생성된 프로젝트 경로 : {}
          %n
          """, parser.getProblem().getDirectory(), e);
    }
  }
}
