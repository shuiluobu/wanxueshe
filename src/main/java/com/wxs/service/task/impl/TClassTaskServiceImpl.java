package com.wxs.service.task.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxs.entity.task.TClassTask;
import com.wxs.mapper.task.TClassTaskMapper;
import com.wxs.service.task.ITClassTaskService;
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
public class TClassTaskServiceImpl extends ServiceImpl<TClassTaskMapper, TClassTask> implements ITClassTaskService {
    @Autowired
    private TClassTaskMapper classTaskMapper;
    public Map<String,Object> getClassTaskMap(Long taskId){
       Map<String,Object> classTask = classTaskMapper.getClassTask(taskId);
       classTask.put("day",classTask.get("endTime").toString());
       return classTask;
    }

}
