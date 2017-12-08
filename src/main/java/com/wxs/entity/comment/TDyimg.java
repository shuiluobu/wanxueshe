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
@TableName("t_dyimg")
public class TDyimg extends Model<TDyimg> {

    private static final long serialVersionUID = 1L;

	private Long id;

	private Long dynamicId; //动态主题
    /**
     * 缩略图
     */
	private String thumbImgUrl;
    /**
     * 原图
     */
	private String originalImgUrl;
	private Integer status;
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThumbImgUrl() {
		return thumbImgUrl;
	}

	public void setThumbImgUrl(String thumbImgUrl) {
		this.thumbImgUrl = thumbImgUrl;
	}

	public String getOriginalImgUrl() {
		return originalImgUrl;
	}

	public void setOriginalImgUrl(String originalImgUrl) {
		this.originalImgUrl = originalImgUrl;
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
