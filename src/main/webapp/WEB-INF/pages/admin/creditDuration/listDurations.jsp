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

    <title>Duration</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

</head>
<body>
<h1 class="heading">Duration</h1>

<c:if test="${!empty listDurations}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Duration</th>
        </tr>
        <c:forEach items="${listDurations}" var="duration">
            <tr>
                <td>${duration.id}</td>
                <td>${duration.duration}</td>
                <td width="50"><a href="/admin/editDuration/${duration.id}">Edit</a></td>
                <td width="50"><a href="/admin/deleteDuration/${duration.id}">Delete</a></td>
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
        <button onclick="location.href = '${contextPath}/admin/addDuration'">
            <h4 class="text-center">
                <spring:message code="button.create"/>
            </h4>
        </button>
    </div>
</div>
</body>
</html>