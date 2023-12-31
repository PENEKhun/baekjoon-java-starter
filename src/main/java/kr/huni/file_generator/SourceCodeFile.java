package kr.huni.file_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;
import kr.huni.code_generator.SourceCodeTemplateImpl;

/**
 * 소스코드 파일을 생성하고, 내용을 채워주기 위한 인터페이스
 */
public interface SourceCodeFile {

  /**
   * 각 언어에 맞는 소스코드 파일을 생성하고, 내용을 채웁니다.
   *
   * @param directory  파일을 생성할 폴더
   * @param sourceCode 소스코드 내용
   * @param testCode   테스트 코드 내용
   * @throws IOException 파일 생성 실패
   * @implSpec 해당 메서드안에서 필요한 하위 폴더를 생성하고, {@link #writeToFile(File, String, String)}를 통해 알고리즘을 구현할
   * 소스코드 파일과 테스트 코드 파일을 생성해야합니다.
   */
  void write(String directory, String sourceCode, String testCode) throws IOException;

  /**
   * 파일을 생성하고, 내용을 채웁니다.
   *
   * @param srcDir     소스코드를 저장할 위치가 담긴 객체
   * @param fileName   파일 이름
   * @param sourceCode 파일 내용
   */
  default void writeToFile(File srcDir, String fileName, String sourceCode) {
    File file = new File(srcDir, fileName);
    if (file.exists()) {
      System.out.printf("%s/%s가 이미 존재합니다. 새롭게 덮어 씌우시겠습니까? (y, n): ", srcDir.getAbsoluteFile(),
          fileName);
      Scanner reader = new Scanner(System.in);
      String answer = reader.nextLine();
      if (answer.equals("y")) {
        System.out.println("파일을 덮어 씌웁니다.");
      } else {
        System.out.println("파일을 덮어 씌우지 않고 진행합니다.");
        return;
      }
    }

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(sourceCode);
    } catch (IOException e) {
      System.out.println("파일 생성 실패. 프로그램을 종료합니다.");
      throw new RuntimeException(e);
    }
  }

  /**
   * Resources 경로에서 파일을 읽어서 String 반환합니다.
   *
   * @param filePath 파일 경로
   * @return 파일 내용
   * @throws IOException 파일 읽기 실패
   */
  static String readFileFromResource(String filePath) throws IOException {
    StringBuilder sourceCode = new StringBuilder();
    try (InputStream inputStream = SourceCodeTemplateImpl.class.getClassLoader()
        .getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(
            Objects.requireNonNull(inputStream));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        sourceCode.append(line).append("\n");
      }

    }
    return sourceCode.toString();
  }

}
