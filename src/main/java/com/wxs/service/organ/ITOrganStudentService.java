package com.wxs.service.organ;

import com.wxs.entity.customer.TTeacher;
import com.wxs.entity.organ.TOrganStudent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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
	
}
