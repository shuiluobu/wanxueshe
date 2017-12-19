package com.wxs.mapper.common;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.common.Sequence;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-12-19
 */
public interface SequenceMapper extends BaseMapper<Sequence> {

    @Select("SELECT NEXTVAL('courseCodeSeq') FROM DUAL")
    @ResultType(Integer.class)
    public Integer getCourseCodeSeq();

}