<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="main_css.css"%>
</style>
<head>
    <title>Main</title>
</head>
<body>
<div>
    <nav>
        <div align='left' class="topnav" id="myTopnav">
            <h2>Level : ${u.lvl} </h2>
            <h2>Points : ${u.points}</h2>
            <h2>Stars : ${u.stars}</h2>
        </div>
    </nav>
</div>
<div align='center' class="container">
    <c:forEach items="${userCards}" var="i">
        <div class="item">
            <h3>${i.name}</h3>
            <h3>${i.cost}</h3>
            <img src='${i.pic}' alt='card'/>
            <br/>
        </div>
    </c:forEach>
</div>
<div align='center'>
    <form action="/fs/deck/" method="GET">
        <input type="submit" value="Form Deck">

    </form>
</div>

</body>
</html>
