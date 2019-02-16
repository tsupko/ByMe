<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Главная страница</title>
  <link href="jsp/css/index.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="head">
  <div class="loginButtons">
    <button class="headButton" onclick="location.href='jsp/add.jsp'">Добавить объявление</button>
    <button class="headButton" onclick="location.href='jsp/authorization.jsp'">Выйти</button>
      <button class="headButton"><a href="${pageContext.request.contextPath}/registration.form">Зарегистрироваться</a></button>
    <button class="headButton" onclick="location.href='jsp/authorization.jsp'">Войти</button>
  </div>
</div>

<div class="context">
  <div class="tableContext">
    <table class="table">
      <tr><td background="resources/testImg/cat0.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat1.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat2.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat3.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="resources/testImg/cat4.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat5.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat6.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat7.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="resources/testImg/cat8.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat0.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat1.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat2.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="resources/testImg/cat3.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat4.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat5.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat6.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="resources/testImg/cat7.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat8.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat0.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat1.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
      <tr><td background="resources/testImg/cat2.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat3.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat4.jpeg" onclick="location.href='jsp/authorization.jsp'"></td>
          <td background="resources/testImg/cat5.jpeg" onclick="location.href='jsp/authorization.jsp'"></td></tr>
    </table>
  </div>
  <div class="head">
    <button class="headButton" onclick="location.href='http://innopolis.ru/'">Иннополис</button>
  </div>
</div>
</body>
</html>