/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oaec.crm.common.service.TreeService;
import com.oaec.crm.modules.sys.dao.AreaDao;
import com.oaec.crm.modules.sys.entity.Area;
import com.oaec.crm.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author kaven.wei
 * @version 2017
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}
