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
		<script type="text/javascript">
			function rm(theRow){
				var tb = document.getElementById("insertPosition");
				//alert(tb.innerHTML)
				//alert(theRow.innerHTML)
				tb.removeChild(theRow);
			}
			$(document).ready(function(){
				$("#btn").click(function(){
					//alert("add button clicked");
					var insertPos = $("#insertPosition");
					
					var trNode = $("<tr align='left'><td><input name='dName' id='dName' type='text'/></td><td><select id='dType' name='dType'><option>整数</option><option>字符串</option><option>日期</option><option>小数</option><option>文件</option></select></td><td><input name='dLength' value='11' id='dLength' type='text'/></td><td><select id='dAllowNull' name='dAllowNull'><option>允许为空</option><option>不允许为空</option></select></td><td><input type='button' value='删除' onclick='rm(this.parentNode.parentNode);'/></td></tr>");
					
					insertPos.append(trNode);
					//alert(document.getElementById("insertPosition").innerHTML)
				});
			});
		</script>
	</head>
	<body>
		<form action="trAdd.action" id="trAddForm" name="trAddForm" method="post">
			<input type="hidden" name="parentId" id="pid" value="${param.pid}"/> 
			<input type="hidden" name="expanded" id="expanded" value="0"/>
			<input type="hidden" name="openurl" id="openurl" value="dList.action"/> 
			<input type="hidden" name="isfolder" id="isfolder" value="0"/> 

			<table style="border: solid 1px blue;border-top: -1px;  width: 100%;" cellpadding="0" cellspacing="0">
				<tbody id="insertPosition">
				<tr align="left"><th align="left">字段名称</th><th align="left">字段类型</th><th align="left">字段长度</th><th>是否允许为空</th><th align="left"><input id="btn" type="button" value="Add"></th></tr>
				<tr align="left"><td>编号</td><td>整数</td><td>11</td><td>不允许为空</td><td>&nbsp;</td></tr>
				</tbody>
			</table>
			<div align="center">
			子模板名称：<input type="text" name="text" size="40" />&nbsp;
			<input type="submit" value="提交" />
			</div>	
		</form>
	</body>
</html>