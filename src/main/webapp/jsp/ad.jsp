<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--    some magic meta--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>Добавить объявлнение</title>

    <%--    from bootsnipp--%>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <style>
        .navPadding{
            padding-top: 100px;
        }
    </style>
</head>
<body>
<!-- новая шапка -->
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">

            <ul class="nav navbar-nav">
                <li><a href="/about">About</a></li>
                <li><a href="/ad">Add</a></li>
                <li><a href="/account">Account</a></li>
                <li><a href="/contact">Contact</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/registration">Registration</a></li>
                <li><a href="/login">LogIn</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="navPadding">

        <form method="post" action="/ad">
            <table class="table">
                <tr>
                    <td>Категория</td>
                    <td>

                        <select name="categoryId">
                            <c:forEach var="item" items="${categories}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Название объявления</td>
                    <td><input type="text" name="title" required autofocus></td>
                </tr>

                <tr>
                <tr>
                    <td>Описание объявления</td>
                    <td><textarea name="text" rows="6" maxlength="3000" required></textarea></td>
                </tr>
                <tr>
                <tr>
                    <td>Цена</td>
                    <td><input type="text" name="price" required pattern="\d+(\.\d{2})?"></td>
                </tr>
                <tr>
                    <td>Минимальная цена</td>
                    <td><input type="text" name="priceMin" required pattern="\d+(\.\d{2})?"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button class="headButton" type="submit">Добавить объявление</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
