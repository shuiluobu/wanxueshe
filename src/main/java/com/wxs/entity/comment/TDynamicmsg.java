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
@TableName("t_dynamicmsg")
public class TDynamicmsg extends Model<TDynamicmsg> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
    /**
     * 动态正文
     */
	private String content;
    /**
     * 动态关联的班级
     */
	private Long classId;
    /**
     * 关联的课时
     */
	private Long classLessonId;
    /**
     * 图片或视频ID集合
     */
	private String imgUrlIds;
	private Long videoId;
    /**
     * 权限，是否公开等
     */
	private Integer jurisdiction;
	private Date createTime;
    /**
     * 创建地方
     */
	private String createPlace;
    /**
     * 坐标，经纬度
     */
	private String coordinate;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getClassLessonId() {
		return classLessonId;
	}

	public void setClassLessonId(Long classLessonId) {
		this.classLessonId = classLessonId;
	}

	public String getImgUrlIds() {
		return imgUrlIds;
	}

	public void setImgUrlIds(String imgUrlIds) {
		this.imgUrlIds = imgUrlIds;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(Integer jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatePlace() {
		return createPlace;
	}

	public void setCreatePlace(String createPlace) {
		this.createPlace = createPlace;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
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
