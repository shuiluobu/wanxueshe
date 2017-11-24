package com.wxs.service.comment;

import com.wxs.entity.comment.TDynamicmsg;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-09-21
 */
public interface ITDynamicmsgService extends IService<TDynamicmsg> {
	public List<Map<String,Object>> getDynamicmsgListByUserId(Long userId);
}
