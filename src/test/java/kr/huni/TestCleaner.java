package kr.huni;

import static kr.huni.user_configuration.UserConfigurationLoader.CONFIGURATION_FILE_NAME;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import kr.huni.user_configuration.UserConfigurationLoader;

public class TestCleaner {

  static public void clean() throws IOException, NoSuchFieldException, IllegalAccessException {
    System.out.println("Clean up...");

    // 설정 파일 삭제
    clearConfigurationFile();

    // 생성된 파일 삭제
    Files.deleteIfExists(Path.of("p1000/src/Main.java"));
    Files.deleteIfExists(Path.of("p1000/src/TestHelper.java"));
    Files.deleteIfExists(Path.of("p1000/src/NoTestHelper.java"));
    Files.deleteIfExists(Path.of("p1000/src"));
    Files.deleteIfExists(Path.of("p1000"));

    assert !new File("p1000").exists();
  }

  private static void clearConfigurationFile()
      throws IOException, NoSuchFieldException, IllegalAccessException {
    File configFile = new File(CONFIGURATION_FILE_NAME);
    if (configFile.exists()) {
      Files.deleteIfExists(Path.of(CONFIGURATION_FILE_NAME));
      assert !new File(CONFIGURATION_FILE_NAME).exists();
    }

    Field instance = UserConfigurationLoader.class.getDeclaredField("config");
    instance.setAccessible(true);
    instance.set(null, null);
  }

}
