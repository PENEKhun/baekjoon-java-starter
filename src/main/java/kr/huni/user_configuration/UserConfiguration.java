package kr.huni.user_configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 사용자 설정을 담는 클래스입니다.
 */
@Slf4j
public class UserConfiguration {

  public final UserConfigurationField srcDirPrefix =
      UserConfigurationField.builder()
          .description("""
              소스코드를 저장할 디렉토리의 접두사입니다. 접두사를 입력하면 해당 디렉토리에 소스코드가 저장됩니다.
              예를 들어, 문제번호가 1000이고 srcDirPrefix가 'problem'이라면 problem1000 디렉토리에 소스코드가 저장됩니다.
              """)
          .defaultValue("p")
          .build();
  public final UserConfigurationField srcCommentFormat =
      UserConfigurationField.builder()
          .description("""
              Main.java 소스코드의 상단에 주석으로 추가될 내용을 지정합니다. {{number}}는 문제번호, {{title}}은 문제제목으로 치환됩니다.
              """)
          .defaultValue("""
              /*
                  BAEKJOON {{number}} {{title}}
                  https://www.acmicpc.net/problem/{{number}}
              */
              """)
          .build();

  public static UserConfiguration defaultConfiguration() {
    return new UserConfiguration();
  }

  /**
   * Configuration 객체를 JSON 형태로 출력합니다.
   */
  void printValue() {
    log.info("""
        설정 정보를 출력합니다.
        srcDirPrefix : {}
        srcCommentFormat : {}
        """, srcDirPrefix.getValue(), srcCommentFormat.getValue());
  }

  void printHelp() {
    log.info("""
        설정 값 설명:
        srcDirPrefix : {}
        srcCommentFormat : {}
        """, srcDirPrefix.toString(), srcCommentFormat.toString());
  }

  protected UserConfiguration() {
  }

  public void merge(UserConfiguration userConfiguration) {
    this.srcDirPrefix.setValue(userConfiguration.srcDirPrefix.getValue());
    this.srcCommentFormat.setValue(userConfiguration.srcCommentFormat.getValue());
  }
}
