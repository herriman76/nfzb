package com.wonders.fzb.citymeeting.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.*;
import com.wonders.fzb.citymeeting.services.*;

/**
 * LegislationCitymeetingTaskd action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationCitymeetingTaskd")
@Controller
@Scope("prototype")
public class LegislationCitymeetingTaskdAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCitymeetingTaskdService")
	private LegislationCitymeetingTaskdService legislationCitymeetingTaskdService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationCitymeetingTaskd的修改
	@Action(value = "legislationCitymeetingTaskd_add", results = {@Result(name = SUCCESS, location = "/LegislationCitymeetingTaskd.jsp"), @Result(name = "List", location = "/legislationCitymeetingTaskd_list.jsp")})
	public String legislationCitymeetingTaskd_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCitymeetingTaskd> legislationCitymeetingTaskdList = new ArrayList<LegislationCitymeetingTaskd>();
		LegislationCitymeetingTaskd legislationCitymeetingTaskd = new LegislationCitymeetingTaskd();
		legislationCitymeetingTaskdService.add(legislationCitymeetingTaskd);
		return SUCCESS;
	}

}
