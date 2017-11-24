package com.wxs.entity.course;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wxs.entity.organ.TOrganization;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author skyer
 * @since 2017-10-25
 */
@TableName("t_course")
public class TCourse extends Model<TCourse> {

	private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 所属机构
	 */
	private Long organizationId;
	/**
	 * 课程代码
	 */
	private String courseCode;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课时数量
	 */
	private Integer canQty;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Integer status;
	/**
	 * 大课程类型Code
	 */
	private String courseCateId;
	//private Long classId;
	/**
	 * 剩余课时
	 */
	private Integer surplusClassLesson;

	private TOrganization organization;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public TOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(TOrganization organization) {
		this.organization = organization;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getCanQty() {
		return canQty;
	}

	public void setCanQty(Integer canQty) {
		this.canQty = canQty;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCourseCateId() {
		return courseCateId;
	}

	public void setCourseCateId(String courseCateId) {
		this.courseCateId = courseCateId;
	}

//	public Long getClassId() {
//		return classId;
//	}
//
//	public void setClassId(Long classId) {
//		this.classId = classId;
//	}

	public Integer getSurplusClassLesson() {
		return surplusClassLesson;
	}

	public void setSurplusClassLesson(Integer surplusClassLesson) {
		this.surplusClassLesson = surplusClassLesson;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
