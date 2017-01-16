<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group ${errorConfirmPassword != null ? 'has-error' : ''}">
    <spring:message code="login.placeholder.password" var="oldPassword"/>
    <h4>
        <input name="oldPassword" type="password" value="" class="form-control" autofocus="true"
               placeholder='${oldPassword}'/>
    </h4>
    <span>${errorConfirmPassword}</span>
</div>
