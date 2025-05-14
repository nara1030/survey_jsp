<%@ page import="org.among.dto.UserResDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<% 
	Object user = session.getAttribute("loginUser");
%>

<!-- 최상단 헤더 -->
<nav class="navbar navbar-light bg-light border-bottom">
  <div class="container-fluid justify-content-end">
    <%  
      if (user == null) { 
    %>
      <!-- 비로그인 상태 -->
      <a class="btn btn-outline-primary me-2" href="/signup">회원가입</a>
      <a class="btn btn-primary" href="/login">로그인</a>
    <% 
      } else { 
    	  String userName = ((UserResDto) user).getName();
    %>
      <!-- 로그인 상태 -->
      <span class="me-3">환영합니다, <%= userName %>님</span>
      <a href="#" class="btn btn-danger" onclick="document.getElementById('logoutForm').submit(); return false;">로그아웃</a>
      <form id="logoutForm" action="/logout" method="post" style="display: none;"></form>
    <% 
      } 
    %>
  </div>
</nav>

<!-- 하단 네비게이션 바 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">로고</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mainNavbar">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
    <% 
      if (user != null) {
    	  String userRole = ((UserResDto) user).getRole();
    	  if ("ADMIN".equals(userRole)) {
    %>
    	<!-- A 메뉴 (admin 전용) -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="/register" id="menuA" role="button" data-bs-toggle="dropdown">
          	설문 관리
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/register">설문 작성</a></li>
            <li><a class="dropdown-item" href="#">설문 발송</a></li>
          </ul>
        </li>
        <!-- B 메뉴 -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="menuB" role="button" data-bs-toggle="dropdown">
          	통계
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">설문 현황</a></li>
            <li><a class="dropdown-item" href="#">기타</a></li>
          </ul>
        </li>
    <% 
    	  } else if ("USER".equals(userRole)) {
    %>
        <!-- C 메뉴 -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="menuC" role="button" data-bs-toggle="dropdown">
          	설문 응답
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">답변 작성</a></li>
          </ul>
        </li>
    <% 
    	  }
      }
    %>
	  </ul>
    </div>
  </div>
</nav>
