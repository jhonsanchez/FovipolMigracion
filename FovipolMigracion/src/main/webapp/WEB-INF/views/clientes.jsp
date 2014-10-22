<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	Aplicativo : ${myname}

	<form method="POST">
		<select name="tipo" required >
			<c:forEach items="${lstTibus}" var="x">
				<option value="${x.codicl}" >${x.ciptcl}</option>
			</c:forEach>
		</select> 
		Codigo Cliente 
		<input name="ccCliente" value="" /> 
		<input type="submit" value="Buscar">
	</form>
	
	<br>
	<c:if test="${not empty lstCustomer}">
		<table>
			<tr>
				<th>CODIGO</th>
				<th>NOMBRE</th>
			</tr>
			<c:forEach items="${lstCustomer}" var="customer">
				<tr>
					<td>
					<a href="vestadocuenta?idcliente=${customer.codicl}">${customer.codicl}</a>
					</td>
					<td>${customer.nococl}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>