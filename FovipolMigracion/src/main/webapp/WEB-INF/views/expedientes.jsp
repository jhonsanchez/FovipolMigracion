<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#procesar").click(function(event) {
		var msg="Se procedera a anular el Expediente ingresado<br>Esta seguro de continuar?"; 
		$.messager.confirm("Mensaje del Sistema",msg, function(r){
			if (r){
				$("#searchForm").submit();
			}
		});
	});
});
</script>
</head>
<body>
	<!-- //COMENTARIO; -->
	<div class="easyui-panel" title="Anulacion de Expedientes"
		style="width: 100%; height: 170px; padding: 5px;">
		<form method="POST" id="searchForm">
			<tabla border="1">
			<tr>
				<td>Cod. Expediente</td>
				<td><input class="easyui-textbox" type="text" id="ccexpediente"
					name="ccexpediente" style="width: 250px; height: 25px"></td>
				<td><input type="button" class="easyui-linkbutton"
					id="procesar" value="Procesar"></td>
			</tr>
			<tr>
				<th colspan="3">${ccrpta}</th>
			<tr>
			</tabla>
		</form>
	</div>
</body>
</html>