package com.wxs.entity.customer;

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
 * 用户登录表
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
@TableName("t_front_user")
public class TFrontUser extends Model<TFrontUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 昵称
     */
	private String nickName;
    /**
     * 登录用户名
     */
	private String userName;
    /**
     * 密码
     */
	private String passWord;
    /**
     * 登录邮箱
     */
	private String email;
    /**
     * 登录手机号
     */
	private String mobilePhone;
    /**
     * 微信号
     */
	private String wxCode;
	/**
	 * 创建时间
	 */
	private Date createTime;
    /**
     * 所在城市
     */
	private String city;
    /**
     * 用户类型 1:家长，2:老师，3:机构
     */
	private Integer userType;
    /**
     * 等级
     */
	private Integer level;
    /**
     * 描述，个性签名，简介
     */
	private String introduce;
    /**
     * 头像
     */
	private String headImg;
	/**
	 * 状态: 0：禁用，1：启用
	 */
	private Integer status;

	//额外字段
	@TableField(exist = false)
	private String teacherName; //教师名称
	@TableField(exist = false)
	private Long organId;  //所属教育机构Id



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getWxCode() {
		return wxCode;
	}

	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}
	@DateTimeFormat(pattern ="yyyy-MM-dd  HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
