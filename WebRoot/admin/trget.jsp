<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="common/global.jsp"%>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="js/jquery-last.js"></script>
		<style type="text/css">
			@IMPORT url("css/all.css");
			@IMPORT url("css/weebox/weebox.css");
			@IMPORT url("css/validator/validator.css");

		</style>
	<title>查看模板配置</title>
	</head>	
	<body>
		模板包含的字段：
		<table style="border: 1px dashed #000000">
		<tr><th>字段名称</th><th>字段类型</th><th>字段长度</th><th>能否为空</th></tr>
		<s:iterator value="mList" status="st">
			<tr>
			<td><s:property value="dNotes" />&nbsp;</td>
			<td><s:property value="dType" /></td>
			<td><s:property value="dLength" /></td>
			<td><s:property value="dAllowNull" /></td>
			</tr>
        </s:iterator>
        </table>
	</body>
</html>