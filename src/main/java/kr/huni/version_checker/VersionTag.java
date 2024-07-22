package kr.huni.version_checker;

public enum VersionTag {
  PRERELEASE(-1),
  RELEASE(0),
  HOTFIX(1),
  HOTFIX2(2),
  HOTFIX3(3),
  HOTFIX4(4);

  private final int priority;

  VersionTag(int priority) {
    this.priority = priority;
  }

  public boolean isMoreRecentThan(VersionTag compareTo) {
    if (this.priority == compareTo.priority) {
      return true;
    }

    return this.priority > compareTo.priority;
  }

  public static VersionTag of(String tag) {
    return VersionTag.valueOf(tag.toUpperCase());
  }
}
