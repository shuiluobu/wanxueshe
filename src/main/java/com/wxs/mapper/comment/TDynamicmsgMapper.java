package com.wxs.mapper.comment;

import com.wxs.entity.comment.TDynamicmsg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface TDynamicmsgMapper extends BaseMapper<TDynamicmsg> {
    public List<Map<String, Object>> getDynamicmsgByParam(Map<String, Object> param);
    public List<Map<String,Object>> getFriendDynamicmsgByUserId(Long userId);

}