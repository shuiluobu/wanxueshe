package com.wxs.service.task;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.task.TStudentWork;

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
}
