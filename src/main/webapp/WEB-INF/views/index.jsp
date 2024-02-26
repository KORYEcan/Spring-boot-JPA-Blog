<%--
  Created by IntelliJ IDEA.
  User: paych
  Date: 2024-01-26
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Blog Project</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

</head>
<body>

<%@include file="layout/header.jsp"%>

<div class="container">


    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2" >
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-primary stretched-link">상세보기</a>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">
            <li class="page-item disabled "><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item "><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled "><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>



</div>
<%@include file="layout/footer.jsp"%>




