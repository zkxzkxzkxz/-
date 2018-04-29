/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.oaec.crm.common.persistence.DataEntity;

/**
 * 客户资料跟踪管理Entity
 * @author kaven.wei
 * @version 2017-06-11
 */
public class CrmCustomfollow extends DataEntity<CrmCustomfollow> {
	
	private static final long serialVersionUID = 1L;
	private Long customid;		// 客户ID
	private Long userid;		// 用户ID
	private String followstate;		// 跟踪状态
	private Date startdate;		// 分配日期
	private Date plandate;		// 计划联系日期
	private Date lastdate;		// 最近联系日期
	private String customtype;		// 客户类型
	
	public CrmCustomfollow() {
		super();
	}

	public CrmCustomfollow(String id){
		super(id);
	}

	public Long getCustomid() {
		return customid;
	}

	public void setCustomid(Long customid) {
		this.customid = customid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=4, message="跟踪状态长度必须介于 0 和 4 之间")
	public String getFollowstate() {
		return followstate;
	}

	public void setFollowstate(String followstate) {
		this.followstate = followstate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlandate() {
		return plandate;
	}

	public void setPlandate(Date plandate) {
		this.plandate = plandate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	
	@Length(min=0, max=4, message="客户类型长度必须介于 0 和 4 之间")
	public String getCustomtype() {
		return customtype;
	}

	public void setCustomtype(String customtype) {
		this.customtype = customtype;
	}
	
}