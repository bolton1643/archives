<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="common/global.jsp" %>
<html>
<head>
        <link type="text/css" href="css/bruce.css" rel="stylesheet" />
        <title>上传文件</title>
        <script type="text/javascript" src="js/jquery-last.js"></script>
        <script type="text/javascript" src="js/ligerui.all.js"></script>
    <script type="text/javascript">
    function addMore()
    {//end
        var td = document.getElementById("more");
        var br = document.createElement("br");
        var input = document.createElement("input");
        var button = document.createElement("input");
        input.type="file";
        input.name="file";
        button.type="button";
        button.value="remove";
        
        button.onclick=function()
        {//end
            td.removeChild(br);
            td.removeChild(input);
            td.removeChild(button);
        }//end
        
        td.appendChild(br);
        td.appendChild(input);
        td.appendChild(button);
        
    }//endof
    
    $(document).ready(function(){
        $("#fileUploadForm").ligerForm();
    });//endoffunc
</script>
</head>
<body>
<%
    String p = (String)request.getParameter("p");
    String cid = (String)request.getParameter("cid");
    String tid = (String)request.getParameter("tid");
%>
<div class="bruce_txt">
  <ul>
    <li>
       <s:fielderror  cssStyle="color=red"/>       
    </li>               
    <s:form id="fileUploadForm" name="fileUploadForm" action="dupload.action" theme="simple" method="post" enctype="multipart/form-data">
    <input type="hidden" id="p" name="p" value="<%=p %>">
    <input type="hidden" id="cid" name="cid" value="<%=cid %>">
    <input type="hidden" id="tid" name="tid" value="<%=tid %>">
    <li>
        <span class="bruce_txt_3"><font color="#FF00FF">注意： 上传大文件，需要较长的时间，请耐心等待   </font></span>         
    </li>
    <li>
        <span class="bruce_txt_1">文件</span>
        <span class="bruce_txt_2"><s:file name="file" id="file"></s:file></span>
    </li>               
    <li>
        <span class="bruce_txt_1"></span>
        <span class="bruce_txt_4">
           <div class="btn" style="margin:0 auto;float:left">
                  <div class="btn_l"></div>
                  <input class="btn_m" type="submit" name="uploadButton" id="uploadButton" value="开始上传"/>  
                  <div class="btn_r"></div>
            </div>
        </span>
        <span class="bruce_txt_4">
           <div class="btn" style="margin:0 auto;float:left">
                  <div class="btn_l"></div>
                  <input class="btn_m" type="button" name="cancelUploadButton" id="cancelUploadButton" value="取消上传"/>
                  <div class="btn_r"></div>
            </div>
        </span>
     </li>        
    </s:form>
</ul>
</div>

    <div id="progressBar">
    <div id="theMeter">
        <div id="progressBarText"></div>
            <div id="totalProgressBarBox">
                <div id="totalProgressBarBoxContent"></div>
            </div>
        </div>
        <div id="progressStatusText"></div>
   </div>
   
<script>
//刷新上传状态
function refreshUploadStatus(){
    var ajaxW = new AjaxWrapper(false);
    ajaxW.putRequest(
        'Upload.action',
        'uploadStatus=',
        function(responseText){
                eval("uploadInfo = " + responseText);
                var progressPercent = Math.ceil(
                    (uploadInfo.ReadTotalSize) / uploadInfo.UploadTotalSize * 100);
    
                $('progressBarText').innerHTML = ' 上传处理进度: '+progressPercent+'% ['+
                    (uploadInfo.ReadTotalSize)+'/'+uploadInfo.UploadTotalSize + ' bytes]'+
                    ' 正在处理第'+uploadInfo.CurrentUploadFileNum+'个文件'+
                    ' 耗时: '+(uploadInfo.ProcessRunningTime-uploadInfo.ProcessStartTime)+' ms';
                $('progressStatusText').innerHTML=' 反馈状态: '+uploadInfo.Status;
                $('totalProgressBarBoxContent').style.width = parseInt(progressPercent * 3.5) + 'px';
        }
    );
}
//上传处理
function startProgress(){
    Element.show('progressBar');
    $('progressBarText').innerHTML = ' 上传中';
    $('progressStatusText').innerHTML=' ';
    $('uploadButton').disabled = true;
    //var periodicalExe=new PeriodicalExecuter(refreshUploadStatus,0.5);
    return true;
}
//取消上传处理
function cancelProgress(){
    $('cancelUploadButton').disabled = true;
    var ajaxW = new AjaxWrapper(false);
    ajaxW.putRequest(
        'Upload.action',
        'cancelUpload=true',
        //因为form的提交，这可能不会执行
        function(responseText){
            eval("uploadInfo = " + responseText);
            $('progressStatusText').innerHTML=' 反馈状态: '+uploadInfo.status;
            if (msgInfo.cancel=='true'){
                alert('删除成功!');
                window.location.reload();
            };
        }
    );
}
</script>
    
</body>
</html>