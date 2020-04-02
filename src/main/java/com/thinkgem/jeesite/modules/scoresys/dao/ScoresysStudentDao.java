/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.scoresys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.scoresys.entity.ScoresysStudent;

/**
 * 分组信息DAO接口
 * @author cyh
 * @version 2020-04-02
 */
@MyBatisDao
public interface ScoresysStudentDao extends CrudDao<ScoresysStudent> {
	
}