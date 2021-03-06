package com.wonders.fzb.legislate.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.TeamOpinion;


public interface TeamOpinionDao  extends BaseDao{
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize)  throws FzbDaoException;
	
	public List<TeamOpinion> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public void saveOrUpdate(TeamOpinion info);
}
