<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--    some magic meta--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>about</title>

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
    <link href="css/about.css" rel="stylesheet" , type="text/css">
</head>

<body>
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/account">Account</a></li>
                <li><a href="/contact">Contact</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="[ col-sm-6 col-md-offset-2 col-md-4 ]">
            <div class="[ info-card ]">
                <img style="width: 100%"
                     src="https://avatars.githubusercontent.com/u/14054783"/>
                <div class="[ info-card-details ] animate">
                    <div class="[ info-card-header ]">
                        <h1> Cat Developer </h1>
                        <h3> DevCat </h3>
                    </div>
                    <div class="[ info-card-detail ]">
                        <!-- Description -->
                        <p>
                            Привет всем! Я обычный кот - девелопер.
                            Тружусь на благо компании, занимаюсь разработкой
                            клиентской части приложений, дизайном и версткой.
                            Главной задачей для меня всегда явзялось создание
                            отзывчивого дизайна, простого для использования
                            любому существу на планете (даже мышам!)
                            Я очень люблю свою работу.
                            А еще я работаю за еду, поэтому если вдруг у вас есть интересная
                            задача и вкусная еда - можете смело обращаться ко мне!
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
                            <a href="www.linkedin.com/"
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
                <img style="width: 100%" src="https://pbs.twimg.com/media/DbshRJiWAAAbBw0.jpg"/>
                <div class="[ info-card-details ] animate">
                    <div class="[ info-card-header ]">
                        <h1> Dog Developer </h1>
                        <h3> DevDat </h3>
                    </div>
                    <div class="[ info-card-detail ]">
                        <p>
                            Привет народ! От имени всех псов девелоперов
                            категорически вас приветствую! Я занимаюсь разработкой
                            высогонагруженых серверных решений для малого бизнеса.
                            Задачи отказоустойчивости и безопасности,
                            хранение и обработка больших данных - все это про меня.
                            И хоть нам и приходится работать бок о бок с кошачими
                            мы научились находить общий язык, ведь все мы в итоге занимаемся
                            общим делом которое очень любим! Гав!
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
                            <a href="www.linkedin.com/"
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
