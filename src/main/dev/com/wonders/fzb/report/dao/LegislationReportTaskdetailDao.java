package com.wonders.fzb.report.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.report.beans.LegislationReportTaskdetail;


/**
 * LegislationReportTaskdetail dao接口
 * @author scalffold created by lj
 */
public abstract interface LegislationReportTaskdetailDao extends BaseDao {
	
	public void save(Object object);
	
	public String saveObj(Object object);
	
	public void saveOrUpdate(LegislationReportTaskdetail info);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<LegislationReportTaskdetail> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<LegislationReportTaskdetail> findByHQL(String hql);
}
