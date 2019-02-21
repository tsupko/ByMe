<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Добавление объявления</title>
    <link href="css/ad.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="head">
</div>

<form method="post" action="${pageContext.request.contextPath}/ad">
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
            <td><button class="headButton" type="submit">Добавить объявление</button></td>
        </tr>
    </table>
</form>
</html>
