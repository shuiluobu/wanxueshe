package com.wxs.entity.customer;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author skyer
 * @since 2017-12-05
 */
@TableName("t_wx_user")
public class TWxUser extends Model<TWxUser> {

    private static final long serialVersionUID = 1L;

	private Long userId; //与user表关联的
	private String openId;
	private String wxCode;
	private String headImg;
	private String gender;
	private String mobilePhone;
    /**
     * 唯一标示
     */
	@TableId(value="unionId", type= IdType.INPUT)
	private String unionId;
	private Integer status;
	private Date createTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWxCode() {
		return wxCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Override
	protected Serializable pkVal() {
		return this.unionId;
	}

}
