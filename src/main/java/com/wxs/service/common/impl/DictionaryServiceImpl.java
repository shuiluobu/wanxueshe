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

}
