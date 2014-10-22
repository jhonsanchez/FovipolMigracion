<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css"> -->
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script> -->
<!-- <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script> -->

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style type="text/css">
        li.ui-state-default{ 
            font-size : 10px;
        }
        div.ui-tabs-panel{
            font-size : 15px;
            font-family : georgia; 
            font-style : italic;
        }
</style>
</head>
<body>

	<!-- <div class="easyui-tabs" style="width:700px;height:250px"> -->
	<!-- <div title="Estado de Cuenta" style="padding:10px"> -->
	<!-- </div> -->
	<!-- <div title="Cambio" style="padding:10px"> -->
	<!-- </div> -->
	<!-- </div> -->
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Estado de Cuenta</a></li>
			<li><a href="#tabs-2">Detalle</a></li>
		</ul>
		<div id="tabs-1">
			<p>Mi Estado de Cuenta</p>
		</div>
		<div id="tabs-2">
			<p>Detalle</p>
		</div>
	</div>
</body>
</html>