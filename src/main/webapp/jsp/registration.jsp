<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--***************************************************--%>
    <%--             мета Bootstrapp                       --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>registration</title>
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
            background-image: url(https://png.pngtree.com/element_origin_min_pic/16/12/09/77619c843826cf8c9184bcf626d14f49.jpg);
            font: 17px/23px 'Lucida Sans', sans-serif;
        }

        .register {
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
            if (pass1.value == pass2.value) {
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
                <li><a href="/">Home</a></li>
                <li><a href="/login">LogIn</a></li>
                <li></li>
            </ul>
        </div>
    </div>
</div>

<div class="register">
    <form id="details" method="post" action="/registration" modelAttribute="user">
        <div class="container">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Registration
                                <small> enjoy with ByMeService...</small>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    <input
                                            class="form-control"
                                            type="text"
                                            name="login"
                                            id="login"
                                            minlength="5" maxlength="30"
                                            placeholder="login">

                                    <div id="errLast"></div>
                                </div>
                                <%--валидация пароля--%>
                                <div class="form-group">
                                    <input
                                            type="password"
                                            name="password"
                                            id="pass1"
                                            minlength="5" maxlength="15"
                                            class="form-control password-field"
                                            placeholder="password"/>
                                </div>
                                <div class="form-group">
                                    <input
                                            type="password"
                                            name="password"
                                            id="pass2"
                                            class="form-control password-field"
                                            placeholder="enter again to validate"
                                            onkeyup="passwordEqualsValidation(); return false;"/>
                                    <span id="confirmMessage" class="confirmMessage"></span>
                                </div>
                                <div class="form-group">
                                    <input
                                            type="text"
                                            name="name"
                                            id="name"
                                            minlength="5" maxlength="30"
                                            class="form-control"
                                            placeholder="name">
                                </div>
                                <div class="form-group">
                                    <input
                                            type="number"
                                            name="phoneNumber"
                                            id="phoneNumber"
                                            class="form-control phone"
                                            placeholder="phone">
                                </div>
                                <div class="form-group">
                                    <input
                                            type="text"
                                            name="email"
                                            id="email"
                                            class="form-control"
                                            placeholder="Email Address"/>
                                </div>
                                <input type="submit" value="Register" class="btn btn-info btn-block">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
