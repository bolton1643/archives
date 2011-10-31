<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>
<%@include file="common/global.jsp" %>
	<head>
		<base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/" />
		
		<title>数据管理</title>
		<link type="text/css" href="css/smoothness/jquery-ui-1.7.custom.css" rel="stylesheet" />
		<style type="text/css">
			body{ font: 75.5% "Trebuchet MS", sans-serif; margin: 50px;}
		</style>		
		<script type="text/javascript" src="js/jquery-last.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.7.custom.min.js"></script>		
		<script type="text/javascript">
		$(document).ready(function(){
			$(function() {
				$('#datepicker').datepicker({
					changeMonth: true,
					changeYear: true,
					dateFormat:'yy-mm-dd'
				});
			});
		});
		</script>
	</head>
<body>	
<s:form action="dDoAdd.action" theme="simple" name="form1" id="form1" method="post">
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<input type="hidden" name="tid" id="tid" value="${tid}"/>
	<s:iterator value="cList" status="st">
		
			<s:if test="dName=='ID'">
				
			</s:if>
			<s:elseif test="dType=='日期'">
				<tr align="left">
					<td><s:property value="dNotes" /></td>
					<td><INPUT type="text" name=<s:property value="dName" /> id="datepicker" width="30">
					</td>
				</tr>				
			</s:elseif>
			<s:elseif test="dName.lastIndexOf('_PHOTO')>=0">
				<tr align="left">
					<td><s:property value="dNotes" /></td>
					<td><INPUT type="text" name="<s:property value="dName" />" id="<s:property value="dName" />">						
					<input type="button" name="选择图片" value="选择图片" onClick="window.open('page/d_upload.jsp?p=notice&cid=<s:property value="dName" />','fileUpload','width=480,height=320')">
			   </tr>	
			</s:elseif>
			<s:elseif test="dType=='文件'">
			    <tr align="left">
					<td><s:property value="dNotes" /></td>
					<td><INPUT type="text" name="<s:property value="dName" />" id="<s:property value="dName" />">						
					<input type="button" name="选择文件" value="选择文件" onClick="window.open('page/d_upload.jsp?p=notice&cid=<s:property value="dName" />','fileUpload','width=480,height=320')">			
			   </tr>	
			</s:elseif>
		
			<s:else>
			    <tr align="left">
				<td><s:property value="dNotes" /></td>
				<td><INPUT  type="text" name="<s:property value="dName" />" id="<s:property value="dName" />">	
				</tr>					
			</s:else>
    </s:iterator>

<tr><td></td><td><input type="submit" value="提交" /></td></tr>
</table>

</s:form>
</body>
</html>