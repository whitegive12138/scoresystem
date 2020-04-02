/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.scoresys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 分组信息Entity
 * @author cyh
 * @version 2020-04-02
 */
public class ScoresysStudent extends DataEntity<ScoresysStudent> {
	
	private static final long serialVersionUID = 1L;
	private String stuId;		// 学号
	private String stuName;		// 姓名
	private String stuClass;		// 班级
	private String stuMajor;		// 科目
	private String stuScore;		// 分数
	private ScoresysGroup groupId;		// 分组编号 父类
	
	public ScoresysStudent() {
		super();
	}

	public ScoresysStudent(String id){
		super(id);
	}

	public ScoresysStudent(ScoresysGroup groupId){
		this.groupId = groupId;
	}

	@Length(min=0, max=64, message="学号长度必须介于 0 和 64 之间")
	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	@Length(min=0, max=255, message="姓名长度必须介于 0 和 255 之间")
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Length(min=0, max=255, message="班级长度必须介于 0 和 255 之间")
	public String getStuClass() {
		return stuClass;
	}

	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	
	@Length(min=0, max=255, message="科目长度必须介于 0 和 255 之间")
	public String getStuMajor() {
		return stuMajor;
	}

	public void setStuMajor(String stuMajor) {
		this.stuMajor = stuMajor;
	}
	
	@Length(min=0, max=3, message="分数长度必须介于 0 和 3 之间")
	public String getStuScore() {
		return stuScore;
	}

	public void setStuScore(String stuScore) {
		this.stuScore = stuScore;
	}
	
	@Length(min=0, max=64, message="分组编号长度必须介于 0 和 64 之间")
	public ScoresysGroup getGroupId() {
		return groupId;
	}

	public void setGroupId(ScoresysGroup groupId) {
		this.groupId = groupId;
	}
	
}