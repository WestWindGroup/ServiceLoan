<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Clients</title>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

    <script language="javascript">
        function move()
        {
            document.getElementById("cancelButton").disabled = true;
            location.href = "${contextPath}/home";
        }

    </script>


</head>
<body>

<a href="<%=request.getContextPath()%>?languageVar=en"><spring:message code="general.UA"/></a>
<a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>

<div>
    <h1>Clients</h1>
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

<c:if test="${!empty listClients}">
    <table class="tg">
        <c:forEach items="${listClients}" var="client">
            <tr>
                <td width="80">${client.id}</td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.firstName}" class="form-control" autofocus="true"/>
                    </h4>
                </td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.lastName}" class="form-control" autofocus="true"/>
                    </h4>
                </td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.registrationDate}" class="form-control" autofocus="true"/>
                    </h4>
                </td>
                <td width="180">
                    <h4 class="form-signin-heading">
                        <input type="text" disabled="true" value="${client.birthDate}" class="form-control" autofocus="true"/>
                    </h4>
                </td>
                <td width="80">
                    <h5 class="text-center">
                        <a href="/user/showClient/${client.id}">
                            <spring:message code="listClient.button.showClient"/>
                        </a>
                    </h5>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<h3 class="text-center">
    <a href="${contextPath}/user/addClient">
        <spring:message code="listClient.button.addClient"/>
    </a>
</h3>
<button id="cancelButton" onclick="move()">
    <spring:message code="buttons.back"/>
</button>
</body>
</html>