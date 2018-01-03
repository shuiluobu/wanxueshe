package com.wxs.entity.course;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author skyer
 * @since 2017-10-25
 */
@TableName("t_class_course")
public class TClassCourse extends Model<TClassCourse> {

	private static final long serialVersionUID = 1L;
    /**
     * 主键,自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

	private String courseName; //冗余字段

	@TableField(exist = false)
	private String courseCode; //课程编号
	/**
	 * 课时数量
	 */
	private Integer canQty;//冗余字段
    /**
     * 开始时间
     */
	private Date beginTime;
    /**
     * 结束时间
     */
	private Date endTime;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 状态 1：启用，0：禁止
     */
	private Integer status;
	/**
	 * 课程大Id
	 */
	private Long courseCateId;
	/**
	 * 剩余课时
	 */
	private Integer surplusClassLesson;

	private Long  teacherId;

	private String teacherName;

	private String assistantIds; //助教集合，来自教师表

	private Long organizationId;//冗余字段

	private String subjectType;//学科分类


	@TableField(exist = false)
	private String organName; //所属教育机构名称
	@TableField(exist = false)
	private Integer page = 1;       //分页查询  页码
	@TableField(exist = false)
	private Integer limit = 10;  //分页查询  每页条数
	@TableField(exist = false)
	private Integer pageStartIndex; //分页查询 起始下表

	public String getAssistantIds() {
		return assistantIds;
	}

	public void setAssistantIds(String assistantIds) {
		this.assistantIds = assistantIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCanQty() {
		return canQty;
	}

	public void setCanQty(Integer canQty) {
		this.canQty = canQty;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCourseCateId() {
		return courseCateId;
	}

	public void setCourseCateId(Long courseCateId) {
		this.courseCateId = courseCateId;
	}

	public Integer getSurplusClassLesson() {
		return surplusClassLesson;
	}

	public void setSurplusClassLesson(Integer surplusClassLesson) {
		this.surplusClassLesson = surplusClassLesson;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPageStartIndex() {
		return pageStartIndex;
	}

	public void setPageStartIndex(Integer pageStartIndex) {
		this.pageStartIndex = pageStartIndex;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
