package com.wxs.service.record;

import com.wxs.entity.record.TBusinessRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构不同业务的操作记录表 服务类
 * </p>
 *
 * @author wyh
 * @since 2018-01-05
 */
public interface ITBusinessRecordService extends IService<TBusinessRecord> {

    //获取某学生的 所有 相关 操作记录
    List<TBusinessRecord> getAllByStuId(Long studentId);
    //根据 签到任务Id 获取 任务所属 课程与课时
    Map<String,String> signInCourseLesson(Long taskId);
}
