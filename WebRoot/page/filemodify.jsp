<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>
<%@include file="common/global.jsp" %>
<html>
	<head>
<style type="text/css">
<!--
a {
	text-decoration: none;
}
a:hover {
	color: #FF0000;
	text-decoration: underline;
}
a:link {
	font-weight: bold;
	color: #FF3300;
}
a:visited {
	font-weight: bold;
	color: #FF9900;
}
.box {
	border: 1px solid #990066;
}
-->
</style>
<style type="text/css">
<!--
form {
	margin: 0px;
}
body {
	background-image: url(images/newsAdd.asp);
}
body,td,th {
	font-family: 宋体;
}
-->
</style>	
	
	
	<sx:head/>
	<base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/">	
	<script type="text/javascript">
	
		function delExistImg(trid)
		{
			if(confirm('你确定要删除该文件？')){
	    	    var tr = document.getElementById(trid);
	    	    tr.parentNode.removeChild(tr);
	    	    }
		}
		function delImg(trid){
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
	    
	    function addVid()
	    {
	        var hasPic = document.getElementById('addVidFun').value
	        if(hasPic =='添加视频') 
	          {
	           	document.getElementById('addVidFun').value = '删除视频';
	           	document.getElementById('shipin').innerHTML = "<input type='file' name='video'/><input type='button' value='添加更多图片...' onclick=addMore('shipin')>";
	          }
	            else 
	          {  
	            document.getElementById('addVidFun').value = '添加视频';
	            //document.getElementById('tupian').parentNode.removeChild(document.getElementById('tupian'));
	            document.getElementById('shipin').innerHTML="";
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
		<title>修改健康保健</title>
		<style type="text/css">
		
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		-->
		</style>		
	</head>
<body>	
		<s:fielderror/>
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="45"><img name="news_r1_c1" src="images/news_r1_c1.gif" width="45" height="38" border="0" alt=""></td>
          <td valign="bottom" background="images/news_r1_c10.gif">
<table width="100%" height="34" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td><img name="news_r2_c8" src="images/news_r2_c84.gif" width="99" height="19" border="0" alt=""></td>
                <td align="right"><font size="-1">[ <a href="Health.action">回管理主画面</a>
                  ]</font></td>
              </tr>
            </table></td>
          <td width="11"><img name="news_r1_c11" src="images/news_r1_c11.gif" width="11" height="38" border="0" alt=""></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="11" background="images/news_r4_c1.gif">　</td>
          <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="7" height="8"><img name="news_r4_c2" src="images/news_r4_c2.gif" width="7" height="8" border="0" alt=""></td>
                <td background="images/news_r4_c3.gif"><img src="images/spacer.gif" width="1" height="8"></td>
              </tr>
              <tr>
                <td height="8" background="images/news_r6_c2.gif">　</td>
                <td style="word-break:break-all">
                <s:form action="HealthModify.action" theme="simple" name="form1" id="form1" method="post">
                    <s:hidden name="c.id"></s:hidden>
                    <table width="100%" border="0" cellspacing="0" cellpadding="4">
                      <tr>
                        <td align="center"> <table width="100%" border="0" cellpadding="4" cellspacing="1" class="box">
                            <tr valign="baseline" bgcolor="#EEEEEE">
                              <td width="100" height="20" align="right" bgcolor="#990099"><font color="#FFFFFF" size="2"><strong>新闻标题：</strong></font></td>
                              <td height="20"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                <s:textfield id="c.title" name="c.title" label="新闻标题" size="40"/>
                                </font></td>
                            </tr>
                            <tr valign="baseline" bgcolor="#EEEEEE">
                              <td height="20" align="right" bgcolor="#990099"><font color="#FFFFFF" size="2"><strong>公告日期：</strong></font></td>
                              <td height="20"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                <sx:datetimepicker name="c.addDate" displayFormat="yyyy-MM-dd" value="today" toggleType="wipe"/>
                              </font></td>
                            </tr>
                            <tr valign="baseline" bgcolor="#EEEEEE">
                              <td height="20" align="right" bgcolor="#990099"><font color="#FFFFFF" size="2"><strong>编辑人：</strong></font></td>
                              <td height="20"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                <s:textfield id="editBy" name="c.editBy" label="编辑人" size="20"/>
                                </font></td>
                            </tr>
                            <tr valign="baseline" bgcolor="#EEEEEE">
                              <td height="20" align="right" valign="middle" bgcolor="#990099"><font color="#FFFFFF" size="2"><strong>内容：</strong></font></td>
                              <td height="20"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                               
							 <s:textarea name="c.content" label="内容" cols="60" rows="10"></s:textarea>
								
								</font></td>
                            </tr>
                            <tr valign="baseline" bgcolor="#EEEEEE">
                            <td height="20" align="right" valign="middle" bgcolor="#990099"><strong><font color="#FFFFFF" size="2">其他功能:</font></strong></td>
                            <td>
                            <!-- 
                                <input type="button" onclick="addPic()" id="addPicFun" value="添加图片" />
                                <input type="button" onclick="addVid()" id="addVidFun" value="添加视频" />      
                             -->

                            </td>
                            </tr>
   							<tr valign="baseline" bgcolor="#EEEEEE">
                              <td height="20" align="right" valign="middle" bgcolor="#990099"><strong><font color="#FFFFFF" size="2">图片:</font></strong></td>
                              <td height="20"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                <input type="button" name="Submit" value="选择图片" onClick="window.open('page/admin/upload.jsp?p=health','fileUpload','width=480,height=320')">

                                </font>
                                <table>
                                
                                <s:iterator  id="image" value="image" status="st">
                                <tr id="etd<s:property value="#st.index"/>"><td>
                                <input type="text" name="image" readOnly="true" size="30" value="<s:property value="image[#st.index]"/>" />
                                <input type="button" value="删除" onclick="delExistImg('etd<s:property value="#st.index"/>')"/>
                                </td></tr>
                                </s:iterator>                                
                                
                                </table>
                                <table><tr id="tupian"></tr></table>
                                </td>
                            </tr>
   							<tr valign="baseline" bgcolor="#EEEEEE">
                              <td height="20" align="right" valign="middle" bgcolor="#990099"><strong><font color="#FFFFFF" size="2">视频:</font></strong></td>
                              <td height="20"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                <input type="button" name="Submit" value="选择视频" onClick="window.open('page/admin/uploadvideo.jsp?p=health','fileUpload','width=480,height=320')">

                                </font>
                                <table>
                                
                                <s:iterator  id="video" value="video" status="st">
                                <tr id="vi<s:property value="#st.index"/>"><td>
                                <input type="text" name="video" readOnly="true" size="30" value="<s:property value="video[#st.index]"/>" />
                                <input type="button" value="删除" onclick="delExistImg('vi<s:property value="#st.index"/>')"/>
                                </td></tr>
                                </s:iterator>                                
                                
                                </table>
                                <table><tr id="shipin"></tr></table>
                                </td>
                            </tr>
                          </table>
                          <hr> <input type="submit" name="Submit" value="添加新闻">
                          <input type="reset" name="Submit2" value="重设新闻"> <div align="center"></div></td>
                      </tr>
                    </table>
                  
                </s:form></td>
              </tr>
            </table></td>
          <td width="11" background="images/news_r4_c11.gif">　</td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="11"><img src="images/news_r8_c1.gif" width="15" height="26"></td>
    <td width="578" background="images/news_r8_c6.gif">&nbsp;</td>
    <td width="11"><img src="images/news_r8_c11.gif" width="14" height="26"></td>
  </tr>
  <script language = "JavaScript">
  	function aaa(){
  	var reV =document.getElementById("reVedio");
		alert(reV.value)
	}
</Script>
</table>		
	</body>
</html>