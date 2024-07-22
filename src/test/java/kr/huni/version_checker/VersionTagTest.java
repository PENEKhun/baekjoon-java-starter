package kr.huni.version_checker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VersionTagTest {

  @Test
  @DisplayName("PRERELEASE는 RELEASE보다 최신 버전이 아니다")
  void preReleaseShouldNotBeMoreRecentThanRelease() {
    assertFalse(VersionTag.PRERELEASE.isMoreRecentThan(VersionTag.RELEASE));
  }

  @Test
  @DisplayName("RELEASE는 PRERELEASE보다 최신 버전이다")
  void releaseShouldBeMoreRecentThanPreRelease() {
    assertTrue(VersionTag.RELEASE.isMoreRecentThan(VersionTag.PRERELEASE));
  }

  @Test
  @DisplayName("HOTFIX는 RELEASE보다 최신 버전이다")
  void hotfixShouldBeMoreRecentThanRelease() {
    assertTrue(VersionTag.HOTFIX.isMoreRecentThan(VersionTag.RELEASE));
  }

  @Test
  @DisplayName("HOTFIX2는 HOTFIX보다 최신 버전이다")
  void hotfix2ShouldBeMoreRecentThanHotfix() {
    assertTrue(VersionTag.HOTFIX2.isMoreRecentThan(VersionTag.HOTFIX));
  }

  @Test
  @DisplayName("HOTFIX3는 HOTFIX2보다 최신 버전이다")
  void hotfix3ShouldBeMoreRecentThanHotfix2() {
    assertTrue(VersionTag.HOTFIX3.isMoreRecentThan(VersionTag.HOTFIX2));
  }

  @Test
  @DisplayName("HOTFIX4는 HOTFIX3보다 최신 버전이다")
  void hotfix4ShouldBeMoreRecentThanHotfix3() {
    assertTrue(VersionTag.HOTFIX4.isMoreRecentThan(VersionTag.HOTFIX3));
  }

  @Test
  @DisplayName("HOTFIX4는 HOTFIX4와 동일한 우선순위를 가진다")
  void hotfix4ShouldBeSamePriorityAsHotfix4() {
    assertTrue(VersionTag.HOTFIX4.isMoreRecentThan(VersionTag.HOTFIX4));
  }

  @Test
  @DisplayName("RELEASE는 RELEASE와 동일한 우선순위를 가진다")
  void releaseShouldBeSamePriorityAsRelease() {
    assertTrue(VersionTag.RELEASE.isMoreRecentThan(VersionTag.RELEASE));
  }
}