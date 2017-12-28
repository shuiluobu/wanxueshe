package com.wxs.service.task;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.comment.TDynamic;
import com.wxs.entity.task.TClassWork;

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
public interface ITClassWorkService extends IService<TClassWork> {
    List<Map<String,Object>> getClassWorkInfosByUserId(Long userId);
    Map<String,Object> getClassWorkOutline(Long workId);
    Map<String,Object> saveStudentWork(List<String> mediaUrls, String mediaType, TDynamic dynamic, Long workId);
    //List<Map<String,Object>> getMyClassWorks(Long userId);
}
