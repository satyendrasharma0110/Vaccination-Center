<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD VACCINATION CENTER</title>
</head>
<body>

<form action="insertCenter">
<h2 align="center" ><b>ADD NEW VACCINATION CENTER</b></h2>
	<table class="container">

		<tbody>

			<tr>
				<td><label for="centerName">CENTER NAME: </label></td>
				<td><input type="text" name="centerName" placeholder="ENTER CLINIC NAME" ><br></td>
				
			</tr>
			<tr>
			<td><label for="city">SELECT CITY:</label></td>
		    <td><select name="city" class="box" >
		      <option value="">--Please choose an city--</option>
              <option value="Bengalore">Bangalore</option>
              <option value="Chennai">Chennai</option>
              <option value="Hyderbad">Hyderbad</option>
              <option value="Pune">Pune</option>
              <option value="Madurai">Madurai</option>
              <option value="Punjab">Punjab</option>
              <option value="Ladak">Ladak</option>
              <option value="Coimbatore">Coimbatore</option>
            </select></td>
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