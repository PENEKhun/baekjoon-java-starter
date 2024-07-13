package kr.huni;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class DynamicCodeCompileSupporter {

  public static boolean checkCompileWorking(String dynamicCode, OutputStream outputStream)
      throws IOException {

    // 임시 파일명으로 바껴서 컴파일이 되지 않기 때문에, 클래스 스코프를 제거해 주었음.
    dynamicCode = dynamicCode.replace("public class", "class");
    Path javaFilePath = Files.createTempFile("test", ".java");
    Files.write(javaFilePath, dynamicCode.getBytes(), StandardOpenOption.CREATE);

    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    int compilationResult = compiler.run(null, outputStream, null,
        javaFilePath.toFile().getAbsolutePath());
    return compilationResult == 0;
  }


  public static boolean testRunWell(String mainCode, String testCode, String expectedOutput)
      throws InterruptedException, IOException {
    // 임시 파일명으로 바껴서 컴파일이 되지 않기 때문에, 클래스 스코프를 제거해 주었음.
    mainCode = mainCode.replace("public class", "class");
    testCode = testCode.replace("public class", "class");

    String mergedCode = mergeCode(mainCode, testCode);
    Path tempPath = Files.createDirectories(Path.of("build/tmp"));
    Path mergedFile = Files.createTempFile(tempPath, "main", ".java");
    Files.write(mergedFile, mergedCode.getBytes(), StandardOpenOption.CREATE);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    assert compiler.run(null, outputStream, null, mergedFile.toFile().getAbsolutePath()) == 0 : "컴파일 에러";

    ProcessBuilder processBuilder = new ProcessBuilder(
        "java", "-cp", tempPath.toString(), "TestHelper");
    processBuilder.redirectErrorStream(true);
    Process process = processBuilder.start();

    ByteArrayOutputStream processOutput = new ByteArrayOutputStream();
    process.getInputStream().transferTo(processOutput);
    process.waitFor();

    String output = removeAnsiEscapeCodes(processOutput.toString().stripTrailing());
    System.out.println("Process output: " + output);
    System.out.println("Expected output: " + expectedOutput.stripTrailing());

    return output.contains(expectedOutput.stripTrailing());
  }

  private static String removeAnsiEscapeCodes(String input) {
    return Pattern.compile("\u001B\\[[;\\d]*m").matcher(input).replaceAll("");
  }

  /**
   * Main Code와 Test Code를 합쳐서 반환한다. 합칠때 import 문을 정렬하여 반환한다.
   */
  private static String mergeCode(String mainCode, String testCode) {
    String[] mainLines = mainCode.split("\n");
    String[] testLines = testCode.split("\n");

    StringBuilder mergedCode = new StringBuilder();

    for (String line : testLines) {
      if (line.startsWith("import ")) {
        mergedCode.append(line).append("\n");
      }
    }

    for (String line : mainLines) {
      if (line.startsWith("import ")) {
        mergedCode.append(line).append("\n");
      }
    }

    mergedCode.append("\n");

    for (String line : testLines) {
      if (!line.startsWith("import ")) {
        mergedCode.append(line).append("\n");
      }
    }

    mergedCode.append("\n");

    for (String line : mainLines) {
      if (!line.startsWith("import ")) {
        mergedCode.append(line).append("\n");
      }
    }

    return mergedCode.toString();
  }
}
