package com.wxs.entity.organ;

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
 * 机构学生表
 * </p>
 *
 * @author wyh
 * @since 2017-12-29
 */
@TableName("t_organ_student")
public class TOrganStudent extends Model<TOrganStudent> {


    /**
     * 主键，自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 所属机构Id
     */
	private Long organId;
    /**
     * 学生真实姓名
     */
	private String studentName;
    /**
     * 手机号
     */
	private String studentMobilePhone;
    /**
     * 来源类型
     */
	private String sourceType;
    /**
     * 课程顾问Id
     */
	private Long advisorTeacherId;
    /**
     * 性别：  1:男，0：女
     */
	private Integer gender;
    /**
     * 学校
     */
	private String school;
    /**
     * 生日
     */
	private Date birthday;
	/**
	 * 头像
	 */
	private String headImg;
	/**
	 * 余额
	 */
	private Double balance;
    /**
     * 创建人用户ID
     */
	private Long createUserId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 状态： 1：启用，0：禁用
     */
	private Integer status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentMobilePhone() {
		return studentMobilePhone;
	}

	public void setStudentMobilePhone(String studentMobilePhone) {
		this.studentMobilePhone = studentMobilePhone;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Long getAdvisorTeacherId() {
		return advisorTeacherId;
	}

	public void setAdvisorTeacherId(Long advisorTeacherId) {
		this.advisorTeacherId = advisorTeacherId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
