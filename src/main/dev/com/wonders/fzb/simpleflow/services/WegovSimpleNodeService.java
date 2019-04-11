package com.wonders.fzb.simpleflow.services;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WegovSimpleNode service接口
 * 
 * @author scalffold created by lj
 */
public interface WegovSimpleNodeService {
	/**
	 * 添加或修改实体对象
	 */
	public void saveOrUpdate(WegovSimpleNode info);

	/**
	 * 添加实体对象
	 */
	public void add(WegovSimpleNode info);

	/**
	 * 添加实体对象并返回主键ID
	 */
	public String addObj(WegovSimpleNode info);

	/**
	 * 更新实体对象
	 */
	public void update(WegovSimpleNode info);

	/**
	 * 删除实体对象
	 */
	public void delete(WegovSimpleNode info);

	/**
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 */
	public WegovSimpleNode findById(String id);

	/**
	 * 根据Map中过滤条件、排序条件和分页参数进行分页查询.
	 * 
	 * @param condMap  过滤条件<propertyName,properyValue>
	 * @param sortMap  排序条件<propertyName,properyValue>
	 * @param pageNo   当前页码
	 * @param pageSize 每页显示记录数.
	 * @return
	 */
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException;

	/**
	 * 根据Map中过滤条件、排序条件进行查询.
	 * 
	 * @param condMap 过滤条件<propertyName,properyValue>
	 * @param sortMap 排序条件<propertyName,properyValue>
	 * @return
	 */
	public List<WegovSimpleNode> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	/**
	 * 根据HQL进行查询.
	 */
	public List<WegovSimpleNode> findByHQL(String hql);

	/****************************************************************************************************/

	/**
	 * 查询列表页tab信息
	 * 
	 * @param stNodeId
	 * @return
	 */
	List<Object> queryButtonInfo(String stNodeId);

	/**
	 * 根据nodeId 获得对象
	 * @param NodeId
	 * @return
	 */
	public WegovSimpleNode findByNodeId(String NodeId);

	
	
	/**
	 * 根据当前taskid的stNodeId编号 查询code表 获得下个节点状态 TODO#DOING#DONE 例如 如果是当前task 是DOING 那返回值就是DONE
	 * 如果是TODO 返回值是DOING sy
	 * 
	 * @param stTaskId
	 * @param stDoneName
	 * @return
	 */
	public HashMap<String, String> getDoneNameNext(String stNodeId, String stDoneName);
}
