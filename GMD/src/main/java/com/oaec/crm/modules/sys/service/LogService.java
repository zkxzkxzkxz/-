/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oaec.crm.common.persistence.Page;
import com.oaec.crm.common.service.CrudService;
import com.oaec.crm.common.utils.DateUtils;
import com.oaec.crm.modules.sys.dao.LogDao;
import com.oaec.crm.modules.sys.entity.Log;

/**
 * 日志Service
 * @author kaven.wei
 * @version 2017
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}
	
}
