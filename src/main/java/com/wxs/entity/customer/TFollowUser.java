package com.wxs.entity.customer;

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
@TableName("t_follow_user")
public class TFollowUser extends Model<TFollowUser> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Long fuserId; //关注的用户的ID
	private String relationType; //10:朋友，20:屏蔽,30:删除
	private String memoName; //备注名称
	private Date createTime;
	private Date updateTime;
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

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getFuserId() {
		return fuserId;
	}

	public void setFuserId(Long fuserId) {
		this.fuserId = fuserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemoName() {
		return memoName;
	}

	public void setMemoName(String memoName) {
		this.memoName = memoName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
