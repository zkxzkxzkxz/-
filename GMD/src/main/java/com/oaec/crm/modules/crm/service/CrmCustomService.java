/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oaec.crm.common.persistence.Page;
import com.oaec.crm.common.service.CrudService;
import com.oaec.crm.modules.crm.dao.CrmCustomDao;
import com.oaec.crm.modules.crm.entity.CrmCustom;
import com.oaec.crm.modules.sys.utils.UserUtils;
import com.oaec.crm.modules.vo.CustomVO;

/**
 * 客户资料管理Service
 * @author kaven.wei
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class CrmCustomService extends CrudService<CrmCustomDao, CrmCustom> {
	@Autowired
	public CrmCustomDao crmCustomDao;

	public CrmCustom get(String id) {
		return super.get(id);
	}
	
	public List<CrmCustom> findList(CrmCustom crmCustom) {
		return super.findList(crmCustom);
	}
	
	public Page<CrmCustom> findPage(Page<CrmCustom> page, CrmCustom crmCustom) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		crmCustom.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser(), "o", "u"));
		crmCustom.setPage(page);
		page.setList(crmCustomDao.findList(crmCustom));
		return page;
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(CrmCustom custom) {
		if (custom.getIsNewRecord()){
			custom.preInsert();
			custom.setUserid(custom.getCreateBy().getId());
			custom.setStartdate(new Date());
			custom.setFollowstate("0");
			dao.insert(custom);
		}else{
			custom.preUpdate();
			dao.update(custom);
		}
	}
	
	@Transactional(readOnly = false)
	public void customAssign(String usertype,String userid,String[] ids) {
		for(String id : ids) {
			CrmCustom custom = new CrmCustom();
			custom.setId(id);
			custom.setUserid(userid);
			custom.setStartdate(new Date());
			custom.setUsertype(usertype);
			
			crmCustomDao.customAssign(custom);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void batCustomFollow(CustomVO customVO) {
		for(CrmCustom custom : customVO.getCustoms()) {
			custom.setLastdate(new Date());
			crmCustomDao.customFollow(custom);
		}
		
	}
	
}