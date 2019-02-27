<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
</head>
<body>
<div>
    <h2>${u}</h2>
</div>

<div>
    <table style="width: 100%">
        <caption>Deck</caption>
        <tr>
            <td style="width: 50%">Selected cards</td>
            <td>All cards</td>
        </tr>
        <tr>
            <td id="userCards">
                <table style="width: 100%">
                    <c:set var="counter" value="${1}"/>
                    <c:forEach items="${userCards}" var="i">
                        <c:set var="counter" value="${counter+1}"/>
                        <c:if test="${counter%2==0}">
                            <tr>
                            <td style="width: 50%">
                                <form action="/fs/deck?id=-${i.id}" method="post">
                                        ${i.name}<br/>
                                        ${i.description}<br/>
                                    <input type="submit" value="Remove"/>
                                </form>
                            </td>
                            <td>
                        </c:if>
                        <c:if test="${counter%2!=0}">
                            <form action="/fs/deck?id=-${i.id}" method="post">
                                    ${i.name}<br/>
                                    ${i.description}<br/>
                                <input type="submit" value="Remove"/>
                            </form>
                            </td>
                            </tr>
                        </c:if>
                    </c:forEach>

                </table>
            </td>


            <td id="allCards">

                <table style="width: 100%">

                    <c:set var="counter" value="${1}"/>
                    <c:forEach items="${cards}" var="i">
                        <c:set var="counter" value="${counter+1}"/>
                        <c:if test="${counter%2==0}">
                            <tr>
                            <td style="width: 50%">
                                <form action="/fs/deck?id=${i.id}" method="post">
                                        ${i.name}<br/>
                                        ${i.description}<br/>
                                    <input type="submit" value="Add"/>
                                </form>
                            </td>
                            <td>
                        </c:if>
                        <c:if test="${counter%2!=0}">
                            <form action="/fs/deck?id=${i.id}" method="post">
                                    ${i.name}<br/>
                                    ${i.description}<br/>
                                <input type="submit" value="Add"/>
                            </form>
                            </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </td>

        </tr>
    </table>
</div>
<div>
    <form action="/fs/deck?id=0" method="post">
        <input type="submit" value="Save Deck"/>
    </form>
</div>
<div>
    <form action="/fs/main/" method="get">
        <input type="submit" value="Main Page"/>
    </form>
</div>
</body>
</html>