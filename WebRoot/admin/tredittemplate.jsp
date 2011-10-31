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
		<title>编辑子模板</title>
		<script type="text/javascript">
			function testFunction(id){
				var postUrl = "trDel.action";
				var d = "id="+id;
				if ((window.confirm('你确定要删除吗？')==true))
				{
					if ((window.confirm('删除后数据无法恢复，请再次确认？')==true))
					{
						$.ajax({
                        type: "post",
                        url:postUrl,
                        dataType:"html",
                        data:d,
                        error:function(){alert('error');},
                        success:function(data,textStatus)
                        		{
                        			if(data == 'success')
                        			{
                        				//window.navigate("http://<%=request.getHeader("host")%><%=request.getContextPath()%>/"); 
                        			}
                        		}
                      });
					}
				}
				else
				{
					return; //返回 下面的代码不执行
				}
			}
					
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
					
					var trNode = $("<tr align='left'><td><input name='dNotes' id='dNotes' type='text'/></td><td><select id='dType' name='dType'><option>整数</option><option>字符串</option><option>日期</option><option>小数</option><option>文件</option></select></td><td><input name='dLength' id='dLength' value='11' type='text'/></td><td><select id='dAllowNull' name='dAllowNull'><option>允许为空</option><option>不允许为空</option></select></td><td><input type='button' value='删除' onclick='rm(this.parentNode.parentNode);'/></td></tr>");
					
					insertPos.append(trNode);
					//alert(document.getElementById("insertPosition").innerHTML)
				});
			});
		</script>
	</head>
	<body>
		<a href="trGet.action?id=<s:property value="id" />" target="blank">查看模板</a>　<a href="javascript:testFunction(<s:property value="id" />)">删除模板</a>
		<form action="trDoEditTemplate.action" id="trEditForm" name="trEditForm" method="post">
			<s:hidden name="id"></s:hidden>
			<s:hidden name="parentId"></s:hidden>
			<s:hidden name="expanded"></s:hidden>
			<input type="hidden" name="isfolder" id="isfolder" value="0"/> 
			<input type="hidden" name="openurl" id="openurl" value="dList.action"/> 

			<table style="border: solid 1px blue;border-top: -1px;  width: 100%;" cellpadding="0" cellspacing="0">
				<tbody id="insertPosition">
				<tr align="left"><th align="left">字段名称</th><th align="left">字段类型</th><th align="left">字段长度</th><th>是否允许为空</th><th align="left"><input id="btn" type="button" value="Add"></th></tr>
				<s:iterator id="mList" value="mList" status="st">
				    <s:if test="dName.lastIndexOf('_PHOTO')>=0 ">
				      
				    </s:if>
				    <s:elseif test="dName=='id'">
				    	<tr align="left"><td>编号</td><td>整数</td><td>11</td><td>不允许为空</td><td>&nbsp;</td></tr>
				    </s:elseif>

					<s:else>
	           			<tr align="left">
	           				<td><input name='dNotes' id='dNotes' type='text' value="<s:property value="dNotes" />"/></td>
	           				<td>
	           					<select name="dType">
		           				<s:if test="%{dType=='整数'}" ><option selected="selected">整数</option></s:if>
		           					<s:else><option >整数</option></s:else>
		           				<s:if  test="%{dType=='字符串'}" ><option selected="selected">字符串</option></s:if>
		           					<s:else><option >字符串</option></s:else>
		           				<s:if  test="%{dType=='日期'}" ><option selected="selected">日期</option></s:if>
		           					<s:else><option >日期</option></s:else>   
		           				<s:if  test="%{dType=='小数'}" ><option selected="selected">小数</option></s:if>
		           					<s:else><option >小数</option></s:else>  
		           				<s:if  test="%{dType=='文件'}" ><option selected="selected">文件</option></s:if>
		           					<s:else><option >文件</option></s:else> 		           					
		           				</select>				
	           				</td>
	           				<td><input name='dLength' id='dLength' type='text' value="<s:property value="dLength" />"/></td>
	           				<td><select name="dAllowNull">
		           				<s:else><option >varchar</option></s:else>
		           				<s:if  test="%{dAllowNull=='允许为空'}" ><option selected="selected">允许为空</option></s:if>
		           					<s:else><option >允许为空</option></s:else>   
		           				<s:if  test="%{dAllowNull=='不允许为空'}" ><option selected="selected">不允许为空</option></s:if>
		           					<s:else><option >不允许为空</option></s:else>   
		           				</select>  	           				  	           				
	           				</td>
	           				<td><input type='button' value='删除' onclick='rm(this.parentNode.parentNode);'/></td>
	           				</tr>
					</s:else>           				
          		</s:iterator>
				</tbody>
			</table>
			<div align="center">
			子模板名称：<s:textfield name="text"></s:textfield>
			<input type="submit" value="提交" />
			</div>	
		</form>
	</body>
</html>