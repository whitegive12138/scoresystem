/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.scoresys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.scoresys.entity.ScoresysGroup;
import com.thinkgem.jeesite.modules.scoresys.dao.ScoresysGroupDao;
import com.thinkgem.jeesite.modules.scoresys.entity.ScoresysStudent;
import com.thinkgem.jeesite.modules.scoresys.dao.ScoresysStudentDao;

/**
 * 分组信息Service
 * @author cyh
 * @version 2020-04-02
 */
@Service
@Transactional(readOnly = true)
public class ScoresysGroupService extends CrudService<ScoresysGroupDao, ScoresysGroup> {

	@Autowired
	private ScoresysStudentDao scoresysStudentDao;
	
	public ScoresysGroup get(String id) {
		ScoresysGroup scoresysGroup = super.get(id);
		scoresysGroup.setScoresysStudentList(scoresysStudentDao.findList(new ScoresysStudent(scoresysGroup)));
		return scoresysGroup;
	}
	
	public List<ScoresysGroup> findList(ScoresysGroup scoresysGroup) {
		return super.findList(scoresysGroup);
	}
	
	public Page<ScoresysGroup> findPage(Page<ScoresysGroup> page, ScoresysGroup scoresysGroup) {
		return super.findPage(page, scoresysGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(ScoresysGroup scoresysGroup) {
		super.save(scoresysGroup);
		for (ScoresysStudent scoresysStudent : scoresysGroup.getScoresysStudentList()){
			if (scoresysStudent.getId() == null){
				continue;
			}
			if (ScoresysStudent.DEL_FLAG_NORMAL.equals(scoresysStudent.getDelFlag())){
				if (StringUtils.isBlank(scoresysStudent.getId())){
					scoresysStudent.setGroupId(scoresysGroup);
					scoresysStudent.preInsert();
					scoresysStudentDao.insert(scoresysStudent);
				}else{
					scoresysStudent.preUpdate();
					scoresysStudentDao.update(scoresysStudent);
				}
			}else{
				scoresysStudentDao.delete(scoresysStudent);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ScoresysGroup scoresysGroup) {
		super.delete(scoresysGroup);
		scoresysStudentDao.delete(new ScoresysStudent(scoresysGroup));
	}
	
}