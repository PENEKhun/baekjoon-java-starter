package kr.huni;

import java.io.IOException;
import java.util.Scanner;
import kr.huni.code_generator.JavaCodeGenerator;
import kr.huni.code_runner.IdeaCodeOpenManager;
import kr.huni.file_generator.JavaSourceCodeFile;
import kr.huni.problem_parser.BaekjoonProblemParser;
import kr.huni.problem_parser.JsoupWebParser;
import kr.huni.user_configuration.UserConfigurationLoader;
import kr.huni.version_checker.GitHubReleaseFetcher;
import kr.huni.version_checker.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import version.CurrentVersion;
import static kr.huni.version_checker.GitHubReleaseFetcher.GITHUB_LATEST_RELEASE_URL;

@Slf4j
@RequiredArgsConstructor
public class Main {

  public static void main(String[] args) throws IllegalArgumentException {
    UserConfigurationLoader.getInstance();

    GitHubReleaseFetcher fetcher = new GitHubReleaseFetcher();
    try {
      Version latestRelease = fetcher.fetchLatestRelease();
      Version currentVersion = new Version(CurrentVersion.getCurrentVersion());
      if (!currentVersion.isMoreRecentThan(latestRelease)) {
        log.warn("최신 버전이 아닙니다. 최신버전 다운로드를 권장합니다.\nURL : {}", GITHUB_LATEST_RELEASE_URL);
        log.warn("현재 버전: {}, 최신 버전: {}", currentVersion, latestRelease);
      } else {
        log.info("Baekjoon Java Starter v{}", CurrentVersion.getCurrentVersion());
      }

    } catch (InterruptedException | IOException e) {
      log.warn("최신 버전을 확인하는데 실패했습니다.");
    }

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
