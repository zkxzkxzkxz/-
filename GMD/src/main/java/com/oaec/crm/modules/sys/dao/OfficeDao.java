/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.sys.dao;

import com.oaec.crm.common.persistence.TreeDao;
import com.oaec.crm.common.persistence.annotation.MyBatisDao;
import com.oaec.crm.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author kaven.wei
 * @version 2017
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
