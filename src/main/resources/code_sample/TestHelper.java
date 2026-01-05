import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 이 테스트 코드는 <a href="https://github.com/PENEKhun/Baekjoon-java-starter">Baekjoon-java-starter</a>를 사용하여
 * 생성되었습니다.
 *
 * @author PENEKhun
 */
public class TestHelper {

  private static final Map<Field, byte[]> initialStates = new HashMap<>();
  private static final double timeLimit = {{time_limit}};

  public static void main(String[] args) {
    captureInitialState();

    TestCase[] testCases = new TestCase[] {
        // {{test_case}}
    };

    int passedCases = runTestCases(testCases);

    printSummary(passedCases, testCases.length);
  }

  private static int runTestCases(TestCase[] testCases) {
    int passedCases = 0;

    for (int i = 0; i < testCases.length; i++) {
      resetToInitialState();
      if (runSingleTestCase(testCases[i], i + 1)) {
        passedCases++;
      }
    }

    return passedCases;
  }

  private static boolean runSingleTestCase(TestCase testCase, int caseNumber) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    setupStreams(testCase.input, outputStream);

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<?> future = submitTask(executor);

    boolean testCaseException = false;
    try {
      future.get((long)(timeLimit * 1000), TimeUnit.MILLISECONDS);
    } catch (TimeoutException e) {
      handleException(originalOut, caseNumber, testCase, "시간 초과 발생", e);
      testCaseException = true;
    } catch (InterruptedException | ExecutionException e) {
      handleException(originalOut, caseNumber, testCase, "Main() Exception 발생", e);
      testCaseException = true;
    } finally {
      executor.shutdown();
    }

    if (!testCaseException) {
      return compareOutput(testCase, caseNumber, outputStream.toString(), originalOut);
    }

    return false;
  }

  private static Future<?> submitTask(ExecutorService executor) {
    return executor.submit(() -> {
      try {
        Class<?> mainClass = findMainClass();
        mainClass
          .getMethod("main", String[].class)
          .invoke(null, (Object) new String[0]);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  private static void setupStreams(String input, ByteArrayOutputStream outputStream) {
    System.setOut(new PrintStream(outputStream));
    System.setIn(new ByteArrayInputStream(input.getBytes()));
  }

  private static boolean compareOutput(TestCase testCase, int caseNumber, String output, PrintStream originalOut) {
    System.setOut(originalOut);
    String actualOutput = removeTrailingSpaces(output);
    String expectedOutput = testCase.expectedOutput.stripTrailing();

    if (actualOutput.equals(expectedOutput)) {
      return true;
    } else {
      printFail(caseNumber, testCase, red("[실제 값]\n%s\n\n출력 값이 기대한 값과 다릅니다.").formatted(actualOutput));
      return false;
    }
  }

  private static String removeTrailingSpaces(String input) {
    String[] lines = input.split("\n");
    StringBuilder result = new StringBuilder();

    for (String line : lines) {
      result.append(line.stripTrailing()).append("\n");
    }

    if (!result.isEmpty()) {
      result.setLength(result.length() - 1);
    }

    return result.toString();
  }

  private static void handleException(PrintStream originalOut, int caseNumber, TestCase testCase, String message, Exception e) {
    System.setOut(originalOut);
    printFail(caseNumber, testCase, message);
    e.printStackTrace(originalOut);
  }

  private static void captureInitialState() {
    try {
      Field[] fields = findMainClass().getDeclaredFields();
      for (Field field : fields) {
        if (Modifier.isStatic(field.getModifiers())) {
          field.setAccessible(true);
          Object fieldValue = field.get(null);
          byte[] serializedField = SerializationUtils.serialize(fieldValue);
          initialStates.put(field, serializedField);
        }
      }
    } catch (Exception e) {
      System.out.println(red("Main 클래스에 접근할 수 없습니다."));
    }
  }

  private static void resetToInitialState() {
    try {
      for (Map.Entry<Field, byte[]> entry : initialStates.entrySet()) {
        Field field = entry.getKey();
        if (!Modifier.isFinal(field.getModifiers())) { // final 변수가 아닌 경우에만 초기화
          Object originalState = SerializationUtils.deserialize(entry.getValue());
          field.setAccessible(true);
          field.set(null, originalState);
        }
      }
    } catch (Exception e) {
      System.out.println(red("Main 클래스에 접근할 수 없습니다."));
    }
  }

  private static void printFail(int caseNumber, TestCase testCase, String message) {
    System.out.printf("""
            ====== %s ======
            [입력 값]
            %s
            [기대 값]
            %s
            """, red(caseNumber + " 번째 케이스 실패"), testCase.input, testCase.expectedOutput);
    System.out.println(green(message));
  }

  private static void printSummary(int passedCases, int totalCases) {
    System.out.println("===============");
    System.out.println("테스트 완료 (" + passedCases + " / " + totalCases + ")");
    if (passedCases == totalCases) {
      System.out.println("주어진 케이스에 대해 잘 동작하고 있습니다.");
    }
  }

  private static String red(String message) {
    return String.format("\u001B[31m%s\u001B[0m", message);
  }

  private static String green(String message) {
    return String.format("\u001B[32m%s\u001B[0m", message);
  }

  private static class TestCase {
    public final String input;
    public final String expectedOutput;

    public TestCase(String input, String expectedOutput) {
      this.input = input;
      this.expectedOutput = expectedOutput;
    }
  }

  public static class SerializationUtils {
    public static byte[] serialize(Object obj) throws IOException {
      try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
          ObjectOutputStream oos = new ObjectOutputStream(bos)) {
        oos.writeObject(obj);
        return bos.toByteArray();
      }
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
      try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
          ObjectInputStream ois = new ObjectInputStream(bis)) {
        return ois.readObject();
      }
    }
  }

  private static Class<?> findMainClass() {
    String[] candidates = { "Main", "MainKt" };

    for (String className : candidates) {
      try {
        return Class.forName(className);
      } catch (ClassNotFoundException ignored) {
      }
    }

    throw new RuntimeException("Main 또는 MainKt 클래스를 찾을 수 없습니다.");
  }
}
