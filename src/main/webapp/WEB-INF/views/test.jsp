<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP 예시</title>
</head>
<body>

<h1>안녕하세요, JSP 예시입니다!</h1>

<%-- Java 코드 부분 --%>
<%
    String name = "홍길동";
    out.println("이름: " + name);
%>

<br>

<%-- 조건문과 반복문 --%>
<%
    int count = 5;
    for (int i = 1; i <= count; i++) {
%>
<p>반복 횟수: <%= i %></p>
<%
    }
%>

</body>
</html>
