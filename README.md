프로젝트명과 리포지토리명이 달라서 그런가 이상하게 올라가져서 프로젝트 외부에 README 작성했음
- - -

## 목차
1. [요구사항](#요구사항)
2. [플로우차트](#플로우차트)
3. [기타](#기타)

## 요구사항
```txt
※ 
JSP의 타일즈 기능을 한 번 사용해보고 싶어 시작했다.
사실상 회원 기능만 구현이 되어 있지만 기록 용도로 남긴다.

---
회원 기반의 간단한 설문시스템을 만들어보고자 한다.

1. 설문작성 권한이 있는 사용자는 설문지를 작성할 수 있다.
2. 설문지 작성 후 발송시 설문 응답자들을 설정할 수 있다.
3. 설문 응답자들의 메일로 설문 응답 URL이 전송되고, 전송 여부가 기록된다.
4. 설문 응답자들은 일정 기간 내 URL 접속해 답변할 수 있고, 답변 여부가 기록된다.

설문작성 및 설문응답을 위해 사용자 테이블에 권한 칼럼을 추가했다.
관리자 권한을 가진 회원의 경우 설문 작성이 가능하며, 사용자 권한을 가진 회원이라면 설문 응답만 가능하다.
```


##### [목차로 이동](#목차)

## 플로우차트
```txt

```

##### [목차로 이동](#목차)

## 기타
```txt
개발 진행한 부분에서 설문지 작성 후 콘솔 로그, DB 저장된 데이터에 한글 깨짐이 발생한다.
원인을 못 찾아서 이후에도 좀 살펴봐야 한다.

---
1. 개발 환경
   Eclipse, Spring MVC, JSP, Tiles, Bootstrap, MyBatis, MySQL
   ---
   최초에는 인텔리제이, 스프링부트 환경에서 JSP와 타일즈를 써보려고 했으나 JSP를 권장하지 않는다고 하여,
   이클립스, 스프링 환경으로 바꿔 진행했다.
   이후에 인텔리제이, 스프링부트 환경에서 타임리프 및 스프링 시큐리티를 사용해서 구현해볼 생각이다.
2. 서버가 한 개 있다고 가정하고, 세션 로그인 방식을 사용했다.
   JSP 페이지에서 세션에 접근해 분기처리하여 로그인 여부를 판단했다.
   컨트롤러는 화면을 반환해야 하므로 @Controller로 구성하였다.
   ---
   다만, 이 경우 브라우저를 껐다 키면 로그인이 유지되지 않았기에 지속적 쿠키를 반환하여 구현하였다.
   이 부분은 AutoLoginInterceptor로 빼서 세션이 없더라도 지속적 쿠키가 있으면 세션을 생성해준다.
3. 최초에는 컨트롤러에서 세션을 검사해, 회원 서비스라면 리다이렉트시켰다.
   하지만 회원이더라도 권한에 따라 메뉴별 접근을 구분해야 했고, 개별 컨트롤러에서 처리하기엔 비효율적이었다.
   해서 이 부분도 AuthInterceptor로 빼주었는데,
   중요한 부분은 예외 처리를 위해 return false 대신 예외 발생만 시켰다는 점이다.
4. 통신은 AJAX 방식을 사용하지 않았다.
   회원가입시도 폼로그인 방식을 사용했고,
   설문 작성 후에도 제출 완료 페이지를 사이에 두어 리다이렉트시키는 방식을 사용했다.
   보통은 제출 완료 같은 경우 모달을 띄우는데, 새 페이지를 띄우니 UI가 좋지 못하다.
   ---
   UI는 다 chatgpt의 도움을 받긴 했다.
5. 컨트롤러에서 타일즈 레이아웃의 body 부분만 받기 위해 다음으로 작성했다.
   타일즈 설정에서 layout을 상속받아 body를 구현했다.
   개별 body에서 쓰이는 js, css를 공통으로 빼면 비효율적이라 컨트롤러에서 경로를 반환해주는 식으로 했다.
6. 설문조사 dto
   설문조사 응답 설계
7. 스프링 빈(Ex. 컨트롤러, 서비스)의 예외 처리는 GlobalExceptionHandler에서 공통적으로 처리했다.
   인터셉터 역시 예외만 발생시키고 응답이 아닌 컨트롤러를 태움으로써 같은 방법으로 예외 처리했다.
   단, DB에서 발생하는 예외를 잡아주는 경우 DataIntegrityViolationException로 감싸주었다.
   org.among.service.JoinService 클래스의 signup 메소드 참고한다.
   ---
   Spring에서 JdbcTemplate 또는 MyBatis를 사용할 경우, SQL 예외는 보통 다음과 같이 감싸집니다:
   외부 예외: org.springframework.dao.DataIntegrityViolationException
   내부 원인 (getCause()): java.sql.SQLIntegrityConstraintViolationException
6. 크롬의 개발자 도구를 이용해보았다.
   ajax와 같은 비동기 통신이 아니었기 때문에 네트워크탭의 유지하기 위해 로그 보존 체크해주었다.
   클라이언트의 로그인 유지 기능을 테스트하기 위해 애플리케이션탭의 쿠키에서 지속적 쿠키를 삭제해주었다.
7. 처음 생각한 전체 DB 설계는 하단과 같다.
   A) 회원: id(이메일), password, name, role(권한), join_dt(가입일시), last_modified_dt(최종수정일시)
   B) 자동 로그인: token, user_id(FK), device_info, ip_address, expired_dt(만료일시), created_dt(생성일시), modified_dt(최종수정일시)
   C) 
   D)
   E)
8. 사용한 DDL은 하단과 같다.
   USE survey;
   DROP TABLE IF EXISTS users;
   DROP TABLE IF EXISTS auto_login;
   CREATE TABLE users (
     id VARCHAR(50) NOT NULL PRIMARY KEY,
     password VARCHAR(100) NOT NULL,
     name VARCHAR(100) NOT NULL,
     role VARCHAR(50) NOT NULL,
     join_dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     last_modified_dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
   );

   CREATE TABLE auto_login (
     token VARCHAR(128) NOT NULL PRIMARY KEY,
     user_id VARCHAR(50) NOT NULL,
     device_info VARCHAR(255),
     ip_address VARCHAR(45),
     expired_dt DATETIME NOT NULL,
     created_dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     modified_dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
     CONSTRAINT fk_auto_login_user FOREIGN KEY (user_id) REFERENCES users(id)
         ON DELETE CASCADE ON UPDATE CASCADE
   );

===================
1. 설문 제출 후 뒤로가기를 연속적으로 누르는 경우를 고려해야 한다.
   .
2. 설문 발송 URL에 응답 만료일시 설정해야 한다.
   .
3. 현재 설문지는 문항수가 1~3개로 제한되어 있는데 UI에서 동적으로 설계해야 한다.
   .
4. 현재 사용자와 권한은 일대일 대응이며, 따로 권한 테이블이 존재하지 않는다.
   즉 새로운 권한이 추가되거나 한 사용자가 두 가지 이상의 권한을 가질 경우 문제가 생긴다.
   .
5. 보안 관련 설정
   로그인 유지 기능을 위해 지속적 쿠키 설정시 XSS 방어 설정(HttpOnly)했다.
   다만, 쿠키는 자동 전송되기 때문에 CSRF의 방어가 필요하다.
   SameSite 쿠키 설정하거나, CSRF 토큰을 직접 구현하는 것이 안전하다.
   한편 ajax 사용하지 않은 단순 JSP 기반 서버기 때문에 CORS 설정은 불필요하다.
   CORS는 다른 도메인에서 우리 서버에 ajax 요청 보내는 걸 막는 것이다.


```

##### [목차로 이동](#목차)