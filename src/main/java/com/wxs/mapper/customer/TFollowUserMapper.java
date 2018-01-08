package com.wxs.mapper.customer;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxs.entity.customer.TFollowUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

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
public interface TFollowUserMapper extends BaseMapper<TFollowUser> {

    public int getIsFriednCount(@Param("userId") Long userId,@Param("friendUserId") Long friendUserId);
    @Select("select fuserId from t_follow_user where userId=#{userId} and relationType=#{relationType}")
    @ResultType(long.class)
    public List<Long> getFollowUserIdsByParam(@Param("userId") Long userId,@Param("relationType") String relationType);
    @Select("select fuserId,memoName friendName from t_follow_user where userId=#{userId} and relationType=#{relationType} order by createTime")
    @ResultType(Map.class)
    public List<Map<String,Object>> getFollowUserByParam(@Param("userId") Long userId,@Param("relationType") String relationType);
}