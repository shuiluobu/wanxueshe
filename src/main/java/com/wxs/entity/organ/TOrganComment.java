package com.wxs.entity.organ;

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
 * 
 * </p>
 *
 * @author skyer
 * @since 2017-12-08
 */
@TableName("t_organ_comment")
public class TOrganComment extends Model<TOrganComment> {

    /**
     * 主键,自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 所属项目Id
     */
	private Long itemId;
    /**
     * 评论类型：1：待办评论，2：课堂点评，3：作业点评
     */
	private Integer type;
    /**
     * 评论人 用户Id
     */
	private Long fromUserId;
    /**
     * 评论@的人的 用户Id
     */
	private Long toUserId;
    /**
     * 评论人 教师名称
     */
	private String fromUserName;
    /**
     * 评论@的人的 教师名称
     */
	private String toUserName;
    /**
     * 评论内容
     */
	private String content;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 状态 0:禁用，1：启用
     */
	private Integer status;

	//额外字段
	@TableField(exist = false)
	private String fromUserHeadImg;  //评论人头像


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@DateTimeFormat(pattern ="yyyy-MM-dd  HH:mm:ss")
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

	public String getFromUserHeadImg() {
		return fromUserHeadImg;
	}

	public void setFromUserHeadImg(String fromUserHeadImg) {
		this.fromUserHeadImg = fromUserHeadImg;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
