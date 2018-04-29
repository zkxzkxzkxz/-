/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.sys.dao;

import com.oaec.crm.common.persistence.CrudDao;
import com.oaec.crm.common.persistence.annotation.MyBatisDao;
import com.oaec.crm.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author kaven.wei
 * @version 2017
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
