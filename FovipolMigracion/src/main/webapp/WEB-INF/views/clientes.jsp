<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.flash-message {
	padding: 8px;
	font-family: arial;
	font-size: 14px;
	margin-bottom: 10px;
	color: #514721;
	background-color: #fff6bf;
	border: 2px solid #ffd324;
}
</style>
</head>
<body>

	<%-- 	Aplicativo : ${myname} --%>
	<h2>Traslado de Aportes</h2>
	<hr>
	<form method="POST">
		<select name="tipo" required>
			<c:forEach items="${lstTibus}" var="x">
				<option value="${x.codicl}">${x.ciptcl}</option>
			</c:forEach>
		</select> Codigo Cliente <input name="ccCliente" value="" /> <input
			type="submit" value="Buscar">
	</form>
	<br>
	<c:if test="${lstCustomer!=null}">
		<c:choose>
			<c:when test="${fn:length(lstCustomer) == 0}">
				<div class="flash-message">
				No se han encontrado resultados
				</div>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>CODIGO</th>
						<th>NOMBRE</th>
					</tr>
					<c:forEach items="${lstCustomer}" var="customer">
						<tr>
							<td><a href="vaportes?ccCliente=${customer.codicl}">${customer.codicl}</a>
							</td>
							<td>${customer.nococl}</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>