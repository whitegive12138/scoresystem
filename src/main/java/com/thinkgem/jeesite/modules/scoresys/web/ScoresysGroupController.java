/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.scoresys.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.scoresys.entity.ScoresysGroup;
import com.thinkgem.jeesite.modules.scoresys.service.ScoresysGroupService;

/**
 * 分组信息Controller
 * @author cyh
 * @version 2020-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/scoresys/scoresysGroup")
public class ScoresysGroupController extends BaseController {

	@Autowired
	private ScoresysGroupService scoresysGroupService;
	
	@ModelAttribute
	public ScoresysGroup get(@RequestParam(required=false) String id) {
		ScoresysGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scoresysGroupService.get(id);
		}
		if (entity == null){
			entity = new ScoresysGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("scoresys:scoresysGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScoresysGroup scoresysGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScoresysGroup> page = scoresysGroupService.findPage(new Page<ScoresysGroup>(request, response), scoresysGroup); 
		model.addAttribute("page", page);
		return "modules/scoresys/scoresysGroupList";
	}

	@RequiresPermissions("scoresys:scoresysGroup:view")
	@RequestMapping(value = "form")
	public String form(ScoresysGroup scoresysGroup, Model model) {
		model.addAttribute("scoresysGroup", scoresysGroup);
		return "modules/scoresys/scoresysGroupForm";
	}

	@RequiresPermissions("scoresys:scoresysGroup:edit")
	@RequestMapping(value = "save")
	public String save(ScoresysGroup scoresysGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scoresysGroup)){
			return form(scoresysGroup, model);
		}
		scoresysGroupService.save(scoresysGroup);
		addMessage(redirectAttributes, "保存分组成功");
		return "redirect:"+Global.getAdminPath()+"/scoresys/scoresysGroup/?repage";
	}
	
	@RequiresPermissions("scoresys:scoresysGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(ScoresysGroup scoresysGroup, RedirectAttributes redirectAttributes) {
		scoresysGroupService.delete(scoresysGroup);
		addMessage(redirectAttributes, "删除分组成功");
		return "redirect:"+Global.getAdminPath()+"/scoresys/scoresysGroup/?repage";
	}

}