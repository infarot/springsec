<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
<h1 class="error">Hello!</h1>
Logged as: <security:authentication property="principal.username"/>
<br>
<br>
<p>
    Role: <security:authentication property="principal.authorities"/>
</p>
<p>This is the homepage!</p>
<p>
    <a href="${pageContext.request.contextPath}/leaders">Leaders page</a>
</p>
<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input class="error" type="submit" value="logout">
</form:form>
</body>
</html>
