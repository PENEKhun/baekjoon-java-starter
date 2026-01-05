# Baekjoon-java-kotlin-starter

해당 프로젝트는 자바(Java) 또는 코틀린(Kotlin)을 이용하여 백준 문제를 풀 때, 프로젝트 생성 및 테스트 케이스 자동 생성을 지원합니다.  
**"여러분은 단지 문제 풀이에만 집중하면 됩니다."**

<div align="center">
  <a>
    <img src="documentation/assets/1.png" alt="매번 귀찮게 프로젝트를 만들지 마세요" width="500px"/> 
  </a>
  <a>
    <img src="documentation/assets/2.png" alt="그냥 번호를 입력하세요" width="500px"/>
  </a>
</div>
<div align="center">
  <a>
    <img src="documentation/assets/3.png" alt="매번 귀찮게 복붙하지 마세요" width="500px"/> 
  </a>
  <a>
    <img src="documentation/assets/4.png" alt="그냥 실행만 하세요" width="500px"/>
  </a>
</div>

## 어떻게 쓰면 되나요?

0. **프로그램 실행**
1. **문제 번호 입력:** 시작하기 위해 문제 번호를 입력하세요.
2. **언어 선택:** 사용하고자 하는 언어를 선택하세요 (1: Java, 2: Kotlin).
3. **자동 설정:** 입력한 번호와 선택한 언어로 디렉터리가 생성되며, 소스 코드 파일(`Main.java` 또는 `Main.kt`)과 `TestHelper.java` 파일이 자동으로 준비됩니다. 이후 인텔리제이가 실행됩니다.
4. **코딩 및 테스트:** `Main` 파일에서 알고리즘을 구현하고, `TestHelper.java`를 실행하여 코드를 테스트하세요. 모든 테스트 케이스가 자동으로 실행됩니다.
5. **제출:** 문제 해결이 완료되면, `Main` 파일의 내용을 백준에 제출합니다.

## 요구 환경

- 인터넷 연결  
  백준 사이트에서 문제를 파싱하기 위해 인터넷 연결이 필요합니다.
- JAVA 13 버전 이상 (프로그램 실행 및 Java 문제 풀이 시)
- Kotlin 환경 (Kotlin 문제 풀이 시)
- 인텔리제이 (IntelliJ IDEA)

## 설치 및 사용 방법

1. [최신 릴리즈] 에서 `Baekjoon-java-starter.zip` 파일을 다운로드합니다. (파일명은 추후 변경될 수 있습니다)
2. **압축을 풀고,** 아래 명령어를 실행합니다.

    ```bash
    java -jar Baekjoon-java-starter.jar
    ```

3. 이제 백준 문제 번호와 언어를 입력하면 됩니다.

기본으로 생성되는 코드 템플릿을 변경하고 싶다면, [코드 템플릿 변경하기]을 참조해주세요.  
**그 외 자세한 내용은 [사용 가이드]를 참고해주세요.**

[코드 템플릿 변경하기]: documentation/DOCUMENTATION.md#생성되는-mainjava-파일의-템플릿-변경하기

[사용 가이드]: documentation/DOCUMENTATION.md

[최신 릴리즈]: https://github.com/PENEKhun/Baekjoon-java-starter/releases/latest

## 기여하기

이 프로젝트에 대한 이슈와 풀 리퀘스트는 언제나 환영합니다. [기여 가이드라인]을 확인해 주세요. 감사합니다!

[기여 가이드라인]: documentation/CONTRIBUTING.md

## 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다.

[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
