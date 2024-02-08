<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

</head>
<body>
<header><h1>VACCINATION CENTER LOGIN</h1></header>
<hr>
<form action="dashboard" method="post">

<h2><b>Login</b></h2>
	<table>
	   
		<tbody>
			<tr>
				<td><label for="email">Email:</label></td>
				<td><input type="email" name="email" placeholder="email" ><br></td>
			</tr>
			<tr>
			    <td><label for="password">Enter Password:</label></td>
				<td><input type="password" name= "password" placeholder="Enter Password"><br></td>
			    
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td></td><td><input type="submit" value="LOGIN" /></td>
			</tr>
		</tfoot>
	</table>
	
</form>

<div class="error-message">
    <c:if test="${not empty message}">
        <span style="color: red;">${message}</span>
    </c:if>
</div>

<div>
<form action="register">
	<button>REGISTER</button>
</form>
</div>
</body>
</html>