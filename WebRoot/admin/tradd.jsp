<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@include file="common/global.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="js/jquery-last.js"></script>
		<style type="text/css">
			@IMPORT url("css/all.css");
			@IMPORT url("css/weebox/weebox.css");
			@IMPORT url("css/validator/validator.css");

</style>
		<title>增加子模板</title>
	</head>
	<body>
		<div align="center" style="padding: 200px 15px 4px 15px;">
		<a target="centerFrame" href="page/traddmenu.jsp?pid=${param.pid}">增加子菜单</a>
		
		<a target="centerFrame" href="page/traddtemplate.jsp?pid=${param.pid}">增加子模板</a>
		</div>
	</body>
</html>