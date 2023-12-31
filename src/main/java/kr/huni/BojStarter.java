package kr.huni;

import java.io.IOException;
import kr.huni.code_generator.CodeGenerator;
import kr.huni.code_generator.GeneratedCode;
import kr.huni.code_runner.CodeOpenManager;
import kr.huni.file_generator.JavaSourceCodeFile;
import kr.huni.os.OperatingSystem;
import kr.huni.problem_parser.BaekjoonProblemParser;
import kr.huni.problem_parser.Problem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BojStarter {

  private final CodeOpenManager codeOpenManager;
  private final JavaSourceCodeFile fileUtil;
  private final CodeGenerator codeGenerator;
  private final BaekjoonProblemParser problemParser;

  public void run(final int problemNumber) {
    Problem problem = problemParser.parse(problemNumber);
    GeneratedCode generatedCode = codeGenerator.generate(problem);

    createSrcFile(problem, generatedCode);
    openSourceCodeWithIde(problem);
  }

  private void openSourceCodeWithIde(Problem problem) {
    try {
      codeOpenManager.run(problem.getSourceRootDirectory(), OperatingSystem.getOperatingSystem());
    } catch (IOException e) {
      log.error("""
          [ERROR]
          IntelliJ IDEA의 idea 명령어가 설치되어 있지 않습니다.
          직접 IntelliJ IDEA를 실행해서 프로젝트를 열어주세요.
          생성된 프로젝트 경로 : {}
          %n
          """, problem.getSourceRootDirectory(), e);
    }
  }

  private void createSrcFile(Problem problem, GeneratedCode generatedCode) {
    try {
      fileUtil.write(problem.getSourceRootDirectory(), generatedCode.mainCode(),
          generatedCode.testCode());
    } catch (IOException e) {
      log.error("소스코드 파일 또는 디렉토리 생성에 실패했습니다.", e);
    }
  }
}
