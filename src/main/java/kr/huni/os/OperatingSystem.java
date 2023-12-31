package kr.huni.os;

/**
 * 현재 운영체제를 나타내는 enum 운영체제 마다 IDE가 열리는 방식이 다르기 때문에, 운영체제를 구분해야 한다.
 */
public enum OperatingSystem {
  WINDOWS,
  LINUX,
  MAC;

  public static OperatingSystem getOperatingSystem() {
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.contains("win")) {
      return WINDOWS;
    } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
      return LINUX;
    } else if (osName.contains("mac")) {
      return MAC;
    } else {
      throw new RuntimeException("지원하지 않는 운영체제입니다. (현재 운영체제: " + osName + ")");
    }
  }
}
