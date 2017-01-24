<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <title>Edit user</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet">

</head>

<body class="backgroundAdmin">
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>

<div class="formForInput">

    <form:form method="POST" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">
            <spring:message code="head.editUser.head"/>
        </h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addUser.placeholder.username" var="Username"/>
                <form:input type="text" path="username" class="form-control" placeholder='${Username}'
                            autofocus="true"/>

                <form:errors path="username"/>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addUser.placeholder.firstname" var="Firstname"/>
                <form:input type="text" path="firstName" class="form-control" placeholder='${Firstname}'
                            autofocus="true"/>
                <form:errors path="firstName"/>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addUser.placeholder.lastname" var="Lastname"/>
                <form:input type="text" path="lastName" class="form-control" placeholder='${Lastname}'
                            autofocus="true"/>
                <form:errors path="lastName"/>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">

                <spring:message code="addUser.placeholder.password" var="newPassword"/>
                <input name="newPassword" type="password" class="form-control" autofocus="true"
                       placeholder='${newPassword}'/>

                <form:errors path="password"/>

            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addUser.placeholder.confirmPassword" var="confirmPassword"/>
                <form:input type="password" path="confirmPassword" class="form-control" autofocus="true"
                            placeholder='${confirmPassword}'/>

                <form:errors path="confirmPassword"/>
            </div>
        </spring:bind>

        <button type="submit">
            <h4 class="text-center">
                <spring:message code="button.accept"/>
            </h4>
        </button>
    </form:form>

</div>
<button class="backButton"  onclick="location.href = '${contextPath}/admin/listUsers'">
    <h4 class="text-center">
        <spring:message code="button.backEn"/>
    </h4>
</button>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
