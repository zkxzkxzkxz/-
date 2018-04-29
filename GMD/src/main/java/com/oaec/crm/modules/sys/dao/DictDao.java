/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.sys.dao;

import java.util.List;

import com.oaec.crm.common.persistence.CrudDao;
import com.oaec.crm.common.persistence.annotation.MyBatisDao;
import com.oaec.crm.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author kaven.wei
 * @version 2017
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}
