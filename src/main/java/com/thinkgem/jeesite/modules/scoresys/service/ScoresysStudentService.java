/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.scoresys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.scoresys.entity.ScoresysStudent;
import com.thinkgem.jeesite.modules.scoresys.dao.ScoresysStudentDao;

/**
 * 学生信息Service
 * @author cyh
 * @version 2020-04-02
 */
@Service
@Transactional(readOnly = true)
public class ScoresysStudentService extends CrudService<ScoresysStudentDao, ScoresysStudent> {

	public ScoresysStudent get(String id) {
		return super.get(id);
	}
	
	public List<ScoresysStudent> findList(ScoresysStudent scoresysStudent) {
		return super.findList(scoresysStudent);
	}
	
	public Page<ScoresysStudent> findPage(Page<ScoresysStudent> page, ScoresysStudent scoresysStudent) {
		return super.findPage(page, scoresysStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(ScoresysStudent scoresysStudent) {
		super.save(scoresysStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(ScoresysStudent scoresysStudent) {
		super.delete(scoresysStudent);
	}
	
}