<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--***************************************************--%>
    <%--             мета Bootstrapp                       --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>Добавить объявлнение</title>
    <%--***************************************************--%>
    <%--            магия Bootstrapp                       --%>
    <%--***************************************************--%>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <style>
        body {
            margin: 0;
            padding: 0;
            background: #EEE;
            font: 17px/23px 'Lucida Sans', sans-serif;
        }

        .navPadding {
            padding-top: 100px;
        }
    </style>
</head>
<body>
<%--***************************************************--%>
<%--            это шапка, не трогаем                  --%>
<%--***************************************************--%>
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">

            <ul class="nav navbar-nav">
                <li><a href="/about">About</a></li>
                <li><a href="/ad/new">Add</a></li>
                <li><a href="/account">Account</a></li>
                <li><a href="/contact">Contact</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/registration">Registration</a></li>
                <li><a href="/logout">LogOut</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="navPadding">

        <table class="table">
            <tr>
                <td>Категория</td>
                <td>
                    <select name="categoryId">
                        <c:forEach var="item" items="${categories}">
                            <option value="${item.id}" ${item.id == selected ? 'selected' : ''}
                                    disabled>${item.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Название объявления</td>
                <td><input type="text" name="title" value="${ad.title}" disabled></td>
            </tr>
            <tr>
                <td>Описание объявления</td>
                <td><textarea name="text" rows="6" disabled>${ad.text}</textarea></td>
            </tr>
            <tr>
                <td>Цена</td>
                <td><input type="text" name="price" value="${ad.price}" disabled></td>
            </tr>
            <tr>
                <td>Фотография</td>
                <td>
                        <img src="/static/repo/${image}" width="245">
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
