/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oaec.crm.common.persistence.Page;
import com.oaec.crm.common.service.CrudService;
import com.oaec.crm.modules.crm.entity.CrmCustomfollow;
import com.oaec.crm.modules.crm.dao.CrmCustomfollowDao;

/**
 * 客户资料跟踪管理Service
 * @author kaven.wei
 * @version 2017-06-11
 */
@Service
@Transactional(readOnly = true)
public class CrmCustomfollowService extends CrudService<CrmCustomfollowDao, CrmCustomfollow> {

	public CrmCustomfollow get(String id) {
		return super.get(id);
	}
	
	public List<CrmCustomfollow> findList(CrmCustomfollow crmCustomfollow) {
		return super.findList(crmCustomfollow);
	}
	
	public Page<CrmCustomfollow> findPage(Page<CrmCustomfollow> page, CrmCustomfollow crmCustomfollow) {
		return super.findPage(page, crmCustomfollow);
	}
	
	@Transactional(readOnly = false)
	public void save(CrmCustomfollow crmCustomfollow) {
		super.save(crmCustomfollow);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrmCustomfollow crmCustomfollow) {
		super.delete(crmCustomfollow);
	}
	
}