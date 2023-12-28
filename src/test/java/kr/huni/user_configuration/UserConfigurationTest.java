package kr.huni.user_configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserConfigurationTest {

  @Test
  @DisplayName("defaultConfiguration() 메서드는 기본 설정 값들이 들어있는 Configuration 객체를 반환한다.")
  void defaultConfiguration() {
    // given & when
    UserConfiguration defaultConfiguration = UserConfiguration.defaultConfiguration();

    // then
    Assertions.assertSame(defaultConfiguration.mainCodeTemplate.getDefaultValue(),
        UserConfiguration.defaultConfiguration().mainCodeTemplate.getValue());
    Assertions.assertSame(defaultConfiguration.srcDirPrefix.getDefaultValue(),
        UserConfiguration.defaultConfiguration().srcDirPrefix.getValue());
  }

  @Test
  @DisplayName("merge() 메서드는 인자로 받은 Configuration 객체의 value 값을 병합한다.")
  void merge() {
    // given
    UserConfiguration userConfiguration = UserConfiguration.defaultConfiguration();
    UserConfiguration userConfiguration2 = UserConfiguration.defaultConfiguration();
    userConfiguration.srcDirPrefix.setValue("1");
    userConfiguration2.srcDirPrefix.setValue("2");

    // when
    userConfiguration.merge(userConfiguration2);

    // then
    Assertions.assertSame("2", userConfiguration.srcDirPrefix.getValue());

  }

  @Test
  @DisplayName("설정 객체에서 defaultValue는 직렬화 되지 않는다.")
  void defaultValue_json_ignore() throws JsonProcessingException {
    // given
    UserConfiguration userConfiguration = UserConfiguration.defaultConfiguration();

    // when
    String jsonString = new ObjectMapper().writeValueAsString(userConfiguration);

    // then
    Assertions.assertFalse(jsonString.contains("defaultValue"));
  }
}