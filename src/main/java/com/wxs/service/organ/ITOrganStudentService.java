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
	
}
