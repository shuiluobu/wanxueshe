package com.wxs.service.task;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.task.TStudentWork;

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
public interface ITStudentWorkService extends IService<TStudentWork> {
    Map<String,Object> getStudentWorkDetail(Long studentId);
    //获取某机构 某学生 的 特定时间内  的 作业
    List<Map> studentWork(Long studentId,Long organId,String startTime,String endTime,String completion);
}
