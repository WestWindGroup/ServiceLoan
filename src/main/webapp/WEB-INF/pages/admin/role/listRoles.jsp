<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Roles</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

</head>
<body>
<h1 class="heading">Roles</h1>

<c:if test="${!empty listRoles}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
        </tr>
        <c:forEach items="${listRoles}" var="role">
            <tr>
                <td>${role.id}</td>
                <td>${role.name}</td>
                <td width="80"><a href="/admin/editRole/${role.id}">Edit</a></td>
                <td width="80"><a href="/admin/deleteRole/${role.id}">Delete</a></td>
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
        <button onclick="location.href = '${contextPath}/admin/addRole'">
            <h4 class="text-center">
                <spring:message code="button.create"/>
            </h4>
        </button>
    </div>
</div>
</body>
</html>