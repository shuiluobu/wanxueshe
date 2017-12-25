package com.wxs.service.common;

import java.util.List;
import java.util.Map;
/*
 * 字典缓存的服务接口
 */

public interface IDictionaryService {
    public Map<String,Object> queryCourseTypeDictes();
    public Map<String,Object> getCourseTypeDict();
    public String getCourseTypeValue(String code,String type);
    public Map<String,String>  getLessonStudyStatus();
    public Map<String,String> getRemindMediaType();
    Map<String,String> getDataAuthority();
    Map<String,String> getDynamicType();

}
