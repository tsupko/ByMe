<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/authorization.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="head">
    <button class="headButton" onclick="location.href='/registration'">Зарегистрироваться</button>
</div>
<form method="post" action="${pageContext.request.contextPath}/authorization">
    <table class="table">
        <tr>
            <td>Введите логин:</td>
            <td><label for="login"></label><input type="text" id="login" name="login"></td>
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
