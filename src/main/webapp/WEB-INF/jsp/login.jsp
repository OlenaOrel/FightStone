<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <div>
        <form action="/fs/" method="POST">
            Enter login:<input name="login"/><br/>
            Enter password:<input type="password" name="pass"/><br/>
            <input type="submit" value="Sign In"/>
        </form>
        <hr/>
        <form action="/fs/register/" method="GET">
            <input type="submit" value="Sign Up"/>
        </form>
    </div>
</body>
</html>
