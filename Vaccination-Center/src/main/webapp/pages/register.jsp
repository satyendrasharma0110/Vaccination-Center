<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registeration</title>

</head>
<body>

<form action="insert" method="post">
<h2 align="center" ><b>REGISTRATION</b></h2>
	<table class="container">

		<tbody>
			<tr>				
				<td><label for="name">NAME:</label></td>
				<td><input name="name" placeholder="Your Name" type="text"/><br></td>
			</tr>
			<tr>
				<td><label for="email">EMAIL:</label></td>
				<td><input type="email" name="email" placeholder="email" ><br></td>
				
			</tr>
			<tr>
				
				<td><label for="password">PASSWORD:</label></td>
				<td><input type="password" name= "password" placeholder="Enter Password"><br></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th></th>
				<th><input type="submit" value="Submit"/></th>
				
			</tr>
		</tfoot>
	</table>
        
</form>
<div class="error-message" align="center">
    <c:if test="${not empty message}">
        <span style="color: red;">${message}</span>
    </c:if>
</div>
</body>
</html>