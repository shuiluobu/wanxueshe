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
@TableName("t_follow_parent")
public class TFollowParent extends Model<TFollowParent> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Long leaderPid;
	private Long followPid;
	private Date createTime;
	private Date updateTime;
	private Integer status;


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

	public Long getLeaderPid() {
		return leaderPid;
	}

	public void setLeaderPid(Long leaderPid) {
		this.leaderPid = leaderPid;
	}

	public Long getFollowPid() {
		return followPid;
	}

	public void setFollowPid(Long followPid) {
		this.followPid = followPid;
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
