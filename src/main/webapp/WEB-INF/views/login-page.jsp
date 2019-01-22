<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 2019-01-22
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css"/>
</head>
<body>
<h3>My login page</h3>
<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
    <c:if test="${param.error != null}">
        <i class="error">Bad username or password!</i>
    </c:if>
    <p>
        <label>
            Username:
            <input type="text" name="username"/>
        </label>
    </p>
    <p>
        <label>
            Password:
            <input type="password" name="password"/>
        </label>
    </p>
    <input type="submit" value="Login"/>
</form:form>
</body>
</html>
