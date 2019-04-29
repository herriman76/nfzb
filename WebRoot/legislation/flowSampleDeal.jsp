<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">

</head>

<body>
			<div class="ibox-tools">
				<input type="button"
					class="btn btn-w-m btn-white" id="btnAdd" name="btnAdd" value="添加">
			</div>
	<div class="ibox-content">
	<div class="ibox-content" id="divAdd">

	</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>样本名称</th>
					<th>是否必须</th>
					<th>操  作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.legislationExampleFilesList.result" var="legislationExample">
					<tr>
						<td><s:property value="#legislationExample.stExampleId" /></td>
						<td><s:property value="#legislationExample.stNeed" /></td>
						<td><s:property value="#legislationExample.stExampleName" /></td>
						<td>处理</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>

	<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${basePath}/legislation/assets/js/plugins/toastr/toastr.min.js"></script>
	
	
	<script type="text/javascript">
	$(function () {
		if("${nodeId}"==101){
			$('#divAdd').show();
		}else{
			$('#divAdd').hide();
		}
	});
	
	//添加草案
    $('#btnAdd').click(function () {
    	var stNodeId='${request.nodeInfo.stNodeId}';
    	window.open ('${basePath}/legislationProcessDoc/draft_create_info.do?action=add&stNodeId='+stNodeId, 'newwindow', 'height=400, width=1080, top=200, left=300, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no') ;
    	
    });
	
		toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"progressBar" : false,
			"positionClass" : "toast-top-center",
			"onclick" : null,
			"showDuration" : "400",
			"hideDuration" : "1000",
			"timeOut" : "7000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}
	</script>
	
</body>

</html>