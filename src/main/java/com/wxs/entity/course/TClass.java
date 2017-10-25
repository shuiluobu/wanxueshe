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
 * @since 2017-10-25
 */
@TableName("t_class")
public class TClass extends Model<TClass> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String classCode;
    /**
     * 班级名称
     */
	private String className;
    /**
     * 授课老师
     */
	private Long teacherId;
    /**
     * 所属机构，冗余
     */
	private Long organId;
    /**
     * 班级等级
     */
	private Integer leval;
    /**
     * 最多容纳人容量
     */
	private Integer capacity;
	private Date createTime;
    /**
     * 是否结束
     */
	private Integer isEnd;
	private Integer status;
    /**
     * 实际容纳学生
     */
	private Integer realQty;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Integer getLeval() {
		return leval;
	}

	public void setLeval(Integer leval) {
		this.leval = leval;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRealQty() {
		return realQty;
	}

	public void setRealQty(Integer realQty) {
		this.realQty = realQty;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
