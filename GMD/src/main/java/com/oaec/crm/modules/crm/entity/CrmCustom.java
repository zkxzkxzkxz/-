/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.oaec.crm.common.persistence.DataEntity;
import com.oaec.crm.common.utils.excel.annotation.ExcelField;

/**
 * 客户资料管理Entity
 * @author kaven.wei
 * @version 2017-06-12
 */
public class CrmCustom extends DataEntity<CrmCustom> {
	
	private static final long serialVersionUID = 1L;
	private String customname;		// 客户姓名
	private String education;		// 教育水平
	private String phone;		// 手机号
	private String qq;		// QQ号
	private String email;		// 电子邮件
	private String userid;		// 所属员工
	private String followstate;		// 跟踪状态
	private Date startdate;		// 分配日期
	private Date lastdate;		// 联系日期
	private String usertype;		// 客户类型
	//用于值传递，没有对应的数据库字段
	private String realname;  //所属用户的用户名
	private String followstatestr;		// 跟踪状态str
	private String usertypestr;		// 客户类型str
	
	public CrmCustom() {
		super();
	}

	public CrmCustom(String id){
		super(id);
	}

	@Length(min=0, max=30, message="客户姓名长度必须介于 0 和 30 之间")
	@ExcelField(title="客户姓名", align=2, sort=5)
	public String getCustomname() {
		return customname;
	}

	public void setCustomname(String customname) {
		this.customname = customname;
	}
	
	@Length(min=0, max=4, message="教育水平长度必须介于 0 和 4 之间")
	@ExcelField(title="教育水平", align=2, sort=25)
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=11, message="手机号长度必须介于 0 和 11 之间")
	@ExcelField(title="手机号", align=2, sort=10)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=12, message="QQ号长度必须介于 0 和 12 之间")
	@ExcelField(title="QQ号", align=2, sort=15)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=20, message="电子邮件长度必须介于 0 和 20 之间")
	@ExcelField(title="电子邮件", align=2, sort=20)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="所属员工长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
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
	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	
	@Length(min=0, max=4, message="客户类型长度必须介于 0 和 4 之间")
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getFollowstatestr() {
		return followstatestr;
	}

	public void setFollowstatestr(String followstatestr) {
		this.followstatestr = followstatestr;
	}

	public String getUsertypestr() {
		return usertypestr;
	}

	public void setUsertypestr(String usertypestr) {
		this.usertypestr = usertypestr;
	}
	
}