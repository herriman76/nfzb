package com.wonders.fzb.report.dao.impl;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.report.beans.*;
import com.wonders.fzb.report.dao.*;

/**
 * LegislationReport dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationReportDao")
public class LegislationReportDaoImpl extends BaseSupportDao implements LegislationReportDao {

	@Override
	public void save(Object object) {
		LegislationReport info = (LegislationReport) object;
		info.setStReportId(super.getId("SEQ_LEGISLATION_REPORT", "RPT_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationReport info = (LegislationReport) object;
		String newId=super.getId("SEQ_LEGISLATION_REPORT", "RPT_", 16);
		info.setStReportId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationReport");
	}

	@Override
	public List<LegislationReport> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationReport");
	}

	@Override
	public void saveOrUpdate(LegislationReport info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationReport> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationReport> result = query.list();
		session.flush();
		return result;
	}

}