<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

</head>
<body>
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>
<div class="head">
    <security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/>
    <security:authorize access="hasRole('ROLE_USER')" var="isUser"/>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div class="block1-home">
            <h2>
                <spring:message code="home.head"/>
            </h2>
        </div>
        <div class="block2-home">
            <h2>${pageContext.request.userPrincipal.name} </h2>
        </div>
        <div class="block3-home">
            <h2>
                <a onclick="document.forms['logoutForm'].submit()">
                    |<spring:message code="home.link.logout"/>
                </a>
            </h2>
        </div>
    </c:if>
</div>
<div class="page">
    <c:if test="${isUser}">
        <h3><a href="/user/listClients"><spring:message code="home.link.client"/></a></h3>
    </c:if>

    <c:if test="${isAdmin}">
        <h3><a href="/admin" ><spring:message code="home.link.adminPage"/></a></h3>
    </c:if>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>