<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
	heloł

<form:form method="post" modelAttribute="person">
		Login: <form:input path="login"/><br> 
		Email: <form:input path="email"/><br> 
		Password: <form:input path="password"/><br> 
		<input type="submit"
			value="Submit">
	</form:form>


	<%-- <form method="post">
		Login: <input type="text" name="login"><br> 
		Email: <input type="text" name="email"><br> 
		Password: <input type="text" name="password"><br> 
		<input type="submit"
			value="Submit">
	</form>

 --%>


	<c:forEach items="${languages}" var="lang">
		${lang}<br>

	</c:forEach>




</body>
</html>