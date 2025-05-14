<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<div class="container mt-5" style="max-width: 500px;">
  <h2 class="mb-4 text-center">회원가입</h2>

  <form action="/signup" method="post">
    <!-- 이메일 -->
    <div class="mb-3">
      <label for="email" class="form-label">이메일 (ID)</label>
      <input type="email" class="form-control" id="email" name="email" placeholder="example@example.com" required>
    </div>

    <!-- 비밀번호 -->
    <div class="mb-3">
      <label for="password" class="form-label">비밀번호</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>

    <!-- 이름 -->
    <div class="mb-3">
      <label for="name" class="form-label">이름</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>

    <!-- 권한 선택 -->
    <div class="mb-3">
      <label for="role" class="form-label">권한</label>
      <select class="form-select" id="role" name="role" required>
        <option value="" disabled selected>선택하세요</option>
        <option value="ADMIN">관리자</option>
        <option value="USER">사용자</option>
      </select>
    </div>

    <!-- 제출 버튼 -->
    <button type="submit" class="btn btn-success w-100">회원가입</button>
  </form>

  <div class="mt-3 text-center">
    <a href="/login">이미 계정이 있으신가요? 로그인</a>
  </div>
</div>