/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.scoresys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 分组信息Entity
 * @author cyh
 * @version 2020-04-02
 */
public class ScoresysGroup extends DataEntity<ScoresysGroup> {
	
	private static final long serialVersionUID = 1L;
	private String groupName;		// 组名
	private List<ScoresysStudent> scoresysStudentList = Lists.newArrayList();		// 子表列表
	
	public ScoresysGroup() {
		super();
	}

	public ScoresysGroup(String id){
		super(id);
	}

	@Length(min=0, max=255, message="组名长度必须介于 0 和 255 之间")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public List<ScoresysStudent> getScoresysStudentList() {
		return scoresysStudentList;
	}

	public void setScoresysStudentList(List<ScoresysStudent> scoresysStudentList) {
		this.scoresysStudentList = scoresysStudentList;
	}
}