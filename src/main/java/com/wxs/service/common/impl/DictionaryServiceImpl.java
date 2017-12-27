package com.wxs.service.common.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.wxs.cache.ICache;
import com.wxs.entity.common.TDictionary;
import com.wxs.entity.common.TSubjectTypeDict;
import com.wxs.mapper.common.TSubjectTypeDictMapper;
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
    public TSubjectTypeDictMapper subjectTypeDictMapper;
    @Autowired
    public TDictionaryMapper dictionaryMapper;

    public final String SUBJECT_TYPE_DICT = "SUBJECT_TYPE_DICT"; //课程的学科分类类型

    public final String LESSON_STUDY_STATUS = "LESSON_STUDY_STATUS"; //学生课时完成情况

    public final String REMIND_MEDIA_TYPE = "REMIND_MEDIA_TYPE"; //消息通知媒介

    public final String DATA_AUTHORITY = "DATA_AUTHORITY";

    public final String DYNAMIC_TYPE = "DYNAMIC_TYPE";

    public final String STUDENT_PARENT_TYPE = "STUDENT_PARENT_TYPE";//学生与家长关系

    public final String WORK_COMPLETION_STATUS = "WORK_COMPLETION_STATUS";//作业完成情况

    @Override
    public Map<String, Object> querySubjectTypeDicts() {
        Map<String, Object> result = Maps.newHashMap();
        List<Map<String, Object>> dicts = subjectTypeDictMapper.getSubjectTypeByParentCode("00");
        result.put("levalA", dicts);
        Map<String, Object> dict2Map = new HashMap<>();
        for (Map<String, Object> dict : dicts) {
            List<Map<String, Object>> dict2s = subjectTypeDictMapper.getSubjectTypeByParentCode(dict.get("code").toString());
            dict2Map.put(dict.get("code").toString(), dict2s);
        }
        result.put("levalB", dict2Map);
        return result;
    }


    @Override
    public Map<String, Object> getSubjectTypeDict() {
        Object codeCache = cache.getCache(SUBJECT_TYPE_DICT);
        if (codeCache == null) {
            Map<String, Object> codeMap = new HashMap<String, Object>();
            List<TSubjectTypeDict> dicts = subjectTypeDictMapper.selectList(null);
            for (TSubjectTypeDict dict : dicts) {
                codeMap.put(dict.getParentCode(), dict);
            }
            cache.putCache(SUBJECT_TYPE_DICT, codeMap, 1000 * 60 * 60 * 24);
            return codeMap;
        } else {
            return (Map<String, Object>) codeCache;
        }
    }

    /**
     * @param code
     * @param type 返回类型，返回一级目录，还是两个都返回 type=1，返回一级目录，type=2 返回两个都返回
     * @return
     */
    @Override
    public String getSubjectTypeValue(String code, String type) {
        String type1 = "";
        String type2 = "";
        if (code != null) {
            TSubjectTypeDict dict = (TSubjectTypeDict) getSubjectTypeDict().get(code);
            type1 = dict.getSubjectTypeName();
            if (!dict.getParentCode().equals("00")) {
                String code2 = dict.getParentCode();
                TSubjectTypeDict dict2 = (TSubjectTypeDict) getSubjectTypeDict().get(code2);
                type2 = dict2.getSubjectTypeName();
            }
        }
        if (type.equals("1")) {
            return type2;
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
     *
     * @return
     */
    @Override
    public Map<String, String> getLessonStudyStatus() {
        return getDictByType(LESSON_STUDY_STATUS);
    }

    @Override
    public Map<String, String> getRemindMediaType() {
        return getDictByType(REMIND_MEDIA_TYPE);
    }

    @Override
    public Map<String, String> getDynamicType() {
        return getDictByType(DYNAMIC_TYPE);
    }

    @Override
    public Map<String, String> getStudentParentType() {

        return getDictByType(STUDENT_PARENT_TYPE);
    }
    @Override
    public Map<String,String> getWorkcompletionStatus(){
        return getDictByType(WORK_COMPLETION_STATUS);
    }

    @Override
    public Map<String, String> getDataAuthority() {
        return getDictByType(DATA_AUTHORITY);
    }

    private Map<String, String> getDictByType(String type) {
        Object codeCache = cache.getCache(type);
        if (codeCache == null) {
            Map<String, String> codeMap = new HashMap<String, String>();
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq("type", type);
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
