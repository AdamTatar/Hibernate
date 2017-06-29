<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
	<h2>Dodaj nową książkę</h2>

	 <form:form method="post" modelAttribute="book">
	 <form:errors path="*" element="div"/>
		Title: <form:input path="title" />
		<br>
		
		Description: <form:textarea path="description" />
		<br> 
		<form:hidden path="proposition" value="true"/>
		
		<input type="submit" value="Submit">
	</form:form> 

	

</body>
</html>