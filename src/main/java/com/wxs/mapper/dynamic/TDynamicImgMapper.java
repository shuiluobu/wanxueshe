package com.wxs.mapper.dynamic;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.comment.TDynamicImg;
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
public interface TDynamicImgMapper extends BaseMapper<TDynamicImg> {
    //根据动态Id  dynamicID 获取  其下 所有动态图片
    @Select(" select * from t_dynamic_img where dynamicId = #{dynamicId}")
    @ResultMap("BaseResultMap")
    public List<TDynamicImg> getAllByDynamicId(@Param("dynamicId") Long dynamicId);
}