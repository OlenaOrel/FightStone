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
<div align="left">
    <form action="/fs/main/" method="post">
        <input type="hidden" name="logout" value="${uDto.login}"/>
        <input type="submit" value="Log out">
    </form>
    <nav>
        <div align ='left'>
            <h2>WaitListSize : ${wait}</h2>
            <h2>OnlineListSize : ${online}</h2>
            <h2>InBattleListSize : ${active}</h2>
        </div>
    </nav>
</div>
<hr>
<div align="center" class="container">
    <nav>
        <div>
            <h2>Login : ${uDto.login} </h2>
            <h2>Level : ${uDto.lvl} </h2>
            <h2>Points : ${uDto.points}</h2>
            <h2>Stars : ${uDto.stars}</h2>
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

<div align='center'>
    <form action="/fs/wait/" method="POST">
        <input type="hidden" name="bat" value="in"/>
        <input type="submit" value="TO BATTLE">
    </form>
</div>

</body>
</html>
