<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Страница регистрации</title>
    <link href="css/registration.css" rel="stylesheet" type="text/css">
    <style>
        .error {
            font-family: Tahoma, sans-serif;
            font-size: small;
            color: red;
        }
        .label {
            width: 40%;
            text-align: right;
        }
        .field {
            alignment: left;
        }
    </style>
    <script type="text/javascript" src="static/jquery.js"></script>
</head>
<body>
<div class="head">
    <div class="head">
        <button class="headButton" onclick="location.href='/'">Домой</button>
        <button class="headButton" onclick="location.href='/authorization'">Войти</button>
    </div>
</div>
<form:form method="post" action="${pageContext.request.contextPath}/registration" modelAttribute="user">
    <table class="table">
        <tr>
            <td class="label">* Введите логин:</td>
            <td class="field"><form:input path="login" type="text" name="login" size="50%"/><br/>
                <form:errors path="login" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">* Введите пароль:</td>
            <td class="field"><form:input path="password" type="password" name="password" size="50%"/><br/>
                <form:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">* Введите пароль еще раз:</td>
            <td class="field"><input type="password" name="checkPassword" size="50%"/></td>
        </tr>
        <tr>
            <td class="label">* Введите имя:</td>
            <td class="field"><form:input path="name" type="text" name="name" size="50%"/><br/>
                <form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">* Введите почту:</td>
            <td class="field"><form:input path="email" type="text" name="email" size="50%"/><br/>
                <form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">Введите телефон:</td>
            <td class="field"><form:input path="phoneNumber" type="text" name="phoneNumber" size="50%"/><br/>
                <form:errors path="phoneNumber" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">Введите город:</td>
            <td class="field"><input type="text" name="city" size="50%"/></td>
        </tr>
        <tr>
            <td class="error">* <small> Обязательные поля</small></td>
            <td><button class="headButton" name="register" type="submit">Зарегистрироваться</button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
