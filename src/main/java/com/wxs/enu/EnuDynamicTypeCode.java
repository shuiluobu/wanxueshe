package com.wxs.enu;

/**
 * 动态类型映射表
 * @author
 */
public enum EnuDynamicTypeCode {

	DYNAMIC_TYPE_MYWORK("MY_WORK","个人作业"),
	DYNAMIC_TYPE_MYGROWTH("MY_GROWTH","个人成长"),
	DYNAMIC_TYPE_CLASS_COMMENT("CLASS_COMMENT","课堂点评"),
	DYNAMIC_TYPE_CLASSWORK("CLASS_WORK","课堂作业"),
	DYNAMIC_TYPE_CLASSWORK_COMMENT("CLASSWORK_COMMENT","作业点评");


	/** msgType:动态类型编号 */
	private String typeCode;
	/** msgCode:动态类型名称 */
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

	/**
	 * 根据 动态类型编号  获取 动态类型名称
	 * @param typeCode
	 * @return
	 */
	public static String getTypeNote(String typeCode){
		for(EnuDynamicTypeCode e :EnuDynamicTypeCode.values()){
			if(e.getTypeCode().equals(typeCode))
				return e.getTypeNote();
		}
		return null;
	}
}
