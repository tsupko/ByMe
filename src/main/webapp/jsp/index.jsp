<%@ page import="ru.innopolis.byme.transfer.CategoryTree" %>
<%@ page import="java.util.List" %>
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
    <title>Main Page</title>
    <%--***************************************************--%>
    <%--                магия Bootstrap                    --%>
    <%--***************************************************--%>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'/>
    <link href="<c:url value="//bootstrap-combobox-test.herokuapp.com/css/bootstrap-combobox.css"/>" media="screen"
          rel="stylesheet" type="text/css">
    <link rel="canonical" href="http://bootstrapessentials.com/fulldocs/components/navbar/navbar-submenu/"/>

    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
    <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <script type='text/javascript'>
        $(document).ready(function () {
            $(".dropdown-menu li a").click(function () {
                var selText = $(this).text();
                $(this).parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
            });
        });
    </script>

    <%-- css styles --%>
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

        .navbar .divider-vertical {
            height: 50px;
            margin: 0 9px;
            border-right: 1px solid #ffffff;
            border-left: 1px solid #f2f2f2;
        }

        .navbar-inverse .divider-vertical {
            border-right-color: #222222;
            border-left-color: #111111;
        }

        .navbar-nav {
            line-height: 50px;
        }

        @media (max-width: 767px) {
            .navbar-collapse .nav > .divider-vertical {
                display: none;
            }
        }
    </style>
</head>

<body class="no-touch">

<!-- новая шапка -->
<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>">ByMeService</a>
        </div>
        <div class="navbar-collapse collapse">
            <form:form class="form-inline" method="post" action="/">
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/about"/>">About</a></li>
                    <c:if test="${not empty user}">
                        <li><a href="<c:url value="/ad/new"/>">Sell</a></li>
                        <li><a href="<c:url value="/account"/>">Account</a></li>
                    </c:if>
                </ul>

                <ul class="nav navbar-nav">
                    <li class="divider-vertical"></li>
                    <select class="form-control" name="categoryId" onchange="this.form.submit()">
                        <%
                            List<CategoryTree> categoryTree = (List<CategoryTree>) request.getAttribute("categoryList");
                            out.print("<option selected hidden value=\"" + categoryTree.get(0).getId() + "\">" + categoryTree.get(0).getName() + "</option>");
                            for (int i = 1; i < categoryTree.size(); i += 1) {
                                out.print(" <option value=\"" + categoryTree.get(i).getId() + "\">");%>

                        <% for (int j = 0; j < categoryTree.get(i).getLevel(); j += 1) {
                            out.print("&nbsp;");%>
                        <% } %>  <%=categoryTree.get(i).getName()%>

                        <%
                                out.print("</option>");
                            } %>
                    </select>

                    <select class="form-control" name="cityId" onchange="this.form.submit()">
                        <c:forEach var="i" begin="0" end="${cityList.size()-1}">
                            <c:if test="${i==0}">
                                <option selected hidden value="${cityList.get(i).id}">${cityList.get(i).name}</option>
                            </c:if>
                            <c:if test="${i>0}">
                                <option value="${cityList.get(i).id}">${cityList.get(i).name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </ul>


                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${not empty user}">
                        <li><a href="<c:url value="/account"/>">${user}</a></li>
                    </c:if>
                    <c:if test="${empty user}">
                        <li><a href="<c:url value="/registration"/>">Registration</a></li>
                    </c:if>
                    <li><a href="${logUrl}">${logStatus}</a></li>
                    <li><a></a></li>
                </ul>
            </form:form>
        </div>
    </div>
</div>

<!-- динамическая таблица картинок -->
<div class="wrap">
    <c:forEach items="${list}" var="item">
        <div class="box">
            <div class="boxInner">
                <a href="/ad/view/${item.id}">
                    <img class="img-responsive" src="/static/repo/${item.image.img == null ? 'no_image.jpg' : item.image.img}" alt="Photo"></a>
                <div class="titleBox">${item.title} - ${item.price} руб.</div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
