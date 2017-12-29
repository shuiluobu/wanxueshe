package com.wxs.entity.organ;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 机构学生的联系人
 * </p>
 *
 * @author skyer
 * @since 2017-12-29
 */
@TableName("t_ostudent_contacts")
public class TOstudentContacts extends Model<TOstudentContacts> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long ostudentId;
	private String contactsName;
	private String contactsPhone;
    /**
     * 联系人：1：父亲，2：母亲，3：朋友，4：其他
     */
	private Integer relationType;
	private Date createTime;
	private Long createUserId;
	private Integer status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOstudentId() {
		return ostudentId;
	}

	public void setOstudentId(Long ostudentId) {
		this.ostudentId = ostudentId;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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
