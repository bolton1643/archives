<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sw.pojo.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@include file="common/global.jsp"%>  
<html>
  <head>
    <base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/">
  
    <title>档案管理系统后台</title>
    
    
    <link href="css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-last.js" type="text/javascript"></script>    
    <script src="js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script src="js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="js/plugins/ligerTab.js" type="text/javascript"></script>

    <script type="text/javascript">
        function submitForm()
        {
        	if(confirm("确认要保存么？")== true)
        	{
        		if(confirm("再次确认要保存么？")== true)
        		{
        			//getCheckedData();
        			document.getElementById("sForm").submit();		
        		}
        	}
        }
        
        
        var tab = null;
        var accordion = null;
        var tree = null;
        $(function ()
        {
            //布局
            $("#layout1").ligerLayout({ leftWidth: 190, height: '100%', onHeightChanged: f_heightChanged });
            

            var height = $(".l-layout-center").height();

           
            //树
            $("#tree1").ligerTree({
                url: 'UserRightJson.action?user.userId=<s:property value="user.userId"/>',
	            idFieldName :'id',
	            parentIDFieldName :'pid',
                onBeforeExpand: onBeforeExpand,
                onExpand: onExpand,
                checkbox: false,
                nodeWidth: 420,
                attribute: ['nodename', 'url'],
                onSelect: function (node)
                {

                }
            });
                
             //树
            $("#tree2").ligerTree({
                url: 'UserRightJson.action?user.userId=0',
	            idFieldName :'id',
	            parentIDFieldName :'pid',
                onBeforeExpand: onBeforeExpand,
                onExpand: onExpand,
                checkbox: false,
                nodeWidth: 450,
                attribute: ['nodename', 'url'],
                onSelect: function (node)
                {

                }
            });
                           
                //accordion = $("#accordion1").ligerGetAccordionManager();
                tree = $("#tree2").ligerGetTreeManager();
                $("#pageloading").hide();
        });
        
		function getCheckedData()       
		{       
            var notes = tree.getChecked();
            var text = "";
            for (var i = 0; i < notes.length; i++)
            {
                
                text += notes[i].data.id + ","+notes[i].data.text;
            }
            alert('选择的节点数：' + text);
            
		}
        
                
        function onBeforeExpand(note)
        {
            //var note = tree.getSelected(); 
            //alert('选择的是:' + note.data.text);
            //alert(note.data.id)
            if (true)
            {
                //tree.loadData(note.target,'tree.action','root='+note.data.id+'&');
                //这里模拟一个加载节点的方法，append方法也用loadData(target,url)代替
                
                /*
                tree.append(note.target, [
                { text: note.data.text + "'s child1" },
                { text: note.data.text + "'s child2" },
                { text: note.data.text + "'s child3" }
               ]);
               */
            }
        }
        function onExpand(note)
        { 
        }
        function f_heightChanged(options)
        {
            if (tab)
                tab.addHeight(options.diff);
            if (accordion && options.middleHeight - 24 > 0)
                accordion.setHeight(options.middleHeight - 24);
        }
        function f_addTab(tabid,text, url)
        { 
            tab.addTabItem({ tabid : tabid,text: text, url: url });
        } 
                        
     </script> 

</head>
<body style="padding:0px;">  
	
	<div id="pageloading"></div>
	<table border="1">
	<tr>
		<td align="center"><a href="javascript:history.go(-1);">返回</a></td>
		<td align="center">
			<input type="button" value="保存" onclick="submitForm();" />
		</td>
	</tr>
	<tr><td>已有权限</td><td><div>修改用户权限</div></td></tr>
	<tr>
	<td align="left" valign="top">
		             <div title="功能列表" class="l-scroll" style="width:50%">
                         <ul id="tree1" style="margin-top:3px;">
                         
                         </ul>   
                    </div>
	</td>
	<td align="left" valign="top">
		            <div title="功能列表" class="l-scroll" style="display:block;width:50%">
		            	<form id="sForm" method="post" action="SaveRight.action">
		            	<input type="hidden" name="user.userId" value="<s:property value='user.userId' />" />
                         <ul id="tree2" style="margin-top:3px;">
                         
                         </ul>   
                         </form>
                    </div>	
	</td>
	</tr>
	</table>

</body>
</html>
