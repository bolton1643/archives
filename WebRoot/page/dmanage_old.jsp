<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en">

<%@ page language="java" pageEncoding="UTF-8"  import="java.util.*,com.sw.util.MetaDataRow"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tangs" uri="/WEB-INF/tlds/paging.tld"%>
<%@include file="common/global.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<head>
	<script type="text/javascript" src="js/jquery-last.js"></script>
	<link href="css/core.css" rel="stylesheet" type="text/css"/>
    <link href="css/Toolbar.css" rel="stylesheet" type="text/css"/>
    <script src="js/tab/Toolbar.js" type="text/javascript"></script>
    <script type="text/javascript">
    //js的注释与html的注释放开,再看一下效果
    $(document).ready(function(){	
            var addIdd = document.getElementById("addId").value;
			var toolbar = new Toolbar({
		        renderTo : 'toolbar',
				//border: 'top',
		        items : [{
		          type : 'button',
		          text : '新建',
		          bodyStyle : 'new',
		          useable : 'T',
		          handler : function(){
		            window.location.href=addIdd;
					//top.tabpanel.addTab({id:'baidu', title:'百度一下！', html:'<iframe src="http://www.baidu.com" width="100%" height="100%" frameborder="0"></iframe>'});
		          }
		        },'-',{
		          type : 'button',
		          text : '查询',
		          bodyStyle : 'search',
		          useable : 'T',
		          handler : function(){
		          	var obj = document.getElementById("queryCondition");
		          	if(obj.style.display=='none')
		          		obj.style.display="";
		          	else
		          		obj.style.display='none';
		          }
		          }
        		],
		        active : 'ALL'//激活哪个
		      });
		
			  toolbar.render();
		    });
		    
		    </script>
			<script type="text/javascript">
			_itemId = "";
			function delData(tid,id){
				var postUrl = "dDel.action";
				var d = "id="+id+"&tid="+tid;
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
		
		<script type="text/javascript">
			function rm(theRow){
				var tb = document.getElementById("insertPosition");
				//alert(tb.innerHTML)
				//alert(theRow.innerHTML)
				tb.removeChild(theRow);
			}
			$(document).ready(function(){
				$("#qRow").click(function(){
					var insertPos = $("#insertPosition");
					var trNode = $("<div><img src='images/minus.gif' onclick='rm(this.parentNode);'/>&nbsp;<select name='qColumn' style='width:100px;'><s:iterator value='cList' status='st'><option value=<s:property value='dName' />><s:property value='dNotes' /></option></s:iterator></select>&nbsp;<select name='qCond'><option>包含</option><option>等于</option><option>大于等于</option><option>小于等于</option></select>&nbsp;<input type='text' name='qValue' /></div>");
					insertPos.append(trNode);
					//alert(document.getElementById("insertPosition").innerHTML)
				});
			});
		</script>		
		<title>数据管理</title>
	</head>
	<body>

		<div
			style="background-color: #7CC3FD; padding: 0 5px; color: #FFFFFF; font-weight: bold">
			+ 您的位置: 数据管理
		</div>
		<!-- 	
			<div align="center">
			    <s:form action="fmList.action" theme="simple">
				文件标题:<s:textfield name="name" label="文件标题" size="10"></s:textfield>
				<s:reset value=" 重置 "></s:reset>
				<s:submit value=" 查询 "></s:submit>
				</s:form>
			</div>
		
			<div id="toolbar">
				[ <A href="dAdd.action?tid=<s:property value="tid"/>">添加数据</A>]	
			</div>
		-->
		<div id="queryCondition" style="align:right;display:none;">
			<form action="dList.action"  method="post">
			<input type="hidden" name="tid" value="<s:property value="tid"/>" />
			<img src="images/plus.gif" id="qRow"/>
			<select id="qCol" style="width:100px;" name="qColumn">
				<s:iterator value="cList" status="st">
					<option value="<s:property value="dName" />"><s:property value="dNotes" /></option>
	        	</s:iterator>
			</select>
			<select name="qCond">
				<option>包含</option>
				<option>等于</option>
				<option>大于等于</option>
				<option>小于等于</option>
			</select>
			<input type="text" name="qValue" />
			<input type="submit" value="查询" />
			<br>
			<div id="insertPosition"></div>
			</form>
		</div>
		<div id="toolbar">
			
		</div>
		<INPUT type="hidden" id="addId" value="dAdd.action?tid=<s:property value="tid"/>">
		<table width="100%" border="0" cellspacing="0" cellpadding="4">
			<tr align="left">
			<s:iterator value="cList" status="st">
				<th><s:property value="dNotes" />&nbsp;</th>
	        </s:iterator>
	        <th>编辑</th>
	        </tr>
	       
			<s:iterator id="dList" value="dList" status="out">
				<tr bgcolor="#EEEEEE" id="tr<s:property value="dList[#out.index][0]"/>">
					<s:iterator value="cList" status="inner">
						<s:if test="dType=='日期'">
							<td><s:date name="dList[#out.index][#inner.index]" format="yyyy-MM-dd"/>&nbsp;</td>
						</s:if>
						<s:else>
							<td><s:property value="dList[#out.index][#inner.index]" />&nbsp;</td>
						</s:else>
						
					</s:iterator>	
					<td align="left">
							[<A href="dEdit.action?tid=<s:property value="tid"/>&id=<s:property value="dList[#out.index][0]"/>">修改</A>]
						 
							[<a title="删除" onclick="javascript:delData('<s:property value="tid"/>',<s:property value="dList[#out.index][0]"/>); return false;" href="javascript:void(0);" >删除</a>]
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
								url="%{url}" param="%{param}" /> </strong> </font>
				</td>
			</tr>
		</table>
	</body>
</html>
