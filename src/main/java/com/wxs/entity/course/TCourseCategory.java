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
 * 课程分类
 * </p>
 *
 * @author skyer
 * @since 2017-10-25
 */
@TableName("t_course_category")
public class TCourseCategory extends Model<TCourseCategory> {

    private static final long serialVersionUID = 1L;
	/**
	 * 主键,自增
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 *课程分类名称
	 */
	private String courseCategoryName;
	/**
	 * 课程分类编号
	 */
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
     * 所属机构Id
     */
	private Long organId;
	/**
	 * 状态 1：启用，0：禁止
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
    /**
     * 已经招多少人
     */
	private Integer alreadyStudySum;
    /**
     * 市场价格
     */
	private Double marketPrice;
    /**
     * 优惠价格
     */
	private Double preferenPrice;
    /**
     * 优惠开始时间
     */
	private Date preferStartTime;
    /**
     * 优惠结束时间
     */
	private Date preferEndTime;
	/**
	 * 课程介绍
	 */
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

	//-----额外字段
	@TableField(exist = false)
	private String organName;   //所属教育机构名称
	@TableField(exist = false)
	private Integer page = 1;       //分页查询  页码
	@TableField(exist = false)
	private Integer limit = 10;  //分页查询  每页条数
	@TableField(exist = false)
	private Integer pageStartIndex; //分页查询 起始下表



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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getPreferStartTime() {
		return preferStartTime;
	}

	public void setPreferStartTime(Date preferStartTime) {
		this.preferStartTime = preferStartTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
