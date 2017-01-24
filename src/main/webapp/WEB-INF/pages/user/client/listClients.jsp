<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Clients</title>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet">


</head>
<body class="background">
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>

<div class="listClientHead">
    <h1><spring:message code="listClient.head"/></h1>
</div>

<div class="divListClientHead">
    <table>
        <tr>
            <th width="10%">ID</th>
            <th width="18%"><spring:message code="listClient.table.client.name"/></th>
            <th width="22%"><spring:message code="listClient.table.client.lastname"/></th>
            <th width="22%"><spring:message code="listClient.table.client.birthDate"/></th>
            <th width="22%"><spring:message code="listClient.table.client.registrationDate"/></th>
            <th width="6%"/>
        </tr>
    </table>
</div>
<div class="listCreditClient">
    <table>
        <c:if test="${!empty listClients}">
            <c:forEach items="${listClients}" var="client">
                <tr>
                    <td width="10%">${client.id}</td>
                    <td width="18%">${client.firstName}</td>
                    <td width="22%">${client.lastName}</td>
                    <td width="22%">${client.birthDate}</td>
                    <td width="22%">${client.registrationDate}</td>
                    <td width="6%">
                        <h5 class="text-center">
                            <a href="/user/pageClient/${client.id}">
                                <spring:message code="listClient.button.showClient"/>
                            </a>
                        </h5>
                    </td>

                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<button class="funcButton" onclick="location.href = '${contextPath}/user/addClient'">
    <h4 class="text-center">
        <spring:message code="listClient.button.addClient"/>
    </h4>
</button>
<button class="backButton" onclick="location.href = '${contextPath}/home'">
    <h4 class="text-center">
        <spring:message code="buttons.back"/>
    </h4>
</button>


</body>
</html>