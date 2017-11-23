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
 * <p>教育机构
 * </p>
 *
 * @author wyh
 * @since 2017-09-21
 */
@TableName("t_organization")
public class TOrganization extends Model<TOrganization> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                //主键，自增
    private Long userId;           //用户Id
    private String organCode;       //机构编号
    private String organName;       //机构名称
    private String province;        //所在省
    private String city;            //所在城市
    private String cityarea;        //所在市的区
    private String address;         //详细地址
    private String linkman;         //机构联系人
    private String telePhone;       //座机
    private String mobilePhone;     //手机号
    private String coordinate;      //坐标
    private Integer leval;          //等级
    private String organRemark;     //机构备注
    private String introduce;       //机构介绍
    private Date createTime;        //创建时间
    private Integer status;         //状态 1:启用，0：禁止
    private Long createUser;        //添加该 机构的 用户Id(userId)
    private String logoImg;         //图标


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

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityarea() {
        return cityarea;
    }

    public void setCityarea(String cityarea) {
        this.cityarea = cityarea;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getLeval() {
        return leval;
    }

    public void setLeval(Integer leval) {
        this.leval = leval;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getOrganRemark() {
        return organRemark;
    }

    public void setOrganRemark(String organRemark) {
        this.organRemark = organRemark;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
