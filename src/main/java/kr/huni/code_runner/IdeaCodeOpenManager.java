package kr.huni.code_runner;

import java.io.IOException;
import kr.huni.os.OperatingSystem;

public class IdeaCodeOpenManager implements CodeOpenManager {

  public void run(String codePath, OperatingSystem os) throws IOException {
    String command = switch (os) {
      case WINDOWS -> "idea.bat";
      case LINUX, MAC -> "idea";
    };

    Runtime.getRuntime().exec(command + " " + codePath);
  }
}
