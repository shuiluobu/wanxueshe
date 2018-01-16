package com.wxs.service.organ;

import com.wxs.entity.course.TClassLesson;
import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganStudent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2017-12-29
 */
public interface ITOrganStudentService extends IService<TOrganStudent> {

    //根据 机构Id  和 课程顾问名字 搜索 该机构的 课程顾问
    List<TTeacher> searchAdvisorByName(Long organId,String name);
    //根据 教育机构Id 和 学生名字 模糊搜索 学生
    List<TOrganStudent> searchByName(String name, Long organId);
    //根据 教育机构Id  获取其下 所有学生
    List<TOrganStudent> getAllByOrganId(Long organId);
    //organId + studentName 获取M某机构的 可办理 补课 的 学生以及其缺课数量
    List<Map<String,Object>> canMULessonStus(Long organId, String studentName);

	
}
