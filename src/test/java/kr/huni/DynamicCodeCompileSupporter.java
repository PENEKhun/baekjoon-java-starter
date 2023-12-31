package kr.huni;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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


  public static void runHelper(String code, OutputStream outputStream, Object o) {
    // 임시 파일명으로 바껴서 컴파일이 되지 않기 때문에, 클래스 스코프를 제거해 주었음.
    code = code.replace("public class", "class");
    try {
      Path javaFilePath = Files.createTempFile("test", ".java");
      Files.write(javaFilePath, code.getBytes(), StandardOpenOption.CREATE);

      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      int compilationResult = compiler.run(null, outputStream, null,
          javaFilePath.toFile().getAbsolutePath());
      if (compilationResult == 0) {
        Runtime.getRuntime().exec("java -cp " + javaFilePath.getParent().toFile().getAbsolutePath()
            + " TestHelper");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
