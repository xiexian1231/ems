<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>工卡内容丨JobcardContents</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <link href="/ems/ui/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/ems/ui/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    <link href="/ems/ui/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="/ems/ui/plugins/jquery-datepicker/jquery-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="/ems/ui/css/jobcard.css" rel="stylesheet" type="text/css" />
    <link href="/ems/ui/plugins/gurde-ZOOM/css/head.css" rel="stylesheet" media="all" />
	<link href="/ems/ui/plugins/gurde-ZOOM/css/zoom.css" rel="stylesheet" media="all" />
    <script type="text/javascript" src="/ems/ui/js/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div class="b_main" id="b_main" style="margin-left: 115px;margin-top: 314px;z-index: 10;"> 
	<div class="border" style="margin-top: -174px;width:580px;height:380px;">
		<div style="background-color: #5C9BD1;">
			<span style="color: white;font-weight: bolder;position: relative;top: -3px;">参考文件</span>
               <span style="margin-left: 479px;"><a href="javascript:;" id="Delc"
                   style="text-decoration: none;font-weight: bolder;font-size: 19px;"> × </a>
               </span>
           </div>
          	<table class="table table-striped table-bordered table-hover" id="References">
			<thead>
                  <tr>
                    <th> Reference </th>
                    <th> Title </th>
                    <th> Remark </th>
                  </tr>
            </thead>
			<tbody>
				<c:forEach var="reference" items="${requestScope.References }">
					<tr>
						<td>${reference.reference }</td>
						<td>${reference.title }</td>
						<td>${reference.remark } </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> 
	<div class="out" style="top:8px;"> 
	<div class="in" ></div> 
	</div> 
	</div> 
		
	<div class="b_main" id="h_main" style="margin-left: 115px;margin-top: 427px;z-index: 10;"> 
	<div class="border" style="margin-top: -174px;width:580px;height:380px;">
		<div style="background-color: #5C9BD1;">
			<span style="color: white;font-weight: bolder;position: relative;top: -3px;">航材资料</span>
               <span style="margin-left: 479px;"><a href="javascript:;" id="Deld"
                   style="text-decoration: none;font-weight: bolder;font-size: 19px;"> × </a>
               </span>
           </div>
		<table class="table table-striped table-bordered table-hover" id="Equipment">
			<thead>
                  <tr>
                    <th> Reference </th>
                    <th> Description </th>
                    <th> QTY </th>
                    <th> Remark </th>
                  </tr>
            </thead>
			<tbody>
				<c:forEach var="equipment" items="${requestScope.Equipments }">
					<tr>
						<td>${equipment.reference }</td>
						<td>${equipment.description }</td>
						<td>${equipment.qty } </td>
						<td>${equipment.remark } </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> 
	<div class="out" style="top:8px;"> 
	<div class="in"></div> 
	</div> 
	</div> 
		
	<div class="b_main" id="f_main" style="margin-left: 115px;margin-top: 540px;z-index: 10;"> 
	<div class="border" style="margin-top: -223px;width:580px;height:480px;">
		<div style="background-color: #5C9BD1;">
			<span style="color: white;font-weight: bolder;top: -2px;position: relative;">设计图</span>
               <span style="margin-left: 495px;"><a href="javascript:;" id="Delp"
                   style="text-decoration: none;font-weight: bolder;font-size: 19px;"> × </a>
               </span>
           </div>
           <div class="gallery">
           	<c:choose>
           		<c:when test="${fn:length(requestScope.pics)>0 }">
           			<c:forEach var="pic" items="${requestScope.pics }" >
           				<div><a href="/ems/ui/img/${pic }"><img src="/ems/ui/img/${pic }" /></a></div>
           			</c:forEach>
           		</c:when>
           		<c:otherwise>
           			<span>此工卡无设计图</span>
           		</c:otherwise>
           	</c:choose>
		</div>
		<div class="clear"></div>
	</div> 
	<div class="out" style="top:8px;"> 
	<div class="in"></div> 
	</div> 
	</div> 
		
	<div class="b_main" id="l_main" style="margin-left: 115px;margin-top: 885px;z-index: 10;"> 
	<div class="border" style="margin-top: -300px;width:580px;height:380px;">
		<div style="background-color: #5C9BD1;">
			<span style="color: white;font-weight: bolder;position: relative;top: -3px;">问题报告</span>
               <span style="margin-left: 479px;"><a href="javascript:;" id="Delw"
                   style="text-decoration: none;font-weight: bolder;font-size: 19px;"> × </a>
               </span>
           </div>
		<input type="text" value="问题报告^_^">
	</div> 
	<div class="out" style="top:8px;"> 
	<div class="in" ></div> 
	</div> 
	</div>
	<div id="d1">
		<table class="table table-striped table-bordered table-hover">
			<thead id="th1">
                   <tr>
                     <th> 工具栏 </th>
                   </tr>
            </thead>
			<tbody id="tb1">
				   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/bj.png');width: 96px;height: 96px;"></button> </td>
                   </tr>
                   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/xpc.png');width: 96px;height: 96px;"></button> </td>
                   </tr>
                   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/ckwj.png');width: 96px;height: 96px;"></button> </td>
                   </tr>
                   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/hczl.png');width: 96px;height: 96px;"></button> </td>
                   </tr>
                   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/gkt.png');width: 96px;height: 96px;"></button> </td>
                   </tr>
                   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/fd.png');width: 96px;height: 96px;"></button> </td>
                   </tr>
                   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/sx.png');width: 96px;height: 96px;"></button> </td>
                   </tr>	
                   <tr>
                       <td> <button type="button" class="btn btn-warning" style="background-image: url('/ems/ui/img/wtbg.png');width: 96px;height: 96px;"></button> </td>
                   </tr>
			</tbody>
		</table>
	</div>
	<div class="portlet box green" style="width: 89%;float: right;">
	     <div class="portlet-title">
	         <div class="tools" style="vertical-align:middle;text-align:center;float: left;">
	             	<div style="float: left;"><img src="<%=request.getContextPath() %>/ui/img/sh.png"></div>
	             	<div style="float: left;margin-top: 5px;"><span>深圳航空有限责任公司</span><br>
	             	<span>Shenzhen Airlines</span></div></div>
	         <div class="caption" style="vertical-align:middle;text-align:center;margin-left: 355px;"> 
	         		<div style="margin-top: 8px;"><span>飞机维修工作卡</span><br>
	             	<span>MAINTENANCE TASK CARD</span></div>
	         </div>
	         <div class="tools" style="vertical-align:middle;text-align:center;">
	         		<span></span><br>
	             	<span>工卡编号/TC NO.30.-010-00-01</span>
	         </div>
	     </div>
	     <div class="portlet-body">
	     	 <div class="table table-striped table-bordered table-hover" id="sample_2" style="height: 708px;">
                 <iframe id="iframe" src="${requestScope.htmlurl }" width="100%" height="100%"
					scrolling="auto" marginheight="0" marginwidth="0" align="center"
					style="border: 0px solid #CCC; margin: 0; padding: 0;">
				 </iframe>
             </div>
	         <table class="table table-striped table-bordered table-hover" id="sample_2">
	         	<thead>
		         	<tr>
		         		<th style="vertical-align:middle;text-align:center;">操作</th>
		         		<th style="width: 40%;vertical-align:middle;text-align:center;">工号</th>
		         		<th style="width: 30%;vertical-align:middle;text-align:center;">时间</th>
		         	</tr>
	         	</thead>
	         	<tbody>
		         	<tr>
		         		<td><span><img src="<%=request.getContextPath() %>/ui/img/zj.png"></span><span><img src="<%=request.getContextPath() %>/ui/img/sc.png"></span><span><input type="button" value="save"> </span> </td>
		         		<td style="text-align: right;"><img src="<%=request.getContextPath() %>/ui/img/tjr.png"></td>
		         		<td><input type="text" id="datepicker" style="width: 100%;height: 100%;text-align: center;" autocomplete="off" placeholder="选择日期" readonly></td>
		         	</tr>
	         	</tbody>
	         </table>
	         <div style="width: 100%;text-align: right;" >
	         	<div style="margin-right: 100px;" id="ds">
	         		工时：<input type="text" id="txt" value="${requestScope.taskTime }" style="text-align: center;" readonly>
	         		<button type="button" class="btn btn-info" onClick="setflag(this,'${requestScope.taskTime }')">启动</button>
		          	<button id="save-info" type="button" class="btn btn-info">保存</button>
		          	<button type="button" class="btn btn-info">提交</button>
	         	</div>
	         </div>
	     </div>
	 </div>
	 <script type="text/javascript" src="/ems/ui/js/jobcard.js"></script>
	 <script type="text/javascript" src="/ems/ui/plugins/gurde-ZOOM/js/zoom.min.js"></script>
	 <script type="text/javascript" src="/ems/ui/plugins/jquery-datepicker/jquery-ui.min.js"></script>
	 <script type="text/javascript" src="/ems/ui/plugins/jquery-DJMask/jquery-DJMask.2.1.1.js"></script>
</body>
</html>