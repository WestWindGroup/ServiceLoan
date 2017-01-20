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

    <title>List payments</title>

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
    <div class="head-listClient">
        <h1><spring:message code="pageClient.head"/></h1>
    </div>
    <c:if test="${!empty client}">
        <table class="tg">
            <tr>
                <th width="10%">ID</th>
                <th width="30%"><spring:message code="listClient.table.client.name"/></th>
                <th width="30%"><spring:message code="listClient.table.client.lastname"/></th>
            </tr>
            <tr>
                <td>${client.id}</td>
                <td>${client.firstName}</td>
                <td>${client.lastName}</td>
            </tr>
        </table>
    </c:if>
    <div class="listPayments_head">
        <h3><spring:message code="listPayments_head"/>${credit}</h3>
    </div>
    <table class="tg-credits">
        <tr>
            <th width="6%">ID</th>
            <th width="12%"><spring:message code="listCredit.table.credit.openDate"/></th>
            <th width="12%"><spring:message code="listCredit.table.credit.shutDate"/></th>
            <th width="12%"><spring:message code="listCredit.table.credit.amount"/></th>
            <th width="12%"><spring:message code="listCredit.table.credit.debt"/></th>
            <th width="10%"><spring:message code="listCredit.table.credit.percent"/></th>
            <th width="12%"><spring:message code="listCredit.table.credit.creditType"/></th>
            <th width="12%"><spring:message code="listCredit.table.credit.creditStatus"/></th>
            <th width="12%"><spring:message code="listCredit.table.credit.creditDuration"/></th>

        </tr>
        <c:if test="${!empty credit}">
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

            </tr>
        </c:if>

    </table>
    <button class="listPaymentsButton" onclick="location.href = '${contextPath}/user/listPayments/${credit.id}'">
        <h4 class="text-center">
            <spring:message code="buttons.listPayments"/>
        </h4>
    </button>
    <button class="makePaymentButton" onclick="location.href = '${contextPath}/user/makePayment/${credit.id}'">
        <h4 class="text-center">
            <spring:message code="buttons.makePayment"/>
        </h4>
    </button>
    <button class="backButton" onclick="location.href = '${contextPath}/user/pageClient/${client.id}'">
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