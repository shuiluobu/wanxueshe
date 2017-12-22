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
    List<Map<String,Object>> getStudentOfUser(Long userId);
    Map<String, Object> getMyCourses(Long userId); //我的课程
    List<Map<String,Object>> isEndMyCourses(Long userId,Integer isEnd);
    Map<String, Object> saveMygrowth(List<String> mediaUrls,String mediaType, TDynamicmsg dynamic, Long workId);
    public List<Map<String, Object>> queryStuInfoByUserId(Long userId);
    public Map<String,Object> saveStudent(TStudent student);
    Integer delStudent(Long studentId,Long userId);
}
