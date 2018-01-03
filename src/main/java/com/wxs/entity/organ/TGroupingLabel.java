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
 * 学生分组标签表
 * </p>
 *
 * @author wyh
 * @since 2018-01-03
 */
@TableName("t_grouping_label")
public class TGroupingLabel extends Model<TGroupingLabel> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="labelId", type= IdType.AUTO)
	private Long labelId;
    /**
     * 分组标签名称
     */
	private String labelDesc;
    /**
     * 所属机构Id
     */
	private Long organId;
    /**
     * 创建人用户Id
     */
	private Long createUserId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 状态：1：启用，0：禁用
     */
	private Integer status;


	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabelDesc() {
		return labelDesc;
	}

	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	@DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
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
		return this.labelId;
	}

}
