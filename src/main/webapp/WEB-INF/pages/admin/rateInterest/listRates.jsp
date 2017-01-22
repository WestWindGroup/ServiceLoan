<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <title>Rate</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

</head>
<body class="backgroundAdmin">
<h1 class="heading">Rates</h1>

<c:if test="${!empty listRates}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Rate</th>
        </tr>
        <c:forEach items="${listRates}" var="rate">
            <tr>
                <td>${rate.id}</td>
                <td>${rate.rate}</td>
                <td width="50"><a href="/admin/editRate/${rate.id}">Edit</a></td>
                <td width="50"><a href="/admin/deleteRate/${rate.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<div class="block-button">
    <div class="block2-listClient">

    </div>
    <div class="block1-listClient">

    </div>
</div>
<button class="funcButton" onclick="location.href = '${contextPath}/admin/addRate'">
    <h4 class="text-center">
        <spring:message code="button.create"/>
    </h4>
</button>
<button class="backButton" onclick="location.href = '${contextPath}/admin'">
    <h4 class="text-center">
        <spring:message code="button.backEn"/>
    </h4>
</button>
</body>
</html>