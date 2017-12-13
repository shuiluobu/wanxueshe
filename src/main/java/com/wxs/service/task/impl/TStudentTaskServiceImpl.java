package com.wxs.service.task.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.wxs.entity.task.TStudentTask;
import com.wxs.mapper.task.TStudentTaskMapper;
import com.wxs.service.task.ITClassTaskService;
import com.wxs.service.task.ITStudentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxs.core.util.BaseUtil;

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
public class TStudentTaskServiceImpl extends ServiceImpl<TStudentTaskMapper, TStudentTask> implements ITStudentTaskService {
    @Autowired
    private ITClassTaskService classTaskService;
    public Map<String,Object> getStudentTaskDetail(Long studentTaskId){
        Map<String,Object> studentTaskMap = Maps.newHashMap();
        try{
            TStudentTask studentTask = this.selectById(studentTaskId);

            return studentTaskMap;
        }catch ( Exception e){

        }

        //todo
        return  studentTaskMap;

    }
}
