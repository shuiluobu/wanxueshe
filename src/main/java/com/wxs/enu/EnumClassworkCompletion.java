package com.wxs.enu;

/**
 * 学生作业 完成情况  表 t_student_work ,字段 completion
 * Created by Administrator on 2017/12/21.
 */
public enum EnumClassworkCompletion {
    NOT_HAND_IN("NOT_HAND_IN","未提交"),
    SUBMITTED("SUBMITTED","已提交"),
     FINISHED("FINISHED","已完成");
    ;

    private String typeCode; //类型编号
    private String typeName; //类型名称

    EnumClassworkCompletion(String typeCode, String typeName) {
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
        for(EnumClassworkCompletion e :EnumClassworkCompletion.values()){
            if(e.getTypeCode().equals(typeCode))
                return e.getTypeName();
        }
        return null;
    }
}
