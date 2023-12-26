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
      return;
    }

    System.out.printf("""
        [ERROR]
        IntelliJ IDEA의 idea 명령어가 설치되어 있지 않습니다.
        직접 IntelliJ IDEA를 실행해서 프로젝트를 열어주세요.
        생성된 프로젝트 경로 : %s
        %n""", codePath);
  }

  private record Command(
      String existCheckCommand,
      String ideaFileName
  ) {

  }
}
