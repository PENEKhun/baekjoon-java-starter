package kr.huni.user_configuration;

import static kr.huni.user_configuration.UserConfiguration.defaultConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자 설정 파일을 읽어오는 클래스입니다. 설정 파일이 존재하지 않으면 기본 설정 파일을 생성합니다.
 */
@Slf4j
public class UserConfigurationLoader {

  public static final String CONFIGURATION_FILE_NAME = "config.json";

  private static UserConfiguration config;


  public static UserConfiguration getInstance() {
    if (config == null) {
      config = loadProperties();
    }
    return config;
  }

  private static UserConfiguration loadProperties() {
    UserConfiguration configuration = defaultConfiguration();

    try {
      File configFile = new File(CONFIGURATION_FILE_NAME);

      if (configFile.exists()) {
        log.info("설정 파일을 읽어옵니다.");

        ObjectMapper objectMapper = new ObjectMapper();
        // read only exist field
        configuration = objectMapper.readerForUpdating(configuration).readValue(configFile);

        // and rewrite all fields
        String jsonString = objectMapper.writeValueAsString(configuration);
        writeStringToFile(jsonString);
      } else {
        log.info("설정 파일이 존재하지 않습니다. 설정 파일을 새로 생성합니다.");
        createDefaultConfigurationFile();
      }
    } catch (IOException e) {
      log.error("설정 파일을 읽는데 실패했습니다. 기본 설정 값으로 진행합니다.");
      log.error(e.getMessage());
    }

    configuration.printValue();
    return configuration;
  }

  private static void createDefaultConfigurationFile() {
    File file = new File(CONFIGURATION_FILE_NAME);
    try {
      file.createNewFile();
      if (file.exists()) {
        log.info("설정 파일을 새로 생성했습니다.");

        UserConfiguration configuration = defaultConfiguration();
        String jsonString = new ObjectMapper().writeValueAsString(configuration);

        writeStringToFile(jsonString);
        return;
      }
      throw new IOException("파일 생성 오류");
    } catch (IOException e) {
      log.error("설정 파일 생성에 실패했습니다. (입출력 오류)");
    }
  }

  private static void writeStringToFile(String content) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(
        UserConfigurationLoader.CONFIGURATION_FILE_NAME))) {
      writer.write(content);
      log.info("JSON 문자열을 파일에 성공적으로 썼습니다.");
    } catch (IOException e) {
      log.error("파일에 JSON 문자열을 쓰는 도중 오류가 발생했습니다.");
      throw e;
    }
  }
}
