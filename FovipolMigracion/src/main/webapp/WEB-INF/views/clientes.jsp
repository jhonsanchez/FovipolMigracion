<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FOVIPOL</title>
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/color.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.min.js"></script>
<script type="text/javascript"
	src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>

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
<script type="text/javascript">
$(function() {
	//alert("as");
	$("#cargar").click(function( event ) {
		$("#accion").val("cargar");
		//$(this).submit();
		$("#searchForm").submit();
	});
	$("#procesar").click(function( event ) {
		$("#accion").val("procesar");
		//$(this).submit();
		$("#searchForm").submit();
	});
});
</script>
</head>
<body>

	<div id="container">
		<div id="header">
			<h2>Carga Masiva de Aportes</h2>
		</div>
		<div id="containerbody">
			<div class="easyui-panel" title="Formato de Carga"
				style="width: 100%; height: 170px; padding: 5px;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'east'"
						style="width: 200px; padding: 5px">
						Datos básicos requeridos para la carga
						<ul>
							<li type="disc">CIP</li>
							<li type="disc">CODOFIN</li>
							<li type="disc">AÑO y MES</li>
							<li type="disc">MONTO</li>
						</ul>
					</div>
					<div data-options="region:'center'" style="padding: 5px">
						<img src="modelocarga.jpg" height="50" /> <img
							src="<c:url value="/images/modelocarga.jpg" />" height="50" />
					</div>
				</div>
			</div>
			<div>
				<form method="POST" enctype="multipart/form-data" id="searchForm">
					<input type="file" name="urlfile"> <input type="button" id="cargar"
						value="Cargar">
					<table border="1">
						<tr>
							<c:forEach items="${xcolumnas}" var="columna">
								<td>${columna}</td>
							</c:forEach>
						</tr>
							<c:forEach items="${xfilas}" var="fila">
								<tr>
									<td>${fila.ctcip}</td>
									<td>${fila.ctcodofin}</td>
									<td>${fila.ctcliente}</td>
									<td>${fila.nimonto}</td>
									<td>${fila.nianhio}</td>
								</tr>
							</c:forEach>
					</table>
					<input type="button" id="procesar" value="Procesar">
					<input type="hidden" name="accion"  id="accion"/>
				</form>
			</div>
			<div>
				<input type="text">
			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>