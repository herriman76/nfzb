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
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
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
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	
	@Action(value = "draft_doc_info", results = {@Result(name = "openAddPage", location = "/legislation/legislationProcessManager_add.jsp"),
			@Result(name = "openEditPage", location = "/legislation/legislationProcessManager_add.jsp"),
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
		List<Map> legislationExampleFilesList=new ArrayList<>();
		legislationExampleList.forEach((LegislationExample legislationExample)->{
			Map map=new HashMap();
			map.put("stExampleId",legislationExample.getStExampleId());
			map.put("stExampleName",legislationExample.getStExampleName());
			map.put("stNeed",legislationExample.getStNeed());
			map.put("fileId",null);
			map.put("fileName",null);
			map.put("fileUrl",null);
			legislationExampleFilesList.add(map);
		});
		request.setAttribute("LegislationExampleList",legislationExampleFilesList);
		return pageController();
	}
	private String openEditPage(){
        String stNodeId = request.getParameter("stNodeId");
        Map<String, Object> condMap = new HashMap<>();
        Map<String, String> sortMap = new HashMap<>();
        condMap.put("stNode", stNodeId);
        sortMap.put("stExampleId", "ASC");
        List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
        String stDocId=request.getParameter("stDocId");
        condMap.clear();
        condMap.put("stParentId",stDocId);
        sortMap.clear();
        sortMap.put("dtPubDate","ASC");
        List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
        List<Map> legislationExampleFilesList=new ArrayList<>();
        legislationExampleList.forEach((LegislationExample legislationExample)->{
            Map map=new HashMap();
            map.put("stExampleId",legislationExample.getStExampleId());
            map.put("stExampleName",legislationExample.getStExampleName());
            map.put("stNeed",legislationExample.getStNeed());
            legislationFilesList.forEach((LegislationFiles legislationFiles)->{
                if(null!=legislationFiles.getStSampleId()&&
                        legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
                    map.put("fileId",legislationFiles.getStFileId());
                    map.put("fileName",legislationFiles.getStTitle());
                    map.put("fileUrl",legislationFiles.getStFileUrl());
                }
            });
            legislationExampleFilesList.add(map);
        });
        LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
        request.setAttribute("LegislationExampleList",legislationExampleFilesList);
        request.setAttribute("legislationFilesList",legislationFilesList);
        request.setAttribute("legislationProcessDoc",legislationProcessDoc);
		return pageController();
	}
	private String openInfoPage(){
		return pageController();
	}
	private String openDraftHistoryPage(){
		return pageController();
	}
	private String openSeparatePage(){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='" + legislationProcessDoc.getStNodeId() + "'");
		for (LegislationProcessTask legislationProcessTask : list) {
			request.setAttribute("legislationProcessTask", legislationProcessTask);
		}
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
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

		if(StringUtil.isEmpty(docId) || docId.equals("null")){
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
		}else {
            LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(docId);
            legislationProcessDoc.setStDocName(docName);
            legislationProcessDoc.setStComent(stComment);
            legislationProcessDocService.update(legislationProcessDoc);
            Enumeration keys=request.getParameterNames();
            while(keys.hasMoreElements()){
                String key=(String)keys.nextElement();
                String value=request.getParameter(key);
                if(value.startsWith("FIL_")){
                    legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+docId+"' where s.stFileId='"+value+"'");
                }
            }
        }
//		else if("edit".equals(action)){
//			legislationProcessDoc.setStDocId(docId);
//			legislationProcessDoc.setStDocName(docName);
//			legislationProcessDoc.setStComent(stComent);
//			legislationProcessDocService.update(legislationProcessDoc);
//		}
//		else if("info".equals(action)){
//			legislationProcessDoc=legislationProcessDocService.findById(docId);
//			request.setAttribute("docInfo", legislationProcessDoc);
//		}
		return null;
	}

	/**
	 * 提交分办
	 * @return
	 * @throws Exception
	 */
	 @Action(value = "draft_dist_info", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")})
	public String draft_dist_info() throws Exception {
		 String action=request.getParameter("action");
		 String stDocId=request.getParameter("stDocId");
		 String stNodeId=request.getParameter("stNodeId");
		 String stComment1=request.getParameter("stComment1");//分办意见
		 String transactDate=request.getParameter("transactDate");//办理时限
		 
		 LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		 
		//修改当前task状态，并生成下一个节点的task
		 List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='" + legislationProcessDoc.getStNodeId() + "'");
	        for (LegislationProcessTask legislationProcessTask : list) {//修改当前task状态
	        	SimpleDateFormat formatter = new SimpleDateFormat( "yyyy年MM月dd日");
	        	legislationProcessTask.setStComment1(stComment1);
	        	if(StringUtils.hasText(transactDate)&&transactDate.length()>3) {
	        		try {
	        			legislationProcessTask.setDtDeadDate(formatter.parse(transactDate));
					} catch (Exception e) {
						e.printStackTrace();
					}
	        	}
	        	if(action.equals("submit")) {//党委提交的时候才改变状态并生成新的task
	        		legislationProcessTask.setDtCloseDate(new Date());
		        	legislationProcessTask.setStTaskStatus("DONE");
		        	legislationProcessTaskService.update(legislationProcessTask);
		            
		            //新增一个Task
		            LegislationProcessTask nextLegislationProcessTask = new LegislationProcessTask();
		            nextLegislationProcessTask.setStDocId(legislationProcessTask.getStDocId());
		            nextLegislationProcessTask.setStFlowId(legislationProcessTask.getStFlowId());
		            List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + legislationProcessTask.getStNodeId()+ "'");
		            nextLegislationProcessTask.setStNodeId(nodeList.get(0).getStNextNode());
		            nextLegislationProcessTask.setStNodeName(wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + nodeList.get(0).getStNextNode() + "'").get(0).getStNodeName());
		            nextLegislationProcessTask.setStTaskStatus("TODO");
		            nextLegislationProcessTask.setDtOpenDate(new Date());
		            
		            legislationProcessTaskService.add(nextLegislationProcessTask);
		            legislationProcessDocService.executeSqlUpdate("update LegislationProcessDoc s set s.stNodeId='" + nextLegislationProcessTask.getStNodeId() + "',s.stNodeName='" + nextLegislationProcessTask.getStNodeName() + "' where s.stDocId='" + nextLegislationProcessTask.getStDocId() + "'");
		      
	        	}else {//保存的话，就保存修改
	        		legislationProcessTaskService.update(legislationProcessTask);
	        	}
	        	
	        }
		 
		 return SUCCESS;
	 }
}
