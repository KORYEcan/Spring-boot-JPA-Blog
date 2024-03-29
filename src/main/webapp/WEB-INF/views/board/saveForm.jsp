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

<%@include file="../layout/header.jsp"%>

<div class="container">

    <form >

        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter title" id="title">
        </div>

        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
    </form>

    <button id="btn-save" class="btn btn-primary">글쓰기 완료</button>

</div>

<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>

<script src="/js/board.js"> </script>
<%@include file="../layout/footer.jsp"%>




