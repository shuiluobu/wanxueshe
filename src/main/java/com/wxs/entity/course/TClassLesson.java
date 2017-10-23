package com.wxs.entity.course;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@TableName("t_class_lesson")
public class TClassLesson extends Model<TClassLesson> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 课时编号
     */
	private Integer lessonSeq;
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
	private String content;
    /**
     * 应该上课老师
     */
	private Long shouldTearcherId;
    /**
     * 实际上课老师
     */
	private Long realTearcherId;
	private Date createTime;
	private Date updateTime;
	private Long status;


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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
