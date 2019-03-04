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
    <title>New Ad</title>
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
            padding-top: 70px;
        }
    </style>
        <script>
            var loadFile = function(event) {
                var output = document.getElementById('output');
                output.src = URL.createObjectURL(event.target.files[0]);
            };
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
                <li><a href="<c:url value="/account"/>">Account</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/account"/>">${user}</a></li>
                <li><a href="<c:url value="/logout"/>">Log Out</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">
            Please change a few things and try submitting again.
        </div>
    </c:if>
    <div class="navPadding">
        <form:form method="post" modelAttribute="ad" enctype="multipart/form-data">
            <h3>Advert</h3>
            <table class="table">
                <tr>
                    <td><label for="category">Category</label></td>
                    <td>
                        <div class="form-group">
                            <select id="category" class="form-control" name="categoryId">
                                <c:forEach var="item" items="${categories}">
                                    <option value="${item.id}" ${item.id == selected ? 'selected' : ''}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label for="title">Title</label></td>
                    <td><input id="title" class="form-control" type="text" name="title" required autofocus value="${ad.title}">
                        <label><input type="text" name="hidden" hidden></label></td>
                </tr>
                <tr>
                    <td><label for="description">Description</label></td>
                    <td><textarea id="description" class="form-control" name="text" rows="6" maxlength="3000"
                                  required>${ad.text}</textarea></td>
                </tr>
                <tr>
                    <td><label for="max">Price</label></td>
                    <td><input id="max" class="form-control" type="text" name="price" required pattern="\d+(\.\d{2})?" value="${ad.price}"/></td>
                </tr>
                <tr>
                    <td><label>Photo</label></td>
                    <td><img id="output" class="img-responsive" src="/static/repo/${image == null ? 'no_image.jpg' : image}" width="300" alt="Photo"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="file" class="form-control-file" name="imageFile" accept="image/gif,image/png,image/jpeg"
                               onchange="loadFile(event)"/>
                        <form:errors path="image" cssClass="error"/>
                    </td>
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
