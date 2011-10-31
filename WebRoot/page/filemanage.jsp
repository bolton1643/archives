<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tlds/paging.tld"%>
<%@include file="common/global.jsp"%>
<html>
	<head>
			<script type="text/javascript" src="js/jquery-last.js"></script>
			<script type="text/javascript">
			_itemId = "";
			function delFile(id){
				var postUrl = "fmDel.action";
				var d = "c.id="+id;
				_itemId = id;
				if ((window.confirm('你确定要删除吗？')==true))
				{
					if ((window.confirm('删除后数据无法恢复，请再次确认？')==true))
					{
						$.ajax({
                        type: "post",
                        url:postUrl,
                        dataType:"html",
                        data:d,
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
	                        //alert(XMLHttpRequest.status);
	                        //alert(XMLHttpRequest.readyState);
	                        //alert(textStatus);
	                        //alert(errorThrown);
	                        alert('网络错误');
                        },
                        success:function(data,textStatus)
                        		{
                        			if(data == 'success')
                        			{
                        				alert("删除成功！");
										var delTR = document.getElementById("tr"+_itemId);
										delTR.parentNode.removeChild(delTR);
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
		<title>文件管理</title>
	</head>
	<body>
		<div
			style="background-color: #7CC3FD; padding: 0 5px; color: #FFFFFF; font-weight: bold">
			+ 您的位置: 文件管理
		</div>
		<div align="center">
		    <s:form action="fmList.action" theme="simple">
			文件标题:<s:textfield name="name" label="文件标题" size="10"></s:textfield>
			<s:reset value=" 重置 "></s:reset>
			<s:submit value=" 查询 "></s:submit>
			[ <A href="fmAdd.action">添加文件</A>]
			</s:form>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="4">
			<tr>
				<td>
					文件名称
				</td>
				<td>
					文件路径
				</td>
				<td>
					缩略图
				</td>				
				<td>
					上传文件人员
				</td>
				<td>
					上传日期
				</td>				
				<td align="center">
					编辑
				</td>
			</tr>

			<s:iterator id="cl" value="cl" status="st">
				<tr bgcolor="#EEEEEE"
					id="tr<s:property value="id"/>">
					<td>
						<s:property value="fileName" />
					</td>
					<td>
						<s:property value="filePath" />
					</td>
					<td>
						<s:property value="image" />
					</td>					
					<td>
						<s:property value="uploadBy" />
					</td>
					<td><s:date name="uploadDate" format="yyyy-MM-dd" /></td>
					<td height="20" align="center">
						<!-- 
							[<A href="fmGet.action?c.id=<s:property value="id"/>">修改</A>]
						 -->
						 
							[<a title="删除" onclick="javascript:delFile(<s:property value="id"/>); return false;" href="javascript:void(0);" >删除</a>]
					</td>
				</tr>
			</s:iterator>

		</table>
		<table width="100%" height="40" border="0" cellpadding="4"
			cellspacing="0">
			<tr>
				<td align="center">
					<font color="#990000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>共
							<s:property value="total" /> 页 第 <s:property value="cpage" />
							页&nbsp; <tangs:pages cpage="%{cpage}" total="%{total}"
								url="%{url}" /> </strong> </font>
				</td>
			</tr>
		</table>
	</body>
</html>
