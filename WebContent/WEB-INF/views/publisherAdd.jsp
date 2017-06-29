<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
	<h2>Dodaj nowego wydawcę</h2>

	<form:form method="post" modelAttribute="publisher">
	<form:errors path="*" element="div"/>
		Name: <form:input path="name" />
		<br>
		REGON: <form:input path="regon" />
		<br> 
		NIP: <form:input path="nip" />
		<br> 
		<input type="submit" value="Submit">
	</form:form>

	

</body>
</html>