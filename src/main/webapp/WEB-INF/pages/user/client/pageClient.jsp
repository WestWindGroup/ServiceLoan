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
<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="180"><spring:message code="listClient.table.client.name"/></th>
        <th width="180"><spring:message code="listClient.table.client.lastname"/></th>
        <th width="180"><spring:message code="listClient.table.client.registrationDate"/></th>
        <th width="180"><spring:message code="listClient.table.client.birthDate"/></th>
    </tr>
</table>
<div class="container">


    <form:form method="POST" modelAttribute="client" class="form-signin">
        <h2 class="form-signin-heading">
            <spring:message code="pageClient.head"/>
        </h2>

        <tr>
            <td width="80">${client.id}</td>
            <td width="180">
                <spring:bind path="firstName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="listClient.table.client.name" var="firstName"/>
                        <form:input type="text" path="firstName" class="form-control" placeholder='${firstName}'
                                   disabled="true" autofocus="true"/>

                        <form:errors path="firstName"/>
                    </div>
                </spring:bind>
            </td>
            <td width="180">
                <spring:bind path="lastName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="listClient.table.client.lastname" var="lastName"/>
                        <form:input type="text" path="lastName" class="form-control" placeholder='${lastName}'
                                    disabled="true" autofocus="true"/>

                        <form:errors path="lastName"/>
                    </div>
                </spring:bind>
            </td>
            <td width="180">
                <spring:bind path="registrationDate">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="listClient.table.client.registrationDate" var="registrationDate"/>
                        <form:input type="text" path="registrationDate" class="form-control" placeholder='${registrationDate}'
                                    disabled="true" autofocus="true"/>

                        <form:errors path="registrationDate"/>
                    </div>
                </spring:bind>
            </td>
            <td width="180">
                <spring:bind path="birthDate">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <spring:message code="listClient.table.client.birthDate" var="birthDate"/>
                        <form:input type="text" path="birthDate" class="form-control" placeholder='${birthDate}'
                                    disabled="true" autofocus="true"/>

                        <form:errors path="birthDate"/>
                    </div>
                </spring:bind>
            </td>
            <td width="80">
                <h5 class="text-center">
                    <a href="/user/editClient/${client.id}">
                        <spring:message code="pageClient.button.edit"/>
                    </a>
                </h5>
            </td>
        </tr>

    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/serviceloan.js"></script>
</body>
</html>