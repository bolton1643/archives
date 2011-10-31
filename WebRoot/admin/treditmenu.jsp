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
		</script>
		<title>编辑子模板</title>
	</head>
	<body>
		<form action="trDoEditMenu.action" id="trAddForm" name="trAddForm" method="post">
			<s:hidden name="id"></s:hidden>
			<s:hidden name="parentId"></s:hidden>
			<s:hidden name="expanded"></s:hidden>
			<input type="hidden" name="isfolder" id="isfolder" value="1"/> 
			<div align="center">
			子菜单名称：<s:textfield name="text"></s:textfield>
			<input type="submit" value="提交" />
			</div>	
		</form>
		<div>
			<a href="admin/traddmenu.jsp?pid=<s:property value="id" />" >增加子菜单</a>
			<br />
			<br />
			<a href="admin/traddtemplate.jsp?pid=<s:property value="id" />">增加子模板 </a>
			<br />
			<br />
			<a href="javascript:testFunction(<s:property value="id" />)">删除模板</a>			
			
		</div>





	
	</body>
</html>