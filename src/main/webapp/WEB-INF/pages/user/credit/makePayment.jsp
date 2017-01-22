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

    <title>Make payment</title>

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
<div class="container">
    <div class="madePayment-head">
        <h2><spring:message code="madePayment.head"/>${credit.id}</h2>
    </div>

    <div class="min_payment">
        <h3><spring:message code="madePayment.min.payment"/> ${minPayment}</h3>
    </div>
    <div class="max_payment">
        <h3><spring:message code="madePayment.max.payment"/> ${maxPayment}</h3>
    </div>

    <form:form method="POST" modelAttribute="payment" class="form-signin">
        <div class="form">
            <h2 class="form-signin-heading">
                <spring:message code="madePayment.input.payment"/>
            </h2>
            <div class="form-group ${errorPayment != null ? 'has-error' : ''}">
                <spring:message code="madePayment.payment" var="NamePayment"/>
                <input name="paymentInput" type="text" class="form-control" autofocus="true"
                       placeholder='${NamePayment}'/>
                <span>${errorPayment}</span>
            </div>
        </div>
        <c:if test="${!credit.creditStatus.status.equals('repaid')}">
            <button class="payButton" type="submit">
                <h4 class="text-center">
                    <spring:message code="buttons.makePaymentNow"/>
                </h4>
            </button>
        </c:if>

    </form:form>

    <button class="backButton" onclick="location.href = '${contextPath}/user/pageCredit/${client.id}/${credit.id}'">
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