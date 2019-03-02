<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%--***************************************************--%>
    <%--                  мета Bootstrap                   --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Account</title>
    <%--***************************************************--%>
    <%--                 магия Bootstrap                   --%>
    <%--***************************************************--%>
    <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>" rel="stylesheet"
          id="bootstrap-css">
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

        table {
            counter-reset: tableCount;
        }

        .counterCell:before {
            content: counter(tableCount);
            counter-increment: tableCount;
        }
    </style>
    <script type="text/javascript">
        function passwordEqualsValidation() {
            var pass1 = document.getElementById('pass1');
            var pass2 = document.getElementById('pass2');
            var message = document.getElementById('confirmMessage');
            var goodColor = "#66cc66";
            var badColor = "#ff6666";
            if (pass1.value === pass2.value) {
                pass2.style.backgroundColor = goodColor;
                message.style.color = goodColor;
                message.innerHTML = "Passwords Match"
            } else {
                pass2.style.backgroundColor = badColor;
                message.style.color = badColor;
                message.innerHTML = "Passwords Do Not Match!"
            }
        }
    </script>
</head>
<body>
<%--***************************************************--%>
<%--            это шапка, не трогаем                  --%>
<%--***************************************************--%>

<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/about"/>">About</a></li>
                <li><a href="<c:url value="/ad/new"/>">Add</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/account"/>">Hello, ${account.login}</a></li>
                <li><a href="<c:url value="/logout"/>">Log Out</a></li>
            </ul>
        </div>
    </div>
</div>
<%--***************************************************--%>
<%--       тут пишем код для аккаунта пользователя     --%>
<%--***************************************************--%>

<div class="container-target">
    <div class="container">
        <div class="row">

            <%--            <form:form method="post" action="ava_update">--%>
            <%--                <div class="col-12">--%>
            <%--                    <img src="<c:url value="/static/repo/17.jpg"/>" class="img-circle" alt="Cinque Terre"--%>
            <%--                         width="300"><br>--%>
            <%--                    <input class="btn btn-success" type="submit" value="Update"/>--%>
            <%--                </div>--%>
            <%--            </form:form>--%>

            <form:form method="post" action="/account">
                <div class="col-12">
                    <h3>Account</h3>
                    <input type=hidden name="id" value="${account.id}"/>
                    <input type=hidden name="login" value="${account.login}"/>
                    <input type=hidden name="password" value="${account.password}"/>
                    <input type=hidden name="roleId" value="${account.roleId}"/>
                    <input type=hidden name="actual" value="${account.actual}"/>
                    <table class="table">
                        <thead class="thead-dark">
                        <tbody>
                        <tr>
                            <td>Login</td>
                            <td>${account.login}</td>
                        <tr>
                            <td>Username</td>
                            <td><input class="form-control" type="text" name="name" value=${account.name} required></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input class="form-control" type="email" name="email" value=${account.email} required>
                            </td>
                        </tr>
                        <tr>
                            <td>Phone Number</td>
                            <td><input class="form-control" type="number" name="phoneNumber" value=${account.phoneNumber} required></td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td>
                                <div class="form-group">
                                    <select class="form-control" name="cityId">
                                        <option value="${city.id}">${city.name}</option>
                                        <c:forEach var="item" items="${cities}">
                                            <c:if test="${account.cityId != item.id}">
                                                <option value="${item.id}" ${item.id == selected ? 'selected' : ''}>${item.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input class="btn btn-success" type="submit" value="Update"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </form:form>
        </div>
    </div>

    <%--***************************************************--%>
    <%--       таблица объявлений отдельным дивом          --%>
    <%--***************************************************--%>

    <div class="row">
        <div class="container">
            <h3>Adverts</h3>
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                    <tr>
                        <th style="text-align: center">#</th>
                        <th style="text-align: center">Title</th>
                        <th style="text-align: center">Text</th>
                        <th style="text-align: center">Price</th>
                        <th style="text-align: center"></th>
                        <th style="text-align: center"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ads}" var="item">
                        <tr>
                            <th class="counterCell"></th>
                            <td>${item.title}</td>
                            <td>${item.text}</td>
                            <td>${item.price}</td>
                            <td><a href="/ad/edit/${item.id}" type="button" class="btn btn-info">update</a></td>
                            <td><a href="/ad/delete/${item.id}" type="button" class="btn btn-danger">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>