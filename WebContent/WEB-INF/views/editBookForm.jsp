<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
	<h2>Edytuj książkę</h2>

	 <form:form method="post" modelAttribute="book">
		Title: <form:input path="title" />
		<br>
		Description: <form:textarea path="description" />
		<br> 
		Rating: <form:input path="rating" />
		Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id" />
		<%-- Authors: <form:select multiple="true" path="authors.id" items="${authors}" itemLabel="lastName" itemValue="id" /> --%>
		
		<input type="submit" value="Submit">
	</form:form> 

	

</body>
</html>