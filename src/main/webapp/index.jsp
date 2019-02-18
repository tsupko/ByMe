<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Главная страница</title>
  <link href="jsp/css/index.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="head">

    <div class="loginButtons">

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

    <button class="headButton" onclick="location.href='jsp/add.jsp'">Добавить объявление</button>
    <button class="headButton" onclick="location.href='jsp/authorization.jsp'">Выйти</button>
    <button class="headButton" onclick="location.href='/registration'">Зарегистрироваться</button>
    <button class="headButton" onclick="location.href='jsp/authorization.jsp'">Войти</button>
  </div>
</div>

<div class="context">
  <div class="tableContext">
    <table class="table">
      <tr><td background="static/testImg/cat0.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat1.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat2.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat3.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="static/testImg/cat4.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat5.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat6.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat7.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="static/testImg/cat8.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat0.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat1.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat2.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="static/testImg/cat3.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat4.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat5.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat6.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="static/testImg/cat7.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat8.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat0.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat1.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="static/testImg/cat2.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat3.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat4.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="static/testImg/cat5.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
    </table>
  </div>
  <div class="head">
    <button class="headButton" onclick="location.href='http://innopolis.ru/'">Иннополис</button>
  </div>
</div>
</body>
</html>