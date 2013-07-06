<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Styling -->
<link rel="stylesheet" href="/css/webStats.css" type="text/css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- Included scripts -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!--  -->

<script type="text/javascript">
	$(document).ready(function() {
		$("button").button();
	});
</script>


<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<table border="1" align="center" width="100%">
		<tr>
			<td class="header"><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr>
			<td class="navigationMenu"><tiles:insertAttribute name="menu" /></td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td class="footer"><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>
</body>
</html>
