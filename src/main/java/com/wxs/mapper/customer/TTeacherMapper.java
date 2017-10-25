package com.wxs.mapper.customer;

import com.wxs.entity.customer.TTeacher;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface TTeacherMapper extends BaseMapper<TTeacher> {
    //使用@Param注解后，可以在mapper映射文件中不用 parameterType参数
    Map selectTeacherById(@Param("tid") Long tid);

    List<Map<String,Object>> getClassByTeacher(Long tid);

}