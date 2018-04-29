package com.oaec.crm.modules.sys.service;

import javax.imageio.spi.RegisterableService;

import org.activiti.engine.impl.cmd.SaveAttachmentCmd;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oaec.crm.common.service.BaseService;
import com.oaec.crm.common.service.ServiceException;
import com.oaec.crm.common.utils.CacheUtils;
import com.oaec.crm.common.utils.StringUtils;
import com.oaec.crm.modules.sys.dao.UserDao;
import com.oaec.crm.modules.sys.entity.User;
import com.oaec.crm.modules.sys.utils.UserUtils;

@Service
public class UserService extends BaseService implements InitializingBean{

	@Autowired
	private UserDao userDao;
	
	public void register(User user) {
		user.preInsert();
		userDao.insert(user);	
		if (StringUtils.isNotBlank(user.getId())){			
			userDao.insertUserRole2(user);		
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		
	}

}
