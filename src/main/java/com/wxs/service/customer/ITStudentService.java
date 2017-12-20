package com.wxs.service.customer;

import com.wxs.entity.comment.TDyimg;
import com.wxs.entity.comment.TDynamicmsg;
import com.wxs.entity.customer.TStudent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITStudentService extends IService<TStudent> {
    Map<String, List> getMyFollow(Long userId); //我的关注
    Map<String, Object> getMyCourses(Long userId); //我的课程
    List<Map<String,Object>> isEndMyCourses(Long userId,Integer isEnd);
    Map<String, Object> saveMygrowth(List<TDyimg> dyimgs, TDynamicmsg dynamic, Long workId);
    public Map<Long, String> queryStudentByUserId(Long userId);
    public Map<String,Object> saveStudent(TStudent student);
}
