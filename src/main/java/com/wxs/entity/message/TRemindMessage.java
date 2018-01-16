package com.wxs.entity.message;

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
 * @since 2017-11-27
 */
@TableName("t_remind_message")
public class TRemindMessage extends Model<TRemindMessage> {

    private static final long serialVersionUID = 1L;
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 发送人
     */
	private Long userId;
    /**
     * 发给谁
     */
	private Long fromUserId;
	private String tile;
    /**
     * 动态（消息）主题ID
     */
	//private Long dyMsgId;
    /**
     * 消息提醒类型
     */
	private String remindType;

	private String jmsType; //点对点通知还是广播 queue or topic

	private String messageContent; //消息主题

	private Integer readStatus=0; //是否已读

	private String remindMedia; //提醒通知媒介 XCX：小程序，GZH:公众号,DX：短信
	/**
	 * 提醒时间
	 */
	private Date remindTime; //提醒时间

	private Date createTime;
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

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}

//	public Long getDyMsgId() {
//		return dyMsgId;
//	}
//
//	public void setDyMsgId(Long dyMsgId) {
//		this.dyMsgId = dyMsgId;
//	}

	public String getRemindType() {
		return remindType;
	}

	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}

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

	public String getJmsType() {
		return jmsType;
	}

	public void setJmsType(String jmsType) {
		this.jmsType = jmsType;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public String getRemindMedia() {
		return remindMedia;
	}

	public void setRemindMedia(String remindMedia) {
		this.remindMedia = remindMedia;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
