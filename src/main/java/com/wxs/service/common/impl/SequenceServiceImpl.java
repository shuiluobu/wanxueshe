package com.wxs.service.common.impl;

import com.wxs.mapper.common.SequenceMapper;
import com.wxs.service.common.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/19 0019.
 */
@Service("sequenceService")
public class SequenceServiceImpl implements ISequenceService {
    @Autowired
    public SequenceMapper sequenceMapper;
    @Override
    public String getCourseCode(Long userId,String type){
        Integer seq = sequenceMapper.getCourseCodeSeq();
        DecimalFormat df = new DecimalFormat("0000");
        return type + BaseUtil.toString(new Date(),"yyMMdd") + df.format(seq)+"KC" + userId;
    }

    @Override
    public String getOrganCode(Long userId,String type){
        Integer seq = sequenceMapper.getCourseCodeSeq();
        DecimalFormat df = new DecimalFormat("0000");
        return type + BaseUtil.toString(new Date(),"yyMMdd") + df.format(seq)+"JG" + userId;
    }

    @Override
    public String getCourseClassCode(Long userId){
        Integer seq = sequenceMapper.getCourseCodeSeq();
        DecimalFormat df = new DecimalFormat("0000");
        return "CL" + BaseUtil.toString(new Date(),"yyMM") + df.format(seq) + "C" + df.format(userId);
    }
}
