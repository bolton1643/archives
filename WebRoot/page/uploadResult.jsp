<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>    
<%@include file="common/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<base target="_self" /> 
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="v" value="<s:property value="fileName" />">

</body>
</html>

<script type="text/javascript">
    //alert(parent.document.getElementById("image").value);
    var s = document.getElementById('v').value;
    var strs = s.split(';');
    var myDate = new Date();
    
	
	var parent = opener.window;
    //var tupian = parent.document.getElementById("tupian");
    for(i=0;i<strs.length;i++)
    {
        myDate = new Date();
        var ss = myDate.getSeconds();
        
        var tr = parent.document.createElement("tr");
        tr.id = ss;
        
        var td1 = parent.document.createElement("td");
    	var br = parent.document.createElement("br");
    	//parent.document.getElementById("tupian").appendChild(br);
    	
    	var td2 = parent.document.createElement("td");
    	var imageName = parent.document.createElement('input');
    	imageName.type="text";
    	imageName.id="image";
    	imageName.name="image";
    	imageName.readOnly = true;
    	imageName.size="30";
    	imageName.value=strs[i];
    	//parent.document.getElementById("tupian").appendChild(imageName);
    	
    	var td3 = parent.document.createElement("td");
    	td3.innerHTML = "<input type='button' value=' 删除 ' onclick='delImg("+ss+")'>";
    	//var imgNow  = parent.document.createElement('img');
    	//imgNow.src="images/delete.gif";
    	//imgNow.value = "aaa";
	    
		td1.appendChild(br)
		td2.appendChild(imageName)
    	
    	tr.appendChild(td1);
    	tr.appendChild(td2);
    	tr.appendChild(td3);
    	
    	parent.document.getElementById("tupian").appendChild(tr);
    	//alert(parent.document.getElementById("tupian").innerHTML);
    	alert("添加图片成功！");
    }
	window.close();
</script>