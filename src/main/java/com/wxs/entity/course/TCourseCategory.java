package com.wxs.entity.course;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author skyer
 * @since 2017-10-25
 */
@TableName("t_course_category")
public class TCourseCategory extends Model<TCourseCategory> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String courseCategoryName;
	private Integer courseCategoryCode;
    /**
     * 课时数量
     */
	private Integer canQty;
    /**
     * 简单说明
     */
	private String explain;
    /**
     * 所属机构
     */
	private Long organId;
	private Integer status;
	private Date createTime;
    /**
     * 已经招多少人
     */
	private Integer alreadyStudySum;
    /**
     * 希望招多少个学生
     */
	private Integer wishStudySum;
    /**
     * 市场价格
     */
	private Double marketPrice;
    /**
     * 优惠价格
     */
	private Double preferenPrice;
    /**
     * 优惠时间
     */
	private Date preferStartTime;
    /**
     * 课程介绍
     */
	private Date preferEndTime;
	private String courseRemark;
    /**
     * 课程类型
     */
	private String courseType;
    /**
     * 课程分类
     */
	private String categoryType;
    /**
     * 封面
     */
	private String cover;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseCategoryName() {
		return courseCategoryName;
	}

	public void setCourseCategoryName(String courseCategoryName) {
		this.courseCategoryName = courseCategoryName;
	}

	public Integer getCourseCategoryCode() {
		return courseCategoryCode;
	}

	public void setCourseCategoryCode(Integer courseCategoryCode) {
		this.courseCategoryCode = courseCategoryCode;
	}

	public Integer getCanQty() {
		return canQty;
	}

	public void setCanQty(Integer canQty) {
		this.canQty = canQty;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAlreadyStudySum() {
		return alreadyStudySum;
	}

	public void setAlreadyStudySum(Integer alreadyStudySum) {
		this.alreadyStudySum = alreadyStudySum;
	}

	public Integer getWishStudySum() {
		return wishStudySum;
	}

	public void setWishStudySum(Integer wishStudySum) {
		this.wishStudySum = wishStudySum;
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

	public String getCourseRemark() {
		return courseRemark;
	}

	public void setCourseRemark(String courseRemark) {
		this.courseRemark = courseRemark;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
