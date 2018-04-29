/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oaec.crm.common.service.CrudService;
import com.oaec.crm.common.utils.CacheUtils;
import com.oaec.crm.modules.sys.dao.DictDao;
import com.oaec.crm.modules.sys.entity.Dict;
import com.oaec.crm.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author kaven.wei
 * @version 2017
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}
