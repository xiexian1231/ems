<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>我的任务丨MyTasks</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link href="/ems/ui/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="/ems/ui/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="/ems/ui/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/ems/ui/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<link href="/ems/ui/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
<link href="/ems/ui/plugins/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
<link href="/ems/ui/css/task.css" rel="stylesheet" type="text/css" />
<link href="/ems/ui/plugins/jquery-confirm/css/jquery-confirm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/ems/ui/js/jquery-1.9.1.min.js"></script>
</head>
<body class="page-container-bg-solid">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<span style="">我的任务</span>
			</div>
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown dropdown-user">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        <i class="icon-user"></i> 
                        <span id="primaryName" class="username username-hide-on-mobile" style="color: white;"> ${sessionScope.user.account } </span>
                        <i class="fa fa-angle-down" style="color: white;"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-default">
                        <li>
                            <a href="#" id="changePassword">
                                <i class="fa fa-check-square-o"></i>&nbsp&nbsp 修改密码 </a>
                        </li>
                        <li>
                            <a href="#" id="logout">
                                <i class="icon-key"></i>&nbsp&nbsp 退出 </a>
                        </li>
                    </ul>
                </li>
            </ul>
		</div>
		<div class="portlet-body">
			<div class="table-responsive">
				<table class="table" id="t1">
					<tbody>
						<tr>
							<td>飞机尾号：</td>
							<td style="width: 920px;" id="planenumber">B-2231</td>
						</tr>
						<tr>
							<td>执行日期：</td>
							<td id="entrydate"><fmt:formatDate type="date"
									value="${sessionScope.today}" /></td>
						</tr>
						<tr>
							<td>执行人：</td>
							<td id="username">${sessionScope.user.name }</td>
						</tr>
					</tbody>
				</table>
				<button type="button" id="loadinfo" class="btn btn-info">过滤</button>
			</div>
		</div>
	</div>
	<div class="portlet box blue" style="display: none;" id="info">
		<div class="portlet-title">
			<div class="caption">工卡任务清单</div>
		</div>
		<div class="portlet-body">
			<div class="table-responsive">
				<table class="table" id="t2">
					<tbody id="tb">
						<tr>
							<th>操作</th>
							<th>编号</th>
							<th>版本</th>
							<th>章节</th>
							<th>维修类别</th>
							<th>工卡执行状态</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/ems/ui/js/task.js"></script>
	<script type="text/javascript" src="/ems/ui/js/jquery.blockui.min.js"></script>
	<script type="text/javascript" src="/ems/ui/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/ems/ui/plugins/jquery-confirm/js/jquery-confirm.js"></script>
</body>
</html>
