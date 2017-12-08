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
@TableName("t_dyvideo")
public class TDyvideo extends Model<TDyvideo> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 封面图片
     */
	private String bookImg;
	private String videoUrl;
	private Integer size;
	private Date createTime;
	private Long dynamicId;
	private Integer status=0;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
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

	public Long getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Long dynamicId) {
		this.dynamicId = dynamicId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
