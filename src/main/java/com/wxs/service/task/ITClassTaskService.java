package com.wxs.service.task;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.task.TClassTask;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-11-24
 */
public interface ITClassTaskService extends IService<TClassTask> {
    Map<String,Object> getClassTaskMap(Long taskId);
}
