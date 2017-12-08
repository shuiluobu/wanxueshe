package com.wxs.mapper.organ;

import com.wxs.entity.organ.TOrganComment;
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
 * @since 2017-12-08
 */
public interface TOrganCommentMapper extends BaseMapper<TOrganComment> {

    //获取 某项 的 全部评论
    @Select(" select t.*,a.headImg fromUserHeadImg from t_organ_comment t left join t_front_user a on a.id = t.fromUserId " +
            " where itemId = #{itemId}  and type = #{type} order by createTime")
    @ResultMap("BaseResultMap")
    public List<TOrganComment> getAllById(@Param("itemId") Long itemId,@Param("type")Integer type);
}