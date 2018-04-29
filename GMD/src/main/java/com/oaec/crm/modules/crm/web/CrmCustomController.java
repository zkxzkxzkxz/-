/**
 * kaven.wei 2017
 */
package com.oaec.crm.modules.crm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.oaec.crm.common.beanvalidator.BeanValidators;
import com.oaec.crm.common.config.Global;
import com.oaec.crm.common.persistence.Page;
import com.oaec.crm.common.utils.DateUtils;
import com.oaec.crm.common.utils.StringUtils;
import com.oaec.crm.common.utils.excel.ExportExcel;
import com.oaec.crm.common.utils.excel.ImportExcel;
import com.oaec.crm.common.web.BaseController;
import com.oaec.crm.modules.crm.entity.CrmCustom;
import com.oaec.crm.modules.crm.service.CrmCustomService;

/**
 * 客户资料管理Controller
 * @author kaven.wei
 * @version 2017-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/crm/crmCustom")
public class CrmCustomController extends BaseController {

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
	
	@RequiresPermissions("crm:crmCustom:view")
	@RequestMapping(value = {"list", ""})
	public String list(CrmCustom crmCustom, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isBlank(crmCustom.getFollowstate())) {
			crmCustom.setFollowstate("0");
		}
		Page<CrmCustom> page = crmCustomService.findPage(new Page<CrmCustom>(request, response), crmCustom); 
		model.addAttribute("page", page);
		return "modules/crm/crmCustomList";
	}

	@RequiresPermissions("crm:crmCustom:view")
	@RequestMapping(value = "form")
	public String form(CrmCustom crmCustom, Model model) {
		model.addAttribute("crmCustom", crmCustom);
		return "modules/crm/crmCustomForm";
	}

	@RequiresPermissions("crm:crmCustom:edit")
	@RequestMapping(value = "save")
	public String save(CrmCustom crmCustom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, crmCustom)){
			return form(crmCustom, model);
		}
		crmCustomService.save(crmCustom);
		addMessage(redirectAttributes, "保存客户资料成功");
		return "redirect:"+Global.getAdminPath()+"/crm/crmCustom/?repage";
	}
	
	@RequiresPermissions("crm:crmCustom:edit")
	@RequestMapping(value = "delete")
	public String delete(CrmCustom crmCustom, RedirectAttributes redirectAttributes) {
		crmCustomService.delete(crmCustom);
		addMessage(redirectAttributes, "删除客户资料成功");
		return "redirect:"+Global.getAdminPath()+"/crm/crmCustom/?repage";
	}
	
	/**
	 * 下载 导入客户资料模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("crm:crmCustom:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "客户资料导入模板.xlsx";
    		List<CrmCustom> list = Lists.newArrayList(); 
    		CrmCustom custom = new CrmCustom();
    		custom.setCustomname("张三");
    		custom.setPhone("18630352270");
    		custom.setQq("864027345");
    		custom.setEmail("864027345@qq.com");
    		custom.setEducation("大专");
    		list.add(custom);
    		new ExportExcel("用户数据", CrmCustom.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/crmCustom/list?repage";
    }
	
	/**
	 * 导出客户资料数据
	 * @param crmCustom
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("crm:crmCustom:edit")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CrmCustom crmCustom, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "客户资料"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CrmCustom> page = crmCustomService.findPage(new Page<CrmCustom>(request, response,-1), crmCustom); 
    		new ExportExcel("客户资料", CrmCustom.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出客户资料信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/crmCustom/list?repage";
    }
	
	/**
	 * 导入客户资料数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("crm:crmCustom:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int rowNum = 2;
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CrmCustom> list = ei.getDataList(CrmCustom.class);
			for (CrmCustom custom : list){
				rowNum++;
				try{
					if (!"".equalsIgnoreCase(custom.getCustomname()) 
							&& !"".equalsIgnoreCase(custom.getPhone())){
						
						crmCustomService.save(custom);
						successNum++;
					}else{
						failureMsg.append("<br/>");
						if("".equalsIgnoreCase(custom.getCustomname()))
							failureMsg.append("第"+rowNum+"行数据客户姓名为空; ");
						if("".equalsIgnoreCase(custom.getPhone()))
							failureMsg.append("第"+rowNum+"行数据手机号为空; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>客户姓名： "+custom.getCustomname()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>>客户姓名： "+custom.getCustomname()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条客户资料"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入客户资料失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/crm/crmCustom/list?repage";
    }
	

}