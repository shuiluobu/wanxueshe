package com.wxs.entity.comment;

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
@TableName("t_dynamicmsg")
public class TDynamicmsg extends Model<TDynamicmsg> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
    /**
     * 动态正文
     */
	private String content;
    /**
     * 动态关联的班级
     */
	private Long classId;
	/**
	 * 课程类ID
	 */
	private  Long courseId;
    /**
     * 关联的课时
     */
	private Long classLessonId;
	/**
	 * 机构Id
	 */
	private Long organId;

	private Long studentId;


	private Date createTime;

	private Integer power; //权限 0:公开，1：好友可看，2:仅自己

	private String dynamicType; //动态类型 个人成长，作业等
    /**
     * 创建地方
     */
	//private String createPlace;
    /**
     * 坐标，经纬度
     */
	private double latitude; //经度
	private double longitude; //维度
	private Integer status=0;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getClassLessonId() {
		return classLessonId;
	}

	public void setClassLessonId(Long classLessonId) {
		this.classLessonId = classLessonId;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getDynamicType() {
		return dynamicType;
	}

	public void setDynamicType(String dynamicType) {
		this.dynamicType = dynamicType;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

//	public String getCreatePlace() {
//		return createPlace;
//	}
//
//	public void setCreatePlace(String createPlace) {
//		this.createPlace = createPlace;
//	}

	public Integer getStatus() {
		return status;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
