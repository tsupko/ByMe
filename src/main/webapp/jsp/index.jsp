<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Главная страница</title>
  <link href="css/index.css" rel="stylesheet" type="text/css">

    <script>
        $(function() {
            $("#add").change(function(){
                var option = $('option:selected', this).attr('stud_name');
                $('#stud_name').val(option);
            });
        });
    </script>

</head>

<body>
<div class="head">

    <div class="loginButtons">

    <select id = "city" name="city" onchange="getCity(this)">
        <option selected disabled>${city}</option>
        <option name="Kazan"    value="1" selected>Казань</option>
        <option name="Moscow"   value="2" selected>Москва</option>
        <option name="Samara"   value="3" selected>Самара</option>
    </select>

    <select name="category">
        <option selected disabled>${category}</option>
        <option label="electronics" value="1" selected>Электроника</option>
        <option label="technique" value="2" selected>Бытовая техника</option>
        <option label="additionally" value="3" selected>Всякая всячина</option>
    </select>

    <button class="headButton" onclick="location.href='/ad'">Добавить объявление</button>
    <button class="headButton" onclick="location.href='/authorization'">Выйти</button>
    <button class="headButton" onclick="location.href='/registration'">Зарегистрироваться</button>
    <button class="headButton" onclick="location.href='/authorization'">Войти</button>
  </div>
</div>

<div class="context">
  <div class="tableContext">
    <table class="table">
      <tr><td background="../static/testImg/cat0.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat1.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat2.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat3.jpeg" onclick="location.href='/authorization'"></td></tr>
      <tr><td background="../static/testImg/cat4.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat5.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat6.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat7.jpeg" onclick="location.href='/authorization'"></td></tr>
      <tr><td background="../static/testImg/cat8.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat0.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat1.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat2.jpeg" onclick="location.href='/authorization'"></td></tr>
      <tr><td background="../static/testImg/cat3.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat4.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat5.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat6.jpeg" onclick="location.href='/authorization'"></td></tr>
      <tr><td background="../static/testImg/cat7.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat8.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat0.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat1.jpeg" onclick="location.href='/authorization'"></td></tr>
      <tr><td background="../static/testImg/cat2.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat3.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat4.jpeg" onclick="location.href='/authorization'"></td>
          <td background="../static/testImg/cat5.jpeg" onclick="location.href='/authorization'"></td></tr>
    </table>
  </div>
  <div class="head">
    <button class="headButton" onclick="location.href='..'">Иннополис</button>
  </div>
</div>
</body>
</html>