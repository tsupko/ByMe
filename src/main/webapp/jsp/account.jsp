<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--***************************************************--%>
    <%--             мета Bootstrapp                       --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>account</title>

    <%--***************************************************--%>
    <%--            магия Bootstrapp                       --%>
    <%--***************************************************--%>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <%--    css styles--%>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: #EEE;
            font: 17px/23px 'Lucida Sans', sans-serif;
        }

        .container-target {
            padding-top: 70px;
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
<%--***************************************************--%>
<%--       тут пишим код для аккаунта пользователя     --%>
<%--***************************************************--%>
<div class="container-target">
    <div class="container">
            <form method="post" action="/account">
                <table class="table">
                    <tr>
                        <td><input type=hidden name="id" value="${account.id}"/>
                        <td><input type=hidden name="password" value="${account.password}"/>
                        <td><input type=hidden name="roleId" value="${account.roleId}"/>
                        <td><input type=hidden name="actual" value="${account.actual}"/>
                    <tr>
                        <td>Login</td>
                        <td>${account.login}</td>
                    </tr>

                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value=${account.name} </td>
                    </tr>

                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value=${account.email} </td>
                    </tr>

                    <tr>
                        <td>Phone</td>
                        <td><input type="text" name="phoneNumber" value=${account.phoneNumber} </td>
                    </tr>

                    <tr>
                        <td>City</td>
                        <td><input type="text" name="city" value=${city.name} </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td>
                            <button class="headButton" type="submit">Сохранить изменения</button>
                        </td>
                    </tr>

                    <td>Объявления</td>
                    <table border="1">
                        <td>Title</td>
                        <td>Text</td>
                        <td>Price</td>
                        <td>Price_min</td>
                        <c:forEach items="${ads}" var="item">
                            <tr>
                                <td> ${ item.title } </td>
                                <td> ${ item.text } </td>
                                <td> ${ item.price } </td>
                                <td> ${ item.priceMin } </td>
                            </tr>
                        </c:forEach>
                    </table>
                </table>
            </form>
    </div>
</div>
</body>
</html>