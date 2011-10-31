<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@include file="common/global.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty user.loginName}">
	<script>
		window.location.href='http://<%=request.getHeader("host")%><%=request.getContextPath()%>/page/login.jsp';
	</script>
</c:if>


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=siteName%></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<link rel="shortcut icon" href="/finance.ico"/>
		<link rel="stylesheet" href="css/tree/jquery.treeview.css" />
		<link rel="stylesheet" href="css/tree/screen.css" />
		<script type="text/javascript" src="js/jquery-last.js"></script>
		<script type="text/javascript" src="js/plugin/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/plugin/jquery.treeview.js"></script>
		<script type="text/javascript" src="js/plugin/jquery.treeview.edit.js"></script>
		<script type="text/javascript" src="js/plugin/jquery.treeview.async.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/menu.js"></script>
		<script src="js/plugin/jquery.contextmenu.r2.js"></script>
	
		<style type="text/css">
			@IMPORT url("css/all.css");
			@IMPORT url("css/layout.css");
			@IMPORT url("css/menu.css");
		</style>
			<script type="text/javascript">
		   $(document).ready(function(){
                $("#myLeftMenu").treeview({
                      url: "tree.action"
                 });

            });
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
                        				window.navigate("http://<%=request.getHeader("host")%><%=request.getContextPath()%>/"); 
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
			
	</head>
	<body>
		<!-- 头部 -->
		<div id="hdr">
			<h1 align="center"><%=siteName %></h1>
	    </div>
		<!-- 中间内容区域 -->
		<div id="c-block">
			<div id="c-col">
				<iframe src='' width='100%'	height='100%' frameborder='0' marginwidth='0' marginheight='0'
					scrolling='no' name='centerFrame' id='centerFrame'></iframe>
			</div>
			<!-- end of center column -->
		</div>
		<!-- 版权部分 -->
		<div id="ftr" align="center">
			Copyright &copy;<%=company %>
		</div>
		<!-- 左栏 -->
		<div id="lh-col">
			<h4 align="center">菜单列表</h4>
			<div><a href="fmList.action" target="centerFrame">档案管理</a></div><br />
			<!-- 
				<div><a href="fListView.action" target="centerFrame">档案查看</a></div><br />
			 -->
			<div><a href="page/irtest.jsp" target="centerFrame">报表样例</a></div><br />
			<hr />
			<ul id="myLeftMenu"></ul>
		</div>
		<!-- end of left column -->

	</body>

</html>
