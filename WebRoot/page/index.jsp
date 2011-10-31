<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/">
  
    <title>档案管理系统</title>
    
    
    <link href="css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script src="js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="js/plugins/ligerTab.js" type="text/javascript"></script>

    <script type="text/javascript">
        var tab = null;
        var accordion = null;
        var tree = null;
        $(function ()
        {
            //布局
            $("#layout1").ligerLayout({ leftWidth: 190, height: '100%', onHeightChanged: f_heightChanged });
            

            var height = $(".l-layout-center").height();

            //Tab
            $("#framecenter").ligerTab({ height: height });
            
            //树
            $("#tree1").ligerTree({
                url: 'tree.action?root=source',
	            idFieldName :'id',
	            parentIDFieldName :'pid',
                onBeforeExpand: onBeforeExpand,
                onExpand: onExpand,
                checkbox: false,
                nodeWidth: 120,
                attribute: ['nodename', 'url'],
                onSelect: function (node)
                {
                	if(node.data.isLeaf == 'false') return;
                    if (!node.data.url) return;
                    var tabid = $(node.target).attr("tabid");
                    if (!tabid)
                    {
                        tabid = new Date().getTime();
                        $(node.target).attr("tabid", tabid)
                    }
                    if ($(">ul >li", tab.tab.links).length >= 100)
                    {
                        var currentTabid = $("li.l-selected", tab.tab.links).attr("tabid"); //当前选择的tabid
                        if (currentTabid == "home") return;
                        tab.overrideTabItem(currentTabid, { tabid: tabid, url: node.data.url, text: node.data.text });
                        return;
                    }
                    f_addTab(tabid, node.data.text, node.data.url);
                }
            });
                
                tab = $("#framecenter").ligerGetTabManager();
                //accordion = $("#accordion1").ligerGetAccordionManager();
                tree = $("#tree1").ligerGetTreeManager();
                $("#pageloading").hide();
        });
        
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
<style type="text/css"> 
    body{ padding:0px; margin:0;   overflow:hidden;}  
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:white;}
    .l-layout-top{background:#102A49; color:White;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('images/loading.gif') no-repeat center; width:100%; height:100%; height:700px; z-index:99999;}
    .l-link{ display:block; line-height:22px; height:22px; padding-left:20px;border:1px solid white; margin:4px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;}
 </style>
</head>
<body style="padding:0px;">  
<%
	String uName = (String)session.getAttribute("username");
	if(uName!=null && uName.length()>0){
		
	}else{
		String c = request.getHeader("host")+request.getContextPath();
		response.sendRedirect("http://"+c+"/page/login.jsp");
	}
%>
<div id="pageloading"></div>
  <div id="layout1" style="width:100%">
        <div position="top" align="right" style="background:#102A49; color:White; ">
            <div align="left" style="margin-top:10px; margin-left:10px">档案管理系统</div>
            欢迎您：<%=uName  %>&nbsp;&nbsp;<a href="admin/logout.jsp">退出系统</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div position="left"  title="主要菜单" id="accordion1"> 
                     <div title="功能列表" class="l-scroll">
                         <ul id="tree1" style="margin-top:3px;">
                         
                         </ul>   
                    </div>
        </div>
        <div position="center" id="framecenter"> 
            <div tabid="home" title="我的主页" style="height:300px" >
                <iframe frameborder="0" name="home" src="page/welcome.html"></iframe>
            </div> 
        </div> 

    </div> 
        <div style="display:none">
    </div>
</body>
</html>
