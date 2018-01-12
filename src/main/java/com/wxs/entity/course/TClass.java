package com.wxs.entity.course;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 班级 ,一个班级由一个老师，一个课程组成
 * </p>
 *
 * @author skyer
 * @since 2017-10-25
 */
@TableName("t_class")
public class TClass extends Model<TClass> {

    private static final long serialVersionUID = 1L;
	/**
	 * 主键,自增
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;
	/**
	 * 班级编号
	 */
	private String classCode;
    /**
     * 班级名称
     */
	private String className;
	/**
	 * 班级类型
	 */
	private String classType;
	/**
	 * 课程ID
	 */
	private Long courseId;
    /**
     * 授课老师Id
     */
	private Long teacherId;
    /**
     * 所属机构，冗余
     */
	private Long organId;
    /**
     * 班级等级
     */
	private Integer level;
    /**
     * 最多容纳人容量
     */
	private Integer capacity;
	/**
	 * 创建时间
	 */
	private Date createTime;
    /**
     * 是否结束 1：是，0：否
     */
	private Integer isEnd;
	/**
	 * 状态 1:启用，0：禁止
	 */
	private Integer status;
    /**
     * 实际容纳学生
     */
	private Integer realQty;

	//-----额外字段
	@TableField(exist = false)
	private String teacherName; //授课老师名字
	@TableField(exist = false)
	private String courseName;  //课程名称
	@TableField(exist = false)
	private Integer doneLessonNum;  //已完成课时数
	@TableField(exist = false)
	private Integer totalLessonNum; //总课时数
	@TableField(exist = false)
	private Long nextLessonId;      //接下来需上课的  课时Id
	@TableField(exist = false)
	private String nextLessonName;  //接下来需上课的  课时名称
	@TableField(exist = false)
	private Date nextLessonSTime;  //接下来需上课的  课时 的 开始时间
	@TableField(exist = false)
	private Date nextLessonETime;  //接下来需上课的  课时 的 结束时间
	@TableField(exist = false)
	private String organName;   //所属教育机构名称
	@TableField(exist = false)
	private Integer organLevel;  //机构级别
	@TableField(exist = false)
	private Integer page = 1;       //分页查询  页码
	@TableField(exist = false)
	private Integer limit = 10;  //分页查询  每页条数
	@TableField(exist = false)
	private Integer pageStartIndex; //分页查询 起始下表


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRealQty() {
		return realQty;
	}

	public void setRealQty(Integer realQty) {
		this.realQty = realQty;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

	public Integer getOrganLevel() {
		return organLevel;
	}

	public void setOrganLevel(Integer organLevel) {
		this.organLevel = organLevel;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPageStartIndex() {
		return pageStartIndex;
	}

	public void setPageStartIndex(Integer pageStartIndex) {
		this.pageStartIndex = pageStartIndex;
	}

	public Integer getDoneLessonNum() {
		return doneLessonNum;
	}

	public void setDoneLessonNum(Integer doneLessonNum) {
		this.doneLessonNum = doneLessonNum;
	}

	public Integer getTotalLessonNum() {
		return totalLessonNum;
	}

	public void setTotalLessonNum(Integer totalLessonNum) {
		this.totalLessonNum = totalLessonNum;
	}

	public Long getNextLessonId() {
		return nextLessonId;
	}

	public void setNextLessonId(Long nextLessonId) {
		this.nextLessonId = nextLessonId;
	}

	public String getNextLessonName() {
		return nextLessonName;
	}

	public void setNextLessonName(String nextLessonName) {
		this.nextLessonName = nextLessonName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getNextLessonSTime() {
		return nextLessonSTime;
	}

	public void setNextLessonSTime(Date nextLessonSTime) {
		this.nextLessonSTime = nextLessonSTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getNextLessonETime() {
		return nextLessonETime;
	}

	public void setNextLessonETime(Date nextLessonETime) {
		this.nextLessonETime = nextLessonETime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
