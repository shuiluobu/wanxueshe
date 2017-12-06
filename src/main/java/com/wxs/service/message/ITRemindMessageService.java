package com.wxs.service.message;


import com.baomidou.mybatisplus.service.IService;
import com.wxs.entity.message.TRemindMessage;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author skyer
 * @since 2017-11-27
 */
public interface ITRemindMessageService extends IService<TRemindMessage> {
   List<Map<String,Object>> getRemindMsgByFromUid(Long uid);
   List<Map<String,Object>> getRemindMsgByToUid(Long userId);
}
