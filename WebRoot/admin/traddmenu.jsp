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
		<form action="trAddMenu.action" id="trAddForm" name="trAddForm" method="post">
			<input type="hidden" name="parentId" id="pid" value="${param.pid}"/> 
			<input type="hidden" name="expanded" id="expanded" value="0"/> 
			<input type="hidden" name="isfolder" id="isfolder" value="1"/> 
			<div align="center">
			子菜单名称：<input type="text" name="text" size="40" />&nbsp;
			<input type="submit" value="提交" />
			</div>	
		</form>
		
	</body>
</html>