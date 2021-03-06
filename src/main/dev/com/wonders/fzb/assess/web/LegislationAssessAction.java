package com.wonders.fzb.assess.web;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.assess.beans.LegislationAssess;
import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.assess.services.LegislationAssessItemService;
import com.wonders.fzb.assess.services.LegislationAssessService;
import com.wonders.fzb.assess.services.LegislationAssessTaskService;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LegislationAssess action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationAssess")
@Controller
@Scope("prototype")
public class LegislationAssessAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationAssessService")
	private LegislationAssessService legislationAssessService;

	@Autowired
	@Qualifier("legislationAssessTaskService")
	private LegislationAssessTaskService legislationAssessTaskService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	@Autowired
	@Qualifier("legislationAssessItemService")
	private LegislationAssessItemService legislationAssessItemService;

	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamInfoService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationAssess的修改
	@Action(value = "legislationAssess_add", results = {@Result(name = SUCCESS, location = "/LegislationAssess.jsp"), @Result(name = "List", location = "/legislationAssess_list.jsp")})
	public String legislationAssess_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationAssess> legislationAssessList = new ArrayList<LegislationAssess>();
		LegislationAssess legislationAssess = new LegislationAssess();
		legislationAssessService.add(legislationAssess);
		return SUCCESS;
	}

	@Action(value = "assess_plan_info", results = { @Result(name = "openAssessAddPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessEditPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessInfoPage", location = "/assess/legislationAssess_form.jsp"),
			@Result(name = "openAssessProjectInfoPage", location = "/assess/legislationAssess_projectInfo.jsp"),
			@Result(name = "openAssessDistributePage", location = "/assess/legislationAssess_distribute.jsp"),
			@Result(name = "openAssessFeedbackPage", location = "/assess/legislationAssess_feedback.jsp")
	})
	public String legislationAssess() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	/**
	 * 页面控制
	 *
	 * @return
	 */
	private String pageController() {
		String methodStr = request.getParameter("method");
		String stNodeId = request.getParameter("stNodeId");
		String stTaskId = request.getParameter("stTaskId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}

	/**
	 * 跳转评估规划发起页面
	 * @return
	 */
	private String openAssessAddPage(){
		request.setAttribute("legislationAssess",new LegislationAssess());
		request.setAttribute("legislationAssessTask",new LegislationAssessTask());
		return pageController();
	}

	/**
	 * 跳转评估规划编辑页面
	 * @return
	 */
	private String openAssessEditPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		LegislationAssess legislationAssess=legislationAssessService.findById(legislationAssessTask.getStParentId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationAssess.getStAssessId());
		condMap.put("stNodeId", "NOD_0000000251");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationAssess",legislationAssess);
		request.setAttribute("legislationAssessTask",legislationAssessTask);
		return pageController();
	}

	/**
	 * 跳转评估规划详情页面
	 * @return
	 */
	private String openAssessInfoPage(){
		return openAssessEditPage();
	}

	/**
	 * 保存评估规划
	 * @return
	 */
	private String saveLegislationAssess(){
		legislationAssessService.saveLegislationAssess(request,session);
		return null;
	}

	/**
	 * 跳转汇总项目详情列表页
	 * @return
	 */
	private String openAssessProjectInfoPage(){
		String stTaskId=request.getParameter("stTaskId");
		String stNodeId=request.getParameter("stNodeId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		List<Map<String, Object>> legislationAssessItemList=legislationAssessItemService.queryProjectByAssessId(legislationAssessTask.getStParentId(),stNodeId);
		request.setAttribute("legislationAssessItemList",legislationAssessItemList);
		return pageController();
	}

	/**
	 * 跳转评估项目汇总分送页
	 * @return
	 */
	private String openAssessDistributePage(){
		//查立法处
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("moduleId","MODULE_LEGISLATE");
		condMap.put("showNameLike","立法");
		sortMap.put("sort","ASC");
		List<MOR> morList=teamInfoService.findMorList(condMap,sortMap);
		request.setAttribute("morList",morList);
		return pageController();
	}

	/**
	 * 保存评估项目汇总分送
	 * @return
	 */
	private String saveLegislationAssessDistribute(){
		legislationAssessTaskService.nextAssessProcess(request,session);
		return null;
	}

	/**
	 * 跳转编辑/查看市政府反馈页面
	 * @return
	 */
	private String openAssessFeedbackPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		LegislationAssess legislationAssess=legislationAssessService.findById(legislationAssessTask.getStParentId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationAssess.getStAssessId());
		condMap.put("stNodeId", "NOD_0000000263");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationAssess",legislationAssess);
		request.setAttribute("legislationAssessTask",legislationAssessTask);
		return pageController();
	}

	/**
	 * 检查是否已编辑市政府反馈
	 * @throws IOException
	 */
	@Action(value = "checkAssessFeedback")
	public void checkAssessFeedback() throws IOException {
		JSONObject jsonObject = new JSONObject();
		String stTaskId = request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		Boolean success=true;
		String message="";
		if(StringUtils.isEmpty(legislationAssessTask.getStComment1())){
			success=false;
			message="请先编辑市政府反馈信息!";
		}
		jsonObject.put("message",message);
		jsonObject.put("success",success);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}
}
