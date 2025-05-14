<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<div class="container mt-5" style="max-width: 400px;">
  <h2 class="mb-4 text-center">로그인</h2>

  <form action="/login" method="post">
    <div class="mb-3">
      <label for="email" class="form-label">이메일</label>
      <input type="email" class="form-control" id="email" name="email" placeholder="example@example.com" required>
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">비밀번호</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>
    
    <!-- 로그인 상태 유지 체크박스 -->
    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" id="autoLoginYn" name="autoLoginYn" value="Y">
      <label class="form-check-label" for="autoLoginYn">
      	로그인 상태 유지
      </label>
    </div>

    <button type="submit" class="btn btn-primary w-100">로그인</button>
  </form>

  <div class="mt-3 text-center">
    <a href="/signup">회원가입</a>
  </div>
</div>