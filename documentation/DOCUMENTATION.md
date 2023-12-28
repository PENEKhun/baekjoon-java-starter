# 사용 가이드

## 설정 커스터 마이징

### 개요

한번 실행이 된 이후로, jar파일이 있는 경로에 config.json이 생성됩니다.  
이 파일을 수정하여 설정을 커스터마이징 할 수 있습니다.

### 생성되는 소스코드 폴더명 변경하기

config.json의 `srcDirPrefix.value`을 수정하여, 생성되는 소스코드 폴더명의 prefix를 변경할 수 있습니다.
예시로 1000번 문제에서 `srcDirPrefix.value`을 `BOJ_`로 설정하면, `BOJ_1000` 폴더가 생성됩니다. 기본 설정 값은 `p`입니다.

```json
{
  ...
  "srcDirPrefix": {
    "value": "BOJ_"
  },
  ...
}
```

### 생성되는 Main.java 파일의 템플릿 변경하기

config.json의 `mainCodeTemplate.value`을 수정하여, 생성되는 Main.java 파일의 템플릿을 변경할 수 있습니다.

```json
{
  ...
  "mainCodeTemplate": {
    "value": "import java.util.Scanner;\n\n/*\n    BAEKJOON {{number}} {{title}}\n    https://www.acmicpc.net/problem/{{number}}\n*/\n\npublic class Main {\n\n  public static void main(String[] args) {\n    Scanner scanner = new Scanner(System.in);\n    // 코드를 작성하세요.\n  }\n}\n"
  },
  ...
}
```

기본 설정 값은 아래와 같습니다.

```java
import java.util.Scanner;
    
/*
  BAEKJOON {{number}} {{title}}
  https://www.acmicpc.net/problem/{{number}}
*/

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 코드를 작성하세요.
  }
}
```

#### 예약어

해당 기능에선 두가지 예약어를 지원합니다.

- `{{problemNumber}}`: 문제 번호
- `{{problemTitle}}`: 문제 제목

해당 설정 값에 위 두가지 예약어를 사용하면, 각각 실제 값으로 치환됩니다.

#### 사용팁

만약 빠른 입출력을 위해 `BufferedReader`와 `BufferedWriter`를 사용하고 싶다면, 아래와 같이 템플릿을 수정하면 됩니다.

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
    bw.flush();
    bw.close();
    br.close();
  }
}
```
