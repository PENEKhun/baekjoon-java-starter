package kr.huni.code_runner;

import java.io.IOException;
import kr.huni.os.OperatingSystem;

public class IdeaCodeOpenManager implements CodeOpenManager {

  public void run(String codePath, OperatingSystem os) throws IOException {
    CodeOpenCommand command = switch (os) {
      case WINDOWS -> new CodeOpenCommand("where idea", "idea");
      case LINUX, MAC -> new CodeOpenCommand("which idea", "idea");
    };

    boolean ideaExist = Runtime.getRuntime().exec(command.ideExistedCommand()).exitValue() == 0;
    if (ideaExist) {
      Runtime.getRuntime().exec(command.executeCommand() + " " + codePath);
    }
  }
}
