package kr.huni.version_checker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VersionTest {

  @Test
  @DisplayName("더 최신 버전 비교 테스트 - major가 더 큰 경우")
  void isMoreRecentThan_MajorVersion() {
    Version v1 = new Version(2, 0, 0, VersionTag.RELEASE);
    Version v2 = new Version(1, 0, 0, VersionTag.RELEASE);
    assertTrue(v1.isMoreRecentThan(v2));
  }

  @Test
  @DisplayName("더 최신 버전 비교 테스트 - minor가 더 큰 경우")
  void isMoreRecentThan_MinorVersion() {
    Version v1 = new Version(1, 1, 0, VersionTag.RELEASE);
    Version v2 = new Version(1, 0, 0, VersionTag.RELEASE);
    assertTrue(v1.isMoreRecentThan(v2));
  }

  @Test
  @DisplayName("더 최신 버전 비교 테스트 - patch가 더 큰 경우")
  void isMoreRecentThan_PatchVersion() {
    Version v1 = new Version(1, 0, 1, VersionTag.RELEASE);
    Version v2 = new Version(1, 0, 0, VersionTag.RELEASE);
    assertTrue(v1.isMoreRecentThan(v2));
  }

  @Test
  @DisplayName("더 최신 버전 비교 테스트 - tag가 더 최신인 경우")
  void isMoreRecentThan_Tag() {
    Version v1 = new Version(1, 0, 0, VersionTag.HOTFIX);
    Version v2 = new Version(1, 0, 0, VersionTag.RELEASE);
    assertTrue(v1.isMoreRecentThan(v2));
  }

  @Test
  @DisplayName("같은 버전이라면 true 를 반환한다")
  void isMoreRecentThan_SameVersion() {
    Version v1 = new Version(1, 0, 0, VersionTag.RELEASE);
    Version v2 = new Version(1, 0, 0, VersionTag.RELEASE);
    assertTrue(v1.isMoreRecentThan(v2));
  }

  private int getField(Object obj, String fieldName) throws Exception {
    Field field = obj.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.getInt(obj);
  }

  private VersionTag getTagField(Object obj, String fieldName) throws Exception {
    Field field = obj.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    return (VersionTag) field.get(obj);
  }

  @Test
  @DisplayName("버전 문자열을 통해 Version 객체 생성 테스트")
  void createVersionFromString() throws Exception {
    Version version = new Version("1.0.0-RELEASE");
    assertEquals(1, getField(version, "major"));
    assertEquals(0, getField(version, "minor"));
    assertEquals(0, getField(version, "patch"));
    assertEquals(VersionTag.RELEASE, getTagField(version, "tag"));
  }

  @Test
  @DisplayName("버전 문자열이 태그를 포함하지 않을 때 Version 객체 생성 테스트")
  void createVersionFromStringWithoutTag() throws Exception {
    Version version = new Version("1.0.0");
    assertEquals(1, getField(version, "major"));
    assertEquals(0, getField(version, "minor"));
    assertEquals(0, getField(version, "patch"));
    assertEquals(VersionTag.PRERELEASE, getTagField(version, "tag"));
  }

  @Test
  @DisplayName("버전 숫자를 통해 Version 객체 생성 테스트")
  void createVersionFromNumbers() throws Exception {
    Version version = new Version(1, 2, 3, VersionTag.HOTFIX);
    assertEquals(1, getField(version, "major"));
    assertEquals(2, getField(version, "minor"));
    assertEquals(3, getField(version, "patch"));
    assertEquals(VersionTag.HOTFIX, getTagField(version, "tag"));
  }

  @Test
  @DisplayName("toString 메서드 테스트 - 태그가 포함된 경우")
  void toString_WithTag() {
    Version version = new Version(1, 0, 0, VersionTag.RELEASE);
    assertEquals("1.0.0-RELEASE", version.toString());
  }

  @Test
  @DisplayName("toString 메서드 테스트 - 태그가 포함되지 않은 경우")
  void toString_WithoutTag() {
    Version version = new Version("1.0.0");
    assertEquals("1.0.0-PRERELEASE", version.toString());
  }

  @Test
  @DisplayName("toString 메서드 테스트 - 문자열 생성자로 태그 포함된 경우")
  void toString_FromStringWithTag() {
    Version version = new Version("1.0.0-HOTFIX");
    assertEquals("1.0.0-HOTFIX", version.toString());
  }

  @Test
  @DisplayName("toString 메서드 테스트 - 문자열 생성자로 태그 포함되지 않은 경우")
  void toString_FromStringWithoutTag() {
    Version version = new Version("2.1.3");
    assertEquals("2.1.3-PRERELEASE", version.toString());
  }
}