<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Add rate interest</title>

    <link href="${contextPath}/resources/css/serviceloan.css" rel="stylesheet" >

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

    <form:form method="POST" modelAttribute="rate" class="form-signin">
        <h2 class="form-signin-heading">
            <spring:message code="addRate.head"/>
        </h2>
        <spring:bind path="rate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="addRate.placeholder.rate" var="Namerate"/>
                <form:input type="text" path="rate" class="form-control"
                            placeholder='${Namerate}' autofocus="true"/>

                <form:errors path="rate"/>
            </div>
        </spring:bind>

        <table>
            <tr>
                <td>
                    <button type="submit">
                        <spring:message code="button.accept"/>
                    </button>
                </td>
                <td>
                    <button id="cancelButton" onclick="location.href = '${contextPath}/admin/listRates'">
                        <spring:message code="button.backEn"/>
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
