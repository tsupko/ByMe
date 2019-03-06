<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%--***************************************************--%>
    <%--                 мета Bootstrap                    --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <%--***************************************************--%>
    <%--                магия Bootstrap                    --%>
    <%--***************************************************--%>
    <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>"
          rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <%-- css styles --%>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url(https://png.pngtree.com/element_origin_min_pic/16/12/09/77619c843826cf8c9184bcf626d14f49.jpg);
            font: 17px/23px 'Lucida Sans', sans-serif;
        }

        .register {
            padding-top: 70px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/about"/>">About</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/"/>">Home</a></li>
                <li><a href="<c:url value="/registration"/>">Registration</a></li>
                <li></li>
            </ul>
        </div>
    </div>
</div>
<div class="register">
    <form id="details" method="post" action="<c:url value="/login"/>">
        <div class="container">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Login</h3>
                        </div>
                        <div class="panel-body">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger" role="alert">
                                    Invalid login or password
                                </div>
                            </c:if>
                            <form:form role="form">
                                <div class="form-group">
                                    <input required autofocus class="form-control" type="text" name="login" placeholder="Username">
                                    <div id="errLast"></div>
                                </div>
                                <div class="form-group">
                                    <input required type="password" name="password" class="form-control password-field"
                                           placeholder="Password"/>
                                </div>
                                <div class="check-box">
                                    <label>
                                        <input type="checkbox" name="remember-me" checked="checked">
                                        <small>Remember me</small>
                                    </label>
                                </div>
                                <input type="submit" value="LogIn" class="btn btn-info btn-block">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
