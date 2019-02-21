<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--    some magic meta--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>home</title>

    <%--    from bootsnipp--%>
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
    </style>
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
                <li><a href="/registration">Registration</a></li>
                <li><a href="/logout">LogOut</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
