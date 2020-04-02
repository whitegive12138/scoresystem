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
import com.thinkgem.jeesite.modules.scoresys.entity.ScoresysStudent;
import com.thinkgem.jeesite.modules.scoresys.service.ScoresysStudentService;

/**
 * 学生信息Controller
 * @author cyh
 * @version 2020-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/scoresys/scoresysStudent")
public class ScoresysStudentController extends BaseController {

	@Autowired
	private ScoresysStudentService scoresysStudentService;
	
	@ModelAttribute
	public ScoresysStudent get(@RequestParam(required=false) String id) {
		ScoresysStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = scoresysStudentService.get(id);
		}
		if (entity == null){
			entity = new ScoresysStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("scoresys:scoresysStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScoresysStudent scoresysStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ScoresysStudent> page = scoresysStudentService.findPage(new Page<ScoresysStudent>(request, response), scoresysStudent); 
		model.addAttribute("page", page);
		return "modules/scoresys/scoresysStudentList";
	}

	@RequiresPermissions("scoresys:scoresysStudent:view")
	@RequestMapping(value = "form")
	public String form(ScoresysStudent scoresysStudent, Model model) {
		model.addAttribute("scoresysStudent", scoresysStudent);
		return "modules/scoresys/scoresysStudentForm";
	}

	@RequiresPermissions("scoresys:scoresysStudent:edit")
	@RequestMapping(value = "save")
	public String save(ScoresysStudent scoresysStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, scoresysStudent)){
			return form(scoresysStudent, model);
		}
		scoresysStudentService.save(scoresysStudent);
		addMessage(redirectAttributes, "保存学生成功");
		return "redirect:"+Global.getAdminPath()+"/scoresys/scoresysStudent/?repage";
	}
	
	@RequiresPermissions("scoresys:scoresysStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(ScoresysStudent scoresysStudent, RedirectAttributes redirectAttributes) {
		scoresysStudentService.delete(scoresysStudent);
		addMessage(redirectAttributes, "删除学生成功");
		return "redirect:"+Global.getAdminPath()+"/scoresys/scoresysStudent/?repage";
	}

}