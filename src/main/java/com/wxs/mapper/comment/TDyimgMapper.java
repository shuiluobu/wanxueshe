package com.wxs.mapper.comment;

import com.wxs.entity.comment.TDyimg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface TDyimgMapper extends BaseMapper<TDyimg> {
    //根据动态Id  dynamicID 获取  其下 所有动态图片
    @Select(" select * from t_dyimg where dynamicId = #{dynamicId}")
    @ResultMap("BaseResultMap")
    public List<TDyimg> getAllByDynamicId(@Param("dynamicId") Long dynamicId);
}