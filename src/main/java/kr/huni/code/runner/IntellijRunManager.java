package kr.huni.code.runner;

import java.io.IOException;
import kr.huni.code.os.OperatingSystem;

public class IntellijRunManager {

  private final OperatingSystem operatingSystem;

  public IntellijRunManager() {
    this.operatingSystem = OperatingSystem.getOperatingSystem();
  }

  public void run(String codePath) throws IOException {
    Command command = switch (this.operatingSystem) {
      case WINDOWS -> new Command("where idea", "idea");
      case LINUX, MAC -> new Command("which idea", "idea");
    };

    boolean ideaExist = Runtime.getRuntime().exec(command.existCheckCommand).exitValue() == 0;
    if (ideaExist) {
      Runtime.getRuntime().exec(command.ideaFileName + " " + codePath);
    }
  }

  private record Command(
      String existCheckCommand,
      String ideaFileName
  ) {

  }
}
