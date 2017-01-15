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

    <title>Add client</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <script language="javascript">
        function move()
        {
            document.getElementById("cancelButton").disabled = true;
            location.href = "${contextPath}/admin/listClients";
        }

    </script>

</head>

<body>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="container">

    <a href="<%=request.getContextPath()%>?languageVar=en"><spring:message code="general.UA"/></a>
    <a href="<%=request.getContextPath()%>?languageVar=ru"><spring:message code="general.RU"/></a>

    <form:form method="POST" modelAttribute="client" class="form-signin">
        <h2 class="form-signin-heading">
            <spring:message code="addClient.head"/>
        </h2>
        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addClient.placeholder.firstName" var="firstName"/>
                <form:input type="text" path="firstName" class="form-control" placeholder='${firstName}' autofocus="true"/>

                <form:errors path="firstName"/>
            </div>
        </spring:bind>
        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addClient.placeholder.lastName" var="lastName"/>
                <form:input type="text" path="lastName" class="form-control" placeholder='${lastName}' autofocus="true"/>

                <form:errors path="lastName"/>
            </div>
        </spring:bind>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addSkill.placeholder.nameskill" var="NameSkill"/>
                <form:input type="text" path="name" class="form-control" placeholder='${NameSkill}' autofocus="true"/>

                <form:errors path="name"/>
            </div>
        </spring:bind>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addSkill.placeholder.nameskill" var="NameSkill"/>
                <form:input type="text" path="name" class="form-control" placeholder='${NameSkill}' autofocus="true"/>

                <form:errors path="name"/>
            </div>
        </spring:bind>

        <table>
            <tr>
                <td>
                    <jsp:include page="/WEB-INF/pages/fragments/inputPassword.jsp"/>
                </td>
                <td>
                    <button type="submit">
                        <spring:message code="addSkill.buttons.add"/>
                    </button>
                </td>
                <td>
                    <button id="cancelButton" onclick="move()">
                        <spring:message code="editSkill.buttons.cancel"/>
                    </button>
                </td>
            </tr>
        </table>
    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
