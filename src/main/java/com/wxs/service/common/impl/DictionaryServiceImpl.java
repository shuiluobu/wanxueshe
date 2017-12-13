package com.wxs.service.common.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wxs.cache.ICache;
import com.wxs.service.common.IDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private ICache cache;

    public final String COURSE_TYPE_DICT = "COURSE_TYPE_DICT"; //课程类型

    @Override
    public Map<String,String> getCourseTypeDict(String courseType){

        Object codeCache = cache.getCache(COURSE_TYPE_DICT);
        if (codeCache == null) {
            Map<String, String> codeMap = new HashMap<String, String>();

            cache.putCache(COURSE_TYPE_DICT, codeMap, 1000 * 60 * 60 * 24);
            return codeMap;
        } else {
            return (Map<String, String>) codeCache;
        }
    }

}
