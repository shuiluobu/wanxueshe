package com.wxs.service.task;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.task.TStudentTask;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-11-24
 */
public interface ITStudentTaskService extends IService<TStudentTask> {
    Map<String,Object> getStudentTaskDetail(Long studentId);
}
