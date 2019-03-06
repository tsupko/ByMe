<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--***************************************************--%>
    <%--                  мета Bootstrap                   --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Show Ad</title>
    <%--***************************************************--%>
    <%--                  магия Bootstrap                  --%>
    <%--***************************************************--%>
    <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>"
          rel="stylesheet" id="bootstrap-css">
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
            <a class="navbar-brand" href="<c:url value="/"/>">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">

            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/about"/>">About</a></li>
                <li><a href="<c:url value="/ad/new"/>">Sell</a></li>
                <li><a href="<c:url value="/account"/>">Account</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty user}">
                    <li><a href="<c:url value="/registration"/>">Registration</a></li>
                    <li><a href="<c:url value="/login"/>">Log In</a></li>
                </c:if>
                <c:if test="${not empty user}">
                    <li><a href="<c:url value="/account"/>">${user}</a></li>
                    <li><a href="<c:url value="/logout"/>">Log Out</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="navPadding">
        <table class="table">
            <tr>
                <td><label for="name">Category</label></td>
                <td><input id="name" class="form-control" type="text" name="title" value="${category.name}" disabled></td>
            </tr>
            <tr>
                <td><label for="title">Title</label></td>
                <td><input id="title" class="form-control" type="text" name="title" value="${ad.title}" disabled></td>
            </tr>
            <tr>
                <td><label for="description">Description</label></td>
                <td><textarea id="description" class="form-control" name="text" rows="5" disabled>${ad.text}</textarea></td>
            </tr>
            <tr>
                <td><label for="price">Price</label></td>
                <td><input id="price" class="form-control" type="text" name="price" value="${ad.price}" disabled></td>
            </tr>
            <tr>
                <td><label>Photo</label></td>
                <td><img class="img-responsive" src="/static/repo/${image == null ? 'no_image.jpg' : image}" width="300" alt="Photo"></td>
            </tr>
            <tr>
                <td><label for="seller">Owner</label></td>
                <td><input id="seller" class="form-control" type="text" name="seller" value=${seller.name} disabled></td>
            </tr>
            <tr>
                <td><label for="phone">Contact</label></td>
                <td><input id="phone" class="form-control" type="text" name="phone" value=${seller.phoneNumber} disabled></td>
            </tr>
            <tr>
                <td><label for="city">City</label></td>
                <td><input id="city" class="form-control" type="text" name="city" value=${city.name} disabled></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
