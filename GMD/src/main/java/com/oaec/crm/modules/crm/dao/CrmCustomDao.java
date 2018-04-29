/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.dao;

import com.oaec.crm.common.persistence.CrudDao;
import com.oaec.crm.common.persistence.annotation.MyBatisDao;
import com.oaec.crm.modules.crm.entity.CrmCustom;

/**
 * 客户资料管理DAO接口
 * @author kaven.wei
 * @version 2017-06-12
 */
@MyBatisDao
public interface CrmCustomDao extends CrudDao<CrmCustom> {
	public void customAssign(CrmCustom custom);
	public void customFollow(CrmCustom custom);
}