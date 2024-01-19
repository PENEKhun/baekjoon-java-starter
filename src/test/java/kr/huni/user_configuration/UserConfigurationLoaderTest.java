package kr.huni.user_configuration;

import static kr.huni.user_configuration.UserConfigurationLoader.CONFIGURATION_FILE_NAME;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kr.huni.TestCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserConfigurationLoaderTest {

  @BeforeEach
  void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
    TestCleaner.clean();
  }

  @Test
  @DisplayName("ConfigurationLoader 인스턴스를 두 번 생성하면 같은 인스턴스를 반환한다.")
  void singleton_test() {
    // given
    UserConfiguration firstInstance = UserConfigurationLoader.getInstance();
    UserConfiguration secondInstance = UserConfigurationLoader.getInstance();

    // when & then
    assertSame(firstInstance, secondInstance);
  }

  @Test
  @DisplayName("getInstance 호출시 설정 파일에 없는 필드는 기본 값으로 덮어 씌워진다.")
  void config_overwrite_work() throws IOException {
    // given
    Map<String, Object> configMap = new HashMap<>();
    configMap.put("srcDirPrefix", Collections.singletonMap("value", "p"));
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(new File(CONFIGURATION_FILE_NAME), configMap);

    // when
    UserConfigurationLoader.getInstance();

    // then
    File file = new File(CONFIGURATION_FILE_NAME);
    String content = new String(Files.readAllBytes(file.toPath()));
    assertTrue(content.contains("mainCodeTemplate"));
  }
}