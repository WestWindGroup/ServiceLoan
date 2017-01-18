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

    <title>Credit Type</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

</head>
<body>
<h1 class="heading">Credit types</h1>

<c:if test="${!empty listTypes}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Type</th>
        </tr>
        <c:forEach items="${listTypes}" var="type">
            <tr>
                <td>${type.id}</td>
                <td>${type.type}</td>
                <td width="80"><a href="/admin/editType/${type.id}">Edit</a></td>
                <td width="80"><a href="/admin/deleteType/${type.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<div class="block-button">
    <div class="block2-listClient">
        <button onclick="location.href = '${contextPath}/admin'">
            <h4 class="text-center">
                <spring:message code="button.backEn"/>
            </h4>
        </button>
    </div>
    <div class="block1-listClient">
        <button onclick="location.href = '${contextPath}/admin/addType'">
            <h4 class="text-center">
                <spring:message code="button.create"/>
            </h4>
        </button>
    </div>
</div>
</body>
</html>