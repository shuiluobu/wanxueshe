package com.wxs.entity.customer;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author skyer
 * @since 2017-12-06
 */
@TableName("t_friend")
public class TFriend extends Model<TFriend> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long muserId; //主userId
    private Long fuserId; //朋友userId
    private String friendName; //备注名称
    /**
     * 好友状态，屏蔽等
     */
    private Integer power;
    private Date createTime;
    private Integer status = 0;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
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

    public Long getMuserId() {
        return muserId;
    }

    public void setMuserId(Long muserId) {
        this.muserId = muserId;
    }

    public Long getFuserId() {
        return fuserId;
    }

    public void setFuserId(Long fuserId) {
        this.fuserId = fuserId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
