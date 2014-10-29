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
	src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
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
		var msgtitle="Mensaje del Sistema";
		$('#urlfile').filebox({
			buttonAlign : 'right',
			prompt : 'Solo archivos con extencion (*.csv)',
			buttonText : 'Buscar Archivo'
		});
		$("#cargar").click(function(event) {
			$("#accion").val("cargar");
			$("#searchForm").submit();
		});

		$("#btnBuscarTit").click(function(event) {
			var formData = {pcip:$('#txtCipT').textbox('getText'),pcodofin:$('#txtCodofinT').textbox('getText')};
			$.ajax({
				url : '/fovipol/getTitu',
				type : "POST",
				data : formData,
				success : function(data) {
					var rpta = data.split(";");
					var cod=rpta[0];
					var nom=rpta[1];
					$('#txtClienteT').textbox('setValue', nom);
					$('#txtTitular').textbox('setValue', nom);
					$('#codTit').val(cod);
				},
				error : function(xhr, status, errorThrown) {
					$.messager.alert(msgtitle,errorThrown,'error');
				},
				statusCode : {
					404 : function() {
						alert("page not found");
					}
				},
				complete : function(xhr, status) {
					//alert("The request is complete!");
				}
			});
		});
		
		$("#btnBuscarBen").click(function(event) {
			var formData = {pcip:$('#txtCipB').textbox('getText'),pcodofin:$('#txtCodofinB').textbox('getText')};
			$.ajax({
				url : '/fovipol/getTitu',
				type : "POST",
				data : formData,
				success : function(data) {
					var rpta = data.split(";");
					var cod=rpta[0];
					var nom=rpta[1];
					$('#txtClienteB').textbox('setValue', nom);
					$('#txtBeneficiario').textbox('setValue', nom);
					$('#codBen').val(cod);
				},
				error : function(xhr, status, errorThrown) {
					$.messager.alert(msgtitle,errorThrown,'error');
				},
				statusCode : {
					404 : function() {
						alert("page not found");
					}
				},
				complete : function(xhr, status) {
					//alert("The request is complete!");
				}
			});
		});
		$("#btnTraspasar").click(function(event) {
			var codben=$('#codBen').val();
			var codtit=$('#codTit').val();
			
			if(codben==''||codtit==''){
				$.messager.alert(msgtitle,'Debe buscar el Titular y al Beneficiario','warning');
			}else{
				$.messager.confirm(msgtitle, 'Esta seguro de continuar con el traslado', function(r){
					if (r){
						$.post("/fovipol/getVereficaBene",{pcodtit:codtit},
								function(data,status){
							//alert("Data: " + data + "\nStatus: " + status);
							if(data>=1){
								var formData={pcodben:codben,pcodtit:codtit};
								$.ajax({
									url : '/fovipol/getTraspasar',
									type : "POST",
									data : formData,
									success : function(data) {
										alert(data)
										$('#frmTraspaso').form('clear');
									},
									error : function(xhr, status, errorThrown) {
										$.messager.alert(msgtitle,errorThrown,'error');
									},
									statusCode : {
										404 : function() {
											alert("page not found");
										}
									},
									complete : function(xhr, status) {
										//alert("The request is complete!");
									}
								});
							}else{
								$.messager.alert(msgtitle,'Debe agregar beneficiario en el Sistema SIGA','error');
							}
						});
					}
				});
			}
		});
		$("#procesar").click(function(event) {
			$("#accion").val("procesar");
			$("#searchForm").submit();
		});

		$("#exportar").click(function(event) {
			$("#accion").val("exportar");
			$("#searchForm").submit();
		});
	});

	function CargarAportes() {
		$('#tblAportes').datagrid({
			title : 'Aportes Cargados',
			rownumbers : true,
			remoteSort : false,
			nowrap : false,
			fitColumns : true,
			singleSelect : true,
			collapsible : true,
			method : 'get',
			url : '/fovipol/aportescargados',
			columns : [ [ {
				field : 'ctcip',
				title : 'CIP',
				width : 80,
				sortable : true
			}, {
				field : 'ctcliente',
				title : 'CODOFIN',
				width : 100,
				align : 'left',
				sortable : true
			} ] ],
			onDblClickRow : function() {
				//getSelectedRowTable();
			}
		});
	}
</script>
</head>
<body>

	<div id="container">
		<div id="header">
			<h2>Carga de Aportes</h2>
		</div>
		<div id="containerbody">
			<div class="easyui-tabs" style="width: 100%;">
				<div title="Carga Masiva" style="padding: 5px">
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
							<input id="urlfile" name="urlfile" type="text"
								style="width: 500px"> <input type="hidden" name="accion"
								id="accion" /> <label>Procedencia Aporte</label> <select
								class="easyui-combobox" name="tipo"
								data-options="panelHeight: 'auto'" style="width: 100px;">
								<option value="E">Estado</option>
								<option value="T">Titular</option>
							</select> <br> <input type="button" class="easyui-linkbutton"
								id="cargar" value="Cargar"> <input type="button"
								class="easyui-linkbutton" id="procesar" value="Procesar">
							<input type="button" class="easyui-linkbutton" id="exportar"
								data-options="iconCls:'icon-print'" value="Exportar">
							<table border="1" id="tbAportes">
								<tr>
									<c:forEach items="${xcolumnas}" var="columna">
										<th>${columna}</th>
									</c:forEach>
								</tr>
								<c:forEach items="${xfilas}" var="fila">
									<tr>
										<td>${fila.ctcip}</td>
										<td>${fila.ctcodofin}</td>
										<td>${fila.ctcliente}</td>
										<td>${fila.nimonto}</td>
										<td>${fila.nifecha}</td>
									</tr>
								</c:forEach>
							</table>
						</form>
						<hr>
					</div>
					<div>
						<table border="1">
							<tr>
								<c:forEach items="${xcolumnasnot}" var="columna">
									<th>${columna}</th>
								</c:forEach>
							</tr>
							<c:forEach items="${xlstaportenot}" var="fila">
								<tr>
									<td>${fila.ctcip}</td>
									<td>${fila.ctcodofin}</td>
									<td>${fila.ctcliente}</td>
									<td>${fila.nimonto}</td>
									<td>${fila.nifecha}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div title="Traslado de Aportes" style="padding: 5px">
				<form id="frmTraspaso">
					<table border="1">
						<tr>
							<th colspan="6">TITULAR</th>
						</tr>
						<tr>
							<td>CIP</td>
							<td><input class="easyui-textbox" id="txtCipT"
								style="width: 150px; height: 25px"></td>
							<td>CODOFIN</td>
							<td><input class="easyui-textbox" id="txtCodofinT"
								style="width: 150px; height: 25px"></td>
							<td><input type="button" class="easyui-linkbutton"
								id="btnBuscarTit" value="Buscar"></td>
							<td><input class="easyui-textbox" id="txtClienteT"
								style="width: 450px; height: 25px" readonly="readonly"></td>
						</tr>
						<tr>
							<th colspan="6">&nbsp;</th>
						</tr>
						<tr>
							<th colspan="6">BENEFICIARIO</th>
						</tr>
						<tr>
							<td>CIP</td>
							<td><input class="easyui-textbox" id="txtCipB"
								style="width: 150px; height: 25px"></td>
							<td>CODOFIN</td>
							<td><input class="easyui-textbox" id="txtCodofinB"
								style="width: 150px; height: 25px"></td>
							<td><input type="button" class="easyui-linkbutton"
								id="btnBuscarBen" value="Buscar"></td>
							<td><input class="easyui-textbox" id="txtClienteB"
								style="width: 450px; height: 25px" readonly="readonly"></td>
						</tr>
						<tr><th colspan="6">&nbsp;</th></tr>
						<tr><th colspan="6">&nbsp;</th></tr>
						<tr >
						<td>Pasar aportes de:</td>
						<td colspan="5"><input class="easyui-textbox" id="txtBeneficiario"
								style="width: 450px; height: 25px" readonly="readonly">
								<input type="hidden" id="codBen" value="">
								</td>
						</tr>
						<tr >
						<td>Al titular:</td>
						<td colspan="5"><input class="easyui-textbox" id="txtTitular"
								style="width: 450px; height: 25px" readonly="readonly">
								<input type="hidden" id="codTit" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="easyui-linkbutton"id="btnTraspasar" value="Traspasar aportes">
						</td>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>