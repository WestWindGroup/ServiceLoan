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

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>
<jsp:include page="../fragments/initialHeader.jsp"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="container">

    <a href="<%=request.getContextPath()%>?languageVar=en"><spring:message code="general.EN"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">
            <spring:message code="signUp.head"/>
        </h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="signUp.placeholder.username" var="Username"/>
                <form:input type="text" path="username" class="form-control" placeholder='${Username}' autofocus="true"/>

                <form:errors path="username"/>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="signUp.placeholder.firstname" var="Firstname"/>
                <form:input type="text" path="firstName" class="form-control" placeholder='${Firstname}'
                            autofocus="true"/>
                <form:errors path="firstName"/>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="signUp.placeholder.lastname" var="Lastname"/>
                <form:input type="text" path="lastName" class="form-control" placeholder='${Lastname}'
                            autofocus="true"/>
                <form:errors path="lastName"/>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="signUp.placeholder.password" var="Password"/>
                <form:input type="password" path="password" class="form-control"
                            placeholder='${Password}'/>
                <form:errors path="password"/>
            </div>
        </spring:bind>


        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="signUp.placeholder.confirmPassword" var="confirmPassword"/>
                <form:input type="password" path="confirmPassword" class="form-control"
                            placeholder='${confirmPassword}'/>
                <form:errors path="confirmPassword"/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="signUp.placeholder.email" var="Email"/>
                <form:input type="text" path="email" class="form-control" placeholder='${Email}'
                            autofocus="true"/>
                <form:errors path="email"/>
            </div>
        </spring:bind>

        <h2 class="form-signin-heading">
            <spring:message code="signUp.selectUserType"/>
        </h2>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:radiobutton path="userType" value="customer" onclick="EnableSubmit()"/>
            <spring:message code="signUp.value.customer"/>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:radiobutton path="userType" value="specialist" onclick="EnableSubmit()"/>
            <spring:message code="signUp.value.specialist"/>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <spring:message code="signUp.buttons.submit"/>
        </button>
    </form:form>

</div>
<!-- /container -->

<jsp:include page="../fragments/initialFooter.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
