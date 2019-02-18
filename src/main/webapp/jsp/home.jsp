<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Привет ${user}</title>
    <link href="css/home.css" rel="stylesheet" type="text/css">
</head>
<body>

<h2>Привет ${user}</h2>

<div class="head">
    <select>
        <option selected disabled>${city}</option>
        <option label="Kazan" value="1" selected>Казань</option>
        <option label="Moscow" value="2" selected>Москва</option>
        <option label="Samara" value="3" selected>Самара</option>
    </select>

    <select>
        <option selected disabled>${category}</option>
        <option label="electronics" value="1" selected>Электроника</option>
        <option label="technique" value="2" selected>Бытовая техника</option>
        <option label="additionally" value="3" selected>Всякая всячина</option>
    </select>
<%--    <button class="headButton" onclick="location.href='/registration'">Зарегистрироваться</button>--%>

<span style="color: ${cookie.color.value}">Hello</span>

</body>
</html>
