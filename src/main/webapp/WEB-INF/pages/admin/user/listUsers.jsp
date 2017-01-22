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

    <title>User</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

</head>
<body class="backgroundAdmin">
<h1 class="heading">Users</h1>

<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120"></th>
            <th width="120">LastName</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.lastName}</td>
                <td width="80"><a href="/admin/editUser/${user.id}">Edit</a></td>
                <td width="80"><a href="/admin/deleteUser/${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<button class="funcButton" onclick="location.href = '${contextPath}/admin/addUser'">
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