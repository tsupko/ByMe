<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--***************************************************--%>
    <%--             мета Bootstrapp                       --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>Главная страница</title>
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

        .wrap {
            padding-top: 50px;
            overflow: hidden;
            margin: 10px;
        }

        .box {
            float: left;
            position: relative;
            width: 20%;
            padding-bottom: 20%;
        }

        .boxInner {
            position: absolute;
            left: 10px;
            right: 10px;
            top: 10px;
            bottom: 10px;
            overflow: hidden;
        }

        .boxInner img {
            object-fit: cover;
            width: 100%;
            height: 100%;
        }

        .boxInner .titleBox {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            margin-bottom: -50px;
            background: #000;
            background: rgba(0, 0, 0, 0.5);
            color: #FFF;
            padding: 10px;
            text-align: center;
            -webkit-transition: all 0.3s ease-out;
            -moz-transition: all 0.3s ease-out;
            -o-transition: all 0.3s ease-out;
            transition: all 0.3s ease-out;
        }

        body.no-touch .boxInner:hover .titleBox,
        body.touch .boxInner.touchFocus .titleBox {
            margin-bottom: 0;
        }

        .container {

        }
    </style>
</head>

<body class="no-touch">

<!-- новая шапка -->
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">

            <ul class="nav navbar-nav">
                <li><a href="/about">About</a></li>
                <li><a href="/ad">Add</a></li>
                <li><a href="/account">Account</a></li>
                <li><a href="/contact">Contact</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/registration">Registration</a></li>
                <li><a href="/login">LogIn</a></li>
            </ul>
        </div>
    </div>
</div>

<!-- динамическая таблица картинок -->
<div class="wrap">
    <c:forEach items="${list}" var="item">
        <div class="box">
            <div class="boxInner">
                <a href="/"><img src=${item}></a>
                <div class="titleBox">${item}</div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>