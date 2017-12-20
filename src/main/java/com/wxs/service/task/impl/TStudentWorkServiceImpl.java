package com.wxs.service.task.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.wxs.entity.task.TStudentWork;
import com.wxs.mapper.task.TStudentWorkMapper;
import com.wxs.service.task.ITClassWorkService;
import com.wxs.service.task.ITStudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author skyer
 * @since 2017-11-24
 */
@Service
public class TStudentWorkServiceImpl extends ServiceImpl<TStudentWorkMapper, TStudentWork> implements ITStudentWorkService {
    @Autowired
    private ITClassWorkService classWorkService;
    public Map<String,Object> getStudentWorkDetail(Long studentWorkId){
        Map<String,Object> studentTaskMap = Maps.newHashMap();
        try{
            TStudentWork studentWork = this.selectById(studentWorkId);

            return studentTaskMap;
        }catch ( Exception e){

        }

        //todo
        return  studentTaskMap;

    }
}
