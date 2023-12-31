package kr.huni;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainTests {

  private Main main;

  @BeforeEach
  void setUp() {
    main = new Main();
  }

  @Test
  @DisplayName("잘못된 입력이 들어오면 예외를 던진다.")
  void testMain() {
    String input = "-100";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    PrintStream out = new PrintStream(System.out);
    System.setOut(out);

    try {
      main.main(new String[]{});
    } catch (Exception e) {
      Assertions.assertTrue(e instanceof IllegalArgumentException);
    }
  }
}

