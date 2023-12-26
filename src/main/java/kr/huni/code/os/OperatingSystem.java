package kr.huni.code.os;

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
