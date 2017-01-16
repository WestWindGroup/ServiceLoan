<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Clients</title>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet">


</head>
<body>
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>
<div class="head-listClient">
    <h1><spring:message code="listClient.head"/></h1>
</div>

<table class="tg">
    <tr>
        <th width="10%">ID</th>
        <th width="24%"><spring:message code="listClient.table.client.name"/></th>
        <th width="22%"><spring:message code="listClient.table.client.lastname"/></th>
        <th width="22%"><spring:message code="listClient.table.client.registrationDate"/></th>
        <th width="22%"><spring:message code="listClient.table.client.birthDate"/></th>
    </tr>
</table>

<c:if test="${!empty listClients}">
    <table class="tg">
        <c:forEach items="${listClients}" var="client">
            <tr>
                <td width="80">${client.id}</td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.firstName}" class="form-control"
                               autofocus="true"/>
                    </h4>
                </td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.lastName}" class="form-control"
                               autofocus="true"/>
                    </h4>
                </td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.registrationDate}" class="form-control"
                               autofocus="true"/>
                    </h4>
                </td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.birthDate}" class="form-control"
                               autofocus="true"/>
                    </h4>
                </td>
                <td width="80">
                    <h5 class="text-center">
                        <a href="/user/showClient/${client.id}">
                            <spring:message code="listClient.button.showClient"/>
                        </a>
                    </h5>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<div class="block-button">
    <div class="block2-listClient">
        <button onclick="location.href = '${contextPath}/home'">
            <h4 class="text-center">
                <spring:message code="buttons.back"/>
            </h4>
        </button>
    </div>
    <div class="block1-listClient">
        <button onclick="location.href = '${contextPath}/user/addClient'">
            <h4 class="text-center">
                <spring:message code="listClient.button.addClient"/>
            </h4>
        </button>
    </div>

</div>

</body>
</html>