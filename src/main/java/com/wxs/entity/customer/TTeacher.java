package com.wxs.entity.customer;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wxs.entity.organ.TOrganization;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@TableName("t_teacher")
public class TTeacher extends Model<TTeacher> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String teacherName;
    private Long organizationId;
    private String realName;
    private String teacherCode;
    private String mobilePhone;
    private String telePhone;
    private Integer leval; //等级
    private String idCode;
    private Integer sex;
    private Integer age;
    private String teachRemark;
    private String introduce;
    private Date createTime;
    private String headImg; //头像
    private Integer status;
    @TableField(exist = false)
    private TOrganization organization;


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

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

	public Integer getLeval() {
		return leval;
	}

	public void setLeval(Integer leval) {
		this.leval = leval;
	}

	public String getMobilePhone() {
        return mobilePhone;
    }

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTeachRemark() {
		return teachRemark;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setTeachRemark(String teachRemark) {
		this.teachRemark = teachRemark;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public TOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(TOrganization organization) {
		this.organization = organization;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
