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
 * @since 2017-09-29
 */
@TableName("t_course_category")
public class TCourseCategory extends Model<TCourseCategory> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String courseCategoryName;
	private String courseCategoryCode;
    /**
     * 课时数量
     */
	private Integer canQty = 0;
	private String explain;
	private Long organId;
	private Integer status;
	private Date createTime;
	private Integer alreadyStudySum = 0; //已学习
	private Integer wishStudySum = 0; //想学习


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

	public String getCourseCategoryCode() {
		return courseCategoryCode;
	}

	public void setCourseCategoryCode(String courseCategoryCode) {
		this.courseCategoryCode = courseCategoryCode;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
