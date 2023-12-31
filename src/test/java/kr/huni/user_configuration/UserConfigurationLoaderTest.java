package kr.huni.user_configuration;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserConfigurationLoaderTest {

  @Test
  @DisplayName("ConfigurationLoader 인스턴스를 두 번 생성하면 같은 인스턴스를 반환한다.")
  void singleton_test() {
    // given
    UserConfiguration firstInstance = UserConfigurationLoader.getInstance();
    UserConfiguration secondInstance = UserConfigurationLoader.getInstance();

    // when & then
    assertSame(firstInstance, secondInstance);
  }
}