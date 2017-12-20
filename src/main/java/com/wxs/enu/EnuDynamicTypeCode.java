package com.wxs.enu;

/**
 * @author 
 */
public enum EnuDynamicTypeCode {

	DYNAMIC_TYPE_MYWORK("MY_WORK","个人作业"),
	DYNAMIC_TYPE_MYGROWTH("MY_GROWTH","个人成长");

	/** msgType:消息类型 */
	private String typeCode;
	/** msgCode:消息内容 */
	private String typeNote;

	private EnuDynamicTypeCode(String typeCode, String typeNote) {
		this.typeNote = typeNote;
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeNote() {
		return typeNote;
	}

	public void setTypeNote(String typeNote) {
		this.typeNote = typeNote;
	}
}
