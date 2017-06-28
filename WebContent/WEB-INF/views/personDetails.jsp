<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>

	<form:form method="post" modelAttribute="personDetails">
		Login: <form:input path="login" />
		<br> 
		Email: <form:input path="email" />
		<br> 
		Password: <form:input path="password" />
		<br> 
		First name: <form:input path="firstName" />
		<br>
		Last name: <form:input path="lastName" />
		<br> 
		Gender:<br> 
		Male:	<form:radiobutton path="gender" value="M" />
		Female:	<form:radiobutton path="gender" value="F" />
		<br>
		Country: <form:select	path="country"	items="${countryItems}"	/>
		<br> 
		Notes: <form:textarea	path="notes"	rows="3"	cols="20"/>
		<br>
		Mailing list: <form:checkbox	path="mailingList"/>
		<br>
		Programming skills: <form:select	path="programmingSkills"	items="${programmingSkills}"	/>
		<br>
		Hobbies: 
		<br>
		Php:	<form:checkbox	path="hobbies"	value="php"/>
		Java:	<form:checkbox	path="hobbies"	value="java"/>
		Ruby:	<form:checkbox	path="hobbies"	value="ruby"/>
		Python:	<form:checkbox	path="hobbies"	value="python"/>
		<br> 
		<br>
		<input type="submit" value="Submit">
	</form:form>

	

</body>
</html>