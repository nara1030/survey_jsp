<%@page import="org.among.dto.UserResDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<%-- 세션에서 작성자ID 꺼내서 세팅 --%>
<% 
	Object user = session.getAttribute("loginUser");
	String authorId = ((UserResDto) user).getId();
%>

<div class="container mt-4">
  <h3>설문 생성</h3>
  
  <form id="surveyForm" action="/register/subjective" method="post">
  	<!-- 작성자 ID를 hidden input으로 전달 -->
  	<input type="hidden" name="authorId" value="<%= authorId %>">
    
    <!-- 설문 타입 선택 -->
    <div class="mb-3">
      <label class="form-label">설문 유형</label><br>
      <input type="radio" name="type" value="OBJECTIVE" id="objectiveType" checked> 객관식
      <input type="radio" name="type" value="SUBJECTIVE" class="ms-3" id="subjectiveType"> 주관식
    </div>

    <!-- 문항 수 선택 (설문 유형 선택 후 보이게 하도록 숨김 처리) -->
    <div class="mb-3" id="questionCountArea" style="display: none;">
      <label for="questionCount" class="form-label">문항 수</label>
      <select id="questionCount" name="questionCount" class="form-select" style="width: 100px;">
        <option value="">선택</option>
        <option value="1">1개</option>
        <option value="2">2개</option>
        <option value="3">3개</option>
      </select>
    </div>

    <!-- 문항 입력 영역 (설문 유형 선택 후 문항 수 선택 후 보여짐) -->
    <div id="questionArea"></div>

    <!-- 제출 버튼 (설문 유형 선택 후 문항 수 선택 후 보이도록 숨김 처리) -->
    <button type="submit" class="btn btn-primary mt-3" id="submitBtn" style="display: none;">제출</button>
  </form>
</div>