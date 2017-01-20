<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <title>Add credit</title>

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
        <h1><spring:message code="addCredit.head"/></h1>
    </div>
    <div class="form">
        <form:form method="POST" modelAttribute="credit" class="form-signin">

            <div class="form-group ${errorAmount != null ? 'has-error' : ''}">
                <spring:message code="addCredit.amount" var="Amount"/>
                <input name="amountInput" type="text" class="form-control" autofocus="true"
                       placeholder='${Amount}'/>
                <span>${errorAmount}</span>
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <h4><spring:message code="addCredit.percent"/></h4>
                <select name="percent" class="form-control" id="percent" >
                    <c:forEach items="${listRates}" var="rate">
                        <option value="${rate.rate}">${rate.rate}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <h4><spring:message code="addCredit.Type"/></h4>
                <select name="type" class="form-control" id="type" >
                    <c:forEach items="${listTypes}" var="type">
                        <option value="${type.type}">${type.type}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <h4><spring:message code="addCredit.status"/></h4>
                <select name="status" class="form-control" id="status" >
                    <c:forEach items="${listStatus}" var="status">
                        <c:if test="${status.status != 'repaid'}">
                            <option value="${status.status}">${status.status}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <h4><spring:message code="addCredit.duration"/></h4>
                <select name="duration" class="form-control" id="duration" >
                    <c:forEach items="${listDuration}" var="duration">
                        <option value="${duration.duration}">${duration.duration}</option>
                    </c:forEach>
                </select>
            </div>
            
            <button type="submit">
                <h4 class="text-center">
                    <spring:message code="button.accept"/>
                </h4>
            </button>
        </form:form>
    </div>
    <button class="backButton"  onclick="location.href = '${contextPath}/user/pageClient/${client.id}'">
        <h4 class="text-center">
            <spring:message code="buttons.back"/>
        </h4>
    </button>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
