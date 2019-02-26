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
            <c:forEach items="${u}" var="i">
            <h2>Level : ${i.lvl} </h2>
            <h2>Points : ${i.points}</h2>
            <h2>Stars : ${i.stars}</h2>
        </div>
        </c:forEach>
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
    <form>
        <input type="submit" value="Form Deck">

    </form>
</div>

</body>
</html>
