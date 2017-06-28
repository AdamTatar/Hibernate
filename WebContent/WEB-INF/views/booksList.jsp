<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>

	<h2>Lista wszystkich książek</h2>
	
	<table border="solid black">
	<tr>
	<th>title</th>
	<th>authors</th>
	<th>description</th>
	<th>rating</th>
	<th>publisher</th>
	<th>edit</th>
	<th>delete</th>
	</tr>
	<c:forEach items="${books}" var="book">
	
	<tr>
	<td>${book.title }</td>
	<td>
	<c:forEach	items="${book.authors}"	var="author">
		${author.lastName}<br> <!-- to dziala dzieki konwerterowi -->
	</c:forEach>
	
	
	</td>
	<td>${book.description }</td>
	<td>${book.rating }</td>
	<td>${book.publisher.name }</td>
	<td><a href="edit/${book.id}">edit</a></td>
	<td><a href="delete/${book.id}">delete</a></td>
	</tr>
	
	
	</c:forEach>

	</table>

</body>
</html>