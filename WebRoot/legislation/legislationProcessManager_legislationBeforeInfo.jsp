<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<c:choose>
				<c:when test="${legislationProcessTask.stNodeId=='NOD_0000000150'}">
					<span >专家论证会 > </span>
				</c:when>
				<c:otherwise>
					<span >立法听证会 > </span>
				</c:otherwise>
			</c:choose>
		</li>
		<li>
			<span>查看会前信息</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered">
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会议题:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stBakOne}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">对应草案:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stFlowId}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会地点:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stBakTwo}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会时间:</label>
			</td>
			<td class="text-center">
			<label ><fmt:formatDate type="date" value="${legislationProcessTask.dtBakDate}" /></label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会人员:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stComment2}</label>
			</td>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${legislationProcessTask.stNodeId=='NOD_0000000150'}">
					<td class="text-right tab-left">
						<label style="white-space: nowrap">专家论证会前相关材料:</label>
					</td>
				</c:when>
				<c:otherwise>
					<td class="text-right tab-left">
						<label style="white-space: nowrap">立法听证会前相关材料:</label>
					</td>
				</c:otherwise>
			</c:choose>

			<td class="text-center">
				<s:iterator value="#request.docList" var="doc">
					<label class="control-label col-md-12 text-center"><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${doc.stTitle}&url=${doc.stFileUrl}">${doc.stTitle}</a></label>
				</s:iterator>
			</td>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${legislationProcessTask.stNodeId=='NOD_0000000150'}">
					<td class="text-right tab-left">
						<label style="white-space: nowrap">专家论证会前其他材料:</label>
					</td>
				</c:when>
				<c:otherwise>
					<td class="text-right tab-left">
						<label style="white-space: nowrap">立法听证会前其他材料:</label>
					</td>
				</c:otherwise>
			</c:choose>

			<td class="text-center">
				<s:iterator value="#request.otherDocList" var="doc">
					<label class="control-label col-md-12 text-center"><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${doc.stTitle}&url=${doc.stFileUrl}">${doc.stTitle}</a></label>
				</s:iterator>
			</td>
		</tr>
	</table>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    })
</script>