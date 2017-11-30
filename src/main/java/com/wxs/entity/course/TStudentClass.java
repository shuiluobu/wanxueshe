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
 * @since 2017-09-23
 */
@TableName("t_student_class")
public class TStudentClass extends Model<TStudentClass> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 所属学生Id
     */
	private Long studentId;
    /**
     * 用户Id
     */
	private Long userId;
	private Long organizationId;
    /**
     * 课程Id
     */
	private Long coursesId;
    /**
     * 课堂，班级ID
     */
	private Long classId;
    /**
     * 状态
     */
	private Integer status;

	private Integer isEnd; //0：未完成，1：已完成 课程是否完成
	private Date createTime;
	private Double coursePrice; //学生实际购买课程的价格


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getCoursesId() {
		return coursesId;
	}

	public void setCoursesId(Long coursesId) {
		this.coursesId = coursesId;
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

	public Integer getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(Double coursePrice) {
		this.coursePrice = coursePrice;
	}

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
	protected Serializable pkVal() {
		return this.id;
	}

}