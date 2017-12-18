package com.wxs.mapper.comment;

import com.wxs.entity.comment.TLike;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface TLikeMapper extends BaseMapper<TLike> {

    //根据 动态Id和用户Id  获取一条赞
    @Select(" select * from t_like where dynamicId = #{dynamicId} and createUserId = #{userId}")
    @ResultMap("BaseResultMap")
    public TLike getOneByDUId(@Param("dynamicId") Long dynamicId, @Param("userId") Long userId);
}