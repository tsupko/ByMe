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
    <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>"
          rel="stylesheet" id="bootstrap-css">
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
        <form:form method="post" action="/account">
            <table class="table">
                <tr>
                    <td><input type=hidden name="id" value="${account.id}"/>
                    <td><input type=hidden name="login" value="${account.login}"/>
                    <td><input type=hidden name="password" value="${account.password}"/>
                    <td><input type=hidden name="roleId" value="${account.roleId}"/>
                    <td><input type=hidden name="actual" value="${account.actual}"/>
                <tr>
                    <td>Login</td>
                    <td>${account.login}</td>
                </tr>

<%--                TODO change password--%>
<%--                <tr>--%>
<%--                    <td>Password</td>--%>
<%--                    <td> <input--%>
<%--                            type="password"--%>
<%--                            name="password"--%>
<%--                            id="pass1"--%>
<%--                            minlength="5" maxlength="15"--%>
<%--                            class="form-control password-field"--%>
<%--                            placeholder="enter password"/></td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td>Password</td>--%>
<%--                    <td><input--%>
<%--                            type="password"--%>
<%--                            id="pass2"--%>
<%--                            class="form-control password-field"--%>
<%--                            placeholder="enter password again to validate"--%>
<%--                            onkeyup="passwordEqualsValidation(); return false;"/>--%>
<%--                        <span id="confirmMessage" class="confirmMessage"></span></td>--%>
<%--                </tr>--%>

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
                    <td>
                        <select name="cityId">
                            <option value="${city.id}">${city.name}</option>
                            <c:forEach var="item" items="${cities}">
                                <c:if test="${account.cityId!=item.id}" >
                                    <option value="${item.id}" ${item.id == selected ? 'selected' : ''}>${item.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <td>
                        <button class="headButton" type="submit" >Сохранить изменения</button>
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
                            <td> <a href="/ad/edit/${item.id}">${ item.title }</a>  </td>
                            <td> ${ item.text } </td>
                            <td> ${ item.price } </td>
                            <td> ${ item.priceMin } </td>
                            <td>
                                <a href="/ad/delete/${item.id}">удалить</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>