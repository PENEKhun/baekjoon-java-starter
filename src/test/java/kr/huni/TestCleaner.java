package kr.huni;

import static kr.huni.user_configuration.UserConfigurationLoader.CONFIGURATION_FILE_NAME;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import kr.huni.user_configuration.UserConfigurationLoader;

public class TestCleaner {

  static public void clean() throws IOException, NoSuchFieldException, IllegalAccessException {
    System.out.println("Clean up...");

    // 설정 파일 삭제
    clearConfigurationFile();

    // 생성된 파일 삭제
    deleteDirectoryRecursively(Path.of("p1000"));
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

  private static void deleteDirectoryRecursively(Path path) throws IOException {
    if (Files.exists(path)) {
      Files.walkFileTree(path, new SimpleFileVisitor<>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          Files.delete(file);
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
          Files.delete(dir);
          return FileVisitResult.CONTINUE;
        }
      });
    }
  }
}
