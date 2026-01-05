package kr.huni;

import java.io.IOException;
import kr.huni.code_generator.FileContentGenerator;
import kr.huni.code_generator.GeneratedCode;
import kr.huni.code_runner.CodeOpenManager;
import kr.huni.file_generator.SourceCodeFile;
import kr.huni.os.OperatingSystem;
import kr.huni.problem_parser.BaekjoonProblemParser;
import kr.huni.problem_parser.Problem;
import kr.huni.user_configuration.UserConfigurationLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BojStarter {

  private final CodeOpenManager codeOpenManager;
  private final SourceCodeFile fileUtil;
  private final FileContentGenerator codeGenerator;
  private final BaekjoonProblemParser problemParser;

  public void run(int problemNumber) {
    Problem problem = problemParser.parse(problemNumber);
    if (!problem.isExist()) {
      log.error("문제를 찾을 수 없습니다.");
      return;
    }
    GeneratedCode generatedCode = codeGenerator.generateCode(problem);

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
          상세 오류 :
          """, problem.getSourceRootDirectory(), e);
    }
  }

  private void createSrcFile(Problem problem, GeneratedCode generatedCode) {
    try {
      fileUtil.write(problem.getSourceRootDirectory(), generatedCode.mainCode(),
          generatedCode.testCode(), codeGenerator.generateMarkdown(problem),
          UserConfigurationLoader.getInstance().enableReadme.getValue().equals("true"));
    } catch (IOException e) {
      log.error("소스코드 파일 또는 디렉토리 생성에 실패했습니다.", e);
    }
  }
}
