<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/authorization.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="head">
    <button class="headButton" onclick="location.href='registration.jsp'">Зарегистрироваться</button>
</div>
<form method="post" action="authorization">
    <table class="table">
        <tr>
            <td>Введите логин:</td>
            <td><label for="name"></label><input type="text" id="name" name="name"></td>
        </tr>
        <tr>
            <td>Введите пароль:</td>
            <td><label for="password"></label><input type="password" id="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><button class="headButton" type="submit">Войти</button></td>
        </tr>
    </table>
</form>
</body>
</html>
