import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 이 테스트코드는 <a href="https://github.com/PENEKhun/Baekjoon-java-starter">Baekjoon-java-starter </a>를 사용하여
 * 생성되었습니다.
 *
 * @Author : PENEKhun
 */
public class TestHelper {

  private static final HashMap<Field, Object> initialStates = new HashMap<>();

  public static void main(String[] args) {
    captureInitialState();

    TestCase[] testCases = new TestCase[] {
        // {{test_case}}
    };

    int passedCases = 0;

    for (int i = 0; i < testCases.length; i++) {
      resetToInitialState();
      TestCase testCase = testCases[i];

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      PrintStream printStream = new PrintStream(outputStream);
      PrintStream printOut = System.out;
      System.setOut(printStream);
      System.setIn(new ByteArrayInputStream(testCase.input.getBytes()));

      try {
        Main.main(new String[0]);
      } catch (Exception e) {
        System.setOut(printOut);
        printFail(i + 1, testCase, "Exception 발생");
        e.printStackTrace();
      }

      String output = outputStream.toString();
      System.setOut(printOut);
      if (output.equals(testCase.expectedOutput)) {
        passedCases++;
        continue;
      } else {
        printFail(i + 1, testCase,
            red("""
                [실제 값]
                %s
                """.formatted(output)));
      }
    }

    System.out.println("테스트 완료 (" + passedCases + " / " + testCases.length + ")");
    if (passedCases == testCases.length) {
      System.out.println("주어진 케이스에 대해 잘 동작하고 있습니다.");
    }
  }

  private static void captureInitialState() {
    try {
      Class<?> clazz = Main.class;
      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields) {
        if (Modifier.isStatic(field.getModifiers())) {
          field.setAccessible(true);
          initialStates.put(field, field.get(null));
        }
      }
    } catch (Exception e) {
      System.out.println(red("Main 클래스에 접근할 수 없습니다."));
    }
  }

  private static void resetToInitialState() {
    try {
      for (Map.Entry<Field, Object> entry : initialStates.entrySet()) {
        Field field = entry.getKey();
        field.setAccessible(true);
        Object value = entry.getValue();
        if (value instanceof Collection) {
          ((Collection<?>) value).clear();
        } else if (value instanceof Map) {
          ((Map<?, ?>) value).clear();
        } else if (value instanceof StringBuilder) {
          ((StringBuilder) value).setLength(0);
        } else {
          field.set(null, value);
        }
      }
    } catch (IllegalAccessException e) {
      System.out.println(red("Main 클래스에 접근할 수 없습니다."));
    }
  }

  public static void printFail(int caseNumber, TestCase testCase, String message) {
    System.out.printf("""
        ====== %s ======
        [입력 값]
        %s
        [기대 값]
        %s
        """, red(caseNumber + " 번째 케이스 실패"), testCase.input, testCase.expectedOutput);

    System.out.println(message);
  }

  public static String red(String message) {
    return "\u001B[31m%s\u001B[0m".formatted(message);
  }

  public static class TestCase {

    public String input;
    public String expectedOutput;

    public TestCase(String input, String expectedOutput) {
      this.input = input;
      this.expectedOutput = expectedOutput;
    }
  }
}