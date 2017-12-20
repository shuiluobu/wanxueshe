package com.wxs.mapper.task;

import com.wxs.entity.task.TClock;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyh
 * @since 2017-12-20
 */
public interface TClockMapper extends BaseMapper<TClock> {
    //根据 课时Id  获取 其下 所有的 签到
    @Select(" select * from t_clock where lessonId = #{lessonId}")
    public List<TClock> getAllByLessonId(@Param("lessonId") Long lessonId);
}