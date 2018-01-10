package com.wxs.entity.record;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxs.entity.comment.TDynamicImg;
import com.wxs.entity.organ.TStudentImpressTag;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 机构不同业务的操作记录表
 * </p>
 *
 * @author wyh
 * @since 2018-01-05
 */
@TableName("t_business_record")
public class TBusinessRecord extends Model<TBusinessRecord> {

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 所属教师Id
     */
	private Long teacherId;
    /**
     * 所属机构Id
     */
	private Long organId;
    /**
     * 所属学生Id
     */
	private Long studentId;
    /**
     * 业务Id,不同类型对应不同的表
     */
	private Long businessId;
    /**
     * 业务类型，如：课程评价、签到、报名等
     */
	private String businessType;
    /**
     * 描述
     */
	private String remark;
    /**
     * 记录生成人用户Id
     */
	private Long createUserId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 状态： 1：启用，0：禁用
     */
	private Integer status;

	//-----额外字段
	@TableField(exist = false)
	private List<TStudentImpressTag> impressTags; //学生印象标签 - 课堂点评
	@TableField(exist = false)
	private String dynamicContent;//点评 下的  动态内容
	@TableField(exist = false)
	private List<TDynamicImg> dynamicImgs;  //点评 下的 动态图片
	@TableField(exist = false)
	private String teacherName; //教师名称
	@TableField(exist = false)
	private String studentName;  //学生姓名
	@TableField(exist = false)
	private String clName;  //课程-课时



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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

	public List<TStudentImpressTag> getImpressTags() {
		return impressTags;
	}

	public void setImpressTags(List<TStudentImpressTag> impressTags) {
		this.impressTags = impressTags;
	}

	public String getDynamicContent() {
		return dynamicContent;
	}

	public void setDynamicContent(String dynamicContent) {
		this.dynamicContent = dynamicContent;
	}

	public List<TDynamicImg> getDynamicImgs() {
		return dynamicImgs;
	}

	public void setDynamicImgs(List<TDynamicImg> dynamicImgs) {
		this.dynamicImgs = dynamicImgs;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClName() {
		return clName;
	}

	public void setClName(String clName) {
		this.clName = clName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
