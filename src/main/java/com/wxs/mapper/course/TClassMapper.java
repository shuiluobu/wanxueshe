package com.wxs.mapper.course;

import com.wxs.entity.course.TClass;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-09-21
 */
public interface TClassMapper extends BaseMapper<TClass> {

    //分页+混合条件 查询 班级
    public List<TClass> pageData(TClass tClass);

}