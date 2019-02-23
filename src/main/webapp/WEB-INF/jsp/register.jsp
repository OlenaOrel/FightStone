<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<style>
    <%@include file='register_css.css' %>
</style>
<head>
    <title>Register</title>
</head>
<body>
<div id="registration-form">
    <div class='fieldset'>
        <legend>Welcome to FightStone</legend>
        <form action="/fs/register" method="post">
            <div class='row'>
                <label for='login'>Login</label>
                <input type="text" placeholder="login" name='login' id='login' data-required="true"
                       data-error-message="Your login is required">
            </div>
            <div class='row'>
                <%--@declare id="pass"--%><label for='pass'>Password</label>
                <input type="password" placeholder="password" name='pass' data-required="true" data-type="password"
                       data-error-message="Your password is required">
            </div>
            <div class='row'>
                <%--@declare id="cpass"--%><label for='cpass'>Confirm your password</label>
                <input type="password" placeholder="Confirm your password" name='cpass' data-required="true"
                       data-error-message="Your password must correspond">
            </div>

            <input type="submit" value="Register">

        </form>
    </div>
</div>
</form>
</body>
</html>
