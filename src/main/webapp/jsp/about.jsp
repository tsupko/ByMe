<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--***************************************************--%>
    <%--                  мета Bootstrap                   --%>
    <%--***************************************************--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>About</title>
    <%--***************************************************--%>
    <%--                  магия Bootstrap                  --%>
    <%--***************************************************--%>
    <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>"
          rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <%-- css styles --%>
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
            <a class="navbar-brand" href="<c:url value="/"/>">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/ad/new"/>">Sell</a></li>
                <li><a href="<c:url value="/account"/>">Account</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty user}">
                    <li><a href="<c:url value="/account"/>">${user}</a></li>
                </c:if>
                <c:if test="${empty user}">
                    <li><a href="<c:url value="/registration"/>">Registration</a></li>
                </c:if>
                <li><a href="${logUrl}">${logStatus}</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="[ col-sm-6 col-md-offset-2 col-md-4 ]">
            <div class="[ info-card ]">
                <br/><br/><br/>
                <a href="<c:url value="/"/>">
                <img style="width: 70%"
                     src="https://pbs.twimg.com/media/DbshRJiWAAAbBw0.jpg"
                     alt="Dog Software Developer"/>
                </a>
                <div class="[ info-card-details ] animate">
                    <div class="[ info-card-header ]">
                        <h2>Welcome!</h2>
                    </div>
                    <div class="[ info-card-detail ]">
                        <p>
                            <b>ByMe</b> is a great place to put your ads on!<br/>
                            It allows not only to sell your stuff, but also
                            to do that with time-varying price.<br/>
                            <br/>
                            Feel free to click <b><a href="<c:url value="/registration"/>">Registration</a></b><br/>
                            if you are not yet registered here.<br/>
                            <br/>
                            Otherwise, click <b><a href="<c:url value="/login"/>">Log In</a></b>
                            <br/>
                            to start adding your ads, view other people's ads, and have a good time!
                        </p>
                        <div class="social">
                            <a href="https://www.facebook.com/"
                               class="[ social-icon facebook ] animate">
                                <span class="fa fa-facebook"></span>
                            </a>
                            <a href="https://twitter.com/"
                               class="[ social-icon twitter ] animate">
                                <span class="fa fa-twitter"></span>
                            </a>
                            <a href="https://github.com/"
                               class="[ social-icon github ] animate">
                                <span class="fa fa-github-alt"></span>
                            </a>
                            <a href="https://www.linkedin.com/"
                               class="[ social-icon linkedin ] animate">
                                <span class="fa fa-linkedin"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="[ col-sm-6 col-md-4 ]">
            <div class="[ info-card ]">
                <br/><br/><br/>
                <a href="<c:url value="/"/>">
                <img style="width: 47%"
                     src="<c:url value="/static/favicon/ads.svg"/>"
                     alt="Ads Favicon"/>
                </a>
                <div class="[ info-card-details ] animate">
                    <div class="[ info-card-header ]">
                        <h2>Добро пожаловать!</h2>
                    </div>
                    <div class="[ info-card-detail ]">
                        <p>
                            <b>ByMe</b> &ndash; отличное место для Ваших объявлений!
                            Сервис позволяет не только продавать вещи, но и задавать
                            изменение их цены со временем.<br/>
                            <br/>
                            Нажмите, пожалуйста, кнопку <b><a href="<c:url value="/registration"/>">Регистрация</a></b>,
                            если Вы пока ещё не зарегистрированы здесь.<br/>
                            <br/>
                            Иначе нажмите кнопку <b><a href="<c:url value="/login"/>">Войти</a></b>,
                            чтобы добавить Ваше объявление, посмотреть другие
                            и хорошо провести время!
                        </p>
                        <div class="social">
                            <a href="https://www.facebook.com/"
                               class="[ social-icon facebook ] animate">
                                <span class="fa fa-facebook"></span>
                            </a>
                            <a href="https://twitter.com/"
                               class="[ social-icon twitter ] animate">
                                <span class="fa fa-twitter"></span>
                            </a>
                            <a href="https://github.com/"
                               class="[ social-icon github ] animate">
                                <span class="fa fa-github-alt"></span>
                            </a>
                            <a href="https://www.linkedin.com/"
                               class="[ social-icon linkedin ] animate">
                                <span class="fa fa-linkedin"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
