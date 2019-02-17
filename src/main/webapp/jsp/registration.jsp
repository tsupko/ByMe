<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница регистрации</title>
    <link href="css/registration.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="head">
    <button class="headButton" onclick="location.href='authorization.jsp'">Войти</button>
</div>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <table class="table">
        <tr>
            <td>Введите логин:</td>
            <td><input type="text" name="login"></td>
        </tr>

        <tr>
        <tr>
            <td>Введите пароль:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
        <tr>
            <td>Введите пароль еще раз:</td>
            <td><input type="password" name="checkPassword"></td>
        </tr>

        <tr>
            <td>Введите имя:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
        <tr>
            <td>Введите почту:</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>Введите телефон:</td>
            <td><input type="text" name="phoneNumber"></td>
        </tr>
        <tr>
        <tr>
            <td>Введите город:</td>
            <td><input type="text" name="city"></td>
        </tr>
        <tr>
            <td></td>
            <td><button class="headButton" name="register" type="submit">Зарегистрироваться</button></td>
        </tr>
    </table>
</form>
</body>
</html>
