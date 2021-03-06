package com.wonders.fzb.plan.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * LEGISLATION_PLAN_TASK Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_PLAN_TASK")
public class LegislationPlanTask implements Serializable {

  public static final String LegislationPlanTask = "LEGISLATION_PLAN_TASK";
	
  public LegislationPlanTask()
  {
  }

	/**
	 * ST_ACTIVE
	 */
	@Column(name = "ST_ACTIVE")
	private String stActive;

	/**
	 * ST_ACTIVE
	 */
	public String getStActive(){
		return stActive;
	}

	/**
	 * ST_ACTIVE
	 */
	public void setStActive (String stActive){
		this.stActive = stActive;
	}

	/**
	 * ST_DEAL_ID
	 */
	@Column(name = "ST_DEAL_ID")
	private String stDealId;

	/**
	 * ST_DEAL_ID
	 */
	public String getStDealId(){
		return stDealId;
	}

	/**
	 * ST_DEAL_ID
	 */
	public void setStDealId (String stDealId){
		this.stDealId = stDealId;
	}

	/**
	 * ST_DEAL_NAME
	 */
	@Column(name = "ST_DEAL_NAME")
	private String stDealName;

	/**
	 * ST_DEAL_NAME
	 */
	public String getStDealName(){
		return stDealName;
	}

	/**
	 * ST_DEAL_NAME
	 */
	public void setStDealName (String stDealName){
		this.stDealName = stDealName;
	}

	/**
	 * ST_TASK_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_TASK_ID")
	private String stTaskId;

	/**
	 * ST_TASK_ID
	 */
	public String getStTaskId(){
		return stTaskId;
	}

	/**
	 * ST_TASK_ID
	 */
	public void setStTaskId (String stTaskId){
		this.stTaskId = stTaskId;
	}

	/**
	 * DT_DEAL_DATE
	 */
	@Column(name = "DT_DEAL_DATE")
	private Date dtDealDate;

	/**
	 * DT_DEAL_DATE
	 */
	public Date getDtDealDate(){
		return dtDealDate;
	}

	/**
	 * DT_DEAL_DATE
	 */
	public void setDtDealDate (Date dtDealDate){
		this.dtDealDate = dtDealDate;
	}

	/**
	 * DT_DEAD_DATE
	 */
	@Column(name = "DT_DEAD_DATE")
	private Date dtDeadDate;

	/**
	 * DT_DEAD_DATE
	 */
	public Date getDtDeadDate(){
		return dtDeadDate;
	}

	/**
	 * DT_DEAD_DATE
	 */
	public void setDtDeadDate (Date dtDeadDate){
		this.dtDeadDate = dtDeadDate;
	}

	/**
	 * ST_NODE_NAME
	 */
	@Column(name = "ST_NODE_NAME")
	private String stNodeName;

	/**
	 * ST_NODE_NAME
	 */
	public String getStNodeName(){
		return stNodeName;
	}

	/**
	 * ST_NODE_NAME
	 */
	public void setStNodeName (String stNodeName){
		this.stNodeName = stNodeName;
	}

	/**
	 * ST_TEAM_ID
	 */
	@Column(name = "ST_TEAM_ID")
	private String stTeamId;

	/**
	 * ST_TEAM_ID
	 */
	public String getStTeamId(){
		return stTeamId;
	}

	/**
	 * ST_TEAM_ID
	 */
	public void setStTeamId (String stTeamId){
		this.stTeamId = stTeamId;
	}

	/**
	 * ST_USER_ID
	 */
	@Column(name = "ST_USER_ID")
	private String stUserId;

	/**
	 * ST_USER_ID
	 */
	public String getStUserId(){
		return stUserId;
	}

	/**
	 * ST_USER_ID
	 */
	public void setStUserId (String stUserId){
		this.stUserId = stUserId;
	}

	/**
	 * ST_ENABLE
	 */
	@Column(name = "ST_ENABLE")
	private String stEnable;

	/**
	 * ST_ENABLE
	 */
	public String getStEnable(){
		return stEnable;
	}

	/**
	 * ST_ENABLE
	 */
	public void setStEnable (String stEnable){
		this.stEnable = stEnable;
	}

	/**
	 * ST_TEAM_NAME
	 */
	@Column(name = "ST_TEAM_NAME")
	private String stTeamName;

	/**
	 * ST_TEAM_NAME
	 */
	public String getStTeamName(){
		return stTeamName;
	}

	/**
	 * ST_TEAM_NAME
	 */
	public void setStTeamName (String stTeamName){
		this.stTeamName = stTeamName;
	}

	/**
	 * DT_OPEN_DATE
	 */
	@Column(name = "DT_OPEN_DATE")
	private Date dtOpenDate;

	/**
	 * DT_OPEN_DATE
	 */
	public Date getDtOpenDate(){
		return dtOpenDate;
	}

	/**
	 * DT_OPEN_DATE
	 */
	public void setDtOpenDate (Date dtOpenDate){
		this.dtOpenDate = dtOpenDate;
	}

	/**
	 * ST_PARENT_ID
	 */
	@Column(name = "ST_PARENT_ID")
	private String stParentId;

	/**
	 * ST_PARENT_ID
	 */
	public String getStParentId(){
		return stParentId;
	}

	/**
	 * ST_PARENT_ID
	 */
	public void setStParentId (String stParentId){
		this.stParentId = stParentId;
	}

	/**
	 * ST_TASK_STATUS
	 */
	@Column(name = "ST_TASK_STATUS")
	private String stTaskStatus;

	/**
	 * ST_TASK_STATUS
	 */
	public String getStTaskStatus(){
		return stTaskStatus;
	}

	/**
	 * ST_TASK_STATUS
	 */
	public void setStTaskStatus (String stTaskStatus){
		this.stTaskStatus = stTaskStatus;
	}

	/**
	 * ST_COMMENT1
	 */
	@Column(name = "ST_COMMENT1")
	private String stComment1;

	/**
	 * ST_COMMENT1
	 */
	public String getStComment1(){
		return stComment1;
	}

	/**
	 * ST_COMMENT1
	 */
	public void setStComment1 (String stComment1){
		this.stComment1 = stComment1;
	}

	/**
	 * ST_TOPIC_ID
	 */
	@Column(name = "ST_TOPIC_ID")
	private String stTopicId;

	/**
	 * ST_TOPIC_ID
	 */
	public String getStTopicId(){
		return stTopicId;
	}

	/**
	 * ST_TOPIC_ID
	 */
	public void setStTopicId (String stTopicId){
		this.stTopicId = stTopicId;
	}

	/**
	 * ST_FLOW_ID
	 */
	@Column(name = "ST_FLOW_ID")
	private String stFlowId;

	/**
	 * ST_FLOW_ID
	 */
	public String getStFlowId(){
		return stFlowId;
	}

	/**
	 * ST_FLOW_ID
	 */
	public void setStFlowId (String stFlowId){
		this.stFlowId = stFlowId;
	}

	/**
	 * ST_ROLE_NAME
	 */
	@Column(name = "ST_ROLE_NAME")
	private String stRoleName;

	/**
	 * ST_ROLE_NAME
	 */
	public String getStRoleName(){
		return stRoleName;
	}

	/**
	 * ST_ROLE_NAME
	 */
	public void setStRoleName (String stRoleName){
		this.stRoleName = stRoleName;
	}

	/**
	 * ST_ROLE_ID
	 */
	@Column(name = "ST_ROLE_ID")
	private String stRoleId;

	/**
	 * ST_ROLE_ID
	 */
	public String getStRoleId(){
		return stRoleId;
	}

	/**
	 * ST_ROLE_ID
	 */
	public void setStRoleId (String stRoleId){
		this.stRoleId = stRoleId;
	}

	/**
	 * ST_USER_NAME
	 */
	@Column(name = "ST_USER_NAME")
	private String stUserName;

	/**
	 * ST_USER_NAME
	 */
	public String getStUserName(){
		return stUserName;
	}

	/**
	 * ST_USER_NAME
	 */
	public void setStUserName (String stUserName){
		this.stUserName = stUserName;
	}

	/**
	 * ST_PLAN_ID
	 */
	@Column(name = "ST_PLAN_ID")
	private String stPlanId;

	/**
	 * ST_PLAN_ID
	 */
	public String getStPlanId(){
		return stPlanId;
	}

	/**
	 * ST_PLAN_ID
	 */
	public void setStPlanId (String stPlanId){
		this.stPlanId = stPlanId;
	}

	/**
	 * ST_NODE_ID
	 */
	@Column(name = "ST_NODE_ID")
	private String stNodeId;

	/**
	 * ST_NODE_ID
	 */
	public String getStNodeId(){
		return stNodeId;
	}

	/**
	 * ST_NODE_ID
	 */
	public void setStNodeId (String stNodeId){
		this.stNodeId = stNodeId;
	}

	/**
	 * DT_CLOSE_DATE
	 */
	@Column(name = "DT_CLOSE_DATE")
	private Date dtCloseDate;

	/**
	 * DT_CLOSE_DATE
	 */
	public Date getDtCloseDate(){
		return dtCloseDate;
	}

	/**
	 * DT_CLOSE_DATE
	 */
	public void setDtCloseDate (Date dtCloseDate){
		this.dtCloseDate = dtCloseDate;
	}

}