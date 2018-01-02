package com.wxs.entity.common;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author skyer
 * @since 2017-12-15
 */
@TableName("t_subject_type_dict")
public class TSubjectTypeDict extends Model<TSubjectTypeDict> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String parentCode;
	private String subjectTypeCode;
	private String subjectTypeName;
	private Integer status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSubjectTypeCode() {
		return subjectTypeCode;
	}

	public void setSubjectTypeCode(String subjectTypeCode) {
		this.subjectTypeCode = subjectTypeCode;
	}

	public String getSubjectTypeName() {
		return subjectTypeName;
	}

	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
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
