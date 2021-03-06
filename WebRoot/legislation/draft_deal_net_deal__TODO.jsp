<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span>公开征求意见接收</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">公开征求意见接收</h2>
	<form id="onlineDemonstrationForm" class="form-horizontal" novalidate="novalidate">
		<input hidden="hidden" name="stTaskId" id="stTaskId" value="${legislationProcessTask.stTaskId}">
		<input type="hidden" name="stDocId" value="${legislationProcessDoc.stDocId}">
		<input type="hidden" id="nodeStatus" value="${nodeStatus}">
		<div class="form-body">
			<br>
			<div class="form-group text-center">
				<label class="col-sm-3 control-label text-left">法规规章草案：</label>
				<label class="col-sm-9 control-label" style="text-align: left;">
					<span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span>
				</label>
			</div>
			<div class="form-group">
			    <label class="col-sm-3 control-label text-left">送审领导:</label>
				<div class="col-sm-9">
					<textarea class="form-control" id="stPersons" name="stPersons" readonly ondblclick="openEditParticipants('局领导')">${legislationProcessTaskdetail.stPersonName}</textarea>
				</div>
				<input type="hidden" name="stPersonsId" id="stPersonsId" <c:if test="${legislationProcessTaskdetail.stPersonId!=null}">value="${legislationProcessTaskdetail.stPersonId}" </c:if> />
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">送审说明:</label>
				<div class="col-sm-9">
					<textarea id="stComment2" name="stComment2" class="form-control">${legislationProcessTaskdetail.stBak1}</textarea>
				</div>
			</div>
		</div>
		
		 <div class="form-group">
				<label class="control-label">公开征求意见接收材料 </label>
			</div>	
		<%@include file="/legislation/file/attachUpload.jsp" %>
		<div class="form-group text-center">
		 <c:if test="${legislationProcessTask.stTaskStatus=='TODO'}">
			<input ${stStyle} type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" value="保存" onclick="saveLegislationDemonstration()">
			&nbsp;&nbsp;
			<input ${stStyle} type="button" class="btn btn-w-m btn-success" onclick="confirmOnlineReport('${stDocId}','${nodeId}','${nodeStatus}')" value="上报">
			&nbsp;&nbsp;
			 </c:if>
			<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
		</div>
	</form>
</div>
</div>
<script>
	function uploadFile(id, type, stSampleId) {
		$.ajaxFileUpload({
			url : '${basePath}/file/upload.do?nodeStatus=${nodeStatus}&stNodeId=${nodeId}&stSampleId=' + stSampleId,
			type : 'post',
			secureuri : false, //是否启用安全提交,默认为false
			fileElementId : id,
			dataType : 'JSON',
			success : function(data, status) { //服务器响应成功时的处理函数
				data = data.replace(/<.*?>/ig, ""); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
				var file = JSON.parse(data);
				if (file.success) {
					if (type == 1) {
						var html = '<a target="_blank" href="${basePath}/file/downloadAttach.do?name=' + file.name + '&url=' + file.url + '">下载</a>&nbsp;&nbsp;' + '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>' + '<label  style="color: red" onclick="deleteAttach(this,1,\'' + id + '\',\'' + file.fileId + '\',\'' + stSampleId + '\')" >删除</label>';
						$("#" + id).parent().prev().html('<span>' + file.name + '</span>');
						$("#" + id).parent().html(html);
					} else {
						var html = '<tr class="text-center">' + '<td class="text-left">需要报送的其他材料</td>' + '<td>' + file.name + '</td>' + '<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=' + file.name + '&url=' + file.url + '">下载</a>&nbsp;&nbsp;' + '<label  style="color: red" onclick="deleteAttach(this,2,\'' + id + '\',\'' + file.fileId + '\',\'' + stSampleId
								+ '\')">删除</label>' + '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>' + '</td></tr>';
						$('#otherMaterial').append(html);
					}
					Duang.success("提示", "上传材料成功");
				} else {
					Duang.error("提示", "上传材料失败");
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				Duang.error("提示", "上传材料失败");
			}
		});
	};
	function saveLegislationDemonstration() {
		var param = $('#onlineDemonstrationForm').formToJson();
		if (param.stComment2 == null || param.stComment2 == "") {
			Duang.error("提示", "请输入送审说明");
		} else if (param.stPersonsId == null || param.stPersonsId == "") {
			Duang.error("提示", "请输入送审领导");
		} else{
			$.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration", param, function(data) {
				if (data.success) {
					$('#processIndexForm').modal('hide');
					//var buttonId=$('#buttonId').val();
					$('#${nodeId}__${nodeStatus}').parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green').addClass('bcg_green');
					Duang.success("提示", "操作成功");
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		}
	};
	function confirmOnlineReport(stDocId, nodeId, nodeStatus) {
		var param = $('#onlineDemonstrationForm').formToJson();
		if (param.stComment2 == null || param.stComment2 == "") {
			Duang.error("提示", "请输入送审说明");
		} else {
			$.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration", param, function(data) {
				//alert("保存返回：" + JSON.stringify(data));
				if (data.success) {
					uploadChildDemonstrationReport(stDocId, nodeId, nodeStatus);
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		}
	}
	function openEditParticipants(showName) {
	    var stPersonsId=$('#stPersonsId').val();
	    var otherPersonsName=$('#otherPersonsName').val();
        $("#processIndexChildForm").modal({
            remote : "${basePath}/legislationSendNotice/openEditParticipants.do?showName="+showName+"&stPersonsId="+stPersonsId+"&otherPersons=N&module=process"
        });
 }
	
</script>