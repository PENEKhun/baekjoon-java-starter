package kr.huni.Integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.NoSuchElementException;
import kr.huni.Main;
import kr.huni.user_configuration.UserConfigurationLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTests {

  @BeforeEach
  void setUp() {
    // 설정 파일 삭제
    File configFile = new File(UserConfigurationLoader.CONFIGURATION_FILE_NAME);
    if (configFile.exists()) {
      configFile.delete();
    }
  }

  @Test
  @DisplayName("프로그램 실행시 바로 설정 파일이 생성된다.")
  void auto_configuration() {
    // given
    String configFileName = UserConfigurationLoader.CONFIGURATION_FILE_NAME;

    // when
    try {
      Main.main(new String[]{});
    } catch (NoSuchElementException e) {
      // ignore
    }

    // then
    assertTrue(new File(configFileName).exists());
  }

}
