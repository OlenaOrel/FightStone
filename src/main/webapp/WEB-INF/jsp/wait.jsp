<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    body {
        background: #b4b4b4;
    }
</style>
<head>
    <title>Wait</title>
</head>
<body>
<div class="block">
    <div align="center" class="button">
        <form action="/fs/wait/" method="post">
            <input type="hidden" name="bat" value="in"/>
            <input type="submit" value="Refresh"/>
        </form>
    </div>
    <div align="center" class="button">
        <form action="/fs/main/" method="get">
            <input type="hidden" name="bat" value="out"/>
            <input type="submit" value="Leave"/>
        </form>
    </div>
</div>
</body>
</html>
