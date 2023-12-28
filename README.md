# Baekjoon-java-starter

해당 프로젝트는 자바를 이용하여 백준 문제를 풀때, 프로젝트 생성 및 테스트 케이스 자동 생성을 지원합니다.  
**"여러분은 단지 문제 풀이에만 집중하면 됩니다."**

![매번 귀찮게 프로젝트를 만들지 마세요](documentation/assets/1.png)
![그냥 번호를 입력하세요](documentation/assets/2.png)
![매번 귀찮게 복붙하지 마세요](documentation/assets/3.png)
![그냥 실행만 하세요](documentation/assets/4.png)

## 어떻게 작동하나요?

프로그램을 사용하려면 먼저 문제 번호를 입력하세요.  
그러면 입력하신 번호로 디렉터리가 생성되며, 그 안에 `Main.java`와 `TestHelper.java` 파일이 자동으로 만들어집니다. 그 후에는 인텔리제이가 실행됩니다.

`Main.java` 파일 내의 main 함수에서 알고리즘을 구현하고, 작성한 코드를 테스트하려면 `TestHelper.java`를 실행하면 됩니다.  
그러면 문제 내 모든 테스트 케이스가 자동으로 수행됩니다.

문제를 다 풀었으면, 백준에 `Main.java`를 복사하여 제출하면 됩니다.

## 요구 환경

- 인터넷 연결  
  백준 사이트에서 문제를 파싱하기 위해 인터넷 연결이 필요합니다.
- JAVA 13 버전 이상
- 인텔리제이

## 설치 및 사용 방법

1. [최신 릴리즈] 에서 `Baekjoon-java-starter.zip` 파일을 다운로드합니다.
2. **압축을 풀고,** 다음 명령어를 실행합니다.

    ```bash
    java -jar Baekjoon-java-starter.jar
    ```

3. 이제 백준 문제 번호를 입력하면 됩니다.

만일 기본으로 생성되는 `Main.java`의 템플릿이 마음에 들지 않는다면, [코드 템플릿 변경하기]을 참고해주세요.  
**그 외 자세한 내용은 [사용 가이드]를 참고해주세요.**

[코드 템플릿 변경하기]: documentation/DOCUMENTATION.md#생성되는-mainjava-파일의-템플릿-변경하기

[사용 가이드]: documentation/DOCUMENTATION.md

[사용 가이드]: documentation/DOCUMENTATION.md

[최신 릴리즈]: https://github.com/PENEKhun/Baekjoon-java-starter/releases/latest

## 기여하기

해당 프로젝트에 올라오는 이슈와 풀 리퀘스트는 언제나 환영합니다.  
자세한건 [기여 가이드라인]을 참고해주세요.  
미리 감사드립니다.

[기여 가이드라인]: documentation/CONTRIBUTING.md

## 라이센스

해당 프로젝트는 MIT 라이센스를 따릅니다.

[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
