<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/19
  Time: 1:39 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Page</h1>

<!--2. PageMaker 잘 받아왔는지 확인-->
<h4>${pageMaker}</h4>
<!--1. Controller끝내고 화면구현 시작-->

<ul>
    <c:forEach items="${dtoList}" var="dto">
    <li>
        ${dto}
    </li>
    </c:forEach>
</ul>

<hr/>
<!-- 4. page번호출력 모양잡기-->
<style>
    .pageList {
        list-style: none;
        display: flex;
        flex-direction: row;
    }

    .pageList li {
        margin-left: 0.1em;
        background-color: green;
        font-family: "Roboto Light";
        border: 1px solid greenyellow;
    }
</style>

<!--3. page출력하기 -->
<ul class="pageList">

    <!-- 5. 이전버튼만들기 -->
    <c:if test="${pageMaker.prev}">
        <li><a href="/board/list?page=${pageMaker.start - 1}&size=${pageMaker.size}">PREV</a></li>
    </c:if>

    <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
        <li><a href="/board/list?page=${page}&size=${pageMaker.size}">
            ${page}
        </a></li>
    </c:forEach>

    <!-- 6. 다음버튼만들기 -->
    <c:if test="${pageMaker.next}">
        <li><a href="/board/list?page=${pageMaker.end + 1}&size=${pageMaker.size}">
            NEXT
        </a></li>
    </c:if>
</ul>

</body>
</html>
