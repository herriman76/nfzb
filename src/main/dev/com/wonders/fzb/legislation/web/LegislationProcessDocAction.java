package com.wonders.fzb.legislation.web;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationExample;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import dm.jdbc.util.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;


/**
 * LegislationProcessDoc action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationProcessDoc")
@Controller
@Scope("prototype")
public class LegislationProcessDocAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;

	
	
	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;
	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	
	@Action(value = "draft_doc_info", results = {@Result(name = "openAddPage", location = "/legislation/legislationProcessManager_add.jsp"),
			@Result(name = "openEditPage", location = "/legislation/legislationProcessManager_edit.jsp"),
			@Result(name = "openInfoPage", location = "/legislation/legislationProcessManager_info.jsp"),
			@Result(name = "openDraftHistoryPage",location = "/legislation/legislationProcessManager_draftHistory.jsp"),
			@Result(name = "openSeparatePage",location = "/legislation/legislationProcessManager_separate.jsp")})
	public String legislationProcessDoc_form() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}
	private String openAddPage(){
		String stNodeId = request.getParameter("stNodeId");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId);
		sortMap.put("stExampleId", "ASC");
		List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
		request.setAttribute("LegislationExampleList",legislationExampleList);
		return pageController();
	}
	private String openEditPage(){
		return pageController();
	}
	private String openInfoPage(){
		return pageController();
	}
	private String openDraftHistoryPage(){
		return pageController();
	}
	private String openSeparatePage(){
		return pageController();
	}

	/**
	 * 页面控制
	 * @return
	 */
	private String pageController(){
		String stDocId=request.getParameter("stDocId");

		String methodStr = request.getParameter("method");

		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("nodeId",stNodeId);
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}
	private String queryDocInfo(){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);

		List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stSampleId!=null order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stSampleId='null' order by t.stFileId ");
		for(LegislationFiles legislationFiles:otherDocList){
			docList.add(legislationFiles);
		}
		request.setAttribute("legislationProcessDoc",legislationProcessDoc);
		request.setAttribute("docList",docList);
		return "openInfoPage";
	}
	private String editLegislationProcessDoc() throws FzbDaoException {
		String docId = request.getParameter("docId");
		String docName = request.getParameter("docName");
		String stComment = request.getParameter("stComent");

		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();

		if(StringUtil.isEmpty(docId)){
			LegislationProcessDoc legislationProcessDoc = new LegislationProcessDoc();
			legislationProcessDoc.setStDocName(docName);
			legislationProcessDoc.setStUnitId(unitId);
			legislationProcessDoc.setStUnitName(unitName);
			legislationProcessDoc.setStUserId(userId);
			legislationProcessDoc.setStUserName(userName);
			legislationProcessDoc.setStComent(stComment);
			legislationProcessDoc.setStNodeId("NOD_0000000101");
			legislationProcessDoc.setStNodeName("草案起草");
			legislationProcessDoc.setDtCreateDate(new Date());

			String stDocId=legislationProcessDocService.addObj(legislationProcessDoc);
			legislationProcessDoc.setStDocNo(stDocId);
			legislationProcessDocService.update(legislationProcessDoc);

			LegislationProcessTask newTask= new LegislationProcessTask();
			newTask.setStDocId(stDocId);
			newTask.setStFlowId(docName);
			newTask.setStNodeId(legislationProcessDoc.getStNodeId());
			newTask.setStNodeName(legislationProcessDoc.getStNodeName());
			newTask.setStTaskStatus("TODO");
			newTask.setDtOpenDate(new Date());
			newTask.setStUserId(legislationProcessDoc.getStUserId());
			newTask.setStUserName(legislationProcessDoc.getStUserName());
			newTask.setStRoleId(session.getAttribute("userRoleId").toString());
			newTask.setStRoleName(session.getAttribute("userRole").toString());
			newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			legislationProcessTaskService.add(newTask);

			Enumeration keys=request.getParameterNames();
			while(keys.hasMoreElements()){
				String key=(String)keys.nextElement();
				String value=request.getParameter(key);
				if(value.startsWith("FIL_")){
					legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+stDocId+"' where s.stFileId='"+value+"'");
				}
			}
//		}
//		else if("edit".equals(action)){
//			legislationProcessDoc.setStDocId(docId);
//			legislationProcessDoc.setStDocName(docName);
//			legislationProcessDoc.setStComent(stComent);
//			legislationProcessDocService.update(legislationProcessDoc);
//		}
//		else if("info".equals(action)){
//			legislationProcessDoc=legislationProcessDocService.findById(docId);
//			request.setAttribute("docInfo", legislationProcessDoc);
		}
		return null;
	}

}
