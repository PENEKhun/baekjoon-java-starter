package kr.huni.user_configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

/**
 * 사용자 설정을 담는 클래스입니다.
 *
 * @see UserConfiguration
 * @see UserConfigurationLoader
 */
public class UserConfigurationField {

  private final String description;
  @JsonIgnore
  private final String defaultValue;
  private String value;

  @Builder
  public UserConfigurationField(String description, String defaultValue) {
    this.description = description;
    this.defaultValue = defaultValue;
    this.value = defaultValue;
  }

  protected UserConfigurationField() {
    this.description = "";
    this.defaultValue = "";
    this.value = "";
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return """
        %s
        기본 설정 값 : %s%n
        """.formatted(this.description, this.defaultValue);
  }
}
