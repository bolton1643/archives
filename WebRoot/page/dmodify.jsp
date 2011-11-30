<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>
<%@include file="common/global.jsp" %>
	<head>
		<title>数据管理</title>
		<link type="text/css" href="css/smoothness/jquery-ui-1.7.custom.css" rel="stylesheet" />	
		<link type="text/css" href="css/bruce.css" rel="stylesheet" />
		<link type="text/css" href="css/ligerui-common.css" rel="stylesheet" />
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
<div class="bruce_txt">
  <ul>
<s:form action="dDoEdit" theme="simple" name="form1" id="form1" method="post">
    <input type="hidden" name="tid" id="tid" value="${tid}"/>
    <input type="hidden" name="id" id="id" value="${id}"/>
	<s:iterator value="cList" status="st">
			<s:if test="dName=='ID'">
				<input type="hidden" name="<s:property value="dName" />" value="<s:property value="dValuee" />" id="<s:property value="dName" />" />
			</s:if>
			<s:elseif test="dName=='ID2'">
                          
    </s:elseif>
			<s:elseif test="dType=='日期'">
				<li>
				  <span class="bruce_txt_1"><s:property value="dNotes" /></span>
					<span class="txt_2"><input class="txt_input" type="text" name="<s:property value="dName" />" value="<s:property value="dValue" />" id="datepicker"	style="width:30;" /></span>
				</li>				
			</s:elseif>
			<s:elseif test="dName.lastIndexOf('_PHOTO')>=0">
			  <li>
         <span class="bruce_txt_1"><s:property value="dNotes" /></span>
         <span class="txt_2">
           <input class="txt_input" type="text" name="<s:property value="dName" />" value="<s:property value="dValue" />" id="<s:property value="dName" />" />
           <input class="txt_input" type="button" name="选择图片" value="选择图片"  onClick="window.open('page/d_upload.jsp?p=notice&cid=<s:property value="dName" />','fileUpload','width=480,height=320')" />        
         </span>
       </li>
			</s:elseif>
			<s:elseif test="dType=='文件'">
       <li>
         <span class="bruce_txt_1"><s:property value="dNotes" /></span>
           <span class="txt_2">
             <input class="txt_input" type="text" name="<s:property value="dName" />" value="<s:property value="dValue" />" id="<s:property value="dName" />" />
            <input  class="txt_input" type="button" name="选择文件" value="选择文件"  onClick="window.open('page/d_upload.jsp?p=notice&cid=<s:property value="dName" />','fileUpload','width=480,height=320')" />
           </span>
       </li>
			</s:elseif>
			<s:else>
       <li>
          <span class="bruce_txt_1"><s:property value="dNotes" /></span>
          <span class="txt_2"><input class="txt_input" type="text" name="<s:property value="dName" />" value="<s:property value="dValue" />" id="<s:property value="dName" />" /></span>
        </li>			  					
			</s:else>
    </s:iterator>
    <li>
          <span class="bruce_txt_1"><s:property value="dNotes" /></span>
					   <div class="btn" style="margin:0 auto;float:left">
					          <div class="btn_l"></div>
					          <button class="btn_m" name="submit" id="submit">提交</button>  
					          <div class="btn_r"></div>
					       </div>
     </li>
</s:form>
  </ul>
</div>
</body>
</html>