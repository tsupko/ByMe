<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Страница регистрации</title>
    <link href="css/registration.css" rel="stylesheet" type="text/css">
    <style>
        .label {
            width: 40%;
            text-align: right;
        }
        .field {
            width: 60%;
        }
        .error {
            font-family: Tahoma, sans-serif;
            font-size: small;
            color: red;
        }
        .valid {
            font-family: Tahoma, sans-serif;
            font-size: small;
            color: green;
        }
    </style>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(onLoad);
        function onLoad() {
            $("#password").keyup(arePasswordsMatching);
            $("#confirm_password").keyup(arePasswordsMatching);
            $("#details").submit(canSubmit); // препятствует отправке формы при несовпадающих паролях
        }
        function arePasswordsMatching() {
            var threshold = 3; // количество символов, которое необходимо ввести для отображения сообщений
            var password = $("#password").val();
            var confirm_password = $("#confirm_password").val();
            if (password.length >= threshold || confirm_password.length >= threshold) {
                if (password === confirm_password) {
                    $("#are_passwords_matching").text("Пароли совпадают")
                                                .addClass("valid")
                                                .removeClass("error");
                } else {
                    $("#are_passwords_matching").text("Пароли не совпадают")
                                                .addClass("error")
                                                .removeClass("valid");
                }
            }
            if (password.length < threshold && confirm_password.length < threshold) {
                $("#are_passwords_matching").text("").removeClass("error");
            }
        }
        function canSubmit() {
            var password = $("#password").val();
            var confirm_password = $("#confirm_password").val();
            if (password !== confirm_password) {
                alert("Пароли не совпадают!");
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
<div class="head">
    <div class="head">
        <button class="headButton" onclick="location.href='/'">Домой</button>
        <button class="headButton" onclick="location.href='/authorization'">Войти</button>
    </div>
</div>
<form:form id="details" method="post" action="${pageContext.request.contextPath}/registration" modelAttribute="user">
    <table class="table">
        <tr>
            <td class="label">* Введите логин:</td>
            <td><form:input path="login" type="text" name="login" cssClass="field" autofocus="autofocus"/><br/>
                <form:errors path="login" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">* Введите пароль:</td>
            <td><form:input id="password" path="password" type="password" name="password" cssClass="field"/><br/>
                <form:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">* Подтвердите пароль:</td>
            <td><form:input id="confirm_password" path="confirmPassword" type="password" name="confirmPassword" cssClass="field"/>
            <br/><div id="are_passwords_matching"></div></td>
        </tr>
        <tr>
            <td class="label">* Введите имя:</td>
            <td><form:input path="name" type="text" name="name" cssClass="field"/><br/>
                <form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">* Введите почту:</td>
            <td><form:input path="email" type="text" name="email" cssClass="field"/><br/>
                <form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">Введите телефон:</td>
            <td><form:input path="phoneNumber" type="text" name="phoneNumber" cssClass="field"/><br/>
                <form:errors path="phoneNumber" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label"><label for="city">Введите город:</label></td>
            <td><input id="city" type="text" name="city" class="field"/></td>
        </tr>
        <tr>
            <td class="error">* <small> Обязательные поля</small></td>
            <td><button class="headButton" name="register" type="submit">Зарегистрироваться</button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
