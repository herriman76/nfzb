package com.wonders.fzb.plan.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.plan.beans.LegislationPlanDeal;
import com.wonders.fzb.plan.beans.LegislationPlanItem;
import com.wonders.fzb.plan.beans.LegislationPlanTask;
import com.wonders.fzb.plan.dao.LegislationPlanItemDao;
import com.wonders.fzb.plan.services.LegislationPlanDealService;
import com.wonders.fzb.plan.services.LegislationPlanItemService;
import com.wonders.fzb.plan.services.LegislationPlanTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * LegislationPlanItem service实现
 * 
 * @author scalffold created by lj
 */
 
@Service("legislationPlanItemService")
@Transactional
public class LegislationPlanItemServiceImpl implements LegislationPlanItemService {

	@Autowired
	private LegislationPlanItemDao legislationPlanItemDao;

	@Autowired
	private LegislationFilesService legislationFilesService;

	@Autowired
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	private LegislationPlanTaskService legislationPlanTaskService;

	@Autowired
	private LegislationPlanDealService legislationPlanDealService;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(LegislationPlanItem info) {
		legislationPlanItemDao.save(info);
	}

	/**
	 * 添加实体对象
	 */
	@Override
	public String addObj(LegislationPlanItem info) {
		return legislationPlanItemDao.saveObj(info);
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(LegislationPlanItem info) {
		legislationPlanItemDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(LegislationPlanItem info) {
		legislationPlanItemDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public LegislationPlanItem findById(String id) {
		return (LegislationPlanItem) legislationPlanItemDao.load(id);
	}

	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 * @throws FzbDaoException
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return legislationPlanItemDao.findByPage(condMap, sortMap, pageNo, pageSize);
	}

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap
	 *            过滤条件<propertyName,properyValue>
	 * @param sortMap
	 *            排序条件<propertyName,properyValue>
	 * @return
	 */
	@Override
	public List<LegislationPlanItem> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return legislationPlanItemDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(LegislationPlanItem info) {
		legislationPlanItemDao.saveOrUpdate(info);
	}


	@Override
	public List<LegislationPlanItem> findByHQL(String hql) {
		List<LegislationPlanItem> legislationPlanItemList = legislationPlanItemDao.findByHQL(hql);
		return legislationPlanItemList;
	}

	@Override
	public void saveLegislationPlan(HttpServletRequest request, HttpSession session) {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		String teamId=currentPerson.getTeamInfos().get(0).getId();
		String teamName=currentPerson.getTeamInfos().get(0).getTeamName();
		String userId=currentPerson.getUserId();
		String userName=currentPerson.getName();
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		String stTaskId=request.getParameter("stTaskId");
		String stPlanId=request.getParameter("stPlanId");
		String stItemName=request.getParameter("stItemName");
		String stTypeId=request.getParameter("stTypeId");
		String stTypeName=request.getParameter("stTypeName");
		String stContent=request.getParameter("stContent");
		String stStatus=request.getParameter("stStatus");
		String stBak=request.getParameter("stBak");
		String stNodeId=request.getParameter("stNodeId");
		String stItemId;
		if(StringUtils.isEmpty(stTaskId)){
			WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
			//添加立法计划
			LegislationPlanItem legislationPlanItem=new LegislationPlanItem();
			legislationPlanItem.setStPlanId(stPlanId);
			legislationPlanItem.setDtCreateDate(new Date());
			legislationPlanItem.setStBak(stBak);
			legislationPlanItem.setStContent(stContent);
			legislationPlanItem.setStItemName(stItemName);
			legislationPlanItem.setStStatus(stStatus);
			legislationPlanItem.setStTypeId(stTypeId);
			legislationPlanItem.setStTypeName(stTypeName);
			legislationPlanItem.setStUnitId(unitId);
			legislationPlanItem.setStUnitName(unitName);
			legislationPlanItem.setStUserId(userId);
			legislationPlanItem.setStUserName(userName);
			legislationPlanItem.setStNodeId(stNodeId);
			legislationPlanItem.setStNodeName(node.getStNodeName());
			stItemId=addObj(legislationPlanItem);

			//添加立法计划任务
			LegislationPlanTask legislationPlanTask=new LegislationPlanTask();
			legislationPlanTask.setStFlowId(stItemName);
			legislationPlanTask.setStPlanId(stPlanId);
			legislationPlanTask.setStParentId(stItemId);
			legislationPlanTask.setStRoleId(userRoleId);
			legislationPlanTask.setStRoleName(userRole);
			legislationPlanTask.setStNodeId(stNodeId);
			legislationPlanTask.setStNodeName(node.getStNodeName());
			legislationPlanTask.setStTaskStatus("TODO");
			legislationPlanTask.setDtOpenDate(new Date());
			legislationPlanTask.setStUserId(userId);
			legislationPlanTask.setStUserName(userName);
			legislationPlanTask.setStTeamId(teamId);
			legislationPlanTask.setStTeamName(teamName);
			legislationPlanTaskService.add(legislationPlanTask);

			if("NOD_0000000207".equals(stNodeId)){
				LegislationPlanDeal legislationPlanDeal=new LegislationPlanDeal();
				legislationPlanDeal.setStActionId(stNodeId);
				legislationPlanDeal.setStActionName(node.getStNodeName());
				legislationPlanDeal.setStUserId(userId);
				legislationPlanDeal.setStUserName(userName);
				legislationPlanDeal.setDtDealDate(new Date());
				legislationPlanDeal.setStPlanId(stItemId);
				legislationPlanDeal.setStBakOne(stItemName);
				legislationPlanDeal.setStBakTwo(stBak);
				legislationPlanDealService.add(legislationPlanDeal);
			}
		}else{
			//修改立法计划任务
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			legislationPlanTask.setStFlowId(stItemName);
			legislationPlanTask.setStPlanId(stPlanId);
			legislationPlanTaskService.update(legislationPlanTask);

			LegislationPlanItem legislationPlanItem=findById(legislationPlanTask.getStParentId());
			legislationPlanItem.setStPlanId(stPlanId);
			legislationPlanItem.setStBak(stBak);
			legislationPlanItem.setStContent(stContent);
			legislationPlanItem.setStItemName(stItemName);
			legislationPlanItem.setStStatus(stStatus);
			legislationPlanItem.setStTypeId(stTypeId);
			legislationPlanItem.setStTypeName(stTypeName);
			update(legislationPlanItem);
			stItemId=legislationPlanItem.getStItemId();

			if("NOD_0000000207".equals(stNodeId)){
				LegislationPlanDeal legislationPlanDeal=legislationPlanDealService.findByHQL("from LegislationPlanDeal t where 1=1 and t.stPlanId='"+stItemId+"' and t.stActionId = '"+stNodeId+"' ").get(0);
				legislationPlanDeal.setStUserId(userId);
				legislationPlanDeal.setStUserName(userName);
				legislationPlanDeal.setDtDealDate(new Date());
				legislationPlanDeal.setStPlanId(stItemId);
				legislationPlanDeal.setStBakOne(stItemName);
				legislationPlanDeal.setStBakTwo(stBak);
				legislationPlanDealService.update(legislationPlanDeal);
			}
		}

		// 处理附件内容
		legislationFilesService.updateParentIdById(request, stItemId);
	}

	@Override
	public List<Map<String, Object>> queryProjectByPlanId(String stPlanId) {
		List<Map<String, Object>> result=new ArrayList<>();
		List<LegislationPlanItem> legislationPlanItemList=findByHQL("from LegislationPlanItem t where 1=1 and t.stPlanId='"+stPlanId+"' and t.stIsDelete is null");
		legislationPlanItemList.forEach((LegislationPlanItem legislationPlanItem)->{
			Map<String, Object> map=new HashMap<>();
			map.put("stItemId",legislationPlanItem.getStItemId());
			map.put("stItemName",legislationPlanItem.getStItemName());
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findByHQL("from LegislationPlanTask t where 1=1 and t.stParentId='"+legislationPlanItem.getStItemId()+"' and t.stNodeId='"+legislationPlanItem.getStNodeId()+"' and t.stEnable is null").get(0);
			String stStatus=legislationPlanTask.getStNodeName();
			if("TODO".equals(legislationPlanTask.getStTaskStatus())){
				stStatus=stStatus+"(待处理)";
			}else{
				stStatus=stStatus+"(已处理)";
			}
			map.put("stStatus",stStatus);
			map.put("stUserName",legislationPlanItem.getStUserName());
			map.put("dtCreateDate",legislationPlanItem.getDtCreateDate());
			result.add(map);
		});
		return result;
	}
	
	@Override
	public JSONObject saveLegislationProjectAscription(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject=new JSONObject();
		String stItemId=request.getParameter("stItemId");
		String stSuggest=request.getParameter("stSuggest");
		LegislationPlanItem legislationPlanItem=findById(stItemId);
		legislationPlanItem.setStSuggest(stSuggest);
		update(legislationPlanItem);
		jsonObject.put("success",true);
		jsonObject.put("stSuggest",stSuggest);
		jsonObject.put("stItemId",stItemId);
		return jsonObject;
	}
}
