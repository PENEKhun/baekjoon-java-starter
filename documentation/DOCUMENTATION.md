# 사용 가이드

이 가이드는 프로그램 설정을 사용자 맞춤형으로 조정하는 방법을 설명합니다.

## 설정 커스터 마이징

### 개요

프로그램 실행 시, `config.json` 파일이 jar 파일이 위치한 경로에 생성됩니다. 이 파일을 통해 다양한 설정을 커스터마이징할 수 있습니다.

### 소스코드 폴더명 변경

**경로:** config.json > srcDirPrefix.value

**설명:** 생성되는 소스코드 폴더의 접두사를 변경합니다.

**기본값:** `p`

**예시:** srcDirPrefix.value를 BOJ_로 설정하면, 1000번 문제에 대해 BOJ_1000 폴더가 생성됩니다.

```json
{
  ...
  "srcDirPrefix": {
    "value": "p"
  },
  ...
}
```

### Main.java 템플릿 변경

**경로:** config.json > mainCodeTemplate.value

**설명:** Main.java 파일 생성 시 사용되는 템플릿을 변경합니다.

**기본 템플릿:**

```java
import java.util.Scanner;
    
/*
  BAEKJOON {{number}}번 {{title}}
  https://www.acmicpc.net/problem/{{number}}
*/

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 코드를 작성하세요.
  }
}
```

#### 예약어 사용

- `{{number}}`: 문제 번호
- `{{title}}`: 문제 제목

#### 사용 팁

빠른 입출력이 필요한 경우, `BufferedReader`와 `BufferedWriter`를 사용하는 템플릿으로 수정할 수 있습니다.

##### 예시 :

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON {{number}} {{title}}
  https://www.acmicpc.net/problem/{{number}}
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
  }
}
```

### README.md 템플릿 변경

**경로:** config.json > markdownTemplate.value

**설명:** 생성되는 README.md 파일의 내용을 변경합니다.

**기본 템플릿:**

```
# {{title}}

> 문제 번호 : {{number}} <br/>
> 출처 : {{url}}

| 메모리 제한 | 시간 제한 |
|--------|-------|
| {{memory_limit}} MB | {{time_limit}} ms |

## 문제 설명

{{description}}
```

#### 예약어

- `{{title}}`: 문제 제목
- `{{number}}`: 문제 번호
- `{{url}}`: 문제 출처 URL
- `{{description}}`: 문제 설명 *(html 태그 포함)*
- `{{time_limit}}`: 시간 제한 *(단위 : 초)*
- `{{memory_limit}}`: 메모리 제한 *(단위 : MB)*

#### README 생성 비활성화

**경로:** config.json > enableReadme.value

**설명:** README.md 파일 생성을 비활성화합니다.

**비활성화 방법:** 값을 `false`로 설정합니다. 꼭 `"`로 감싸진 문자열로 입력해야 합니다.  
