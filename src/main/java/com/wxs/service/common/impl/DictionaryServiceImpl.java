package com.wxs.service.common.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.cache.ICache;
import com.wxs.entity.dictionary.TCourseTypeDict;
import com.wxs.entity.dictionary.TDictionary;
import com.wxs.mapper.dictionary.TCourseTypeDictMapper;
import com.wxs.mapper.dictionary.TDictionaryMapper;
import com.wxs.service.common.IDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.text.DecimalFormat;
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
        Object codeCache = cache.getCache(LESSON_STUDY_STATUS);
        if (codeCache == null) {
            Map<String, String> codeMap = new HashMap<String, String>();
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq("type",LESSON_STUDY_STATUS);
            List<TDictionary> list = dictionaryMapper.selectList(wrapper);
            for (TDictionary dict : list) {
                codeMap.put(dict.getKey(), dict.getValue());
            }
            cache.putCache(LESSON_STUDY_STATUS, codeMap, 1000 * 60 * 60 * 24);
            return codeMap;
        } else {
            return (Map<String, String>) codeCache;
        }
    }
}
