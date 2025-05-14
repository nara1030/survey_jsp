<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <title>${title}</title>
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/header.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <tiles:insertAttribute name="header" />
  </div>
  <div class="body">
    <tiles:insertAttribute name="body" />
  </div>
  <div class="footer">
    <tiles:insertAttribute name="footer" />
  </div>
  <script src="/resources/js/bootstrap.bundle.min.js"></script>
  
  <!-- 각 페이지 전용 JS: 반드시 body 삽입 이후에 위치해야 함 -->
  <c:if test="${not empty pageScript}">
    <script src="${pageScript}"></script>
  </c:if>
</body>
</html>