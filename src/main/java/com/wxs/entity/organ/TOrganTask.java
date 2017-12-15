package com.wxs.entity.organ;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 机构  教务-待办 附属 任务
 * 类型: 1：签到，2：课程点评，3：作业发布，4：作业点评
 * </p>
 *
 * @author wyh
 * @since 2017-12-08
 */
@TableName("t_organ_task")
public class TOrganTask extends Model<TOrganTask> {

    /**
     * 主键,自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 任务主题
     */
	private String title;
    /**
     * 类型: 1：签到，2：课程点评，3：作业发布，4：作业点评
     */
	private Integer type;
    /**
     * 所属待办Id
     */
	private Long agendaId;
    /**
     * 所属课程Id
     */
	private Long crouseId;
	/**
	 * 所属教师Id
	 */
	private Long classLessonId;
    /**
     * 所属教师Id
     */
	private Long teacherId;
    /**
     * 任务所属学生Id
     */
	private Long studentId;
    /**
     * 任务内容
     */
	private String content;
	/**
	 * 作业规定完成时间
	 */
	private Date shouldDoneTime;
	/**
	 * 完成时间
	 */
	private Date doneTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 课堂点评 权限   0:公开，1：好友可看，2:仅自己
	 */
	private Integer power = 0;
    /**
     * 状态 :  0:未完成 ， 1：已完成，2：签到请假
     */
	private Integer status;


	//---------------------额外字段
	@TableField(exist = false)
	private String studentName; //学生真实名字
	@TableField(exist = false)
	private String studentHeadImg;//学生头像
	@TableField(exist = false)
	private String teacherName; //教师名称
	@TableField(exist = false)
	private String courseName; //课程名称
	@TableField(exist = false)
	private String organName; //教师所属机构名称


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Long agendaId) {
		this.agendaId = agendaId;
	}

	public Long getCrouseId() {
		return crouseId;
	}

	public void setCrouseId(Long crouseId) {
		this.crouseId = crouseId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getShouldDoneTime() {
		return shouldDoneTime;
	}

	public void setShouldDoneTime(Date shouldDoneTime) {
		this.shouldDoneTime = shouldDoneTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentHeadImg() {
		return studentHeadImg;
	}

	public void setStudentHeadImg(String studentHeadImg) {
		this.studentHeadImg = studentHeadImg;
	}

	public Long getClassLessonId() {
		return classLessonId;
	}

	public void setClassLessonId(Long classLessonId) {
		this.classLessonId = classLessonId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
