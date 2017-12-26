package com.wxs.service.course;

import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.course.TCourse;

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
public interface ITCoursesService extends IService<TCourse> {

    //分页+混合条件 查询课程
    List<TCourse> pageData(TCourse course);
    /**
     * 课程基本信息
     * @param coursesId
     * @return
     */
    Map<String,Object> getCourseOutlineInfo(Long coursesId,Long userId);

    /**
     * 获取关注该课程的所有用户
     * @param coursesId
     * @return
     */
    List<Map<String,Object>> getCourseFllowUserList(Long coursesId,Long loginUserId);

    /**
     * 获取课程的所有课时，如果订阅了该课程，则显示课时状态
     * @param courseId
     * @param studentId
     * @return
     */
    Map<String,Object> getLessesonByCourse(Long courseId, Long studentId);

    /**
     * 家长端通过小程序添加课程
     * @param userId
     * @param param
     * @return
     */
    Map<String,Object> addCourseByApp(Long userId,Map param);

    List<Map<String,Object>> getFollowCourseInfoByUserId(Long userId);

    /**
     * 获取用户的课程基本信息
     * @param organId
     * @param page
     * @param endType
     * @return
     */
    //List<Map<String,Object>> getUserCourseInfo(Long organId,Integer page,Integer endType);
}
