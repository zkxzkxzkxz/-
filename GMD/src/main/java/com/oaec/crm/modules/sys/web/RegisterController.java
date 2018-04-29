package com.oaec.crm.modules.sys.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oaec.crm.common.web.BaseController;
import com.oaec.crm.modules.sys.entity.Role;
import com.oaec.crm.modules.sys.entity.User;
import com.oaec.crm.modules.sys.service.SystemService;
import com.oaec.crm.modules.sys.service.UserService;


@Controller
public class RegisterController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "${adminPath}/register", method = RequestMethod.GET)
	public String registerView() {
		System.out.println("registercontroller");
		return "modules/sys/sysRegister";
	}
	
	@RequestMapping(value = "${adminPath}/register", method = RequestMethod.POST)
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		user.setPassword(SystemService.entryptPassword(user.getPassword()));
		if("1".equals(user.getUserType())) {
			user.setRole(new Role("1"));
		}else if("2".equals(user.getUserType())) {
			user.setRole(new Role("0f7991b5e02b437b97c7114ba68c4e12"));
		}else if("3".equals(user.getUserType())) {
			user.setRole(new Role("259117875515496fab5fc577c210e0d1"));
		}
		userService.register(user);
		
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	

}
