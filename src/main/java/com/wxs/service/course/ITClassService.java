package com.wxs.service.course;

import com.wxs.entity.course.TClass;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyh
 * @since 2017-09-21
 */
public interface ITClassService extends IService<TClass> {

    //分页+混合条件 查询 班级
    public List<TClass> pageData(TClass tClass);
    //根据课程Id 获取班级
    public TClass getByCourseId(Long courseId);
	
}
