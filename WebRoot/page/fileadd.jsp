<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="common/global.jsp" %>
<html>
	<head>
	<script type="text/javascript">
	    function delImg(trid){
	    	if(confirm('你确定要删除该文件？')){
	    	    var tr = document.getElementById(trid);
	    	    tr.parentNode.removeChild(tr);
	    	    }
		}
	    function delVideo(trid){
	    	if(confirm('你确定要删除该文件？')){
	    	    var tr = document.getElementById(trid);
	    	    tr.parentNode.removeChild(tr);
	    	    }
		}		
	    function addPic()
	    {
	        var hasPic = document.getElementById('addPicFun').value
	        if(hasPic =='添加图片') 
	          {
	           	document.getElementById('addPicFun').value = '删除图片';
	           	document.getElementById('tupian').innerHTML = "<input type='file' name='image'/><input type='button' value='添加更多图片...' onclick=addMore('tupian')>";
	          }
	            else 
	          {  
	            document.getElementById('addPicFun').value = '添加图片';
	            //document.getElementById('tupian').parentNode.removeChild(document.getElementById('tupian'));
	            document.getElementById('tupian').innerHTML="";
	          } 
	    }
	    
		function addMore(myId)
	{
		var td = document.getElementById(myId);
		
		var br = document.createElement("br");
		
		var input = document.createElement("input");
		
		var button = document.createElement("input");
		
		input.type="file";
		input.name="file";
		
		button.type="button";
		button.value="remove";
		
		button.onclick=function()
		{
			td.removeChild(br);
			td.removeChild(input);
			td.removeChild(button);
		}
		
		
		td.appendChild(br);
		td.appendChild(input);
		td.appendChild(button);
		
	}
	</script>
		<title>文件管理</title>
	</head>
<body>	
<s:form action="fmDoAdd.action" theme="simple" name="form1" id="form1" method="post"  enctype="multipart/form-data">
<table border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>上传路径</td><td><s:select list="fileFolder" name="uploadFolder"></s:select></td></tr>
<tr><td>上传人</td><td><input type="text" name="c.uploadBy" value="<s:property value="#session.user.userName"/>"/></td></tr>
<tr id="tupian">
	<td>图片</td>
	<td><input type="button" name="Submit" value="选择图片" onClick="window.open('page/upload.jsp?p=notice','fileUpload','width=480,height=320')"></td>
</tr>
<tr><td></td><td><input type="submit" value="提交" /></td></tr>
</table>


</s:form>
</body>
</html>