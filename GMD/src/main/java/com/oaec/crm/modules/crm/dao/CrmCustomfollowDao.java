/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.dao;

import com.oaec.crm.common.persistence.CrudDao;
import com.oaec.crm.common.persistence.annotation.MyBatisDao;
import com.oaec.crm.modules.crm.entity.CrmCustomfollow;

/**
 * 客户资料跟踪管理DAO接口
 * @author kaven.wei
 * @version 2017-06-11
 */
@MyBatisDao
public interface CrmCustomfollowDao extends CrudDao<CrmCustomfollow> {
	
}