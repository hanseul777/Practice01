<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/22
  Time: 5:00 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>${boardDTO}</div>
<div>${pageDTO}</div>

<a href="/board/list?page=${pageDTO.page}&size=${pageDTO.size}">LIST</a>


</body>
</html>
