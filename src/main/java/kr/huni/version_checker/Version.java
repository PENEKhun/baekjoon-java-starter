package kr.huni.version_checker;

public class Version {
  private final int major;
  private final int minor;
  private final int patch;
  private final VersionTag tag;

  public Version(String version) {
    assert version != null && !version.isEmpty();

    if (version.contains("-")) {
      String[] versionArray = version.split("-");
      String[] versionNumberArray = versionArray[0].split("\\.");

      this.major = Integer.parseInt(versionNumberArray[0]);
      this.minor = Integer.parseInt(versionNumberArray[1]);
      this.patch = Integer.parseInt(versionNumberArray[2]);
      this.tag = VersionTag.of(versionArray[1]);
      return;
    }

    String[] versionArray = version.split("\\.");

    this.major = Integer.parseInt(versionArray[0]);
    this.minor = Integer.parseInt(versionArray[1]);
    this.patch = Integer.parseInt(versionArray[2]);
    this.tag = VersionTag.PRERELEASE;
  }

  public Version(int major, int minor, int patch, VersionTag tag) {
    assert major >= 0;
    assert minor >= 0;
    assert patch >= 0;

    this.major = major;
    this.minor = minor;
    this.patch = patch;
    this.tag = tag;
  }

  public boolean isMoreRecentThan(Version compareTo) {
    // TODO: target이 PRELEASE 이고 compareTo가 RELEASE인 경우에 대한 처리가 필요함
    if (this.major > compareTo.major) {
      return true;
    } else if (this.major < compareTo.major) {
      return false;
    }

    if (this.minor > compareTo.minor) {
      return true;
    } else if (this.minor < compareTo.minor) {
      return false;
    }

    if (this.patch > compareTo.patch) {
      return true;
    } else if (this.patch < compareTo.patch) {
      return false;
    }

    return this.tag.isMoreRecentThan(compareTo.tag);
  }

  @Override
  public String toString() {
    return major + "." + minor + "." + patch + "-" + tag.name();
  }
}
