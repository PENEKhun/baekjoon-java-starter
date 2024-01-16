package kr.huni.user_configuration;

import kr.huni.code_generator.JavaTemplate;
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
  public final UserConfigurationField mainCodeTemplate =
      UserConfigurationField.builder()
          .description("""
              Main.java 파일의 템플릿입니다. 예악어 {{number}}와 {{title}}을 사용하면 문제번호와 문제제목으로 자동 치환됩니다.
              """)
          .defaultValue(JavaTemplate.DEFAULT_MAIN_CODE_TEMPLATE)
          .build();
  public final UserConfigurationField markdownTemplate =
      UserConfigurationField.builder()
          .description("""
              문제 설명을 저장할 마크다운 파일의 템플릿입니다. 예악어 {{title}}, {{problem_number}}, {{description}}, {{source}}를 사용하면 문제제목, 문제번호, 문제설명, 출처로 자동 치환됩니다.
              """)
          .defaultValue(JavaTemplate.DEFAULT_MARKDOWN_TEMPLATE)
          .build();

  public static UserConfiguration defaultConfiguration() {
    return new UserConfiguration();
  }

  /**
   * Configuration 객체를 로그로 출력합니다.
   */
  void printValue() {
    log.info("""
        설정 정보를 출력합니다.
        srcDirPrefix : {}
        srcCommentFormat : {}
        mainCodeTemplate : {}
        """, srcDirPrefix.getValue(), mainCodeTemplate.getValue(), markdownTemplate.getValue());
  }

  protected UserConfiguration() {
  }

  public void merge(UserConfiguration userConfiguration) {
    this.srcDirPrefix.setValue(userConfiguration.srcDirPrefix.getValue());
    this.mainCodeTemplate.setValue(userConfiguration.mainCodeTemplate.getValue());
    this.markdownTemplate.setValue(userConfiguration.markdownTemplate.getValue());
  }
}
