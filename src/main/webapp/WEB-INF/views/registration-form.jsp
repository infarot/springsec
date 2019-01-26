<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 2019-01-26
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<!-- Registration Form -->
<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
           modelAttribute="crmUser"
           class="form-horizontal">
    <!-- Check for registration error -->
    <c:if test="${registrationError != null}">
        <div class="error">
            There are errors!: ${registrationError}
        </div>
    </c:if>

    <form:input path="userName" placeholder="username" class="form-control"/>

    <form:password path="password" placeholder="password" class="form-control"/>

    <form:password path="matchingPassword" placeholder="repeat password" class="form-control"/>

    <form:input path="firstName" placeholder="first name" class="form-control"/>

    <form:input path="lastName" placeholder="last name" class="form-control"/>

    <form:input path="email" placeholder="email" class="form-control"/>
    <input type="submit" value="Register"/>
</form:form>
</body>
</html>
