package kr.huni.code_runner;

import java.io.IOException;
import kr.huni.os.OperatingSystem;

public interface CodeOpenManager {

  /**
   * @param codePath 소스코드의 경로
   * @throws IOException 실행 실패
   * @implSpec 주어진 codePath를 IDE로 열어준다
   */
  void run(String codePath, OperatingSystem operatingSystem)
      throws IOException;

}
