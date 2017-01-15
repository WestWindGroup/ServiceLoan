<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .head {
            position: absolute;
            top: 10%;
            left: 25%;
            width: 55%;
        }

        .page {
            position: absolute;
            text-align: left;
            width: auto;
            top: 20%;
            left: 35%;
        }

        .lang {
            position: absolute;
            top: 5%;
            left: 70%;
        }
        .block1 {
            width: 40%;
            float: left;
        }
        .block2 {
            width: 40%;
            float: left;
        }
        .block3 {
            width: 20%;
            float: right;
        }
    </style>
</head>
<body>
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>
<div class="head">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div class="block1">
            <h2>
                <spring:message code="home.head"/>
            </h2>
        </div>
        <div class="block2">
            <h2 style="color: #449d44" >${pageContext.request.userPrincipal.name} </h2>
        </div>
        <div class="block3">
            <h2>
                <a onclick="document.forms['logoutForm'].submit()">
                    |<spring:message code="home.link.logout"/>
                </a>
            </h2>
        </div>
    </c:if>
</div>
<div class="page">
    <h3><a href="/admin/listUsers"><spring:message code="home.link.client"/></a></h3>
    <%--<h2><a href="/admin/listTeams">Teams</a></h2>--%>
    <%--<h2><a href="/admin/listSkills">Skills</a></h2>--%>
    <%--<h2><a href="/admin/listSpecialties">Specialties</a></h2>--%>
    <%--<h2><a href="/admin/listRoles">Roles</a></h2>--%>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>