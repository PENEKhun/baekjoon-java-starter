package kr.huni.code.runner;

import kr.huni.code_runner.CodeOpenManager;
import kr.huni.os.OperatingSystem;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FakeCodeOpen implements CodeOpenManager {

  @Override
  public void run(String codePath, OperatingSystem operatingSystem) {
    assert codePath != null;
    assert operatingSystem != null;
    System.out.println("FakeCodeOpenManager.run()");
  }
}
