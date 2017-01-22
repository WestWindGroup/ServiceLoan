<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add client</title>

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

<div class="formForInput">

    <form:form method="POST" modelAttribute="client" class="form-signin">
        <h2 class="form-signin-heading">
            <spring:message code="addClient.head"/>
        </h2>
        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="listClient.table.client.name" var="firstName"/>
                <form:input type="text" path="firstName" class="form-control" placeholder='${firstName}'
                            autofocus="true"/>

                <form:errors path="firstName"/>
            </div>
        </spring:bind>
        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="listClient.table.client.lastname" var="lastName"/>
                <form:input type="text" path="lastName" class="form-control" placeholder='${lastName}'
                            autofocus="true"/>

                <form:errors path="lastName"/>
            </div>
        </spring:bind>

        <spring:bind path="birthDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="listClient.table.client.birthDate" var="birthDate"/>
                <form:input type="date" min="1899-01-01" max="2099-01-01" path="birthDate" class="form-control"
                            placeholder='${birthDate}' autofocus="true"/>

                <form:errors path="birthDate"/>
            </div>
        </spring:bind>
        <button type="submit">
            <h4 class="text-center">
                <spring:message code="button.accept"/>
            </h4>
        </button>
    </form:form>

</div>
<button class="backButton"  onclick="location.href = '${contextPath}/user/listClients'">
    <h4 class="text-center">
        <spring:message code="buttons.back"/>
    </h4>
</button>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
