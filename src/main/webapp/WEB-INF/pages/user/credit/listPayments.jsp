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
    <div class="head-list">
        <h3><spring:message code="listPaymentsTimetable_head"/> ${credit.id}</h3>
    </div>
    <div class="div-listPayments">
        <table>
            <tr>
                <th width="23%"><spring:message code="listPayments.table.amount"/></th>
                <th width="23%"><spring:message code="listPayments.table.body"/></th>
                <th width="23%"><spring:message code="listPayments.table.rate"/></th>
                <th width="23%"><spring:message code="listPayments.table.date"/></th>
            </tr>
            <c:if test="${!empty listPayments}">
                <c:forEach items="${listPayments}" var="payment">
                    <tr>
                        <td>${payment.amountPayment}</td>
                        <td>${payment.bodyCredit}</td>
                        <td>${payment.ratePayment}</td>
                        <td>${payment.paymentDate}</td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>
    </div>
    <button class="backButton" onclick="location.href = '${contextPath}/user/pageCredit/${client}/${credit.id}'">
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