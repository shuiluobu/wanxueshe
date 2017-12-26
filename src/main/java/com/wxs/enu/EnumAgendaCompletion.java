package com.wxs.enu;

/**
 * 教育机构- 待办 - 执行情况
 * Created by Administrator on 2017/12/26.
 */
public enum EnumAgendaCompletion {
    METHOD_TEL("TEL","电话"),
    METHOD_QQ("QQ","腾讯QQ"),
    METHOD_WX("WX","微信"),
    INTENTION_GENERAL("GENERAL","一般"),
    INTENTION_NOT_BAD("NOT_BAD","还行"),
    INTENTION_ZEAL("ZEAL","热情"),
    NEXT_ACTION_CONTINUE_FOLLOW("CONTINUE_FOLLOW","继续跟进"),
    NEXT_ACTION_ABANDON_FOLLOW("ABANDON_FOLLOW","放弃跟进");

    private String typeCode; //类型编号
    private String typeName; //类型名称

    EnumAgendaCompletion(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    /**
     * 根据 动态类型编号  获取 动态类型名称
     * @param typeCode
     * @return
     */
    public static String getTypeName(String typeCode){
        for(EnumAgendaCompletion e :EnumAgendaCompletion.values()){
            if(e.getTypeCode().equals(typeCode))
                return e.getTypeName();
        }
        return null;
    }
}
