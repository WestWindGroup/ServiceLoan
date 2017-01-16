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

    <title>Add client</title>

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

<div class="edit">

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
                <form:input type="date" max="2012-06-04" min="2012-05-29" path="birthDate" class="form-control"
                            placeholder='${birthDate}' autofocus="true"/>

                <form:errors path="birthDate"/>
            </div>
        </spring:bind>

    </form:form>
</div>
<div class="messagePassword">
    <h4>
        <spring:message code="confirmPassword"/>
    </h4>
</div>
<div class="block-password">
    <div class="addClientLeft">
        <jsp:include page="/WEB-INF/pages/fragments/inputPassword.jsp"/>
    </div>
    <div class="addClientRight">
        <button type="submit">
            <h4>
                <spring:message code="addClient.buttons.add"/>
            </h4>
        </button>
    </div>
</div>
<div class="block-button">
    <div class="block2-listClient">
        <button onclick="location.href = '${contextPath}/user/listClients'">
            <h4>
                <spring:message code="buttons.back"/>
            </h4>
        </button>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
