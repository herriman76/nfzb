package com.wonders.fzb.legislate.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislate.beans.SendFeedback;
import com.wonders.fzb.legislate.dao.SendFeedbackDao;
import com.wonders.fzb.legislate.services.SendFeedbackService;

/**
 * SendFeedback service实现
 * 
 * @author scalffold created by lj
 */
@Service(value="sendFeedbackService")
@Transactional
public class SendFeedbackServiceImpl implements SendFeedbackService{
	@Autowired
	SendFeedbackDao sendFeedbackDao;

	/**
	 * 添加实体对象
	 */
	@Override
	public void add(SendFeedback info) {
		sendFeedbackDao.save(info);
	}
	

	/**
	 * 更新实体对象
	 */
	@Override
	public void update(SendFeedback info) {
		sendFeedbackDao.update(info);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void delete(SendFeedback info) {
		sendFeedbackDao.delete(info);
	}

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	@Override
	public SendFeedback findById(String id) {
		return (SendFeedback) sendFeedbackDao.load(id);
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
	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return sendFeedbackDao.findByPage(condMap, sortMap, pageNo, pageSize);
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
	public List<SendFeedback> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return sendFeedbackDao.findByList(condMap, sortMap);
	}

	@Override
	public void saveOrUpdate(SendFeedback info) {
		sendFeedbackDao.saveOrUpdate(info);
	}
}
