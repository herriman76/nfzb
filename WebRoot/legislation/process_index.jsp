<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >单位意见征求 > </span>
		</li>
		<li>
			<span>填写意见</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table">
		<tbody>
		<tr>
			<td>
				<label id="unitEdit" class="${unitEditClass}" <c:if test="${unitEditDisabled}">disabled="disabled" </c:if> onclick="openDemonstrationPage(this.id,'openUnitDemonstrationPage','${stDocId}','NOD_0000000120')">部门征求意见编辑</label>
			</td>
			<%--<td>--%>
				<%--<label id="unitSeal" class="btn btn-default btn-rounded process-btn" disabled="disabled">部门征求意见盖章</label>--%>
			<%--</td>--%>
			<td>
				<label id="unitSend" class="${unitSendClass}" <c:if test="${unitSendDisabled}">disabled="disabled" </c:if> onclick="openDemonstrationPage(this.id,'openUnitSeekPage','${stDocId}','NOD_0000000121')">部门征求意见发送部门</label>
			</td>
			<td>
				<label id="unitReceive" class="${unitReceiveClass}" <c:if test="${unitReceiveDisabled}">disabled="disabled" </c:if> onclick="openDemonstrationPage(this.id,'openUnitReceivePage','${stDocId}','NOD_0000000120')">部门征求意见接受反馈</label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="btn btn-default process-btn">网上征求意见发起</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">网上征求意见接收</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">网上征求意见送审</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">网上征求意见上网发布</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">网上意见汇总</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">采纳意见发布</label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="btn btn-default process-btn">专家论证会发起</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">专家论证会结果归档</label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="btn btn-default process-btn">立法听证会发起</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">立法听证会接收</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">立法听证会送审</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">立法听证会上网发布</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">立法听证会网上报名</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">立法听证会结果归档</label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="btn btn-default process-btn">部门会签编辑</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">部门会签盖章</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">部门会签发起发送部门</label>
			</td>
			<td>
				<label class="btn btn-default process-btn">部门会签结果接收反馈</label>
			</td>
		</tr>
		</tbody>
	</table>
</div>
<script>
    $(function () {
        $(".process-btn").css('width', '170px');
        $(".table>tbody>tr>td").css('border', '0px solid #e7eaec');
        $(".table>tbody>tr>td").css('padding-top', '30px');
    });
    function openDemonstrationPage(buttonId,method,stDocId,stNodeId) {
		if($('#'+buttonId).attr("disabled")==undefined){
            $("#processIndexForm").modal({
                remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?buttonId="+buttonId+"&method="+method+"&stDocId="+stDocId+"&stNodeId="+stNodeId
            });
		}
    };
    function uploadReport1(stDocId,stNodeId,buttonId) {
        $.post("../legislationProcessTask/uploadReport.do?stDocId="+stDocId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextProcess1(stDocId,stNodeId,"nextProcess",buttonId);
                }else{
                    Duang.error("提示", "请补全必填材料！");
                }
            },
            "json")
    };
    function nextProcess1(stDocId,stNodeId,method,buttonId) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stDocId="+stDocId+"&stNodeId="+stNodeId+"&method="+method+"&buttonId="+buttonId,function(data){
                if(data.success){
                    $('#processIndexForm').modal('hide');
                    $('#'+buttonId).attr("class","btn btn-primary process-btn");
                    if(data.removeDisabled){
						$('#'+buttonId).parent().next().children().attr("class","btn btn-warning process-btn");
                        $('#'+buttonId).parent().next().children().disabled=false;
                    }
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        });
    };
    function toUploadFile(obj) {
        $(obj).next().click();
    };
    function deleteAttach(attachObj,type,id,fileId,stSampleId) {
        $.post('${basePath}/file/deleteAttach.do?fileId='+fileId);
        var obj=$(attachObj);
        if(type==1){
            obj.parent().prev().html('<span style="color: red">暂未上传</span>');
            var html= '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>'
                +'<input id="'+id+'" name="upload" type="file" style="display:none"  onchange="uploadFile(\''+id+'\',1,\''+stSampleId+'\')">';
            obj.parent().html(html);
        }else{
            obj.parent().parent().remove();
        }
    }
</script>