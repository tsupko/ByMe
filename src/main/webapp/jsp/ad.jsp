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
    <title>New Advertisement</title>
    <%--***************************************************--%>
    <%--                 магия Bootstrap                   --%>
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
            <a class="navbar-brand" href="<c:url value="/"/>">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/about"/>">About</a></li>
                <li><a href="<c:url value="/account"/>">Account</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/account"/>">Hello, ${user}</a></li>
                <li><a href="<c:url value="/logout"/>">Log Out</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Oh snap!</strong> Change a few things up and try submitting again.
        </div>
    </c:if>
    <div class="navPadding">
        <form:form method="post" enctype="multipart/form-data">
            <table class="table">
                <tr>
                    <td>Category</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="categoryId">
                                <c:forEach var="item" items="${categories}">
                                    <option value="${item.id}" ${item.id == selected ? 'selected' : ''}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Title</td>
                    <td><input class="form-control" type="text" name="title" required autofocus value="${ad.title}">
                        <input type=text hidden name="hidden"></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><textarea class="form-control" name="text" rows="6" maxlength="3000"
                                  required>${ad.text}</textarea></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input id="max" class="form-control" type="text" name="price" required pattern="\d+(\.\d{2})?" value="${ad.price}"/></td>
                </tr>
                <tr>
                    <td>Photo</td>
                    <td>
                        <c:if test="${ad.id != 0}">
                            <img class="img-responsive" src="/static/repo/${ad.id}.jpg" width="300">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="file" class="form-control-file" name="imageFile"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input id="submit" class="btn btn-success" type="submit" value="${submit}"></td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>
