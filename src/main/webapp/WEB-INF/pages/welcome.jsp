<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>

    <title>Welcome</title>


    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <style>
        .main {
            position: absolute;
            top: 20%;
            left: 40%;
            width: 20%;
            height: 20%;
        }

        .table {
            text-align: center;
        }

        .lang {
            position: absolute;
            top: 5%;
            left: 70%;
        }
    </style>

</head>
<body>
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>

<div class="main">
    <table class="table">
        <tr>
            <th><h1 class="text-center">
                <spring:message code="welcome.head"/>
            </h1></th>
        </tr>
        <tr>
            <th><h3 class="text-center"><a href="${contextPath}/login">
                <spring:message code="welcome.link.input"/>
            </a></h3></th>
        </tr>
    </table>
</div>
</body>
</html>