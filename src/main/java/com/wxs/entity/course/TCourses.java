package com.wxs.entity.course;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wxs.entity.organ.TOrganization;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@TableName("t_courses")
public class TCourses extends Model<TCourses> {

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
     * 课程介绍
     */
	private String courseRemark;
    /**
     * 市场价
     */
	private Double marketPrice;
    /**
     * 优惠价格
     */
	private Double preferenPrice;
	private Date preferStartTime;
	private Date preferEndTime;
    /**
     * 课时数量
     */
	private Integer canQty;
	/**
	 * 课程类型
	 */
	private String categoryCode;
	private String categoryName;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Integer status;

	@TableField(exist=false)
	private List<TClassLesson> lessons;
	@TableField(exist = false)
	private TOrganization organization;

	public List<TClassLesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<TClassLesson> lessons) {
		this.lessons = lessons;
	}

	public TOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(TOrganization organization) {
		this.organization = organization;
	}

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

	public String getCourseRemark() {
		return courseRemark;
	}

	public void setCourseRemark(String courseRemark) {
		this.courseRemark = courseRemark;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getPreferenPrice() {
		return preferenPrice;
	}

	public void setPreferenPrice(Double preferenPrice) {
		this.preferenPrice = preferenPrice;
	}

	public Date getPreferStartTime() {
		return preferStartTime;
	}

	public void setPreferStartTime(Date preferStartTime) {
		this.preferStartTime = preferStartTime;
	}

	public Date getPreferEndTime() {
		return preferEndTime;
	}

	public void setPreferEndTime(Date preferEndTime) {
		this.preferEndTime = preferEndTime;
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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
