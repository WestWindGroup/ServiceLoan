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

    <title>Page credit</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


</head>

<body class="background">
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>
<div>
    <div class="headPageCredit">
        <h2><spring:message code="head.pageCredit.head"/>:  ${client.lastName} ${client.firstName}</h2>
    </div>

    <div class="headPageCreditCredit">
        <h3><spring:message code="pageClient.credit"/></h3>
    </div>
    <div>
        <table class="credit">
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
                <div>
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
                </div>
            </c:if>

        </table>
    </div>

    <div class="paymentsCredit_head">
        <h3><spring:message code="head.listPaymentsCredit_head"/></h3>
    </div>
    <div class="paymentsHeat">
        <table>
            <tr>
                <th width="8%">ID</th>
                <th width="23%"><spring:message code="listPayments.table.amount"/></th>
                <th width="23%"><spring:message code="listPayments.table.body"/></th>
                <th width="23%"><spring:message code="listPayments.table.rate"/></th>
                <th width="23%"><spring:message code="listPayments.table.date"/></th>
            </tr>
        </table>
    </div>
    <div class="payments">
        <table>
            <c:if test="${!empty listPayments}">
                <c:forEach items="${listPayments}" var="payment">
                    <tr>
                        <td width="8%">${payment.id}</td>
                        <td width="23%">${payment.amountPayment}</td>
                        <td width="23%">${payment.bodyCredit}</td>
                        <td width="23%">${payment.ratePayment}</td>
                        <td width="23%">${payment.paymentDate}</td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>
    </div>

    <button class="listPaymentsButton" onclick="location.href = '${contextPath}/user/listPayments/${client.id}/${credit.id}'">
        <h4 class="text-center">
            <spring:message code="buttons.listPayments"/>
        </h4>
    </button>
    <c:if test="${!credit.creditStatus.status.equals('repaid')}">
        <button class="makePaymentButton" onclick="location.href = '${contextPath}/user/makePayment/${client.id}/${credit.id}'">
            <h4 class="text-center">
                <spring:message code="buttons.makePayment"/>
            </h4>
        </button>
    </c:if>
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