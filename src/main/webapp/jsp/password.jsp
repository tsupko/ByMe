<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--***************************************************--%>
    <%--                   мета Bootstrap                  --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <%--***************************************************--%>
    <%--                  магия Bootstrap                  --%>
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
    <script type="text/javascript">
        function passwordEqualsValidation() {
            var pass1 = document.getElementById('pass1');
            var pass2 = document.getElementById('pass2');
            var message = document.getElementById('confirmMessage');
            var goodColor = "#66cc66";
            var badColor = "#ff6666";
            var threshold = 3;
            if (pass1.value.length < threshold || pass2.value.length < threshold) {
                pass2.style.backgroundColor = "";
                message.innerHTML = "";
            } else {
                if (pass1.value === pass2.value) {
                    pass2.style.backgroundColor = goodColor;
                    message.style.color = goodColor;
                    message.innerHTML = "Passwords match.";
                } else {
                    pass2.style.backgroundColor = badColor;
                    message.style.color = badColor;
                    message.innerHTML = "Passwords do not match.";
                }
            }
        }

        function checkPass() {
            document.getElementById('submit').disabled = document.getElementById('pass1').value !== document.getElementById('pass2').value;
        }
    </script>
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
                <li><a href="<c:url value="/ad/new"/>">Sell</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/account"/>">${user.login}</a></li>
                <li><a href="<c:url value="/logout"/>">Log Out</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="register">
    <form id="details" method="post" action="<c:url value="/password"/>">
        <div class="container">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Change Password</h3>
                        </div>
                        <div class="panel-body">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger" role="alert">
                                    ${param.error}
                                </div>
                            </c:if>
                            <form:form role="form">
                                <input type=hidden name="id" value="${user.id}"/>
                                <input type=hidden name="login" value="${user.login}"/>
                                <%--валидация пароля--%>
                                <div class="form-group">
                                    <input required type="password" name="password" id="pass1" minlength="5" maxlength="30" class="form-control password-field" placeholder="new password" onkeyup="passwordEqualsValidation(); return true;" onchange='checkPass();'/>
                                </div>
                                <div class="form-group">
                                    <input required type="password" id="pass2" class="form-control password-field" placeholder="enter new password again" onkeyup="passwordEqualsValidation(); return false;" onchange='checkPass();'/>
                                    <span id="confirmMessage" class="confirmMessage"></span>
                                </div>
                                <input type="submit" value="Save" class="btn btn-info btn-block" id="submit" disabled>
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
