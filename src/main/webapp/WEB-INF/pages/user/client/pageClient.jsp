<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Page client</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


</head>

<body>
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>
<div class="container">
    <div class="head-list">
        <h1><spring:message code="pageClient.head"/></h1>
    </div>
    <div class="div-client">
        <table>
            <tr>
                <th width="10%">ID</th>
                <th width="24%"><spring:message code="listClient.table.client.name"/></th>
                <th width="22%"><spring:message code="listClient.table.client.lastname"/></th>
                <th width="22%"><spring:message code="listClient.table.client.birthDate"/></th>
                <th width="22%"><spring:message code="listClient.table.client.registrationDate"/></th>
            </tr>
            <c:if test="${!empty client}">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.birthDate}</td>
                    <td>${client.registrationDate}</td>
                    <td width="80">
                        <h5>
                            <a href="/user/editClient/${client.id}">
                                <spring:message code="listClient.button.editClient"/>
                            </a>
                        </h5>
                    </td>
                </tr>
            </c:if>
        </table>
    </div>

    <div class="listCreditsClient_head2">
        <h3><spring:message code="pageClient.credits"/></h3>
    </div>

    <div class="div-credits">
        <table>
            <tr>
                <th width="6%">ID</th>
                <th width="12%"><spring:message code="listCredit.table.credit.openDate"/></th>
                <th width="12%"><spring:message code="listCredit.table.credit.shutDate"/></th>
                <th width="10%"><spring:message code="listCredit.table.credit.amount"/></th>
                <th width="10%"><spring:message code="listCredit.table.credit.debt"/></th>
                <th width="10%"><spring:message code="listCredit.table.credit.percent"/></th>
                <th width="12%"><spring:message code="listCredit.table.credit.creditType"/></th>
                <th width="12%"><spring:message code="listCredit.table.credit.creditStatus"/></th>
                <th width="10%"><spring:message code="listCredit.table.credit.creditDuration"/></th>
                <th width="6%"/>
            </tr>
            <c:if test="${!empty listCredits}">

                <c:forEach items="${listCredits}" var="credit">
                    <tr>
                        <td>${credit.id}</td>
                        <td>${credit.openDate}</td>
                        <td>${credit.shutDate}</td>
                        <td>${credit.amount}</td>
                        <td>${credit.debt}</td>
                        <td>${credit.percent.rate}</td>
                        <td>${credit.creditType.type}</td>
                        <td>${credit.creditStatus.status}</td>
                        <td>${credit.duration.duration}</td>
                        <td>
                            <h5 class="text-center">
                                <a href="/user/pageCredit/${client.id}/${credit.id}">
                                    <spring:message code="listCredit.button.showCredit"/>
                                </a>
                            </h5>
                        </td>

                    </tr>
                </c:forEach>
            </c:if>

        </table>
    </div>

    <button class="funcButton"  onclick="location.href = '${contextPath}/user/addCredit/${client.id}'">
        <h4 class="text-center">
            <spring:message code="buttons.newCredit"/>
        </h4>
    </button>
    <button class="backButton"  onclick="location.href = '${contextPath}/user/listClients'">
        <h4 class="text-center">
            <spring:message code="buttons.back"/>
        </h4>
    </button>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/serviceloan.js"></script>
</body>
</html>