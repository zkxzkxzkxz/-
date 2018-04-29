/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oaec.crm.common.config.Global;
import com.oaec.crm.common.persistence.Page;
import com.oaec.crm.common.web.BaseController;
import com.oaec.crm.common.utils.StringUtils;
import com.oaec.crm.modules.crm.entity.CrmCustom;
import com.oaec.crm.modules.crm.service.CrmCustomService;

/**
 * 客户资料管理Controller
 * @author kaven.wei
 * @version 2017-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/crmCustomAssign")
public class CrmCustomAssignController extends BaseController {

	@Autowired
	private CrmCustomService crmCustomService;
	
	@ModelAttribute
	public CrmCustom get(@RequestParam(required=false) String id) {
		CrmCustom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = crmCustomService.get(id);
		}
		if (entity == null){
			entity = new CrmCustom();
		}
		return entity;
	}
	
	@RequiresPermissions("crm:crmCustomAssign:view")
	@RequestMapping(value = {"list", ""})
	public String list(CrmCustom crmCustom, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isBlank(crmCustom.getFollowstate())) {
			crmCustom.setFollowstate("0");
		}
		Page<CrmCustom> page = crmCustomService.findPage(new Page<CrmCustom>(request, response), crmCustom); 
		model.addAttribute("page", page);
		return "modules/crm/crmCustomAssignList";
	}

	@RequiresPermissions("crm:crmCustomAssign:edit")
	@RequestMapping(value = "customAssign")
	public String customAssign(String usertype,String[] userid,String[] ids, Model model, RedirectAttributes redirectAttributes) {
		String userId = userid[0];
		crmCustomService.customAssign(usertype, userId, ids);
		addMessage(redirectAttributes, "客户资料分配成功");
		return "redirect:"+Global.getAdminPath()+"/crm/crmCustomAssign/?repage";
	}
	
	
}