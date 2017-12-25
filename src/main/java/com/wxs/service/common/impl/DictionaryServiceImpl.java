package com.wxs.service.common.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.wxs.cache.ICache;
import com.wxs.entity.common.TCourseTypeDict;
import com.wxs.entity.common.TDictionary;
import com.wxs.mapper.common.TCourseTypeDictMapper;
import com.wxs.mapper.common.TDictionaryMapper;
import com.wxs.service.common.IDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private ICache cache;
    @Autowired
    public TCourseTypeDictMapper courseTypeDictMapper;
    @Autowired
    public TDictionaryMapper dictionaryMapper;

    public final String COURSE_TYPE_DICT = "COURSE_TYPE_DICT"; //课程类型

    public final String LESSON_STUDY_STATUS = "LESSON_STUDY_STATUS"; //学生课时完成情况

    public final String REMIND_MEDIA_TYPE = "REMIND_MEDIA_TYPE"; //消息通知媒介

    public final String DATA_AUTHORITY = "DATA_AUTHORITY";

    public final String DYNAMIC_TYPE = "DYNAMIC_TYPE";

    @Override
    public Map<String,Object> queryCourseTypeDictes(){
        Map<String,Object> result = Maps.newHashMap();
        List<Map<String,Object>> dicts = courseTypeDictMapper.getCourseTypeByParentCode("00");
        result.put("levalA",dicts);
        Map<String,Object> dict2Map = new HashMap<>();
        for(Map<String,Object> dict : dicts) {
            List<Map<String,Object>> dict2s = courseTypeDictMapper.getCourseTypeByParentCode(dict.get("code").toString());
            dict2Map.put(dict.get("code").toString(),dict2s);
        }
        result.put("levalB",dict2Map);
        return result;
    }


    @Override
    public Map<String, Object> getCourseTypeDict() {
        Object codeCache = cache.getCache(COURSE_TYPE_DICT);
        if (codeCache == null) {
            Map<String, Object> codeMap = new HashMap<String, Object>();
            List<TCourseTypeDict> dicts = courseTypeDictMapper.selectList(null);
            for (TCourseTypeDict dict : dicts) {
                codeMap.put(dict.getCourseTypeCode(), dict);
            }
            cache.putCache(COURSE_TYPE_DICT, codeMap, 1000 * 60 * 60 * 24);
            return codeMap;
        } else {
            return (Map<String, Object>) codeCache;
        }
    }

    /**
     *
     * @param code
     * @param type 返回类型，返回一级目录，还是两个都返回 type=1，返回一级目录，type=2 返回两个都返回
     * @return
     */
    @Override
    public String getCourseTypeValue(String code,String type) {
        String type1 = "";
        String type2 = "";
        if (code != null) {
            TCourseTypeDict dict = (TCourseTypeDict) getCourseTypeDict().get(code);
            type1 = dict.getCourseTypeName();
            if (!dict.getParentCode().equals("00")) {
                String code2 = dict.getParentCode();
                TCourseTypeDict dict2 = (TCourseTypeDict) getCourseTypeDict().get(code2);
                type2 = dict2.getCourseTypeName();
            }
        }
        if(type.equals("1")){
            return  type2;
        } else {
            if (!StringUtils.isEmpty(type1)) {
                return type2 + "-" + type1;
            } else {
                return "";
            }
        }
    }

    /**
     * 获取课时完成情况
     * @return
     */
    @Override
    public Map<String,String> getLessonStudyStatus(){
        return  getDictByType(LESSON_STUDY_STATUS);
    }

    @Override
    public Map<String,String> getRemindMediaType(){
        return  getDictByType(REMIND_MEDIA_TYPE);
    }
    @Override
    public Map<String,String> getDynamicType(){
        return  getDictByType(DYNAMIC_TYPE);
    }

    @Override
    public Map<String,String> getDataAuthority(){
        return getDictByType(DATA_AUTHORITY);
    }
    private Map<String,String> getDictByType(String type){
        Object codeCache = cache.getCache(type);
        if (codeCache == null) {
            Map<String, String> codeMap = new HashMap<String, String>();
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq("type",type);
            List<TDictionary> list = dictionaryMapper.selectList(wrapper);
            for (TDictionary dict : list) {
                codeMap.put(dict.getKey(), dict.getValue());
            }
            cache.putCache(type, codeMap, 1000 * 60 * 60 * 24);
            return codeMap;
        } else {
            return (Map<String, String>) codeCache;
        }
    }
}
