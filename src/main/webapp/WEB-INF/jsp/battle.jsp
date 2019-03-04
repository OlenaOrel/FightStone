<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file="main_css.css"%>
</style>
<head>
    <title>Battle</title>
</head>
<body>
<div>
    <div>
        <nav>
            <div align='center' class="topnav">
                <h2>Login : ${u.login} </h2>
                <h2>Level : ${u.lvl} </h2>
                <h2>Points : ${u.points}</h2>
                <h2>Stars : ${u.stars}</h2>
            </div>
        </nav>
    </div>
    <div align='left' class="container">
        <c:forEach items="${userCards}" var="i">
            <div class="item">
                <img src='${i.pic}' alt='card'/>
            </div>
        </c:forEach>
    </div>
    <div align='right' class="container">
        <c:forEach items="${userCards}" var="i">
            <div class="item">
                <img src='${i.pic}' alt='card'/>
            </div>
        </c:forEach>
    </div>
</div>
<div>

</div>
<div>
    <div align='left' class="container">
        <c:forEach items="${userCards}" var="i">
            <div class="item">
                <img src='${i.pic}' alt='card'/>
            </div>
        </c:forEach>
    </div>
    <div align='right' class="container">
        <c:forEach items="${userCards}" var="i">
            <div class="item">
                <h3>${i.name}</h3>
                <h3>${i.cost}</h3>
                <img src='${i.pic}' alt='card'/>
            </div>
        </c:forEach>
    </div>
    <div>
        <nav>
            <div align='center' class="topnav">
                <h2>Login : ${u.login} </h2>
                <h2>Level : ${u.lvl} </h2>
                <h2>Points : ${u.points}</h2>
                <h2>Stars : ${u.stars}</h2>
            </div>
        </nav>
    </div>
</div>
</body>
</html>