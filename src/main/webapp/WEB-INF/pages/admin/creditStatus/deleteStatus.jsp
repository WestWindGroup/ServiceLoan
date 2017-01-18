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

    <title>Delete status</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet">

</head>

<body>
<div class="lang">
    <a href="<%=request.getContextPath()%>?languageVar=ua"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="container">

    <form:form method="POST" modelAttribute="status" class="form-signin">
        <h2 class="form-signin-heading">
            <spring:message code="deleteRole.head"/>
        </h2>
        <spring:bind path="status">
            <div class="form-group ${errorDelete != null ? 'has-error' : ''}">
                <form:input type="text" path="status" class="form-control" disabled="true" autofocus="true"/>

                <span>${errorDelete}</span>
            </div>
        </spring:bind>


        <button type="submit">
            <h4 class="text-center">
                <spring:message code="buttons.delete"/>
            </h4>
        </button>
    </form:form>
    <div class="block-button">
        <div class="block1-listClient">
            <button id="cancelButton"  onclick="location.href = '${contextPath}/admin/listStatuses'">
                <h4 class="text-center">
                    <spring:message code="button.backEn"/>
                </h4>
            </button>
        </div>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>