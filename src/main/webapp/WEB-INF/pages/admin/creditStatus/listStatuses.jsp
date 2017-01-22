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

    <title>Credit Status</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

</head>
<body class="backgroundAdmin">
<h1 class="heading">Credit statuses</h1>

<c:if test="${!empty listStatuses}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Status</th>
        </tr>
        <c:forEach items="${listStatuses}" var="status">
            <tr>
                <td>${status.id}</td>
                <td>${status.status}</td>
                <td width="80"><a href="/admin/editStatus/${status.id}">Edit</a></td>
                <td width="80"><a href="/admin/deleteStatus/${status.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<button class="backButton" onclick="location.href = '${contextPath}/admin'">
    <h4 class="text-center">
        <spring:message code="button.backEn"/>
    </h4>
</button>
<button class="funcButton" onclick="location.href = '${contextPath}/admin/addStatus'">
    <h4 class="text-center">
        <spring:message code="button.create"/>
    </h4>
</button>
</body>
</html>