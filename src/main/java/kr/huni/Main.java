package kr.huni;

import java.util.Scanner;
import kr.huni.code_generator.JavaCodeGenerator;
import kr.huni.code_runner.IdeaCodeOpenManager;
import kr.huni.file_generator.JavaSourceCodeFile;
import kr.huni.problem_parser.BaekjoonProblemParser;
import kr.huni.problem_parser.JsoupWebParser;
import kr.huni.user_configuration.UserConfigurationLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Main {

  public static void main(String[] args) throws IllegalArgumentException {
    UserConfigurationLoader.getInstance();

    Scanner scanner = new Scanner(System.in);
    log.info("백준 문제 번호를 입력해 주세요: ");
    int problemNumber = scanner.nextInt();

    BojStarter bojStarter = new BojStarter(
        new IdeaCodeOpenManager(),
        new JavaSourceCodeFile(),
        new JavaCodeGenerator(),
        new BaekjoonProblemParser(new JsoupWebParser())
    );
    bojStarter.run(problemNumber);
    scanner.close();
  }

}