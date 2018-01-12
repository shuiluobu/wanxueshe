package com.wxs.entity.course;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 课时
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@TableName("t_class_lesson")
public class TClassLesson extends Model<TClassLesson> {

    private static final long serialVersionUID = 1L;
	/**
	 * 主键,自增
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;
    /**
     * 课时编号
     */
	private Integer lessonSeq;
	/**
	 * 课时名称
	 */
	private String lessonName;
    /**
     * 所属课程
     */
	private Long courseId;
    /**
     * 上课开始时间
     */
	private Date beginTime;
    /**
     * 上课结束时间
     */
	private Date endTime;
    /**
     * 应到人数
     */
	private Integer shouldQty;
    /**
     * 实到人数
     */
	private Integer realQty;
	/**
	 * 讲课内容
	 */
	private String content;
    /**
     * 应该上课老师
     */
	private Long shouldTearcherId;
    /**
     * 实际上课老师
     */
	private Long realTearcherId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 状态 1:启用，0：禁止
	 */
	private Integer status;

	//------额外字段
	@TableField(exist = false)
	private String courseName;    //所属课程名称
	@TableField(exist = false)
	private String shouldTearcherName; //应上课老师姓名
	@TableField(exist = false)
	private String realTearcherName; //实际上课老师姓名
	@TableField(exist = false)
	private Integer page = 1;       //分页查询  页码
	@TableField(exist = false)
	private Integer limit = 10;  //分页查询  每页条数
	@TableField(exist = false)
	private Integer pageStartIndex; //分页查询 起始下表
	@TableField(exist = false)
	private Integer ifDone;      //课时是否已上过了



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLessonSeq() {
		return lessonSeq;
	}

	public void setLessonSeq(Integer lessonSeq) {
		this.lessonSeq = lessonSeq;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

	public Integer getShouldQty() {
		return shouldQty;
	}

	public void setShouldQty(Integer shouldQty) {
		this.shouldQty = shouldQty;
	}

	public Integer getRealQty() {
		return realQty;
	}

	public void setRealQty(Integer realQty) {
		this.realQty = realQty;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getShouldTearcherId() {
		return shouldTearcherId;
	}

	public void setShouldTearcherId(Long shouldTearcherId) {
		this.shouldTearcherId = shouldTearcherId;
	}

	public Long getRealTearcherId() {
		return realTearcherId;
	}

	public void setRealTearcherId(Long realTearcherId) {
		this.realTearcherId = realTearcherId;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getShouldTearcherName() {
		return shouldTearcherName;
	}

	public void setShouldTearcherName(String shouldTearcherName) {
		this.shouldTearcherName = shouldTearcherName;
	}

	public String getRealTearcherName() {
		return realTearcherName;
	}

	public void setRealTearcherName(String realTearcherName) {
		this.realTearcherName = realTearcherName;
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

	public Integer getIfDone() {
		return ifDone;
	}

	public void setIfDone(Integer ifDone) {
		this.ifDone = ifDone;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
