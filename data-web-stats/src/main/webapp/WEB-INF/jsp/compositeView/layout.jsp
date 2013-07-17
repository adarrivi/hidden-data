<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<tiles:insertAttribute name="title" />

<tilesx:useAttribute id="mainStylesheets" name="mainStylesheets"
	classname="java.util.List" />
<c:forEach items="${mainStylesheets}" var="src">
	<link rel="stylesheet" type="text/css" href="${src}.css" />
</c:forEach>
<tilesx:useAttribute id="specificStylesheets" name="specificStylesheets"
	classname="java.util.List" />
<c:forEach items="${specificStylesheets}" var="src">
	<link rel="stylesheet" type="text/css" href="${src}.css" />
</c:forEach>
</head>
<body>
	<table align="center" width="100%">
		<tr>
			<td class="header"><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr>
			<td class="menu"><tiles:insertAttribute name="menu" /></td>
		</tr>
		<tr valign="top" height="400px">
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td class="footer"><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>
	<tilesx:useAttribute id="mainScripts" name="mainScripts"
		classname="java.util.List" />
	<c:forEach items="${mainScripts}" var="src">
		<script type="text/javascript" src="${src}.js"></script>
	</c:forEach>
	<tilesx:useAttribute id="specificScripts" name="specificScripts"
		classname="java.util.List" />
	<c:forEach items="${specificScripts}" var="src">
		<script type="text/javascript" src="${src}.js"></script>
	</c:forEach>
</body>
</html>