<%--
  Created by IntelliJ IDEA.
  User: aleksandrtsupko
  Date: 2019-02-12
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница регистрации</title>
</head>
<body>
    <h2>Добро пожаловать на страницу регистрации!</h2>
    <form method="post" action="/">
        <label>
            Введите логин: <input type="text" name="username">
        </label><br>
        <label>
            Введите пароль: <input type="password" name="password">
        </label><br>
        <input type="submit" name="register" value="Зарегистрироваться"><br>
    </form>
</body>
</html>
