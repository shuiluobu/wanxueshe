package com.wxs.mapper.customer;

import com.wxs.entity.customer.TFllowCourse;
import com.wxs.entity.customer.TFollowParent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultType;
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
public interface TFollowParentMapper extends BaseMapper<TFollowParent> {
    @Select("select userId from t_follow_parent where status=0 and followPid=#{followPid}")
    @ResultType(Long.class)
    List<Long> getFllowUserIdsOfFollowPid(Long followPid);
}