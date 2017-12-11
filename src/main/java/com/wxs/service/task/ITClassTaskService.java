package com.wxs.service.task;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.task.TClassTask;

import java.util.List;
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
    Map<String,Object> saveStudentWork(List<TDyimg> dyimgs, TDynamicmsg dynamic, Long workId);
}
